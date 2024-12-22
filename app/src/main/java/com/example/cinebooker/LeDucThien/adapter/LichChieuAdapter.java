package com.example.cinebooker.LeDucThien.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_NgayChieu;
import com.example.cinebooker.LeDucThien.entity.ent_NgayChieu;
import com.example.cinebooker.R;

import java.util.List;

public class LichChieuAdapter extends RecyclerView.Adapter<LichChieuAdapter.ViewHolder> {
    private List<ent_NgayChieu> ngayChieuList;
    private final SharedPreferences.Editor editor;
    private final Context context;
    private int selectedMaThoiGianChieu; // Lưu lại mã thời gian chiếu được chọn

    // Constructor
    public LichChieuAdapter(Context context) {
        this.context = context;
        BL_NgayChieu blNgayChieu = new BL_NgayChieu();
        SharedPreferences sharedPreferences = context.getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        selectedMaThoiGianChieu = sharedPreferences.getInt("maThoiGianChieu", -1);
        if (selectedMaThoiGianChieu == -1) {
            selectedMaThoiGianChieu = blNgayChieu.getMinNgayChieu();
            editor.putInt("maThoiGianChieu", selectedMaThoiGianChieu).apply();
        }
    }

    public int getSelectedMaThoiGianChieu() {
        return selectedMaThoiGianChieu;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setSelectedMaThoiGianChieu(int selectedMaThoiGianChieu) {
        this.selectedMaThoiGianChieu = selectedMaThoiGianChieu;
        editor.putInt("maThoiGianChieu", selectedMaThoiGianChieu).apply();
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ent_NgayChieu> ngayChieuList) {
        this.ngayChieuList = ngayChieuList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ngaychieu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (ngayChieuList == null || ngayChieuList.isEmpty()) return;

        ent_NgayChieu ngayChieu = ngayChieuList.get(position);
        int maThoiGianChieuItem = ngayChieu.getMaThoiGianChieu();

        holder.day.setText(ngayChieu.getNgayChieu());
        holder.dayName.setText(ngayChieu.getKieuNgay());

        // Highlight item được chọn
        if (maThoiGianChieuItem == getSelectedMaThoiGianChieu()) {
            holder.containerImageView.setBackgroundResource(R.drawable.strock_1_pink_radius_10_white);
            holder.dayName.setBackgroundResource(R.color.pink);
            holder.dayName.setTextColor(context.getColor(R.color.white));
            holder.day.setBackgroundResource(R.color.white);
            holder.day.setTextColor(context.getColor(R.color.pink));
        } else {
            holder.containerImageView.setBackgroundResource(R.drawable.strock_1_darkergray_radius_10_white);
            holder.dayName.setBackgroundResource(R.color.white);
            holder.dayName.setTextColor(context.getColor(R.color.black));
            holder.day.setBackgroundResource(R.color.white);
            holder.day.setTextColor(context.getColor(R.color.black));
        }

        // Xử lý sự kiện click vào item
        holder.itemView.setOnClickListener(v -> {
            if (maThoiGianChieuItem != getSelectedMaThoiGianChieu()) {
                setSelectedMaThoiGianChieu(maThoiGianChieuItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ngayChieuList != null ? ngayChieuList.size() : 0;
    }

    // ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout containerImageView;
        TextView dayName, day;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            containerImageView = itemView.findViewById(R.id.container_imageview);
            dayName = itemView.findViewById(R.id.dayname);
            day = itemView.findViewById(R.id.day);
        }
    }
}
