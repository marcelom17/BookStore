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
    final MutableLiveData<ArrayList<Book>> favorites = new MutableLiveData<>();
    final MutableLiveData<Book> book = new MutableLiveData<>();

    ArrayList<Book> tempBooks = new ArrayList<>();

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
                  //  books.postValue(result);

                    //Add favorite to books
                    ArrayList<Book> updated = result;
                    ArrayList<String> favs = SharedPrefs.getFavoriteBooks();
                    int i=0;
                    for(Book b : result){
                        for(String id : favs) {
                            if (b.getId().equalsIgnoreCase(id)) {
                                updated.get(i).setFavorite(true);
                                break;
                            }
                        }
                        i++;
                    }
                    books.postValue(updated);


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

    public void fetchFavorites(){
        ArrayList<String> temp =  SharedPrefs.getFavoriteBooks();

        for(String id : temp){
            getSpecificBookFromAPI(id, true);
        }

    }

    public void fetchBook(String bookID){
        boolean isFavorite = false;
        ArrayList<String> favs = SharedPrefs.getFavoriteBooks();
        int i=0;
        for(String id : favs){
            if (id.equalsIgnoreCase(bookID)) {
                isFavorite = true;
                break;
            }
        }
        getSpecificBookFromAPI(bookID, isFavorite);
    }

    public void getSpecificBookFromAPI(String bookID, boolean isFavorite){

        BookTask.getSpecificBook(new Callback<JsonObject>() {
            @Override
            public void returnResult(JsonObject jsonObject) {
                JSONFilesManager.getSpecificBookFromJSON(jsonObject).done((DoneCallback<Book>) result ->{
                    if(isFavorite)
                        result.setFavorite(true);

                    boolean exists = false;
                    for(int i=0;i<tempBooks.size();i++){
                        if(result.getId().equalsIgnoreCase(tempBooks.get(i).getId())){
                            exists = true;
                            break;
                        }
                    }
                    if(!exists){
                        tempBooks.add(result);
                        favorites.postValue(tempBooks);
                    }
                    book.postValue(result);
                });

                //TODO implement fail to not brick the app if reading json fails
            }

            @Override
            public void returnError(String message) {

            }
        }, bookID);

    }

    public LiveData<ArrayList<Book>> getFavorites(){
        return favorites;
    }

    public LiveData<Book> getBook(){
        return book;
    }

}
