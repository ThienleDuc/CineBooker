package com.example.cinebooker.LeDucThien.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.entity.ent_XepHang;
import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Activity.XemChiTietPhim;

import com.example.cinebooker.generalMethod.NumberFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class XepHangAdapter extends RecyclerView.Adapter<XepHangAdapter.viewHolder> {

    private List<ent_XepHang> dangChieulist;

    public XepHangAdapter() {

    }

    @SuppressLint("NotifyDataSetChanged")
    public void SetData(List<ent_XepHang> dangChieulist) {
        this.dangChieulist = dangChieulist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies_xephang, parent, false);
        return new viewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ent_XepHang controller = dangChieulist.get(position);

        String imageName = controller.getAnhPhim();
        Context context = holder.itemView.getContext();
        @SuppressLint("DiscouragedApi") int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId)  // Load resource ID
                .resize(800, 800)// Load resource ID
                .into(holder.moviePoster);

        holder.age.setText(controller.getTuoi() + "+");
        holder.movieName.setText(controller.getTenPhim());
        holder.styleMovie.setText(controller.getTenTheLoai());

        holder.vote.setText(NumberFormatter.formatDoubleValueToString(controller.getDiemDanhGiaTrungBinh()));
        holder.shopping.setText(NumberFormatter.formatIntValueToString(controller.getTongLuotMuaPhim()));
        holder.comment.setText(NumberFormatter.formatIntValueToString(controller.getTongDanhGiaPhim()));

        int maPhim = controller.getMaPhim();
        holder.itemView.setOnClickListener(v -> {
            Context context2 = v.getContext();
            if (context2 instanceof AppCompatActivity) {
                SharedPreferences sharedPreferences = context2.getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor;
                editor = sharedPreferences.edit();
                editor.putInt("maPhim", maPhim);
                editor.apply();
                Intent intent = new Intent(context, XemChiTietPhim.class);
                context.startActivity(intent);            }
                notifyDataSetChanged();
        });
    }


    @Override
    public int getItemCount() {
        return dangChieulist.size();
    }
    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView age, movieName, styleMovie, vote, shopping, comment;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            moviePoster = itemView.findViewById(R.id.lichChieu_moviePoster);
            age = itemView.findViewById(R.id.lichChieu_age);
            movieName = itemView.findViewById(R.id.lichChieu_nameMovie);
            styleMovie = itemView.findViewById(R.id.lichChieu_styleMovie);
            vote = itemView.findViewById(R.id.lichChieu_vote);
            shopping = itemView.findViewById(R.id.lichChieu_shopping);
            comment = itemView.findViewById(R.id.lichChieu_comment);

        }
    }
}
