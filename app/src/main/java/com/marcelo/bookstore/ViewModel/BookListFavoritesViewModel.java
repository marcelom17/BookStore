package com.marcelo.bookstore.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.marcelo.bookstore.Model.Book;
import com.marcelo.bookstore.Repository.BooksRepository;

import java.util.ArrayList;

public class BookListFavoritesViewModel extends ViewModel {

    private BooksRepository booksRepository;
    private LiveData<ArrayList<Book>> favorites;

    public void init(Context mContext){
        booksRepository = new BooksRepository(mContext);
        favorites = booksRepository.getFavorites();

        booksRepository.fetchFavorites();
    }

    public LiveData<ArrayList<Book>> getFavorites(){
        return favorites;
    }

}
