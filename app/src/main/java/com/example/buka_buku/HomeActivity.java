package com.example.buka_buku;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<HomeModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.recyler_view_home);
        arrayList.add(new HomeModel(R.drawable.ic_books1, "Your Title", "Your Description"));
        arrayList.add(new HomeModel(R.drawable.ic_books1, "Your Title", "Your Description"));
        arrayList.add(new HomeModel(R.drawable.ic_books1, "Your Title", "Your Description"));
        arrayList.add(new HomeModel(R.drawable.ic_books1, "Your Title", "Your Description"));
        arrayList.add(new HomeModel(R.drawable.ic_books1, "Your Title", "Your Description"));
        arrayList.add(new HomeModel(R.drawable.ic_books1, "Your Title", "Your Description"));
        arrayList.add(new HomeModel(R.drawable.ic_books1, "Your Title", "Your Description"));
        arrayList.add(new HomeModel(R.drawable.ic_books1, "Your Title", "Your Description"));
        recyclerView.setAdapter(new HomeAdapter(this, arrayList));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}