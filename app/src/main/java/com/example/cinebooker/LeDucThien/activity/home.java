package com.example.cinebooker.LeDucThien.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.cinebooker.LeDucThien.fragment.Movies;
import com.example.cinebooker.LeDucThien.fragment.Profile;
import com.example.cinebooker.LeDucThien.fragment.Search;
import com.example.cinebooker.LeDucThien.fragment.Tickets;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.FragmentUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class home extends AppCompatActivity {
    private Map<Integer, Fragment> fragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Gọi phương thức loadData() để tải dữ liệu nặng
        loadData();

        // Khởi tạo fragment map
        fragmentMap = new HashMap<>();
        fragmentMap.put(R.id.action_movies, new Movies());
        fragmentMap.put(R.id.action_search, new Search());
        fragmentMap.put(R.id.action_tickets, new Tickets());
        fragmentMap.put(R.id.action_profile, new Profile());

        BottomNavigationView bottomNavigationView = findViewById(R.id.home_bottom_navigation);

        // Kiểm tra xem có fragment nào trong fragment_container không
        if (savedInstanceState == null) {
            // Nếu chưa có fragment, thêm fragment mặc định (ví dụ: Movies)
            FragmentUtils.loadFragment(this, new Movies(), R.id.home_fragment_container);
        }

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = fragmentMap.get(item.getItemId());

                if (selectedFragment != null) {
                    FragmentUtils.loadFragment(home.this, selectedFragment, R.id.home_fragment_container);
                }

                return true;
            }
        });
    }

    /**
     * Tải dữ liệu từ mạng trên luồng phụ và cập nhật giao diện.
     */
    private void loadData() {
        new Thread(() -> {
            // Giả lập tải dữ liệu từ mạng (thay thế bằng logic thực tế)
            List<String> data = fetchDataFromNetwork();

            // Cập nhật giao diện trên luồng chính
            runOnUiThread(() -> {
                // Hiển thị thông báo hoặc cập nhật giao diện
                showData(data);
            });
        }).start();
    }

    /**
     * Phương thức giả lập tải dữ liệu từ mạng.
     */
    private List<String> fetchDataFromNetwork() {
        try {
            // Giả lập độ trễ tải dữ liệu
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Dữ liệu giả lập
        List<String> dummyData = new ArrayList<>();
        dummyData.add("Movie 1");
        dummyData.add("Movie 2");
        dummyData.add("Movie 3");
        return dummyData; // Thay bằng dữ liệu thực tế
    }

    /**
     * Cập nhật giao diện sau khi tải dữ liệu thành công.
     *
     * @param data Danh sách dữ liệu đã tải
     */
    private void showData(List<String> data) {
        // Ví dụ: Hiển thị thông báo hoặc cập nhật RecyclerView
        Toast.makeText(this, "Dữ liệu đã tải: " + data, Toast.LENGTH_SHORT).show();
    }
}
