package com.example.cinebooker.LeDucThien.ProcessData;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cinebooker.LeDucThien.adapter.TimKiemAdapter;

import com.example.cinebooker.LeDucThien.entity.ent_TimKiemPhim;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ConnectionDatabase;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PD_TimKiemPhim {
    private static final String TAG = "PD_TimKiemPhim"; // Tag để log
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public List<ent_TimKiemPhim> getAllPhim() {
        List<ent_TimKiemPhim> phimList = new ArrayList<>();

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return phimList;
            }

            // Câu lệnh SQL gọi stored procedure pr_TimKiemPhim
            String sql = "EXEC pr_LayTatCaPhim";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Duyệt qua kết quả và thêm vào danh sách đối tượng ent_TimKiemPhim
            while (resultSet.next()) {
                int maPhim = resultSet.getInt("MaPhim");
                String anhPhim = resultSet.getString("AnhPhim");
                String tenPhimResult = resultSet.getString("TenPhim");
                int tuoi = resultSet.getInt("Tuoi");
                String dinhDangPhim = resultSet.getString("DinhDangPhim");
                String tenTheLoai = resultSet.getString("TenTheLoai");
                String ngayKhoiChieu = resultSet.getString("NgayKhoiChieu");
                String ngayKetThuc = resultSet.getString("NgayKetThuc");
                String trangThaiChieu = resultSet.getString("TrangThaiChieu");
                String thoiLuong = resultSet.getString("ThoiLuong");
                float diemDanhGiaTrungBinh = resultSet.getFloat("DiemDanhGiaTrungBinh");
                int tongLuotMuaPhim = resultSet.getInt("TongLuotMuaPhim");
                int tongLuotDanhGiaPhim = resultSet.getInt("TongLuotDanhGiaPhim");

                ent_TimKiemPhim phim = new ent_TimKiemPhim(maPhim, anhPhim, tenPhimResult, tuoi, dinhDangPhim,
                        tenTheLoai, ngayKhoiChieu, ngayKetThuc, trangThaiChieu,
                        thoiLuong, diemDanhGiaTrungBinh, tongLuotMuaPhim, tongLuotDanhGiaPhim);
                phimList.add(phim);
            }

        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching movie data from the database", e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error occurred", e);
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo đóng các tài nguyên
            closeResources();
        }

        return phimList;
    }

    // Phương thức gọi stored procedure pr_TimKiemPhim để lấy dữ liệu phim theo tên
    public List<ent_TimKiemPhim> getPhimByTen(String tenPhim) {
        List<ent_TimKiemPhim> phimList = new ArrayList<>();

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return phimList;
            }

            // Câu lệnh SQL gọi stored procedure pr_TimKiemPhim
            String sql = "EXEC pr_TimKiemPhim @TenPhim = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tenPhim);
            resultSet = preparedStatement.executeQuery();

            // Duyệt qua kết quả và thêm vào danh sách đối tượng ent_TimKiemPhim
            while (resultSet.next()) {
                int maPhim = resultSet.getInt("MaPhim");
                String anhPhim = resultSet.getString("AnhPhim");
                String tenPhimResult = resultSet.getString("TenPhim");
                int tuoi = resultSet.getInt("Tuoi");
                String dinhDangPhim = resultSet.getString("DinhDangPhim");
                String tenTheLoai = resultSet.getString("TenTheLoai");
                String ngayKhoiChieu = resultSet.getString("NgayKhoiChieu");
                String ngayKetThuc = resultSet.getString("NgayKetThuc");
                String trangThaiChieu = resultSet.getString("TrangThaiChieu");
                String thoiLuong = resultSet.getString("ThoiLuong");
                float diemDanhGiaTrungBinh = resultSet.getFloat("DiemDanhGiaTrungBinh");
                int tongLuotMuaPhim = resultSet.getInt("TongLuotMuaPhim");
                int tongLuotDanhGiaPhim = resultSet.getInt("TongLuotDanhGiaPhim");

                ent_TimKiemPhim phim = new ent_TimKiemPhim(maPhim, anhPhim, tenPhimResult, tuoi, dinhDangPhim,
                        tenTheLoai, ngayKhoiChieu, ngayKetThuc, trangThaiChieu,
                        thoiLuong, diemDanhGiaTrungBinh, tongLuotMuaPhim, tongLuotDanhGiaPhim);
                phimList.add(phim);
            }

        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching movie data from the database", e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error occurred", e);
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo đóng các tài nguyên
            closeResources();
        }

        return phimList;
    }

    // Phương thức đóng các tài nguyên
    private void closeResources() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error closing resources", e);
        }
    }

    // Phương thức load dữ liệu vào RecyclerView
    public void loadPhimToRecyclerView(Context context, RecyclerView recyclerView, List<ent_TimKiemPhim> list, TimKiemAdapter adapter) {
        // Thiết lập LayoutManager nếu chưa có
        if (recyclerView.getLayoutManager() == null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        }

        // Kiểm tra và thêm ItemDecoration nếu chưa được thêm
        boolean hasItemDecoration = false;
        for (int i = 0; i < recyclerView.getItemDecorationCount(); i++) {
            if (recyclerView.getItemDecorationAt(i) instanceof SpaceItemDecoration) {
                hasItemDecoration = true;
                break;
            }
        }

        if (!hasItemDecoration) {
            int spacingInPixels = context.getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
            recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        }

        // Thiết lập Adapter và dữ liệu nếu chưa có adapter
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(adapter);
        }

        adapter.SetData(list);
    }


}
