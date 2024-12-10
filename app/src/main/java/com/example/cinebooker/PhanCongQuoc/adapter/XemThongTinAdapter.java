
package com.example.cinebooker.PhanCongQuoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.entity.XemThongTinEntity;
import com.example.cinebooker.R;

import java.util.List;

public class XemThongTinAdapter extends RecyclerView.Adapter<XemThongTinAdapter.ViewHolder> {

    private Context context;
    private List<XemThongTinEntity> xemThongTinList;

    public void SetData(Context context, List<XemThongTinEntity> xemThongTinList) {
        this.context = context;
        this.xemThongTinList = xemThongTinList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the XML layout for the item
        View view = LayoutInflater.from(context).inflate(R.layout.activity_xem_thong_tin_ve, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        XemThongTinEntity item = xemThongTinList.get(position);

        // Set values in the UI elements
        holder.dateXemThongTin.setText(item.getDateXemThongTin());
        holder.date1XemThongTin.setText(item.getDate1XemThongTin());
        holder.posterXemThongTin.setImageResource(item.getPosterXemThongTin()); // Add image resource or URL loading logic
        holder.ageXemThongTin.setText(item.getAgeXemThongTin());
        holder.nameXemThongTin.setText(item.getNameXemThongTin());
        holder.styleXemThongTin.setText(item.getStyleXemThongTin());
        holder.soLuong.setText(item.getSoLuong());
        holder.iconRap.setImageResource(item.getIconRap()); // Set appropriate icon resource
        holder.diaChi.setText(item.getDiaChi());
        holder.timebd.setText(item.getTime_batdau());
        holder.timekt.setText(item.getTime_ketthuc());
        holder.dateXemThongTin1.setText(item.getDateXemThongTin1());
        holder.dinhDang.setText(item.getDinhDang());
        holder.gheNgoi.setText(item.getGheNgoi());
        holder.phongChieu.setText(item.getPhongChieu());
        holder.trangThai.setText(item.getTrangThai());
    }

    @Override
    public int getItemCount() {
        return xemThongTinList.size();
    }

    // ViewHolder to represent the layout of each item
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView posterXemThongTin, iconRap;
        TextView dateXemThongTin, date1XemThongTin, ageXemThongTin, nameXemThongTin, styleXemThongTin;
        TextView soLuong, diaChi, timebd,timekt, dateXemThongTin1, dinhDang, gheNgoi, phongChieu, trangThai;

        public ViewHolder(View itemView) {
            super(itemView);

            // Find views from the item layout
            posterXemThongTin = itemView.findViewById(R.id.poster_xemthongtin);
            iconRap = itemView.findViewById(R.id.iconrap_xemthongtin);
            dateXemThongTin = itemView.findViewById(R.id.date_xemthongtin);
            date1XemThongTin = itemView.findViewById(R.id.date_1_xemthongtin);
            ageXemThongTin = itemView.findViewById(R.id.age_xemthongtin);
            nameXemThongTin = itemView.findViewById(R.id.name_xemthongtin);
            styleXemThongTin = itemView.findViewById(R.id.style_xemthongtin);
            soLuong = itemView.findViewById(R.id.soluong_xemthongtin);
            diaChi = itemView.findViewById(R.id.diachi_xemthongtin);
            timebd = itemView.findViewById(R.id.time_batdau);
            timebd = itemView.findViewById(R.id.time_ketthuc);
            dateXemThongTin1 = itemView.findViewById(R.id.date_xemthongtin_1);
            dinhDang = itemView.findViewById(R.id.dinhdang_xemthongtin);
            gheNgoi = itemView.findViewById(R.id.ghe_xemthongtin);
            phongChieu = itemView.findViewById(R.id.phong_xemthongtin);
            trangThai = itemView.findViewById(R.id.trangthai_xemthongtin);
        }
    }
}
