/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 18-Feb-2019.
 */

package com.venomvendor.tigerspike.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.venomvendor.sdk.core.model.gallery.Feed
import com.venomvendor.tigerspike.R
import com.venomvendor.tigerspike.ui.factory.OnItemClickListener
import com.venomvendor.tigerspike.util.DiffUtilHelper

/**
 * Recycler Adapter for displaying list of results.
 */
internal class GalleryListAdapter(glideManager: RequestManager) :
    ListAdapter<Feed, GalleryListAdapter.GalleryViewHolder>(DiffUtilHelper.FEED_DIFF) {

    // Image renderer
    private val glide = glideManager
    // Event listener
    private var itemClickListener: OnItemClickListener<Feed>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view = LayoutInflater.from(parent.context)
            // Update item view
            .inflate(R.layout.gallery_item, parent, false)

        return GalleryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        // Get item
        val feed = getItem(position)

        // Update views
        holder.author.text = feed.author
        holder.published.text = feed.published.toString()

        // Load image
        glide.load(feed.media.medium)
            .centerCrop()
            // place holder until image loads
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.media)
    }

    fun setOnItemClickLister(listener: OnItemClickListener<Feed>?) {
        itemClickListener = listener
    }

    /**
     * View Holder pattern, used by recycler view.
     */
    internal inner class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        internal val author: TextView = itemView.findViewById(R.id.author)
        internal val published: TextView = itemView.findViewById(R.id.desc)
        internal val media: ImageView = itemView.findViewById(R.id.media)

        init {
            // Add event listener
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            val selectedPos = adapterPosition
            if (selectedPos == RecyclerView.NO_POSITION) {
                return
            }

            // Pass on to root level event listener
            itemClickListener?.onClick(getItem(selectedPos), view, selectedPos)
        }
    }
}
