package com.jameslawler.library;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class OpenGraphParser {
    public static List<OpenGraph> Parse(String html) {
        List<OpenGraph> openGraphs = new ArrayList<>();

        Document document = Jsoup.parse(html);
        Elements elements = document.select("meta[property^=og:");

        for (Element element: elements) {
            String property = element.attributes().get("property");
            String content = element.attributes().get("content");

            OpenGraph openGraph = new OpenGraph(property, content);

            openGraphs.add(openGraph);
        }

        return openGraphs;
    }

    public static Observable<OpenGraph> ParseObservable(final String html) {
        return Observable.defer(() -> {
            try {
                return Observable.from(Parse(html));
            } catch (Exception exception) {
                return Observable.error(exception);
            }
        });
    }
}
