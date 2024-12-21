package com.example.cinebooker.LeDucThien.ProcessData;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cinebooker.LeDucThien.adapter.DanhGiaPhimAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_DanhGiaPhim;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ConnectionDatabase;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PD_DanhGiaPhim {
    private static final String TAG = "PD_DanhGiaPhim"; // Tag để log
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    // Phương thức gọi stored procedure pr_BinhLuanNoiBat để lấy dữ liệu đánh giá phim
    public List<ent_DanhGiaPhim> getDanhGiaPhimFromProcedure() {
        List<ent_DanhGiaPhim> danhGiaPhimList = new ArrayList<>();

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return danhGiaPhimList;
            }

            // Câu lệnh SQL gọi stored procedure pr_BinhLuanNoiBat
            String sql = "EXEC pr_BinhLuanNoiBat";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Duyệt qua kết quả và thêm vào danh sách đối tượng ent_DanhGiaPhim
            while (resultSet.next()) {
                int maPhim = resultSet.getInt("MaPhim");
                String anhPhim = resultSet.getString("AnhPhim");
                String tenPhim = resultSet.getString("TenPhim");
                int tuoi = resultSet.getInt("Tuoi");
                String tenTheLoai = resultSet.getString("TenTheLoai");
                float diemDanhGiaTrungBinh = resultSet.getFloat("DiemDanhGiaTrungBinh");
                int tongLuotMua = resultSet.getInt("TongLuotMuaPhim");
                int tongLuotDanhGia = resultSet.getInt("TongLuotDanhGiaPhim");
                String anhKhachHang = resultSet.getString("AnhKhachHang");
                String tenKhachHang = resultSet.getString("TenKhachHang");
                String ngayDanhGia = resultSet.getString("NgayDanhGia");
                String danhGia = resultSet.getString("DanhGia");
                int luotThich = resultSet.getInt("LuotThich");

                ent_DanhGiaPhim danhGiaPhim = new ent_DanhGiaPhim(maPhim, anhPhim, tenPhim, tuoi, tenTheLoai,
                        diemDanhGiaTrungBinh, tongLuotMua, tongLuotDanhGia, anhKhachHang, tenKhachHang, ngayDanhGia, danhGia, luotThich);

                danhGiaPhimList.add(danhGiaPhim);
            }

        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching movie review data from the database", e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error occurred", e);
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo đóng các tài nguyên
            closeResources();
        }

        return danhGiaPhimList;
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
    public void loadDanhGiaPhimToRecyclerView(Context context, RecyclerView recyclerView, List<ent_DanhGiaPhim> list, DanhGiaPhimAdapter adapter) {
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
