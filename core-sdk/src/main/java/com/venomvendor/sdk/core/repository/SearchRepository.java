/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 17-Feb-2019.
 */

package com.venomvendor.sdk.core.repository;

import android.annotation.SuppressLint;

import com.venomvendor.sdk.core.data.RepositoryManager;
import com.venomvendor.sdk.core.data.factory.Repository;
import com.venomvendor.sdk.core.model.gallery.Feed;
import com.venomvendor.sdk.core.model.gallery.PublicFeed;
import com.venomvendor.sdk.core.network.NetworkManager;

import java.util.List;
import java.util.Objects;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Responsible getting data from cache or server when requested.
 */
public class SearchRepository implements Repository<List<Feed>> {

    // Query prefix
    private static final String RES_FORMAT = "json";
    private static final int IGNORE_JSON_CB = 1;

    private String mLang;

    @Nullable
    @Override
    public List<Feed> getCachedData() {
        // No Cache
        return null;
    }

    @NonNull
    @Override
    public Single<List<Feed>> getRequest() {
        // Creates network request to get data from server.
        return NetworkManager.getInstance()
                // Get service
                .getWebservice()
                // Get Api
                .getPublicFeed(mLang, RES_FORMAT, IGNORE_JSON_CB)
                // Check result
                // Move this to common location, that's applicable for all api calls by default
                .map(new ResponseValidator<PublicFeed>())
                // TODO: This will break if data is fake `{}`, Move on for now
                // Transform result
                .map(PublicFeed::getFeeds)
                // Save Data
                .doOnSuccess(this::saveData);
    }

    @SuppressLint("CheckResult")
    @Override
    public void saveData(@NonNull List<Feed> data) {
        // TODO: This can be improved, but not now for demo.
        Completable.complete()
                .subscribeOn(Schedulers.io())
                .doFinally(() -> {
                    // TODO: SAVE DATA, IGNORES RESULT
                });
    }

    @NonNull
    @CheckResult
    public Single<List<Feed>> getPublicFeed(@NonNull String lang) {
        // TODO: Check for formatted language
        mLang = Objects.requireNonNull(lang, "Language cannot be null").trim();
        // Create request object
        return RepositoryManager.getInstance()
                // Execute repository
                .execute(this)
                // Receive data in main thread
                .observeOn(AndroidSchedulers.mainThread());
    }
}
