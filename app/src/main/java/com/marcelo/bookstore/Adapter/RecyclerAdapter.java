package com.marcelo.bookstore.Adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marcelo.bookstore.Model.Book;

import java.util.ArrayList;

public class RecyclerAdapter {

    private ArrayList<Book> books;
    private OnItemClickListener listener;
    private Context mContext;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public static class BookStoreViewHolder extends RecyclerView.ViewHolder{

        ImageView thumbnail;
        TextView title;

        public BookStoreViewHolder(@NonNull View itemView) {
            super(itemView);
        }


    }

}
