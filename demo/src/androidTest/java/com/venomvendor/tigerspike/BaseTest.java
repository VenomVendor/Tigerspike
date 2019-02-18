/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 18-Feb-2019.
 */

package com.venomvendor.tigerspike;

import com.venomvendor.sdk.core.network.NetworkManager;
import com.venomvendor.tigerspike.testhelper.TestHelper;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;

@RunWith(AndroidJUnit4.class)
public abstract class BaseTest {

    static MockWebServer server;
    static TestHelper testHelper;

    @BeforeClass
    public static void setUpAll() throws Exception {
        server = new MockWebServer();
        server.start();

        Retrofit executor = new TestHelper(null).getExecutor(server.url("/").toString());

        NetworkManager.getInstance().setExecutor(executor);
    }

    @Before
    public void setUp() {
        testHelper = new TestHelper(this);
    }
}
