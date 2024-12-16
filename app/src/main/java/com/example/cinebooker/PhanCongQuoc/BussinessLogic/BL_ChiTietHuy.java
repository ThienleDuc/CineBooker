package com.example.cinebooker.PhanCongQuoc.BussinessLogic;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.ProcessData.PD_ChiTietHuy;
import com.example.cinebooker.PhanCongQuoc.adapter.ChiTietHuyAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.ChiTietHuyEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_ChiTietHuy {
    private final PD_ChiTietHuy pdChiTietHuy;

    public BL_ChiTietHuy() {
        pdChiTietHuy = new PD_ChiTietHuy();
    }

    // Phương thức lấy danh sách phim xuất vé từ PD_ChiTietHuy
    private List<ChiTietHuyEntity> getDanhSachPhimChiTietHuy(int maVe) {
        return pdChiTietHuy.getChiTietHuy(maVe);
    }

    // Phương thức load phim vào RecyclerView
    public void loadPhimToRecyclerView(Context context, RecyclerView recyclerView, ChiTietHuyAdapter adapter, int maVe) {
        if (recyclerView == null) {
            Log.w("BL_ChiTietHuy", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Tìm kiếm phim theo tên từ cơ sở dữ liệu
                List<ChiTietHuyEntity> list = getDanhSachPhimChiTietHuy(maVe);

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof Activity) {
                    ((Activity) context).runOnUiThread(() -> {
                        pdChiTietHuy.loadPhimToRecyclerView(context, recyclerView, list, adapter);
                    });
                }
            } catch (Exception e) {
                Log.e("BL_ChiTietHuy", "Error while loading data", e);
            } finally {
                executor.shutdown();
            }
        });
    }


}
