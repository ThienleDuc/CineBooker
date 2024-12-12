package com.example.cinebooker.LeDucThien.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_TinhThanh;
import com.example.cinebooker.LeDucThien.adapter.TinhThanhAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_TinhThanh;
import com.example.cinebooker.R;
import com.example.cinebooker.LeDucThien.fragment.kham_pha;

import java.util.List;

public class DanhSachDiaDiemRap extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TinhThanhAdapter tinhThanhAdapter;
    private TextView tenTinhThanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_dia_diem_rap);

        recyclerView = findViewById(R.id.diachirap_recyclerview);

        tinhThanhAdapter = new TinhThanhAdapter();
        recyclerView.setAdapter(tinhThanhAdapter);

        // Giả sử dữ liệu đã được load vào adapter
        BL_TinhThanh blTinhThanh = new BL_TinhThanh();
        TinhThanhAdapter adapter = new TinhThanhAdapter();
        blTinhThanh.loadTenTinhThanhToRecyclerView(this, recyclerView, adapter);

        ImageView close = findViewById(R.id.danhsachdiadiemrap_close);
        close.setOnClickListener(v->finish());

    }

    // Phương thức đóng Activity
    public void dongActivity() {
        finish();
    }
}
