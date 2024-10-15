package com.example.cinebooker.appCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinebooker.R;
import com.example.cinebooker.mainproject.MainActivity;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        // Thiết lập sự kiện cho TextView đăng ký
        TextView loginTextView = findViewById(R.id.register_lg);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến Activity Register
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });
        Button loginButton = findViewById(R.id.login_btn);
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
        });
    }
}