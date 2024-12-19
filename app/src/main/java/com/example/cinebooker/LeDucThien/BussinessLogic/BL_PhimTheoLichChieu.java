package com.example.cinebooker.LeDucThien.BussinessLogic;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.ProcessData.PD_PhimTheoLichChieu;
import com.example.cinebooker.LeDucThien.adapter.PhimTheoLichChieuAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_XepHang;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_PhimTheoLichChieu {
    private final PD_PhimTheoLichChieu pdPhimTheoLichChieu;

    public BL_PhimTheoLichChieu() {
        pdPhimTheoLichChieu = new PD_PhimTheoLichChieu();
    }

    // Phương thức lấy danh sách phim theo ngày chiếu và rạp chiếu con
    private List<ent_XepHang> getPhimTheoLichChieu(int maRapChieuCon, int maThoiGianChieu) {
        return pdPhimTheoLichChieu.getPhimTheoNgayChieuRapChieuCon(maRapChieuCon, maThoiGianChieu);
    }

    // Phương thức chung để load danh sách phim vào RecyclerView
    public void loadPhimTheoLichChieuToRecyclerView(Context context, RecyclerView recyclerView, PhimTheoLichChieuAdapter adapter, int maRapChieuCon, int maThoiGianChieu) {
        if (recyclerView == null) {
            Log.w("BL_PhimTheoLichChieu", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách phim từ cơ sở dữ liệu
                List<ent_XepHang> list = getPhimTheoLichChieu(maRapChieuCon, maThoiGianChieu);

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        pdPhimTheoLichChieu.loadPhimToRecyclerView(context, recyclerView, list, adapter);
                    });
                }
            } catch (Exception e) {
                Log.e("BL_PhimTheoLichChieu", "Error while loading data", e);
            } finally {
                executor.shutdown();
            }
        });
    }
}
