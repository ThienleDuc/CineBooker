package com.example.cinebooker.PhanCongQuoc.BussinessLogic;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
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

    // Phương thức lấy mã vé từ SharedPreferences


    // Phương thức lấy danh sách phim xuất vé từ PD_XuatVe
    private List<xuatveEntity> getDanhSachPhimXuatVe(int maVe) {
        List<xuatveEntity> list = pdXuatVe.getXuatVe(maVe);
        if (list == null || list.isEmpty()) {
            Log.w("BL_XuatVe", "Danh sách phim không có dữ liệu hoặc bị lỗi.");
        }
        return list;
    }

    // Phương thức load phim vào RecyclerView
    public void loadPhimToRecyclerView(Context context, RecyclerView recyclerView, int maVe, XuatVeAdapter adapter) {
        if (recyclerView == null) {
            Log.w("BL_XuatVe", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        // Tạo ExecutorService để chạy công việc không đồng bộ
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách phim sắp chiếu từ cơ sở dữ liệu
                List<xuatveEntity> list = getDanhSachPhimXuatVe(maVe);

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        // Gọi phương thức từ PD_XuatVe để load dữ liệu vào RecyclerView
                        pdXuatVe.loadPhimToRecyclerView(context, recyclerView, list, adapter);
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


}
