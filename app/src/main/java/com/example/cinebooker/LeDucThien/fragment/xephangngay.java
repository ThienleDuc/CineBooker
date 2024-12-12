package com.example.cinebooker.LeDucThien.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_XepHang;
import com.example.cinebooker.LeDucThien.adapter.XepHangAdapter;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link xephangngay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class xephangngay extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public xephangngay() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment xephangngay.
     */
    // TODO: Rename and change types and number of parameters
    public static xephangngay newInstance(String param1, String param2) {
        xephangngay fragment = new xephangngay();
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
        View view = inflater.inflate(R.layout.fragment_xephangngay, container, false);

        // Khởi tạo RecyclerView cho lịch chiếu
        RecyclerView recyclerview = view.findViewById(R.id.xephangtheongay);
        BL_XepHang blXepHang = new BL_XepHang();
        XepHangAdapter adapter = new XepHangAdapter();
        blXepHang.loadXepHangNgayToRecyclerView(getContext(), recyclerview, adapter);
        return view;
    }
}