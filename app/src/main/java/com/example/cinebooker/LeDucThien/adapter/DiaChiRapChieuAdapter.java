package com.example.cinebooker.LeDucThien.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cinebooker.LeDucThien.BussinessLogic.BL_RapChieuCon;
import com.example.cinebooker.LeDucThien.entity.ent_RapChieuCon;
import com.example.cinebooker.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DiaChiRapChieuAdapter extends RecyclerView.Adapter<DiaChiRapChieuAdapter.viewHolder> {

    private List<ent_RapChieuCon> rapChieulist;
    private int maTinhThanh;
    private int maRapChieu;
    public DiaChiRapChieuAdapter(Context context) {
        TinhThanhAdapter tinhThanhAdapter = new TinhThanhAdapter(context);
        maTinhThanh = tinhThanhAdapter.getSelectedMaTinhThanh();
        RapChieuAdapter rapChieuAdapter = new RapChieuAdapter(context);
        maRapChieu = rapChieuAdapter.getSelectedMaRapChieu();
    }

    public int getMaRapChieu() {
        return maRapChieu;
    }

    public int getMaTinhThanh() {
        return maTinhThanh;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void SetData(List<ent_RapChieuCon> rapChieulist) {
        this.rapChieulist = rapChieulist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timdiachirapchieu, parent, false);
        return new viewHolder(view);
    }

    @SuppressLint("QueryPermissionsNeeded")
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ent_RapChieuCon rapChieu = rapChieulist.get(position);

        String imageName = rapChieu.getAnhRapChieu();
        Context context = holder.itemView.getContext();
        @SuppressLint("DiscouragedApi") int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId)  // Load resource ID
                .placeholder(R.drawable.camposter)  // Optional: Placeholder image
                .into(holder.cinemaLogor);

        holder.name.setText(rapChieu.getTenRapChieuCon());
        holder.location.setText(rapChieu.getDiaChiRapChieu());

        holder.map.setOnClickListener(v -> {
            String map = rapChieu.getMap();
            Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(map));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
            mapIntent.setPackage("com.google.android.apps.maps");

            // Kiểm tra xem Google Maps có sẵn trên thiết bị không
            if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
                // Nếu có, mở Google Maps
                context.startActivity(mapIntent);
            } else {
                // Nếu không có Google Maps, mở trên trình duyệt
                Uri webUri = Uri.parse("https://maps.google.com/?q=" + Uri.encode(map));
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webUri);
                context.startActivity(webIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return rapChieulist.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView cinemaLogor, map;
        TextView location, name;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            cinemaLogor = itemView.findViewById(R.id.cinema_logo);
            name = itemView.findViewById(R.id.cinema_name);
            location = itemView.findViewById(R.id.cinema_location);
            map = itemView.findViewById(R.id.cinema_map);
        }
    }
}
