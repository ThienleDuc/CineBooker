package com.example.cinebooker.PhanCongQuoc.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ActivityOpen;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);

        ImageView logo = findViewById(R.id.batdau);
        logo.setOnClickListener(v -> {
            ActivityOpen.openActivityOnClick(this, login.class, R.id.batdau);
        });
    }
}