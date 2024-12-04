package com.example.cinebooker.LeDucThien.BussinessLogic;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.ProcessData.PD_PhimDangChieu;
import com.example.cinebooker.LeDucThien.entity.ent_PhimDangChieu;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BL_PhimDangChieu {
    private PD_PhimDangChieu pdPhimDangChieu;

    public BL_PhimDangChieu() {
        pdPhimDangChieu = new PD_PhimDangChieu();
    }

    // Lấy danh sách phim đang chiếu
    public List<ent_PhimDangChieu> getDanhSachPhimDangChieu() {
        // Gọi đến phương thức của PD_PhimDangChieu để lấy dữ liệu
        return pdPhimDangChieu.getPhimDangChieu();
    }

    public void LoadDangChieuVer(Context context, RecyclerView recyclerView) {
        // Sử dụng ExecutorService để thực hiện việc lấy dữ liệu trong một luồng khác (không phải UI thread)
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {

                // Lấy danh sách phim đang chiếu
                List<ent_PhimDangChieu> list = getDanhSachPhimDangChieu();

                // Trả kết quả về UI thread để cập nhật RecyclerView
                // Sử dụng runOnUiThread để cập nhật UI sau khi xử lý xong
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdPhimDangChieu.loadMoviesToRecyclerViewVertical(context, recyclerView, list);
                        }
                    });
                }
            }
        });
    }

    public void LoadDangChieuHor(Context context, RecyclerView recyclerView) {
        // Sử dụng ExecutorService để thực hiện việc lấy dữ liệu trong một luồng khác (không phải UI thread)
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Lấy danh sách phim đang chiếu
                List<ent_PhimDangChieu> list = getDanhSachPhimDangChieu();

                // Trả kết quả về UI thread để cập nhật RecyclerView
                // Sử dụng runOnUiThread để cập nhật UI sau khi xử lý xong
                if (context instanceof android.app.Activity) {
                    ((android.app.Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pdPhimDangChieu.loadMoviesToRecyclerViewHorizontal(context, recyclerView, list);
                        }
                    });
                }
            }
        });
    }

}
