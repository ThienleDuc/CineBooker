package com.example.cinebooker.LeDucThien.BussinessLogic;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.ProcessData.PD_TinhThanh;
import com.example.cinebooker.LeDucThien.adapter.TinhThanhAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_TinhThanh;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_TinhThanh {
    private PD_TinhThanh pdTinhThanh;

    public BL_TinhThanh() {
        pdTinhThanh = new PD_TinhThanh();
    }

    // Phương thức lấy danh sách phim sắp chiếu
    private List<ent_TinhThanh> getDanhSachTenTinhThanh() {
        return pdTinhThanh.getAllTinhThanh();
    }

    public int loadMinMaTinhThanh () {
        return pdTinhThanh.getMinMaTinhThanh();
    }

    // Phương thức lấy danh sách tỉnh thành theo điều kiện MaTinhThanh
    private List<ent_TinhThanh> getDanhSachTinhThanhTheoDieuKien(int maTinhThanh) {
        // Nếu maTinhThanh chưa xác định, lấy giá trị nhỏ nhất
        if (maTinhThanh == -1) {
            int minMaTinhThanh = loadMinMaTinhThanh();
            if (minMaTinhThanh > 0) { // Chỉ lấy giá trị hợp lệ
                maTinhThanh = minMaTinhThanh;
            } else {
                // Trả về danh sách rỗng nếu không có dữ liệu
                return new ArrayList<>();
            }
        }

        // Truy vấn danh sách tỉnh thành theo mã
        return pdTinhThanh.getTinhThanhByMaTinhThanh(maTinhThanh);
    }


    // Phương thức chung để load phim vào RecyclerView
    public void loadTenTinhThanhToRecyclerView(Context context, RecyclerView recyclerView, TinhThanhAdapter adapter) {
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
                List<ent_TinhThanh> list = getDanhSachTenTinhThanh();

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        // Gọi phương thức từ PD_PhimSapChieu để load dữ liệu vào RecyclerView
                        pdTinhThanh.loadTenTinhThanhToRecyclerView(context, recyclerView, list, adapter);
                    });
                }
            } catch (Exception e) {
                // Log lỗi nếu có exception xảy ra
                Log.e("BL_TinhThanh", "Error while loading data", e);
            } finally {
                // Đảm bảo shutdown ExecutorService để tránh rò rỉ tài nguyên
                executor.shutdown();
            }
        });
    }

    // Phương thức trả về TextView sau khi load Tên Tỉnh Thành theo MaTinhThanh
    public void loadTenTinhThanhTheoDieuKien(Context context, TextView textView, int maTinhThanh) {
        // Kiểm tra TextView không được null
        if (textView == null) {
            Log.w("BL_TinhThanh", "TextView is null. Data will not be loaded.");
            return;
        }

        // Tạo ExecutorService để chạy công việc không đồng bộ
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách tỉnh thành theo điều kiện MaTinhThanh
                List<ent_TinhThanh> list = getDanhSachTinhThanhTheoDieuKien(maTinhThanh);

                if (list != null && !list.isEmpty()) {
                    // Lấy tên tỉnh thành từ danh sách
                    String tenTinhThanh = list.get(0).getTenTinhThanh();

                    // Cập nhật TextView trên UI thread
                    if (context instanceof android.app.Activity) {
                        ((android.app.Activity) context).runOnUiThread(() -> {
                            // Cập nhật TextView với tên tỉnh thành tìm được
                            textView.setText(tenTinhThanh);
                        });
                    }
                }
            } catch (Exception e) {
                // Log lỗi nếu có exception xảy ra
                Log.e("BL_TinhThanh", "Error while loading data with condition", e);
            } finally {
                // Đảm bảo shutdown ExecutorService để tránh rò rỉ tài nguyên
                executor.shutdown();
            }
        });
    }
}
