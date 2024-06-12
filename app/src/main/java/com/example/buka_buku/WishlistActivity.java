package com.example.buka_buku;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private ImageButton btnCheckout;
    private RecyclerView rvWishlist;
    private WishlistAdapter wishlistAdapter;
    private List<WishlistItem> wishlistItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        // Initialize views
        btnBack = findViewById(R.id.btn_back);
        btnCheckout = findViewById(R.id.btn_checkout);
        rvWishlist = findViewById(R.id.rv_wishlist);

        // Setup RecyclerView
        rvWishlist.setLayoutManager(new LinearLayoutManager(this));
        wishlistItems = new ArrayList<>();
        wishlistAdapter = new WishlistAdapter(wishlistItems);
        rvWishlist.setAdapter(wishlistAdapter);

        // Load wishlist items (this should be replaced with actual data loading logic)
        loadWishlistItems();

        // Setup button click listeners
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WishlistActivity.this, "Checkout button clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadWishlistItems() {
        // Example data; replace with actual data retrieval logic
        wishlistItems.add(new WishlistItem(false, R.drawable.ic_books2, "Book Title 1", "Genre 1", "Author 1"));
        wishlistItems.add(new WishlistItem(false, R.drawable.ic_books2, "Book Title 2", "Genre 2", "Author 2"));
        wishlistItems.add(new WishlistItem(false, R.drawable.ic_books2, "Book Title 3", "Genre 3", "Author 3"));
        wishlistAdapter.notifyDataSetChanged();
    }
}
