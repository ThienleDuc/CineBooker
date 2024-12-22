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
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_RapChieu;
import com.example.cinebooker.LeDucThien.activity.danhSachRap;
import com.example.cinebooker.LeDucThien.activity.home;
import com.example.cinebooker.LeDucThien.entity.ent_RapChieu;
import com.example.cinebooker.LeDucThien.fragment.rap_chieu;
import com.example.cinebooker.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RapChieuAdapter extends RecyclerView.Adapter<RapChieuAdapter.ViewHolder> {
    private List<ent_RapChieu> rapChieuList;
    private final SharedPreferences.Editor editor;
    private final Context context;
    private int selectedMaRapChieu;

    // Constructor
    public RapChieuAdapter(Context context) {
        this.context = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        BL_RapChieu blRapChieu = new BL_RapChieu();
        selectedMaRapChieu = sharedPreferences.getInt("maRapChieu", -1);
        if (selectedMaRapChieu == -1) {
            selectedMaRapChieu = blRapChieu.loadMinMaRapChieu();
            editor.putInt("maRapChieu", selectedMaRapChieu).apply();
        }
    }

    public int getSelectedMaRapChieu() {
        return selectedMaRapChieu;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setSelectedMaRapChieu(int selectedMaRapChieu) {
        this.selectedMaRapChieu = selectedMaRapChieu;
        editor.putInt("maRapChieu", selectedMaRapChieu).apply();
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ent_RapChieu> rapChieuList) {
        this.rapChieuList = rapChieuList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_thoi_gian_chieu_theo_ngay, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ent_RapChieu rapChieu = rapChieuList.get(position);
        int maRapChieuItem = rapChieu.getMaRapChieu();

        // Load ảnh bằng Picasso
        String imageName = rapChieu.getAnhRapChieu();
        @SuppressLint("DiscouragedApi") int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        Picasso.get()
                .load(resourceId)
                .placeholder(R.drawable.camposter)
                .into(holder.cinemaLogo);

        holder.name.setText(rapChieu.getTenRapChieu());

        // Highlight item được chọn
        if (maRapChieuItem == getSelectedMaRapChieu()) {
            holder.containerImageView.setBackgroundResource(R.drawable.strock_1_pink_radius_10_white);
            holder.name.setTextColor(context.getColor(R.color.primary_color));
            holder.name.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            holder.containerImageView.setBackgroundResource(R.drawable.strock_1_darkergray_radius_10_white);
            holder.name.setTextColor(context.getColor(android.R.color.darker_gray));
            holder.name.setTypeface(Typeface.DEFAULT);
        }

        // Xử lý sự kiện click
        holder.itemView.setOnClickListener(v -> {
            if (maRapChieuItem != getSelectedMaRapChieu()) {
                setSelectedMaRapChieu(maRapChieuItem);
            }

            if (context instanceof danhSachRap) {
                int maTinhThanh = ((danhSachRap) context).getRapChieuConAdapter().getMaTinhThanh();
                int maRapChieu = getSelectedMaRapChieu();
                RecyclerView recyclerView = ((danhSachRap) context).getRecyclerView1();
                ((danhSachRap) context).getBlRapChieuCon().loadRapChieuConToRecyclerView(context, recyclerView,
                        maTinhThanh, maRapChieu, ((danhSachRap) context).getRapChieuConAdapter());
            }

            if (context instanceof home) {

                FragmentManager fragmentManager = ((home) context).getSupportFragmentManager();

                rap_chieu fragment = (rap_chieu) fragmentManager.findFragmentByTag(rap_chieu.class.getSimpleName());

                if (fragment != null) {
                    int maTinhThanh = fragment.getDiaChiRapChieuAdapter().getMaTinhThanh();
                    int maRapChieu = getSelectedMaRapChieu();

                    RecyclerView recyclerView = fragment.getRcRecyclerView();
                    fragment.getBlRapChieuCon().
                            loadDiaChiRapChieuConToRecyclerView(context, recyclerView, maTinhThanh,
                                    maRapChieu, fragment.getDiaChiRapChieuAdapter());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rapChieuList != null ? rapChieuList.size() : 0;
    }

    // ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout containerImageView;
        ImageView cinemaLogo;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            containerImageView = itemView.findViewById(R.id.container_imageview);
            cinemaLogo = itemView.findViewById(R.id.cinema_logo);
            name = itemView.findViewById(R.id.cinema_name);
        }
    }
}
