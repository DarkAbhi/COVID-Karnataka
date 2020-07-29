package com.darkabhi.covidproject;

public class NewsModel {
    String mTitle;
    String mContent;
    String mImageUrl;
    String mNewsUrl;
    String mSourceName;

    public NewsModel(String mTitle, String mContent, String mImageUrl, String mNewsUrl, String mSourceName) {
        this.mContent = mContent;
        this.mImageUrl = mImageUrl;
        this.mNewsUrl = mNewsUrl;
        this.mTitle = mTitle;
        this.mSourceName = mSourceName;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getContent() {
        return mContent;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getNewsUrl() {
        return mNewsUrl;
    }

    public String getSourceName() {
        return mSourceName;
    }
}

