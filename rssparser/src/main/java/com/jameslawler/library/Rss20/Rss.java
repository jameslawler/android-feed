package com.jameslawler.library.Rss20;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Rss {

    @Attribute
    public String version;

    @Element
    public Channel channel;

    @Override
    public String toString() {
        return "RSS{" +
                "version='" + version + '\'' +
                ", channel=" + channel +
                '}';
    }
}