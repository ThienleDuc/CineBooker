package com.example.cinebooker.LeDucThien.ProcessData;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.adapter.KhachHangAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_KhachHang;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ConnectionDatabase;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PD_KhachHang {
    private static final String TAG = "PD_KhachHang"; // Tag để log
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    // Phương thức lấy dữ liệu khách hàng từ cơ sở dữ liệu
    public List<ent_KhachHang> getKhachHangFromDatabase() {
        List<ent_KhachHang> khachHangList = new ArrayList<>();

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return khachHangList;
            }

            // Câu lệnh SQL lấy dữ liệu khách hàng
            String sql = "SELECT MaKhachHang, Email, MatKhau, AnhKhachHang, TenKhachHang, LopHocPhan, MaSinhVien FROM KhachHang";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Duyệt qua kết quả và thêm vào danh sách đối tượng ent_KhachHang
            while (resultSet.next()) {
                int maKhachHang = resultSet.getInt("MaKhachHang");
                String email = resultSet.getString("Email");
                String matKhau = resultSet.getString("MatKhau");
                String anhKhachHang = resultSet.getString("AnhKhachHang");
                String tenKhachHang = resultSet.getString("TenKhachHang");
                String lopHocPhan = resultSet.getString("LopHocPhan");
                String maSinhVien = resultSet.getString("MaSinhVien");

                ent_KhachHang khachHang = new ent_KhachHang(maKhachHang, email, matKhau, anhKhachHang, tenKhachHang, lopHocPhan, maSinhVien);
                khachHangList.add(khachHang);
            }

        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching customer data from the database", e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error occurred", e);
        } finally {
            // Đảm bảo đóng các tài nguyên
            closeResources();
        }

        return khachHangList;
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
    public void loadKhachHangToRecyclerView(Context context, RecyclerView recyclerView, List<ent_KhachHang> list, KhachHangAdapter adapter) {
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
        adapter.setData(list);
    }
}
