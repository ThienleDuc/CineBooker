package com.example.cinebooker.LeDucThien.BussinessLogic;

import android.content.Context;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.ProcessData.PD_NgayChieu;
import com.example.cinebooker.LeDucThien.adapter.LichChieuAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_NgayChieu;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_NgayChieu {
    private final PD_NgayChieu pdNgayChieu;

    public BL_NgayChieu() {
        pdNgayChieu = new PD_NgayChieu();
    }

    // Phương thức lấy danh sách ngày chiếu
    private List<ent_NgayChieu> getDanhSachNgayChieu() {
        return pdNgayChieu.get7NgayChieuFromToday();
    }

    // Phương thức chung để load danh sách ngày chiếu vào RecyclerView
    public void loadNgayChieuToRecyclerView(Context context, RecyclerView recyclerView, LichChieuAdapter adapter) {
        if (recyclerView == null) {
            Log.w("BL_NgayChieu", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        // Tạo ExecutorService để chạy tác vụ trong một thread riêng
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách ngày chiếu từ cơ sở dữ liệu
                List<ent_NgayChieu> list = getDanhSachNgayChieu();

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        // Chuyển dữ liệu vào adapter và load vào RecyclerView
                        pdNgayChieu.loadNgayChieuToRecyclerView(context, recyclerView, list, adapter);
                    });
                }
            } catch (Exception e) {
                Log.e("BL_NgayChieu", "Error while loading data", e);
            } finally {
                executor.shutdown(); // Đảm bảo ExecutorService được đóng sau khi hoàn thành
            }
        });
    }
}
