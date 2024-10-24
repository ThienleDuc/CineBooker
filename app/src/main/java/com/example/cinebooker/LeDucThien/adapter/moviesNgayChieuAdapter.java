package com.example.cinebooker.LeDucThien.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.entity.moviesNgayChieuEntity;
import com.example.cinebooker.LeDucThien.entity.thoiGianChieuPhimEntity;
import com.example.cinebooker.LeDucThien.generalMethod.HorizontalSpaceItemDecoration;
import com.example.cinebooker.LeDucThien.generalMethod.NumberFormatter;
import com.example.cinebooker.R;

import java.util.ArrayList;
import java.util.List;

public class moviesNgayChieuAdapter extends RecyclerView.Adapter<moviesNgayChieuAdapter.viewHolder> {

    private List<moviesNgayChieuEntity> dangChieulist;

    public moviesNgayChieuAdapter() {

    }

    public moviesNgayChieuAdapter(List<moviesNgayChieuEntity> dangChieulist) {
        this.dangChieulist = dangChieulist;
    }

    @NonNull
    @Override
    public moviesNgayChieuAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies_calendar, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull moviesNgayChieuAdapter.viewHolder holder, int position) {
        moviesNgayChieuEntity controller = dangChieulist.get(position);

        holder.moviePoster.setImageResource(controller.getMoviePoster());

        holder.age.setText(controller.getAge());
        holder.movieName.setText(controller.getMovieName());
        holder.styleMovie.setText(controller.getStyleMovie());

        holder.vote.setText(NumberFormatter.formatNumber(controller.getVote().doubleValue()));
        holder.shopping.setText(NumberFormatter.formatNumber(controller.getShoping().doubleValue()));
        holder.comment.setText(NumberFormatter.formatNumber(controller.getComment().doubleValue()));

        // Giả sử bạn đã thêm thuộc tính thoiGianChieuPhim vào moviesNgayChieuEntity
        List<thoiGianChieuPhimEntity> thoiGianChieuPhim = controller.getThoiGianChieuPhim();

        // Nếu danh sách thoiGianChieuPhim rỗng, bạn có thể xử lý hoặc thêm thông báo
        if (thoiGianChieuPhim == null || thoiGianChieuPhim.isEmpty()) {
            // Xử lý khi danh sách trống, có thể thêm thông báo hoặc ẩn RecyclerView
            holder.thoiGianChieuPhim.setVisibility(View.GONE);
            return;
        } else {
            holder.thoiGianChieuPhim.setVisibility(View.VISIBLE);
        }

        // Tạo adapter cho thoiGianChieuPhim
        thoiGianChieuAdapter thoiGianChieuAdapter = new thoiGianChieuAdapter(thoiGianChieuPhim);
        holder.thoiGianChieuPhim.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
        holder.thoiGianChieuPhim.setAdapter(thoiGianChieuAdapter);

        int spacingInPixels = holder.itemView.getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        holder.thoiGianChieuPhim.addItemDecoration(new HorizontalSpaceItemDecoration(spacingInPixels));
    }


    @Override
    public int getItemCount() {
        return 3;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView age, movieName, styleMovie, vote, shopping, comment;
        RecyclerView thoiGianChieuPhim;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            moviePoster = itemView.findViewById(R.id.lichChieu_moviePoster);
            age = itemView.findViewById(R.id.lichChieu_age);
            movieName = itemView.findViewById(R.id.lichChieu_nameMovie);
            styleMovie = itemView.findViewById(R.id.lichChieu_styleMovie);
            vote = itemView.findViewById(R.id.lichChieu_vote);
            shopping = itemView.findViewById(R.id.lichChieu_shopping);
            comment = itemView.findViewById(R.id.lichChieu_comment);

            thoiGianChieuPhim = itemView.findViewById(R.id.thoiGianChieuPhim);

        }
    }
}
