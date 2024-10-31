package com.example.cinebooker.LeDucThien.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cinebooker.LeDucThien.fragment.xephangngay;
import com.example.cinebooker.LeDucThien.fragment.xephangtuan;
import com.example.cinebooker.PhanCongQuoc.fragment.ticket_chuadungFragment;
import com.example.cinebooker.PhanCongQuoc.fragment.ticket_da_dungFragment;
import com.example.cinebooker.PhanCongQuoc.fragment.ticket_khuhoiFragment;

import java.util.ArrayList;
import java.util.List;

public class TicketsViewPagerAdapter extends FragmentStateAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();

    public TicketsViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        // Thay đổi ở đây
        fragmentList.add(new ticket_chuadungFragment());
        fragmentList.add(new ticket_da_dungFragment());
        fragmentList.add(new ticket_khuhoiFragment());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}