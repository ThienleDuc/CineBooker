package com.example.cinebooker.LeDucThien.BussinessLogic;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.ProcessData.PD_KhachHang;
import com.example.cinebooker.LeDucThien.adapter.KhachHangAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_KhachHang;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_KhachHang {
    private static final String TAG = "BL_KhachHang";
    private final PD_KhachHang pdKhachHang;
    private final ExecutorService executor;

    public BL_KhachHang() {
        pdKhachHang = new PD_KhachHang();
        // Sử dụng cached thread pool để có thể tái sử dụng các thread
        executor = Executors.newCachedThreadPool();
    }

    // Phương thức lấy danh sách khách hàng
    private List<ent_KhachHang> getDanhSachKhachHang() {
        return pdKhachHang.getKhachHangFromDatabase();
    }

    // Phương thức chung để load danh sách khách hàng vào RecyclerView
    public void loadKhachHangToRecyclerView(Context context, RecyclerView recyclerView, KhachHangAdapter adapter) {
        if (recyclerView == null) {
            Log.w(TAG, "RecyclerView is null. Data will not be loaded.");
            return;
        }

        executor.execute(() -> {
            try {
                // Lấy danh sách khách hàng từ cơ sở dữ liệu
                List<ent_KhachHang> list = getDanhSachKhachHang();

                // Cập nhật RecyclerView trên UI thread
                if (context != null && context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        pdKhachHang.loadKhachHangToRecyclerView(context, recyclerView, list, adapter);
                    });
                }
            } catch (Exception e) {
                Log.e(TAG, "Error while loading data", e);
            } finally {
                executor.shutdown();
            }
        });
    }

    // Phương thức hủy bỏ executor khi không sử dụng nữa (đảm bảo không bị rò rỉ tài nguyên)
    public void shutdownExecutor() {
        if (!executor.isShutdown()) {
            executor.shutdown();
        }
    }
}
