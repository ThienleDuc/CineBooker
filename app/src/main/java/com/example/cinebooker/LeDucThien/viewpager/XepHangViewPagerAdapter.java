package com.example.cinebooker.LeDucThien.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cinebooker.LeDucThien.fragment.xephangngay;
import com.example.cinebooker.LeDucThien.fragment.xephangthang;
import com.example.cinebooker.LeDucThien.fragment.xephangtuan;


import java.util.ArrayList;
import java.util.List;

public class XepHangViewPagerAdapter extends FragmentStateAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();

    public XepHangViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

        fragmentList.add(new xephangngay());
        fragmentList.add(new xephangtuan());
        fragmentList.add(new xephangthang());

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