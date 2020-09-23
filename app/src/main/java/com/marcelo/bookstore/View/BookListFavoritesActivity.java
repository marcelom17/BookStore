package com.marcelo.bookstore.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.marcelo.bookstore.Adapter.BookListAdapter;
import com.marcelo.bookstore.Model.Book;
import com.marcelo.bookstore.R;
import com.marcelo.bookstore.Utils.EndlessRecyclerViewScrollListener;
import com.marcelo.bookstore.Utils.Utils;
import com.marcelo.bookstore.ViewModel.BookListFavoritesViewModel;

public class BookListFavoritesActivity extends AppCompatActivity {

    private BookListFavoritesViewModel bookListFavoritesViewModel;

    private RecyclerView booksListRecyclerView;
    private BookListAdapter bookListAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private GridLayoutManager bookListLayoutManager;
    private int scrollPosition;

    public BookListFavoritesActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list_favorites);

        init();
    }

    private void init() {
        bookListFavoritesViewModel = new ViewModelProvider(this).get(BookListFavoritesViewModel.class);
        bookListFavoritesViewModel.init(getApplicationContext());

        bookListLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        booksListRecyclerView = findViewById(R.id.bookListFavoritesRecyclerView);
        bookListAdapter = new BookListAdapter(getApplicationContext());
        booksListRecyclerView.setLayoutManager(bookListLayoutManager);
        booksListRecyclerView.setHasFixedSize(true);
        booksListRecyclerView.setAdapter(bookListAdapter);


        bookListFavoritesViewModel.getFavorites().observe(this, books -> {
            if (books != null) {
                Log.i("BookList Observer", "Arraylist Size: " + books.size());
                bookListAdapter.addBooks(books);
                bookListAdapter.notifyDataSetChanged();
            }
        });

        scrollListener = new EndlessRecyclerViewScrollListener(bookListLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                //no need to use, repository already got all fav books
            }

            @Override
            public void onLoading() {
                booksListRecyclerView.setClickable(false);
            }

            @Override
            public void onFinishLoading() {
            }

            @Override
            public void onNoMoreItems() {
            }

            @Override
            public void saveScrollPosition(RecyclerView view, int dx, int dy) {
                scrollPosition += dy;
            }
        };

        booksListRecyclerView.addOnScrollListener(scrollListener);
        bookListAdapter.setOnItemClickListener(position -> {
            Book book = bookListAdapter.getBook(position);
            Intent intent = new Intent(this, BookDetailsActivity.class);
            intent.putExtra(Utils.BOOK_BUNDLE_KEY, book);
            startActivity(intent);
        });

        booksListRecyclerView.setScrollY(scrollPosition);

    }

    public void showAllBooks(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}