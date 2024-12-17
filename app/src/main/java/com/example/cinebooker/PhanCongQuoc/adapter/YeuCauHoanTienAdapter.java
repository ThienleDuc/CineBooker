package com.example.cinebooker.PhanCongQuoc.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.entity.XemThongTinEntity;
import com.example.cinebooker.PhanCongQuoc.entity.YeuCauHoanTienEntity;
import com.example.cinebooker.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class YeuCauHoanTienAdapter extends RecyclerView.Adapter<YeuCauHoanTienAdapter.YeuCauHoanTienViewHolder> {

    private Context context;
    private List<YeuCauHoanTienEntity> yeuCauHoanTienList;
    private SharedPreferences.Editor editor;
    private int MaVe=-1;


    public YeuCauHoanTienAdapter(Context context, List<YeuCauHoanTienEntity> yeuCauHoanTienList) {
        this.context = context;
        this.yeuCauHoanTienList = yeuCauHoanTienList;
        notifyDataSetChanged();
    }
    public void SetData(List<YeuCauHoanTienEntity> movieList) {

        this.yeuCauHoanTienList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public YeuCauHoanTienViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoantien, parent, false);
        return new YeuCauHoanTienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YeuCauHoanTienViewHolder holder, int position) {
        YeuCauHoanTienEntity item = yeuCauHoanTienList.get(position);

        SharedPreferences
                sharedPreferences = holder.itemView.getContext().getSharedPreferences("QuocDepTrai", Context.MODE_PRIVATE);
        int mave = sharedPreferences.getInt("MaVe", -1);
        int maveiem = item.getMaVe();

        // Set data to views
        Picasso.get().load(item.getPosterTrHang())
                .placeholder(R.drawable.placeholder)  // Optional: Add a placeholder image
                .into(holder.posterTrHang);
        holder.namePhimTrHang.setText(item.getNamePhimTrHang());
        holder.dateTrHang.setText(item.getDateTrHang());
        holder.timebd.setText(item.getTime_batdau());
        holder.timekt.setText(item.getTime_ketthuc());
        holder.soLuongTrHang.setText(String.valueOf(item.getSoLuongTrHang()));
        Picasso.get().load(item.getIconTrHang())
                .placeholder(R.drawable.drawn_star)  // Optional: Add a placeholder image
                .into(holder.iconTrHang);
        holder.diaChiTrHang.setText(item.getDiaChiTrHang());
        holder.soTienHoanTrHang.setText(String.valueOf(item.getSoTienHoanTrHang()));
        holder.itemView.setOnClickListener(v -> {
            MaVe = maveiem;  // Cập nhật maTinhThanh từ item được chọn

            // Lưu giá trị maTinhThanh vào SharedPreferences
            editor = sharedPreferences.edit();
            editor.putInt("MaVe", mave);  // Lưu giá trị maTinhThanh
            editor.apply();  // Lưu thay đổi
        });

    }

    @Override
    public int getItemCount() {
        return yeuCauHoanTienList.size();
    }

    public static class YeuCauHoanTienViewHolder extends RecyclerView.ViewHolder {

        ImageView posterTrHang, iconTrHang;
        TextView namePhimTrHang, dateTrHang, timebd,timekt, soLuongTrHang, diaChiTrHang;
        TextView  soTienHoanTrHang, soDuTrHang;

        public YeuCauHoanTienViewHolder(View itemView) {
            super(itemView);

            // Initialize views
            posterTrHang = itemView.findViewById(R.id.poster_trhang);
            namePhimTrHang = itemView.findViewById(R.id.namephim_trhang);
            dateTrHang = itemView.findViewById(R.id.date_trhang);
            timebd = itemView.findViewById(R.id.time_batdau_trh);
            timekt = itemView.findViewById(R.id.time_ketthuc_trh);
            soLuongTrHang = itemView.findViewById(R.id.soluong_trhang);
            iconTrHang = itemView.findViewById(R.id.icon_trhang);
            diaChiTrHang = itemView.findViewById(R.id.diachi_trhang);
            soTienHoanTrHang = itemView.findViewById(R.id.sotienhoan_trhang);
        }
    }
}