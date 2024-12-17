package com.example.cinebooker.LeDucThien.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinebooker.LeDucThien.adapter.timDiaChiRapChieuAdapter;
import com.example.cinebooker.LeDucThien.adapter.heThongRapChieuAdapter;
import com.example.cinebooker.LeDucThien.adapter.rapChieuAdapter;
import com.example.cinebooker.LeDucThien.entity.timDiaChiRapChieuEntity;
import com.example.cinebooker.LeDucThien.entity.heThongRapChieuEntity;
import com.example.cinebooker.LeDucThien.entity.rapChieuEntity;

import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.HorizontalSpaceItemDecoration;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link rap_chieu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class rap_chieu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public rap_chieu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment rap_chieu.
     */
    // TODO: Rename and change types and number of parameters
    public static rap_chieu newInstance(String param1, String param2) {
        rap_chieu fragment = new rap_chieu();
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
        View view = inflater.inflate(R.layout.fragment_rap_chieu, container, false);

        danhSachRap(view);

        danhSachDiaChiRap(view);

        heThongRapChieu(view);
        return view;
    }

    private void danhSachRap (View view) {
        RecyclerView rapChieuRecycleView = view.findViewById(R.id.danhsachrap_recycle_view);
        rapChieuRecycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        rapChieuRecycleView.addItemDecoration(new HorizontalSpaceItemDecoration(spacingInPixels));

        List<rapChieuEntity> rapChieuList = new ArrayList<>();

        rapChieuList.add(new rapChieuEntity(R.drawable.star, "Tất cả"));
        rapChieuList.add(new rapChieuEntity(R.drawable.cgvlogo, "CGV"));
        rapChieuList.add(new rapChieuEntity(R.drawable.lottelogo, "Lotte Cinema"));
        rapChieuList.add(new rapChieuEntity(R.drawable.bhd_logo, "BHD Cinema"));
        rapChieuList.add(new rapChieuEntity(R.drawable.logo_beta, "Beta Cinema"));
        rapChieuList.add(new rapChieuEntity(R.drawable.mega_logo, "Mega GS"));
        rapChieuList.add(new rapChieuEntity(R.drawable.galaxy_cinema, "Galaxy Cinema"));
        rapChieuList.add(new rapChieuEntity(R.drawable.cinerstar, "CineStar"));
        rapChieuList.add(new rapChieuEntity(R.drawable.dcine_logo, "Dcine"));

        rapChieuAdapter rapChieuAdapter = new rapChieuAdapter(rapChieuList);
        rapChieuRecycleView.setAdapter(rapChieuAdapter);

    }

    private void danhSachDiaChiRap (View view) {
        RecyclerView rapChieuRecycleView = view.findViewById(R.id.diachirap_recyclerview);
        rapChieuRecycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        rapChieuRecycleView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        List<timDiaChiRapChieuEntity> rapChieuList = new ArrayList<>();

        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));
        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));
        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));
        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));
        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));
        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));
        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));
        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));
        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));
        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));

        timDiaChiRapChieuAdapter rapChieuAdapter = new timDiaChiRapChieuAdapter(rapChieuList, getContext());
        rapChieuRecycleView.setAdapter(rapChieuAdapter);

    }

    public void heThongRapChieu(View view) {
        // Khởi tạo RecyclerView cho hệ thống rạp chiếu
        RecyclerView recyclerView = view.findViewById(R.id.recycleView_heThongRapChieu);

        // Lấy khoảng cách giữa các mục từ resources
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);

        // Thiết lập LayoutManager cho RecyclerView (dạng dọc)
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Thêm khoảng cách giữa các mục trong RecyclerView
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        // Tạo danh sách mẫu cho các hệ thống rạp chiếu
        List<heThongRapChieuEntity> list = new ArrayList<>();

        // Thêm một số hệ thống rạp chiếu vào danh sách (thay đổi các giá trị này tùy theo dữ liệu thực)
        list.add(new heThongRapChieuEntity(R.drawable.cgvlogo, "CGV", "Hệ thống rạp chiếu phim lớn nhất Việt Nam", 6.2, 81.0, 10500.0));
        list.add(new heThongRapChieuEntity(R.drawable.bhd_logo, "BHD Cinema", "Phim hay mỗi ngày", 8.4, 210.0, 25000.0));
        list.add(new heThongRapChieuEntity(R.drawable.lottelogo, "Lotte Cinema", "Giá ưu đãi", 7.1, 130.0, 17000.0));
        list.add(new heThongRapChieuEntity(R.drawable.dcine_logo, "DcineCinema", "Giá ưu đãi", 7.1, 130.0, 17000.0));
        list.add(new heThongRapChieuEntity(R.drawable.mega_logo, "Mega GS", "Giá ưu đãi", 7.1, 130.0, 17000.0));
        list.add(new heThongRapChieuEntity(R.drawable.galaxy_cinema, "Galaxy Cinema", "Giá ưu đãi", 7.1, 130.0, 17000.0));
        list.add(new heThongRapChieuEntity(R.drawable.cinerstar, "CinerStar Cinema", "Giá ưu đãi", 7.1, 130.0, 17000.0));
        list.add(new heThongRapChieuEntity(R.drawable.logo_beta, "Beta Cinema", "Giá ưu đãi", 7.1, 130.0, 17000.0));


        // Khởi tạo adapter và gán cho RecyclerView
        heThongRapChieuAdapter adapter = new heThongRapChieuAdapter(list);
        recyclerView.setAdapter(adapter);
    }

}