package com.example.cinebooker.LeDucThien.BussinessLogic;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.ProcessData.PD_DoiTac;
import com.example.cinebooker.LeDucThien.adapter.DoiTacAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_DoiTac;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_DoiTac {
    private PD_DoiTac pdDoiTac;

    public BL_DoiTac() {
        pdDoiTac = new PD_DoiTac();
    }

    // Phương thức lấy danh sách đối tác
    private List<ent_DoiTac> getDanhSachDoiTac() {
        return pdDoiTac.getDoiTacFromProcedure();
    }

    // Phương thức chung để load danh sách đối tác vào RecyclerView
    public void loadDoiTacToRecyclerView(Context context, RecyclerView recyclerView, DoiTacAdapter adapter) {
        if (recyclerView == null) {
            Log.w("BL_DoiTac", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách đối tác từ cơ sở dữ liệu
                List<ent_DoiTac> list = getDanhSachDoiTac();

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        pdDoiTac.loadDoiTacToRecyclerView(context, recyclerView, list, adapter);
                    });
                }
            } catch (Exception e) {
                Log.e("BL_DoiTac", "Error while loading data", e);
            } finally {
                executor.shutdown();
            }
        });
    }
}
