package com.example.cinebooker.LeDucThien.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_KhachHang;
import com.example.cinebooker.LeDucThien.adapter.KhachHangAdapter;
import com.example.cinebooker.R;

public class VeChungToi extends AppCompatActivity {

    private BL_KhachHang blKhachHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ve_chung_toi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        blKhachHang = new BL_KhachHang();
        KhachHangAdapter khachHangAdapter = new KhachHangAdapter();

        // Tìm RecyclerView và load dữ liệu vào
        RecyclerView recyclerView = findViewById(R.id.recyclerView_khach_hang);
        blKhachHang.loadKhachHangToRecyclerView(this, recyclerView, khachHangAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Giải phóng tài nguyên nếu cần thiết
        if (blKhachHang != null) {
            blKhachHang.shutdownExecutor();
        }
    }
}