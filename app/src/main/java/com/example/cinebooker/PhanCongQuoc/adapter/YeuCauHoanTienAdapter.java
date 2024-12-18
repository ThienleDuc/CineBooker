package com.example.cinebooker.PhanCongQuoc.adapter;

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
    private List<YeuCauHoanTienEntity> yeuCauHoanTienList;

    // Constructor
    public YeuCauHoanTienAdapter(Context context, List<YeuCauHoanTienEntity> yeuCauHoanTienList) {
        this.context = context;
        this.yeuCauHoanTienList = yeuCauHoanTienList;
        notifyDataSetChanged();
    }

    public void SetData(List<YeuCauHoanTienEntity> movieList) {

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

        // Set dữ liệu cho các View
        Picasso.get().load(item.getPosterTrHang())
                .placeholder(R.drawable.placeholder)
                .resize(800, 800) // Ảnh chờ nếu không tải được
                .into(holder.posterTrHang);
        holder.namePhimTrHang.setText(item.getNamePhimTrHang());
        holder.dateTrHang.setText(item.getDateTrHang());
        holder.timebd.setText(item.getTime_batdau());
        holder.timekt.setText(item.getTime_ketthuc());
        holder.soLuongTrHang.setText(String.valueOf(item.getSoLuongTrHang()));
        Picasso.get().load(item.getIconTrHang())
                .placeholder(R.drawable.drawn_star)  // Ảnh chờ cho biểu tượng
                .into(holder.iconTrHang);
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

    }

    @Override
    public int getItemCount() {
        return yeuCauHoanTienList != null ? yeuCauHoanTienList.size() : 0;
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
        ImageView posterTrHang, iconTrHang;
        TextView namePhimTrHang, dateTrHang, timebd, timekt, soLuongTrHang, diaChiTrHang, soTienHoanTrHang;
        Button btn_ht;

        public YeuCauHoanTienViewHolder(View itemView) {
            super(itemView);
            // Ánh xạ các View
            posterTrHang = itemView.findViewById(R.id.poster_trhang);
            namePhimTrHang = itemView.findViewById(R.id.namephim_trhang);
            dateTrHang = itemView.findViewById(R.id.date_trhang);
            timebd = itemView.findViewById(R.id.time_batdau_trh);
            timekt = itemView.findViewById(R.id.time_ketthuc_trh);
            soLuongTrHang = itemView.findViewById(R.id.soluong_trhang);
            iconTrHang = itemView.findViewById(R.id.icon_trhang);
            diaChiTrHang = itemView.findViewById(R.id.diachi_trhang);
            soTienHoanTrHang = itemView.findViewById(R.id.sotienhoan_trhang);
            btn_ht = itemView.findViewById(R.id.btn_ht);
        }
    }
}
