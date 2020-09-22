package com.marcelo.bookstore.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.marcelo.bookstore.Adapter.BookListAdapter;
import com.marcelo.bookstore.R;
import com.marcelo.bookstore.Utils.EndlessRecyclerViewScrollListener;
import com.marcelo.bookstore.ViewModel.BookListViewModel;
import com.marcelo.bookstore.ViewModel.SplashScreenViewModel;

public class BookListActivity extends AppCompatActivity {

    private BookListViewModel bookListViewModel;

    private RecyclerView booksListRecyclerView;
    private BookListAdapter bookListAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private GridLayoutManager bookListLayoutManager;
    private int scrollPosition;
    // to user as refresh on top & progress on the bottom, needs to be redesigned for 2 columns
    private ConstraintLayout llLoading;
    private TextView tvReleaseToUpdate;
    private boolean toUpdate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        init();
    }

    private void init(){
        bookListViewModel = new ViewModelProvider(this).get(BookListViewModel.class);
        bookListViewModel.init();

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
                //put loading
                //loadingLayout.setVisibility(View.VISIBLE);
                booksListRecyclerView.setClickable(false);
            }

            @Override
            public void onFinishLoading() {
                //loadingLayout.setVisibility(View.GONE);
                //fleetRecyclerView.setClickable(true);
            }

            @Override
            public void onNoMoreItems() {
                // show view "no more items"
                //fleetRecyclerViewAdapter.removeLoading();
            }

            @Override
            public void saveScrollPosition(RecyclerView view, int dx, int dy) {
                scrollPosition += dy;
            }
        };

        booksListRecyclerView.addOnScrollListener(scrollListener);
   //     bookListAdapter.setOnItemClickListener(position -> {openVehicleWithID(position); });

        booksListRecyclerView.setScrollY(scrollPosition);

    }
}