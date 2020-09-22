package com.marcelo.bookstore.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.marcelo.bookstore.Model.Book;
import com.marcelo.bookstore.Repository.BooksRepository;

import java.util.ArrayList;

public class SplashScreenViewModel extends ViewModel {

    private BooksRepository booksRepository;
    private LiveData<ArrayList<Book>> mBooks;

    public void init(Context mContext){
        booksRepository = new BooksRepository(mContext);
        mBooks = booksRepository.getBooks();
        //get the first 20 books for when app displays the main list, is already populated, no need to wait
        booksRepository.getBooksFromAPI(0);
    }

    public LiveData<ArrayList<Book>> getBooks(){
        return  mBooks;
    }

}
