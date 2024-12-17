
package com.example.cinebooker.PhanCongQuoc.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_PhimDangChieu;
import com.example.cinebooker.PhanCongQuoc.BussinessLogic.BL_ChuaDung;
import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_chuadungAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.ticketchuadungMoviesEntity;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class ticket_chuadungFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1"; // Định nghĩa biến
    private static final String ARG_PARAM2 = "param2"; // Định nghĩa biến

    private int mave=-1;
    private RecyclerView list_chuadung;
    private Ticket_chuadungAdapter ticketChuadungAdapter;
    private List<ticketchuadungMoviesEntity> ticketChuadungMoviesList;

    public ticket_chuadungFragment() {
        // Required empty public constructor
    }

    public static ticket_chuadungFragment newInstance(String param1, String param2) {
        ticket_chuadungFragment fragment = new ticket_chuadungFragment();
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
            // Xử lý tham số nếu cần
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket_chuadung, container, false);
        chuadung(view);
        return view;
    }


    public void chuadung (View view) {
        RecyclerView chuaRecyclerView = view.findViewById(R.id.list_chuadung);
        SharedPreferences sharedPreferences = getContext().
                getSharedPreferences("QuocDepTrai", Context.MODE_PRIVATE);
        mave = sharedPreferences.getInt("MaVe", -1);
        BL_ChuaDung blChuaDung = new BL_ChuaDung();
        blChuaDung.loadChuaDungVertical(getContext(), chuaRecyclerView);
    }



}
