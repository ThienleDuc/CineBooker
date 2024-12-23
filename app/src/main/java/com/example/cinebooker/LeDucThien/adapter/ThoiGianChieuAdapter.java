package com.example.cinebooker.LeDucThien.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import com.example.cinebooker.LeDucThien.entity.ent_ThoiGianChieu;
import com.example.cinebooker.R;

import java.util.List;

public class ThoiGianChieuAdapter extends RecyclerView.Adapter<ThoiGianChieuAdapter.ViewHolder> {

    private List<ent_ThoiGianChieu> thoiGianChieuList; // Changed to more descriptive name

    @SuppressLint("NotifyDataSetChanged")
    public void setData (List<ent_ThoiGianChieu> thoiGianChieuList) {
        this.thoiGianChieuList = thoiGianChieuList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for the item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thoi_gian_chieu_phim, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data for this item
        ent_ThoiGianChieu thoiGianChieu = thoiGianChieuList.get(position);

        // Set the data to the views
        holder.begin.setText(thoiGianChieu.getThoiGianBatDau());
        holder.end.setText(thoiGianChieu.getThoiGianKetThuc());

    }

    @Override
    public int getItemCount() {
        return thoiGianChieuList != null ? thoiGianChieuList.size() : 0; // Return the actual size of the list
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView begin, end;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            begin = itemView.findViewById(R.id.thoiGian_BatDau);
            end = itemView.findViewById(R.id.thoiGian_KetThuc);
        }
    }
}
