/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 17-Feb-2019.
 */

package com.venomvendor.tigerspike.testhelper

import androidx.annotation.CallSuper
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.rules.Timeout
import retrofit2.Retrofit

abstract class BaseTest {
    lateinit var server: MockWebServer
    lateinit var testHelper: TestHelper
    lateinit var executor: Retrofit

    @Rule
    @JvmField
    var globalTimeout = Timeout.seconds(10)!!

    @CallSuper
    @Before
    @Throws(Exception::class)
    open fun setUp() {
        testHelper = TestHelper.with(this)
        server = MockWebServer()
        server.start()

        executor = testHelper.getExecutor(server.url("/").toString())
    }
}
