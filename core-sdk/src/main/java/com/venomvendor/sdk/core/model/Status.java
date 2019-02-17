/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 16-Feb-2019.
 */

package com.venomvendor.sdk.core.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class Status {

    private static final String STAT_OK = "ok";

    @SerializedName("stat")
    private String stat;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public boolean isSuccess() {
        return STAT_OK.equals(stat);
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @NonNull
    @Override
    public String toString() {
        return "Status{" +
                "stat = '" + stat + '\'' +
                ",code = '" + code + '\'' +
                ",message = '" + message + '\'' +
                "}";
    }
}
