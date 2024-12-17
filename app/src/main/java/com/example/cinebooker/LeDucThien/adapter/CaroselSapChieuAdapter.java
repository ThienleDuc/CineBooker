package com.example.cinebooker.LeDucThien.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cinebooker.LeDucThien.entity.ent_PhimSapChieu;
import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Activity.XemChiTietPhim;
import com.example.cinebooker.generalMethod.ActivityOpen;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CaroselSapChieuAdapter extends RecyclerView.Adapter<CaroselSapChieuAdapter.viewHolder> {
    private List<ent_PhimSapChieu> sapChieuList;

    // Constructor
    public CaroselSapChieuAdapter() {
    }

    // Set dữ liệu cho adapter
    @SuppressLint("NotifyDataSetChanged")
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ent_PhimSapChieu sapChieu = sapChieuList.get(position);

        // Load ảnh poster phim bằng Picasso
        String imageName = sapChieu.getAnhPhim();
        Context context = holder.itemView.getContext();
        @SuppressLint("DiscouragedApi") int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId)
                .resize(800, 800)// Load resource ID
                .into(holder.moviePoster);
        // Set thông tin độ tuổi
        holder.age.setText(sapChieu.getTuoi() + "+");

        // Set tên phim
        holder.movieName.setText(sapChieu.getTenPhim() != null ? sapChieu.getTenPhim() : "Chưa cập nhật");

        // Set thể loại phim
        holder.styleMovie.setText(sapChieu.getTenTheLoai() != null ? sapChieu.getTenTheLoai() : "Chưa cập nhật");
        int maPhim = sapChieu.getMaPhim();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                if (context instanceof AppCompatActivity) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor;
                    editor = sharedPreferences.edit();
                    editor.putInt("maPhim", sapChieu.getMaPhim());
                    editor.apply();
                    notifyDataSetChanged();
                    Intent intent = new Intent(context, XemChiTietPhim.class);
                    context.startActivity(intent);                }
            }
        });
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
