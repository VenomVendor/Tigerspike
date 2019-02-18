/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 18-Feb-2019.
 */

package com.venomvendor.tigerspike.network

import androidx.annotation.CheckResult
import io.reactivex.functions.BiConsumer

/**
 * Bridge between Rx, LiveData &amp; Error.
 *
 * @param <T>
</T> */
class LiveDataSubscriber<T> private constructor(liveDataHolder: LiveDataWrap<T>) :
    BiConsumer<T, Throwable> {

    private val dataWrapper: LiveDataWrap<T> = liveDataHolder

    override fun accept(data: T?, ex: Throwable?) {
        // Dispatch result wrapped inside Response.
        dataWrapper.postValue(Response.createResponse(data, ex))
    }

    companion object {

        /**
         * Create Live Data Subscriber
         *
         * @param liveDataHolder data holder
         * @param <T> type of data
        </T> */
        @CheckResult
        fun <T> with(liveDataHolder: LiveDataWrap<T>): LiveDataSubscriber<T> {
            return LiveDataSubscriber(liveDataHolder)
        }
    }
}
