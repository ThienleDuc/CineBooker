package com.example.cinebooker.LeDucThien.BussinessLogic;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.ProcessData.PD_RapChieuCon;
import com.example.cinebooker.LeDucThien.adapter.DiaChiRapChieuAdapter;
import com.example.cinebooker.LeDucThien.adapter.RapChieuConAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_RapChieuCon;
import com.example.cinebooker.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_RapChieuCon {
    private final PD_RapChieuCon pdRapChieuCon;

    public BL_RapChieuCon() {
        pdRapChieuCon = new PD_RapChieuCon();
    }

    // Phương thức lấy danh sách Rạp Chiếu Con theo tên tỉnh thành
    private List<ent_RapChieuCon> getDanhSachRapChieuConTheoDieuKien(int maTinhThanh, int maRapChieu) {

        return pdRapChieuCon.getRapChieuConByTinhThanh(maTinhThanh, maRapChieu);
    }

    // Phương thức lấy danh sách Rạp Chiếu Con theo tên tỉnh thành
    private List<ent_RapChieuCon> getDanhSachRapChieuConTheoMaRapChieuCon(int maRapChieuCon) {
        return pdRapChieuCon.getRapChieuConByMaRapChieuCon(maRapChieuCon);
    }

    public int loadMinRapChieuCon (int maTinhThanh, int maRapChieu) {
        return pdRapChieuCon.getMinMaRapChieuCon(maTinhThanh, maRapChieu);
    }
    // Phương thức trả về TextView và ImageView sau khi load thông tin Rạp Chiếu Con theo tên tỉnh thành
    public void loadThongTinRapChieuCon(Context context, ImageView anhRapChieuCon, TextView tenRapChieuCon,
                                        TextView moTaRapChieuCon, int maRapChieuCon) {
        // Tạo ExecutorService để chạy công việc không đồng bộ
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách Rạp Chiếu Con theo điều kiện tên tỉnh thành
                List<ent_RapChieuCon> list = getDanhSachRapChieuConTheoMaRapChieuCon(maRapChieuCon);

                if (list != null && !list.isEmpty()) {
                    // Lấy thông tin Rạp Chiếu Con đầu tiên từ danh sách
                    ent_RapChieuCon rapChieuCon = list.get(0);

                    // Cập nhật UI trên UI thread
                    if (context instanceof android.app.Activity) {
                        ((android.app.Activity) context).runOnUiThread(() -> {
                            // Cập nhật tên Rạp Chiếu Con
                            tenRapChieuCon.setText(rapChieuCon.getTenRapChieuCon());

                            // Cập nhật mô tả Rạp Chiếu Con
                            moTaRapChieuCon.setText(rapChieuCon.getDiaChiRapChieu());

                            // Cập nhật hình ảnh nếu có (giả sử là URL hoặc resource ID)
                            String anhUrl = rapChieuCon.getAnhRapChieu();
                            if (anhUrl != null && !anhUrl.isEmpty()) {
                                // Sử dụng Picasso để load hình ảnh
                                Picasso.get()
                                        .load(anhUrl)
                                        .placeholder(R.drawable.cgv)
                                        .into(anhRapChieuCon);
                            } else {
                                anhRapChieuCon.setImageResource(R.drawable.cgv);
                            }
                        });
                    }
                }
            } catch (Exception e) {
                // Log lỗi nếu có exception xảy ra
                Log.e("BL_RapChieuCon", "Error while loading data with condition", e);
            } finally {
                // Đảm bảo shutdown ExecutorService để tránh rò rỉ tài nguyên
                executor.shutdown();
            }
        });
    }

    // Phương thức chung để load phim vào RecyclerView
    public void loadRapChieuConToRecyclerView(Context context, RecyclerView recyclerView, int maTinhThanh, int maRapChieu, RapChieuConAdapter adapter) {
        // Kiểm tra RecyclerView không được null
        if (recyclerView == null) {
            Log.w("BL_RapChieuCon", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        // Tạo ExecutorService để chạy công việc không đồng bộ
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách phim sắp chiếu từ cơ sở dữ liệu
                List<ent_RapChieuCon> list = getDanhSachRapChieuConTheoDieuKien(maTinhThanh, maRapChieu);

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        // Gọi phương thức từ PD_RapChieuCon để load dữ liệu vào RecyclerView
                        pdRapChieuCon.loadRapChieuConToRecyclerView(context, recyclerView, list, adapter);
                    });
                }
            } catch (Exception e) {
                // Log lỗi nếu có exception xảy ra
                Log.e("BL_RapChieuCon", "Error while loading data", e);
            } finally {
                // Đảm bảo shutdown ExecutorService để tránh rò rỉ tài nguyên
                executor.shutdown();
            }
        });
    }

    public void loadDiaChiRapChieuConToRecyclerView(Context context, RecyclerView recyclerView, int maTinhThanh, int maRapChieu, DiaChiRapChieuAdapter adapter) {
        // Kiểm tra RecyclerView không được null
        if (recyclerView == null) {
            Log.w("BL_RapChieuCon", "RecyclerView is null. Data will not be loaded.");
            return;
        }

        // Tạo ExecutorService để chạy công việc không đồng bộ
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                // Lấy danh sách phim sắp chiếu từ cơ sở dữ liệu
                List<ent_RapChieuCon> list = getDanhSachRapChieuConTheoDieuKien(maTinhThanh, maRapChieu);

                // Cập nhật RecyclerView trên UI thread
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(() -> {
                        // Gọi phương thức từ PD_RapChieuCon để load dữ liệu vào RecyclerView
                        pdRapChieuCon.loadDiaChiRapChieuConToRecyclerView(context, recyclerView, list, adapter);
                    });
                }
            } catch (Exception e) {
                // Log lỗi nếu có exception xảy ra
                Log.e("BL_RapChieuCon", "Error while loading data", e);
            } finally {
                // Đảm bảo shutdown ExecutorService để tránh rò rỉ tài nguyên
                executor.shutdown();
            }
        });
    }
}
