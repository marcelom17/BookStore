package com.marcelo.bookstore.Model.Singleton;

import android.app.Application;

import com.marcelo.bookstore.Model.Book;

import java.util.ArrayList;

/* Singleton to fetch data to be used around the app*/

public class DataHolder extends Application {

    private ArrayList<Book> books;
    private ArrayList<Book> favorites;

    private static final DataHolder holder = new DataHolder();

    public static DataHolder getInstance(){
        return holder;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Book> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Book> favorites) {
        this.favorites = favorites;
    }
}
