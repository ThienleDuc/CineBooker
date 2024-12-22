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
    private final SharedPreferences sharedPreferences;
    private final Context context;
    private int _maRapChieuConPref;
    private final int maTinhThanh;
    private final int maRapChieu;

    public RapChieuConAdapter(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);
        BL_RapChieuCon blRapChieuCon = new BL_RapChieuCon();
        _maRapChieuConPref = sharedPreferences.getInt("maRapChieuCon", -1);
        TinhThanhAdapter tinhThanhAdapter = new TinhThanhAdapter(context);
        maTinhThanh = tinhThanhAdapter.getSelectedMaTinhThanh();
        RapChieuAdapter rapChieuAdapter = new RapChieuAdapter(context);
        maRapChieu = rapChieuAdapter.getSelectedMaRapChieu();
        if (_maRapChieuConPref == -1) {
            _maRapChieuConPref = blRapChieuCon.loadMinRapChieuCon(maTinhThanh, maRapChieu);
            sharedPreferences.edit().putInt("maRapChieuCon", _maRapChieuConPref).apply();
        }
    }

    public int getMaTinhThanh() {
        return maTinhThanh;
    }

    public int getMaRapChieu() {
        return maRapChieu;
    }

    public int get_maRapChieuConPref() {
        return _maRapChieuConPref;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void set_maRapChieuConPref(int _maRapChieuConPref) {
        this._maRapChieuConPref = _maRapChieuConPref;
        sharedPreferences.edit().putInt("maRapChieuCon", _maRapChieuConPref).apply();
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void SetData(List<ent_RapChieuCon> rapChieulist) {
        this.rapChieulist = rapChieulist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diachirapchieu, parent, false);
        return new viewHolder(view);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ent_RapChieuCon rapChieu = rapChieulist.get(position);

        String imageName = rapChieu.getAnhRapChieu();
        @SuppressLint("DiscouragedApi")
        int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId)  // Load resource ID
                .placeholder(R.drawable.camposter)  // Optional: Placeholder image
                .into(holder.cinemaLogor);
        holder.location.setText(rapChieu.getTenRapChieuCon());

        int _maRapChieuCon = rapChieu.getMaRapChieuCon();
        // Lấy giá trị maTinhThanh từ SharedPreferences

        // Đặt background cho item
        if (get_maRapChieuConPref() == _maRapChieuCon) {
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
            if (_maRapChieuCon != get_maRapChieuConPref()) {
                set_maRapChieuConPref(rapChieu.getMaRapChieuCon());
            }

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
