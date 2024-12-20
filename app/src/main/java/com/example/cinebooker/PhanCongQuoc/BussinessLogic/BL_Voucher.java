package com.example.cinebooker.PhanCongQuoc.BussinessLogic;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.ProcessData.PD_Voucher;
import com.example.cinebooker.PhanCongQuoc.entity.ticketvoucherMoviesEntity;

import java.security.AccessControlContext;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_Voucher {
    private final PD_Voucher pdVoucher;

    public BL_Voucher() {
        pdVoucher = new PD_Voucher();
    }

    // Phương thức lấy danh sách phim chưa dùng
    private List<ticketvoucherMoviesEntity> getDanhSachPhimVoucher() {
        return pdVoucher.getVoucher();
    }

    // Phương thức chung để load phim vào RecyclerView
    private void loadPhimToRecyclerView(Context context, RecyclerView recyclerView, boolean isHorizontal) {
        // Kiểm tra RecyclerView không được null
        if (recyclerView == null) {
            Log.w("BL_Voucher", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        // Tạo ExecutorService để chạy công việc không đồng bộ
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách phim chưa dùng từ cơ sở dữ liệu
                List<ticketvoucherMoviesEntity> list = getDanhSachPhimVoucher();

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        // Gọi phương thức từ PD_Voucher để load dữ liệu vào RecyclerView
                        pdVoucher.loadMoviesToRecyclerView(context, recyclerView, list, isHorizontal);
                    });
                }
            } catch (Exception e) {
                // Log lỗi nếu có exception xảy ra
                Log.e("BL_Voucher", "Error while loading data", e);
            } finally {
                // Đảm bảo shutdown ExecutorService để tránh rò rỉ tài nguyên
                executor.shutdown();
            }
        });
    }

    // Gọi loadPhimToRecyclerView với kiểu dọc
    public void loadVoucherVertical(Context context, RecyclerView recyclerView) {
        loadPhimToRecyclerView(context, recyclerView, false); // false cho kiểu dọc
    }

    // Gọi loadPhimToRecyclerView với kiểu ngang
    public void loadVoucherHorizontal(Context context, RecyclerView recyclerView) {
        loadPhimToRecyclerView(context, recyclerView, true); // true cho kiểu ngang
    }
}
