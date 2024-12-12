package com.example.cinebooker.LeDucThien.ProcessData;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.adapter.DoiTacAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_DoiTac;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ConnectionDatabase;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PD_DoiTac {
    private static final String TAG = "PD_DoiTac"; // Tag để log
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    // Phương thức gọi stored procedure pr_LayThongTinRapChieuCha để lấy dữ liệu đối tác
    public List<ent_DoiTac> getDoiTacFromProcedure() {
        List<ent_DoiTac> doiTacList = new ArrayList<>();

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return doiTacList;
            }

            // Câu lệnh SQL gọi stored procedure pr_LayThongTinRapChieuCha
            String sql = "EXEC pr_LayThongTinRapChieuCha";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Duyệt qua kết quả và thêm vào danh sách đối tượng ent_DoiTac
            while (resultSet.next()) {
                String anhDoiTac = resultSet.getString("AnhRapChieu");
                String tenDoiTac = resultSet.getString("TenRapChieu");
                String moTaDoiTac = resultSet.getString("MoTaRapChieu");
                float diemDanhGiaDoiTacTrungBinh = resultSet.getFloat("DiemDanhGiaRapChieuTrungBinh");
                int tongLuotDanhGia = resultSet.getInt("TongLuotDanhGia");
                int tongDiaDiemDoiTac = resultSet.getInt("TongDiaDiemRap");

                ent_DoiTac doiTac = new ent_DoiTac(anhDoiTac, tenDoiTac, moTaDoiTac, diemDanhGiaDoiTacTrungBinh, tongLuotDanhGia, tongDiaDiemDoiTac);
                doiTacList.add(doiTac);
            }

        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching partner data from the database", e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error occurred", e);
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo đóng các tài nguyên
            closeResources();
        }

        return doiTacList;
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
    public void loadDoiTacToRecyclerView(Context context, RecyclerView recyclerView, List<ent_DoiTac> list, DoiTacAdapter adapter) {
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

        // Cập nhật dữ liệu cho adapter
        adapter.SetData(list);
    }

}
