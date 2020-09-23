package com.marcelo.bookstore.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.marcelo.bookstore.Model.Book;
import com.marcelo.bookstore.Repository.BooksRepository;

public class BookDetailsViewModel extends ViewModel {

    private BooksRepository booksRepository;
    private LiveData<Book> book;

    public void init(Context mContext) {
        booksRepository = new BooksRepository(mContext);

        book = booksRepository.getBook();

    }

    public LiveData<Book> getBook(){
        return book;
    }

    public void addBookToFavorite(String bookID){
        booksRepository.addBookToFavorites(bookID);
    }

    public void removeBookFromFavorite(String id) {
        booksRepository.removeBookFromFavorites(id);
    }

    public void getUpdatedBook(String id){
        booksRepository.fetchBook(id);
    }
}
