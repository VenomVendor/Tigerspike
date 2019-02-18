/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 17-Feb-2019.
 */

package com.venomvendor.sdk.core.data.factory;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import io.reactivex.Single;

/**
 * Repository to be handled by {@link com.venomvendor.sdk.core.data.RepositoryManager}
 *
 * @param <T> Data type received from server
 */
public interface Repository<T> {

    /**
     * Get Cached data from storage
     *
     * @return Cached data or {@code null}
     */
    @CheckResult
    @Nullable
    T getCachedData();

    /**
     * Request to be executed if data is not available.
     *
     * @return Request or {@code null} if no request to be made to server.
     */
    @CheckResult
    @NonNull
    Single<T> getRequest();

    /**
     * Save a copy of data to cache
     *
     * @param data data to be saved.
     */
    @WorkerThread
    void saveData(@NonNull T data) throws Exception;
}
