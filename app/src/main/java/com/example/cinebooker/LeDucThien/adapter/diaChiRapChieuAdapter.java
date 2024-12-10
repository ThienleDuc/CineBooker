package com.example.cinebooker.LeDucThien.adapter;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.entity.diaChiRapChieuEntity;
import com.example.cinebooker.R;

import java.util.List;

public class diaChiRapChieuAdapter extends RecyclerView.Adapter<diaChiRapChieuAdapter.viewHolder> {
    private int selectedPosition = 0;
    private List<diaChiRapChieuEntity> rapChieulist;

    public diaChiRapChieuAdapter() {

    }

    public diaChiRapChieuAdapter(List<diaChiRapChieuEntity> rapChieulist) {
        this.rapChieulist = rapChieulist;
    }

    @NonNull
    @Override
    public diaChiRapChieuAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diachirapchieu, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull diaChiRapChieuAdapter.viewHolder holder, int position) {
        diaChiRapChieuEntity rapChieu = rapChieulist.get(position);

        holder.cinemaLogor.setImageResource(rapChieu.getCinemalog0());

        holder.location.setText(rapChieu.getName());

        // Đặt background cho item
        if (position == selectedPosition) {
            holder.container_imageview.setBackgroundResource(R.drawable.strock_1_pink_radius_10_transparent); // Nền khi được chọn
            holder.location.setTextColor(holder.itemView.getContext().getColor(R.color.primary_color));
            holder.location.setTypeface(holder.location.getTypeface(), Typeface.BOLD);
        } else {
            holder.container_imageview.setBackgroundResource(R.drawable.strock_1_darkergray_radius_10_transparent); // Nền khi không được chọn
            holder.location.setTextColor(holder.itemView.getContext().getColor(R.color.black));
            holder.location.setTypeface(holder.location.getTypeface(), Typeface.NORMAL);

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
        ImageView cinemaLogor;
        TextView location;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            container_imageview = itemView.findViewById(R.id.container_imageview);
            cinemaLogor = itemView.findViewById(R.id.cinema_logo);
            location = itemView.findViewById(R.id.cinema_location);
        }
    }
}
