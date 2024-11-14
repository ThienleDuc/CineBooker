package com.example.cinebooker.PhanCongQuoc.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cinebooker.LeDucThien.activity.home;
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
        dangNhap.setOnClickListener(v -> ActivityOpen.openActivityOnClick(this, home.class, R.id.login_btn));
        ggDangNhap.setOnClickListener(v -> ActivityOpen.openActivityOnClick(this, home.class, R.id.login_btn_1));
    }
}