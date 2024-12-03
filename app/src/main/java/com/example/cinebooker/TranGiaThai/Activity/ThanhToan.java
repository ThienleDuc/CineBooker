package com.example.cinebooker.TranGiaThai.Activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Adapter.PTTT_Adapter;
import com.example.cinebooker.TranGiaThai.Entity.PTTT_Entity;

import java.util.ArrayList;
import java.util.List;

public class ThanhToan extends AppCompatActivity {

    private RecyclerView PTTTRecyclerView;
    private PTTT_Adapter PTTTAdapter;
    private List<PTTT_Entity> PTTTList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thanh_toan);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initRecyclerView();
    }

    private void initRecyclerView() {
        PTTTRecyclerView = findViewById(R.id.Rcv_PTTT);
        PTTTRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Dữ liệu mẫu
        PTTTList = new ArrayList<>();
        PTTTList.add(new PTTT_Entity("Momo", R.drawable.momo, "Số dư: 123456", "Nạp thêm"));
        PTTTList.add(new PTTT_Entity("ZaloPay", R.drawable.zal, "Số dư: 123456", "Nạp thêm"));
        PTTTList.add(new PTTT_Entity("Visa", R.drawable.visa, "Số dư: 123456", "Nạp thêm"));
        PTTTList.add(new PTTT_Entity("MasterCard", R.drawable.card, "Số dư: 123456", "Nạp thêm"));

        PTTTAdapter = new PTTT_Adapter(PTTTList);
        PTTTRecyclerView.setAdapter(PTTTAdapter);
    }
}
