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

public class yeu_cau_hoan_tien extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_yeu_cau_hoan_tien);
        ImageView back = findViewById(R.id.itemback);

        back.setOnClickListener(v -> onBackPressed());

        Button btn = findViewById(R.id.btn_ht);

        btn.setOnClickListener(v -> onBackPressed());

    }
}