/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 16-Feb-2019.
 */

package com.venomvendor.sdk.core.model.gallery;

import com.google.gson.annotations.SerializedName;
import com.venomvendor.sdk.core.model.Status;

import java.util.List;

import androidx.annotation.NonNull;

public class PublicFeed extends Status {

    @SerializedName("link")
    private String link;

    @SerializedName("description")
    private String description;

    @SerializedName("modified")
    private String modified;

    @SerializedName("generator")
    private String generator;

    @SerializedName("title")
    private String title;

    @SerializedName("items")
    private List<Feed> feeds;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(List<Feed> feeds) {
        this.feeds = feeds;
    }

    @NonNull
    @Override
    public String toString() {
        return "PublicFeed{" +
                "link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", title='" + title + '\'' +
                "} " + super.toString();
    }
}
