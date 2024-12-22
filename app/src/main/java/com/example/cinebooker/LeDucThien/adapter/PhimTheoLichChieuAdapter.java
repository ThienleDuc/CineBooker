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
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_ThoiGianChieu;
import com.example.cinebooker.LeDucThien.activity.home;
import com.example.cinebooker.LeDucThien.entity.ent_XepHang;
import com.example.cinebooker.LeDucThien.fragment.kham_pha;
import com.example.cinebooker.LeDucThien.fragment.lich_chieu;
import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Activity.XemChiTietPhim;
import com.example.cinebooker.generalMethod.NumberFormatter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhimTheoLichChieuAdapter extends RecyclerView.Adapter<PhimTheoLichChieuAdapter.viewHolder> {

    private List<ent_XepHang> dangChieulist;
    private final Context context;
    private final SharedPreferences sharedPreferences;
    private final BL_ThoiGianChieu blThoiGianChieu;
    private final ThoiGianChieuAdapter thoiGianChieuAdapter;
    private int maPhim;
    private final int maRapChieuCon;
    private final int maThoigianChieu;

    @SuppressLint("NotifyDataSetChanged")
    public PhimTheoLichChieuAdapter(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);
        RapChieuConAdapter rapChieuConAdapter = new RapChieuConAdapter(context);
        thoiGianChieuAdapter = new ThoiGianChieuAdapter();
        LichChieuAdapter lichChieuAdapter = new LichChieuAdapter(context);
        blThoiGianChieu = new BL_ThoiGianChieu();
        maRapChieuCon = rapChieuConAdapter.get_maRapChieuConPref();
        maThoigianChieu = lichChieuAdapter.getSelectedMaThoiGianChieu();
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

    @SuppressLint("NotifyDataSetChanged")
    public void setMaPhim(int maPhim) {
        this.maPhim = maPhim;
        sharedPreferences.edit().putInt("maPhim", maPhim).apply();
        notifyDataSetChanged();
    }

    public int getMaRapChieuCon() {
        return maRapChieuCon;
    }

    public int getMaThoigianChieu() {
        return maThoigianChieu;
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
            if (maPhim != getMaPhim()) {
                setMaPhim(controller.getMaPhim());

                if (context instanceof home) {

                    FragmentManager fragmentManager = ((home) context).getSupportFragmentManager();

                    kham_pha fragment = (kham_pha) fragmentManager.findFragmentByTag(kham_pha.class.getSimpleName());
                    lich_chieu fragment1 = (lich_chieu) fragmentManager.findFragmentByTag(lich_chieu.class.getSimpleName());

                    if (fragment != null) {
                        int _maRapChieuCon = fragment.getPhimTheoLichChieuAdapter().getMaRapChieuCon();

                        RecyclerView recyclerView = fragment.getRecyclerView1();
                        fragment.getBlPhimTheoLichChieu().
                                loadPhimTheoLichChieuToRecyclerView(context, recyclerView,
                                        fragment.getPhimTheoLichChieuAdapter(), _maRapChieuCon, getMaThoigianChieu());
                    }

                    if (fragment1 != null) {
                        int _maRapChieuCon = fragment1.getPhimTheoLichChieuAdapter().getMaRapChieuCon();

                        RecyclerView recyclerView = fragment1.getRecyclerView1();
                        fragment1.getBlPhimTheoLichChieu().
                                loadPhimTheoLichChieuToRecyclerView(context, recyclerView,
                                        fragment1.getPhimTheoLichChieuAdapter(), _maRapChieuCon, getMaThoigianChieu());
                    }
                }
            }



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
