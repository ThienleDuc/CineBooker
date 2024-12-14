package com.example.cinebooker.PhanCongQuoc.BussinessLogic;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cinebooker.PhanCongQuoc.ProcessData.PD_XuatVe;
import com.example.cinebooker.PhanCongQuoc.entity.xuatveEntity;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_XuatVe {
    private final PD_XuatVe pdXuatVe;

    public BL_XuatVe() {
        pdXuatVe = new PD_XuatVe();
    }

    // Phương thức lấy mã vé từ SharedPreferences
    private int getMaVeFromSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("QuocDepTrai", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("MaVe", -1);  // -1 là giá trị mặc định khi không có MaVe
    }

    // Phương thức lấy danh sách phim xuất vé từ PD_XuatVe
    private List<xuatveEntity> getDanhSachPhimXuatVe(int maVe) {
        List<xuatveEntity> list = pdXuatVe.getXuatVe(maVe);
        if (list == null || list.isEmpty()) {
            Log.w("BL_XuatVe", "Danh sách phim không có dữ liệu hoặc bị lỗi.");
        }
        return list;
    }

    // Phương thức load phim vào RecyclerView
    private void loadPhimToRecyclerView(Context context, RecyclerView recyclerView, boolean isHorizontal) {
        // Lấy mã vé từ SharedPreferences
        int maVe = getMaVeFromSharedPreferences(context);

        // Kiểm tra nếu mã vé hợp lệ
        if (maVe == -1) {
            Log.e("BL_XuatVe", "Mã vé không hợp lệ hoặc không được lưu.");
            return;
        }

        // Kiểm tra RecyclerView không phải null
        if (recyclerView == null) {
            Log.w("BL_XuatVe", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        // Tạo ExecutorService để chạy công việc không đồng bộ
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách phim chưa dùng từ cơ sở dữ liệu
                List<xuatveEntity> list = getDanhSachPhimXuatVe(maVe);
                Log.d("BL_XuatVe", "Fetched list size: " + (list != null ? list.size() : "null"));

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        if (list != null && !list.isEmpty()) {
                            // Gọi phương thức từ PD_XuatVe để load dữ liệu vào RecyclerView
                            pdXuatVe.loadMoviesToRecyclerView(context, recyclerView, list, isHorizontal);
                        } else {
                            // Hiển thị thông báo nếu không có dữ liệu
                            Log.w("BL_XuatVe", "Không có dữ liệu cho Mã vé");
                        }
                    });
                }
            } catch (Exception e) {
                // Log lỗi nếu có exception xảy ra
                Log.e("BL_XuatVe", "Lỗi khi tải dữ liệu", e);
            } finally {
                // Đảm bảo shutdown ExecutorService để tránh rò rỉ tài nguyên
                executor.shutdown();
            }
        });
    }  

    // Phương thức load phim theo chiều dọc
    public void loadXuatVeVertical(Context context, RecyclerView recyclerView) {
        loadPhimToRecyclerView(context, recyclerView, false); // false cho kiểu dọc
    }

    // Phương thức load phim theo chiều ngang (nếu cần)
    public void loadXuatVeHorizontal(Context context, RecyclerView recyclerView) {
        loadPhimToRecyclerView(context, recyclerView, true); // true cho kiểu ngang
    }
}
