package com.marcelo.bookstore.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.marcelo.bookstore.Model.Book;
import com.marcelo.bookstore.Repository.BooksRepository;

import java.util.ArrayList;

public class BookListViewModel extends ViewModel {

    private BooksRepository booksRepository;
    private LiveData<ArrayList<Book>> books;

    public void init(Context mContext){
        booksRepository = new BooksRepository(mContext);
        books = booksRepository.getBooks();
        //get the first 20 books for when app displays the main list, is already populated, no need to wait
        startGettingBooks();
    }

    public void startGettingBooks(){
        booksRepository.getBooksFromAPI(0);
    }

    public void getMoreBooks(int start){
        booksRepository.getBooksFromAPI(start);
    }

    public LiveData<ArrayList<Book>> getBooks(){
        return books;
    }

}
