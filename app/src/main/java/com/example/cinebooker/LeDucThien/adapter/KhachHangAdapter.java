package com.example.cinebooker.LeDucThien.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.entity.ent_KhachHang;
import com.example.cinebooker.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHolder> {

    private List<ent_KhachHang> khachHangList;

    public KhachHangAdapter() {
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ent_KhachHang> khachHangList) {
        this.khachHangList = khachHangList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khach_hang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ent_KhachHang khachHang = khachHangList.get(position);
        // Set text fields
        holder.lopHocPhan.setText(khachHang.getLopHocPhan());
        holder.tenKhachHang.setText(khachHang.getTenKhachHang());
        holder.maSinhVien.setText(khachHang.getMaSinhVien());

        String imageName = khachHang.getAnhKhachHang();
        Context context = holder.itemView.getContext();
        @SuppressLint("DiscouragedApi")
        int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId)
                .placeholder(R.drawable.avatar)
                .into(holder.anhKhachHang);
    }

    @Override
    public int getItemCount() {
        return khachHangList != null ? khachHangList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView anhKhachHang;
        TextView lopHocPhan, tenKhachHang, maSinhVien;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize the views
            anhKhachHang = itemView.findViewById(R.id.anh_khach_hang);
            lopHocPhan = itemView.findViewById(R.id.lop_hoc_phan);
            tenKhachHang = itemView.findViewById(R.id.ten_khach_hang);
            maSinhVien = itemView.findViewById(R.id.ma_sinh_vien);
        }
    }
}
