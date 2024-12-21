package com.example.cinebooker.LeDucThien.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_PhimSapChieu;
import com.example.cinebooker.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link sap_chieu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class sap_chieu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public sap_chieu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment sap_chieu.
     */
    // TODO: Rename and change types and number of parameters
    public static sap_chieu newInstance(String param1, String param2) {
        sap_chieu fragment = new sap_chieu();
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
        View view = inflater.inflate(R.layout.fragment_sap_chieu, container, false);
        sapChieu(view);
        return view;
    }

    public void sapChieu (View view) {
        RecyclerView sapChieuRecycleView = view.findViewById(R.id.recycleView_sapChieu);
        BL_PhimSapChieu blPhimSapChieu = new BL_PhimSapChieu();
        blPhimSapChieu.loadPhimToRecyclerView(getContext(), sapChieuRecycleView);
    }
}