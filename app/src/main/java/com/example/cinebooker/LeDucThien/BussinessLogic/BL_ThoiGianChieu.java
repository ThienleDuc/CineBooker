package com.example.cinebooker.LeDucThien.BussinessLogic;

import android.content.Context;
import android.util.Log;


import androidx.recyclerview.widget.RecyclerView;


import com.example.cinebooker.LeDucThien.ProcessData.PD_ThoiGianChieu;
import com.example.cinebooker.LeDucThien.adapter.ThoiGianChieuAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_ThoiGianChieu;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_ThoiGianChieu {
    private final PD_ThoiGianChieu pdThoiGianChieu;

    public BL_ThoiGianChieu() {
        pdThoiGianChieu = new PD_ThoiGianChieu();
    }

    // Phương thức lấy danh sách thời gian chiếu
    private List<ent_ThoiGianChieu> getDanhSachThoiGianChieu(int maPhim, int maRapChieuCon, int maThoiGianChieu) {
        return pdThoiGianChieu.getThoiGianBatDauKetThucTheoMaPhimVaRapChieuCon(maPhim, maRapChieuCon, maThoiGianChieu);
    }

    // Phương thức load danh sách thời gian chiếu vào RecyclerView
    public void loadThoiGianToRecyclerView(Context context, RecyclerView recyclerView, ThoiGianChieuAdapter adapter, int maPhim, int maRapChieuCon, int maThoiGianChieu) {
        if (recyclerView == null) {
            Log.w("BL_ThoiGianChieu", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách thời gian chiếu từ cơ sở dữ liệu
                List<ent_ThoiGianChieu> list = getDanhSachThoiGianChieu(maPhim, maRapChieuCon, maThoiGianChieu);

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        pdThoiGianChieu.loadThoiGianToRecyclerView(context, recyclerView, list, adapter);
                    });
                }
            } catch (Exception e) {
                Log.e("BL_ThoiGianChieu", "Error while loading data", e);
            } finally {
                executor.shutdown();
            }
        });
    }
}
