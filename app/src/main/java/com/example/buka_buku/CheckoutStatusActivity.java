package com.example.buka_buku;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class CheckoutStatusActivity extends AppCompatActivity {
    ImageButton btn_back;
    AppCompatButton btn_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_status);

        btn_back = findViewById(R.id.btn_back);
        btn_finish = findViewById(R.id.btn_finish);

        btn_back.setOnClickListener(v -> startActivity(new Intent(CheckoutStatusActivity.this, CheckoutActivity.class)));
        btn_finish.setOnClickListener(v -> startActivity(new Intent(CheckoutStatusActivity.this, HomeActivity.class)));
    }
}