package com.example.cinebooker.PhanCongQuoc.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.BussinessLogic.BL_CapBac;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class cap_bac extends AppCompatActivity {

    private TextView tenKhachHangTextView;
    private TextView capBacTextView;
    private TextView hanMucTextView;
    private TextView thoiHanResetTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cap_bac);
          capbac();
        // Khởi tạo các TextView để hiển thị thông tin khách hàng
        tenKhachHangTextView = findViewById(R.id.username);
        capBacTextView = findViewById(R.id.capbac);
        hanMucTextView = findViewById(R.id.chitieu1);
        thoiHanResetTextView = findViewById(R.id.date_capbac);

        // Xử lý sự kiện quay lại
        ImageView back = findViewById(R.id.level_back);
        back.setOnClickListener(v -> onBackPressed());

        // Lấy MaKhachHang từ SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("QuocDepTrai", MODE_PRIVATE);
        int maKhachHang = sharedPreferences.getInt("user_id", -1);  // Mặc định là -1 nếu không tìm thấy

        if (maKhachHang != -1) {
            // Gọi phương thức để lấy thông tin khách hàng
            getCustomerDetails(maKhachHang);
        } else {
            Toast.makeText(this, "Không tìm thấy thông tin khách hàng", Toast.LENGTH_SHORT).show();
        }
    }

    // Phương thức gọi stored procedure và lấy thông tin khách hàng
    private void getCustomerDetails(int maKhachHang) {
        new Thread(() -> {
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                // Kết nối tới cơ sở dữ liệu
                connection = new ConnectionDatabase().getConnection();
                if (connection == null) {
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Không thể kết nối đến cơ sở dữ liệu", Toast.LENGTH_SHORT).show());
                    return;
                }

                // Gọi stored procedure
                String sql = "EXEC GetCustomerDetailsByMaKhachHang ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, maKhachHang);

                resultSet = statement.executeQuery();

                // Xử lý kết quả
                if (resultSet.next()) {
                    String tenKhachHang = resultSet.getString("TenKhachHang");
                    String tenCapBac = resultSet.getString("TenCapBac");
                    int hanMucChiTieu = resultSet.getInt("HanMucChiTieu");
                    String thoiHanReset = resultSet.getString("ThoiHanReset");

                    // Cập nhật giao diện người dùng
                    runOnUiThread(() -> {
                        tenKhachHangTextView.setText(tenKhachHang);
                        capBacTextView.setText(tenCapBac);
                        hanMucTextView.setText(String.valueOf(hanMucChiTieu));
                        thoiHanResetTextView.setText(thoiHanReset);
                    });

                } else {
                    runOnUiThread(() -> {
                        Toast.makeText(getApplicationContext(), "Không tìm thấy thông tin khách hàng", Toast.LENGTH_SHORT).show();
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> {
                    Toast.makeText(getApplicationContext(), "Lỗi khi truy vấn dữ liệu", Toast.LENGTH_SHORT).show();
                });
            } finally {
                try {
                    if (resultSet != null) resultSet.close();
                    if (statement != null) statement.close();
                    if (connection != null) connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void capbac() {
        RecyclerView capbacRecyclerView = findViewById(R.id.list_capbac);
        BL_CapBac blCapBac = new BL_CapBac();

        // Load dữ liệu từ BL_CapBac
        blCapBac.loadCapBacVertical(this, capbacRecyclerView);
    }
}
