package com.example.cinebooker.LeDucThien.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.entity.xepHangEntity;
import com.example.cinebooker.LeDucThien.generalMethod.NumberFormatter;
import com.example.cinebooker.R;

import java.util.List;

public class xepHangAdapter extends RecyclerView.Adapter<xepHangAdapter.viewHolder> {

    private List<xepHangEntity> dangChieulist;

    public xepHangAdapter() {

    }

    public xepHangAdapter(List<xepHangEntity> dangChieulist) {
        this.dangChieulist = dangChieulist;
    }

    @NonNull
    @Override
    public xepHangAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies_xephang, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull xepHangAdapter.viewHolder holder, int position) {
        xepHangEntity controller = dangChieulist.get(position);

        holder.moviePoster.setImageResource(controller.getMoviePoster());

        holder.age.setText(controller.getAge());
        holder.movieName.setText(controller.getMovieName());
        holder.styleMovie.setText(controller.getStyleMovie());

        holder.vote.setText(NumberFormatter.formatNumber(controller.getVote().doubleValue()));
        holder.shopping.setText(NumberFormatter.formatNumber(controller.getShoping().doubleValue()));
        holder.comment.setText(NumberFormatter.formatNumber(controller.getComment().doubleValue()));
    }


    @Override
    public int getItemCount() {
        return dangChieulist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
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
