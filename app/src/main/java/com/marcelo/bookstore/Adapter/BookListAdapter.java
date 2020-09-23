package com.marcelo.bookstore.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.marcelo.bookstore.Model.Book;
import com.marcelo.bookstore.R;

import java.util.ArrayList;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookListViewHolder>{

    private ArrayList<Book> books =  new ArrayList<>();
    private Context mContext;
    private OnItemClickListener onClickListener;

    public BookListAdapter(ArrayList<Book> books, Context mContext) {
        this.books = books;
        this.mContext = mContext;
    }

    public BookListAdapter(Context mContext) {
        this.mContext = mContext;
    }



    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.onClickListener = listener;
    }

    public static class BookListViewHolder extends RecyclerView.ViewHolder{

        private ImageView thumbnailIV;
        private TextView titleTV;

        public BookListViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            thumbnailIV = itemView.findViewById(R.id.thumbnailIV);
            titleTV = itemView.findViewById(R.id.titleTV);

            itemView.setOnClickListener(v ->{
                if(listener != null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public BookListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        BookListViewHolder bookListViewHolder;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        v = inflater.inflate(R.layout.item_book_list, parent, false);
        bookListViewHolder = new BookListViewHolder(v, onClickListener);
        //TODO add end type to add a progressbar for when user is at the bottom & more books are loading

        return bookListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookListViewHolder holder, int position) {
        Book book = books.get(position);

        holder.titleTV.setText(book.getVolumeInfo().getTitle());

        if (book.getVolumeInfo().getImageLinks() != null) {
            String imageUrl = book.getVolumeInfo().getImageLinks().getThumbnail()
                    .replace("http://", "https://");

            Glide.with(mContext)
                    .load(imageUrl)
                    //.centerCrop()
                    .into(holder.thumbnailIV);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void addBooks(ArrayList<Book> newBooks){
        books.addAll(newBooks);
    }

    public Book getBook(int position){
        return books.get(position);
    }

    public void clearBooks(){
        books.clear();
    }

    public ArrayList<Book> getCurrentArray() {
        return books;
    }

}
