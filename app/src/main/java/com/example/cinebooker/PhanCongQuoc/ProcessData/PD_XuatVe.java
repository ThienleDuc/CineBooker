package com.example.cinebooker.PhanCongQuoc.ProcessData;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_chuadungAdapter;
import com.example.cinebooker.PhanCongQuoc.adapter.XuatVeAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.xuatveEntity;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ConnectionDatabase;
import com.example.cinebooker.generalMethod.HorizontalSpaceItemDecoration;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PD_XuatVe {

    private static final String TAG = "PD_XuatVe"; // Đặt tag cho log
    private Connection connection = null;
    private CallableStatement callableStatement = null;
    private ResultSet resultSet = null;

    // Phương thức lấy danh sách vé chưa sử dụng
    public List<xuatveEntity> getXuatVe(int maVe) {
        List<xuatveEntity> phimList = new ArrayList<>();
        try {
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return phimList;
            }

            callableStatement = connection.prepareCall("{call GetVePhimDadung(?)}");
            callableStatement.setInt(1, maVe); // Truyền MaVe vào thủ tục

            resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                xuatveEntity phim = new xuatveEntity();
                // Gán giá trị cho các thuộc tính của đối tượng phim
                phim.setQrXuatVe(resultSet.getString("QRThanhToan"));
                phim.setDateXuatVe1(resultSet.getString("ThoiGian"));
                phim.setPosterXuatVe2(resultSet.getString("AnhPhim"));
                phim.setAgeXuatVe(resultSet.getInt("Tuoi"));
                phim.setNameXuatVe(resultSet.getString("TenPhim"));
                phim.setStyleXuatVe(resultSet.getString("TenTheLoai"));
                phim.setSoLuongXuatVe(resultSet.getInt("SoLuongVe"));
                phim.setIconRapXuatVe(resultSet.getString("AnhRapChieu"));
                phim.setDiaChiXuatVe(resultSet.getString("DiaChiRapChieu"));
                phim.setTime_batdau(resultSet.getString("ThoiGianBatDau"));
                phim.setTime_ketthuc(resultSet.getString("ThoiGianKetThuc"));
                phim.setDateXuatVe2(resultSet.getString("NgayChieu"));
                phim.setDinhDangXuatPhim(resultSet.getString("DinhDangPhim"));
                phim.setGheXuatVe(resultSet.getInt("GheNgoi"));
                phim.setPhongXuatVe(resultSet.getInt("PhongChieu"));
                phim.setTrangThaiXuatVe(resultSet.getString("TinhTrang"));

                phimList.add(phim);
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching movie data from the database", e);
        } finally {
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

    public void loadMoviesToRecyclerView(Context context, RecyclerView recyclerView, List<xuatveEntity> movieList, boolean isHorizontal) {
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
        XuatVeAdapter adapter = new XuatVeAdapter();
        recyclerView.setAdapter(adapter);
        adapter.SetData(movieList); // Đưa danh sách phim vào adapter
    }

}
