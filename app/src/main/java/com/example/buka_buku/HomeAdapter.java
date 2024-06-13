package com.example.buka_buku;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buka_buku.model.Book;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Viewholder> {
    ArrayList<Book> arrayList;
    Activity activity;

    public HomeAdapter(ArrayList<Book> arrayList, Activity activity){
        this.arrayList = arrayList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_home, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Book book = arrayList.get(position);
        holder.titleText.setText(book.getTitle());
        holder.autorText.setText(book.getAuthor());
        Glide.with(holder.image.getContext()).load(book.getCover()).into(holder.image);
        holder.cardBook.setOnClickListener(v ->{
            Intent intent = new Intent(activity, DetailActivity.class);
            intent.putExtra("book", book);
            activity.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView titleText, autorText;
        CardView cardBook;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            titleText = itemView.findViewById(R.id.txt_booktitle);
            autorText = itemView.findViewById(R.id.txt_bookautor);
            cardBook = itemView.findViewById(R.id.cardView);
        }
    }
}
