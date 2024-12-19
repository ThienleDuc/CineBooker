package com.example.cinebooker.PhanCongQuoc.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.BussinessLogic.BL_ChuaDung;
import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_chuadungAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.ticketchuadungMoviesEntity;
import com.example.cinebooker.R;

import java.util.List;

public class ticket_chuadungFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int mave = -1;
    private RecyclerView list_chuadung;
    private Ticket_chuadungAdapter ticketChuadungAdapter;
    private List<ticketchuadungMoviesEntity> ticketChuadungMoviesList;

    // Handler để làm mới dữ liệu mỗi giây

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
        setRetainInstance(false); // Đảm bảo rằng fragment không bị tái sử dụng
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket_chuadung, container, false);
        chuadung(view);

        return view;
    }



    private void chuadung(View view) {
        RecyclerView chuaRecyclerView = view.findViewById(R.id.list_chuadung);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("QuocDepTrai", Context.MODE_PRIVATE);
        mave = sharedPreferences.getInt("MaVe", -1);

        BL_ChuaDung blChuaDung = new BL_ChuaDung();

        // Tải lại dữ liệu mà không thay đổi cấu trúc RecyclerView hoặc Adapter
        blChuaDung.loadChuaDungVertical(getContext(), chuaRecyclerView);


    }
    @Override
    public void onResume() {
        super.onResume();
            chuadung(getView());
        }




}
