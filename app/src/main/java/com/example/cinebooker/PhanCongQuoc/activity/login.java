package com.example.cinebooker.PhanCongQuoc.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinebooker.LeDucThien.activity.home;
import com.example.cinebooker.R;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Gắn layout cho màn hình đăng nhập

        TextView dangKy = findViewById(R.id.register_lg);
        Button dangNhap = findViewById(R.id.login_btn);

        dangKy.setOnClickListener(v -> {
            Intent intent = new Intent(login.this, register.class);
            startActivity(intent);
        });

        EditText passwordInput = findViewById(R.id.password_input);
        final boolean[] isPasswordVisible = {false};

        // Xử lý hiển thị/ẩn mật khẩu
        passwordInput.setOnTouchListener((v, event) -> togglePasswordVisibility(v, event, passwordInput, isPasswordVisible));

        dangNhap.setOnClickListener(v -> {
            EditText emailInput = findViewById(R.id.email_input);
            String enteredEmail = emailInput.getText().toString();
            String enteredPassword = passwordInput.getText().toString();

            // Lấy thông tin đã lưu trong SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            String savedEmail = sharedPreferences.getString("email", "");
            String savedPassword = sharedPreferences.getString("password", "");

            // Kiểm tra thông tin đăng nhập
            if (enteredEmail.equals(savedEmail) && enteredPassword.equals(savedPassword)) {
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(login.this, home.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Email hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean togglePasswordVisibility(View v, MotionEvent event, EditText editText, boolean[] isPasswordVisible) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getRawX() >= (editText.getRight() - editText.getPaddingRight())) {
                if (isPasswordVisible[0]) {
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                isPasswordVisible[0] = !isPasswordVisible[0];
                editText.setSelection(editText.getText().length());
                return true;
            }
        }
        return false;
    }
}
