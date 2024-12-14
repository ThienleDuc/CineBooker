package com.example.cinebooker.LeDucThien.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_RapChieu;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_RapChieuCon;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_TinhThanh;
import com.example.cinebooker.LeDucThien.adapter.RapChieuAdapter;
import com.example.cinebooker.LeDucThien.adapter.RapChieuConAdapter;
import com.example.cinebooker.R;

public class danhSachRap extends AppCompatActivity {

    private int _maRapChieu = -1;
    private int _maTinhThanh = -1;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danh_sach_rap);

        // Đóng Activity khi nhấn nút close
        ImageView close = findViewById(R.id.danhsachrap_close);
        close.setOnClickListener(v -> finish());

        danhSachRap();
        danhSachDiaChiRap();
    }


    private void danhSachRap() {
        // Hiển thị danh sách các rạp chiếu
        RecyclerView recyclerView = findViewById(R.id.danhsachrap_recycle_view);
        BL_RapChieu blRapChieu = new BL_RapChieu();
        RapChieuAdapter adapter = new RapChieuAdapter();
        blRapChieu.loadRapChieuToRecyclerView(this, recyclerView, adapter);
    }

    private void danhSachDiaChiRap() {
        // Hiển thị danh sách địa chỉ rạp chiếu
        RecyclerView recyclerView = findViewById(R.id.diachirap_recyclerview);
        BL_RapChieuCon blRapChieuCon = new BL_RapChieuCon();
        BL_TinhThanh blTinhThanh = new BL_TinhThanh();
        BL_RapChieu blRapChieu = new BL_RapChieu();

        sharedPreferences = getSharedPreferences("LeDucThien", MODE_PRIVATE);
        _maRapChieu = sharedPreferences.getInt("maRapChieu", -1);
        if (_maRapChieu == -1 ) {
            _maRapChieu = blRapChieu.loadMinMaRapChieu();
            editor = sharedPreferences.edit();
            editor.putInt("maRapChieu", _maRapChieu);
            editor.apply();;
        }

        _maTinhThanh = sharedPreferences.getInt("maTinhThanh", -1);

        RapChieuConAdapter adapter = new RapChieuConAdapter();
        blRapChieuCon.loadRapChieuConToRecyclerView(this, recyclerView, _maTinhThanh,_maRapChieu, adapter);
    }

    // Phương thức đóng Activity
    public void dongActivity() {
        finish();
    }
}
