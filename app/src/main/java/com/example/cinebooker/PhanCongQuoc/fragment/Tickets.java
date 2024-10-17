package com.example.cinebooker.PhanCongQuoc.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinebooker.PhanCongQuoc.viewpager.TicketsViewPagerAdapter;
import com.example.cinebooker.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Tickets#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tickets extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private TicketsViewPagerAdapter adapter;

    public Tickets() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tickets.
     */
    // TODO: Rename and change types and number of parameters
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
        viewPager = view.findViewById(R.id.tickets_view_pager);

        adapter = new TicketsViewPagerAdapter(requireActivity());
        viewPager.setAdapter(adapter);

        String[] tabTitles = new String[] {
                "Chưa sử dụng",
                "Đã sử dụng"
        };

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTitles[position]);
            }
        }).attach();

        tabLayout.setTabTextColors(
                ContextCompat.getColor(getContext(), R.color.colorSelected),
                ContextCompat.getColor(getContext(), R.color.colorUnSelected)
        );

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Đổi màu nền của tab được chọn
                View selectedTabView = tab.view;
                selectedTabView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSelected));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Đổi màu nền của tab khi bỏ chọn
                View unselectedTabView = tab.view;
                unselectedTabView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorUnSelected));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        TabLayout.Tab firstTab = tabLayout.getTabAt(0);
        if (firstTab != null) {
            firstTab.select();  // Chọn tab đầu tiên
            View selectedTabView = firstTab.view;
            selectedTabView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorSelected));
        }

        return view;
    }
}