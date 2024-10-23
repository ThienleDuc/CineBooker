package com.example.cinebooker.PhanCongQuoc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.entity.ticketchuadungMoviesEntity;
import com.example.cinebooker.PhanCongQuoc.generalMethod.NumberFormatter;
import com.example.cinebooker.R;

import java.util.List;

public class Ticket_chuadungAdapter extends RecyclerView.Adapter<Ticket_chuadungAdapter.TicketViewHolder> {
        private List<ticketchuadungMoviesEntity> ticketChuadungMoviesList;
    private int currentItemCount; // Hiển thị ban đầu 10 mục

    public Ticket_chuadungAdapter(List<ticketchuadungMoviesEntity> ticketChuadungMoviesList) {
        this.ticketChuadungMoviesList = ticketChuadungMoviesList;
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
    public Ticket_chuadungAdapter.TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket_chua_dung, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Ticket_chuadungAdapter.TicketViewHolder holder, int position) {
        ticketchuadungMoviesEntity ticket = ticketChuadungMoviesList.get(position);

        holder.date_chuadung.setText(ticket.getDate_chuadung());
        holder.posterMovie.setImageResource(ticket.getPoster_chuadung());
        holder.age.setText(ticket.getAge_chuadung());
        holder.movieName.setText(ticket.getName_chuadung());
        holder.styleMovie.setText(ticket.getStyle_chuadung());

    }

    @Override
    public int getItemCount() {
        return Math.min(currentItemCount, ticketChuadungMoviesList.size());
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder {
        ImageView posterMovie;
        TextView age, movieName, styleMovie, date_chuadung;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            posterMovie = itemView.findViewById(R.id.poster_chuadung);
            age = itemView.findViewById(R.id.age_chuadung);
            movieName = itemView.findViewById(R.id.name_chuadung);
            styleMovie = itemView.findViewById(R.id.style_chuadung);
            date_chuadung = itemView.findViewById(R.id.date_chuadung);
        }
    }
}
