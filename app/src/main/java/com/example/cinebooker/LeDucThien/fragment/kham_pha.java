package com.example.cinebooker.LeDucThien.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.cinebooker.LeDucThien.BussinessLogic.BL_DoiTac;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_NgayChieu;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_PhimDangChieu;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_PhimSapChieu;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_PhimTheoLichChieu;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_RapChieu;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_RapChieuCon;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_TinhThanh;
import com.example.cinebooker.LeDucThien.activity.DanhSachDiaDiemRap;
import com.example.cinebooker.LeDucThien.activity.danhSachRap;
import com.example.cinebooker.LeDucThien.adapter.DoiTacAdapter;
import com.example.cinebooker.LeDucThien.adapter.LichChieuAdapter;
import com.example.cinebooker.LeDucThien.adapter.PhimTheoLichChieuAdapter;
import com.example.cinebooker.LeDucThien.adapter.TinhThanhAdapter;
import com.example.cinebooker.LeDucThien.viewpager.XepHangViewPagerAdapter;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ActivityOpen;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Objects;


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

    private int _maTinhThanh = -1;
    private TinhThanhAdapter tinhThanhAdapter;
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
        dangChieu(view);
        sapChieu(view);
        controllerLichChieu(view);
        lichChieu(view);
        heThongRapChieu(view);
        xepHang(view);
        return view;
    }

    public void dangChieu (View view) {

        RecyclerView dangChieuRecycleView = view.findViewById(R.id.carosel_recycleView_dangChieu);
        BL_PhimDangChieu blPhimDangChieu = new BL_PhimDangChieu();
        blPhimDangChieu.loadCaroselPhimToRecyclerView(getContext(), dangChieuRecycleView);

        TextView dangChieuMoreThan = view.findViewById(R.id.dang_chieu_more);
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
                            if (tab.getText().toString().equalsIgnoreCase(tabTitleToSelect.trim())) {
                                tab.select();
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    public void sapChieu (View view) {
        RecyclerView sapChieuRecycleView = view.findViewById(R.id.carosel_recycleView_sapChieu);
        BL_PhimSapChieu blPhimSapChieu = new BL_PhimSapChieu();
        blPhimSapChieu.loadCaroselPhimToRecyclerView(getContext(), sapChieuRecycleView);

        TextView sapChieuMoreThan = view.findViewById(R.id.sapChieu_more);
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
                            if (tab.getText().toString().equalsIgnoreCase(tabTitleToSelect.trim())) {
                                tab.select();
                                break;
                            }
                        }
                    }
                }
            }
        });
    }


    public void controllerLichChieu(View view) {
        BL_TinhThanh blTinhThanh = new BL_TinhThanh();
        BL_RapChieu blRapChieu = new BL_RapChieu();
        BL_RapChieuCon blRapChieuCon = new BL_RapChieuCon();

        TextView TenTinhThanh = view.findViewById(R.id.ten_tinh_thanh);
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);

        int _maTinhThanh = sharedPreferences.getInt("maTinhThanh", -1);

        blTinhThanh.loadTenTinhThanhTheoDieuKien(requireContext(), TenTinhThanh, _maTinhThanh);

        ImageView anhRapChieuCon = view.findViewById(R.id.calendar_logo);
        TextView tenRapChieuCon = view.findViewById(R.id.sapChieu_movie_name);
        TextView diaChiRapChieuCon = view.findViewById(R.id.sapChieu_movie_style);

        int _maRapChieuCon = sharedPreferences.getInt("maRapChieuCon", -1);

        blRapChieuCon.loadThongTinRapChieuCon(requireContext(), anhRapChieuCon,
                tenRapChieuCon, diaChiRapChieuCon, _maRapChieuCon);
        // Khởi tạo TabLayout cho location
        LinearLayout danhsachtinhthanh_open = view.findViewById(R.id.open_list_tinh_thanh);
        LinearLayout danhsachrap_open = view.findViewById(R.id.danhsachrap_open);
        LinearLayout ganday_search = view.findViewById(R.id.search_gan_ban);

        ImageView icon_location = view.findViewById(R.id.icon_location);
        ImageView icon_location2 = view.findViewById(R.id.icon_location2);
        TextView ganban = view.findViewById(R.id.gan_ban);

        // Xử lý sự kiện click để mở Activity
        danhsachtinhthanh_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityOpen.openActivityOnClick(requireActivity(), DanhSachDiaDiemRap.class, R.id.open_list_tinh_thanh);

                ganday_search.setBackgroundResource(R.drawable.strock_1_white_radius_10_transparent);
                ganban.setTextColor(view.getContext().getColor(R.color.colorUnSelected));
                icon_location2.setColorFilter(view.getContext().getColor(R.color.colorUnSelected));

                danhsachtinhthanh_open.setBackgroundResource(R.drawable.strock_1_pink_radius_10_transparent);
                TenTinhThanh.setTextColor(view.getContext().getColor(R.color.colorSelected));
                icon_location.setColorFilter(view.getContext().getColor(R.color.colorSelected));
            }
        });

        ganday_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ganday_search.setBackgroundResource(R.drawable.strock_1_pink_radius_10_transparent);
                ganban.setTextColor(view.getContext().getColor(R.color.colorSelected));
                icon_location2.setColorFilter(view.getContext().getColor(R.color.colorSelected));

                danhsachtinhthanh_open.setBackgroundResource(R.drawable.strock_1_white_radius_10_transparent);
                TenTinhThanh.setTextColor(view.getContext().getColor(R.color.colorUnSelected));
                icon_location.setColorFilter(view.getContext().getColor(R.color.colorUnSelected));
            }
        });

        danhsachrap_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityOpen.openActivityOnClick(requireActivity(), danhSachRap.class, R.id.danhsachrap_open);
            }
        });
    }



    public void lichChieu(View view) {

        // Khởi tạo RecyclerView cho lịch chiếu
        RecyclerView recyclerView = view.findViewById(R.id.calendar_tabLayout);
        BL_NgayChieu blNgayChieu = new BL_NgayChieu();
        LichChieuAdapter adapter = new LichChieuAdapter();

        blNgayChieu.loadNgayChieuToRecyclerView(getContext(), recyclerView, adapter);

        BL_PhimTheoLichChieu blPhimTheoLichChieu = new BL_PhimTheoLichChieu();
        RecyclerView recyclerView1 = view.findViewById(R.id.calendar_recyclerview);

        PhimTheoLichChieuAdapter phimTheoLichChieuAdapter = new PhimTheoLichChieuAdapter(requireContext());

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);
        int _maThoiGianChieu = sharedPreferences.getInt("maThoiGianChieu", -1);
        if (_maThoiGianChieu == -1) {
            _maThoiGianChieu = blNgayChieu.getMinNgayChieu();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("maThoiGianChieu", _maThoiGianChieu);
            editor.apply();
        }
        int _maRapChieuCon = sharedPreferences.getInt("maRapChieuCon", -1);
        blPhimTheoLichChieu.loadPhimTheoLichChieuToRecyclerView(requireContext(), recyclerView1,
                phimTheoLichChieuAdapter,_maRapChieuCon, _maThoiGianChieu);
    }


    public void heThongRapChieu(View view) {
        // Khởi tạo RecyclerView cho hệ thống rạp chiếu
        RecyclerView recyclerView = view.findViewById(R.id.recycleView_heThongRapChieu);
        BL_DoiTac blDoiTac = new BL_DoiTac();
        DoiTacAdapter adapter = new DoiTacAdapter();
        blDoiTac.loadDoiTacToRecyclerView(getContext(), recyclerView, adapter);
        TextView btnXemThem = view.findViewById(R.id.doitac_more_than);

        TextView rapChieu_more = view.findViewById(R.id.rapChieu_more);
        rapChieu_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabLayout tabLayout = requireActivity().findViewById(R.id.movies_tab_layout);

                if(tabLayout != null) {
                    String tabTitleToSelect = "Rạp chiếu";
                    int tabCount = tabLayout.getTabCount();

                    for(int i = 0; i < tabCount; i++) {
                        TabLayout.Tab tab = tabLayout.getTabAt(i);

                        if (tab != null && tab.getText() != null) {
                            if (tab.getText().toString().equalsIgnoreCase(tabTitleToSelect.trim())) {
                                tab.select();
                                break;
                            }
                        }
                    }
                }
            }
        });
    }


    public void xepHang(View view) {
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
                ContextCompat.getColor(view.getContext(), R.color.colorUnSelected),
                ContextCompat.getColor(view.getContext(), R.color.colorSelected)
        );
        TextView xephang_more = view.findViewById(R.id.xephang_more);
        xephang_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabLayout tabLayout = requireActivity().findViewById(R.id.movies_tab_layout);

                if(tabLayout != null) {
                    String tabTitleToSelect = "Xếp hạng";
                    int tabCount = tabLayout.getTabCount();

                    for(int i = 0; i < tabCount; i++) {
                        TabLayout.Tab tab = tabLayout.getTabAt(i);

                        if (tab != null && tab.getText() != null) {
                            if (tab.getText().toString().equalsIgnoreCase(tabTitleToSelect.trim())) {
                                tab.select();
                                break;
                            }
                        }
                    }
                }
            }
        });

    }
}