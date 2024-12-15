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

import com.example.cinebooker.LeDucThien.entity.ent_DanhGiaPhim;
import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Activity.XemChiTietPhim;
import com.example.cinebooker.generalMethod.ActivityOpen;
import com.example.cinebooker.generalMethod.NumberFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DanhGiaPhimAdapter extends RecyclerView.Adapter<DanhGiaPhimAdapter.viewHolder> {

    private List<ent_DanhGiaPhim> dangChieulist;

    public DanhGiaPhimAdapter() {

    }

    @SuppressLint("NotifyDataSetChanged")
    public void SetData(List<ent_DanhGiaPhim> dangChieulist) {
        this.dangChieulist = dangChieulist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DanhGiaPhimAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies_binhluan, parent, false);
        return new viewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DanhGiaPhimAdapter.viewHolder holder, int position) {
        ent_DanhGiaPhim dangChieu = dangChieulist.get(position);

        String imageName = dangChieu.getAnhPhim();
        Context context = holder.itemView.getContext();
        @SuppressLint("DiscouragedApi")
        int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId)
                .resize(800, 800)// Load resource ID
                .into(holder.moviePoster);

        holder.age.setText(dangChieu.getTuoi() + "+");
        holder.movieName.setText(dangChieu.getTenPhim());
        holder.styleMovie.setText(dangChieu.getTenTheLoai());

        holder.vote.setText(NumberFormatter.formatDoubleValueToString(dangChieu.getDiemDanhGiaTrungBinh()));
        holder.shopping.setText(NumberFormatter.formatIntValueToString(dangChieu.getTongLuotMua()));
        holder.comment.setText(NumberFormatter.formatIntValueToString(dangChieu.getTongLuotDanhGia()));

        Picasso.get().load(dangChieu.getAnhKhachHang())
                .placeholder(R.drawable.avatar).into(holder.avatar);
        holder.userName.setText(dangChieu.getTenKhachHang());
        holder.day.setText(dangChieu.getNgayDanhGia());
        holder.userComment.setText(dangChieu.getDanhGia());

        int maPhim = dangChieu.getMaPhim();
        holder.xemThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                if (context instanceof AppCompatActivity) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor;
                    editor = sharedPreferences.edit();
                    editor.putInt("maPhim", maPhim);
                    editor.apply();
                    Intent intent = new Intent(context, XemChiTietPhim.class);
                    context.startActivity(intent);                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dangChieulist.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster, avatar;
        TextView age, movieName, styleMovie, vote, shopping, comment, userName, day, userComment;
        TextView xemThem;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            moviePoster = itemView.findViewById(R.id.binhluan_moviePoster);
            avatar = itemView.findViewById(R.id.comment_avatar);
            age = itemView.findViewById(R.id.binhluan_age);
            movieName = itemView.findViewById(R.id.binhluan_nameMovie);
            styleMovie = itemView.findViewById(R.id.binhluan_styleMovie);
            vote = itemView.findViewById(R.id.binhluan_vote);
            shopping = itemView.findViewById(R.id.binhluan_shopping);
            comment = itemView.findViewById(R.id.binhluan_comment);
            userName = itemView.findViewById(R.id.comment_username);
            day = itemView.findViewById(R.id.comment_day);
            userComment = itemView.findViewById(R.id.write_comment);
            xemThem = itemView.findViewById(R.id.binhluan_xemthem);
        }
    }
}
