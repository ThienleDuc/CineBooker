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


import com.example.cinebooker.LeDucThien.entity.ent_TimKiemPhim;
import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Activity.XemChiTietPhim;
import com.example.cinebooker.generalMethod.ActivityOpen;
import com.example.cinebooker.generalMethod.NumberFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;


public class TimKiemAdapter extends RecyclerView.Adapter<TimKiemAdapter.SearchViewHolder> {
    private List<ent_TimKiemPhim> searchMoviesList;

    @SuppressLint("NotifyDataSetChanged")
    public void SetData(List<ent_TimKiemPhim> searchMoviesList) {
        this.searchMoviesList = searchMoviesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TimKiemAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_search, parent, false);
        return new SearchViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TimKiemAdapter.SearchViewHolder holder, int position) {
        ent_TimKiemPhim search_movies =  searchMoviesList.get(position);

        // Load poster movie using Picasso
        String imageName = search_movies.getAnhPhim();
        Context context = holder.itemView.getContext();
        @SuppressLint("DiscouragedApi") int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId)  // Load resource ID
                .resize(800, 800)// Load resource ID
                .into(holder.posterMovie);

        holder.age.setText(search_movies.getTuoi() + "+");
        holder.movieName.setText(search_movies.getTenPhim() != null ? search_movies.getTenPhim() : "Chưa cập nhật");
        holder.styleMovie.setText(search_movies.getTenTheLoai() != null ? search_movies.getTenTheLoai() : "Chưa cập nhật");
        holder.vote.setText(NumberFormatter.formatNumber(search_movies.getDiemDanhGiaTrungBinh()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                if (context instanceof AppCompatActivity) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor;
                    editor = sharedPreferences.edit();
                    editor.putInt("maPhim", search_movies.getMaPhim());
                    editor.apply();
                    notifyDataSetChanged();
                    Intent intent = new Intent(context, XemChiTietPhim.class);
                    context.startActivity(intent);                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchMoviesList.size();
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView posterMovie;
        TextView age, movieName, styleMovie, vote, purchases, comment;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            posterMovie = itemView.findViewById(R.id.poster_movie);
            age = itemView.findViewById(R.id.age); // Thay thế bằng ID thực tế
            movieName = itemView.findViewById(R.id.movie_name); // Thay thế bằng ID thực tế
            styleMovie = itemView.findViewById(R.id.movie_style); // Thay thế bằng ID thực tế
            vote = itemView.findViewById(R.id.vote); // Thay thế bằng ID thực tế
            purchases = itemView.findViewById(R.id.purchases); // Thay thế bằng ID thực tế
            comment = itemView.findViewById(R.id.comment); // Thay thế bằng ID thực tế

        }
    }
}
