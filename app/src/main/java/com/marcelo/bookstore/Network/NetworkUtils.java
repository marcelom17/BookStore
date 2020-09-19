package com.marcelo.bookstore.Network;

import com.marcelo.bookstore.Network.API.BookService;
import com.marcelo.bookstore.Network.Adapter.RetrofitAdapter;

public class NetworkUtils {

    public static BookService getBookApiInstance(){
        return RetrofitAdapter.getInstance().create(BookService.class);
    }

}
