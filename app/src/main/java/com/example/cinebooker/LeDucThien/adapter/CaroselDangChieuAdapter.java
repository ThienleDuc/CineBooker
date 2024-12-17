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

import com.example.cinebooker.LeDucThien.entity.ent_PhimDangChieu;
import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Activity.XemChiTietPhim;
import com.example.cinebooker.generalMethod.NumberFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CaroselDangChieuAdapter extends RecyclerView.Adapter<CaroselDangChieuAdapter.viewHolder> {

    private List<ent_PhimDangChieu> dangChieulist;

    // Constructor
    @SuppressLint("NotifyDataSetChanged")
    public void SetData(List<ent_PhimDangChieu> dangChieulist) {
        this.dangChieulist = dangChieulist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.iteams_carosel_movie_dangchieu, parent, false);
        return new viewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ent_PhimDangChieu dangChieu = dangChieulist.get(position);

        // Lấy tên ảnh từ đối tượng dangChieu
        String imageName = dangChieu.getAnhPhim();
        Context context = holder.itemView.getContext();
        @SuppressLint("DiscouragedApi") int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId)
                .resize(800, 800)// Load resource ID
                .into(holder.moviePoster);

        // Set age (check null or invalid data)
        holder.age.setText(dangChieu.getTuoi() + "+");

        // Set movie name
        holder.movieName.setText(dangChieu.getTenPhim() != null ? dangChieu.getTenPhim() : "Chưa cập nhật");

        // Set movie style
        holder.styleMovie.setText(dangChieu.getTenTheLoai() != null ? dangChieu.getTenTheLoai() : "Chưa cập nhật");

        // Format and set vote
        holder.vote.setText(NumberFormatter.formatNumber(dangChieu.getDiemDanhGiaTrungBinh()));

        int maPhim = dangChieu.getMaPhim();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                if (context instanceof AppCompatActivity) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor;
                    editor = sharedPreferences.edit();
                    editor.putInt("maPhim", dangChieu.getMaPhim());
                    editor.apply();
                    notifyDataSetChanged();
                    Intent intent = new Intent(context, XemChiTietPhim.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dangChieulist != null ? dangChieulist.size() : 0;
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView age, movieName, styleMovie, vote, duration, releaseDate;

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
