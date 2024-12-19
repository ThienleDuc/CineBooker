package com.example.cinebooker.PhanCongQuoc.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.BussinessLogic.BL_XemThongTin;
import com.example.cinebooker.PhanCongQuoc.BussinessLogic.BL_XemThongTin;
import com.example.cinebooker.PhanCongQuoc.adapter.XemThongTinAdapter;
import com.example.cinebooker.R;

import java.util.ArrayList;

public class xem_thong_tin_ve extends AppCompatActivity {
    private int maVe = -1;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xem_thong_tin_ve);
        ImageView back = findViewById(R.id.itemback);

        back.setOnClickListener(v -> onBackPressed());

        xemthongtin();
    }
    private void xemthongtin() {
        RecyclerView xemRecyclerView = findViewById(R.id.xemthongtin_recycle_view);
        BL_XemThongTin blXemThongTin = new BL_XemThongTin();
        sharedPreferences = getSharedPreferences("QuocDepTrai", MODE_PRIVATE);
        maVe = sharedPreferences.getInt("MaVe", -1);

        // Khởi tạo adapter và gắn vào RecyclerView
        XemThongTinAdapter adapter = new XemThongTinAdapter(this, new ArrayList<>());  // Truyền dữ liệu trống
        xemRecyclerView.setAdapter(adapter);

        // Load dữ liệu
        blXemThongTin.loadPhimToRecyclerView(this, xemRecyclerView, adapter, maVe);
    }
}