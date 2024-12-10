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

import com.example.cinebooker.R;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); // Gắn layout cho màn hình đăng ký

        TextView quayLai = findViewById(R.id.back_register);
        quayLai.setOnClickListener(v -> onBackPressed()); // Quay lại màn hình trước khi đăng ký

        Button dangKy = findViewById(R.id.register_btn); // Nút đăng ký

        EditText passwordInput = findViewById(R.id.password_input);
        EditText confirmPasswordInput = findViewById(R.id.reenter_password_input);
        final boolean[] isPasswordVisible = {false}; // Biến theo dõi trạng thái hiển thị mật khẩu

        // Xử lý hiển thị/ẩn mật khẩu
        passwordInput.setOnTouchListener((v, event) -> togglePasswordVisibility(v, event, passwordInput, isPasswordVisible));
        confirmPasswordInput.setOnTouchListener((v, event) -> togglePasswordVisibility(v, event, confirmPasswordInput, isPasswordVisible));

        // Xử lý khi nhấn nút Đăng ký
        dangKy.setOnClickListener(v -> {
            EditText usernameInput = findViewById(R.id.username_input);
            EditText emailInput = findViewById(R.id.email_input);

            String username = usernameInput.getText().toString();
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();
            String reenterPassword = confirmPasswordInput.getText().toString();

            // Kiểm tra ràng buộc
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || reenterPassword.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(reenterPassword)) {
                Toast.makeText(this, "Mật khẩu nhập lại không khớp!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lưu thông tin vào SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("email", email); // Lưu email
            editor.putString("password", password); // Lưu mật khẩu
            editor.apply();

            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

            // Chuyển đến màn hình đăng nhập
            Intent intent = new Intent(register.this, login.class);
            startActivity(intent);
            finish();
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
