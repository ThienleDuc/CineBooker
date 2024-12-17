
package com.example.cinebooker.PhanCongQuoc.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.activity.xuat_ve;
import com.example.cinebooker.PhanCongQuoc.activity.yeu_cau_hoan_tien;
import com.example.cinebooker.PhanCongQuoc.entity.ticketchuadungMoviesEntity;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ActivityOpen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Ticket_chuadungAdapter extends RecyclerView.Adapter<Ticket_chuadungAdapter.TicketViewHolder> {
    private List<ticketchuadungMoviesEntity> ticketChuadungMoviesList;
    private int currentItemCount;
    private SharedPreferences.Editor editor;
    private int MaVe=-1;
 private    SharedPreferences sharedPreferences;

    public void SetData(List<ticketchuadungMoviesEntity> ticketChuadungMoviesList) {
        if (this.ticketChuadungMoviesList == null) {
            this.ticketChuadungMoviesList = new ArrayList<>();
        }

        int startPosition = this.ticketChuadungMoviesList.size();
        this.ticketChuadungMoviesList.addAll(ticketChuadungMoviesList);

        // Thay vì notifyDataSetChanged, sử dụng notifyItemRangeInserted
        notifyItemRangeInserted(startPosition, ticketChuadungMoviesList.size() - startPosition);
    }


    public Ticket_chuadungAdapter() {

    }



    public int getCurrentItemCount() {
        return currentItemCount;
    }

    public void setCurrentItemCount(int currentItemCount) {
        this.currentItemCount = currentItemCount;
    }

    public void updateItemCount(int count) {
        this.currentItemCount = count;
        notifyDataSetChanged();
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
        holder.date_chuadung.setText(ticket.getDate_chuadung() != null ? ticket.getDate_chuadung() : "Ngày không xác định");
        Picasso.get().load(ticket.getPoster_chuadung())
                .placeholder(R.drawable.drawn_star)  // Optional: Add a placeholder image
                .into(holder.posterMovie_chuadung);// Optional: Add a placeholder image


        holder.age_chuadung.setText(ticket.getAge_chuadung() > 0
                ? String.valueOf(ticket.getAge_chuadung()) + "+"
                : "Tuổi không xác định");

        holder.movieName_chuadung.setText(ticket.getName_chuadung() != null ? ticket.getName_chuadung() : "Tên phim không xác định");
        holder.styleMovie_chuadung.setText(ticket.getStyle_chuadung() != null ? ticket.getStyle_chuadung() : "Thể loại không xác định");
        holder.soluong_chuadung.setText(ticket.getSoluong_chuadung() > 0 ? String.valueOf(ticket.getSoluong_chuadung()) : "0");
        Picasso.get().load(ticket.getAnhrap())
                .placeholder(R.drawable.drawn_star)  // Optional: Add a placeholder image
                .into(holder.anhrap);
        holder.diachi.setText(ticket.getDiachi_chuadung() != null ? ticket.getDiachi_chuadung() : "Địa chỉ không xác định");

// Xử lý sự kiện cho nút
        holder.btn_chuadung.setOnClickListener(v -> {
            Context context = v.getContext();
            if (context instanceof AppCompatActivity) {
                // Lưu MaVe vào SharedPreferences
                SharedPreferences sharedPreferences = context.getSharedPreferences("QuocDepTrai", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("MaVe", ticket.getMaVe());
                editor.apply();
                // Mở Activity xuat_ve bằng Intent
                Intent intent = new Intent(context, yeu_cau_hoan_tien.class);
                context.startActivity(intent);

            }
        });

        holder.btn_chuadung1.setOnClickListener(v -> {
            Context context = v.getContext();
            if (context instanceof AppCompatActivity) {
                // Lưu MaVe vào SharedPreferences
                SharedPreferences sharedPreferences = context.getSharedPreferences("QuocDepTrai", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("MaVe", ticket.getMaVe());
                editor.apply();
                // Mở Activity xuat_ve bằng Intent
                Intent intent = new Intent(context, xuat_ve.class);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return ticketChuadungMoviesList.size();  // Trả về toàn bộ số lượng item trong danh sách
    }


    public class TicketViewHolder extends RecyclerView.ViewHolder {
        ImageView posterMovie_chuadung,anhrap;
        TextView age_chuadung, movieName_chuadung, styleMovie_chuadung, date_chuadung,soluong_chuadung,diachi;
        Button btn_chuadung, btn_chuadung1;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            posterMovie_chuadung = itemView.findViewById(R.id.poster_chuadung);
            age_chuadung = itemView.findViewById(R.id.age_chuadung);
            movieName_chuadung = itemView.findViewById(R.id.name_chuadung);
            styleMovie_chuadung = itemView.findViewById(R.id.style_chuadung);
            date_chuadung = itemView.findViewById(R.id.date_chuadung);
            soluong_chuadung = itemView.findViewById(R.id.soluong_chuadung);
            anhrap = itemView.findViewById(R.id.icon_CGV);
            diachi = itemView.findViewById(R.id.diachi_chuadung);
            btn_chuadung = itemView.findViewById(R.id.btn_chuadung);
            btn_chuadung1 = itemView.findViewById(R.id.btn_chuadung1);
        }
    }
}
