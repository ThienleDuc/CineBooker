package com.example.cinebooker.PhanCongQuoc.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.BussinessLogic.BL_XuatVe;
import com.example.cinebooker.PhanCongQuoc.adapter.XuatVeAdapter;
import com.example.cinebooker.R;

import java.util.ArrayList;

public class xuat_ve extends AppCompatActivity {
    private int maVe = -1;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xuat_ve);

        // Nút quay lại
        ImageView back = findViewById(R.id.itemback);
        back.setOnClickListener(v-> onBackPressed());


        xuatve();
    }

    // Trong activity xuat_ve
    private void xuatve() {
        RecyclerView xuatRecyclerView = findViewById(R.id.xuatve_recycle_view);
        BL_XuatVe blXuatVe = new BL_XuatVe();
        sharedPreferences = getSharedPreferences("QuocDepTrai", MODE_PRIVATE);
        maVe = sharedPreferences.getInt("MaVe", -1);

        // Khởi tạo adapter và gắn vào RecyclerView
        XuatVeAdapter adapter = new XuatVeAdapter(this, new ArrayList<>());  // Truyền dữ liệu trống
        xuatRecyclerView.setAdapter(adapter);

        // Load dữ liệu
        blXuatVe.loadPhimToRecyclerView(this, xuatRecyclerView, adapter, maVe);
    }




}
