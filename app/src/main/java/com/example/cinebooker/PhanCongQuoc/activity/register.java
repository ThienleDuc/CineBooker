package com.example.cinebooker.PhanCongQuoc.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ActivityOpen;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

     TextView quayLai = findViewById(R.id.back_register);
        quayLai.setOnClickListener(v -> onBackPressed());

        Button dangKy = findViewById(R.id.register_btn);

        dangKy.setOnClickListener(v -> {
            ActivityOpen.openActivityOnClick(this, login.class, R.id.register_btn);
        });
    }
}