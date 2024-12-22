package com.example.cinebooker.LeDucThien.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_TinhThanh;
import com.example.cinebooker.LeDucThien.adapter.TinhThanhAdapter;
import com.example.cinebooker.R;


public class DanhSachDiaDiemRap extends AppCompatActivity {
    private EditText editText;
    private TextWatcher textWatcher;
    private BL_TinhThanh blTinhThanh;
    private TinhThanhAdapter tinhThanhAdapter;
    private RecyclerView recyclerView;
    private String previousSearch = ""; // Lưu lại tìm kiếm trước đó

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_dia_diem_rap);

        // Bảo vệ RecyclerView null
        recyclerView = findViewById(R.id.diachirap_recyclerview);
        if (recyclerView == null) {
            throw new IllegalStateException("RecyclerView is null. Check the layout file.");
        }

        // Khởi tạo SharedViewModel và BL_TinhThanh
        blTinhThanh = new BL_TinhThanh();
        // Khởi tạo Adapter
        tinhThanhAdapter = new TinhThanhAdapter(this);
        // Load dữ liệu ban đầu và thêm Log để kiểm tra dữ liệu
        Log.d("BL_TinhThanh", "Loading initial data for RecyclerView...");
        blTinhThanh.loadTenTinhThanhToRecyclerView(this, recyclerView, tinhThanhAdapter);

        // Khởi tạo các view khác
        ImageView close = findViewById(R.id.danhsachdiadiemrap_close);
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
                        blTinhThanh.loadTenTinhThanhToRecyclerViewAfterSearch(
                                DanhSachDiaDiemRap.this,
                                recyclerView,
                                tinhThanhAdapter,
                                input
                        );
                    } else {
                        Log.d("Search", "Clearing search input");
                        blTinhThanh.loadTenTinhThanhToRecyclerView(DanhSachDiaDiemRap.this, recyclerView, tinhThanhAdapter);
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

        // Sự kiện đóng Activity
        close.setOnClickListener(v -> {
            Log.d("Activity", "Closing the activity");  // Log đóng activity
            finish();
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        editText.setText(""); // Xóa nội dung tìm kiếm khi quay lại Activity
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Loại bỏ TextWatcher khi Activity bị hủy
        if (editText != null) {
            editText.removeTextChangedListener(textWatcher);
        }
    }

    // Phương thức đóng Activity từ bên ngoài
    public void dongActivity() {
        finish();
    }
}
