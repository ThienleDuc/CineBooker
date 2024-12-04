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

public class caroselDangChieuAdapter extends RecyclerView.Adapter<caroselDangChieuAdapter.viewHolder> {

    private List<ent_PhimDangChieu> dangChieulist;

    public caroselDangChieuAdapter() {

    }

    public caroselDangChieuAdapter(List<ent_PhimDangChieu> dangChieulist) {
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
        ent_PhimDangChieu dangChieu = dangChieulist.get(position);

        Picasso.get().load(dangChieu.getAnhPhim()).into(holder.moviePoster);

        holder.age.setText(dangChieu.getTuoi());
        holder.movieName.setText(dangChieu.getTenPhim());
        holder.styleMovie.setText(dangChieu.getTenTheLoai());

        holder.vote.setText(NumberFormatter.formatNumber(dangChieu.getDiemDanhGiaTrungBinh()));

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
