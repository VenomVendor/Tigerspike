/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 18-Feb-2019.
 */

package com.venomvendor.tigerspike.util

/**
 * App level constants
 */
class AppConstant private constructor() {

    init {
        throw UnsupportedOperationException("Instance should not be created")
    }

    companion object {

        const val DATA_ITEM = "DATA_ITEM"
    }
}
