package com.example.cinebooker.LeDucThien.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.entity.ent_PhimDangChieu;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.NumberFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class moviesDangChieuAdapter extends RecyclerView.Adapter<moviesDangChieuAdapter.viewHolder> {

    private List<ent_PhimDangChieu> dangChieulist;

    public moviesDangChieuAdapter() {
    }
    
    public void setData(List<ent_PhimDangChieu> dangChieulist) {
        this.dangChieulist = dangChieulist;
        notifyDataSetChanged(); // Cập nhật adapter khi có dữ liệu mới
    }
    @NonNull
    @Override
    public moviesDangChieuAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies_dangchieu, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull moviesDangChieuAdapter.viewHolder holder, int position) {
        ent_PhimDangChieu dangChieu = dangChieulist.get(position);

        // Load poster movie using Picasso
        Picasso.get().load(dangChieu.getAnhPhim())
                .placeholder(R.drawable.placeholder)  // Optional: Add a placeholder image
                .into(holder.moviePoster);

        // Set age (convert to String)
        holder.age.setText(String.valueOf(dangChieu.getTuoi()) + "+");

        // Set movie name with "Chưa cập nhật" if null
        holder.movieName.setText(dangChieu.getTenPhim() != null ? dangChieu.getTenPhim() : "Chưa cập nhật");

        // Set movie style with "Chưa cập nhật" if null
        holder.styleMovie.setText(dangChieu.getTenTheLoai() != null ? dangChieu.getTenTheLoai() : "Chưa cập nhật");

        // Set formatted vote
        holder.vote.setText(NumberFormatter.formatNumber(dangChieu.getDiemDanhGiaTrungBinh()));
    }

    @Override
    public int getItemCount() {
        return dangChieulist != null ? dangChieulist.size() : 0;
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView age, movieName, styleMovie, vote;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize the views
            moviePoster = itemView.findViewById(R.id.dang_chieu_poster_movie);
            age = itemView.findViewById(R.id.dang_chieu_age);
            movieName = itemView.findViewById(R.id.dang_chieu_movie_name);
            styleMovie = itemView.findViewById(R.id.dang_chieu_movie_style);
            vote = itemView.findViewById(R.id.dang_chieu_vote);
        }
    }
}
