package com.example.cinebooker.LeDucThien.fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cinebooker.LeDucThien.viewpager.TicketsViewPagerAdapter;
import com.example.cinebooker.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tickets#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tickets extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private TabLayout tabLayout;

    public Tickets() {
        // Required empty public constructor
    }

    public static Tickets newInstance(String param1, String param2) {
        Tickets fragment = new Tickets();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tickets, container, false);

        tabLayout = view.findViewById(R.id.tickets_tab_layout);
        ViewPager2 viewPager = view.findViewById(R.id.tickets_view_pager);

        TicketsViewPagerAdapter adapter = new TicketsViewPagerAdapter(requireActivity());
        viewPager.setAdapter(adapter);

        String[] tabTitles = new String[] {
                "Vé chưa dùng",
                "Vé đã dùng",
                "Vé khứ hồi"
        };

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTitles[position]);
                // Đặt màu nền cho tab
                View tabView = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(position);
                if (tabView != null) {
                    tabView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorUnSelected)); // Màu nền mặc định
                }
            }
        }).attach();

        // Đặt màu cho tab text
        tabLayout.setTabTextColors(
                ContextCompat.getColor(getContext(), R.color.colorSelected),
                ContextCompat.getColor(getContext(), R.color.colorUnSelected)
        );

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Đổi màu nền của tab được chọn
                View selectedTabView = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition());
                if (selectedTabView != null) {
                    selectedTabView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSelected));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Đổi màu nền của tab khi bỏ chọn
                View unselectedTabView = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(tab.getPosition());
                if (unselectedTabView != null) {
                    unselectedTabView.setBackgroundColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorUnSelected));
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Có thể xử lý khi tab được chọn lại nếu cần
            }
        });

        // Đảm bảo rằng tab đầu tiên được chọn và màu nền được cập nhật
        TabLayout.Tab firstTab = tabLayout.getTabAt(0);
        if (firstTab != null) {
            firstTab.select();  // Chọn tab đầu tiên
            View selectedTabView = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(0);
            if (selectedTabView != null) {
                selectedTabView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSelected));
            }
        }

        return view;
    }
}
