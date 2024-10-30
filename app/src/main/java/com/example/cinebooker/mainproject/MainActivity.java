package com.example.cinebooker.mainproject;

import android.os.Bundle;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.cinebooker.PhanCongQuoc.fragment.dac_quyen_cap_bacFragment;
import com.example.cinebooker.PhanCongQuoc.fragment.loginFragment;
import com.example.cinebooker.PhanCongQuoc.fragment.phuong_thuc_thanh_toanFragment;
import com.example.cinebooker.PhanCongQuoc.fragment.ticket_chuadungFragment;
import com.example.cinebooker.PhanCongQuoc.fragment.ticket_da_dungFragment;
import com.example.cinebooker.PhanCongQuoc.fragment.ticket_khuhoiFragment;
import com.example.cinebooker.PhanCongQuoc.fragment.trahanghoantienFragment;
import com.example.cinebooker.PhanCongQuoc.fragment.voucherFragment;
import com.example.cinebooker.PhanCongQuoc.fragment.xemthongtinFragment;
import com.example.cinebooker.PhanCongQuoc.fragment.xuatveFragment;
import com.example.cinebooker.PhanCongQuoc.generalMethod.FragmentUtils;
import com.example.cinebooker.R;


public class MainActivity extends AppCompatActivity {
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

        // Khởi chạy fragment mặc định khi ứng dụng mở lên
        if (savedInstanceState == null) {
            FragmentUtils.loadFragment(this, new ticket_khuhoiFragment()  , R.id.fragment_container);
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