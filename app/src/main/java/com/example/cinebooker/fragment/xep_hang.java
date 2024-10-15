package com.example.cinebooker.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinebooker.R;
import com.example.cinebooker.viewpager.XepHangViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link xep_hang#newInstance} factory method to
 * create an instance of this fragment.
 */
public class xep_hang extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public xep_hang() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment xep_hang.
     */
    // TODO: Rename and change types and number of parameters
    public static xep_hang newInstance(String param1, String param2) {
        xep_hang fragment = new xep_hang();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_xep_hang, container, false);

        TabLayout xepHangtabLayout = view.findViewById(R.id.xepHang_TabLayout);
        ViewPager2 xepHangviewPager = view.findViewById(R.id.xephang_viewPager);

        XepHangViewPagerAdapter xepHangAdapter = new XepHangViewPagerAdapter(requireActivity());
        xepHangviewPager.setAdapter(xepHangAdapter);

        String[] tabTitles = new String[] {
                "Top ngày",
                "Top tuần",
                "Top tháng"
        };

        new TabLayoutMediator(xepHangtabLayout, xepHangviewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTitles[position]);
            }
        }).attach();

        xepHangtabLayout.setTabTextColors(
                ContextCompat.getColor(getContext(), R.color.colorUnSelected),
                ContextCompat.getColor(getContext(), R.color.colorSelected)
        );
        return view;
    }
}