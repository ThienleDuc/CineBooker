package com.example.cinebooker.PhanCongQuoc.BussinessLogic;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.ProcessData.PD_XuatVe;

import com.example.cinebooker.PhanCongQuoc.adapter.XuatVeAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.xuatveEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_XuatVe {
    private final PD_XuatVe pdXuatVe;

    public BL_XuatVe() {
        pdXuatVe = new PD_XuatVe();
    }

    // Phương thức lấy danh sách phim chưa dùng
    private List<xuatveEntity> getDanhSachPhimXuatVe(int maVe) {
        return pdXuatVe.getXuatVe(maVe); // Truyền mã vé vào
    }

    // Phương thức chung để load phim vào RecyclerView
    // Phương thức chung để load phim vào RecyclerView
    private void loadPhimToRecyclerView(Context context, RecyclerView recyclerView, boolean isHorizontal, int maVe) {
        // Kiểm tra RecyclerView không được null
        if (recyclerView == null) {
            Log.w("BL_XuatVe", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        // Tạo ExecutorService để chạy công việc không đồng bộ
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách phim chưa dùng từ cơ sở dữ liệu
                List<xuatveEntity> list = getDanhSachPhimXuatVe(maVe); // Truyền tham số maVe vào

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        // Gọi phương thức từ PD_XuatVe để load dữ liệu vào RecyclerView
                        pdXuatVe.loadMoviesToRecyclerView(context, recyclerView, list, isHorizontal);
                    });
                }
            } catch (Exception e) {
                // Log lỗi nếu có exception xảy ra
                Log.e("BL_XuatVe", "Error while loading data", e);
            } finally {
                // Đảm bảo shutdown ExecutorService để tránh rò rỉ tài nguyên
                executor.shutdown();
            }
        });
    }


    // Phương thức loadXuatVeVertical trong BL_XuatVe
    public void loadXuatVeVertical(Context context, RecyclerView recyclerView, int maVe) {
        loadPhimToRecyclerView(context, recyclerView, false, maVe); // false cho kiểu dọc, truyền tham số maVe vào
    }






}
