package com.example.cinebooker.PhanCongQuoc.BussinessLogic;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.ProcessData.PD_XemThongTin;
import com.example.cinebooker.PhanCongQuoc.adapter.XemThongTinAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.XemThongTinEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_XemThongTin {
    private final PD_XemThongTin pdXemThongTin;

    public BL_XemThongTin() {
        pdXemThongTin = new PD_XemThongTin();
    }

    // Phương thức lấy mã vé từ SharedPreferences


    // Phương thức lấy danh sách phim xuất vé từ PD_XemThongTin
    private List<XemThongTinEntity> getDanhSachPhimXemThongTin(int maVe) {
        return pdXemThongTin.getXemThongTin(maVe);
    }

    // Phương thức load phim vào RecyclerView
    public void loadPhimToRecyclerView(Context context, RecyclerView recyclerView, XemThongTinAdapter adapter, int maVe) {
        if (recyclerView == null) {
            Log.w("BL_XemThongTin", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Tìm kiếm phim theo tên từ cơ sở dữ liệu
                List<XemThongTinEntity> list = getDanhSachPhimXemThongTin(maVe);

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof Activity) {
                    ((Activity) context).runOnUiThread(() -> {
                        pdXemThongTin.loadPhimToRecyclerView(context, recyclerView, list, adapter);
                    });
                }
            } catch (Exception e) {
                Log.e("BL_XemThongTin", "Error while loading data", e);
            } finally {
                executor.shutdown();
            }
        });
    }


}
