/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 18-Feb-2019.
 */

package com.venomvendor.tigerspike.network

import android.util.Pair

import androidx.annotation.CheckResult

/**
 * When Live data is used, error cannot be dispatched directly.
 * Hence live data is wrapped inside response which can deliver response &amp; error
 *
 * @param <T> Type of response.
</T> */
@Suppress("unused")
class Response<T>
/**
 * Constructor for a Pair.
 *
 * @param first the first object in the Pair
 * @param second the second object in the pair
 */
private constructor(first: T?, second: Throwable?) : Pair<T, Throwable>(first, second) {

    /**
     * Get data or `null`
     */
    val data: T? = first

    /**
     * Get error or `null`
     */
    val error: Throwable? = second

    companion object {

        /**
         * Create Response with data
         */
        @CheckResult
        fun <T> createResponse(data: T?): Response<T> {
            return createResponse(data, null)
        }

        /**
         * Create Response with error
         */
        @CheckResult
        fun <T> createResponse(err: Throwable?): Response<T> {
            return createResponse(null, err)
        }

        /**
         * Create Response with data &amp; error
         */
        @CheckResult
        fun <T> createResponse(data: T?, err: Throwable?): Response<T> {
            if (data == null && err == null) {
                throw IllegalArgumentException("Data & Error are null")
            }
            return Response(data, err)
        }
    }
}
