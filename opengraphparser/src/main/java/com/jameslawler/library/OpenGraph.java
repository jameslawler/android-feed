package com.jameslawler.library;

/**
 * Created by james on 06/11/2015.
 */
public class OpenGraph {
    private String property;
    private String content;

    public OpenGraph(String property, String content) {
        this.property = property;
        this.content = content;
    }

    public String getProperty() {
        return property;
    }

    public String getContent() {
        return content;
    }
}
