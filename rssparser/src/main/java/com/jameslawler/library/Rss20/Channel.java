package com.jameslawler.library.Rss20;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class Channel {
    @ElementList(name = "item", required = true, inline = true)
    public List<Item> itemList;

    @Element
    public String title;

    @Element
    public String language;

    @Element(name = "ttl", required = false)
    public int ttl;

    @Element(name = "pubDate", required = false)
    public String pubDate;

    @Override
    public String toString() {
        return "Channel{" +
                ", itemList=" + itemList +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", ttl=" + ttl +
                ", pubDate='" + pubDate + '\'' +
                '}';
    }
}