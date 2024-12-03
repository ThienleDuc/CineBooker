package com.example.cinebooker.TranGiaThai.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Adapter.Ghe_Adapter;
import com.example.cinebooker.TranGiaThai.Entity.Ghe_Entity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ChonGhe extends AppCompatActivity {

    private RecyclerView GheRecyclerView;
    private Ghe_Adapter GheAdapter;
    private List<Ghe_Entity> GheList;
    private Button continueButton;
    private Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chon_ghe);

        // Cài đặt Insets cho giao diện
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khởi tạo RecyclerView và các thành phần khác
        initUI();
    }

    private void initUI() {
        GheRecyclerView = findViewById(R.id.Rcv_ghengoi);
        GheRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        GheList = new ArrayList<>();
        GheList.add(new Ghe_Entity(R.drawable.sofa, "Ghế thường", "30/30"));
        GheList.add(new Ghe_Entity(R.drawable.sofa_a, "Ghế VIP", "50/50"));
        GheList.add(new Ghe_Entity(R.drawable.sofa_b, "Ghế SweetBox", "10/10"));
        GheList.add(new Ghe_Entity(R.drawable.sofa_c, "Ghế Đã Đặt", "0"));
        GheList.add(new Ghe_Entity(R.drawable.sofa_d, "Ghế Bạn Chọn", "0"));

        GheAdapter = new Ghe_Adapter(GheList);
        GheRecyclerView.setAdapter(GheAdapter);

    }
}
