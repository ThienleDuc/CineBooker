package com.example.cinebooker.PhanCongQuoc.PhanCongQuoc.BussinessLogic;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.ProcessData.PD_HoanTien;
import com.example.cinebooker.PhanCongQuoc.adapter.YeuCauHoanTienAdapter;
import com.example.cinebooker.PhanCongQuoc.adapter.YeuCauHoanTienAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.YeuCauHoanTienEntity;
import com.example.cinebooker.PhanCongQuoc.entity.YeuCauHoanTienEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_HoanTien {
    private final PD_HoanTien pdHoanTien;

    public BL_HoanTien() {
        pdHoanTien = new PD_HoanTien();
    }

    // Phương thức lấy danh sách phim xuất vé từ PD_HoanTien
    private List<YeuCauHoanTienEntity> getDanhSachPhimHoanTien(int maVe) {
        return pdHoanTien.getHoanTien(maVe);
    }

    // Phương thức load phim vào RecyclerView
    public void loadPhimToRecyclerView(Context context, RecyclerView recyclerView, YeuCauHoanTienAdapter adapter, int maVe) {
        if (recyclerView == null) {
            Log.w("BL_HoanTien", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Tìm kiếm phim theo tên từ cơ sở dữ liệu
                List<YeuCauHoanTienEntity> list = getDanhSachPhimHoanTien(maVe);

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof Activity) {
                    ((Activity) context).runOnUiThread(() -> {
                        pdHoanTien.loadPhimToRecyclerView(context, recyclerView, list, adapter);
                    });
                }
            } catch (Exception e) {
                Log.e("BL_HoanTien", "Error while loading data", e);
            } finally {
                executor.shutdown();
            }
        });
    }


}
