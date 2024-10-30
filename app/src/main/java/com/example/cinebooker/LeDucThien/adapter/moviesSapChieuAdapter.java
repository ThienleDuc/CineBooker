package com.example.cinebooker.LeDucThien.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.entity.caroselSapChieuEntity;
import com.example.cinebooker.LeDucThien.generalMethod.NumberFormatter;
import com.example.cinebooker.R;

import java.util.List;

public class moviesSapChieuAdapter extends RecyclerView.Adapter<moviesSapChieuAdapter.viewHolder> {

    private List<caroselSapChieuEntity> dangChieulist;

    public moviesSapChieuAdapter() {

    }

    public moviesSapChieuAdapter(List<caroselSapChieuEntity> dangChieulist) {
        this.dangChieulist = dangChieulist;
    }

    @NonNull
    @Override
    public moviesSapChieuAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies_sapchieu, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull moviesSapChieuAdapter.viewHolder holder, int position) {
        caroselSapChieuEntity dangChieu = dangChieulist.get(position);

        holder.moviePoster.setImageResource(dangChieu.getMoviePoster());

        holder.age.setText(dangChieu.getAge());
        holder.movieName.setText(dangChieu.getMovieName());
        holder.styleMovie.setText(dangChieu.getStyleMovie());
    }

    @Override
    public int getItemCount() {
        return dangChieulist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
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
