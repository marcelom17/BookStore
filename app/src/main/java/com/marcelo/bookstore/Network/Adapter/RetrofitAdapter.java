package com.marcelo.bookstore.Network.Adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitAdapter {

    private static Retrofit retrofit;
    private static Gson gson;
    public static String DEFAULT_API_PATH = "https://www.googleapis.com/books/v1/";

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(DEFAULT_API_PATH);


    public static synchronized Retrofit getInstance() {

        if (retrofit == null) {
            if (gson == null) {
                gson = new GsonBuilder().setLenient().create();
            }

            retrofit = builder.addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();

        }

        return retrofit;
    }

}
