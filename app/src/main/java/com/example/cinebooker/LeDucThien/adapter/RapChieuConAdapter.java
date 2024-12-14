package com.example.cinebooker.LeDucThien.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_RapChieuCon;
import com.example.cinebooker.LeDucThien.activity.danhSachRap;
import com.example.cinebooker.LeDucThien.entity.ent_RapChieuCon;
import com.example.cinebooker.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RapChieuConAdapter extends RecyclerView.Adapter<RapChieuConAdapter.viewHolder> {
    private List<ent_RapChieuCon> rapChieulist;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;;
    private BL_RapChieuCon blRapChieuCon;

    @SuppressLint("NotifyDataSetChanged")
    public void SetData(List<ent_RapChieuCon> rapChieulist) {
        this.rapChieulist = rapChieulist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RapChieuConAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diachirapchieu, parent, false);
        return new viewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull RapChieuConAdapter.viewHolder holder, int position) {
        ent_RapChieuCon rapChieu = rapChieulist.get(position);

        String imageName = rapChieu.getAnhRapChieu();
        Context context = holder.itemView.getContext();
        @SuppressLint("DiscouragedApi")
        int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId)  // Load resource ID
                .placeholder(R.drawable.camposter)  // Optional: Placeholder image
                .into(holder.cinemaLogor);
        holder.location.setText(rapChieu.getTenRapChieuCon());

        int _maRapChieuCon = rapChieu.getMaRapChieuCon();
        // Lấy giá trị maTinhThanh từ SharedPreferences
        sharedPreferences = holder.itemView.getContext().getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);
        int _maRapChieuConPref = sharedPreferences.getInt("maRapChieuCon", -1);

        // Đặt background cho item
        if (_maRapChieuConPref == _maRapChieuCon) {
            holder.container_imageview.setBackgroundResource(R.drawable.strock_1_pink_radius_10_transparent); // Nền khi được chọn
            holder.location.setTextColor(holder.itemView.getContext().getColor(R.color.primary_color));
            holder.location.setTypeface(holder.location.getTypeface(), Typeface.BOLD);
        } else {
            holder.container_imageview.setBackgroundResource(R.drawable.strock_1_darkergray_radius_10_transparent); // Nền khi không được chọn
            holder.location.setTextColor(holder.itemView.getContext().getColor(R.color.black));
            holder.location.setTypeface(holder.location.getTypeface(), Typeface.NORMAL);

        }

        // Xử lý sự kiện click
        holder.itemView.setOnClickListener(v -> {
            editor = sharedPreferences.edit();
            editor.putInt("maRapChieuCon", _maRapChieuCon);
            editor.apply();  // Lưu thay đổi
            // Cập nhật lại giao diện của RecyclerView
            notifyDataSetChanged(); // Refresh toàn bộ adapter để áp dụng thay đổi
            if (holder.itemView.getContext() instanceof danhSachRap) {
                ((danhSachRap) holder.itemView.getContext()).dongActivity();  // Đóng Activity nếu cần
            }
        });
    }

    @Override
    public int getItemCount() {
        return rapChieulist.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        LinearLayout container_imageview;
        ImageView cinemaLogor;
        TextView location;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            container_imageview = itemView.findViewById(R.id.container_imageview);
            cinemaLogor = itemView.findViewById(R.id.cinema_logo);
            location = itemView.findViewById(R.id.cinema_location);
        }
    }
}
