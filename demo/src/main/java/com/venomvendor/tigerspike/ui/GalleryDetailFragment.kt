/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 18-Feb-2019.
 */

package com.venomvendor.tigerspike.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation.findNavController
import com.bumptech.glide.Glide
import com.venomvendor.sdk.core.model.gallery.Feed
import com.venomvendor.tigerspike.R
import com.venomvendor.tigerspike.common.BaseFragment
import com.venomvendor.tigerspike.util.AppConstant
import kotlinx.android.synthetic.main.fragment_gallery_detail.*
import java.util.Objects
import kotlin.reflect.KClass

/**
 * Detailed view of selected Gallery.
 */
class GalleryDetailFragment : BaseFragment<ViewModel>() {
    override fun initViews(view: View) {
    }

    // Selected item
    private var feed: Feed? = null

    @LayoutRes
    // Return current layout
    override val layout = R.layout.fragment_gallery_detail

    // No ViewModel required, pass null
    override val viewModelClz: KClass<ViewModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retrieve data from previous view
        feed = arguments?.getParcelable(AppConstant.DATA_ITEM)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (feed == null) {
            // It no data is available, return to previous view
            findNavController(Objects.requireNonNull<View>(view)).navigateUp()
        } else {
            // Update data.
            setData()
        }
    }

    /**
     * Update data to views.
     */
    private fun setData() {
        feed?.let {
            author.text = it.title
            desc.text = it.description
            url.text = it.link
            tags.text = it.tags
            published.text = it.published.toString()
            // Load image
            Glide.with(this)
                .load(it.media.medium)
                .centerCrop()
                // place holder until image loads
                .placeholder(R.drawable.ic_launcher_background)
                .into(media)
        }
    }
}
