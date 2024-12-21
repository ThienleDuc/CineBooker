package com.example.cinebooker.PhanCongQuoc.PhanCongQuoc.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.BussinessLogic.BL_ChiTietHuy;
import com.example.cinebooker.PhanCongQuoc.BussinessLogic.BL_ChiTietHuy;
import com.example.cinebooker.PhanCongQuoc.adapter.ChiTietHuyAdapter;
import com.example.cinebooker.PhanCongQuoc.adapter.ChiTietHuyAdapter;
import com.example.cinebooker.R;

import java.util.ArrayList;

public class chiTietHuy extends AppCompatActivity {
    private int maVe_huy = -1;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet_huy);

        ImageView back = findViewById(R.id.itemback);

        back.setOnClickListener(v -> onBackPressed());

        chitiethuy();


    }
    private void chitiethuy() {
        RecyclerView chitietRecyclerView = findViewById(R.id.chitiethuy_recycle_view);
        BL_ChiTietHuy blChiTietHuy = new BL_ChiTietHuy();
        sharedPreferences = getSharedPreferences("QuocDepTrai", MODE_PRIVATE);
        maVe_huy = sharedPreferences.getInt("MaVe", -1);

        // Khởi tạo adapter và gắn vào RecyclerView
        ChiTietHuyAdapter adapter = new ChiTietHuyAdapter(this, new ArrayList<>());  // Truyền dữ liệu trống
        chitietRecyclerView.setAdapter(adapter);

        // Load dữ liệu
        blChiTietHuy.loadPhimToRecyclerView(this, chitietRecyclerView, adapter, maVe_huy);
    }
}