/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 16-Feb-2019.
 */

package com.venomvendor.sdk.core.model.gallery;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import androidx.annotation.NonNull;

public class Feed implements Parcelable {

    public static final Creator<Feed> CREATOR = new Creator<Feed>() {
        @Override
        public Feed createFromParcel(Parcel source) {
            return new Feed(source);
        }

        @Override
        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };
    @SerializedName("author")
    private String author;
    @SerializedName("link")
    private String link;
    @SerializedName("description")
    private String description;
    @SerializedName("media")
    private Media media;
    @SerializedName("published")
    private Date published;
    @SerializedName("title")
    private String title;
    @SerializedName("author_id")
    private String authorId;
    @SerializedName("date_taken")
    private String dateTaken;
    @SerializedName("tags")
    private String tags;

    public Feed() {
    }

    protected Feed(Parcel in) {
        author = in.readString();
        link = in.readString();
        description = in.readString();
        media = in.readParcelable(Media.class.getClassLoader());
        long tmpPublished = in.readLong();
        published = tmpPublished == -1 ? null : new Date(tmpPublished);
        title = in.readString();
        authorId = in.readString();
        dateTaken = in.readString();
        tags = in.readString();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

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

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @NonNull
    @Override
    public String toString() {
        return "Feed{" +
                "author = '" + author + '\'' +
                ",link = '" + link + '\'' +
                ",description = '" + description + '\'' +
                ",media = '" + media + '\'' +
                ",published = '" + published + '\'' +
                ",title = '" + title + '\'' +
                ",author_id = '" + authorId + '\'' +
                ",date_taken = '" + dateTaken + '\'' +
                ",tags = '" + tags + '\'' +
                "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(author);
        dest.writeString(link);
        dest.writeString(description);
        dest.writeParcelable(media, flags);
        dest.writeLong(published != null ? published.getTime() : -1);
        dest.writeString(title);
        dest.writeString(authorId);
        dest.writeString(dateTaken);
        dest.writeString(tags);
    }
}
