package com.example.cinebooker.LeDucThien.ProcessData;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.adapter.moviesDangChieuAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_PhimDangChieu;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ConnectionDatabase;
import com.example.cinebooker.generalMethod.HorizontalSpaceItemDecoration;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PD_PhimDangChieu {

    // Phương thức lấy danh sách phim đang chiếu
    public List<ent_PhimDangChieu> getPhimDangChieu() {
        List<ent_PhimDangChieu> phimList = new ArrayList<>();

        // Sử dụng try-with-resources để tự động đóng tài nguyên
        try (Connection connection = ConnectionDatabase.getConnection();
             CallableStatement callableStatement = connection.prepareCall("{call pr_LayPhimDangChieu}");
             ResultSet resultSet = callableStatement.executeQuery()) {

            // Duyệt qua kết quả trả về và lưu vào List
            while (resultSet.next()) {
                ent_PhimDangChieu phim = new ent_PhimDangChieu();

                phim.setMaPhim(resultSet.getInt("MaPhim"));
                phim.setAnhPhim(resultSet.getString("AnhPhim"));
                phim.setTenPhim(resultSet.getString("TenPhim"));
                phim.setTuoi(resultSet.getInt("Tuoi"));
                phim.setDinhDangPhim(resultSet.getString("DinhDangPhim"));
                phim.setTenTheLoai(resultSet.getString("TenTheLoai"));

                // Lấy ngày khởi chiếu và ngày kết thúc dưới dạng Date
                java.sql.Date ngayKhoiChieu = resultSet.getDate("NgayKhoiChieu");
                if (ngayKhoiChieu != null) {
                    phim.setNgayKhoiChieu(new java.util.Date(ngayKhoiChieu.getTime()));
                }

                java.sql.Date ngayKetThuc = resultSet.getDate("NgayKetThuc");
                if (ngayKetThuc != null) {
                    phim.setNgayKetThuc(new java.util.Date(ngayKetThuc.getTime()));
                }

                phim.setTrangThaiChieu(resultSet.getString("TrangThaiChieu"));
                phim.setThoiLuong(resultSet.getString("ThoiLuong"));

                // Lấy điểm đánh giá trung bình từ kết quả SQL
                phim.setDiemDanhGiaTrungBinh(resultSet.getFloat("DiemDanhGiaTrungBinh"));

                // Thêm đối tượng vào danh sách
                phimList.add(phim);
            }

        } catch (SQLException e) {
            Log.e("DatabaseError", "Error when fetching movies data from the database", e);
        }

        return phimList;
    }

    public void loadMoviesToRecyclerViewVertical(Context context, RecyclerView recyclerView, List<ent_PhimDangChieu> movieList) {
        // Thiết lập LayoutManager cho RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));

        // Thêm ItemDecoration để tạo khoảng cách giữa các item
        int spacingInPixels = context.getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        // Tạo adapter với danh sách phim và thiết lập vào RecyclerView
        moviesDangChieuAdapter adapter = new moviesDangChieuAdapter(movieList);
        recyclerView.setAdapter(adapter);
    }

    public void loadMoviesToRecyclerViewHorizontal(Context context, RecyclerView recyclerView, List<ent_PhimDangChieu> movieList) {
        // Thiết lập LayoutManager cho RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        // Thêm ItemDecoration để tạo khoảng cách giữa các item
        int spacingInPixels = context.getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_7_5);
        recyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(spacingInPixels));

        // Tạo adapter với danh sách phim và thiết lập vào RecyclerView
        moviesDangChieuAdapter adapter = new moviesDangChieuAdapter(movieList);
        recyclerView.setAdapter(adapter);
    }
}
