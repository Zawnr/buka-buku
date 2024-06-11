package com.example.buka_buku;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buka_buku.R;
import com.example.buka_buku.model.Book;

import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.ViewHolder> {
    List<Book> books;

    public CheckoutAdapter(List<Book> books) {
        this.books = books;
    }

    @NonNull
    @Override
    public CheckoutAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_checkout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutAdapter.ViewHolder holder, int position) {
        final Book book = books.get(position);

        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthor());
        holder.genre.setText(book.getGenre());

        // Using a placeholder image from resources
        holder.ic_cover.setImageResource(R.drawable.ic_books1);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, author, genre;
        ImageView ic_cover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.txt_bookname);
            this.author = itemView.findViewById(R.id.txt_bookauthor);
            this.genre = itemView.findViewById(R.id.txt_bookgenre);
            this.ic_cover = itemView.findViewById(R.id.ic_cover_checkout);
        }
    }
}
