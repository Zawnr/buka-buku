package com.example.buka_buku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Viewholder> {
    Context context;
    ArrayList<HomeModel> arrayList = new ArrayList<>();
    public HomeAdapter(Context context, ArrayList<HomeModel> arrayList){
        this.arrayList = arrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_list_home, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.image.setImageResource(arrayList.get(position).image);
        holder.titleText.setText(arrayList.get(position).title);
        holder.autorText.setText(arrayList.get(position).autor);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView titleText, autorText;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            titleText = itemView.findViewById(R.id.txt_booktitle);
            autorText = itemView.findViewById(R.id.txt_bookautor);
        }
    }
}
