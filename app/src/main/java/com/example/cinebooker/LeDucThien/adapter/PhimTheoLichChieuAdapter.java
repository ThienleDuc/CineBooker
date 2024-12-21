package com.example.cinebooker.LeDucThien.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_ThoiGianChieu;
import com.example.cinebooker.LeDucThien.entity.ent_XepHang;
import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Activity.XemChiTietPhim;
import com.example.cinebooker.generalMethod.NumberFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhimTheoLichChieuAdapter extends RecyclerView.Adapter<PhimTheoLichChieuAdapter.viewHolder> {

    private List<ent_XepHang> dangChieulist;
    private Context context;
    private SharedPreferences sharedPreferences;
    private BL_ThoiGianChieu blThoiGianChieu;
    private ThoiGianChieuAdapter thoiGianChieuAdapter;
    private int maPhim;
    private int maRapChieuCon;
    private int maThoigianChieu;

    @SuppressLint("NotifyDataSetChanged")
    public PhimTheoLichChieuAdapter(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);
        blThoiGianChieu = new BL_ThoiGianChieu();
        thoiGianChieuAdapter = new ThoiGianChieuAdapter(context);
        maRapChieuCon = sharedPreferences.getInt("maRapChieuCon", -1);
        maThoigianChieu = sharedPreferences.getInt("maThoiGianChieu", -1);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<ent_XepHang> dangChieulist) {
        this.dangChieulist = dangChieulist;
        notifyDataSetChanged();
    }

    public int getMaPhim() {
        Log.d("maPhim", String.valueOf(maPhim));
        return maPhim;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies_calendar, parent, false);
        return new viewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "NotifyDataSetChanged"})
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        ent_XepHang controller = dangChieulist.get(position);
        // Load ảnh phim từ URL hoặc tài nguyên
        maPhim = controller.getMaPhim();
        String imageName = controller.getAnhPhim();
        Context context = holder.itemView.getContext();
        @SuppressLint("DiscouragedApi") int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId)
                .resize(800, 800)// Load resource ID
                .into(holder.moviePoster);

        holder.age.setText(controller.getTuoi() + "+");
        holder.movieName.setText(controller.getTenPhim());
        holder.styleMovie.setText(controller.getTenTheLoai());

        holder.vote.setText(NumberFormatter.formatDoubleValueToString(controller.getDiemDanhGiaTrungBinh()));
        holder.shopping.setText(NumberFormatter.formatIntValueToString(controller.getTongLuotMuaPhim()));
        holder.comment.setText(NumberFormatter.formatIntValueToString(controller.getTongDanhGiaPhim()));


        blThoiGianChieu.loadThoiGianToRecyclerView(context, holder.recyclerView, thoiGianChieuAdapter,
                maPhim, maRapChieuCon, maThoigianChieu);

        holder.itemView.setOnClickListener(view -> {
            // Lưu maPhim vào SharedPreferences
            sharedPreferences.edit().putInt("maPhim", controller.getMaPhim()).apply();
            notifyDataSetChanged();
            // Chuyển sang Activity XemChiTietPhim
            Intent intent = new Intent(context, XemChiTietPhim.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        // Trả về số phần tử trong danh sách
        return (dangChieulist != null) ? dangChieulist.size() : 0;
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView age, movieName, styleMovie, vote, shopping, comment;
        RecyclerView recyclerView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            moviePoster = itemView.findViewById(R.id.lichChieu_moviePoster);
            age = itemView.findViewById(R.id.lichChieu_age);
            movieName = itemView.findViewById(R.id.lichChieu_nameMovie);
            styleMovie = itemView.findViewById(R.id.lichChieu_styleMovie);
            vote = itemView.findViewById(R.id.lichChieu_vote);
            shopping = itemView.findViewById(R.id.lichChieu_shopping);
            comment = itemView.findViewById(R.id.lichChieu_comment);
            recyclerView = itemView.findViewById(R.id.thoiGianChieuPhim);
        }
    }
}
