package com.example.cinebooker.LeDucThien.ProcessData;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.adapter.TinhThanhAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_TinhThanh;
import com.example.cinebooker.generalMethod.ConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PD_TinhThanh {
    private static final String TAG = "PD_TinhThanh"; // Tag để log
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    // Phương thức lấy danh sách tỉnh thành
    public List<ent_TinhThanh> getAllTinhThanh() {
        List<ent_TinhThanh> tinhThanhList = new ArrayList<>();

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return tinhThanhList; // Trả về danh sách rỗng nếu kết nối thất bại
            }

            // Chuẩn bị và thực thi câu lệnh truy vấn
            String sql = "SELECT MaTinhThanh, TenTinhThanh FROM TinhThanhPho";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Duyệt qua kết quả và thêm vào danh sách đối tượng ent_TinhThanh
            while (resultSet.next()) {
                int maTinhThanh = resultSet.getInt("MaTinhThanh");
                String tenTinhThanh = resultSet.getString("TenTinhThanh");
                ent_TinhThanh tinhThanh = new ent_TinhThanh(maTinhThanh, tenTinhThanh);
                tinhThanhList.add(tinhThanh);
            }

        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching city data from the database", e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error occurred", e);
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo đóng các tài nguyên
            closeResources();
        }

        return tinhThanhList;
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

    public List<ent_TinhThanh> getTinhThanhByMaTinhThanh(int maTinhThanh) {
        List<ent_TinhThanh> tinhThanhList = new ArrayList<>();

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return tinhThanhList; // Trả về danh sách rỗng nếu kết nối thất bại
            }

            // Chuẩn bị câu lệnh SQL với điều kiện MaTinhThanh
            String sql = "SELECT MaTinhThanh, TenTinhThanh FROM TinhThanhPho WHERE MaTinhThanh = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, maTinhThanh);
            resultSet = preparedStatement.executeQuery();

            // Duyệt qua kết quả và thêm vào danh sách đối tượng ent_TinhThanh
            while (resultSet.next()) {
                int maTinhThanhResult = resultSet.getInt("MaTinhThanh");
                String tenTinhThanh = resultSet.getString("TenTinhThanh");
                ent_TinhThanh tinhThanh = new ent_TinhThanh(maTinhThanhResult, tenTinhThanh);
                tinhThanhList.add(tinhThanh);
            }

        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching city data from the database", e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error occurred", e);
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo đóng các tài nguyên
            closeResources();
        }

        return tinhThanhList;
    }

    public List<ent_TinhThanh> getTinhThanhByTenTinhThanh(String input) {
        List<ent_TinhThanh> tinhThanhList = new ArrayList<>();

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return tinhThanhList; // Trả về danh sách rỗng nếu kết nối thất bại
            }

            // Chuẩn bị câu lệnh SQL với điều kiện MaTinhThanh
            String sql = "SELECT MaTinhThanh, TenTinhThanh FROM TinhThanhPho WHERE TenTinhThanh LIKE N'%' + ? + N'%'";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, input);
            resultSet = preparedStatement.executeQuery();

            // Duyệt qua kết quả và thêm vào danh sách đối tượng ent_TinhThanh
            while (resultSet.next()) {
                int maTinhThanhResult = resultSet.getInt("MaTinhThanh");
                String tenTinhThanh = resultSet.getString("TenTinhThanh");
                ent_TinhThanh tinhThanh = new ent_TinhThanh(maTinhThanhResult, tenTinhThanh);
                tinhThanhList.add(tinhThanh);
            }

        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching city data from the database", e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error occurred", e);
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo đóng các tài nguyên
            closeResources();
        }

        return tinhThanhList;
    }

    public int getMinMaTinhThanh() {
        int minMaTinhThanh = -1; // Giá trị mặc định nếu không tìm thấy kết quả

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return minMaTinhThanh;
            }

            // Chuẩn bị câu lệnh SQL để lấy giá trị nhỏ nhất của MaTinhThanh
            String sql = "SELECT MIN(MaTinhThanh) AS MinMaTinhThanh FROM TinhThanhPho";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Lấy giá trị nhỏ nhất từ kết quả truy vấn
            if (resultSet.next()) {
                minMaTinhThanh = resultSet.getInt("MinMaTinhThanh");
            }

        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching minimum MaTinhThanh from the database", e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error occurred", e);
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo đóng các tài nguyên
            closeResources();
        }

        return minMaTinhThanh;
    }

    public void loadTenTinhThanhToRecyclerView(Context context, RecyclerView recyclerView, List<ent_TinhThanh> list, TinhThanhAdapter adapter) {
        if (list == null || list.isEmpty()) {
            Log.e(TAG, "Danh sách tỉnh thành trống hoặc không hợp lệ.");
            return;
        }

        // Thiết lập GridLayoutManager cho RecyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));

        // Thiết lập Adapter và dữ liệu nếu chưa có adapter
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(adapter);
        }

        // Cập nhật dữ liệu cho adapter và đảm bảo adapter không rỗng
        adapter.SetData(list);
    }

}
