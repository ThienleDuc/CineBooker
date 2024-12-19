
package com.example.cinebooker.PhanCongQuoc.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.activity.chiTietHuy;
import com.example.cinebooker.PhanCongQuoc.activity.xem_thong_tin_ve;
import com.example.cinebooker.PhanCongQuoc.activity.xuat_ve;
import com.example.cinebooker.PhanCongQuoc.entity.ticketkhuhoiMoviesEntity;
import com.example.cinebooker.PhanCongQuoc.fragment.ticket_khuhoiFragment;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ActivityOpen;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Ticket_khuhoiAdapter extends RecyclerView.Adapter<Ticket_khuhoiAdapter.TicketViewHolder> {
    private List<ticketkhuhoiMoviesEntity> ticketkhuhoiMoviesList;
    private int currentItemCount;
    private SharedPreferences.Editor editor;
    private int MaVe=-1;// Hiển thị ban đầu 10 mục

    public void SetData(List<ticketkhuhoiMoviesEntity> ticketkhuhoiMoviesList) {
        this.ticketkhuhoiMoviesList = ticketkhuhoiMoviesList;
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
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket_khuhoi, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        ticketkhuhoiMoviesEntity ticket = ticketkhuhoiMoviesList.get(position);

        // Gán giá trị từ ticketkhuhoiMoviesEntity
        holder.date_khuhoi.setText(ticket.getDate_khuhoi());
           Picasso.get().load(ticket.getPoster_khuhoi())
                .placeholder(R.drawable.placeholder)
                   .resize(800, 800) // Optional: Add a placeholder image
                .into(holder.poster_khuhoi);
        holder.age_khuhoi.setText(ticket.getAge_khuhoi() > 0 ? String.valueOf(ticket.getAge_khuhoi())+ "+" : "Tuổi không xác định");
        holder.name_khuhoi.setText(ticket.getName_khuhoi());
        holder.style_khuhoi.setText(ticket.getStyle_khuhoi());
        holder.soluong_khuhoi.setText(ticket.getSoluong_khuhoi() > 0 ? String.valueOf(ticket.getSoluong_khuhoi()) : "Tuổi không xác định");

        holder.diachi_khuhoi.setText(ticket.getDiachi_khuhoi());
        Picasso.get().load(ticket.getIcon_rap_khuhoi())
                .placeholder(R.drawable.drawn_star)  // Optional: Add a placeholder image
                .into(holder.icon_rap_khuhoi);
        // Thiết lập listener cho nút
        holder.btn_khuhoi.setOnClickListener(v -> {
            Context context = v.getContext();
            if (context instanceof AppCompatActivity) {
                int maVe = ticket.getMaVe();  // Lấy giá trị MaVe từ ticket
                if (maVe > 0) {  // Kiểm tra giá trị MaVe hợp lệ
                    // Lưu MaVe vào SharedPreferences
                    SharedPreferences sharedPreferences = context.getSharedPreferences("QuocDepTrai", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("MaVe", maVe);  // Lưu giá trị MaVe
                    editor.apply();  // Sử dụng commit để đảm bảo lưu ngay lập tức
                    Log.d("TicketAdapter", "Đã lưu MaVe vào SharedPreferences: " + maVe);

                    // Chuyển sang Activity chiTietHuy bằng Intent
                    Intent intent = new Intent(context, chiTietHuy.class);
                    intent.putExtra("MaVe", maVe);  // Truyền thêm MaVe qua Intent
                    context.startActivity(intent);
                } else {
                    Log.e("TicketAdapter", "MaVe không hợp lệ: " + maVe);
                }
            }
        });

    }

    @Override

    public int getItemCount() {
        return ticketkhuhoiMoviesList.size();  // Trả về toàn bộ số lượng item trong danh sách
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder {
        TextView date_khuhoi;
        ImageView poster_khuhoi; // Hình ảnh poster
        TextView age_khuhoi;
        TextView name_khuhoi;
        TextView style_khuhoi;
        TextView soluong_khuhoi;
        TextView diachi_khuhoi;
        ImageView icon_rap_khuhoi; // Icon của rạp
        Button btn_khuhoi;

        public TicketViewHolder(View itemView) {
            super(itemView);
            date_khuhoi = itemView.findViewById(R.id.date_khuhoi);
            poster_khuhoi = itemView.findViewById(R.id.poster_khuhoi);
            age_khuhoi = itemView.findViewById(R.id.age_khuhoi);
            name_khuhoi = itemView.findViewById(R.id.name_khuhoi);
            style_khuhoi = itemView.findViewById(R.id.style_khuhoi);
            soluong_khuhoi = itemView.findViewById(R.id.soluong_khuhoi);
            diachi_khuhoi = itemView.findViewById(R.id.diachi_khuhoi);
            icon_rap_khuhoi = itemView.findViewById(R.id.icon_CGV);
            btn_khuhoi = itemView.findViewById(R.id.btn_khuhoi);

        }
    }

}
