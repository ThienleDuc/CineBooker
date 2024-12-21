package com.example.cinebooker.LeDucThien.BussinessLogic;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;



import com.example.cinebooker.LeDucThien.ProcessData.PD_PhimDangChieu;
import com.example.cinebooker.LeDucThien.entity.ent_PhimDangChieu;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_PhimDangChieu {
    private final PD_PhimDangChieu pdPhimDangChieu;

    public BL_PhimDangChieu() {
        pdPhimDangChieu = new PD_PhimDangChieu();
    }

    // Phương thức lấy danh sách phim đang chiếu
    private List<ent_PhimDangChieu> getDanhSachPhimDangChieu() {
        return pdPhimDangChieu.getPhimDangChieu();
    }

    // Phương thức chung để load phim vào RecyclerView
    public void loadPhimToRecyclerView(Context context, RecyclerView recyclerView) {
        // Kiểm tra RecyclerView không được null
        if (recyclerView == null) {
            Log.w("BL_PhimDangChieu", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        // Tạo ExecutorService để chạy công việc không đồng bộ
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách phim đang chiếu từ cơ sở dữ liệu
                List<ent_PhimDangChieu> list = getDanhSachPhimDangChieu();

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        // Gọi phương thức từ PD_PhimDangChieu để load dữ liệu vào RecyclerView
                        pdPhimDangChieu.loadMoviesToRecyclerView(context, recyclerView, list);
                    });
                }
            } catch (Exception e) {
                // Log lỗi nếu có exception xảy ra
                Log.e("BL_PhimDangChieu", "Error while loading data", e);
            } finally {
                // Đảm bảo shutdown ExecutorService để tránh rò rỉ tài nguyên
                executor.shutdown();
            }
        });
    }

    public void loadCaroselPhimToRecyclerView(Context context, RecyclerView recyclerView) {
        // Kiểm tra RecyclerView không được null
        if (recyclerView == null) {
            Log.w("BL_PhimDangChieu", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        // Tạo ExecutorService để chạy công việc không đồng bộ
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách phim đang chiếu từ cơ sở dữ liệu
                List<ent_PhimDangChieu> list = getDanhSachPhimDangChieu();

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        // Gọi phương thức từ PD_PhimDangChieu để load dữ liệu vào RecyclerView
                        pdPhimDangChieu.loadCaroselMoviesToRecyclerView(context, recyclerView, list);
                    });
                }
            } catch (Exception e) {
                // Log lỗi nếu có exception xảy ra
                Log.e("BL_PhimDangChieu", "Error while loading data", e);
            } finally {
                // Đảm bảo shutdown ExecutorService để tránh rò rỉ tài nguyên
                executor.shutdown();
            }
        });
    }
}
