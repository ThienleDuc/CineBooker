package com.example.cinebooker.PhanCongQuoc.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.entity.YeuCauHoanTienEntity;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ConnectionDatabase;
import com.squareup.picasso.Picasso;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

public class YeuCauHoanTienAdapter extends RecyclerView.Adapter<YeuCauHoanTienAdapter.YeuCauHoanTienViewHolder> {

    private Context context;
    private int currentItemCount;
    private List<YeuCauHoanTienEntity> yeuCauHoanTienList;

    // Constructor
    public YeuCauHoanTienAdapter(Context context, List<YeuCauHoanTienEntity> yeuCauHoanTienList) {
        this.context = context;
        this.yeuCauHoanTienList = yeuCauHoanTienList;
        notifyDataSetChanged();
    }

    public void SetData(List<YeuCauHoanTienEntity> movieList) {
        this.currentItemCount = 1;
        this.yeuCauHoanTienList = movieList;
        notifyDataSetChanged();
    }

    @Override
    public YeuCauHoanTienViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hoantien, parent, false);
        return new YeuCauHoanTienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YeuCauHoanTienViewHolder holder, int position) {
        YeuCauHoanTienEntity item = yeuCauHoanTienList.get(position);
        String imageName = item.getPosterTrHang();
        Context context = holder.itemView.getContext();
        @SuppressLint("DiscouragedApi") int resourceId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId)
                .resize(800, 800)// Load resource ID
                .into(holder.posterTrHang);


        String imageName1 = item.getIconTrHang();

        @SuppressLint("DiscouragedApi") int resourceId1 = context.getResources().getIdentifier(imageName1, "drawable", context.getPackageName());

        Picasso.get()
                .load(resourceId1)
                .resize(800, 800)// Load resource ID
                .into(holder.iconTrHang);

        // Set dữ liệu cho các View
        holder.namePhimTrHang.setText(item.getNamePhimTrHang());
        holder.stylehoantien.setText(item.getStylehoantien());
        holder.dateTrHang.setText(item.getDateTrHang());
        holder.timebd.setText(item.getTime_batdau());
        holder.timekt.setText(item.getTime_ketthuc());
        holder.soLuongTrHang.setText(String.valueOf(item.getSoLuongTrHang()));

        holder.diaChiTrHang.setText(item.getDiaChiTrHang());
        holder.soTienHoanTrHang.setText(String.valueOf(item.getSoTienHoanTrHang()));

        holder.btn_ht.setOnClickListener(v -> {
            int maVe = item.getMaVe(); // Lấy mã vé từ mục hiện tại

            // Gọi stored procedure để cập nhật trạng thái vé
            chuyenTinhTrangVeKhuhHoi(maVe);

            // Hiển thị thông báo
            Toast.makeText(context, "Đã chuyển trạng thái vé hoàn thành!", Toast.LENGTH_SHORT).show();

            // Quay lại fragment trước đó
            ((AppCompatActivity) context).onBackPressed(); // Quay lại fragment trước đó
        });
        // Show a dialog with 3 reasons when the "lydo" button is clicked
        holder.lydo.setOnClickListener(v -> {
            // Show dialog with the reasons
            String[] reasons = {"Vé bị lỗi", "Không có thời gian xem", "Lý do khác"};
            new android.app.AlertDialog.Builder(context)
                    .setTitle("Chọn lý do")
                    .setItems(reasons, (dialog, which) -> {
                        // Get the selected reason
                        String selectedReason = reasons[which];

                        // Display the selected reason in a TextView
                        holder.phuongan.setText(selectedReason); // Display the reason in the TextView
                    })
                    .setCancelable(true)
                    .show();
        });

    }

    @Override
    public int getItemCount() {
        return Math.min(currentItemCount, yeuCauHoanTienList.size());
    }

    // Phương thức xử lý stored procedure
    private void chuyenTinhTrangVeKhuhHoi(int maVe) {
        ConnectionDatabase connectionDatabase = new ConnectionDatabase();
        Connection connection = null;
        CallableStatement callableStatement = null;

        try {
            connection = connectionDatabase.getConnection();
            if (connection != null) {
                String sql = "{call ChuyenTinhTrangVeKhuhHoi(?)}"; // Gọi stored procedure
                callableStatement = connection.prepareCall(sql);
                callableStatement.setInt(1, maVe); // Gắn tham số "MaVe" vào stored procedure
                callableStatement.execute(); // Thực thi stored procedure
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và statement
            ConnectionDatabase.closeCallableStatement(callableStatement);
            ConnectionDatabase.closeConnection(connection);
        }
    }

    // Lớp ViewHolder
    public static class YeuCauHoanTienViewHolder extends RecyclerView.ViewHolder {
        ImageView posterTrHang, iconTrHang,lydo;
        TextView namePhimTrHang,stylehoantien, dateTrHang, timebd, timekt, soLuongTrHang, diaChiTrHang, soTienHoanTrHang,phuongan;
        Button btn_ht;

        public YeuCauHoanTienViewHolder(View itemView) {
            super(itemView);
            // Ánh xạ các View
            posterTrHang = itemView.findViewById(R.id.poster_trhang);
            namePhimTrHang = itemView.findViewById(R.id.namephim_trhang);
            stylehoantien = itemView.findViewById(R.id.style_hoantien);
            dateTrHang = itemView.findViewById(R.id.date_trhang);
            timebd = itemView.findViewById(R.id.time_batdau_trh);
            timekt = itemView.findViewById(R.id.time_ketthuc_trh);
            soLuongTrHang = itemView.findViewById(R.id.soluong_trhang);
            iconTrHang = itemView.findViewById(R.id.icon_trhang);
            diaChiTrHang = itemView.findViewById(R.id.diachi_trhang);
            soTienHoanTrHang = itemView.findViewById(R.id.sotienhoan_trhang);
            btn_ht = itemView.findViewById(R.id.btn_ht);
            lydo = itemView.findViewById(R.id.lydo);
            phuongan = itemView.findViewById(R.id.phuongan);
        }
    }
}