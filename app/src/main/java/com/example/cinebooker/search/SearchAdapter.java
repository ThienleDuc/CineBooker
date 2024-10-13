package com.example.cinebooker.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.Search;
import com.example.cinebooker.generalMethod.NumberFormatter;

import com.example.cinebooker.R;

import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private List<search_movies> searchMoviesList;
    private int currentItemCount; // Hiển thị ban đầu 10 mục

    public SearchAdapter(List<search_movies> searchMoviesList) {
        this.searchMoviesList = searchMoviesList;
        this.currentItemCount = 10;
    }

    public int getCurrentItemCount() {
        return currentItemCount;
    }

    public void setCurrentItemCount(int currentItemCount) {
        this.currentItemCount = currentItemCount;
    }

    public void updateItemCount(int count) {
        this.currentItemCount = count;
        notifyDataSetChanged(); // Cập nhật lại giao diện
    }


    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_search, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {
        search_movies search_movies=  searchMoviesList.get(position);

        holder.posterMovie.setImageResource(search_movies.getPosster_movies());
        holder.age.setText(search_movies.getAge());
        holder.movieName.setText(search_movies.getMovieName());
        holder.styleMovie.setText(search_movies.getStyleMovie());

        holder.vote.setText(NumberFormatter.formatNumber(search_movies.getVote().intValue()));
        holder.purchases.setText(NumberFormatter.formatNumber(search_movies.getPurchases().intValue()));
        holder.comment.setText(NumberFormatter.formatNumber(search_movies.getComment().intValue()));


    }

    @Override
    public int getItemCount() {
        return Math.min(currentItemCount, searchMoviesList.size());
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
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
