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

import com.example.cinebooker.LeDucThien.activity.DanhSachDiaDiemRap;
import com.example.cinebooker.LeDucThien.entity.ent_TinhThanh;
import com.example.cinebooker.R;

import java.util.List;

public class TinhThanhAdapter extends RecyclerView.Adapter<TinhThanhAdapter.viewHolder> {
    private List<ent_TinhThanh> listTinhThanh;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    // Set dữ liệu cho adapter
    @SuppressLint("NotifyDataSetChanged")
    public void SetData(List<ent_TinhThanh> listTinhThanh) {
        this.listTinhThanh = listTinhThanh;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TinhThanhAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_tinh_thanh, parent, false);
        return new viewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull TinhThanhAdapter.viewHolder holder, int position) {
        ent_TinhThanh ent_list = listTinhThanh.get(position);
        holder.tenTinhThanh.setText(ent_list.getTenTinhThanh());
        int maTinhThanhItem = ent_list.getMaTinhThanh();
        sharedPreferences = holder.itemView.getContext().getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);
        int maTinhThanhFromPrefs = sharedPreferences.getInt("maTinhThanh", -1);

        // Đặt background cho item
        if (maTinhThanhFromPrefs == maTinhThanhItem) {
            holder.container_imageview.setBackgroundResource(R.drawable.strock_1_pink_radius_10_transparent);
            holder.tenTinhThanh.setTextColor(holder.itemView.getContext().getColor(R.color.primary_color));
            holder.tenTinhThanh.setTypeface(holder.tenTinhThanh.getTypeface(), Typeface.BOLD);
        } else {
            holder.container_imageview.setBackgroundResource(R.drawable.strock_1_white_radius_10_transparent);
            holder.tenTinhThanh.setTextColor(holder.itemView.getContext().getColor(android.R.color.darker_gray));
            holder.tenTinhThanh.setTypeface(holder.tenTinhThanh.getTypeface(), Typeface.NORMAL);
        }
        // Xử lý sự kiện click
        holder.itemView.setOnClickListener(v -> {
            editor = sharedPreferences.edit();
            // Lưu giá trị maTinhThanh vào SharedPreferences
            editor.putInt("maTinhThanh", maTinhThanhItem);
            editor.apply();
            notifyDataSetChanged();

            // Gọi hàm để thông báo cho fragment hoặc activity (nếu cần)
            if (holder.itemView.getContext() instanceof DanhSachDiaDiemRap) {
                ((DanhSachDiaDiemRap) holder.itemView.getContext()).dongActivity();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTinhThanh != null ? listTinhThanh.size() : 0;
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        LinearLayout container_imageview;
        TextView tenTinhThanh;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            container_imageview = itemView.findViewById(R.id.container_imageview);
            tenTinhThanh = itemView.findViewById(R.id.ten_tinh_thanh);
        }
    }
}