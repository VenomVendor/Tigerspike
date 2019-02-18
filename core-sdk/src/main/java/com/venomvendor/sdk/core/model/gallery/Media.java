/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 16-Feb-2019.
 */

package com.venomvendor.sdk.core.model.gallery;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class Media {

    @SerializedName("m")
    private String medium;

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    @NonNull
    @Override
    public String toString() {
        return "Media{" +
                "m = '" + medium + '\'' +
                "}";
    }
}
