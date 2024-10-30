package com.example.cinebooker.PhanCongQuoc.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.entity.ticketdadungMoviesEntity;
import com.example.cinebooker.PhanCongQuoc.fragment.xemthongtinFragment;
import com.example.cinebooker.R;

import java.util.List;

public class Ticket_dadungAdapter extends RecyclerView.Adapter<Ticket_dadungAdapter.TicketViewHolder> {
        private List<ticketdadungMoviesEntity> ticketdadungMoviesList;
    private int currentItemCount; // Hiển thị ban đầu 10 mục

    public Ticket_dadungAdapter(List<ticketdadungMoviesEntity> ticketdadungMoviesList) {
        this.ticketdadungMoviesList = ticketdadungMoviesList;
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
    public Ticket_dadungAdapter.TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket_da_dung, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Ticket_dadungAdapter.TicketViewHolder holder, int position) {
        ticketdadungMoviesEntity ticket = ticketdadungMoviesList.get(position);

        holder.date_dadung.setText(ticket.getDate_dadung());
        holder.date_1_dadung.setText(ticket.getDate_1_dadung());
        holder.posterMovie_dadung.setImageResource(ticket.getPoster_dadung());
        holder.age_dadung.setText(ticket.getAge_dadung());
        holder.movieName_dadung.setText(ticket.getName_dadung());
        holder.styleMovie_dadung.setText(ticket.getStyle_dadung());
        // Thiết lập listener cho nút
        holder.btn_dadung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy Activity chứa RecyclerView
                AppCompatActivity activity = (AppCompatActivity) v.getContext();

                // Tạo instance của fragment mới
                xemthongtinFragment fragment = new xemthongtinFragment();



                // Thay thế fragment
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment) // `fragment_container` là ID của ViewGroup chứa fragment
                        .addToBackStack(null) // Thêm vào back stack nếu cần
                        .commit();
            }
        });


    }

    @Override
    public int getItemCount() {
        return Math.min(currentItemCount, ticketdadungMoviesList.size());
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder {
        ImageView posterMovie_dadung;
        TextView age_dadung, movieName_dadung, styleMovie_dadung, date_dadung,date_1_dadung;
        Button btn_dadung;
        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            posterMovie_dadung = itemView.findViewById(R.id.poster_dadung);
            age_dadung = itemView.findViewById(R.id.age_dadung);
            movieName_dadung = itemView.findViewById(R.id.name_dadung);
            styleMovie_dadung = itemView.findViewById(R.id.style_dadung);
            date_dadung = itemView.findViewById(R.id.date_dadung);
            date_1_dadung = itemView.findViewById(R.id.date_1_dadung);
            btn_dadung = itemView.findViewById(R.id.btn_dadung);
        }
    }
}
