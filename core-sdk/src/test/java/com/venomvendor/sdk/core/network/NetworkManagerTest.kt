/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 17-Feb-2019.
 */

package com.venomvendor.sdk.core.network

import org.junit.Assert.assertNotSame
import org.junit.Assert.assertSame
import org.junit.Test

class NetworkManagerTest {

    @Test
    fun instanceTest() {
        assertSame(NetworkManager.getInstance(), NetworkManager.getInstance())
    }

    @Test
    fun webServiceInstanceTest() {
        val instance = NetworkManager.getInstance()
        assertNotSame(instance.webservice, instance.webservice)
    }

    @Test
    fun setExecutorTest() {
        val instance = NetworkManager.getInstance()
        val executor = instance.executor

        instance.executor = null

        assertNotSame(executor, instance.executor)

        instance.executor = executor
        assertSame(executor, instance.executor)
    }
}
