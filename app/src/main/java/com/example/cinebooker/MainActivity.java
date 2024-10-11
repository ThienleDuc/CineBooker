package com.example.cinebooker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<Integer, Fragment> fragmentMap;

    private void loadFragment(Fragment fragment) {
        // Thay thế Fragment hiện tại bằng Fragment đã chọn
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); // Chuển trang
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left); // Tạo hiê ứng chuển trang
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khởi tạo fragment map
        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.action_movies, new Movies());
        fragmentMap.put(R.id.action_search, new Search());
        fragmentMap.put(R.id.action_tickets, new Tickets());
        fragmentMap.put(R.id.action_profile, new Profile());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Load Fragment mặc định khi ứng dụng khởi chạy
        if (savedInstanceState == null) {
            loadFragment(new Movies());
        }

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = fragmentMap.get(item.getItemId());

                if (selectedFragment != null) {
                    loadFragment(selectedFragment);
                }

                return true;
            }
        });
    }
}
