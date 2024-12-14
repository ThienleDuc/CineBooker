package com.example.cinebooker.LeDucThien.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_TinhThanh;
import com.example.cinebooker.LeDucThien.adapter.TinhThanhAdapter;
import com.example.cinebooker.R;

public class DanhSachDiaDiemRap extends AppCompatActivity {
    protected EditText editText;
    private TextWatcher textWatcher;
    private BL_TinhThanh blTinhThanh;
    private TinhThanhAdapter tinhThanhAdapter;
    private RecyclerView recyclerView;
    private String previousSearch = ""; // Lưu lại tìm kiếm trước đó để tránh làm việc không cần thiết

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_dia_diem_rap);

        recyclerView = findViewById(R.id.diachirap_recyclerview);
        editText = findViewById(R.id.header_search_input);
        ImageView close = findViewById(R.id.danhsachdiadiemrap_close);

        blTinhThanh = new BL_TinhThanh();
        tinhThanhAdapter = new TinhThanhAdapter();

        // Load dữ liệu ban đầu
        blTinhThanh.loadTenTinhThanhToRecyclerView(this, recyclerView, tinhThanhAdapter);

        // TextWatcher chỉ thêm khi EditText có focus
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Không cần làm gì ở đây
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Không cần làm gì ở đây
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString().trim();

                // Chỉ khi tìm kiếm thay đổi mới cập nhật dữ liệu
                if (!input.equals(previousSearch)) {
                    previousSearch = input; // Lưu lại tìm kiếm hiện tại

                    if (!input.isEmpty()) {
                        blTinhThanh.loadTenTinhThanhToRecyclerViewAfterSearch(
                                DanhSachDiaDiemRap.this,
                                recyclerView,
                                tinhThanhAdapter,
                                input
                        );
                    } else {
                        blTinhThanh.loadTenTinhThanhToRecyclerView(DanhSachDiaDiemRap.this, recyclerView, tinhThanhAdapter);
                    }
                }
            }
        };

        // Xử lý sự kiện focus
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                editText.addTextChangedListener(textWatcher);
            } else {
                editText.removeTextChangedListener(textWatcher);
            }
        });

        // Sự kiện đóng Activity
        close.setOnClickListener(v -> finish());
    }

    @Override
    protected void onResume() {
        super.onResume();
        editText.setText(""); // Xóa nội dung khi quay lại Activity
    }

    // Phương thức đóng Activity
    public void dongActivity() {
        finish();
    }
}
