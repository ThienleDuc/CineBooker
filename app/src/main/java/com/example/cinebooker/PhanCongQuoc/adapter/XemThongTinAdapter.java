package com.example.cinebooker.PhanCongQuoc.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.entity.XemThongTinEntity;
import com.example.cinebooker.PhanCongQuoc.entity.xuatveEntity;
import com.example.cinebooker.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class XemThongTinAdapter extends RecyclerView.Adapter<XemThongTinAdapter.ViewHolder> {

    private Context context;
    private List<XemThongTinEntity> xemThongTinList;
    private SharedPreferences.Editor editor;
    private int MaVe=-1;

    public XemThongTinAdapter (Context context, List<XemThongTinEntity> xemThongTinList) {
        this.context = context;
        this.xemThongTinList = xemThongTinList;
        notifyDataSetChanged();
    }
    public void SetData(List<XemThongTinEntity> movieList) {

        this.xemThongTinList = movieList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the XML layout for the item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xemthongtin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        XemThongTinEntity item = xemThongTinList.get(position);
        SharedPreferences
                sharedPreferences = holder.itemView.getContext().getSharedPreferences("QuocDepTrai", Context.MODE_PRIVATE);
        int mave = sharedPreferences.getInt("MaVe", -1);
        int maveiem = item.getMaVe();
        // Set values in the UI elements
        holder.dateXemThongTin.setText(item.getDateXemThongTin());

        String imageName = item.getPosterXemThongTin();
        Context context = holder.itemView.getContext();
        @SuppressLint("DiscouragedApi") int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId)
                .resize(800, 800)// Load resource ID
                .into(holder.posterXemThongTin);


        String imageName1 = item.getIconRap();

        @SuppressLint("DiscouragedApi") int resourceId1 = context.getResources().getIdentifier(imageName1, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId1)
                .resize(800, 800)// Load resource ID
                .into(holder.iconRap);



        holder.ageXemThongTin.setText(String.valueOf(item.getAgeXemThongTin())+"+");
        holder.nameXemThongTin.setText(item.getNameXemThongTin());
        holder.styleXemThongTin.setText(item.getStyleXemThongTin());
        holder.soLuong.setText(String.valueOf(item.getSoLuong()));

        holder.diaChi.setText(item.getDiaChi());
        holder.timebd.setText(item.getTime_batdau());
        holder.timekt.setText(item.getTime_ketthuc());
        holder.dateXemThongTin1.setText(item.getDateXemThongTin1());
        holder.dinhDang.setText(item.getDinhDang());
        holder.gheNgoi.setText(String.valueOf(item.getGheNgoi()));
        holder.phongChieu.setText(String.valueOf(item.getPhongChieu()));
        holder.trangThai.setText(item.getTrangThai());
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
        return xemThongTinList.size();
    }



    // ViewHolder to represent the layout of each item
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView posterXemThongTin, iconRap;
        TextView dateXemThongTin, ageXemThongTin, nameXemThongTin, styleXemThongTin;
        TextView soLuong, diaChi, timebd,timekt, dateXemThongTin1, dinhDang, gheNgoi, phongChieu, trangThai;

        public ViewHolder(View itemView) {
            super(itemView);

            // Find views from the item layout
            posterXemThongTin = itemView.findViewById(R.id.poster_xemthongtin);
            iconRap = itemView.findViewById(R.id.iconrap_xemthongtin);
            dateXemThongTin = itemView.findViewById(R.id.date_xemthongtin);
            ageXemThongTin = itemView.findViewById(R.id.age_xemthongtin);
            nameXemThongTin = itemView.findViewById(R.id.name_xemthongtin);
            styleXemThongTin = itemView.findViewById(R.id.style_xemthongtin);
            soLuong = itemView.findViewById(R.id.soluong_xemthongtin);
            diaChi = itemView.findViewById(R.id.diachi_xemthongtin);
            timebd = itemView.findViewById(R.id.time_batdau_xemthongtin);
            timekt = itemView.findViewById(R.id.time_ketthuc_xemthongtin);
            dateXemThongTin1 = itemView.findViewById(R.id.date_xemthongtin_1);
            dinhDang = itemView.findViewById(R.id.dinhdang_xemthongtin);
            gheNgoi = itemView.findViewById(R.id.ghe_xemthongtin);
            phongChieu = itemView.findViewById(R.id.phong_xemthongtin);
            trangThai = itemView.findViewById(R.id.trangthai_xemthongtin);
        }
    }
}