package com.example.buka_buku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.buka_buku.model.Book;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetailActivity extends AppCompatActivity {

    private ImageView bookCover;
    private TextView bookTitle;
    private TextView bookAuthor;
    private ImageButton btnBack;
    private Button btnBookmark;
    private TextView bookDescription;
    private TextView bookRating;
    private TextView bookGenre;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bookCover = findViewById(R.id.img_cover);
        bookTitle = findViewById(R.id.title);
        bookAuthor = findViewById(R.id.writer);
        btnBack = findViewById(R.id.btn_back);
        bookDescription = findViewById(R.id.description);
        bookRating = findViewById(R.id.rating);
        bookGenre = findViewById(R.id.genre);
        btnBookmark = findViewById(R.id.btn_wishlist);

        databaseReference = FirebaseDatabase.getInstance("https://buka-buku-919aa-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("book");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndAddToWishlist(book);
            }
        });

        if (book != null) {
            bookTitle.setText(book.getTitle());
            bookAuthor.setText(book.getAuthor());
            bookDescription.setText(book.getDescription());
            bookRating.setText(book.getRating());
            bookGenre.setText(book.getGenre());
            Glide.with(this).load(book.getCover()).into(bookCover);
        }
    }

    private void checkAndAddToWishlist(Book book) {
        String userId = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference wishlistRef = databaseReference.child("wishlist").child(userId);
        wishlistRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean alreadyInWishlist = false;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Book wishlistBook = snapshot.getValue(Book.class);
                    if (wishlistBook != null && wishlistBook.getTitle().equals(book.getTitle())) {
                        alreadyInWishlist = true;
                        break;
                    }
                }
                if (alreadyInWishlist) {
                    Toast.makeText(DetailActivity.this, "Book is already in wishlist.", Toast.LENGTH_SHORT).show();
                } else {
                    addToWishlist(book);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }

    private void addToWishlist(Book book) {
        String userId = firebaseAuth.getCurrentUser().getUid();
        DatabaseReference cartRef = databaseReference.child("wishlist").child(userId);
        String cartId = cartRef.push().getKey();

        if (cartId != null) {
            cartRef.child(cartId).setValue(book);
            Toast.makeText(DetailActivity.this, "Success added Book in wishlist.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DetailActivity.this, WishlistActivity.class);
            startActivity(intent);
        }
    }
}