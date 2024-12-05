package com.example.cinebooker.PhanCongQuoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cinebooker.PhanCongQuoc.entity.xuatveEntity;
import com.example.cinebooker.R;

import java.util.List;

public class XuatVeAdapter extends RecyclerView.Adapter<XuatVeAdapter.XuatVeViewHolder> {

    private Context context;
    private List<xuatveEntity> xuatVeList;

    public XuatVeAdapter(Context context, List<xuatveEntity> xuatVeList) {
        this.context = context;
        this.xuatVeList = xuatVeList;
    }

    @Override
    public XuatVeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_xuat_ve, parent, false);
        return new XuatVeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(XuatVeViewHolder holder, int position) {
        xuatveEntity item = xuatVeList.get(position);

        // Set data to views
        holder.qrXuatVe.setImageResource(item.getQrXuatVe());
        holder.dateXuatVe1.setText(item.getDateXuatVe1());
        holder.posterXuatVe2.setImageResource(item.getPosterXuatVe2());
        holder.ageXuatVe.setText(item.getAgeXuatVe());
        holder.nameXuatVe.setText(item.getNameXuatVe());
        holder.styleXuatVe.setText(item.getStyleXuatVe());
        holder.soLuongXuatVe.setText(item.getSoLuongXuatVe());
        holder.iconRapXuatVe.setImageResource(item.getIconRapXuatVe());
        holder.diaChiXuatVe.setText(item.getDiaChiXuatVe());
        holder.timebd.setText(item.getTime_batdau());
        holder.timekt.setText(item.getTime_ketthuc());
        holder.dateXuatVe2.setText(item.getDateXuatVe2());
        holder.dinhDangXuatPhim.setText(item.getDinhDangXuatPhim());
        holder.gheXuatVe.setText(item.getGheXuatVe());
        holder.phongXuatVe.setText(item.getPhongXuatVe());
        holder.trangThaiXuatVe.setText(item.getTrangThaiXuatVe());
    }

    @Override
    public int getItemCount() {
        return xuatVeList.size();
    }

    public static class XuatVeViewHolder extends RecyclerView.ViewHolder {

        ImageView qrXuatVe, posterXuatVe1, posterXuatVe2, iconRapXuatVe;
        TextView dateXuatVe1, ageXuatVe, nameXuatVe, styleXuatVe, soLuongXuatVe, diaChiXuatVe;
        TextView  timebd,timekt, dateXuatVe2, dinhDangXuatPhim, gheXuatVe, phongXuatVe, trangThaiXuatVe;

        public XuatVeViewHolder(View itemView) {
            super(itemView);

            qrXuatVe = itemView.findViewById(R.id.qr_xuatve);
            dateXuatVe1 = itemView.findViewById(R.id.date_xuatve_1);
            posterXuatVe2 = itemView.findViewById(R.id.poster_xuatve_2);
            ageXuatVe = itemView.findViewById(R.id.age_xuatve);
            nameXuatVe = itemView.findViewById(R.id.name_xuatve);
            styleXuatVe = itemView.findViewById(R.id.style_xuatve);
            soLuongXuatVe = itemView.findViewById(R.id.soluong_xuatve);
            iconRapXuatVe = itemView.findViewById(R.id.icon_rap_xuatve);
            diaChiXuatVe = itemView.findViewById(R.id.diachi_xuatve);
            timebd = itemView.findViewById(R.id.time_batdau);
            timebd = itemView.findViewById(R.id.time_ketthuc);
            dateXuatVe2 = itemView.findViewById(R.id.date_xuatve_2);
            dinhDangXuatPhim = itemView.findViewById(R.id.dinhdang_xuatphim);
            gheXuatVe = itemView.findViewById(R.id.ghe_xuatve);
            phongXuatVe = itemView.findViewById(R.id.phong_xuatve);
            trangThaiXuatVe = itemView.findViewById(R.id.trangthai_xuatve);
        }
    }
}
