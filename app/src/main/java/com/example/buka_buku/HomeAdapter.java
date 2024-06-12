package com.example.buka_buku;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buka_buku.model.Book;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Viewholder> {
    ArrayList<Book> arrayList;

    public HomeAdapter(ArrayList<Book> arrayList){
        this.arrayList = arrayList;
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
        holder.image.setImageResource(R.drawable.ic_books2);
        holder.titleText.setText(book.getTitle());
        holder.autorText.setText(book.getAuthor());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView titleText, autorText;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            titleText = itemView.findViewById(R.id.txt_booktitle);
            autorText = itemView.findViewById(R.id.txt_bookautor);
        }
    }
}
