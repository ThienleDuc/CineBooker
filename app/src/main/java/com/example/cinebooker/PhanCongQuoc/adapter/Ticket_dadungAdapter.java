
package com.example.cinebooker.PhanCongQuoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.activity.xem_thong_tin_ve;
import com.example.cinebooker.PhanCongQuoc.activity.xuat_ve;
import com.example.cinebooker.PhanCongQuoc.activity.yeu_cau_hoan_tien;
import com.example.cinebooker.PhanCongQuoc.entity.ticketdadungMoviesEntity;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ActivityOpen;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Ticket_dadungAdapter extends RecyclerView.Adapter<Ticket_dadungAdapter.TicketViewHolder> {
    private List<ticketdadungMoviesEntity> ticketdadungMoviesList;
    private int currentItemCount; // Hiển thị ban đầu 10 mục

    public void SetData(List<ticketdadungMoviesEntity> ticketdadungMoviesList) {
        this.ticketdadungMoviesList = ticketdadungMoviesList;
        this.currentItemCount = 10;
        notifyDataSetChanged();
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

        holder.date_dadung.setText(ticket.getDate_dadung() != null ? ticket.getDate_dadung() : "Ngày không xác định");
         Picasso.get().load(ticket.getPoster_dadung())
                .placeholder(R.drawable.placeholder)  // Optional: Add a placeholder image
                .into(holder.posterMovie_dadung);
        holder.age_dadung.setText(ticket.getAge_dadung() > 0 ? String.valueOf(ticket.getAge_dadung()) : "Tuổi không xác định");
        holder.movieName_dadung.setText(ticket.getName_dadung() != null ? ticket.getName_dadung() : "Tên phim không xác định");
        holder.styleMovie_dadung.setText(ticket.getStyle_dadung() != null ? ticket.getStyle_dadung() : "Thể loại không xác định");
        holder.soluong_dadung.setText(ticket.getSoluong_dadung() > 0 ? String.valueOf(ticket.getSoluong_dadung()) : "0");
        Picasso.get().load(ticket.getAnhrap_dadung())
                .placeholder(R.drawable.drawn_star)  // Optional: Add a placeholder image
                .into(holder.anhrap);
        holder.diachi.setText(ticket.getDiachi_dadung() != null ? ticket.getDiachi_dadung() : "Địa chỉ không xác định");

        holder.btn_dadung.setOnClickListener(v -> {
            Context context = v.getContext();
            if (context instanceof AppCompatActivity) {
                ActivityOpen.openActivityOnClick((AppCompatActivity) context, xem_thong_tin_ve.class, R.id.btn_dadung);
            }
        });
    }

        @Override
    public int getItemCount() {
        return Math.min(currentItemCount, ticketdadungMoviesList.size());
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder {
        ImageView posterMovie_dadung,anhrap;
        TextView age_dadung, movieName_dadung, styleMovie_dadung, date_dadung,soluong_dadung,diachi;
        Button btn_dadung;
        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            date_dadung = itemView.findViewById(R.id.date_dadung);

            posterMovie_dadung = itemView.findViewById(R.id.poster_dadung);
            age_dadung = itemView.findViewById(R.id.age_dadung);
            movieName_dadung = itemView.findViewById(R.id.name_dadung);
            styleMovie_dadung = itemView.findViewById(R.id.style_dadung);
            soluong_dadung = itemView.findViewById(R.id.soluong_dadung);
            anhrap = itemView.findViewById(R.id.icon_CGV);
            diachi = itemView.findViewById(R.id.diachi_dadung);
            btn_dadung = itemView.findViewById(R.id.btn_dadung);
        }
    }
}
