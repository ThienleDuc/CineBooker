package com.example.cinebooker.TranGiaThai.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Adapter.Khunggiochieu_Adapter;
import com.example.cinebooker.TranGiaThai.Adapter.ViewPagerAdapter;
import com.example.cinebooker.TranGiaThai.Fragment.Main;
import com.example.cinebooker.generalMethod.FragmentUtils;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private RecyclerView recyclerView;
    private Khunggiochieu_Adapter KHCadapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_maingiathai);


        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);












        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        // Khởi chạy fragment mặc định khi ứng dụng mở lên
        if (savedInstanceState == null) {
            FragmentUtils.loadFragment(this, new Main(), R.id.fragment_container);
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