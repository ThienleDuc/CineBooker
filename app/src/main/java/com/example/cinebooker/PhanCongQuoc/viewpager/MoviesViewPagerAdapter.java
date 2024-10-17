package com.example.cinebooker.PhanCongQuoc.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.example.cinebooker.PhanCongQuoc.fragment.dang_chieu;
import com.example.cinebooker.PhanCongQuoc.fragment.kham_pha;
import com.example.cinebooker.PhanCongQuoc.fragment.lich_chieu;
import com.example.cinebooker.PhanCongQuoc.fragment.rap_chieu;
import com.example.cinebooker.PhanCongQuoc.fragment.sap_chieu;
import com.example.cinebooker.PhanCongQuoc.fragment.xep_hang;

import java.util.ArrayList;
import java.util.List;

public class MoviesViewPagerAdapter extends FragmentStateAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();

    public MoviesViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);

        fragmentList.add(new kham_pha());
        fragmentList.add(new lich_chieu());
        fragmentList.add(new dang_chieu());
        fragmentList.add(new sap_chieu());
        fragmentList.add(new rap_chieu());
        fragmentList.add(new xep_hang());

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