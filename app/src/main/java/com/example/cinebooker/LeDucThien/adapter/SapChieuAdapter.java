package com.example.cinebooker.LeDucThien.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.R;
import com.example.cinebooker.LeDucThien.entity.ent_PhimSapChieu;
import com.example.cinebooker.TranGiaThai.Activity.XemChiTietPhim;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SapChieuAdapter extends RecyclerView.Adapter<SapChieuAdapter.viewHolder> {

    private List<ent_PhimSapChieu> dangChieulist;

    public SapChieuAdapter() {

    }

    @SuppressLint("NotifyDataSetChanged")
    public void SetData(List<ent_PhimSapChieu> dangChieulist) {
        this.dangChieulist = dangChieulist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SapChieuAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies_sapchieu, parent, false);
        return new viewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SapChieuAdapter.viewHolder holder, int position) {
        ent_PhimSapChieu dangChieu = dangChieulist.get(position);

        // Load poster movie using Picasso
        String imageName = dangChieu.getAnhPhim();
        Context context = holder.itemView.getContext();
        @SuppressLint("DiscouragedApi") int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId)  // Load resource ID
                .resize(800, 800)// Load resource ID
                .into(holder.moviePoster);

        // Set age (check null or invalid data)
        holder.age.setText(String.valueOf(dangChieu.getTuoi()) + "+");

        // Set movie name
        holder.movieName.setText(dangChieu.getTenPhim() != null ? dangChieu.getTenPhim() : "Chưa cập nhật");

        // Set movie style
        holder.styleMovie.setText(dangChieu.getTenTheLoai() != null ? dangChieu.getTenTheLoai() : "Chưa cập nhật");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, XemChiTietPhim.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dangChieulist.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView age, movieName, styleMovie;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            moviePoster = itemView.findViewById(R.id.sap_chieu_poster_movie);
            age = itemView.findViewById(R.id.sap_chieu_age);
            movieName = itemView.findViewById(R.id.sap_chieu_movie_name);
            styleMovie = itemView.findViewById(R.id.sap_chieu_movie_style);
        }
    }
}
