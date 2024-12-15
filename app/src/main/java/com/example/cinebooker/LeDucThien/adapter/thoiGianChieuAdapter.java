package com.example.cinebooker.LeDucThien.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.entity.thoiGianChieuPhimEntity;
import com.example.cinebooker.R;

import java.util.List;

public class thoiGianChieuAdapter extends RecyclerView.Adapter<thoiGianChieuAdapter.viewHolder> {

    private List<thoiGianChieuPhimEntity> dangChieulist;

    public thoiGianChieuAdapter() {

    }

    public thoiGianChieuAdapter(List<thoiGianChieuPhimEntity> dangChieulist) {
        this.dangChieulist = dangChieulist;
    }

    @NonNull
    @Override
    public thoiGianChieuAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thoi_gian_chieu_phim, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull thoiGianChieuAdapter.viewHolder holder, int position) {
        thoiGianChieuPhimEntity controller = dangChieulist.get(position);

        holder.begin.setText(controller.getBegin());
        holder.end.setText(controller.getEnd());

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView begin, end;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            begin = itemView.findViewById(R.id.thoiGian_BatDau);
            end = itemView.findViewById(R.id.thoiGian_KetThuc);
        }
    }
}
