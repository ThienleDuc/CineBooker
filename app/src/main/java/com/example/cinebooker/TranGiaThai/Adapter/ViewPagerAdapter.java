package com.example.cinebooker.TranGiaThai.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.cinebooker.TranGiaThai.Fragment.ChonGhe;
import com.example.cinebooker.TranGiaThai.Fragment.ThanhToan;
import com.example.cinebooker.TranGiaThai.Fragment.ThongTin;
import com.example.cinebooker.TranGiaThai.Fragment.ThucAn;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behaviorResumeOnlyCurrentFragment) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ThongTin();
            case 1:
                return new ChonGhe();
            case 2:
                return new ThucAn();
            case 3:
                return new ThanhToan();

                default:
                return new ThongTin();
        }


    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position)
        {
            case 0:
                title = "Thông tin";
                break;
            case 1:
                title = "Chọn ghế";
                break;
            case 2:
                title = "Thức ăn";
                break;
            case 3:
                title = "Thanh toán";
                break;

        }
        return title;
    }
}
