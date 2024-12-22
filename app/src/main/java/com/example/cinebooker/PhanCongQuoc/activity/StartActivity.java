package com.example.cinebooker.PhanCongQuoc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import com.example.cinebooker.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Chuyển màn hình sau 5 giây
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, login.class);
            startActivity(intent);
            finish();  // Để không quay lại màn hình start khi nhấn back
        }, 5000); // 5 giây
    }
}
