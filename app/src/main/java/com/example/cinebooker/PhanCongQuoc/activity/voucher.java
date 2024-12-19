package com.example.cinebooker.PhanCongQuoc.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_voucherAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.ticketvoucherMoviesEntity;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class voucher extends AppCompatActivity {

    private RecyclerView list_voucher;
    private Ticket_voucherAdapter ticketvoucherAdapter;
    private List<ticketvoucherMoviesEntity> ticketvoucherMoviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);
        ImageView back = findViewById(R.id.voucher_back);
        back.setOnClickListener(v -> onBackPressed());

        // Khởi tạo RecyclerView và thiết lập layout manager
        list_voucher = findViewById(R.id.list_voucher);
        list_voucher.setLayoutManager(new LinearLayoutManager(this));


    }
}
