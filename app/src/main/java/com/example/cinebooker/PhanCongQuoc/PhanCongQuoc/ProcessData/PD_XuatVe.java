package com.example.cinebooker.PhanCongQuoc.PhanCongQuoc.ProcessData;

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
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class PD_XuatVe {

    private static final String TAG = "PD_XuatVe"; // Đặt tag cho log
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    private CallableStatement callableStatement = null;
    private ResultSet resultSet = null;

    // Phương thức lấy danh sách vé chưa sử dụng
    public List<xuatveEntity> getXuatVe(int MaVe) {
        List<xuatveEntity> phimList = new ArrayList<>();
        try {
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return phimList;
            }

            String sql = "EXEC GetVePhimDadung @MaVe = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, MaVe);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                xuatveEntity phim = new xuatveEntity();
               phim.setMaVe(resultSet.getInt("MaVe"));
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

    public void loadPhimToRecyclerView(Context context, RecyclerView recyclerView, List<xuatveEntity> list, XuatVeAdapter adapter) {
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
