package com.example.buka_buku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.buka_buku.model.Book;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistViewHolder> {

    private List<Book> wishlistItems;
    private Context context;
    private DatabaseReference databaseReference;

    public WishlistAdapter(List<Book> wishlistItems) {
        this.wishlistItems = wishlistItems;
        this.databaseReference = FirebaseDatabase.getInstance("https://buka-buku-919aa-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
    }

    @NonNull
    @Override
    public WishlistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_wishlist, parent, false);
        return new WishlistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WishlistViewHolder holder, int position) {
        Book book = wishlistItems.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return wishlistItems.size();
    }

    class WishlistViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        ImageView bookCover;
        TextView bookTitle, genre, author;
        ImageButton removeButton;

        WishlistViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            bookCover = itemView.findViewById(R.id.book_cover);
            bookTitle = itemView.findViewById(R.id.book_title);
            genre = itemView.findViewById(R.id.genre);
            author = itemView.findViewById(R.id.author);
            removeButton = itemView.findViewById(R.id.remove);

            // Setup event click untuk tombol hapus
            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Book book = wishlistItems.get(position);
                        removeBookFromWishlist(book);
                    }
                }
            });
        }

        void bind(Book book) {
            bookTitle.setText(book.getTitle());
            genre.setText(book.getGenre());
            author.setText(book.getAuthor());
            Glide.with(bookCover.getContext()).load(book.getCover()).into(bookCover); // Load gambar dari URL

            // Set initial state of checkbox
            checkBox.setChecked(book.isSelected());

            // Handle checkbox state change
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> book.setSelected(isChecked));
        }


        void removeBookFromWishlist(Book book) {
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DatabaseReference bookRef = databaseReference.child("wishlist").child(userId).child(book.getKey());

            bookRef.removeValue().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    wishlistItems.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), wishlistItems.size());
                    Toast.makeText(context, "Book removed from wishlist", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to remove book", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
