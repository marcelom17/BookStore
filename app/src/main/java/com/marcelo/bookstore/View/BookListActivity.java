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

import com.marcelo.bookstore.Adapter.BookListAdapter;
import com.marcelo.bookstore.Model.Book;
import com.marcelo.bookstore.R;
import com.marcelo.bookstore.Utils.EndlessRecyclerViewScrollListener;
import com.marcelo.bookstore.Utils.Utils;
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
    private ImageView favBtn;
    boolean isFavoriteOn = false;
    private Observer favsObserver;
    private Observer bookObserver;

    public BookListActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        init();
    }

    private void init(){
        bookObserver = o -> {};
        favsObserver = o -> {};

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
        if(!isFavoriteOn) {
           // bookListViewModel.saveCurrentArray(bookListAdapter.getCurrentArray());
            bookListViewModel.getBooks().removeObservers(this);
            bookListViewModel.clearFavorites();
            bookListAdapter.clearBooks();
            bookListAdapter.notifyDataSetChanged();
            bookListViewModel.getFavorites().observe(this, books -> {
                if (books != null) {
                    bookListAdapter.addBooks(books);
                    bookListAdapter.notifyDataSetChanged();
                }
            });
            favBtn.setImageResource(R.drawable.ic_baseline_favorite_24);
            isFavoriteOn = true;
        } else{
            bookListViewModel.getFavorites().removeObservers(this);
            bookListViewModel.getMoreBooks(0);
            bookListViewModel.clearBooks();
            bookListAdapter.clearBooks();
            bookListAdapter.notifyDataSetChanged();
            bookListViewModel.getBooks().observe(this, books -> {
                if (books != null) {
                    bookListAdapter.addBooks(books);
                    bookListAdapter.notifyDataSetChanged();
                }
            });
            favBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24);
            isFavoriteOn = false;
        }
    }
}