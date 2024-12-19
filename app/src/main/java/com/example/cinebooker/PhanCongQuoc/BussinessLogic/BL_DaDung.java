package com.example.cinebooker.PhanCongQuoc.BussinessLogic;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.ProcessData.PD_DaDung;
import com.example.cinebooker.PhanCongQuoc.entity.ticketdadungMoviesEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_DaDung {
    private final PD_DaDung pdDaDung;

    public BL_DaDung() {
        pdDaDung = new PD_DaDung();
    }

    // Phương thức lấy danh sách phim chưa dùng
    private List<ticketdadungMoviesEntity> getDanhSachPhimDaDung() {
        return pdDaDung.getDaDung();
    }

    // Phương thức chung để load phim vào RecyclerView
    private void loadPhimToRecyclerView(Context context, RecyclerView recyclerView, boolean isHorizontal) {
        // Kiểm tra RecyclerView không được null
        if (recyclerView == null) {
            Log.w("BL_DaDung", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        // Tạo ExecutorService để chạy công việc không đồng bộ
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách phim chưa dùng từ cơ sở dữ liệu
                List<ticketdadungMoviesEntity> list = getDanhSachPhimDaDung();

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        // Gọi phương thức từ PD_DaDung để load dữ liệu vào RecyclerView
                        pdDaDung.loadMoviesToRecyclerView(context, recyclerView, list, isHorizontal);
                    });
                }
            } catch (Exception e) {
                // Log lỗi nếu có exception xảy ra
                Log.e("BL_DaDung", "Error while loading data", e);
            } finally {
                // Đảm bảo shutdown ExecutorService để tránh rò rỉ tài nguyên
                executor.shutdown();
            }
        });
    }

    // Gọi loadPhimToRecyclerView với kiểu dọc
    public void loadDaDungVertical(Context context, RecyclerView recyclerView) {
        loadPhimToRecyclerView(context, recyclerView, false); // false cho kiểu dọc
    }

    // Gọi loadPhimToRecyclerView với kiểu ngang
    public void loadDaDungHorizontal(Context context, RecyclerView recyclerView) {
        loadPhimToRecyclerView(context, recyclerView, true); // true cho kiểu ngang
    }
}
