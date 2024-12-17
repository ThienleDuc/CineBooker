package com.example.cinebooker.LeDucThien.ProcessData;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.adapter.LichChieuAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_NgayChieu;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ConnectionDatabase;
import com.example.cinebooker.generalMethod.HorizontalSpaceItemDecoration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PD_NgayChieu {

    private static final String TAG = "PD_NgayChieu"; // Tag để log
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public List<ent_NgayChieu> get7NgayChieuFromToday() {
        List<ent_NgayChieu> ngayChieuList = new ArrayList<>();

        try {
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return ngayChieuList;
            }

            // Thực thi stored procedure
            String sql = "EXEC pr_Get7NgayChieuFromToday";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            // Lặp qua kết quả truy vấn
            while (resultSet.next()) {
                // Lấy dữ liệu từ ResultSet
                int maThoiGianChieu = resultSet.getInt("MaThoiGianChieu");
                String kieuNgay = resultSet.getString("KieuNgay");
                String ngayChieu = resultSet.getString("NgayChieu"); // Đọc ngày dưới dạng String (dd)

                // Khởi tạo đối tượng ent_NgayChieu với KieuNgay và NgayChieu
                ent_NgayChieu ngayChieuEntity = new ent_NgayChieu(maThoiGianChieu, kieuNgay, ngayChieu);
                ngayChieuList.add(ngayChieuEntity);

                // Ghi log thông tin từng bản ghi
                Log.d(TAG, "Ngày chiếu lấy được: " + kieuNgay + " - " + ngayChieu);
            }

        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching movie show dates from the database", e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error occurred", e);
            throw new RuntimeException(e);
        } finally {
            closeResources();
        }

        // Ghi log danh sách hoàn chỉnh trước khi trả về
        Log.d(TAG, "Danh sách ngày chiếu: " + ngayChieuList.toString());

        return ngayChieuList;
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

    public void loadNgayChieuToRecyclerView(Context context, RecyclerView recyclerView, List<ent_NgayChieu> list, LichChieuAdapter adapter) {
        // Log danh sách dữ liệu nhận được
        if (list == null || list.isEmpty()) {
            Log.w(TAG, "Danh sách ngày chiếu rỗng hoặc null.");
        } else {
            for (ent_NgayChieu ngayChieu : list) {
                Log.d(TAG, "Dữ liệu ngày chiếu: " + ngayChieu.toString());
            }
        }

        // Thiết lập LayoutManager nếu chưa có
        if (recyclerView.getLayoutManager() == null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);
        }

        int spacingInPixels = context.getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        recyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(spacingInPixels));

        // Thiết lập Adapter và dữ liệu
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(adapter);
        }

        adapter.setData(list);
    }
}
