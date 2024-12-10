package com.example.cinebooker.LeDucThien.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cinebooker.LeDucThien.entity.ent_PhimSapChieu;
import com.example.cinebooker.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class caroselSapChieuAdapter extends RecyclerView.Adapter<caroselSapChieuAdapter.viewHolder> {
    private List<ent_PhimSapChieu> sapChieuList;

    // Constructor
    public caroselSapChieuAdapter() {
    }

    // Set dữ liệu cho adapter
    public void SetData(List<ent_PhimSapChieu> sapChieuList) {
        this.sapChieuList = sapChieuList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteams_carosel_movie_sapchieu, parent, false);
        return new viewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ent_PhimSapChieu sapChieu = sapChieuList.get(position);

        // Load ảnh poster phim bằng Picasso
        Picasso.get().load(sapChieu.getAnhPhim())
                .placeholder(R.drawable.placeholder)  // Optional: Thêm ảnh placeholder
                .into(holder.moviePoster);

        // Set thông tin độ tuổi
        holder.age.setText(String.valueOf(sapChieu.getTuoi()) + "+");

        // Set tên phim
        holder.movieName.setText(sapChieu.getTenPhim() != null ? sapChieu.getTenPhim() : "Chưa cập nhật");

        // Set thể loại phim
        holder.styleMovie.setText(sapChieu.getTenTheLoai() != null ? sapChieu.getTenTheLoai() : "Chưa cập nhật");
    }

    @Override
    public int getItemCount() {
        return sapChieuList != null ? sapChieuList.size() : 0;
    }

    // ViewHolder
    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView age, movieName, styleMovie;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            // Khởi tạo các view
            moviePoster = itemView.findViewById(R.id.sapChieu_poster_movie);
            age = itemView.findViewById(R.id.sapChieu_age);
            movieName = itemView.findViewById(R.id.sapChieu_movie_name);
            styleMovie = itemView.findViewById(R.id.sapChieu_movie_style);
        }
    }
}
