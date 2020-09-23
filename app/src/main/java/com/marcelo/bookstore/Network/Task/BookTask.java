package com.marcelo.bookstore.Network.Task;

import com.google.gson.JsonObject;
import com.marcelo.bookstore.Network.NetworkUtils;
import com.marcelo.bookstore.Utils.Callback;
import com.marcelo.bookstore.Utils.Utils;

import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Response;

public class BookTask {

    public static void getBooks(final Callback<JsonObject> callback, Map<String, String> parameters){
        NetworkUtils.getBookApiInstance().getVolumes(parameters)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<JsonObject>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<JsonObject> jsonObjectResponse) {
                        if(jsonObjectResponse.code() == Utils.HTTP_SUCCESSFUL)
                            callback.returnResult(jsonObjectResponse.body());
                        else
                            callback.returnError(jsonObjectResponse.message());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.returnError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void getSpecificBook(final Callback<JsonObject> callback, String bookID){
        NetworkUtils.getBookApiInstance().getVolume(bookID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Response<JsonObject>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Response<JsonObject> jsonObjectResponse) {
                        if(jsonObjectResponse.code() == Utils.HTTP_SUCCESSFUL)
                            callback.returnResult(jsonObjectResponse.body());
                        else
                            callback.returnError(jsonObjectResponse.message());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callback.returnError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
