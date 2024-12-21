package com.example.cinebooker.LeDucThien.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class taiKhoan_BaoMat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan_bao_mat);

        ImageView back = findViewById(R.id.account_back);
        back.setOnClickListener(v -> finish());

        ImageView doimatkhauBtn = findViewById(R.id.doimatkhau);
        doimatkhauBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo AlertDialog để hiển thị form đổi mật khẩu
                AlertDialog.Builder builder = new AlertDialog.Builder(taiKhoan_BaoMat.this);
                View dialogView = getLayoutInflater().inflate(R.layout.doimatkhau, null);
                builder.setView(dialogView);

                EditText passCu = dialogView.findViewById(R.id.pass_cu);
                EditText passMoi = dialogView.findViewById(R.id.pass_moi);
                EditText passConfim = dialogView.findViewById(R.id.pass_confin);

                builder.setCancelable(true)
                        .setPositiveButton("Đổi mật khẩu", (dialog, id) -> {
                            String oldPassword = passCu.getText().toString();
                            String newPassword = passMoi.getText().toString();
                            String confirmPassword = passConfim.getText().toString();

                            // Lấy MaKhachHang từ SharedPreferences
                            SharedPreferences sharedPreferences = getSharedPreferences("QuocDepTrai", MODE_PRIVATE);
                            int maKhachHang = sharedPreferences.getInt("user_id", -1);

                            if (maKhachHang != -1) {
                                if (newPassword.equals(confirmPassword)) {
                                    // Thực hiện đổi mật khẩu
                                    changePassword(maKhachHang, oldPassword, newPassword);
                                } else {
                                    Toast.makeText(taiKhoan_BaoMat.this, "Mật khẩu mới không khớp!", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(taiKhoan_BaoMat.this, "Không tìm thấy thông tin người dùng!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Hủy", (dialog, id) -> dialog.dismiss());

                builder.create().show();
            }
        });
    }

    private void changePassword(int maKhachHang, String oldPassword, String newPassword) {
        new Thread(() -> {
            Connection connection = null;
            PreparedStatement checkStmt = null;
            PreparedStatement updateStmt = null;
            ResultSet resultSet = null;

            try {
                connection = new ConnectionDatabase().getConnection();
                if (connection == null) {
                    runOnUiThread(() -> Toast.makeText(this, "Lỗi kết nối cơ sở dữ liệu!", Toast.LENGTH_SHORT).show());
                    return;
                }

                // Kiểm tra mật khẩu cũ
                String checkQuery = "SELECT MatKhau FROM KhachHang WHERE MaKhachHang = ?";
                checkStmt = connection.prepareStatement(checkQuery);
                checkStmt.setInt(1, maKhachHang);
                resultSet = checkStmt.executeQuery();

                if (resultSet.next()) {
                    String currentPassword = resultSet.getString("MatKhau");
                    if (currentPassword.equals(oldPassword)) {
                        // Cập nhật mật khẩu mới
                        String updateQuery = "UPDATE KhachHang SET MatKhau = ? WHERE MaKhachHang = ?";
                        updateStmt = connection.prepareStatement(updateQuery);
                        updateStmt.setString(1, newPassword);
                        updateStmt.setInt(2, maKhachHang);

                        int rowsUpdated = updateStmt.executeUpdate();
                        if (rowsUpdated > 0) {
                            runOnUiThread(() -> Toast.makeText(this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show());
                        } else {
                            runOnUiThread(() -> Toast.makeText(this, "Đổi mật khẩu thất bại!", Toast.LENGTH_SHORT).show());
                        }
                    } else {
                        runOnUiThread(() -> Toast.makeText(this, "Mật khẩu cũ không đúng!", Toast.LENGTH_SHORT).show());
                    }
                } else {
                    runOnUiThread(() -> Toast.makeText(this, "Không tìm thấy tài khoản!", Toast.LENGTH_SHORT).show());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) resultSet.close();
                    if (checkStmt != null) checkStmt.close();
                    if (updateStmt != null) updateStmt.close();
                    if (connection != null) connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
