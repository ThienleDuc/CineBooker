package com.example.cinebooker.LeDucThien.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_TimKiemPhim;
import com.example.cinebooker.LeDucThien.entity.ent_PhimDangChieu;
import com.example.cinebooker.R;
import com.example.cinebooker.LeDucThien.adapter.TimKiemAdapter;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Search extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private TimKiemAdapter timKiemAdapter;
    private List<ent_PhimDangChieu> searchMoviesList;
    private TextView moreThan;

    public Search() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Search.
     */
    // TODO: Rename and change types and number of parameters
    public static Search newInstance(String param1, String param2) {
        Search fragment = new Search();
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
        View view =  inflater.inflate(R.layout.fragment_search, container, false);
        // Inflate the layout for this fragment
        EditText timKiem = view.findViewById(R.id.header_search_input);

        recyclerView = view.findViewById(R.id.recycler_view);
        BL_TimKiemPhim blTimKiemPhim = new BL_TimKiemPhim();
        TimKiemAdapter adapter = new TimKiemAdapter();
        blTimKiemPhim.loadPhimToRecyclerView(getContext(), recyclerView, adapter);

        timKiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString().trim();
                if (!input.isEmpty()) {
                    blTimKiemPhim.loadPhimToRecyclerViewAfterSearch(getContext(), recyclerView, adapter, input);
                } else {
                    blTimKiemPhim.loadPhimToRecyclerView(getContext(), recyclerView, adapter);
                }
            }
        });


        moreThan = view.findViewById(R.id.search_more_than);
        moreThan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

}