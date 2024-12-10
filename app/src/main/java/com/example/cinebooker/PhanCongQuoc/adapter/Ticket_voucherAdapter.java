package com.example.cinebooker.PhanCongQuoc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.entity.ticketvoucherMoviesEntity;
import com.example.cinebooker.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Ticket_voucherAdapter extends RecyclerView.Adapter<Ticket_voucherAdapter.TicketViewHolder> {
    private List<ticketvoucherMoviesEntity> ticketvoucherMoviesList;
    private int currentItemCount; // Hiển thị ban đầu 10 mục

    public void SetData(List<ticketvoucherMoviesEntity> ticketvoucherMoviesList) {
        this.ticketvoucherMoviesList = ticketvoucherMoviesList;
        this.currentItemCount = 10;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket_voucher, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        ticketvoucherMoviesEntity ticket = ticketvoucherMoviesList.get(position);

        // Gán giá trị từ ticketvoucherMoviesEntity
        holder.namedonvi_voucher.setText(ticket.getNamedonvi_voucher());
        Picasso.get().load(ticket.getIcondonvi_voucher())
                .placeholder(R.drawable.drawn_star)  // Optional: Add a placeholder image
                .into(holder.icondonvi_voucher);
        holder.mucgiam_voucher.setText(ticket.getMucgiam_voucher());
        holder.gioihangiam_voucher.setText(ticket.getGioihangiam_voucher());
        holder.date_voucher.setText(ticket.getDate_voucher());
        holder.dadung_voucher.setText(ticket.getDadung_voucher());
        // Không cần gán btn_voucher nữa
    }

    @Override
    public int getItemCount() {
        return Math.min(currentItemCount, ticketvoucherMoviesList.size());
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder {
        TextView namedonvi_voucher;
        ImageView icondonvi_voucher; // Giả sử poster là một icon
        TextView mucgiam_voucher;
        TextView gioihangiam_voucher;
        TextView date_voucher;
        TextView dadung_voucher; // Nếu cần hiển thị trạng thái đã dùng

        public TicketViewHolder(View itemView) {
            super(itemView);
            namedonvi_voucher = itemView.findViewById(R.id.namedonvi_voucher);
            icondonvi_voucher = itemView.findViewById(R.id.icondonvi_voucher);
            mucgiam_voucher = itemView.findViewById(R.id.mucgiam_voucher);
            gioihangiam_voucher = itemView.findViewById(R.id.gioihangiam_voucher);
            date_voucher = itemView.findViewById(R.id.date_voucher);
            dadung_voucher = itemView.findViewById(R.id.dadung_voucher); // Nếu có
            // Không cần btn_voucher nữa
        }
    }
}
