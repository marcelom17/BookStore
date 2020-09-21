package com.marcelo.bookstore.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.marcelo.bookstore.Model.Book;

import java.util.ArrayList;

public class SplashScreenViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Book>> mBooks;

    public LiveData<ArrayList<Book>> getBooks(){
        return  mBooks;
    }

}
