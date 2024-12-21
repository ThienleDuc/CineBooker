package com.example.cinebooker.LeDucThien.ProcessData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cinebooker.LeDucThien.adapter.PhimTheoLichChieuAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_XepHang;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ConnectionDatabase;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PD_PhimTheoLichChieu {
    private static final String TAG = "PD_PhimTheoLichChieu";

    // Phương thức mở kết nối
    private Connection getConnection() throws SQLException {
        Connection connection = new ConnectionDatabase().getConnection();
        if (connection == null) {
            throw new SQLException("Không thể kết nối đến cơ sở dữ liệu.");
        }
        return connection;
    }

    // Phương thức lấy thông tin phim theo ngày chiếu và rạp chiếu con
    public List<ent_XepHang> getPhimTheoNgayChieuRapChieuCon(int maRapChieuCon, int maThoiGianChieu) {
        List<ent_XepHang> phimList = new ArrayList<>();
        String sql = "EXEC pr_LayThongTinPhimTheoNgayChieuRapChieuCon ?, ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, maRapChieuCon);
            preparedStatement.setInt(2, maThoiGianChieu);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int maPhim = resultSet.getInt("MaPhim");
                    String anhPhim = resultSet.getString("AnhPhim");
                    String tenPhim = resultSet.getString("TenPhim");
                    int tuoi = resultSet.getInt("Tuoi");
                    String tenTheLoai = resultSet.getString("TenTheLoai");
                    String dinhDangPhim = resultSet.getString("DinhDangPhim");
                    float diemDanhGiaTrungBinh = resultSet.getFloat("DiemDanhGiaTrungBinh");
                    int tongLuotMuaPhim = resultSet.getInt("TongLuotMuaPhim");
                    int tongDanhGiaPhim = resultSet.getInt("TongDanhGiaPhim");
                    float diemXepHang = resultSet.getFloat("DiemXepHang");

                    ent_XepHang phim = new ent_XepHang(maPhim, anhPhim, tenPhim, tuoi, tenTheLoai, dinhDangPhim,
                            diemDanhGiaTrungBinh, tongLuotMuaPhim, tongDanhGiaPhim, diemXepHang);
                    phimList.add(phim);
                }
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching movie data from the database", e);
        }
        return phimList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void loadPhimToRecyclerView(Context context, RecyclerView recyclerView, List<ent_XepHang> list, PhimTheoLichChieuAdapter adapter) {
        if (list == null || list.isEmpty()) {
            Log.e(TAG, "Danh sách xếp hạng trống hoặc không hợp lệ.");
            return;
        }

        // Thiết lập LayoutManager cho RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        // Thêm ItemDecoration
        int spacingInPixels = context.getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        // Thiết lập Adapter và cập nhật dữ liệu
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(adapter);
        }
        adapter.setData(list);
    }
}
