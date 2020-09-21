package com.marcelo.bookstore.Repository;

import androidx.lifecycle.MutableLiveData;

import com.marcelo.bookstore.Model.Book;

import java.util.ArrayList;

public class BooksRepository {

    final MutableLiveData<ArrayList<Book>> books = new MutableLiveData<>();
    final MutableLiveData<ArrayList<Book>> favorites = new MutableLiveData<>();



}
