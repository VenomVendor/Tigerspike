/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 17-Feb-2019.
 */

package com.venomvendor.sdk.core.repository;

import com.venomvendor.sdk.core.model.Status;

import io.reactivex.functions.Function;

/**
 * Validate response, check if the data received is successful in business level.
 *
 * @param <T> Type of data
 */
@SuppressWarnings("unchecked")
public class ResponseValidator<T> implements Function<Status, T> {

    @Override
    public T apply(Status status) throws Exception {
        if (status.isSuccess()) {
            return (T) status;
        }
        // TODO Create Custom Exception
        throw new Exception(status.toString());
    }
}
