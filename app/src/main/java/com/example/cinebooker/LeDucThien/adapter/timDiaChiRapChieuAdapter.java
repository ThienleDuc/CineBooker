package com.example.cinebooker.LeDucThien.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.entity.timDiaChiRapChieuEntity;
import com.example.cinebooker.R;

import java.util.List;

public class timDiaChiRapChieuAdapter extends RecyclerView.Adapter<timDiaChiRapChieuAdapter.viewHolder> {

    private List<timDiaChiRapChieuEntity> rapChieulist;
    private Context context;

    public timDiaChiRapChieuAdapter() {

    }

    public timDiaChiRapChieuAdapter(List<timDiaChiRapChieuEntity> rapChieulist, Context context) {
        this.rapChieulist = rapChieulist;
        this.context = context;
    }

    @NonNull
    @Override
    public timDiaChiRapChieuAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timdiachirapchieu, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull timDiaChiRapChieuAdapter.viewHolder holder, int position) {
        timDiaChiRapChieuEntity rapChieu = rapChieulist.get(position);

        holder.cinemaLogor.setImageResource(rapChieu.getCinemalog0());
        holder.name.setText(rapChieu.getName());
        holder.location.setText(rapChieu.getLocation());

        holder.map.setOnClickListener(v -> {
            String map = rapChieu.getMap();
            Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(map));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            context.startActivity(mapIntent);
        });
    }

    @Override
    public int getItemCount() {
        return rapChieulist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView cinemaLogor, map;
        TextView location, name;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            cinemaLogor = itemView.findViewById(R.id.cinema_logo);
            name = itemView.findViewById(R.id.cinema_name);
            location = itemView.findViewById(R.id.cinema_location);
            map = itemView.findViewById(R.id.cinema_map);
        }
    }
}
