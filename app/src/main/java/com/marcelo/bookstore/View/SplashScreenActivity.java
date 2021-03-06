package com.marcelo.bookstore.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.marcelo.bookstore.Model.Book;
import com.marcelo.bookstore.R;
import com.marcelo.bookstore.ViewModel.SplashScreenViewModel;

import java.util.ArrayList;

public class SplashScreenActivity extends AppCompatActivity {

    private SplashScreenViewModel splashScreenViewModel;

    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        init();

    }

    private void init(){
        loadingPB = findViewById(R.id.loadingProgress);
        splashScreenViewModel = new ViewModelProvider(this).get(SplashScreenViewModel.class);
        splashScreenViewModel.init(getApplicationContext());
        splashScreenViewModel.getBooks().observe(this, books -> {
            //when new data it's available, will do the next steps
            Log.i("observer", "Arraylist Size: " + books.size());
            //go to main list
            Intent intent = new Intent(this, BookListActivity.class);
            startActivity(intent);
            finish();
        });
    }

}