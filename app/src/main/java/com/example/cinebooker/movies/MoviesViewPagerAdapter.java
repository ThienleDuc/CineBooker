package com.example.cinebooker.movies;

import android.hardware.Camera;
import android.view.FrameStats;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

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