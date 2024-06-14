package com.example.buka_buku;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buka_buku.model.Book;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {

    private List<Book> wishlistItems;

    public WishlistAdapter(List<Book> wishlistItems) {
        this.wishlistItems = wishlistItems;
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_wishlist, parent, false);
        return new WishlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int position) {
        Book item = wishlistItems.get(position);
        holder.bookTitle.setText(item.getTitle());
        holder.genre.setText(item.getGenre());
        holder.author.setText(item.getAuthor());

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    wishlistItems.remove(currentPosition);
                    notifyItemRemoved(currentPosition);
                    notifyItemRangeChanged(currentPosition, wishlistItems.size());
                }
            }
        });

//        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            int currentPosition = holder.getAdapterPosition();
////            if (currentPosition != RecyclerView.NO_POSITION) {
////                wishlistItems.get(currentPosition).setChecked(isChecked);
////            }
//        });
    }

    @Override
    public int getItemCount() {
        return wishlistItems.size();
    }

    public static class WishlistViewHolder extends RecyclerView.ViewHolder {
//        CheckBox checkBox;
//        ImageView bookCover;
        TextView bookTitle;
        TextView genre;
        TextView author;
        ImageButton removeButton;

        public WishlistViewHolder(@NonNull View itemView) {
            super(itemView);
//            checkBox = itemView.findViewById(R.id.checkBox);
//            bookCover = itemView.findViewById(R.id.book_cover);
            bookTitle = itemView.findViewById(R.id.book_title);
            genre = itemView.findViewById(R.id.genre);
            author = itemView.findViewById(R.id.author);
            removeButton = itemView.findViewById(R.id.remove);
        }
    }
}
