package com.marcelo.bookstore.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.marcelo.bookstore.Model.Book;
import com.marcelo.bookstore.Repository.BooksRepository;

public class BookDetailsViewModel extends ViewModel {

    private BooksRepository booksRepository;
    private MutableLiveData<Book> book;

    public void init() {
        booksRepository = new BooksRepository();
    }
/*
    public void getSpecificBookDetails(String bookID){
        booksRepository.getSpecificBookFromAPI(bookID);
    }
*/
    public LiveData<Book> getBook(){
        return book;
    }

}
