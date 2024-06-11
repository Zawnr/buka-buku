package com.example.buka_buku;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buka_buku.R;
import com.example.buka_buku.model.Book;
import com.example.buka_buku.model.User;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {
    ImageButton btn_back;
    AppCompatButton btn_submit_form;
    List<Book> books;
    TextView txt_name;
    TextView txt_email;
    TextView txt_id;
    TextView txt_phone;
    RecyclerView recyclerView;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        btn_back = findViewById(R.id.btn_back);
        btn_submit_form = findViewById(R.id.btn_submit_form);
        txt_name = findViewById(R.id.in_name);
        txt_email = findViewById(R.id.in_email);
        txt_id = findViewById(R.id.in_id);
        txt_phone = findViewById(R.id.in_phone);
        recyclerView = findViewById(R.id.rv_checkout);
        RecyclerView.LayoutManager wishLayout = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(wishLayout);

        getUser();
        loadDummyBooks();

        CheckoutAdapter checkoutAdapter = new CheckoutAdapter(books);
        recyclerView.setAdapter(checkoutAdapter);
        checkoutAdapter.notifyDataSetChanged();

        btn_back.setOnClickListener(v -> finish());
        btn_submit_form.setOnClickListener(v -> {
            Toast.makeText(CheckoutActivity.this, "Form Submitted", Toast.LENGTH_SHORT).show();
        });
    }

    public void getUser() {
        User user = new User("John Doe", "johndoe@example.com", "123456", "123-456-7890");
        txt_name.setText(user.getName());
        txt_email.setText(user.getEmail());
        txt_id.setText(user.getStudentId());
        txt_phone.setText(user.getPhoneNumber());
    }

    public void loadDummyBooks() {
        books = new ArrayList<>();
        books.add(new Book(getString(R.string.book_name), getString(R.string.book_author), getString(R.string.book_genre)));
    }
}
