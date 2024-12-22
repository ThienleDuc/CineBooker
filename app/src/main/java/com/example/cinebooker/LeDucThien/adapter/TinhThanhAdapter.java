package com.example.cinebooker.LeDucThien.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_TinhThanh;
import com.example.cinebooker.LeDucThien.activity.DanhSachDiaDiemRap;
import com.example.cinebooker.LeDucThien.entity.ent_TinhThanh;
import com.example.cinebooker.R;

import java.util.List;

public class TinhThanhAdapter extends RecyclerView.Adapter<TinhThanhAdapter.ViewHolder> {
    private List<ent_TinhThanh> listTinhThanh;
    private final SharedPreferences.Editor editor;
    private final Context context;
    private int selectedMaTinhThanh; // Lưu lại mã tỉnh thành được chọn

    // Constructor
    public TinhThanhAdapter(Context context) {
        this.context = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        BL_TinhThanh blTinhThanh = new BL_TinhThanh();
        selectedMaTinhThanh = sharedPreferences.getInt("maTinhThanh", -1);
        if (selectedMaTinhThanh == -1) {
            selectedMaTinhThanh = blTinhThanh.loadMinMaTinhThanh();
            editor.putInt("maTinhThanh", selectedMaTinhThanh).apply();
        }
    }

    public int getSelectedMaTinhThanh() {
        return selectedMaTinhThanh;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setSelectedMaTinhThanh(int selectedMaTinhThanh) {
        this.selectedMaTinhThanh = selectedMaTinhThanh;
        editor.putInt("maTinhThanh", selectedMaTinhThanh).apply();
        notifyDataSetChanged();
    }

    // Set dữ liệu cho Adapter
    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ent_TinhThanh> listTinhThanh) {
        this.listTinhThanh = listTinhThanh;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_tinh_thanh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ent_TinhThanh tinhThanh = listTinhThanh.get(position);
        int maTinhThanhItem = tinhThanh.getMaTinhThanh();

        // Hiển thị tên tỉnh thành
        holder.tenTinhThanh.setText(tinhThanh.getTenTinhThanh());

        // Highlight item được chọn
        if (maTinhThanhItem == getSelectedMaTinhThanh()) {
            holder.container_imageview.setBackgroundResource(R.drawable.strock_1_pink_radius_10_transparent);
            holder.tenTinhThanh.setTextColor(context.getColor(R.color.primary_color));
            holder.tenTinhThanh.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            holder.container_imageview.setBackgroundResource(R.drawable.strock_1_white_radius_10_transparent);
            holder.tenTinhThanh.setTextColor(context.getColor(android.R.color.darker_gray));
            holder.tenTinhThanh.setTypeface(Typeface.DEFAULT);
        }

        // Xử lý sự kiện click
        holder.itemView.setOnClickListener(v -> {
            if (maTinhThanhItem != getSelectedMaTinhThanh()) {
                setSelectedMaTinhThanh(maTinhThanhItem); // Cập nhật mã tỉnh thành được chọn

                // Đóng Activity nếu cần
                if (context instanceof DanhSachDiaDiemRap) {
                    ((DanhSachDiaDiemRap) context).dongActivity();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTinhThanh != null ? listTinhThanh.size() : 0;
    }

    // ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout container_imageview;
        TextView tenTinhThanh;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container_imageview = itemView.findViewById(R.id.container_imageview);
            tenTinhThanh = itemView.findViewById(R.id.ten_tinh_thanh);
        }
    }
}
