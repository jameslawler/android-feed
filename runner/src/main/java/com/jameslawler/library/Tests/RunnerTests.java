package com.jameslawler.library.Tests;

import com.jameslawler.library.HttpRx;
import com.jameslawler.library.OpenGraphParser;
import com.jameslawler.library.RssParser;

import org.junit.Assert;
import org.junit.Test;

import rx.Observable;

/**
 * Created by james on 06/11/2015.
 */
public class RunnerTests {
    @Test
    public void When() {
        Observable
                .just("http://rss.dw.com/xml/DKpodcast_topthemamitvokabeln_de")
                .flatMap(x -> HttpRx.GetObservable(x))
                .flatMap(x -> RssParser.ParseObservable(x))
                .flatMapIterable(x -> x.channel.itemList)
                .flatMap(x -> HttpRx.GetObservable(x.link))
                .flatMap(x -> OpenGraphParser.ParseObservable(x))
                .filter(x -> x.getProperty().equalsIgnoreCase("og:image"))
                .subscribe(
                        x -> System.out.println(x.getContent()));
    }
}
