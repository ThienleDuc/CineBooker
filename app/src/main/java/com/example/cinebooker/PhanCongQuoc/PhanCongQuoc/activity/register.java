package com.example.cinebooker.PhanCongQuoc.PhanCongQuoc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cinebooker.PhanCongQuoc.activity.login;
import com.example.cinebooker.generalMethod.ConnectionDatabase;
import androidx.appcompat.app.AppCompatActivity;


import com.example.cinebooker.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerButton = findViewById(R.id.register_btn);
        EditText usernameInput = findViewById(R.id.username_input);
        EditText emailInput = findViewById(R.id.email_input);
        EditText passwordInput = findViewById(R.id.password_input);
        EditText confirmPasswordInput = findViewById(R.id.reenter_password_input);

        final boolean[] isPasswordVisible = {false}; // Biến theo dõi trạng thái hiển thị mật khẩu
        // Xử lý hiển thị/ẩn mật khẩu
        passwordInput.setOnTouchListener((v, event) -> togglePasswordVisibility(v, event, passwordInput, isPasswordVisible));
        confirmPasswordInput.setOnTouchListener((v, event) -> togglePasswordVisibility(v, event, confirmPasswordInput, isPasswordVisible));

        registerButton.setOnClickListener(v -> {
            String username = usernameInput.getText().toString().trim();
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            String confirmPassword = confirmPasswordInput.getText().toString().trim();

            // Kiểm tra dữ liệu đầu vào
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Mật khẩu nhập lại không khớp!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Kiểm tra và thêm khách hàng
            new Thread(() -> {
                if (checkUserExists(username, email)) {
                    runOnUiThread(() -> Toast.makeText(this, "Tên hoặc Email đã được sử dụng!", Toast.LENGTH_SHORT).show());
                } else {
                    boolean success = addUser(username, email, password);
                    runOnUiThread(() -> {
                        if (success) {
                            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(register.this, login.class));
                            finish();
                        } else {
                            Toast.makeText(this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).start();
        });
    }

    // Kiểm tra khách hàng đã tồn tại hay chưa
    private boolean checkUserExists(String customerName, String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e("RegisterActivity", "Không thể kết nối đến cơ sở dữ liệu.");
                return false;
            }

            String sql = "SELECT COUNT(*) FROM KhachHang WHERE TenKhachHang = ? OR Email = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, customerName);
            statement.setString(2, email);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Có khách hàng tồn tại
            }
        } catch (Exception e) {
            Log.e("RegisterActivity", "Lỗi khi kiểm tra khách hàng: ", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                Log.e("RegisterActivity", "Lỗi khi đóng kết nối: ", e);
            }
        }
        return false;
    }

    // Thêm khách hàng mới
    private boolean addUser(String customerName, String email, String password) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e("RegisterActivity", "Không thể kết nối đến cơ sở dữ liệu.");
                return false;
            }
            String sql = "INSERT INTO KhachHang (TenKhachHang, Email, MatKhau, AnhKhachHang) VALUES (?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, customerName);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, "default_user_image.png"); // Ảnh mặc định của người dùng

            return statement.executeUpdate() > 0; // Trả về true nếu thêm thành công
        } catch (Exception e) {
            Log.e("RegisterActivity", "Lỗi khi thêm khách hàng: ", e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                Log.e("RegisterActivity", "Lỗi khi đóng kết nối: ", e);
            }
        }
        return false;
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
