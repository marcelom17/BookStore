package com.marcelo.bookstore.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.marcelo.bookstore.Adapter.BookListAdapter;
import com.marcelo.bookstore.Model.Book;
import com.marcelo.bookstore.R;
import com.marcelo.bookstore.Utils.EndlessRecyclerViewScrollListener;
import com.marcelo.bookstore.Utils.Utils;
import com.marcelo.bookstore.ViewModel.BookListViewModel;
import com.marcelo.bookstore.ViewModel.SplashScreenViewModel;

public class BookListActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    private BookListViewModel bookListViewModel;

    private RecyclerView booksListRecyclerView;
    private BookListAdapter bookListAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private GridLayoutManager bookListLayoutManager;
    private int scrollPosition;
    // to user as refresh on top & progress on the bottom, needs to be redesigned for 2 columns
    private ImageView favBtn;
    boolean isFavoriteOn = false;

    public BookListActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        init();
    }

    private void init(){
        bookListViewModel = new ViewModelProvider(this).get(BookListViewModel.class);
        bookListViewModel.init(getApplicationContext());

        bookListLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        booksListRecyclerView = findViewById(R.id.bookListRecyclerView);
        bookListAdapter = new BookListAdapter(getApplicationContext());
        booksListRecyclerView.setLayoutManager(bookListLayoutManager);
        booksListRecyclerView.setHasFixedSize(true);
        booksListRecyclerView.setAdapter(bookListAdapter);


        bookListViewModel.getBooks().observe(this, books -> {
            if(books != null) {
                Log.i("BookList Observer", "Arraylist Size: " + books.size());
                bookListAdapter.addBooks(books);
                bookListAdapter.notifyDataSetChanged();
            }
        });

        scrollListener = new EndlessRecyclerViewScrollListener(bookListLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                bookListViewModel.getMoreBooks(bookListAdapter.getItemCount());
            }

            @Override
            public void onLoading() {
                booksListRecyclerView.setClickable(false);
            }

            @Override
            public void onFinishLoading() { }

            @Override
            public void onNoMoreItems() { }

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
        favBtn = findViewById(R.id.bookListShowFavoritesBtn);

    }


    public void showFavorites(View view) {

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(R.id.bookListFavoritesBtn));
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Favorites");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        Log.i("PRESS FAV", "after logEvent");

        Intent intent = new Intent(this, BookListFavoritesActivity.class);
        startActivity(intent);

    }
}