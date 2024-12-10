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
import com.squareup.picasso.Picasso;

import java.util.List;

public class XuatVeAdapter extends RecyclerView.Adapter<XuatVeAdapter.XuatVeViewHolder> {

    private Context context;
    private List<xuatveEntity> xuatVeList;





    public XuatVeAdapter(Context context, List<xuatveEntity> xuatVeList) {
        this.context = context;
        this.xuatVeList = xuatVeList;
    }

    public XuatVeAdapter() {

    }

    @Override
    public XuatVeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_xuatve, parent, false);
        return new XuatVeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(XuatVeViewHolder holder, int position) {
        xuatveEntity item = xuatVeList.get(position);

        // Set data to views
        Picasso.get().load(item.getQrXuatVe())
                .placeholder(R.drawable.placeholder)  // Optional: Add a placeholder image
                .into(holder.qrXuatVe);
        holder.dateXuatVe1.setText(item.getDateXuatVe1());
        Picasso.get().load(item.getPosterXuatVe2())
                .placeholder(R.drawable.placeholder)  // Optional: Add a placeholder image
                .into(holder.posterXuatVe2);
        holder.ageXuatVe.setText(String.valueOf(item.getAgeXuatVe()));
        holder.nameXuatVe.setText(item.getNameXuatVe());
        holder.styleXuatVe.setText(item.getStyleXuatVe());
        holder.soLuongXuatVe.setText(String.valueOf(item.getSoLuongXuatVe()));  // SoLuong là kiểu int
        Picasso.get().load(item.getIconRapXuatVe())
                .placeholder(R.drawable.placeholder)  // Optional: Add a placeholder image
                .into(holder.iconRapXuatVe);
        holder.diaChiXuatVe.setText(item.getDiaChiXuatVe());
        holder.timebd.setText(item.getTime_batdau());
        holder.timekt.setText(item.getTime_ketthuc());
        holder.dateXuatVe2.setText(item.getDateXuatVe2());
        holder.dinhDangXuatPhim.setText(item.getDinhDangXuatPhim());
        holder.gheXuatVe.setText(String.valueOf(item.getGheXuatVe()));
        holder.phongXuatVe.setText(String.valueOf(item.getPhongXuatVe()));
        holder.trangThaiXuatVe.setText(item.getTrangThaiXuatVe());
    }

    @Override
    public int getItemCount() {
        return xuatVeList.size();
    }

    public void SetData(List<xuatveEntity> movieList) {

        this.xuatVeList = movieList;
        notifyDataSetChanged();
    }

    public static class XuatVeViewHolder extends RecyclerView.ViewHolder {

        ImageView qrXuatVe, posterXuatVe2, iconRapXuatVe;
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
            timebd = itemView.findViewById(R.id.time_batdau_xv);
            timekt = itemView.findViewById(R.id.time_ketthuc_xv);
            dateXuatVe2 = itemView.findViewById(R.id.date_xuatve_2);
            dinhDangXuatPhim = itemView.findViewById(R.id.dinhdang_xuatphim);
            gheXuatVe = itemView.findViewById(R.id.ghe_xuatve);
            phongXuatVe = itemView.findViewById(R.id.phong_xuatve);
            trangThaiXuatVe = itemView.findViewById(R.id.trangthai_xuatve);
        }
    }
}