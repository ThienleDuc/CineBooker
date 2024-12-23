package com.example.cinebooker.LeDucThien.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cinebooker.PhanCongQuoc.fragment.ticket_chuadungFragment;
import com.example.cinebooker.PhanCongQuoc.fragment.ticket_da_dungFragment;
import com.example.cinebooker.PhanCongQuoc.fragment.ticket_khuhoiFragment;

import java.util.ArrayList;
import java.util.List;

public class TicketsViewPagerAdapter extends FragmentStateAdapter {

    private final List<Fragment> fragmentList;

    // Sử dụng constructor để tạo danh sách các fragment
    public TicketsViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        // Khởi tạo danh sách fragment ngay trong constructor
        fragmentList = new ArrayList<>();
        fragmentList.add(new ticket_chuadungFragment());  // Fragment cho Vé chưa dùng
        fragmentList.add(new ticket_da_dungFragment());   // Fragment cho Vé đã dùng
        fragmentList.add(new ticket_khuhoiFragment());    // Fragment cho Vé khứ hồi
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Trả về fragment tương ứng với vị trí trong danh sách
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        // Trả về số lượng fragment trong danh sách
        return fragmentList.size();
    }
}
