package com.example.cinebooker.LeDucThien.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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

public class DangChieuAdapter extends RecyclerView.Adapter<DangChieuAdapter.viewHolder> {

    private List<ent_PhimDangChieu> dangChieulist;

    public DangChieuAdapter() {
    }

    @SuppressLint("NotifyDataSetChanged")
    public void SetData (List<ent_PhimDangChieu> dangChieulist) {
        this.dangChieulist = dangChieulist;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public DangChieuAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies_dangchieu, parent, false);
        return new viewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DangChieuAdapter.viewHolder holder, int position) {
        ent_PhimDangChieu dangChieu = dangChieulist.get(position);

        // Load poster movie using Picasso
        String imageName = dangChieu.getAnhPhim();
        Context context = holder.itemView.getContext();
        @SuppressLint("DiscouragedApi") int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId)  // Load resource ID
                .resize(800, 800)// Load resource ID
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
