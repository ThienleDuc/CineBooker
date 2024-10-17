package com.example.cinebooker.LeDucThien.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cinebooker.LeDucThien.fragment.kham_pha;
import com.example.cinebooker.LeDucThien.fragment.taikhoan_baomat;
import com.example.cinebooker.LeDucThien.fragment.voucher;

import java.util.ArrayList;
import java.util.List;

public class TicketsViewPagerAdapter extends FragmentStateAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();

    public TicketsViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        // Thay đổi ở đây
        fragmentList.add(new voucher());
        fragmentList.add(new taikhoan_baomat());
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