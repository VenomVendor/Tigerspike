/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 17-Feb-2019.
 */

package com.venomvendor.sdk.core.data

import com.venomvendor.sdk.core.data.factory.Repository
import com.venomvendor.sdk.core.model.gallery.PublicFeed
import io.reactivex.Single
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertSame
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock

class RepositoryManagerTest {

    @Test
    fun instanceTest() {
        assertSame(RepositoryManager.getInstance(), RepositoryManager.getInstance())
    }

    @Test
    fun executeTest() {
        val instance = RepositoryManager.getInstance()
        val mock = mock(Repository::class.java)
        assertNotNull(instance.execute(mock))
    }

    @Test
    fun getFromCache() {
        val instance = RepositoryManager.getInstance()
        val mock = mock(Repository::class.java)
        val mockFeed = mock(PublicFeed::class.java)

        doReturn(mockFeed)
            .`when`<Repository<*>>(mock)
            .cachedData

        assertSame(mockFeed, instance.execute(mock).blockingGet())
    }

    @Test
    fun getFromServer() {
        val instance = RepositoryManager.getInstance()
        val mock = mock(Repository::class.java)

        val fakeFeed = mock(PublicFeed::class.java)
        val fakeFeedObs = Single.just(fakeFeed)

        doReturn(null)
            .`when`<Repository<*>>(mock)
            .cachedData

        doReturn(fakeFeedObs)
            .`when`<Repository<*>>(mock)
            .request

        assertSame(fakeFeed, instance.execute(mock).blockingGet())
    }
}
