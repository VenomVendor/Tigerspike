/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 17-Feb-2019.
 */

package com.venomvendor.sdk.core.repository

import com.venomvendor.sdk.core.network.NetworkManager
import com.venomvendor.tigerspike.testhelper.BaseTest
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class SearchRepositoryTest : BaseTest() {

    private val searchRepository = SearchRepository()

    @Before
    @Throws(Exception::class)
    override fun setUp() {
        super.setUp()
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        NetworkManager.getInstance().setExecutor(executor)
    }

    @Test(expected = NullPointerException::class)
    fun getErrorForNullLang() {
        // This is invalid test, this has to be tested via java.
        searchRepository.getPublicFeed(null!!)
    }

    @Test
    @Throws(InterruptedException::class)
    fun getPublicFeed() {
        val response = testHelper.read("response-200-ok.json")
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(response)
        )

        // Can be replaced by Observable<>.test().assert();
        // Not enough time.
        val latch = CountDownLatch(1)
        searchRepository.getPublicFeed("en-us")
            .subscribe { feeds, throwable ->
                assertFalse(feeds.isEmpty())
                assertNull(throwable)

                assertEquals(20, feeds.size.toLong())
                latch.countDown()
            }
        latch.await(3L, TimeUnit.SECONDS)
    }

    @Test
    @Throws(InterruptedException::class)
    fun getPublicFeedWithError() {
        val response = testHelper.read("response-200-error.json")
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(response)
        )

        try {
            searchRepository.getPublicFeed("en-us").blockingGet()
            fail("I Shouldn't succeed")
        } catch (ex: Exception) {
            // With Custom Exception, validation can be improved
            assertTrue(ex.message!!.contains("code = '100'"))
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun getPublicFeedWithError404() {
        server.enqueue(
            MockResponse()
                .setResponseCode(404)
                .setBody("{}")
        )

        try {
            searchRepository.getPublicFeed("en-us").blockingGet()
            fail("I Shouldn't succeed")
        } catch (ex: Exception) {
            // With Custom Exception, validation can be improved
            assertTrue(ex.message!!.contains("404"))
        }
    }
}
