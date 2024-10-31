package com.example.cinebooker.LeDucThien.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cinebooker.LeDucThien.entity.rapChieuEntity;
import com.example.cinebooker.R;

import java.util.List;

public class rapChieuAdapter extends RecyclerView.Adapter<rapChieuAdapter.viewHolder> {

    private List<rapChieuEntity> rapChieulist;

    public rapChieuAdapter() {

    }

    public rapChieuAdapter(List<rapChieuEntity> dangChieulist) {
        this.rapChieulist = dangChieulist;
    }

    @NonNull
    @Override
    public rapChieuAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rapchieu, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rapChieuAdapter.viewHolder holder, int position) {
        rapChieuEntity rapChieu = rapChieulist.get(position);

        holder.cinemaLogor.setImageResource(rapChieu.getCinemalog0());

        holder.name.setText(rapChieu.getName());
    }

    @Override
    public int getItemCount() {
        return rapChieulist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView cinemaLogor;
        TextView name;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            cinemaLogor = itemView.findViewById(R.id.cinema_logo);
            name = itemView.findViewById(R.id.cinema_name);
        }
    }
}
