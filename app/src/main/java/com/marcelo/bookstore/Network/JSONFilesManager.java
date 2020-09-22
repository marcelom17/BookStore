package com.marcelo.bookstore.Network;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import com.marcelo.bookstore.Model.Book;
import com.marcelo.bookstore.Model.SaleInfo;
import com.marcelo.bookstore.Model.VolumeInfo;

import org.jdeferred2.Promise;
import org.jdeferred2.impl.DeferredObject;

import java.util.ArrayList;

public final class JSONFilesManager {

    public static Promise<Integer, Throwable, ?> getTotalBooksFromJSON(JsonObject json) {
        /* Promise declaration */
        DeferredObject dObj = new DeferredObject();

        /* Variables declaration */
        int total = 0;

        /* Read total number of books from JSON */
        try {
            if (json.has("totalItems"))
                total = json.get("totalItems").getAsInt();

            return dObj.resolve(total);
        } catch (Exception e){
            Log.i("getTotalBooksFromJSON","Exception: "+e.getMessage());
        }

        return dObj.reject(null);
    }
        public static Promise<ArrayList<Book>, Throwable, ?> getBooksFromJSON(JsonObject json){
        /* Promise declaration */
        DeferredObject dObj = new DeferredObject();

        /* Variables */
        ArrayList<Book> books = new ArrayList<>();

        /* Read books from JSON */
        try{
            /* Read Array of Books  */
            JsonArray itemsObject = json.getAsJsonObject().getAsJsonArray("items");

            /* Fields to Read */
            String id = "--";
            String title = "--";
            ArrayList<String> authors = new ArrayList<>();
            String description = "--";
            String buylink = "--";

            for(JsonElement jsonElement : itemsObject){
                try{
                    JsonObject temp = jsonElement.getAsJsonObject();

                    /* Read items sub fields */
                    if(temp.has("id"))
                        id = temp.get("id").getAsString();

                    if(temp.has("volumeInfo")){
                        JsonObject volumeInfoObject = temp.get("volumeInfo").getAsJsonObject();

                        /* Read volumeInfo subfields*/
                        if(volumeInfoObject.has("title"))
                            title = volumeInfoObject.get("title").getAsString();
                        if(volumeInfoObject.has("authors")) {
                            JsonArray authorsJsonArray = volumeInfoObject.get("authors").getAsJsonArray();
                            for(JsonElement author : authorsJsonArray){
                                authors.add(author.getAsString());
                            }
                           /* for(int i=0; i< authorsJsonArray.size();i++){
                                authors.add(authorsJsonArray.get(i).getAsString());
                            }*/
                        }
                        if(volumeInfoObject.has("description"))
                            description = volumeInfoObject.get("description").getAsString();
                        if(volumeInfoObject.has("buylink"))
                            buylink = volumeInfoObject.get("buyLink").getAsString();

                    }
                } catch (JsonParseException j){
                    continue;
                } catch (NumberFormatException nfe){
                    nfe.fillInStackTrace();
                    continue;
                }

                VolumeInfo volumeInfo = new VolumeInfo();
                volumeInfo.setTitle(title);
                volumeInfo.setAuthors(authors);
                volumeInfo.setDescription(description);

                SaleInfo saleInfo = new SaleInfo();
                saleInfo.setBuyLink(buylink);

                Book newBook = new Book();
                newBook.setId(id);
                newBook.setVolumeInfo(volumeInfo);
                newBook.setSaleInfo(saleInfo);
                books.add(newBook);
            }

            return dObj.resolve(books);
        } catch(Exception e){
            Log.i("getBooksFromJSON","Exception: "+e.getMessage());
        }

        return dObj.reject(null);


    }

}
