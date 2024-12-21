package com.example.cinebooker.PhanCongQuoc.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinebooker.LeDucThien.activity.home;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login); // Gắn layout cho màn hình đăng nhập

        TextView dangKy = findViewById(R.id.register_lg);
        Button dangNhap = findViewById(R.id.login_btn);

        // Chuyển sang màn hình đăng ký
        dangKy.setOnClickListener(v -> {
            Intent intent = new Intent(login.this, register.class);
            startActivity(intent);
        });

        EditText passwordInput = findViewById(R.id.password_input);
        final boolean[] isPasswordVisible = {false};

        // Xử lý hiển thị/ẩn mật khẩu
        // Xử lý hiển thị/ẩn mật khẩu
        passwordInput.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                // Kiểm tra nếu người dùng nhấn vào drawableEnd
                if (event.getRawX() >= (passwordInput.getRight() - passwordInput.getCompoundDrawables()[2].getBounds().width())) {
                    if (isPasswordVisible[0]) {
                        // Đổi sang trạng thái ẩn mật khẩu
                        passwordInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        passwordInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.mat_dong, 0); // Biểu tượng hiển thị
                    } else {
                        // Đổi sang trạng thái hiển thị mật khẩubaseline_remove_red_eye_24
                        passwordInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        passwordInput.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_remove_red_eye_24, 0); // Biểu tượng ẩn
                    }
                    isPasswordVisible[0] = !isPasswordVisible[0];
                    passwordInput.setSelection(passwordInput.getText().length()); // Đặt lại con trỏ văn bản
                    return true;
                }
            }
            return false;
        });

        // Xử lý sự kiện nhấn nút "Đăng nhập"
        dangNhap.setOnClickListener(v -> {
            EditText emailInput = findViewById(R.id.email_input);
            String enteredEmail = emailInput.getText().toString().trim();
            String enteredPassword = passwordInput.getText().toString().trim();

            if (enteredEmail.isEmpty() || enteredPassword.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ email và mật khẩu!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Kiểm tra thông tin trong cơ sở dữ liệu
            new Thread(() -> {
                boolean isValid = checkUserCredentials(enteredEmail, enteredPassword);
                runOnUiThread(() -> {
                    if (isValid) {
                        // Lưu MaKhachHang vào SharedPreferences
                        SharedPreferences sharedPreferences = getSharedPreferences("QuocDepTrai", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("user_id", getMaKhachHangFromDatabase(enteredEmail));  // Lưu MaKhachHang
                        editor.apply();

                        Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this, home.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Email hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        });
    }



    // Phương thức kiểm tra thông tin đăng nhập từ cơ sở dữ liệu
    private boolean checkUserCredentials(String email, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return false;
            }

            String sql = "SELECT COUNT(*) FROM KhachHang WHERE Email = ? AND MatKhau = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Đúng nếu có người dùng phù hợp
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi kiểm tra thông tin đăng nhập: ", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                Log.e(TAG, "Lỗi khi đóng kết nối: ", e);
            }
        }
        return false; // Đăng nhập thất bại
    }

    // Phương thức lấy MaKhachHang từ cơ sở dữ liệu
    private int getMaKhachHangFromDatabase(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int maKhachHang = -1;

        try {
            connection = new ConnectionDatabase().getConnection();
            if (connection == null) {
                Log.e(TAG, "Không thể kết nối đến cơ sở dữ liệu.");
                return maKhachHang;
            }

            String sql = "SELECT MaKhachHang FROM KhachHang WHERE Email = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, email);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                maKhachHang = resultSet.getInt("MaKhachHang");
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi lấy MaKhachHang: ", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                Log.e(TAG, "Lỗi khi đóng kết nối: ", e);
            }
        }
        return maKhachHang;
    }

    // Method to retrieve the stored MaKhachHang from SharedPreferences
    public int getStoredUserId() {
        SharedPreferences sharedPreferences = getSharedPreferences("QuocDepTrai", MODE_PRIVATE);
        return sharedPreferences.getInt("user_id", -1);  // Default to -1 if user_id is not found
    }
}
