package com.marcelo.bookstore.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.marcelo.bookstore.Model.Book;
import com.marcelo.bookstore.Network.JSONFilesManager;
import com.marcelo.bookstore.Network.Task.BookTask;
import com.marcelo.bookstore.Utils.Callback;

import org.jdeferred2.DoneCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BooksRepository {

    final MutableLiveData<ArrayList<Book>> books = new MutableLiveData<>();
    final MutableLiveData<ArrayList<Book>> favorites = new MutableLiveData<>(); //maybe switch to save id, & search for the books next
    final MutableLiveData<Book> book = new MutableLiveData<>();

    public void getBooksFromAPI(int start){
        Map<String, String> parameters =  new HashMap<>();
        parameters.put("maxResults", "20");
        parameters.put("startIndex", ""+start);
        parameters.put("q","ios");

        BookTask.getBooks(new Callback<JsonObject>() {
            @Override
            public void returnResult(JsonObject jsonObject) {
                JSONFilesManager.getBooksFromJSON(jsonObject).done((DoneCallback<ArrayList<Book>>) result ->{
                    books.postValue(result);

                    //add glide to get images here?

                }); //TODO implement fail to not brick the app if reading json fails
            }

            @Override
            public void returnError(String message) {

            }
        }, parameters);
    }

    public LiveData<ArrayList<Book>> getBooks() {
        return books;
    }

  /*  public void getSpecificBookFromAPI(String bookID){
        Map<String, String> parameters =  new HashMap<>();
        parameters.put("maxResults", "20");


        BookTask.getBooks(new Callback<JsonObject>() {
            @Override
            public void returnResult(JsonObject jsonObject) {
                JSONFilesManager.getBooksFromJSON(jsonObject).done((DoneCallback<ArrayList<Book>>) result ->{
                    books.postValue(result);

                    //add glide to get images here?

                }); //TODO implement fail to not brick the app if reading json fails
            }

            @Override
            public void returnError(String message) {

            }
        }, parameters);
    }

    public LiveData<Book> getSingleBook() {
        return book;
    }
*/
}
