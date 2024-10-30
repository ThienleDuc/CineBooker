package com.example.cinebooker.PhanCongQuoc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.entity.ticketkhuhoiMoviesEntity;
import com.example.cinebooker.PhanCongQuoc.entity.ticketkhuhoiMoviesEntity;
import com.example.cinebooker.PhanCongQuoc.fragment.chitiethuyFragment;
import com.example.cinebooker.PhanCongQuoc.fragment.xemthongtinFragment;
import com.example.cinebooker.R;

import java.util.List;

public class Ticket_khuhoiAdapter extends RecyclerView.Adapter<Ticket_khuhoiAdapter.TicketViewHolder> {
    private List<ticketkhuhoiMoviesEntity> ticketkhuhoiMoviesList;
    private int currentItemCount; // Hiển thị ban đầu 10 mục

    public Ticket_khuhoiAdapter(List<ticketkhuhoiMoviesEntity> ticketkhuhoiMoviesList) {
        this.ticketkhuhoiMoviesList = ticketkhuhoiMoviesList;
        this.currentItemCount = 10;
    }

    public int getCurrentItemCount() {
        return currentItemCount;
    }

    public void setCurrentItemCount(int currentItemCount) {
        this.currentItemCount = currentItemCount;
    }

    public void updateItemCount(int count) {
        this.currentItemCount = count;
        notifyDataSetChanged(); // Cập nhật lại giao diện
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket_khuhoi, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        ticketkhuhoiMoviesEntity ticket = ticketkhuhoiMoviesList.get(position);

        // Gán giá trị từ ticketkhuhoiMoviesEntity
        holder.icon_khuhoi.setImageResource(ticket.getIcon_khuhoi());
        holder.date_khuhoi.setText(ticket.getDate_khuhoi());
        holder.date_1_khuhoi.setText(ticket.getDate_1_khuhoi());
        holder.poster_khuhoi.setImageResource(ticket.getPoster_khuhoi());
        holder.age_khuhoi.setText(ticket.getAge_khuhoi());
        holder.name_khuhoi.setText(ticket.getName_khuhoi());
        holder.style_khuhoi.setText(ticket.getStyle_khuhoi());
        holder.soluong_khuhoi.setText(String.valueOf(ticket.getSoluong_khuhoi()));
        holder.diachi_khuhoi.setText(ticket.getDiachi_khuhoi());
        holder.icon_rap_khuhoi.setImageResource(ticket.getIcon_rap_khuhoi());
        holder.btn_khuhoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy Activity chứa RecyclerView
                AppCompatActivity activity = (AppCompatActivity) v.getContext();

                // Tạo instance của fragment mới
                chitiethuyFragment fragment = new chitiethuyFragment();



                // Thay thế fragment
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment) // `fragment_container` là ID của ViewGroup chứa fragment
                        .addToBackStack(null) // Thêm vào back stack nếu cần
                        .commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return Math.min(currentItemCount, ticketkhuhoiMoviesList.size());
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder {
        TextView date_khuhoi;
        TextView date_1_khuhoi;
        ImageView poster_khuhoi; // Hình ảnh poster
        TextView age_khuhoi;
        TextView name_khuhoi;
        TextView style_khuhoi;
        TextView soluong_khuhoi;
        TextView diachi_khuhoi;
        ImageView icon_khuhoi; // Icon của đơn vị
        ImageView icon_rap_khuhoi; // Icon của rạp
        Button btn_khuhoi;

        public TicketViewHolder(View itemView) {
            super(itemView);
            date_khuhoi = itemView.findViewById(R.id.date_khuhoi);
            date_1_khuhoi = itemView.findViewById(R.id.date_1_khuhoi);
            poster_khuhoi = itemView.findViewById(R.id.poster_khuhoi);
            age_khuhoi = itemView.findViewById(R.id.age_khuhoi);
            name_khuhoi = itemView.findViewById(R.id.name_khuhoi);
            style_khuhoi = itemView.findViewById(R.id.style_khuhoi);
            soluong_khuhoi = itemView.findViewById(R.id.soluong_khuhoi);
            diachi_khuhoi = itemView.findViewById(R.id.diachi_khuhoi);
            icon_khuhoi = itemView.findViewById(R.id.icon17_khuhoi);
            icon_rap_khuhoi = itemView.findViewById(R.id.icon_rap_khuhoi);
            btn_khuhoi = itemView.findViewById(R.id.btn_khuhoi);

        }
    }

}

