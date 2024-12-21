package com.example.cinebooker.PhanCongQuoc.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.entity.ticketcapbacMoviesEntity;
import com.example.cinebooker.PhanCongQuoc.entity.ticketcapbacMoviesEntity;
import com.example.cinebooker.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Ticket_capbacAdapter extends RecyclerView.Adapter<Ticket_capbacAdapter.TicketViewHolder> {
    private List<ticketcapbacMoviesEntity> ticketcapbacMoviesList;
    private int currentItemCount; // Hiển thị ban đầu 10 mục

    public void SetData(List<ticketcapbacMoviesEntity> ticketcapbacMoviesList) {
        this.ticketcapbacMoviesList = ticketcapbacMoviesList;
        this.currentItemCount = 30;
        notifyDataSetChanged();
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket_capbac, parent, false);
        return new TicketViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        ticketcapbacMoviesEntity ticket = ticketcapbacMoviesList.get(position);

        holder.namedonvi_capbac.setText(ticket.getNamedonvi_capbac());

        String imageName = ticket.getIcondonvi_capbac();
        Context context = holder.itemView.getContext();
        @SuppressLint("DiscouragedApi") int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId)
                .resize(800, 800)// Load resource ID
                .into(holder.icondonvi_capbac);

        holder.date_capbac.setText(ticket.getDate_capbac());
        holder.dadung_capbac.setText(ticket.getDadung_capbac()+" lượt");

        // Cập nhật mức giảm
        String mucgiam = ticket.getMucgiam_capbac(); // Lấy mức giảm
        if (mucgiam != null) {
            // Kiểm tra nếu mức giảm có 2 ký tự
            if (mucgiam.length() == 2) {
                // Nếu có 2 ký tự thì hiển thị "Giảm [Mức giảm]%"
                holder.mucgiam_capbac.setText("Giảm " + mucgiam + "%");
            } else if (mucgiam.length() > 2) {
                // Nếu có hơn 2 ký tự, thêm "K" vào mức giảm và hiển thị số lượng tối đa
                holder.mucgiam_capbac.setText("Giảm " + mucgiam + "VND");
            } else {
                // Nếu không có mức giảm hợp lệ, có thể để trống hoặc hiển thị một thông báo khác
                holder.mucgiam_capbac.setText("Không có giảm giá");
            }
        } else {
            // Nếu không có mức giảm
            holder.mucgiam_capbac.setText("Không có giảm giá");
        }

        // Hiển thị số lượng tối đa
        String gioihangiam = ticket.getGioihangiam_capbac();
        if (gioihangiam != null) {
            // Hiển thị số lượng tối đa
            holder.gioihangiam_capbac.setText("Số lượng tối đa: " + gioihangiam +" lượt");
        } else {
            // Nếu không có số lượng tối đa
            holder.gioihangiam_capbac.setText("Số lượng tối đa: N/A");
        }

        // Không cần gán btn_capbac nữa
    }

    @Override
    public int getItemCount() {
        return Math.min(currentItemCount, ticketcapbacMoviesList.size());
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder {
        TextView namedonvi_capbac;
        ImageView icondonvi_capbac; // Giả sử poster là một icon
        TextView mucgiam_capbac;
        TextView gioihangiam_capbac;
        TextView date_capbac,dadung_capbac;
      

        public TicketViewHolder(View itemView) {
            super(itemView);
            namedonvi_capbac = itemView.findViewById(R.id.namedonvi_capbac);
            icondonvi_capbac = itemView.findViewById(R.id.icondonvi_capbac);
            mucgiam_capbac = itemView.findViewById(R.id.mucgiam_capbac);
            gioihangiam_capbac = itemView.findViewById(R.id.gioihangiam_capbac);
            date_capbac = itemView.findViewById(R.id.date_capbac);
            dadung_capbac = itemView.findViewById(R.id.dadung_capbac);
             // Nếu có
            // Không cần btn_capbac nữa
        }
    }
}
