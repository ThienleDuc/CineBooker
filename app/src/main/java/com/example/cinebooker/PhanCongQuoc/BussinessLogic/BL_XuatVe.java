package com.example.cinebooker.PhanCongQuoc.BussinessLogic;

import android.app.Activity;
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
        return pdXuatVe.getXuatVe(maVe);
    }

    // Phương thức load phim vào RecyclerView
    public void loadPhimToRecyclerView(Context context, RecyclerView recyclerView, XuatVeAdapter adapter, int maVe) {
        if (recyclerView == null) {
            Log.w("BL_XuatVe", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Tìm kiếm phim theo tên từ cơ sở dữ liệu
                List<xuatveEntity> list = getDanhSachPhimXuatVe(maVe);

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof Activity) {
                    ((Activity) context).runOnUiThread(() -> {
                        pdXuatVe.loadPhimToRecyclerView(context, recyclerView, list, adapter);
                    });
                }
            } catch (Exception e) {
                Log.e("BL_TimKiemPhim", "Error while loading data", e);
            } finally {
                executor.shutdown();
            }
        });
    }


}
