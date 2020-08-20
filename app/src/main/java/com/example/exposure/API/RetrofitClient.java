package com.example.exposure.API;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Connect to NodeJS server
 */
public class RetrofitClient {
    private static Retrofit instance;
    
    public static Retrofit getInstance(){
        // The Url is a string literal and not a string resource
        // because this is a non-activity class, to treat it as
        // an activity class will make it subject to memory leaks
        // This is a settings file after all
        String nodeJsUrl = "http://54.80.123.56:3000";
        if (instance == null)
            instance = new Retrofit.Builder().baseUrl(nodeJsUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        return instance;
    }
}
