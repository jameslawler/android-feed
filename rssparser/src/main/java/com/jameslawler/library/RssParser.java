package com.jameslawler.library;

import com.jameslawler.library.Rss20.Rss;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import rx.Observable;

public class RssParser {
    public static Rss Parse(String xml) throws Exception {
        Serializer ser = new Persister();
        byte[] data = xml.getBytes("UTF-8");
        InputStream source = new ByteArrayInputStream(data);

        return ser.read(Rss.class, source);
    }

    public static Observable<Rss> ParseObservable(final String xml) {
        return Observable.defer(() -> {
            try {
                return Observable.just(Parse(xml));
            } catch (Exception exception) {
                return Observable.error(exception);
            }
        });
    }
}
