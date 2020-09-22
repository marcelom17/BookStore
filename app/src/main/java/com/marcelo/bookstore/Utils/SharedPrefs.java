package com.marcelo.bookstore.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPrefs {

    /*  VARIABLES  */
    private static SharedPreferences sharedPreferences;
    private static Gson gson = new Gson();

    /*  KEYS  */
    public static final String SHARED_PREFERENCES_KEY = "STRATIO_PREFERENCES";
    public static final String FAV_BOOKS_KEY = "FAV_BOOKS";

    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE);
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }

    /* FAVORITE BOOKS */
    public static ArrayList<String> getFavoriteBooks() {
        String json = sharedPreferences.getString(FAV_BOOKS_KEY, null);

        if (json == null) {
            return new ArrayList<>();
        } else {
            Type type = new TypeToken<List<String>>(){}.getType();
            return gson.fromJson(json, type);
        }
    }
    public static void setFavoriteBooks(ArrayList<String> searches) {
        sharedPreferences.edit().putString(FAV_BOOKS_KEY, gson.toJson(searches)).apply();
    }
    public static void removeFavoriteBooks(){
        sharedPreferences.edit().remove(FAV_BOOKS_KEY).apply();
    }

}
