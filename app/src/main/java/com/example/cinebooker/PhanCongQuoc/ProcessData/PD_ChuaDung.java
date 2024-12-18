package com.example.cinebooker.PhanCongQuoc.ProcessData;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.adapter.moviesDangChieuAdapter;
import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_chuadungAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.ticketchuadungMoviesEntity;
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
import android.os.Handler;

public class PD_ChuaDung {

    private static final String TAG = "PD_PhimChuaDung"; // Đặt tag cho log
    private Connection connection = null;
    private CallableStatement callableStatement = null;
    private ResultSet resultSet = null;
    private Handler handler = new Handler();

    // Phương thức lấy danh sách vé chưa sử dụng
    public List<ticketchuadungMoviesEntity> getChuaDung() {
        List<ticketchuadungMoviesEntity> phimList = new ArrayList<>();

        try {
            // Mở kết nối đến cơ sở dữ liệu
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return phimList; // Nếu kết nối không thành công, trả về danh sách rỗng
            }

            // Tạo CallableStatement và ResultSet
            callableStatement = connection.prepareCall("{call VePhimChuaDung}"); // Giả sử thủ tục lưu trữ tên là VePhimChuaDung
            resultSet = callableStatement.executeQuery();

            // Duyệt qua kết quả trả về và lưu vào List<ticketchuadungMoviesEntity>
            while (resultSet.next()) {
                // Tạo đối tượng phim
                ticketchuadungMoviesEntity phim = new ticketchuadungMoviesEntity();
                phim.setMaVe(resultSet.getInt("MaVe"));
                  phim.setDate_chuadung(resultSet.getString("ThoiGian"));
                  phim.setPoster_chuadung(resultSet.getString("AnhPhim"));
                  phim.setAge_chuadung( resultSet.getInt("Tuoi"));
                  phim.setName_chuadung( resultSet.getString("TenPhim"));
                  phim.setStyle_chuadung( resultSet.getString("TenTheLoai"));
                  phim.setSoluong_chuadung( resultSet.getInt("SoLuongVe"));
                  phim.setAnhrap(resultSet.getString("AnhRapChieu"));
                  phim.setDiachi_chuadung( resultSet.getString("DiaChiRapChieu"));
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
    public void loadMoviesToRecyclerView(Context context, RecyclerView recyclerView, List<ticketchuadungMoviesEntity> movieList, boolean isHorizontal) {
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
        Ticket_chuadungAdapter adapter = new Ticket_chuadungAdapter();
        recyclerView.setAdapter(adapter);
        adapter.SetData(movieList); // Đưa danh sách phim vào adapter
        // Đặt một Runnable để cập nhật dữ liệu sau mỗi 5 giây
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<ticketchuadungMoviesEntity> newMovieList = getChuaDung();
                adapter.SetData(newMovieList);
                adapter.notifyDataSetChanged(); // Thêm dòng này để đảm bảo RecyclerView được làm mới
                handler.postDelayed(this, 5000);
            }
        }, 5000);

    }
}
