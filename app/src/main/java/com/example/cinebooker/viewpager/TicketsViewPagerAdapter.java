package com.example.cinebooker.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cinebooker.fragment.Ticket_chuadungFragment;
import com.example.cinebooker.fragment.kham_pha;
import com.example.cinebooker.fragment.lich_chieu;
import com.example.cinebooker.fragment.ticket_dadungFragment;

import java.util.ArrayList;
import java.util.List;

public class TicketsViewPagerAdapter extends FragmentStateAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();

    public TicketsViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        // Thay đổi ở đây
        fragmentList.add(new Ticket_chuadungFragment());
        fragmentList.add(new ticket_dadungFragment());
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