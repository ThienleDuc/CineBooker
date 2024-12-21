package com.example.cinebooker.PhanCongQuoc.PhanCongQuoc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


import com.example.cinebooker.PhanCongQuoc.activity.login;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ActivityOpen;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);

        // Sử dụng Handler để chuyển màn hình tự động sau 5 giây
        new Handler().postDelayed(() -> {
            // Chuyển đến màn hình login sau 5 giây
            Intent intent = new Intent(this, login.class);
            startActivity(intent);
            finish();  // Để không quay lại màn hình start khi nhấn back
        }, 5000); // 5
    }
}