package com.example.cinebooker.PhanCongQuoc.PhanCongQuoc.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.BussinessLogic.BL_ChuaDung;
import com.example.cinebooker.PhanCongQuoc.BussinessLogic.BL_DaDung;
import com.example.cinebooker.PhanCongQuoc.BussinessLogic.BL_KhuHoi;
import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_khuhoiAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.ticketkhuhoiMoviesEntity;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class ticket_khuhoiFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1"; // Định nghĩa biến
    private static final String ARG_PARAM2 = "param2"; // Định nghĩa biến
    private int mave=-1;
    private RecyclerView list_khuhoi;
    private Ticket_khuhoiAdapter ticketkhuhoiAdapter;
    private List<ticketkhuhoiMoviesEntity> ticketkhuhoiMoviesList;


    private String mParam1;
    private String mParam2;


    public ticket_khuhoiFragment() {
        // Required empty public constructor
    }

    public static ticket_khuhoiFragment newInstance(String param1, String param2) {
        ticket_khuhoiFragment fragment = new ticket_khuhoiFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket_khuhoi, container, false);
        khuhoi(view);

        return view;
    }
    public void khuhoi (View view) {
        RecyclerView hoiRecyclerView = view.findViewById(R.id.list_khuhoi);
        SharedPreferences sharedPreferences = getContext().
                getSharedPreferences("QuocDepTrai", Context.MODE_PRIVATE);
        mave = sharedPreferences.getInt("MaVe", -1);
        BL_KhuHoi blKhuHoi = new BL_KhuHoi();
        blKhuHoi.loadKhuHoiVertical(getContext(), hoiRecyclerView);

}
    @Override
    public void onResume() {
        super.onResume();
        khuhoi(getView());
    }}