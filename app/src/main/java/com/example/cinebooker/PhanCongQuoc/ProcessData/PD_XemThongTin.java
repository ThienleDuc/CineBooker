package com.example.cinebooker.PhanCongQuoc.ProcessData;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.adapter.XemThongTinAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.XemThongTinEntity;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ConnectionDatabase;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PD_XemThongTin {

    private static final String TAG = "PD_XemThongTin"; // Đặt tag cho log
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    private CallableStatement callableStatement = null;
    private ResultSet resultSet = null;

    // Phương thức lấy danh sách vé chưa sử dụng
    public List<XemThongTinEntity> getXemThongTin(int MaVe) {
        List<XemThongTinEntity> phimList = new ArrayList<>();
        try {
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return phimList;
            }

            String sql = "EXEC GetThongTinVePhim @MaVe = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, MaVe);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                XemThongTinEntity phim = new XemThongTinEntity();
               phim.setMaVe(resultSet.getInt("MaVe"));
                phim.setDateXemThongTin(resultSet.getString("ThoiGian"));
                phim.setPosterXemThongTin(resultSet.getString("AnhPhim"));
                phim.setAgeXemThongTin(resultSet.getInt("Tuoi"));
                phim.setNameXemThongTin(resultSet.getString("TenPhim"));
                phim.setStyleXemThongTin(resultSet.getString("TenTheLoai"));
                phim.setSoLuong(resultSet.getInt("SoLuongVe"));
                phim.setIconRap(resultSet.getString("AnhRapChieu"));
                phim.setDiaChi(resultSet.getString("DiaChiRapChieu"));
                phim.setTime_batdau(resultSet.getString("ThoiGianBatDau"));
                phim.setTime_ketthuc(resultSet.getString("ThoiGianKetThuc"));
                phim.setDateXemThongTin1(resultSet.getString("NgayChieu"));
                phim.setDinhDang(resultSet.getString("DinhDangPhim"));
                phim.setGheNgoi(resultSet.getInt("GheNgoi"));
                phim.setPhongChieu(resultSet.getInt("PhongChieu"));
                phim.setTrangThai(resultSet.getString("TinhTrang"));

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

    public void loadPhimToRecyclerView(Context context, RecyclerView recyclerView, List<XemThongTinEntity> list, XemThongTinAdapter adapter) {
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
