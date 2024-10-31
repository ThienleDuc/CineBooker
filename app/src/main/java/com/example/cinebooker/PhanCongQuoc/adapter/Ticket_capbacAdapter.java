package com.example.cinebooker.PhanCongQuoc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.entity.ticketcapbacMoviesEntity;
import com.example.cinebooker.R;

import java.util.List;

public class Ticket_capbacAdapter extends RecyclerView.Adapter<Ticket_capbacAdapter.TicketViewHolder> {
    private List<ticketcapbacMoviesEntity> ticketcapbacMoviesList;
    private int currentItemCount; // Hiển thị ban đầu 10 mục

    public Ticket_capbacAdapter(List<ticketcapbacMoviesEntity> ticketcapbacMoviesList) {
        this.ticketcapbacMoviesList = ticketcapbacMoviesList;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket_capbac, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        ticketcapbacMoviesEntity ticket = ticketcapbacMoviesList.get(position);

        holder.namedonvi_capbac.setText(ticket.getNamedonvi_capbac());
        holder.icondonvi_capbac.setImageResource(ticket.getIcondonvi_capbac());
        holder.mucgiam_capbac.setText(ticket.getMucgiam_capbac());
        holder.gioihangiam_capbac.setText(ticket.getGioihangiam_capbac());
        holder.date_capbac.setText(ticket.getDate_capbac());

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
        TextView date_capbac;
        TextView dadung_capbac; // Nếu cần hiển thị trạng thái đã dùng

        public TicketViewHolder(View itemView) {
            super(itemView);
            namedonvi_capbac = itemView.findViewById(R.id.namedonvi_capbac);
            icondonvi_capbac = itemView.findViewById(R.id.icondonvi_capbac);
            mucgiam_capbac = itemView.findViewById(R.id.mucgiam_capbac);
            gioihangiam_capbac = itemView.findViewById(R.id.gioihangiam_capbac);
            date_capbac = itemView.findViewById(R.id.date_capbac);
             // Nếu có
            // Không cần btn_capbac nữa
        }
    }
}
