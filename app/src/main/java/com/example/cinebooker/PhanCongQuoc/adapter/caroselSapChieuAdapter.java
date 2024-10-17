package com.example.cinebooker.PhanCongQuoc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.entity.caroselSapChieuEntity;
import com.example.cinebooker.R;

import java.util.List;

public class caroselSapChieuAdapter extends RecyclerView.Adapter<caroselSapChieuAdapter.viewHolder> {
    private List<caroselSapChieuEntity> caroselSapChieuEntityList;

    public caroselSapChieuAdapter() {
    }

    public caroselSapChieuAdapter(List<caroselSapChieuEntity> caroselSapChieuEntityList) {
        this.caroselSapChieuEntityList = caroselSapChieuEntityList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteams_carosel_movie_sapchieu, parent, false);
        return new viewHolder(view);
    }

    @NonNull
    @Override
    public void onBindViewHolder(@NonNull caroselSapChieuAdapter.viewHolder holder, int position) {
        caroselSapChieuEntity sapChieu = caroselSapChieuEntityList.get(position);

        holder.moviePoster.setImageResource(sapChieu.getMoviePoster());

        holder.age.setText(sapChieu.getAge());
        holder.movieName.setText(sapChieu.getMovieName());
        holder.styleMovie.setText(sapChieu.getStyleMovie());
    }


    @Override
    public int getItemCount() {
        return caroselSapChieuEntityList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView age, movieName, styleMovie;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            moviePoster = itemView.findViewById(R.id.sapChieu_poster_movie);
            age = itemView.findViewById(R.id.sapChieu_age);
            movieName = itemView.findViewById(R.id.sapChieu_movie_name);
            styleMovie = itemView.findViewById(R.id.sapChieu_movie_style);
        }
    }
}
