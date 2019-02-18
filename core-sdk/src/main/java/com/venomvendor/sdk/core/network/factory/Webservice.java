/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 17-Feb-2019.
 */

package com.venomvendor.sdk.core.network.factory;

import com.venomvendor.sdk.core.model.gallery.PublicFeed;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Skeleton template of RESTful webservices, that are used to communicate with server.
 */
public interface Webservice {

    /**
     * Get public feed matching tags
     * <p>
     * To get list of api params format refer
     * <a href="https://www.flickr.com/services/feeds/docs/photos_public">Public feed</a>
     *
     * @param lang response language
     * @param format Response format ex: <b>json</b>,
     * more at <a href="https://www.flickr.com/services/feeds/">Feed Formats</a>
     * @param ignoreJsonCallback Should json callback be ignored in response
     * @return SingleObservable to subscribe the result.
     */
    @GET("/services/feeds/photos_public.gne")
    Single<PublicFeed> getPublicFeed(@NonNull @Query("lang") String lang,
                                     @NonNull @Query("format") String format,
                                     @IntRange(from = 0, to = 1) @Query("nojsoncallback") int ignoreJsonCallback);
}
