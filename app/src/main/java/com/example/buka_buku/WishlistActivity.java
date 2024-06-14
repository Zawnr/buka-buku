package com.example.buka_buku;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buka_buku.model.Book;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private ImageButton btnCheckout;
    private RecyclerView rvWishlist;
    private WishlistAdapter wishlistAdapter;
    private List<Book> wishlistItems;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

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

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance("https://buka-buku-919aa-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();

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
        String userId = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference wishlistRef = databaseReference.child("wishlist").child(userId);

        wishlistRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                wishlistItems.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book book = snapshot.getValue(Book.class);
                    if (book != null) {
                        wishlistItems.add(book);
                    }
                }
                wishlistAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }

}
