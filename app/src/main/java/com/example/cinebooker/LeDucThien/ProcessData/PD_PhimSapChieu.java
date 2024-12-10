package com.example.cinebooker.LeDucThien.ProcessData;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.adapter.moviesSapChieuAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_PhimSapChieu;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ConnectionDatabase;
import com.example.cinebooker.generalMethod.HorizontalSpaceItemDecoration;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PD_PhimSapChieu {

    private static final String TAG = "PD_PhimSapChieu"; // Đặt tag cho log
    private Connection connection = null;
    private CallableStatement callableStatement = null;
    private ResultSet resultSet = null;

    // Phương thức lấy danh sách phim sắp chiếu
    public List<ent_PhimSapChieu> getPhimSapChieu() {
        List<ent_PhimSapChieu> phimList = new ArrayList<>();

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return phimList; // Nếu kết nối không thành công, trả về danh sách rỗng
            }

            // Tạo CallableStatement và ResultSet
            callableStatement = connection.prepareCall("{call pr_LayPhimSapChieu}");
            resultSet = callableStatement.executeQuery();

            // Duyệt qua kết quả trả về và lưu vào List<ent_PhimSapChieu>
            while (resultSet.next()) {
                ent_PhimSapChieu phim = new ent_PhimSapChieu();

                phim.setMaPhim(resultSet.getInt("MaPhim"));
                phim.setAnhPhim(resultSet.getString("AnhPhim"));
                phim.setTenPhim(resultSet.getString("TenPhim"));
                phim.setTuoi(resultSet.getInt("Tuoi"));
                phim.setDinhDangPhim(resultSet.getString("DinhDangPhim"));
                phim.setTenTheLoai(resultSet.getString("TenTheLoai"));
                phim.setNgayKhoiChieu(resultSet.getString("NgayKhoiChieu"));
                phim.setNgayKetThuc(resultSet.getString("NgayKetThuc"));
                phim.setTrangThaiChieu(resultSet.getString("TrangThaiChieu"));
                phim.setThoiLuong(resultSet.getString("ThoiLuong"));

                // Thêm đối tượng phim vào danh sách
                phimList.add(phim);
            }

        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching upcoming movies data from the database", e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error occurred", e);
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo rằng các tài nguyên luôn được đóng
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
            if (callableStatement != null) {
                callableStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error closing resources", e);
        }
    }

    // Phương thức load dữ liệu vào RecyclerView
    public void loadMoviesToRecyclerView(Context context, RecyclerView recyclerView, List<ent_PhimSapChieu> movieList, boolean isHorizontal) {
        if (movieList == null || movieList.isEmpty()) {
            Log.e(TAG, "Danh sách phim trống hoặc không hợp lệ.");
            return;
        }

        // Thiết lập LayoutManager cho RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(isHorizontal ? LinearLayoutManager.HORIZONTAL : LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // Thêm ItemDecoration nếu cần
        int spacingInPixels = context.getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        if (isHorizontal) {
            recyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(spacingInPixels));
        } else {
            recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
        }

        // Tạo adapter và gán vào RecyclerView
        moviesSapChieuAdapter adapter = new moviesSapChieuAdapter();
        recyclerView.setAdapter(adapter);
        adapter.SetData(movieList);
    }
}
