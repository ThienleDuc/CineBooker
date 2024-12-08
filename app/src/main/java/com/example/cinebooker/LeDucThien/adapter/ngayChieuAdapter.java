package com.example.cinebooker.LeDucThien.adapter;

import static androidx.recyclerview.widget.LinearSmoothScroller.SNAP_TO_START;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.entity.ngayChieuEntity;
import com.example.cinebooker.LeDucThien.entity.thoiGianChieuPhimEntity;
import com.example.cinebooker.R;

import java.util.ArrayList;
import java.util.List;

public class ngayChieuAdapter extends RecyclerView.Adapter<ngayChieuAdapter.viewHolder> {

    private List<ngayChieuEntity> ngayChieuList;
    private int selectedPosition = -1; // Theo dõi vị trí item đang được chọn
    private OnItemClickListener onItemClickListener;

    // Constructor cho adapter với danh sách dữ liệu
    public ngayChieuAdapter(List<ngayChieuEntity> ngayChieuList) {
        this.ngayChieuList = ngayChieuList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ngaychieu, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ngayChieuEntity ngayChieu = ngayChieuList.get(position);

        // Cập nhật dữ liệu cho item
        holder.day.setText(String.valueOf(ngayChieu.getDay()));
        holder.dayName.setText(ngayChieu.getDayName());

        // Thay đổi màu nền dựa trên trạng thái được chọn
        if (position == selectedPosition) {
//            holder.itemView.setBackgroundResource(R.drawable.selected_tab_background);
        } else {
//            holder.itemView.setBackgroundResource(R.drawable.default_tab_background);
        }

        // Xử lý sự kiện click
        holder.itemView.setOnClickListener(v -> {
            int oldPosition = selectedPosition;
            selectedPosition = position;
            notifyItemChanged(oldPosition); // Cập nhật lại item cũ
            notifyItemChanged(selectedPosition); // Cập nhật lại item mới

            // Gọi callback về Fragment/Activity để xử lý cuộn hoặc sự kiện khác
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });

        List<thoiGianChieuPhimEntity> thoiGianChieuPhim = new ArrayList<>();

        thoiGianChieuPhim.add(new thoiGianChieuPhimEntity("10:40", "12:42"));

    }

    @Override
    public int getItemCount() {
        return ngayChieuList != null ? ngayChieuList.size() : 0;
    }

    // Thiết lập callback khi item được click
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // ViewHolder class để ánh xạ các view trong item layout
    public class viewHolder extends RecyclerView.ViewHolder {
        TextView day, dayName;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.day);
            dayName = itemView.findViewById(R.id.dayname);
        }
    }
}