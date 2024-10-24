package com.example.cinebooker.LeDucThien.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.entity.diaChiRapChieuEntity;
import com.example.cinebooker.R;

import java.util.List;

public class diaChiRapChieuAdapter extends RecyclerView.Adapter<diaChiRapChieuAdapter.viewHolder> {

    private List<diaChiRapChieuEntity> rapChieulist;

    public diaChiRapChieuAdapter() {

    }

    public diaChiRapChieuAdapter(List<diaChiRapChieuEntity> rapChieulist) {
        this.rapChieulist = rapChieulist;
    }

    @NonNull
    @Override
    public diaChiRapChieuAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diachirapchieu, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull diaChiRapChieuAdapter.viewHolder holder, int position) {
        diaChiRapChieuEntity rapChieu = rapChieulist.get(position);

        holder.cinemaLogor.setImageResource(rapChieu.getCinemalog0());

        holder.location.setText(rapChieu.getName());
    }

    @Override
    public int getItemCount() {
        return rapChieulist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView cinemaLogor;
        TextView location;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            cinemaLogor = itemView.findViewById(R.id.cinema_logo);
            location = itemView.findViewById(R.id.cinema_location);
        }
    }
}
