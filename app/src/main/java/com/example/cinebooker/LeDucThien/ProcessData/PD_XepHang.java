package com.example.cinebooker.LeDucThien.ProcessData;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.adapter.XepHangAdapter;
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

public class PD_XepHang {
    private static final String TAG = "PD_XepHang";

    // Lấy danh sách phim theo ngày
    public List<ent_XepHang> getPhimXepHangTheoNgay() {
        return getPhimXepHang("pr_LayThongTinPhimXepHangTheoNgay");
    }

    // Lấy danh sách phim theo tuần
    public List<ent_XepHang> getPhimXepHangTheoTuan() {
        return getPhimXepHang("pr_LayThongTinPhimXepHangTheoTuan");
    }

    // Lấy danh sách phim theo tháng
    public List<ent_XepHang> getPhimXepHangTheoThang() {
        return getPhimXepHang("pr_LayThongTinPhimXepHangTheoThang");
    }

    // Hàm chung để lấy danh sách phim xếp hạng
    private List<ent_XepHang> getPhimXepHang(String procedureName) {
        List<ent_XepHang> phimXepHangList = new ArrayList<>();
        try (Connection connection = new ConnectionDatabase().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("EXEC " + procedureName);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return phimXepHangList;
            }

            while (resultSet.next()) {
                ent_XepHang phim = new ent_XepHang(
                        resultSet.getInt("MaPhim"),
                        resultSet.getString("AnhPhim"),
                        resultSet.getString("TenPhim"),
                        resultSet.getInt("Tuoi"),
                        resultSet.getString("DinhDangPhim"),
                        resultSet.getString("TenTheLoai"),
                        resultSet.getFloat("DiemDanhGiaTrungBinh"),
                        resultSet.getInt("TongLuotMuaPhim"),
                        resultSet.getInt("TongDanhGiaPhim"),
                        resultSet.getFloat("DiemXepHang")
                );
                phimXepHangList.add(phim);
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching ranked movies data", e);
        }

        return phimXepHangList;
    }

    // Load dữ liệu vào RecyclerView
    public void loadXepHangToRecyclerView(Context context, RecyclerView recyclerView, List<ent_XepHang> list, XepHangAdapter adapter) {
        if (list == null || list.isEmpty()) {
            Log.e(TAG, "Danh sách xếp hạng trống hoặc không hợp lệ.");
            return;
        }

        // Thiết lập LayoutManager cho RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // Thêm ItemDecoration nếu cần
        int spacingInPixels = context.getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        // Thiết lập Adapter và dữ liệu nếu chưa có adapter
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(adapter);
        }

        // Cập nhật dữ liệu cho adapter
        adapter.SetData(list);
    }

}
