package com.example.cinebooker.mainproject;

import android.os.Bundle;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.cinebooker.TranGiaThai.fragment.testContent;
import com.example.cinebooker.TranGiaThai.generalMethod.FragmentUtils;
import com.example.cinebooker.R;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_maingiathai);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khởi chạy fragment mặc định khi ứng dụng mở lên
        if (savedInstanceState == null) {
            FragmentUtils.loadFragment(this, new testContent(), R.id.fragment_container);
        }
    }

    @Override
    public void onBackPressed() {
        // Kiểm tra xem có fragment nào trong back stack không
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            // Quay trở lại fragment trước đó
            getSupportFragmentManager().popBackStack();
        } else {
            // Nếu không, thực hiện hành động quay lại mặc định
            super.onBackPressed();
        }
    }
}