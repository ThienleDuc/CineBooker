package com.example.cinebooker.LeDucThien.BussinessLogic;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.cinebooker.LeDucThien.ProcessData.PD_TinhThanh;
import com.example.cinebooker.LeDucThien.adapter.TinhThanhAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_TinhThanh;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_TinhThanh {
    private final PD_TinhThanh pdTinhThanh;
    public BL_TinhThanh() {
        pdTinhThanh = new PD_TinhThanh();
    }

    // Phương thức lấy danh sách phim sắp chiếu
    private List<ent_TinhThanh> getDanhSachTenTinhThanh() {
        return pdTinhThanh.getAllTinhThanh();
    }

    public int loadMinMaTinhThanh () {
        return pdTinhThanh.getMinMaTinhThanh();
    }


    private List<ent_TinhThanh> getDanhSachTinhThanhTheoDieuKien(int maTinhThanh) {
        if (maTinhThanh == -1) {
            int minMaTinhThanh = loadMinMaTinhThanh();
            if (minMaTinhThanh > 0) {
                maTinhThanh = minMaTinhThanh;
            } else {
                return new ArrayList<>();
            }
        }

        // Truy vấn danh sách tỉnh thành theo mã
        return pdTinhThanh.getTinhThanhByMaTinhThanh(maTinhThanh);
    }


    // Phương thức chung để load phim vào RecyclerView
    public void loadTenTinhThanhToRecyclerView(Context context, RecyclerView recyclerView, TinhThanhAdapter adapter) {
        if (recyclerView == null) {
            Log.w("BL_PhimSapChieu", "RecyclerView is null. Data will not be loaded.");
            return;
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                List<ent_TinhThanh> list = getDanhSachTenTinhThanh();
                if (context instanceof Activity) {
                    ((Activity) context).runOnUiThread(() -> {
                        pdTinhThanh.loadTenTinhThanhToRecyclerView(context, recyclerView, list, adapter);
                    });
                }
            } catch (Exception e) {
                // Log lỗi nếu có exception xảy ra
                Log.e("BL_TinhThanh", "Error while loading data", e);
            } finally {
                executor.shutdown();
            }
        });
    }

    // Phương thức trả về TextView sau khi load Tên Tỉnh Thành theo MaTinhThanh
    public void loadTenTinhThanhTheoDieuKien(Context context, TextView textView, int maTinhThanh) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                List<ent_TinhThanh> list = getDanhSachTinhThanhTheoDieuKien(maTinhThanh);

                if (list != null && !list.isEmpty()) {
                    String tenTinhThanh = list.get(0).getTenTinhThanh();

                    // Cập nhật TextView trên UI thread
                    if (context instanceof Activity) {
                        ((Activity) context).runOnUiThread(() -> {
                            // Cập nhật TextView với tên tỉnh thành tìm được
                            textView.setText(tenTinhThanh);
                        });
                    }
                }
            } catch (Exception e) {
                Log.e("BL_TinhThanh", "Error while loading data with condition", e);
            } finally {
                executor.shutdown();
            }
        });
    }

    private List<ent_TinhThanh> getDanhSachByTenTinhThanh(String input) {
        return pdTinhThanh.getTinhThanhByTenTinhThanh(input);
    }

    public void loadTenTinhThanhToRecyclerViewAfterSearch(Context context, RecyclerView recyclerView, TinhThanhAdapter adapter, String input) {
        if (recyclerView == null) {
            Log.w("BL_TinhThanh", "RecyclerView is null. Data will not be loaded.");
            return;
        }
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                List<ent_TinhThanh> list = getDanhSachByTenTinhThanh(input);
                if (context instanceof Activity) {
                    ((Activity) context).runOnUiThread(() -> {
                        pdTinhThanh.loadTenTinhThanhToRecyclerView(context, recyclerView, list, adapter);
                    });
                }
            } catch (Exception e) {
                Log.e("BL_TinhThanh", "Error while loading data", e);
            } finally {
                executor.shutdown();
            }
        });
    }
}