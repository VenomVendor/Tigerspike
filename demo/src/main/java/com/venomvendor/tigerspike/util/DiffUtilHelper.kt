/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 18-Feb-2019.
 */

package com.venomvendor.tigerspike.util

import androidx.recyclerview.widget.DiffUtil
import com.venomvendor.sdk.core.model.gallery.Feed

/**
 * Helper Utility for Recycler View's Data.
 */
class DiffUtilHelper private constructor() {

    init {
        throw UnsupportedOperationException("Instance should not be created")
    }

    companion object {

        val FEED_DIFF: DiffUtil.ItemCallback<Feed> = object : DiffUtil.ItemCallback<Feed>() {
            override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
                return oldItem.authorId == newItem.authorId
                    && oldItem.media?.medium == newItem.media?.medium
            }

            override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
                return oldItem.description == newItem.description
            }
        }
    }
}
