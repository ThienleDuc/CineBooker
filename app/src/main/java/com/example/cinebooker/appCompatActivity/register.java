package com.example.cinebooker.appCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cinebooker.R;

import java.util.Calendar;

public class register extends AppCompatActivity {

    private EditText dateInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Khởi tạo EditText cho ngày sinh
        dateInput = findViewById(R.id.date_input);

        // Thiết lập listener cho EditText
        dateInput.setOnClickListener(v -> showDatePicker());

        // Thiết lập padding cho hệ thống
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Thiết lập sự kiện cho TextView quay lại
        TextView back = findViewById(R.id.back_register);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(register.this, login.class);
            startActivity(intent);
        });

        // Thiết lập sự kiện cho Button đăng ký
        Button registerButton = findViewById(R.id.register_btn);
        registerButton.setOnClickListener(v -> {
            Intent intent = new Intent(register.this, login.class);
            startActivity(intent);
        });
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Định dạng và gán ngày đã chọn vào EditText
                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    dateInput.setText(selectedDate);
                },
                year, month, day
        );
        datePickerDialog.show();
    }
}
