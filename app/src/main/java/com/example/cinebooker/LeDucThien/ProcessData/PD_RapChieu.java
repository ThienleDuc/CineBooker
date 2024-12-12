package com.example.cinebooker.LeDucThien.ProcessData;


import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.adapter.RapChieuAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_RapChieu;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ConnectionDatabase;
import com.example.cinebooker.generalMethod.HorizontalSpaceItemDecoration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PD_RapChieu {
    private static final String TAG = "PD_RapChieu"; // Tag để log
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public List<ent_RapChieu> getAllRapChieu() {
        List<ent_RapChieu> rapChieuList = new ArrayList<>();

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return rapChieuList;
            }

            // Câu lệnh SQL để lấy toàn bộ thông tin từ bảng RapChieu
            String sql = "SELECT MaRapChieu, AnhRapChieu, TenRapChieu, MoTaRapChieu FROM RapChieu";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Duyệt qua kết quả và thêm vào danh sách đối tượng ent_RapChieu
            while (resultSet.next()) {
                int maRapChieu = resultSet.getInt("MaRapChieu");
                String anhRapChieu = resultSet.getString("AnhRapChieu");
                String tenRapChieu = resultSet.getString("TenRapChieu");
                String moTaRapChieu = resultSet.getString("MoTaRapChieu");

                ent_RapChieu rapChieu = new ent_RapChieu(maRapChieu, anhRapChieu, tenRapChieu, moTaRapChieu);
                rapChieuList.add(rapChieu);
            }

        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching all cinema data from the database", e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error occurred", e);
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo đóng các tài nguyên
            closeResources();
        }

        return rapChieuList;
    }
    public int getMinMaRapChieu() {
        int minMaRapChieu = -1;

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e("DatabaseError", "Không thể kết nối đến cơ sở dữ liệu.");
                return minMaRapChieu;
            }

            // Gọi hàm SQL fn_GetMinMaRapChieu
            String sql = "SELECT dbo.fn_GetMinMaRapChieu() AS MinMaRapChieu";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Lấy kết quả từ ResultSet
            if (resultSet.next()) {
                minMaRapChieu = resultSet.getInt("MinMaRapChieu");
            }
        } catch (SQLException e) {
            Log.e("DatabaseError", "Error while fetching MinMaRapChieu", e);
        } finally {
            // Đảm bảo đóng các tài nguyên
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                Log.e("DatabaseError", "Error while closing database resources", e);
            }
        }

        return minMaRapChieu;
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
    public void loadRapChieuToRecyclerView(Context context, RecyclerView recyclerView, List<ent_RapChieu> list, RapChieuAdapter adapter) {
        if (list == null || list.isEmpty()) {
            Log.e(TAG, "Danh sách rạp chiếu trống hoặc không hợp lệ.");
            return;
        }

        // Thiết lập LayoutManager cho RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        // Thêm ItemDecoration nếu cần
        int spacingInPixels = context.getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        recyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(spacingInPixels));

        // Thiết lập Adapter và dữ liệu nếu chưa có adapter
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(adapter);
        }

        // Cập nhật dữ liệu cho adapter
        adapter.SetData(list);
    }

}
