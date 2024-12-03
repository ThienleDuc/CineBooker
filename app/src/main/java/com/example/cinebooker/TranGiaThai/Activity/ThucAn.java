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
import com.example.cinebooker.TranGiaThai.Adapter.Thucan_Adapter;
import com.example.cinebooker.TranGiaThai.Entity.Thucan_Entity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ThucAn extends AppCompatActivity {

    private RecyclerView ThucAnRecyclerView;
    private Thucan_Adapter ThucAnAdapter;
    private List<Thucan_Entity> ThucAnList;
    private Button btn_thanhtoan_thucan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thuc_an);

        // Apply window insets for proper padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Setup RecyclerView and adapter
        ThucAnRecyclerView = findViewById(R.id.Rcv_ThucAn);
        ThucAnRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ThucAnList = new ArrayList<>();
        ThucAnList.add(new Thucan_Entity(R.drawable.food1, "Combo Bắp Nước 1", "90.000đ", R.drawable.add, R.drawable.subtract));
        ThucAnList.add(new Thucan_Entity(R.drawable.food1, "Combo Bắp Nước 2", "90.000đ", R.drawable.add, R.drawable.subtract));
        ThucAnList.add(new Thucan_Entity(R.drawable.food1, "Combo Bắp Nước 3", "90.000đ", R.drawable.add, R.drawable.subtract));
        ThucAnList.add(new Thucan_Entity(R.drawable.food1, "Combo Bắp Nước 4", "90.000đ", R.drawable.add, R.drawable.subtract));
        ThucAnList.add(new Thucan_Entity(R.drawable.food1, "Combo Bắp Nước 5", "90.000đ", R.drawable.add, R.drawable.subtract));
        ThucAnList.add(new Thucan_Entity(R.drawable.food1, "Combo Bắp Nước 6", "90.000đ", R.drawable.add, R.drawable.subtract));
        ThucAnList.add(new Thucan_Entity(R.drawable.food1, "Combo Bắp Nước 7", "90.000đ", R.drawable.add, R.drawable.subtract));
        ThucAnList.add(new Thucan_Entity(R.drawable.food1, "Combo Bắp Nước 8", "90.000đ", R.drawable.add, R.drawable.subtract));
        ThucAnList.add(new Thucan_Entity(R.drawable.food1, "Combo Bắp Nước 9", "90.000đ", R.drawable.add, R.drawable.subtract));

        ThucAnAdapter = new Thucan_Adapter(ThucAnList);
        ThucAnRecyclerView.setAdapter(ThucAnAdapter);

        // Handle the payment button click
        btn_thanhtoan_thucan = findViewById(R.id.btn_thanhtoan_thucan);
    }
}
