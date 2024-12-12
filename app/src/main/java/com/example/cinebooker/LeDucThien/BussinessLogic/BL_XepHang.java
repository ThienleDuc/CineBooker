package com.example.cinebooker.LeDucThien.BussinessLogic;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.ProcessData.PD_XepHang;
import com.example.cinebooker.LeDucThien.adapter.XepHangAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_TinhThanh;
import com.example.cinebooker.LeDucThien.entity.ent_XepHang;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_XepHang {
    private static final String TAG = "BL_XepHang";
    private final PD_XepHang pdXepHang;

    public BL_XepHang() {
        pdXepHang = new PD_XepHang();
    }

    // Phương thức chung để load dữ liệu
    private void loadXepHangToRecyclerView(Context context, RecyclerView recyclerView, String type, XepHangAdapter adapter) {
        if (recyclerView == null) {
            Log.w(TAG, "RecyclerView is null. Data will not be loaded.");
            return;
        }

        // Tạo ExecutorService để chạy công việc không đồng bộ
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                List<ent_XepHang> list = null;

                // Chọn phương thức phù hợp dựa trên type
                switch (type) {
                    case "ngay":
                        list = pdXepHang.getPhimXepHangTheoNgay();
                        break;
                    case "tuan":
                        list = pdXepHang.getPhimXepHangTheoTuan();
                        break;
                    case "thang":
                        list = pdXepHang.getPhimXepHangTheoThang();
                        break;
                    default:
                        Log.e(TAG, "Invalid type for loading ranked movies: " + type);
                        return;
                }

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    List<ent_XepHang> finalList = list;
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        pdXepHang.loadXepHangToRecyclerView(context, recyclerView, finalList, adapter);
                        Log.i(TAG, "Data loaded successfully for type: " + type);
                    });
                }
            } catch (Exception e) {
                Log.e(TAG, "Error while loading data for type: " + type, e);
            }
        });
    }

    // Gọi các phương thức tương ứng
    public void loadXepHangNgayToRecyclerView(Context context, RecyclerView recyclerView, XepHangAdapter adapter) {
        loadXepHangToRecyclerView(context, recyclerView, "ngay", adapter);
    }

    public void loadXepHangTuanToRecyclerView(Context context, RecyclerView recyclerView, XepHangAdapter adapter) {
        loadXepHangToRecyclerView(context, recyclerView, "tuan", adapter);
    }

    public void loadXepHangThangToRecyclerView(Context context, RecyclerView recyclerView, XepHangAdapter adapter) {
        loadXepHangToRecyclerView(context, recyclerView, "thang", adapter);
    }

}
