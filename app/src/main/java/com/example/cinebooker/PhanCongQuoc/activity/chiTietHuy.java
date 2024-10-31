package com.example.cinebooker.PhanCongQuoc.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cinebooker.R;

public class chiTietHuy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet_huy);

        ImageView back = findViewById(R.id.itemback);

        Button btn = findViewById(R.id.btn_chitiethuy);
        back.setOnClickListener(v -> onBackPressed());
        btn.setOnClickListener(v -> onBackPressed());


    }
}