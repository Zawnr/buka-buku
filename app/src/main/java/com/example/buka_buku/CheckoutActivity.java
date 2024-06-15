package com.example.buka_buku;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buka_buku.model.Book;
import com.example.buka_buku.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {
    ImageButton btn_back;
    AppCompatButton btn_submit_form;
    List<Book> books;
    TextView txt_name;
    TextView txt_email;
    TextView txt_phone;
    RecyclerView recyclerView;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        btn_back = findViewById(R.id.btn_back);
        btn_submit_form = findViewById(R.id.btn_submit_form);
        txt_name = findViewById(R.id.in_name);
        txt_email = findViewById(R.id.in_email);
        txt_phone = findViewById(R.id.in_phone);
        recyclerView = findViewById(R.id.rv_checkout);
        RecyclerView.LayoutManager wishLayout = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(wishLayout);

        getUser();
        if (getIntent().hasExtra("book")) {
            loadDummyBooks();
        }
        if (getIntent().hasExtra("wishlistItems")) {
            loadWishlistBooks();
        }
//        loadDummyBooks();

        CheckoutAdapter checkoutAdapter = new CheckoutAdapter(books);
        recyclerView.setAdapter(checkoutAdapter);
        checkoutAdapter.notifyDataSetChanged();

        btn_back.setOnClickListener(v -> finish());
        btn_submit_form.setOnClickListener(v -> {

            Toast.makeText(CheckoutActivity.this, "Form Submitted", Toast.LENGTH_SHORT).show();
            // Navigate to CheckoutStatusActivity
            Intent intent = new Intent(CheckoutActivity.this, CheckoutStatusActivity.class);
            startActivity(intent);
        });
    }

    public void getUser() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatabaseReference userRef = databaseReference.child("users").child(userId);
            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User user = dataSnapshot.getValue(User.class);
                    if (user != null) {
                        txt_name.setText(user.getName());
                        txt_email.setText(user.getEmail());
                        txt_phone.setText(user.getPhoneNumber());
                    } else {
                        Toast.makeText(CheckoutActivity.this, "User data not found.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(CheckoutActivity.this, "Failed to load user data.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "User not logged in.", Toast.LENGTH_SHORT).show();
            // Handle the case where the user is not logged in, such as redirecting to the login activity
        }
    }

    public void loadDummyBooks() {
        books = new ArrayList<>();
        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("book");
        if (book != null) {
            books.add(book);
        }
    }

    private void loadWishlistBooks() {
        Intent intent = getIntent();
        if (intent.hasExtra("wishlistItems")) {
            List<Book> allWishlistItems = (List<Book>) intent.getSerializableExtra("wishlistItems");
            books = getSelectedBooks(allWishlistItems);
        } else {
            books = new ArrayList<>();
        }
    }
    private List<Book> getSelectedBooks(List<Book> wishlistItems) {
        List<Book> selectedBooks = new ArrayList<>();
        for (Book book : wishlistItems) {
            if (book.isSelected()) {
                selectedBooks.add(book);
            }
        }
        return selectedBooks;
    }
}