package com.example.cinebooker.LeDucThien.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cinebooker.LeDucThien.entity.ent_DoiTac;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.NumberFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DoiTacAdapter extends RecyclerView.Adapter<DoiTacAdapter.viewHolder> {

    private List<ent_DoiTac> dangChieulist;

    public DoiTacAdapter() {

    }

    @SuppressLint("NotifyDataSetChanged")
    public void SetData (List<ent_DoiTac> dangChieulist) {
        this.dangChieulist = dangChieulist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DoiTacAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hethongrapchieuphim, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoiTacAdapter.viewHolder holder, int position) {
        ent_DoiTac controller = dangChieulist.get(position);

        String imageName = controller.getAnhDoiTac();
        Context context = holder.itemView.getContext();
        @SuppressLint("DiscouragedApi") int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId)  // Load resource ID
                .placeholder(R.drawable.camposter)  // Optional: Placeholder image
                .into(holder.moviePoster);
        holder.ten.setText(controller.getTenDoiTac());
        holder.mota.setText(controller.getMoTaDoiTac());

        holder.vote.setText(NumberFormatter.formatDoubleValueToString(controller.getDiemDanhGiaDoiTacTrungBinh()));
        holder.location.setText(NumberFormatter.formatIntValueToString(controller.getTongDiaDiemDoiTac()));
        holder.comment.setText(NumberFormatter.formatIntValueToString(controller.getTongLuotDanhGia()));
    }

    @Override
    public int getItemCount() {
        return dangChieulist.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView ten, mota, vote, location, comment;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            moviePoster = itemView.findViewById(R.id.heThongRapChieu_Logo);
            ten = itemView.findViewById(R.id.tenRapChieu);
            mota = itemView.findViewById(R.id.motaRapChieu);
            vote = itemView.findViewById(R.id.rapChieu_vote);
            location = itemView.findViewById(R.id.rapchieu_vitri);
            comment = itemView.findViewById(R.id.rapChieu_comment);

        }
    }
}
