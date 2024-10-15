package com.example.cinebooker.appCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinebooker.R;

public class batdau extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_batdau);
        // Thiết lập sự kiện cho TextView đăng ký
            ImageView batdauImage = findViewById(R.id.batdau_image);
        batdauImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến Activity Register
                Intent intent = new Intent(batdau.this, login.class);
                startActivity(intent);
            }
        });
    }
}