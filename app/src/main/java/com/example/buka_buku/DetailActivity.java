package com.example.buka_buku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.buka_buku.model.Book;

public class DetailActivity extends AppCompatActivity {

    private ImageView bookCover;
    private TextView bookTitle;
    private TextView bookAuthor;
    private ImageButton btnBack;
    private TextView bookDescription;
    private TextView bookRating;
    private TextView bookGenre;

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

        bookCover = findViewById(R.id.image);
        bookTitle = findViewById(R.id.title);
        bookAuthor = findViewById(R.id.writer);
        btnBack = findViewById(R.id.btn_back);
        bookDescription = findViewById(R.id.description);
        bookRating = findViewById(R.id.rating);
        bookGenre = findViewById(R.id.genre);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("book");

        if (book != null) {
            bookTitle.setText(book.getTitle());
            bookAuthor.setText(book.getAuthor());
            bookDescription.setText(book.getDescription());
            bookRating.setText(book.getRating());
            bookGenre.setText(book.getGenre());
            Glide.with(this).load(book.getCover()).into(bookCover);
        }
    }
}