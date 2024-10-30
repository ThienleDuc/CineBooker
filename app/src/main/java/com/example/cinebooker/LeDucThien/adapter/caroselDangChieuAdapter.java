package com.example.cinebooker.LeDucThien.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.entity.caroselDangChieuEntity;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.NumberFormatter;

import java.util.List;

public class caroselDangChieuAdapter extends RecyclerView.Adapter<caroselDangChieuAdapter.viewHolder> {

    private List<caroselDangChieuEntity> dangChieulist;

    public caroselDangChieuAdapter() {

    }

    public caroselDangChieuAdapter(List<caroselDangChieuEntity> dangChieulist) {
        this.dangChieulist = dangChieulist;
    }

    @NonNull
    @Override
    public caroselDangChieuAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteams_carosel_movie_dangchieu, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull caroselDangChieuAdapter.viewHolder holder, int position) {
        caroselDangChieuEntity dangChieu = dangChieulist.get(position);

        holder.moviePoster.setImageResource(dangChieu.getMoviePoster());

        holder.age.setText(dangChieu.getAge());
        holder.movieName.setText(dangChieu.getMovieName());
        holder.styleMovie.setText(dangChieu.getStyleMovie());

        holder.vote.setText(NumberFormatter.formatNumber(dangChieu.getVote().doubleValue()));

    }

    @Override
    public int getItemCount() {
        return dangChieulist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView age, movieName, styleMovie, vote;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            moviePoster = itemView.findViewById(R.id.dang_chieu_poster_movie);
            age = itemView.findViewById(R.id.dang_chieu_age);
            movieName = itemView.findViewById(R.id.dang_chieu_movie_name);
            styleMovie = itemView.findViewById(R.id.dang_chieu_movie_style);
            vote = itemView.findViewById(R.id.dang_chieu_vote);
        }
    }
}
