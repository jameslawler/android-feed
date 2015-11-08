package com.jameslawler.library.Rss20;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class Item {
    @Element(name = "title", required = false)
    public String title;

    @Element(name = "link", required = false)
    public String link;

    @Element(name = "description", required = true)
    public String description;

    @Element(name = "author", required = false)
    public String author;

    @Element(name = "category", required = false)
    public String category;

    @Element(name = "comments", required = false)
    public String comments;

    @Element(name = "enclosure", required = false)
    public String enclosure;

    @Element(name = "guid", required = false)
    public String guid;

    @Element(name = "pubDate", required = false)
    public String pubDate;

    @Element(name = "source", required = false)
    public String source;

    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", comments='" + comments + '\'' +
                ", enclosure='" + enclosure + '\'' +
                ", guid='" + guid + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
