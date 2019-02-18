/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 18-Feb-2019.
 */

package com.venomvendor.tigerspike.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.venomvendor.sdk.core.model.gallery.Feed
import com.venomvendor.tigerspike.R
import com.venomvendor.tigerspike.common.BaseFragment
import com.venomvendor.tigerspike.network.Response
import com.venomvendor.tigerspike.ui.adapter.GalleryListAdapter
import com.venomvendor.tigerspike.ui.factory.OnItemClickListener
import com.venomvendor.tigerspike.util.AppConstant
import com.venomvendor.tigerspike.viewmodel.FlickrViewModel
import kotlinx.android.synthetic.main.gallery_list_fragment.*

/**
 * Main View for listing public feed.
 */
class GalleryListFragment : BaseFragment<FlickrViewModel>(), OnItemClickListener<Feed> {

    /* Adapter holding data. */
    private lateinit var galleryAdapter: GalleryListAdapter

    @LayoutRes
    // Return current layout
    override val layout = R.layout.gallery_list_fragment

    public override val viewModelClz = FlickrViewModel::class

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Initialize Listeners
        initListener()

        // Fetch/Update Data
        getData()
    }

    override fun initViews(view: View) {
        // For performance
        feed_list.setHasFixedSize(true)
        // Set layout type

        updateLayoutManager()

        // Create Image Renderer
        val glide = Glide.with(this)
        // Create Adapter
        galleryAdapter = GalleryListAdapter(glide)
        // Set adapter
        feed_list.adapter = galleryAdapter
    }

    private fun updateLayoutManager() {
        feed_list.layoutManager = GridLayoutManager(context, 2)
    }

    /**
     * Initialize event listeners
     */
    private fun initListener() {
        galleryAdapter.setOnItemClickLister(this)
        refresh.setOnRefreshListener { getData() }
    }

    /**
     * Fetch data
     */
    private fun getData() {
        progress_bar.visibility = View.VISIBLE
        viewModel.publicFeed
            .observe(viewLifecycleOwner, Observer<Response<List<Feed>>> { consumeData(it) })
    }

    /**
     * Handler for data received
     *
     * @param response data
     */
    private fun consumeData(response: Response<List<Feed>>) {
        // TODO: Handle internet error.

        updateLayoutManager()

        // Update indicators
        progress_bar.visibility = View.GONE
        refresh.isRefreshing = false

        // Get result
        val data = response.data

        // Get error
        val error = response.error
        if (error != null) {
            // Show error toast
            Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
        } else {
            // Update data.
            galleryAdapter.submitList(data)
        }
    }

    override fun onClick(item: Feed, view: View, position: Int) {
        // Create bundle to pass data.
        val args = Bundle()
        args.putParcelable(AppConstant.DATA_ITEM, item)

        // Get Navigation controller
        findNavController(view)
            // Navigate to next view with predefined action & data.
            .navigate(R.id.action_gallery_list_fragment_to_gallery_detail_fragment, args)
    }
}
