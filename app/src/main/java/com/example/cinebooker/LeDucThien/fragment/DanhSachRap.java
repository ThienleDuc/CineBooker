package com.example.cinebooker.LeDucThien.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cinebooker.LeDucThien.adapter.caroselDangChieuAdapter;
import com.example.cinebooker.LeDucThien.adapter.diaChiRapChieuAdapter;
import com.example.cinebooker.LeDucThien.adapter.rapChieuAdapter;
import com.example.cinebooker.LeDucThien.entity.diaChiRapChieuEntity;
import com.example.cinebooker.LeDucThien.entity.rapChieuEntity;
import com.example.cinebooker.LeDucThien.generalMethod.FragmentUtils;
import com.example.cinebooker.LeDucThien.generalMethod.HorizontalSpaceItemDecoration;
import com.example.cinebooker.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DanhSachRap#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DanhSachRap extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DanhSachRap() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DanhSachRap.
     */
    // TODO: Rename and change types and number of parameters
    public static DanhSachRap newInstance(String param1, String param2) {
        DanhSachRap fragment = new DanhSachRap();
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
        View view = inflater.inflate(R.layout.fragment_danh_sach_rap, container, false);

        ImageView close = view.findViewById(R.id.danhsachrap_close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentUtils.hideOverlayFragment(requireActivity(), R.id.home_overlay_fragment_container);
            }
        });

        danhSachRap(view);

        danhSachDiaChiRap(view);
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
        rapChieuRecycleView.addItemDecoration(new HorizontalSpaceItemDecoration(spacingInPixels));

        List<diaChiRapChieuEntity> rapChieuList = new ArrayList<>();

        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));
        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));
        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));
        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));
        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));
        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));
        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));
        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));
        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));
        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));


        diaChiRapChieuAdapter rapChieuAdapter = new diaChiRapChieuAdapter(rapChieuList);
        rapChieuRecycleView.setAdapter(rapChieuAdapter);

    }
}