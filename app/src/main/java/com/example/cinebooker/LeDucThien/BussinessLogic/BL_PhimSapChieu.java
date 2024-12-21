package com.example.cinebooker.LeDucThien.BussinessLogic;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.ProcessData.PD_PhimSapChieu;
import com.example.cinebooker.LeDucThien.entity.ent_PhimSapChieu;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_PhimSapChieu {
    private final PD_PhimSapChieu pdPhimSapChieu;

    public BL_PhimSapChieu() {
        pdPhimSapChieu = new PD_PhimSapChieu();
    }

    // Phương thức lấy danh sách phim sắp chiếu
    private List<ent_PhimSapChieu> getDanhSachPhimSapChieu() {
        return pdPhimSapChieu.getPhimSapChieu();
    }

    // Phương thức chung để load phim vào RecyclerView
    public void loadPhimToRecyclerView(Context context, RecyclerView recyclerView) {
        // Kiểm tra RecyclerView không được null
        if (recyclerView == null) {
            Log.w("BL_PhimSapChieu", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        // Tạo ExecutorService để chạy công việc không đồng bộ
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách phim sắp chiếu từ cơ sở dữ liệu
                List<ent_PhimSapChieu> list = getDanhSachPhimSapChieu();

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        // Gọi phương thức từ PD_PhimSapChieu để load dữ liệu vào RecyclerView
                        pdPhimSapChieu.loadMoviesToRecyclerView(context, recyclerView, list);
                    });
                }
            } catch (Exception e) {
                // Log lỗi nếu có exception xảy ra
                Log.e("BL_PhimSapChieu", "Error while loading data", e);
            } finally {
                // Đảm bảo shutdown ExecutorService để tránh rò rỉ tài nguyên
                executor.shutdown();
            }
        });
    }

    // Phương thức chung để load phim vào RecyclerView
    public void loadCaroselPhimToRecyclerView(Context context, RecyclerView recyclerView) {
        // Kiểm tra RecyclerView không được null
        if (recyclerView == null) {
            Log.w("BL_PhimSapChieu", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        // Tạo ExecutorService để chạy công việc không đồng bộ
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách phim sắp chiếu từ cơ sở dữ liệu
                List<ent_PhimSapChieu> list = getDanhSachPhimSapChieu();

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        // Gọi phương thức từ PD_PhimSapChieu để load dữ liệu vào RecyclerView
                        pdPhimSapChieu.loadCaroselMoviesToRecyclerView(context, recyclerView, list);
                    });
                }
            } catch (Exception e) {
                // Log lỗi nếu có exception xảy ra
                Log.e("BL_PhimSapChieu", "Error while loading data", e);
            } finally {
                // Đảm bảo shutdown ExecutorService để tránh rò rỉ tài nguyên
                executor.shutdown();
            }
        });
    }
}
