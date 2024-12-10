package com.example.cinebooker.LeDucThien.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cinebooker.LeDucThien.entity.rapChieuEntity;
import com.example.cinebooker.R;

import java.util.List;

public class rapChieuAdapter extends RecyclerView.Adapter<rapChieuAdapter.viewHolder> {
    private int selectedPosition = 0;
    private List<rapChieuEntity> rapChieulist;

    public rapChieuAdapter() {

    }

    public rapChieuAdapter(List<rapChieuEntity> dangChieulist) {
        this.rapChieulist = dangChieulist;
    }

    @NonNull
    @Override
    public rapChieuAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thoi_gian_chieu_theo_ngay, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rapChieuAdapter.viewHolder holder, int position) {
        rapChieuEntity rapChieu = rapChieulist.get(position);

        holder.cinemaLogo.setImageResource(rapChieu.getCinemalog0());
        holder.name.setText(rapChieu.getName());

        // Đặt background cho item
        if (position == selectedPosition) {
            holder.container_imageview.setBackgroundResource(R.drawable.strock_1_pink_radius_10_white); // Nền khi được chọn
            holder.name.setTextColor(holder.itemView.getContext().getColor(R.color.primary_color));
        } else {
            holder.container_imageview.setBackgroundResource(R.drawable.strock_1_darkergray_radius_10_white); // Nền khi không được chọn
            holder.name.setTextColor(holder.itemView.getContext().getColor(android.R.color.darker_gray));
        }

        // Xử lý sự kiện click
        holder.itemView.setOnClickListener(v -> {
            selectedPosition = position; // Cập nhật vị trí được chọn
            notifyDataSetChanged(); // Refresh toàn bộ adapter để áp dụng thay đổi
        });
    }

    @Override
    public int getItemCount() {
        return rapChieulist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        LinearLayout container_imageview;
        ImageView cinemaLogo;
        TextView name;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            container_imageview = itemView.findViewById(R.id.container_imageview);
            cinemaLogo = itemView.findViewById(R.id.cinema_logo);
            name = itemView.findViewById(R.id.cinema_name);
        }
    }
}
