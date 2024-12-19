package com.example.cinebooker.PhanCongQuoc.ProcessData;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_dadungAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.ticketdadungMoviesEntity;
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

public class PD_DaDung {

    private static final String TAG = "PD_PhimDaDung"; // Đặt tag cho log
    private Connection connection = null;
    private CallableStatement callableStatement = null;
    private ResultSet resultSet = null;

    // Phương thức lấy danh sách vé chưa sử dụng
    public List<ticketdadungMoviesEntity> getDaDung() {
        List<ticketdadungMoviesEntity> phimList = new ArrayList<>();

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return phimList; // Nếu kết nối không thành công, trả về danh sách rỗng
            }

            // Tạo CallableStatement và ResultSet
            callableStatement = connection.prepareCall("{call VePhimDaDung}"); // Giả sử thủ tục lưu trữ tên là VePhimDaDung
            resultSet = callableStatement.executeQuery();

            // Duyệt qua kết quả trả về và lưu vào List<ticketdadungMoviesEntity>
            while (resultSet.next()) {
                // Tạo đối tượng phim
                ticketdadungMoviesEntity phim = new ticketdadungMoviesEntity();
                phim.setMaVe(resultSet.getInt("MaVe"));
                  phim.setDate_dadung(resultSet.getString("ThoiGian"));
                  phim.setPoster_dadung(resultSet.getString("AnhPhim"));
                  phim.setAge_dadung( resultSet.getInt("Tuoi"));
                  phim.setName_dadung( resultSet.getString("TenPhim"));
                  phim.setStyle_dadung( resultSet.getString("TenTheLoai"));
                  phim.setSoluong_dadung( resultSet.getInt("SoLuongVe"));
                  phim.setAnhrap_dadung(resultSet.getString("AnhRapChieu"));
                  phim.setDiachi_dadung( resultSet.getString("DiaChiRapChieu"));
                // Thêm đối tượng phim vào danh sách
                phimList.add(phim);
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
    public void loadMoviesToRecyclerView(Context context, RecyclerView recyclerView, List<ticketdadungMoviesEntity> movieList, boolean isHorizontal) {
        if (movieList == null || movieList.isEmpty()) {
            Log.e(TAG, "Danh sách phim trống hoặc không hợp lệ.");
            return;
        }
        // Thiết lập LayoutManager
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(isHorizontal ? LinearLayoutManager.HORIZONTAL : LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // Xóa tất cả các ItemDecoration cũ trước khi thêm mới (tránh cộng dồn)
        while (recyclerView.getItemDecorationCount() > 0) {
            recyclerView.removeItemDecorationAt(0);
        }

        // Tạo khoảng cách giữa các item (chỉ trên và dưới)
        int spacing = 16; // Khoảng cách cố định (pixel), có thể tùy chỉnh
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                if (position != RecyclerView.NO_POSITION) {
                    outRect.top = spacing; // Khoảng cách trên
                    outRect.bottom = spacing; // Khoảng cách dưới
                }
            }
        });


        // Tạo adapter và gán vào RecyclerView
        Ticket_dadungAdapter adapter = new Ticket_dadungAdapter();
        recyclerView.setAdapter(adapter);
        adapter.SetData(movieList); // Đưa danh sách phim vào adapter
    }
}
