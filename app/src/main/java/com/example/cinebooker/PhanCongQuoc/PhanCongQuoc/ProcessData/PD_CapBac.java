package com.example.cinebooker.PhanCongQuoc.PhanCongQuoc.ProcessData;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_capbacAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.ticketcapbacMoviesEntity;
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

public class PD_CapBac {

    private static final String TAG = "PD_PhimCapBac"; // Đặt tag cho log
    private Connection connection = null;
    private CallableStatement callableStatement = null;
    private ResultSet resultSet = null;

    // Phương thức lấy danh sách vé chưa sử dụng
    public List<ticketcapbacMoviesEntity> getCapBac() {
        List<ticketcapbacMoviesEntity> phimList = new ArrayList<>();

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return phimList; // Nếu kết nối không thành công, trả về danh sách rỗng
            }

            // Tạo CallableStatement và ResultSet
            callableStatement = connection.prepareCall("{call sp_GetVouchercapbacList}"); // Giả sử thủ tục lưu trữ tên là VePhimDaDung
            resultSet = callableStatement.executeQuery();
            // Duyệt qua kết quả trả về và lưu vào List<ticketCapBacMoviesEntity>
            while (resultSet.next()) {
                // Tạo đối tượng CapBac
                ticketcapbacMoviesEntity CapBac = new ticketcapbacMoviesEntity();



                CapBac.setIcondonvi_capbac(resultSet.getString("Icon")); // Lấy ảnh ứng dụng
                CapBac.setNamedonvi_capbac(resultSet.getString("TenVoucher")); // Lấy tên CapBac
                CapBac.setDadung_capbac(resultSet.getString("SoLuongSuDung")); // Trạng thái giảm
                CapBac.setMucgiam_capbac(resultSet.getString("MucGiam"));
                CapBac.setGioihangiam_capbac(resultSet.getString("SoLuongToiDa")); // Lấy mức giảm
                CapBac.setDate_capbac(resultSet.getString("HanSuDung")); // Lấy hạn sử dụng

                // Thêm vào danh sách
                phimList.add(CapBac);
            }

        } catch (SQLException e) {
            Log.e(TAG, "Error when fetching movies data from the database", e);
        } catch (Exception e) {
            Log.e(TAG, "Unexpected error occurred", e);
            throw new RuntimeException(e);
        } finally {
            // Đảm bảo rằng các tài nguyên luôn được đóng
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

    // Phương thức load dữ liệu vào RecyclerView
    public void loadMoviesToRecyclerView(Context context, RecyclerView recyclerView, List<ticketcapbacMoviesEntity> movieList, boolean isHorizontal) {
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
        Ticket_capbacAdapter adapter = new Ticket_capbacAdapter();
        recyclerView.setAdapter(adapter);
        adapter.SetData(movieList); // Đưa danh sách phim vào adapter
    }
}
