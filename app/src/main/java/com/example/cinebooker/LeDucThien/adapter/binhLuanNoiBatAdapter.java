package com.example.cinebooker.LeDucThien.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.entity.binhLuanNoiBatEntity;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.NumberFormatter;

import java.util.List;

public class binhLuanNoiBatAdapter extends RecyclerView.Adapter<binhLuanNoiBatAdapter.viewHolder> {

    private List<binhLuanNoiBatEntity> dangChieulist;

    public binhLuanNoiBatAdapter() {

    }

    public binhLuanNoiBatAdapter(List<binhLuanNoiBatEntity> dangChieulist) {
        this.dangChieulist = dangChieulist;
    }

    @NonNull
    @Override
    public binhLuanNoiBatAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies_binhluan, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull binhLuanNoiBatAdapter.viewHolder holder, int position) {
        binhLuanNoiBatEntity dangChieu = dangChieulist.get(position);

        holder.moviePoster.setImageResource(dangChieu.getMoviePoster());

        holder.age.setText(dangChieu.getAge());
        holder.movieName.setText(dangChieu.getMovieName());
        holder.styleMovie.setText(dangChieu.getStyleMovie());

        holder.vote.setText(NumberFormatter.formatNumber(dangChieu.getVote().doubleValue()));
        holder.shopping.setText(NumberFormatter.formatNumber(dangChieu.getShopping().doubleValue()));
        holder.comment.setText(NumberFormatter.formatNumber(dangChieu.getComment().doubleValue()));

        holder.avatar.setImageResource(dangChieu.getAvatar());
        holder.userName.setText(dangChieu.getUserName());
        holder.day.setText(dangChieu.getDay());
        holder.userComment.setText(dangChieu.getUserComment());
    }

    @Override
    public int getItemCount() {
        return dangChieulist.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster, avatar;
        TextView age, movieName, styleMovie, vote, shopping, comment, userName, day, userComment;
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

        }
    }
}
