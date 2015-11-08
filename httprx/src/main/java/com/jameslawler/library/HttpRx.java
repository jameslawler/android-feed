package com.jameslawler.library;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import rx.Observable;

public class HttpRx {

    public static String Get(String url) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                    .url(url)
                    .build();

        Response response = okHttpClient.newCall(request).execute();

        return response.body().string();
    }

    public static Observable<String> GetObservable(final String url) {
        return Observable.defer(() -> {
            try {
                return Observable.just(Get(url));
            } catch (Exception exception) {
                return Observable.error(exception);
            }
        });
    }
}
