package com.example.cinebooker.LeDucThien.ProcessData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cinebooker.LeDucThien.adapter.ThoiGianChieuAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_ThoiGianChieu;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ConnectionDatabase;
import com.example.cinebooker.generalMethod.HorizontalSpaceItemDecoration;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PD_ThoiGianChieu {
    private static final String TAG = "PD_ThoiGianChieu";

    // Phương thức mở kết nối
    private Connection getConnection() throws SQLException {
        Connection connection = new ConnectionDatabase().getConnection();
        if (connection == null) {
            throw new SQLException("Không thể kết nối đến cơ sở dữ liệu.");
        }
        return connection;
    }

    // Phương thức lấy thời gian bắt đầu và kết thúc của phim theo mã phim, rạp chiếu con, và thời gian chiếu
    public List<ent_ThoiGianChieu> getThoiGianBatDauKetThucTheoMaPhimVaRapChieuCon(int maPhim, int maRapChieuCon, int maThoiGianChieu) {
        List<ent_ThoiGianChieu> thoiGianList = new ArrayList<>();
        String sql = "EXEC pr_LayThoiGianBatDauKetThucTheoMaPhimVaRapChieuCon ?, ?, ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, maPhim);
            preparedStatement.setInt(2, maRapChieuCon);
            preparedStatement.setInt(3, maThoiGianChieu);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int maPhimResult = resultSet.getInt("MaPhim");
                    String thoiGianBatDau = resultSet.getString("ThoiGianBatDau");
                    String thoiGianKetThuc = resultSet.getString("ThoiGianKetThuc");

                    ent_ThoiGianChieu thoiGian = new ent_ThoiGianChieu(maPhimResult, thoiGianBatDau, thoiGianKetThuc);
                    thoiGianList.add(thoiGian);
                }
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching showtime data from the database", e);
        }
        return thoiGianList;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void loadThoiGianToRecyclerView(Context context, RecyclerView recyclerView, List<ent_ThoiGianChieu> list, ThoiGianChieuAdapter adapter) {
        if (recyclerView.getLayoutManager() == null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
        }

        // Kiểm tra và thêm ItemDecoration nếu chưa được thêm
        boolean hasItemDecoration = false;
        for (int i = 0; i < recyclerView.getItemDecorationCount(); i++) {
            if (recyclerView.getItemDecorationAt(i) instanceof HorizontalSpaceItemDecoration) {
                hasItemDecoration = true;
                break;
            }
        }

        if (!hasItemDecoration) {
            int spacingInPixels = context.getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
            recyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(spacingInPixels));
        }

        // Thiết lập Adapter và dữ liệu nếu chưa có adapter
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(adapter);
        }

        adapter.setData(list);
    }
}
