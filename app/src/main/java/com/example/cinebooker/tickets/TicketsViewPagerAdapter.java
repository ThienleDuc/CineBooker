package com.example.cinebooker.tickets;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cinebooker.movies.dang_chieu;
import com.example.cinebooker.movies.kham_pha;
import com.example.cinebooker.movies.lich_chieu;
import com.example.cinebooker.movies.rap_chieu;
import com.example.cinebooker.movies.sap_chieu;
import com.example.cinebooker.movies.xep_hang;

import java.util.ArrayList;
import java.util.List;

public class TicketsViewPagerAdapter extends FragmentStateAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();

    public TicketsViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        // Thay đổi ở đây
        fragmentList.add(new kham_pha());
        fragmentList.add(new lich_chieu());
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
