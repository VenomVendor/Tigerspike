/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 18-Feb-2019.
 */

@file:Suppress("unused")

package com.venomvendor.tigerspike.viewmodel

import androidx.annotation.CheckResult
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.venomvendor.sdk.core.model.gallery.Feed
import com.venomvendor.sdk.core.repository.SearchRepository
import com.venomvendor.tigerspike.network.LiveDataSubscriber
import com.venomvendor.tigerspike.network.LiveDataWrap
import com.venomvendor.tigerspike.network.Response
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.reactivestreams.Publisher

/**
 * To get data from Flickr.
 * This has combined Rx &amp; Livedata. Ideally, either one is enough.
 * In this case Rx
 */
class FlickrViewModel : ViewModel() {
    /* Create Repository that acts as single source of data */
    private val repository = SearchRepository()
    /* Create live data holder. */
    private val liveData = LiveDataWrap<List<Feed>>()
    private var disposables: CompositeDisposable? = null

    /**
     * THIS API WRAPS **DATA** &amp; **ERROR**
     * ******************************************
     * Inside RESPONSE with [LiveData]
     * ******************************************
     * Get list of Public Feed
     *
     * @return LiveData of [Feed]
     */
    val publicFeed: LiveData<Response<List<Feed>>>
        @CheckResult
        get() {
            if (disposables == null) {
                // Create disposables
                disposables = CompositeDisposable()
            }

            disposables!!.add(
                // Get data
                repository.getPublicFeed(LANG)
                    // Subscribe for Rx result with LiveData
                    .subscribe(LiveDataSubscriber.with(liveData))
            )
            return liveData
        }

    /**
     * THIS API WRAPS **DATA**
     * ******************************************
     * Inside [Single]. Error is propagated as is.
     * ******************************************
     * Get list of Public Feed
     *
     * @return LiveData of [Feed] wrapped inside response.
     */
    @get:CheckResult
    val publicFeedRx: Single<List<Feed>> = repository.getPublicFeed(LANG)

    /**
     * THIS API WRAPS **DATA** &amp; **ERROR**
     * ******************************************
     * Inside [Publisher], [Response], with [LifecycleOwner]
     * ******************************************
     * Get list of Public Feed
     *
     * @param lifecycle Update current instance with lifecycle owner for further operations
     * @return LiveData of [Feed] wrapped inside response.
     */
    @CheckResult
    fun getPublicFeed(lifecycle: LifecycleOwner): Publisher<Response<List<Feed>>> =
        LiveDataReactiveStreams.toPublisher(lifecycle, publicFeed)

    override fun onCleared() {
        super.onCleared()
        // Clean data observers
        if (disposables != null) {
            disposables!!.clear()
            disposables!!.dispose()
            disposables = null
        }
    }

    companion object {

        // Can be changed to runtime value.
        private const val LANG = "en-us"
    }
}
