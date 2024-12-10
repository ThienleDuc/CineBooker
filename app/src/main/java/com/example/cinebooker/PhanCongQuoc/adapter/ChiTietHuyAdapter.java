
package com.example.cinebooker.PhanCongQuoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.entity.ChiTietHuyEntity;
import com.example.cinebooker.R;

import java.util.List;

public class ChiTietHuyAdapter extends RecyclerView.Adapter<ChiTietHuyAdapter.ViewHolder> {

    private Context context;
    private List<ChiTietHuyEntity> chiTietHuyList;

    public void SetData(Context context, List<ChiTietHuyEntity> chiTietHuyList) {
        this.context = context;
        this.chiTietHuyList = chiTietHuyList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the XML layout for the item
        View view = LayoutInflater.from(context).inflate(R.layout.activity_chi_tiet_huy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChiTietHuyEntity item = chiTietHuyList.get(position);

        // Set values in the UI elements
        holder.dateChitiethuy.setText(item.getDateChitietHuy());
        holder.dateChitiethuy1.setText(item.getDateChitietHuy1());
        holder.posterChitiethuy.setImageResource(item.getPosterChitietHuy()); // Add image resource or URL loading logic
        holder.ageChitiethuy.setText(item.getAgeChitietHuy());
        holder.nameChitiethuy.setText(item.getNameChitietHuy());
        holder.styleChitiethuy.setText(item.getStyleChitietHuy());
        holder.soLuongChitiethuy.setText(item.getSoLuongChitietHuy());
        holder.iconRapChitiethuy.setImageResource(item.getIconRapChitietHuy()); // Set appropriate icon resource
        holder.diaChiChitiethuy.setText(item.getDiaChiChitietHuy());
        holder.timebd.setText(item.getTime_batdau());
        holder.timekt.setText(item.getTime_ketthuc());
        holder.dinhDangXuatPhim.setText(item.getDinhDangXuatPhim());
        holder.gheChitiethuy.setText(item.getGheChitietHuy());
        holder.phongChieuChitiethuy.setText(item.getPhongChitietHuy());
        holder.trangThaiChitiethuy.setText(item.getTrangThaiChitietHuy());
    }

    @Override
    public int getItemCount() {
        return chiTietHuyList.size();
    }

    // ViewHolder to represent the layout of each item
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView posterChitiethuy, iconRapChitiethuy;
        TextView dateChitiethuy, dateChitiethuy1, ageChitiethuy, nameChitiethuy, styleChitiethuy;
        TextView soLuongChitiethuy, diaChiChitiethuy, timebd,timekt, dinhDangXuatPhim, gheChitiethuy;
        TextView phongChieuChitiethuy, trangThaiChitiethuy;

        public ViewHolder(View itemView) {
            super(itemView);

            // Find views from the item layout
            posterChitiethuy = itemView.findViewById(R.id.poster_chitiethuy);
            iconRapChitiethuy = itemView.findViewById(R.id.iconrap_chitiethuy);
            dateChitiethuy = itemView.findViewById(R.id.date_chitiethuy);
            dateChitiethuy1 = itemView.findViewById(R.id.date_chitiethuy1);
            ageChitiethuy = itemView.findViewById(R.id.age_chitiethuy);
            nameChitiethuy = itemView.findViewById(R.id.name_chitiethuy);
            styleChitiethuy = itemView.findViewById(R.id.style_chitiethuy);
            soLuongChitiethuy = itemView.findViewById(R.id.soluong_chitiethuy);
            diaChiChitiethuy = itemView.findViewById(R.id.diachi_chitiethuy);
            timebd = itemView.findViewById(R.id.time_batdau);
            timebd = itemView.findViewById(R.id.time_ketthuc);
            dinhDangXuatPhim = itemView.findViewById(R.id.dinhdang_xuatphim);
            gheChitiethuy = itemView.findViewById(R.id.ghe_chitiethuy);
            phongChieuChitiethuy = itemView.findViewById(R.id.phong_chitiethuy);
            trangThaiChitiethuy = itemView.findViewById(R.id.trangthai_chitiethuy);
        }
    }
}
