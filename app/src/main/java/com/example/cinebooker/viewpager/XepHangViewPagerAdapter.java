package com.example.cinebooker.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cinebooker.fragment.dang_chieu;
import com.example.cinebooker.fragment.kham_pha;
import com.example.cinebooker.fragment.lich_chieu;
import com.example.cinebooker.fragment.rap_chieu;
import com.example.cinebooker.fragment.sap_chieu;
import com.example.cinebooker.fragment.xep_hang;
import com.example.cinebooker.fragment.xephangngay;
import com.example.cinebooker.fragment.xephangthang;
import com.example.cinebooker.fragment.xephangtuan;

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