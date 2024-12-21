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

import com.example.cinebooker.PhanCongQuoc.BussinessLogic.BL_HoanTien;
import com.example.cinebooker.PhanCongQuoc.adapter.YeuCauHoanTienAdapter;
import com.example.cinebooker.PhanCongQuoc.adapter.YeuCauHoanTienAdapter;
import com.example.cinebooker.R;

import java.util.ArrayList;

public class yeu_cau_hoan_tien extends AppCompatActivity {
    private int maVe_huy = -1;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_yeu_cau_hoan_tien);
        ImageView back = findViewById(R.id.itemback);

        back.setOnClickListener(v -> onBackPressed());
        ImageView lydohuy = findViewById(R.id.doimatkhau);

        HoanTien();

    }
    private void HoanTien() {
        RecyclerView hoanRecyclerView = findViewById(R.id.hoantien_recycle_view);
        BL_HoanTien blHoanTien = new BL_HoanTien();
        sharedPreferences = getSharedPreferences("QuocDepTrai", MODE_PRIVATE);
        maVe_huy = sharedPreferences.getInt("MaVe", -1);

        // Khởi tạo adapter và gắn vào RecyclerView
        YeuCauHoanTienAdapter adapter = new YeuCauHoanTienAdapter(this, new ArrayList<>());  // Truyền dữ liệu trống
        hoanRecyclerView.setAdapter(adapter);

        // Load dữ liệu
        blHoanTien.loadPhimToRecyclerView(this, hoanRecyclerView, adapter, maVe_huy);
    }
}