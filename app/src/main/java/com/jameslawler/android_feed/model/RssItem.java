package com.jameslawler.android_feed.model;

public class RssItem {
    public String name;
    public String description;
    public String publishDate;
    public int image;

    public RssItem(String name, String description, String publishDate, int image) {
        this.name = name;
        this.description = description;
        this.publishDate = publishDate;
        this.image = image;
    }
}
