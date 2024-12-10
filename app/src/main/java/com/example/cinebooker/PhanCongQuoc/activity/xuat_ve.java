package com.example.cinebooker.PhanCongQuoc.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.BussinessLogic.BL_XuatVe;
import com.example.cinebooker.R;

public class xuat_ve extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xuat_ve);

        // Nút quay lại
        ImageView back = findViewById(R.id.itemback);
        back.setOnClickListener(v -> onBackPressed());

        // Gọi hàm xuất vé
        xuatve();
    }

    // Trong activity xuat_ve
    private void xuatve() {
        // Tìm RecyclerView từ layout
        RecyclerView xuatRecyclerView = findViewById(R.id.xuatve_recycle_view);
        int maVe = 2; // Ví dụ mã vé là 3

        // Tạo đối tượng BL_XuatVe và gọi phương thức loadXuatVeVertical
        BL_XuatVe blXuatVe = new BL_XuatVe();
        blXuatVe.loadXuatVeVertical(this, xuatRecyclerView, maVe); // Truyền maVe vào
    }


}
