package com.example.cinebooker.LeDucThien.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cinebooker.LeDucThien.entity.heThongRapChieuEntity;
import com.example.cinebooker.LeDucThien.generalMethod.NumberFormatter;
import com.example.cinebooker.R;

import java.util.List;

public class heThongRapChieuAdapter extends RecyclerView.Adapter<heThongRapChieuAdapter.viewHolder> {

    private List<heThongRapChieuEntity> dangChieulist;

    public heThongRapChieuAdapter() {

    }

    public heThongRapChieuAdapter(List<heThongRapChieuEntity> dangChieulist) {
        this.dangChieulist = dangChieulist;
    }

    @NonNull
    @Override
    public heThongRapChieuAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hethongrapchieuphim, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull heThongRapChieuAdapter.viewHolder holder, int position) {
        heThongRapChieuEntity controller = dangChieulist.get(position);

        holder.moviePoster.setImageResource(controller.getMoviePoster());

        holder.ten.setText(controller.getTen());
        holder.mota.setText(controller.getMoTa());

        holder.vote.setText(NumberFormatter.formatNumber(controller.getVote().doubleValue()));
        holder.location.setText(NumberFormatter.formatNumber(controller.getLocation().intValue()));
        holder.comment.setText(NumberFormatter.formatNumber(controller.getComment().doubleValue()));
    }


    @Override
    public int getItemCount() {
        return dangChieulist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
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
