
package com.example.cinebooker.PhanCongQuoc.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinebooker.LeDucThien.activity.home;
import com.example.cinebooker.PhanCongQuoc.activity.register;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ActivityOpen;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        TextView dangKy = findViewById(R.id.register_lg);
        Button dangNhap = findViewById(R.id.login_btn);
        Button ggDangNhap = findViewById(R.id.login_btn_1);

        dangKy.setOnClickListener(v -> ActivityOpen.openActivityOnClick(this, register.class, R.id.register_lg));
        ggDangNhap.setOnClickListener(v -> ActivityOpen.openActivityOnClick(this, home.class, R.id.login_btn_1));

        // Xử lý sự kiện hiển thị/ẩn mật khẩu
        EditText passwordInput = findViewById(R.id.password_input);

        // Biến để theo dõi trạng thái hiển thị mật khẩu
        final boolean[] isPasswordVisible = {false};

        passwordInput.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (passwordInput.getRight()
                        - passwordInput.getPaddingRight())) {
                    // Đổi trạng thái hiển thị mật khẩu
                    if (isPasswordVisible[0]) {
                        passwordInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        passwordInput.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.baseline_remove_red_eye_24, 0);
                    } else {
                        passwordInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        passwordInput.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.icons8_eye, 0);
                    }
                    isPasswordVisible[0] = !isPasswordVisible[0];
                    // Đặt lại con trỏ sau cùng
                    passwordInput.setSelection(passwordInput.getText().length());
                    return true;
                }
            }
            return false;
        });
        // Xử lý đăng nhập
        dangNhap.setOnClickListener(v -> {
            EditText emailInput = findViewById(R.id.email_input); // Thay đổi từ usernameInput thành emailInput
            EditText passwordInput_1 = findViewById(R.id.password_input);

            String enteredEmail = emailInput.getText().toString();  // Lấy email người dùng nhập
            String enteredPassword = passwordInput_1.getText().toString();  // Lấy mật khẩu người dùng nhập

            // Lấy thông tin từ SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            String savedEmail = sharedPreferences.getString("email", "");  // Lấy email đã lưu trong SharedPreferences
            String savedPassword = sharedPreferences.getString("password", "");  // Lấy mật khẩu đã lưu trong SharedPreferences

            // Kiểm tra email và mật khẩu
            if (enteredEmail.equals(savedEmail) && enteredPassword.equals(savedPassword)) {
                // Đăng nhập thành công
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(login.this, home.class);
                startActivity(intent);
                finish();
            } else {
                // Đăng nhập thất bại
                Toast.makeText(this, "Email hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
