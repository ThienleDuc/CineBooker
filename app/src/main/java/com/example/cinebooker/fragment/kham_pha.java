package com.example.cinebooker.fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.HorizontalSpaceItemDecoration;
import com.example.cinebooker.adapter.caroselDangChieuAdapter;
import com.example.cinebooker.entity.caroselDangChieuEntity;
import com.example.cinebooker.adapter.caroselSapChieuAdapter;
import com.example.cinebooker.entity.caroselSapChieuEntity;
import com.example.cinebooker.viewpager.MoviesViewPagerAdapter;
import com.example.cinebooker.viewpager.XepHangViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link kham_pha#newInstance} factory method to
 * create an instance of this fragment.
 */
public class kham_pha extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView dangChieuRecycleView, sapChieuRecycleView;
    private caroselDangChieuAdapter dangChieuAdapter;
    private List<caroselDangChieuEntity> movieDangChieuList;
    private TextView dangChieuMoreThan;
    private List<caroselSapChieuEntity> caroselSapChieuEntityList;
    private caroselSapChieuAdapter sapChieuAdapter;
    private TextView sapChieuMoreThan;

    public kham_pha() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment kham_pha.
     */
    // TODO: Rename and change types and number of parameters
    public static kham_pha newInstance(String param1, String param2) {
        kham_pha fragment = new kham_pha();
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
        View view = inflater.inflate(R.layout.fragment_kham_pha, container, false);

        dangChieuRecycleView = view.findViewById(R.id.carosel_recycleView_dangChieu);
        dangChieuRecycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_7_5);
        dangChieuRecycleView.addItemDecoration(new HorizontalSpaceItemDecoration(spacingInPixels));

        movieDangChieuList = new ArrayList<>();

        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));

        dangChieuAdapter = new caroselDangChieuAdapter(movieDangChieuList);
        dangChieuRecycleView.setAdapter(dangChieuAdapter);

        dangChieuMoreThan = view.findViewById(R.id.dang_chieu_more);
        dangChieuMoreThan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabLayout tabLayout = requireActivity().findViewById(R.id.movies_tab_layout);

                if(tabLayout != null) {
                    String tabTitleToSelect = "Đang chiếu";
                    int tabCount = tabLayout.getTabCount();

                    for(int i = 0; i < tabCount; i++) {
                        TabLayout.Tab tab = tabLayout.getTabAt(i);

                        if (tab != null && tab.getText() != null) {
                            if (tab.getText().toString().toUpperCase().equals(tabTitleToSelect.trim().toUpperCase())) {
                                tab.select();
                                break;
                            }
                        }
                    }
                }
            }
        });

        sapChieuRecycleView = view.findViewById(R.id.carosel_recycleView_sapChieu);
        sapChieuRecycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        sapChieuRecycleView.addItemDecoration(new HorizontalSpaceItemDecoration(spacingInPixels));

        caroselSapChieuEntityList = new ArrayList<>();

        caroselSapChieuEntityList.add(new caroselSapChieuEntity(R.drawable.camposter, "18+","Cám", "Kinh dị"));
        caroselSapChieuEntityList.add(new caroselSapChieuEntity(R.drawable.camposter, "18+","Cám", "Kinh dị"));
        caroselSapChieuEntityList.add(new caroselSapChieuEntity(R.drawable.camposter, "18+","Cám", "Kinh dị"));
        caroselSapChieuEntityList.add(new caroselSapChieuEntity(R.drawable.camposter, "18+","Cám", "Kinh dị"));
        caroselSapChieuEntityList.add(new caroselSapChieuEntity(R.drawable.camposter, "18+","Cám", "Kinh dị"));
        caroselSapChieuEntityList.add(new caroselSapChieuEntity(R.drawable.camposter, "18+","Cám", "Kinh dị"));
        caroselSapChieuEntityList.add(new caroselSapChieuEntity(R.drawable.camposter, "18+","Cám", "Kinh dị"));
        caroselSapChieuEntityList.add(new caroselSapChieuEntity(R.drawable.camposter, "18+","Cám", "Kinh dị"));
        caroselSapChieuEntityList.add(new caroselSapChieuEntity(R.drawable.camposter, "18+","Cám", "Kinh dị"));
        caroselSapChieuEntityList.add(new caroselSapChieuEntity(R.drawable.camposter, "18+","Cám", "Kinh dị"));

        sapChieuAdapter = new caroselSapChieuAdapter(caroselSapChieuEntityList);
        sapChieuRecycleView.setAdapter(sapChieuAdapter);

        sapChieuMoreThan = view.findViewById(R.id.dang_chieu_more);
        sapChieuMoreThan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabLayout tabLayout = requireActivity().findViewById(R.id.movies_tab_layout);

                if(tabLayout != null) {
                    String tabTitleToSelect = "Sắp chiếu";
                    int tabCount = tabLayout.getTabCount();

                    for(int i = 0; i < tabCount; i++) {
                        TabLayout.Tab tab = tabLayout.getTabAt(i);

                        if (tab != null && tab.getText() != null) {
                            if (tab.getText().toString().toUpperCase().equals(tabTitleToSelect.trim().toUpperCase())) {
                                tab.select();
                                break;
                            }
                        }
                    }
                }
            }
        });


        TabLayout location_tablayout = view.findViewById(R.id.location_tabLayout);

        View tab1 = LayoutInflater.from(getContext()).inflate(R.layout.location_tab1, null);
        View tab2= LayoutInflater.from(getContext()).inflate(R.layout.location_tab2, null);

        // Thêm các custom view vào TabLayout
        location_tablayout.addTab(location_tablayout.newTab().setCustomView(tab1));
        location_tablayout.addTab(location_tablayout.newTab().setCustomView(tab2));

//        fragmentLayout = view.findViewById(R.id.fragmentLayout); // Lấy ID của LinearLayout
//        tabLayout = view.findViewById(R.id.location_tabLayout); // Lấy ID của TabLayout
//
//        // Thiết lập listener cho TabLayout
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                // Đổi màu khi tab được chọn
//                fragmentLayout.setBackgroundColor(Color.parseColor("#FF4081")); // Màu hồng
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//                // Đổi màu khi tab không còn được chọn
//                fragmentLayout.setBackgroundColor(Color.WHITE); // Màu trắng
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//                // Có thể bỏ qua
//            }
//        });

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