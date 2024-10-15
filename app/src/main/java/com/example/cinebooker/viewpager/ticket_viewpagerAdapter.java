package com.example.cinebooker.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cinebooker.fragment.Ticket_chuadungFragment;
import com.example.cinebooker.fragment.ticket_dadungFragment;

public class ticket_viewpagerAdapter extends FragmentStateAdapter {
    public ticket_viewpagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Ticket_chuadungFragment();
            case 1:
                return new ticket_dadungFragment();
            default:                return new Ticket_chuadungFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2; // Số lượng tab
    }
}
