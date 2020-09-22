package com.marcelo.bookstore.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.marcelo.bookstore.Model.Book;
import com.marcelo.bookstore.Network.JSONFilesManager;
import com.marcelo.bookstore.Network.Task.BookTask;
import com.marcelo.bookstore.Utils.Callback;
import com.marcelo.bookstore.Utils.SharedPrefs;

import org.jdeferred2.DoneCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BooksRepository {

    final MutableLiveData<ArrayList<Book>> books = new MutableLiveData<>();
    final MutableLiveData<ArrayList<Book>> favorites = new MutableLiveData<>(); //maybe switch to save id, & search for the books next
    final MutableLiveData<Book> book = new MutableLiveData<>();

    public BooksRepository(Context mContext) {
        SharedPrefs.init(mContext);
    }

    public void getBooksFromAPI(int start){
        Map<String, String> parameters =  new HashMap<>();
        parameters.put("maxResults", "20");
        parameters.put("startIndex", ""+start);
        parameters.put("q","ios");

        BookTask.getBooks(new Callback<JsonObject>() {
            @Override
            public void returnResult(JsonObject jsonObject) {
                JSONFilesManager.getBooksFromJSON(jsonObject).done((DoneCallback<ArrayList<Book>>) result ->{
                 //   books.postValue(result);

                    //Add favorites
                    ArrayList<Book> updated = result;
                    ArrayList<Book> temp = new ArrayList<>();
                    ArrayList<String> favs = SharedPrefs.getFavoriteBooks();
                    int i=0;
                    for(Book b : result){
                        for(String id : favs) {
                            if (b.getId().equalsIgnoreCase(id)) {
                                temp.add(b);
                                updated.get(i).setFavorite(true);
                                break;
                            }
                        }
                        i++;
                    }
                    books.postValue(updated);
                    favorites.postValue(temp);
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

    public LiveData<ArrayList<Book>> getFavorites(){
        return favorites;
    }

    public void addBookToFavorites(String bookID){
        Boolean exists = false;
        ArrayList<String> temp = SharedPrefs.getFavoriteBooks();

        for(String id : temp)
            if(id.equalsIgnoreCase(bookID)){
                exists = true;
                break;
            }
        if(!exists) {
            temp.add(bookID);
            SharedPrefs.setFavoriteBooks(temp);
        }
    }

    public void removeBookFromFavorites(String bookID) {
        ArrayList<String> temp = SharedPrefs.getFavoriteBooks();

        for(String id : temp)
            if(id.equalsIgnoreCase(bookID)){
                temp.remove(id);
                SharedPrefs.setFavoriteBooks(temp);
                break;
            }
    }

    public void clearBooks() {
        books.postValue(new ArrayList<>());
    }

    public void clearFavorites() {
        favorites.postValue(new ArrayList<>());
    }

 /*   public void getFavsFromSharedPrefs(){
        ArrayList<Book> temp = new ArrayList<>();
        ArrayList<String> favs = SharedPrefs.getFavoriteBooks();
        for(Book b : result){
            for(String id : favs) {
                if (b.getId().equalsIgnoreCase(id)) {
                    temp.add(b);
                    break;
                }
            }
        }
        favorites.postValue(temp);
    }
   */

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
