package com.example.cinebooker.LeDucThien.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_RapChieu;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_RapChieuCon;
import com.example.cinebooker.LeDucThien.adapter.RapChieuAdapter;
import com.example.cinebooker.LeDucThien.adapter.RapChieuConAdapter;
import com.example.cinebooker.R;

public class danhSachRap extends AppCompatActivity {
    private EditText editText;
    private TextWatcher textWatcher;
    private String previousSearch = "";
    private RapChieuConAdapter rapChieuConAdapter;
    private RecyclerView recyclerView1;
    private BL_RapChieuCon blRapChieuCon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danh_sach_rap);

        // Đóng Activity khi nhấn nút close
        ImageView close = findViewById(R.id.danhsachrap_close);
        close.setOnClickListener(v -> finish());

        danhSachRapChieu();
        danhSachDiaChiRap();
    }

    private void danhSachRapChieu() {
        // Hiển thị danh sách các rạp chiếu
        RecyclerView recyclerView = findViewById(R.id.danhsachrap_recycle_view);
        BL_RapChieu blRapChieu = new BL_RapChieu();
        RapChieuAdapter adapter = new RapChieuAdapter(this);
        blRapChieu.loadRapChieuToRecyclerView(this, recyclerView, adapter);
    }

    private void danhSachDiaChiRap() {
        // Hiển thị danh sách địa chỉ rạp chiếu
        recyclerView1 = findViewById(R.id.diachirap_recyclerview);
        blRapChieuCon = new BL_RapChieuCon();
        rapChieuConAdapter = new RapChieuConAdapter(this);

        int _maRapChieu = rapChieuConAdapter.getMaRapChieu();
        int _maTinhThanh = rapChieuConAdapter.getMaTinhThanh();

        blRapChieuCon.loadRapChieuConToRecyclerView(this, recyclerView1, _maTinhThanh,_maRapChieu, rapChieuConAdapter);

        editText = findViewById(R.id.header_search_input);

        // TextWatcher để xử lý tìm kiếm
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Không cần xử lý
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Không cần xử lý
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString().trim();

                // Cập nhật dữ liệu chỉ khi từ khóa tìm kiếm thay đổi
                if (!input.equals(previousSearch)) {
                    previousSearch = input;

                    if (!input.isEmpty()) {
                        Log.d("Search", "Searching for: " + input);  // Log tìm kiếm
                        blRapChieuCon.loadRapChieuConToRecyclerViewAfterSearch(
                                danhSachRap.this,
                                recyclerView1,
                                _maTinhThanh,
                                _maRapChieu,
                                input,
                                rapChieuConAdapter);
                    } else {
                        Log.d("Search", "Clearing search input");
                    }
                }
            }
        };

        // Xử lý sự kiện focus của EditText
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                editText.addTextChangedListener(textWatcher);
            } else {
                editText.removeTextChangedListener(textWatcher);
            }
        });
    }

    // Phương thức đóng Activity
    public void dongActivity() {
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        editText.setText("");
    }

    public RapChieuConAdapter getRapChieuConAdapter() {
        return rapChieuConAdapter;
    }

    public RecyclerView getRecyclerView1() {
        return recyclerView1;
    }

    public BL_RapChieuCon getBlRapChieuCon() {
        return blRapChieuCon;
    }
}