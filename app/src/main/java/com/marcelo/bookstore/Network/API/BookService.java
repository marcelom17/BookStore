package com.marcelo.bookstore.Network.API;

import com.google.gson.JsonObject;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface BookService {

    @GET("volumes")
    Observable<Response<JsonObject>> getVolumes(@QueryMap Map<String, String> parameters);

    @GET("volumes/{id}")
    Observable<Response<JsonObject>> getVolume(@Path("id") String id);


}
