package com.example.cinebooker.LeDucThien.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.entity.ent_NgayChieu;
import com.example.cinebooker.R;

import java.util.List;

public class LichChieuAdapter extends RecyclerView.Adapter<LichChieuAdapter.ViewHolder> {

    private List<ent_NgayChieu> ngayChieuList;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    // Thiết lập dữ liệu cho adapter
    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ent_NgayChieu> ngayChieuList) {
        this.ngayChieuList = ngayChieuList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ngaychieu, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (ngayChieuList == null || ngayChieuList.isEmpty()) return;

        ent_NgayChieu ngayChieu = ngayChieuList.get(position);
        holder.day.setText(ngayChieu.getNgayChieu());
        holder.dayName.setText(ngayChieu.getKieuNgay());

        // Lấy SharedPreferences để kiểm tra ngày đã chọn
        sharedPreferences = holder.itemView.getContext().getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);
        int maThoiGianChieu = ngayChieu.getMaThoiGianChieu();
        int _maThoiGianChieu = sharedPreferences.getInt("maThoiGianChieu", -1);

        // Kiểm tra và cập nhật giao diện dựa trên ngày đã chọn
        if (_maThoiGianChieu == maThoiGianChieu) {
            holder.containerImageView.setBackgroundResource(R.drawable.strock_1_pink_radius_10_white);
            holder.dayName.setBackgroundResource(R.color.pink);
            holder.dayName.setTextColor(holder.itemView.getContext().getColor(R.color.white));
            holder.day.setBackgroundResource(R.color.white);
            holder.day.setTextColor(holder.itemView.getContext().getColor(R.color.pink));
        } else {
            holder.day.setBackgroundResource(R.color.white);
            holder.dayName.setBackgroundResource(R.color.white);
            holder.day.setTextColor(holder.itemView.getContext().getColor(R.color.black));
            holder.dayName.setTextColor(holder.itemView.getContext().getColor(R.color.black));
            holder.containerImageView.setBackgroundResource(R.drawable.strock_1_pink_radius_10_white);
        }

        // Xử lý sự kiện click vào item
        holder.itemView.setOnClickListener(v -> {
            editor = sharedPreferences.edit();
            editor.putInt("maThoiGianChieu", ngayChieu.getMaThoiGianChieu());
            editor.apply();
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return ngayChieuList != null ? ngayChieuList.size() : 0;
    }

    // Lớp ViewHolder để ánh xạ các view trong item layout
    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout containerImageView;
        TextView dayName, day;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            containerImageView = itemView.findViewById(R.id.container_imageview);
            dayName = itemView.findViewById(R.id.dayname);
            day = itemView.findViewById(R.id.day);

        }
    }
}
