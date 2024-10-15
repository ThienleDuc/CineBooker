package com.example.cinebooker.mainproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.cinebooker.R;
import com.example.cinebooker.fragment.Movies;
import com.example.cinebooker.fragment.Profile;
import com.example.cinebooker.fragment.Search;
import com.example.cinebooker.fragment.Tickets;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<Integer, Fragment> fragmentMap;

    private void loadFragment(Fragment fragment) {
        // Thay thế Fragment hiện tại bằng Fragment đã chọn
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction(); // Chuển trang
        // transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    public void showOverlayFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.overlay_fragment_container, fragment);
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        transaction.addToBackStack(null);
        transaction.commit();

        // Hiển thị FrameLayout đè lên
        FrameLayout overlayContainer = findViewById(R.id.overlay_fragment_container);
        overlayContainer.setVisibility(View.VISIBLE);
    }

    public void hideOverlayFragment() {
        // Ẩn Fragment và FrameLayout khi không cần hiển thị nữa
        getSupportFragmentManager().popBackStack();
        FrameLayout overlayContainer = findViewById(R.id.overlay_fragment_container);
        overlayContainer.setVisibility(View.GONE);
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

        // Thêm callback cho sự kiện quay lại
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FrameLayout overlayContainer = findViewById(R.id.overlay_fragment_container);

                // Nếu overlay đang hiển thị, ẩn nó
                if (overlayContainer.getVisibility() == View.VISIBLE) {
                    hideOverlayFragment();
                } else {
                    // Nếu không, thực hiện hành động mặc định (quay lại trang trước)
                    MainActivity.super.onBackPressed();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

}
