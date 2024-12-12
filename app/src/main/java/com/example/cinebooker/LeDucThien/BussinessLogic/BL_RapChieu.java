package com.example.cinebooker.LeDucThien.BussinessLogic;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.ProcessData.PD_RapChieu;
import com.example.cinebooker.LeDucThien.adapter.RapChieuAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_RapChieu;
import com.example.cinebooker.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_RapChieu {
    private PD_RapChieu pdRapChieu;

    public BL_RapChieu() {
        pdRapChieu = new PD_RapChieu();
    }

    // Phương thức lấy danh sách rạp chiếu
    private List<ent_RapChieu> getDanhSachRapChieu() {
        return pdRapChieu.getAllRapChieu();
    }

    // Phương thức lấy MaRapChieu nhỏ nhất
    public int loadMinMaRapChieu() {
        return pdRapChieu.getMinMaRapChieu();
    }

    // Phương thức để load thông tin rạp chiếu vào UI
    public void loadThongTinRapChieu(Context context, ImageView anhRapChieu, TextView tenRapChieu,
                                     TextView moTaRapChieu) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách rạp chiếu
                List<ent_RapChieu> list = getDanhSachRapChieu();

                // Cập nhật UI trên thread chính
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        if (list != null && !list.isEmpty()) {
                            // Lấy rạp chiếu đầu tiên trong danh sách
                            ent_RapChieu rapChieu = list.get(0);

                            // Cập nhật thông tin lên UI
                            tenRapChieu.setText(rapChieu.getTenRapChieu());
                            moTaRapChieu.setText(rapChieu.getMoTaRapChieu());

                            // Cập nhật hình ảnh nếu có
                            String anhUrl = rapChieu.getAnhRapChieu();
                            if (anhUrl != null && !anhUrl.isEmpty()) {
                                Picasso.get()
                                        .load(anhUrl)
                                        .placeholder(R.drawable.cgv)
                                        .into(anhRapChieu);
                            } else {
                                anhRapChieu.setImageResource(R.drawable.cgv);
                            }
                        }
                    });
                }
            } catch (Exception e) {
                Log.e("BL_RapChieu", "Error while loading cinema data", e);
            } finally {
                executor.shutdown();
            }
        });
    }

    // Phương thức chung để load danh sách rạp chiếu vào RecyclerView
    public void loadRapChieuToRecyclerView(Context context, RecyclerView recyclerView, RapChieuAdapter adapter) {
        if (recyclerView == null) {
            Log.w("BL_RapChieu", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách rạp chiếu từ cơ sở dữ liệu
                List<ent_RapChieu> list = getDanhSachRapChieu();

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        pdRapChieu.loadRapChieuToRecyclerView(context, recyclerView, list, adapter);
                    });
                }
            } catch (Exception e) {
                Log.e("BL_RapChieu", "Error while loading data", e);
            } finally {
                executor.shutdown();
            }
        });
    }
}
