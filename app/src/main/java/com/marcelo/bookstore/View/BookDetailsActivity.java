package com.marcelo.bookstore.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.marcelo.bookstore.Model.Book;
import com.marcelo.bookstore.R;
import com.marcelo.bookstore.Utils.Utils;
import com.marcelo.bookstore.ViewModel.BookDetailsViewModel;

public class BookDetailsActivity extends AppCompatActivity {

    private BookDetailsViewModel bookDetailsViewModel;
    private Book book = new Book();

    private ImageView thumbnailIV;
    private TextView titleTV;
    private TextView authorTV;
    private TextView descriptionTV;
    private Button  buyBtn;
    private ImageView favBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        //getIntent with ID
        if(getIntent() != null)
            book = getIntent().getParcelableExtra(Utils.BOOK_BUNDLE_KEY);

        init();
    }

    @SuppressLint("ResourceAsColor")
    private void init(){
        bookDetailsViewModel = new ViewModelProvider(this).get(BookDetailsViewModel.class);
        bookDetailsViewModel.init(getApplicationContext());

        thumbnailIV = findViewById(R.id.bookDetailsThumbnailIV);
        titleTV = findViewById(R.id.bookDetailsTitleTV);
        authorTV = findViewById(R.id.bookDetailsAuthorTV);
        descriptionTV = findViewById(R.id.bookDetailsDescriptionTV);
        buyBtn = findViewById(R.id.bookDetailsBuyButton);
        favBtn = findViewById(R.id.bookDetailsFavoriteBtn);

        if(book != null) {
            if (book.getSaleInfo().getBuyLink() == null || book.getSaleInfo().getBuyLink().equals("--")) {
                buyBtn.setClickable(false);
                buyBtn.setAlpha((float) .5);
                buyBtn.setBackgroundColor(R.color.colorGray);
            } else {
                buyBtn.setClickable(true);
                buyBtn.setAlpha(1);
            }
            if(book.isFavorite())
                favBtn.setImageResource(R.drawable.ic_baseline_favorite_24);
            else
                favBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24);

            if (book.getVolumeInfo().getImageLinks() != null) {
                String imageUrl = book.getVolumeInfo().getImageLinks().getThumbnail()
                        .replace("http://", "https://");

                Glide.with(this)
                        .load(imageUrl)
                        //.centerCrop()
                        .into(thumbnailIV);
            }

            titleTV.setText(book.getVolumeInfo().getTitle());
            authorTV.setText(book.getVolumeInfo().getAuthors().toString());
            descriptionTV.setText(book.getVolumeInfo().getDescription());

        }
    }

    public void buyBookOnClick(View view) {
        //open browser with the link
        if(book.getSaleInfo().getBuyLink() != null || !book.getSaleInfo().getBuyLink().equals("--")) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(book.getSaleInfo().getBuyLink()));
            browserIntent.setPackage("com.android.chrome");
            try {
                startActivity(browserIntent);
            } catch (ActivityNotFoundException ex) {
                // Chrome browser presumably not installed so allow user to choose instead
                browserIntent.setPackage(null);
                startActivity(browserIntent);
            }
        } else{
            Toast.makeText(this, "No Link Available", Toast.LENGTH_SHORT).show();
        }
    }

    public void backButtonDetails(View view) {
        onBackPressed();
    }

    public void changeFavorites(View view) {
       // if(bookDetailsViewModel.isBookFavorite()) {
        if(book.isFavorite()) {
            bookDetailsViewModel.removeBookFromFavorite(book.getId());
            favBtn.setImageResource(R.drawable.ic_baseline_favorite_border_24);
            book.setFavorite(false);
        } else{
            bookDetailsViewModel.addBookToFavorite(book.getId());
            favBtn.setImageResource(R.drawable.ic_baseline_favorite_24);
            book.setFavorite(true);
        }
    }
}