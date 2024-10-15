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