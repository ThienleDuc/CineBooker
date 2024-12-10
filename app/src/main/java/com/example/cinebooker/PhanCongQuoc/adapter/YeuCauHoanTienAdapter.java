package com.example.cinebooker.PhanCongQuoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cinebooker.PhanCongQuoc.entity.YeuCauHoanTienEntity;
import com.example.cinebooker.R;
import java.util.List;

public class YeuCauHoanTienAdapter extends RecyclerView.Adapter<YeuCauHoanTienAdapter.YeuCauHoanTienViewHolder> {

    private Context context;
    private List<YeuCauHoanTienEntity> yeuCauHoanTienList;

    public void SetData(Context context, List<YeuCauHoanTienEntity> yeuCauHoanTienList) {
        this.context = context;
        this.yeuCauHoanTienList = yeuCauHoanTienList;
        notifyDataSetChanged();
    }

    @Override
    public YeuCauHoanTienViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_yeu_cau_hoan_tien, parent, false);
        return new YeuCauHoanTienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YeuCauHoanTienViewHolder holder, int position) {
        YeuCauHoanTienEntity item = yeuCauHoanTienList.get(position);

        // Set data to views
        holder.posterTrHang.setImageResource(item.getPosterTrHang());
        holder.namePhimTrHang.setText(item.getNamePhimTrHang());
        holder.dateTrHang.setText(item.getDateTrHang());
        holder.timebd.setText(item.getTime_batdau());
        holder.timekt.setText(item.getTime_ketthuc());
        holder.soLuongTrHang.setText(item.getSoLuongTrHang());
        holder.iconTrHang.setImageResource(item.getIconTrHang());
        holder.diaChiTrHang.setText(item.getDiaChiTrHang());
        holder.soTienHoanTrHang.setText(item.getSoTienHoanTrHang());
        holder.soDuTrHang.setText(item.getSoDuTrHang());
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
            timebd = itemView.findViewById(R.id.time_batdau);
            timebd = itemView.findViewById(R.id.time_ketthuc);
            soLuongTrHang = itemView.findViewById(R.id.soluong_trhang);
            iconTrHang = itemView.findViewById(R.id.icon_trhang);
            diaChiTrHang = itemView.findViewById(R.id.diachi_trhang);
            soTienHoanTrHang = itemView.findViewById(R.id.sotienhoan_trhang);
            soDuTrHang = itemView.findViewById(R.id.sodu_trhang);
        }
    }
}