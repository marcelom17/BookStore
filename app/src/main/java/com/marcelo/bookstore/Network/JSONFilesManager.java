package com.marcelo.bookstore.Network;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import com.marcelo.bookstore.Model.Book;
import com.marcelo.bookstore.Model.ImageLinks;
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
        } catch (Exception e) {
            Log.i("getTotalBooksFromJSON", "Exception: " + e.getMessage());
        }

        return dObj.reject(null);
    }

    public static Promise<ArrayList<Book>, Throwable, ?> getBooksFromJSON(JsonObject json) {
        /* Promise declaration */
        DeferredObject dObj = new DeferredObject();

        /* Variables */
        ArrayList<Book> books = new ArrayList<>();

        /* Read books from JSON */
        try {
            /* Read Array of Books  */
            JsonArray itemsObject = json.getAsJsonObject().getAsJsonArray("items");

            /* Fields to Read */
            String id = "--";
            String title = "--";
            ArrayList<String> authors = new ArrayList<>();
            String description = "--";
            String buylink = "--";
            String thumbnail = "--";
            String smallThumbnail = "--";

            for (JsonElement jsonElement : itemsObject) {
                try {
                    JsonObject temp = jsonElement.getAsJsonObject();
                    authors.clear();
                    /* Read items sub fields */
                    if (temp.has("id"))
                        id = temp.get("id").getAsString();

                    if (temp.has("volumeInfo")) {
                        JsonObject volumeInfoObject = temp.get("volumeInfo").getAsJsonObject();

                        /* Read volumeInfo subfields*/
                        if (volumeInfoObject.has("title"))
                            title = volumeInfoObject.get("title").getAsString();
                        if (volumeInfoObject.has("authors")) {
                            JsonArray authorsJsonArray = volumeInfoObject.get("authors").getAsJsonArray();
                            for (JsonElement author : authorsJsonArray) {
                                authors.add(author.getAsString());
                            }
                        }
                        if (volumeInfoObject.has("description"))
                            description = volumeInfoObject.get("description").getAsString();

                        if (volumeInfoObject.has("imageLinks")) {
                            JsonObject linksObj = volumeInfoObject.get("imageLinks").getAsJsonObject();
                            if (linksObj.has("thumbnail"))
                                thumbnail = linksObj.get("thumbnail").getAsString();
                            if (linksObj.has("smallThumbnail"))
                                smallThumbnail = linksObj.get("smallThumbnail").getAsString();
                        }
                    }
                    if (temp.has("saleInfo")) {
                        JsonObject saleInfoObject = temp.get("saleInfo").getAsJsonObject();
                        if (saleInfoObject.has("buyLink"))
                            buylink = saleInfoObject.get("buyLink").getAsString();
                    }
                } catch (JsonParseException j) {
                    continue;
                } catch (NumberFormatException nfe) {
                    nfe.fillInStackTrace();
                    continue;
                }

                ArrayList<String> temp = new ArrayList<>();
                for (String author : authors)
                    temp.add(author);

                ImageLinks imageLinks = new ImageLinks();
                imageLinks.setThumbnail(thumbnail);
                imageLinks.setSmallThumbnail(smallThumbnail);

                VolumeInfo volumeInfo = new VolumeInfo();
                volumeInfo.setTitle(title);
                volumeInfo.setAuthors(temp);
                volumeInfo.setDescription(description);
                volumeInfo.setImageLinks(imageLinks);

                SaleInfo saleInfo = new SaleInfo();
                saleInfo.setBuyLink(buylink);

                Book newBook = new Book();
                newBook.setId(id);
                newBook.setVolumeInfo(volumeInfo);
                newBook.setSaleInfo(saleInfo);
                books.add(newBook);
            }

            return dObj.resolve(books);
        } catch (Exception e) {
            Log.i("getBooksFromJSON", "Exception: " + e.getMessage());
        }

        return dObj.reject(null);

    }

    public static Promise<Book, Throwable, ?> getSpecificBookFromJSON(JsonObject json) {
        /* Promise declaration */
        DeferredObject dObj = new DeferredObject();

        /* Variables */
        Book book = new Book();

        /* Read books from JSON */
        try {
            /* Fields to Read */
            String id = "--";
            String title = "--";
            ArrayList<String> authors = new ArrayList<>();
            String description = "--";
            String buylink = "--";
            String thumbnail = "--";
            String smallThumbnail = "--";


            try {
                JsonObject temp = json;
                authors.clear();
                /* Read items sub fields */
                if (temp.has("id"))
                    id = temp.get("id").getAsString();

                if (temp.has("volumeInfo")) {
                    JsonObject volumeInfoObject = temp.get("volumeInfo").getAsJsonObject();

                    /* Read volumeInfo subfields*/
                    if (volumeInfoObject.has("title"))
                        title = volumeInfoObject.get("title").getAsString();
                    if (volumeInfoObject.has("authors")) {
                        JsonArray authorsJsonArray = volumeInfoObject.get("authors").getAsJsonArray();
                        for (JsonElement author : authorsJsonArray) {
                            authors.add(author.getAsString());
                        }
                    }
                    if (volumeInfoObject.has("description"))
                        description = volumeInfoObject.get("description").getAsString();

                    if (volumeInfoObject.has("imageLinks")) {
                        JsonObject linksObj = volumeInfoObject.get("imageLinks").getAsJsonObject();
                        if (linksObj.has("thumbnail"))
                            thumbnail = linksObj.get("thumbnail").getAsString();
                        if (linksObj.has("smallThumbnail"))
                            smallThumbnail = linksObj.get("smallThumbnail").getAsString();
                    }
                }
                if (temp.has("saleInfo")) {
                    JsonObject saleInfoObject = temp.get("saleInfo").getAsJsonObject();
                    if (saleInfoObject.has("buyLink"))
                        buylink = saleInfoObject.get("buyLink").getAsString();
                }
            } catch (JsonParseException j) {

            } catch (NumberFormatException nfe) {
                nfe.fillInStackTrace();
            }

            ArrayList<String> temp = new ArrayList<>();
            for (String author : authors)
                temp.add(author);

            ImageLinks imageLinks = new ImageLinks();
            imageLinks.setThumbnail(thumbnail);
            imageLinks.setSmallThumbnail(smallThumbnail);

            VolumeInfo volumeInfo = new VolumeInfo();
            volumeInfo.setTitle(title);
            volumeInfo.setAuthors(temp);
            volumeInfo.setDescription(description);
            volumeInfo.setImageLinks(imageLinks);

            SaleInfo saleInfo = new SaleInfo();
            saleInfo.setBuyLink(buylink);


            book.setId(id);
            book.setVolumeInfo(volumeInfo);
            book.setSaleInfo(saleInfo);


            return dObj.resolve(book);
        } catch (Exception e) {
            Log.i("getBooksFromJSON", "Exception: " + e.getMessage());
        }

        return dObj.reject(null);


    }

}
