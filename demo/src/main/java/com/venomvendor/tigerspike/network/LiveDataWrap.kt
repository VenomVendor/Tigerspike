/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 18-Feb-2019.
 */

package com.venomvendor.tigerspike.network

import androidx.lifecycle.MutableLiveData

/**
 * This acts as wrapper around Response&lt;T>
 *
 * @param <T> Type of data
</T> */
class LiveDataWrap<T> : MutableLiveData<Response<T>>()
// Do nothing
