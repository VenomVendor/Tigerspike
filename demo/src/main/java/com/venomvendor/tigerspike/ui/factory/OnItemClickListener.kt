/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 18-Feb-2019.
 */

package com.venomvendor.tigerspike.ui.factory

import android.view.View

/**
 * Handle item click
 *
 * @param <T> Type of data
</T> */
interface OnItemClickListener<T> {

    /**
     * Callback when item is clicked
     */
    fun onClick(item: T, view: View, position: Int)
}
