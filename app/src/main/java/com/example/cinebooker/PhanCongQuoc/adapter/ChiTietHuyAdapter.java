package com.example.cinebooker.PhanCongQuoc.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.entity.ChiTietHuyEntity;
import com.example.cinebooker.PhanCongQuoc.entity.XemThongTinEntity;
import com.example.cinebooker.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChiTietHuyAdapter extends RecyclerView.Adapter<ChiTietHuyAdapter.ViewHolder> {

    private Context context;
    private List<ChiTietHuyEntity> chiTietHuyList;
    private SharedPreferences.Editor editor;
    private int MaVe=-1;

    public ChiTietHuyAdapter (Context context, List<ChiTietHuyEntity> chiTietHuyList) {
        this.context = context;
        this.chiTietHuyList = chiTietHuyList;
        notifyDataSetChanged();
    }
    public void SetData(List<ChiTietHuyEntity> movieList) {

        this.chiTietHuyList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitiethuy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChiTietHuyAdapter.ViewHolder holder, int position) {
        ChiTietHuyEntity item = chiTietHuyList.get(position);

        SharedPreferences
                sharedPreferences = holder.itemView.getContext().getSharedPreferences("QuocDepTrai", Context.MODE_PRIVATE);
        int mave = sharedPreferences.getInt("MaVe", -1);
        int maveiem = item.getMaVe();

        holder.dateChitiethuy.setText(item.getDateChitietHuy());
        Picasso.get().load(item.getPosterChitietHuy())
                .placeholder(R.drawable.placeholder)
                .resize(800, 800) // Optional: Add a placeholder image
                .into(holder.posterChitiethuy);
        holder.ageChitiethuy.setText(String.valueOf(item.getAgeChitietHuy())+"+");
        holder.nameChitiethuy.setText(item.getNameChitietHuy());
        holder.styleChitiethuy.setText(item.getStyleChitietHuy());
        holder.soLuongChitiethuy.setText(String.valueOf(item.getSoLuongChitietHuy()));
        Picasso.get().load(item.getIconRapChitietHuy())
                .placeholder(R.drawable.drawn_star)  // Optional: Add a placeholder image
                .into(holder.iconRapChitiethuy);
        holder.diaChiChitiethuy.setText(item.getDiaChiChitietHuy());
        holder.timebd.setText(item.getTime_batdau());
        holder.timekt.setText(item.getTime_ketthuc());
        holder.dateChitiethuy1.setText(item.getDateChitietHuy1());
        holder.dinhDangXuatPhim.setText(item.getDinhDangXuatPhim());
        holder.gheChitiethuy.setText(String.valueOf(item.getGheChitietHuy()));
        holder.phongChieuChitiethuy.setText(String.valueOf(item.getPhongChitietHuy()));
        holder.trangThaiChitiethuy.setText(item.getTrangThaiChitietHuy());
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

            posterChitiethuy = itemView.findViewById(R.id.poster_chitiethuy);
            iconRapChitiethuy = itemView.findViewById(R.id.iconrap_chitiethuy);
            dateChitiethuy = itemView.findViewById(R.id.date_chitiethuy);
            ageChitiethuy = itemView.findViewById(R.id.age_chitiethuy);
            nameChitiethuy = itemView.findViewById(R.id.name_chitiethuy);
            styleChitiethuy = itemView.findViewById(R.id.style_chitiethuy);
            soLuongChitiethuy = itemView.findViewById(R.id.soluong_chitiethuy);
            diaChiChitiethuy = itemView.findViewById(R.id.diachi_chitiethuy);
            timebd = itemView.findViewById(R.id.time_batdau_chitiethuy);
            timekt = itemView.findViewById(R.id.time_ketthuc_chitiethuy);
            dateChitiethuy1 = itemView.findViewById(R.id.date_chitiethuy_1);
            dinhDangXuatPhim = itemView.findViewById(R.id.dinhdang_chitiethuy);
            gheChitiethuy = itemView.findViewById(R.id.ghe_chitiethuy);
            phongChieuChitiethuy = itemView.findViewById(R.id.phong_chitiethuy);
            trangThaiChitiethuy = itemView.findViewById(R.id.trangthai_chitiethuy);
        }
    }
}
