package com.example.cinebooker.LeDucThien.BussinessLogic;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.ProcessData.PD_TimKiemPhim;
import com.example.cinebooker.LeDucThien.adapter.TimKiemAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_TimKiemPhim;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_TimKiemPhim {
    private final PD_TimKiemPhim pdTimKiemPhim;

    public BL_TimKiemPhim() {
        pdTimKiemPhim = new PD_TimKiemPhim();
    }

    private List<ent_TimKiemPhim> getAllPhim() {
        return pdTimKiemPhim.getAllPhim();
    }

    // Phương thức tìm kiếm phim theo tên
    private List<ent_TimKiemPhim> timKiemPhim(String tenPhim) {
        return pdTimKiemPhim.getPhimByTen(tenPhim);
    }

    // Phương thức chung để load danh sách phim vào RecyclerView
    public void loadPhimToRecyclerView(Context context, RecyclerView recyclerView, TimKiemAdapter adapter) {
        if (recyclerView == null) {
            Log.w("BL_TimKiemPhim", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Tìm kiếm phim theo tên từ cơ sở dữ liệu
                List<ent_TimKiemPhim> list = getAllPhim();

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof Activity) {
                    ((Activity) context).runOnUiThread(() -> {
                        pdTimKiemPhim.loadPhimToRecyclerView(context, recyclerView, list, adapter);
                    });
                }
            } catch (Exception e) {
                Log.e("BL_TimKiemPhim", "Error while loading data", e);
            } finally {
                executor.shutdown();
            }
        });
    }

    public void loadPhimToRecyclerViewAfterSearch(Context context, RecyclerView recyclerView, TimKiemAdapter adapter, String tenPhim) {
        if (recyclerView == null) {
            Log.w("BL_TimKiemPhim", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Tìm kiếm phim theo tên từ cơ sở dữ liệu
                List<ent_TimKiemPhim> list = timKiemPhim(tenPhim);

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof Activity) {
                    ((Activity) context).runOnUiThread(() -> {
                        pdTimKiemPhim.loadPhimToRecyclerView(context, recyclerView, list, adapter);
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
