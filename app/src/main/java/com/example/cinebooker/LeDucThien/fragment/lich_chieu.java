package com.example.cinebooker.LeDucThien.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_NgayChieu;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_PhimDangChieu;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_PhimTheoLichChieu;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_RapChieu;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_RapChieuCon;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_TinhThanh;
import com.example.cinebooker.LeDucThien.activity.DanhSachDiaDiemRap;
import com.example.cinebooker.LeDucThien.activity.danhSachRap;
import com.example.cinebooker.LeDucThien.adapter.LichChieuAdapter;
import com.example.cinebooker.LeDucThien.adapter.PhimTheoLichChieuAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_PhimDangChieu;

import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ActivityOpen;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link lich_chieu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class lich_chieu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<ent_PhimDangChieu> movieDangChieuList;


    public lich_chieu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment lich_chieu.
     */
    // TODO: Rename and change types and number of parameters
    public static lich_chieu newInstance(String param1, String param2) {
        lich_chieu fragment = new lich_chieu();
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
        View view = inflater.inflate(R.layout.fragment_lich_chieu, container, false);

        controllerLichChieu(view);
        lichChieu(view);
        dangChieu(view);

        return view;
    }

    public void controllerLichChieu(View view) {
        BL_TinhThanh blTinhThanh = new BL_TinhThanh();
        BL_RapChieu blRapChieu = new BL_RapChieu();
        BL_RapChieuCon blRapChieuCon = new BL_RapChieuCon();

        TextView TenTinhThanh = view.findViewById(R.id.ten_tinh_thanh);
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor;

        int _maTinhThanh = sharedPreferences.getInt("maTinhThanh", -1);
        if (_maTinhThanh == -1) {
            _maTinhThanh = blTinhThanh.loadMinMaTinhThanh();
            editor = sharedPreferences.edit();
            editor.putInt("maTinhThanh", _maTinhThanh);
            editor.apply();
        }

        blTinhThanh.loadTenTinhThanhTheoDieuKien(requireContext(), TenTinhThanh, _maTinhThanh);

        int _maRapChieu = sharedPreferences.getInt("maRapChieu", -1);
        if (_maRapChieu == -1) {
            _maRapChieu = blRapChieu.loadMinMaRapChieu();
            editor = sharedPreferences.edit();
            editor.putInt("maRapChieu", _maRapChieu);
            editor.apply();
        }

        ImageView anhRapChieuCon = view.findViewById(R.id.calendar_logo);
        TextView tenRapChieuCon = view.findViewById(R.id.sapChieu_movie_name);
        TextView diaChiRapChieuCon = view.findViewById(R.id.sapChieu_movie_style);

        int _maRapChieuCon = sharedPreferences.getInt("maRapChieuCon", -1);
        if (_maRapChieuCon == -1) {
            _maRapChieuCon = blRapChieuCon.loadMinRapChieuCon(_maTinhThanh, _maRapChieu);
            editor = sharedPreferences.edit();
            editor.putInt("maRapChieuCon", _maRapChieuCon);
            editor.apply();
        }

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
            _maThoiGianChieu = blPhimTheoLichChieu.getNowThoiGianChieu();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("maThoiGianChieu", _maThoiGianChieu);
            editor.apply();
        }
        int _maRapChieuCon = sharedPreferences.getInt("maRapChieuCon", -1);
        blPhimTheoLichChieu.loadPhimTheoLichChieuToRecyclerView(requireContext(), recyclerView1,
                phimTheoLichChieuAdapter,_maRapChieuCon, _maThoiGianChieu);
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
                            if (tab.getText().toString().toUpperCase().equals(tabTitleToSelect.trim().toUpperCase())) {
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