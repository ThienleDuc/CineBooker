package com.example.cinebooker.TranGiaThai.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Adapter.Khunggiochieu_Adapter;
import com.example.cinebooker.TranGiaThai.Adapter.Rap_Adapter;
import com.example.cinebooker.TranGiaThai.Entity.Khunggiochieu_Entity;
import com.example.cinebooker.TranGiaThai.Entity.Rap_Entity;
import com.example.cinebooker.generalMethod.ActivityOpen;

import java.util.ArrayList;
import java.util.List;

public class XemChiTietPhim extends AppCompatActivity {

    private RecyclerView KCHRecyclerView, RapRecyclerView;
    private Khunggiochieu_Adapter KHCAdapter;
    private List<Khunggiochieu_Entity> KCHList;
    private Rap_Adapter RapAdapter;
    private List<Rap_Entity> RapList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xem_chi_tiet_phim);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupKhungGioChieu();
        setupRap();
        setupMap();
    }

    private void setupKhungGioChieu() {
        KCHRecyclerView = findViewById(R.id.Rcv_khunggiochieu);
        KCHRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        KCHList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            KCHList.add(new Khunggiochieu_Entity("10:00", "70/100"));
        }
        KHCAdapter = new Khunggiochieu_Adapter(KCHList);
        KCHRecyclerView.setAdapter(KHCAdapter);
    }

    private void setupRap() {
        RapRecyclerView = findViewById(R.id.Rcv_Rap);
        RapRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        RapList = new ArrayList<>();
        RapList.add(new Rap_Entity(R.drawable.cgv, "Rạp 1", R.drawable.placeholder));
        RapList.add(new Rap_Entity(R.drawable.galaxy, "Rạp 2", R.drawable.placeholder));
        RapList.add(new Rap_Entity(R.drawable.lotte_icon, "Rạp 3", R.drawable.placeholder));

        RapAdapter = new Rap_Adapter(RapList);
        RapRecyclerView.setAdapter(RapAdapter);

        ActivityOpen.openActivityOnClick(this, ChonGhe.class, R.id.btn_tieptuc);
    }

    private void setupMap() {
        ActivityOpen.openActivityOnClick(this, SecondActivity.class, R.id.LN1);
    }
}
