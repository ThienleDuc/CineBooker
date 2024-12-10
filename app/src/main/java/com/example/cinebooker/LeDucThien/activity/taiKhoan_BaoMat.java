
package com.example.cinebooker.LeDucThien.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cinebooker.R;

public class taiKhoan_BaoMat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tai_khoan_bao_mat);


        ImageView back = findViewById(R.id.account_back);

        back.setOnClickListener(v -> onBackPressed());

        // Sự kiện khi bấm vào ImageView "doimatkhau"

        ImageView doimatkhauBtn = findViewById(R.id.doimatkhau);
        doimatkhauBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo AlertDialog để hiển thị form đổi mật khẩu
                AlertDialog.Builder builder = new AlertDialog.Builder(taiKhoan_BaoMat.this);
                View dialogView = getLayoutInflater().inflate(R.layout.doimatkhau, null);
                builder.setView(dialogView);

                // Lấy các EditText trong Dialog
                EditText passCu = dialogView.findViewById(R.id.pass_cu);
                EditText passMoi = dialogView.findViewById(R.id.pass_moi);
                EditText passConfim = dialogView.findViewById(R.id.pass_confin);

                // Hiển thị dialog
                builder.setCancelable(true)
                        .setPositiveButton("Đổi mật khẩu", (dialog, id) -> {
                            // Lấy dữ liệu từ các EditText và thực hiện logic thay đổi mật khẩu
                            String oldPassword = passCu.getText().toString();
                            String newPassword = passMoi.getText().toString();
                            String confirmPassword = passConfim.getText().toString();

                            // Logic xử lý đổi mật khẩu (ví dụ kiểm tra mật khẩu cũ và mật khẩu mới)
                            if (newPassword.equals(confirmPassword)) {
                                // Thực hiện logic đổi mật khẩu thành công
                            } else {
                                // Thông báo lỗi nếu mật khẩu không khớp
                            }
                        })
                        .setNegativeButton("Hủy", (dialog, id) -> dialog.dismiss());

                // Hiển thị dialog
                builder.create().show();
            }
        });


    }
}
