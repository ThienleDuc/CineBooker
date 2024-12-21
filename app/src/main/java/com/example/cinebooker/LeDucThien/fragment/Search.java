package com.example.cinebooker.LeDucThien.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_TimKiemPhim;
import com.example.cinebooker.LeDucThien.adapter.TimKiemAdapter;
import com.example.cinebooker.R;


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
    private BL_TimKiemPhim blTimKiemPhim;
    private EditText timKiem;
    private TimKiemAdapter timKiemAdapter;
    private SharedPreferences sharedPreferences;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        TimKiem(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        timKiem.setText("");
    }

    private void TimKiem(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        timKiem = view.findViewById(R.id.header_search_input);
        blTimKiemPhim = new BL_TimKiemPhim();
        timKiemAdapter = new TimKiemAdapter();


        // Load dữ liệu ban đầu
        blTimKiemPhim.loadPhimToRecyclerView(getContext(), recyclerView, timKiemAdapter);


        // Lắng nghe sự kiện khi EditText nhận focus
        timKiem.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) { // Khi người dùng nhấn vào EditText
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
                            blTimKiemPhim.loadPhimToRecyclerViewAfterSearch(getContext(), recyclerView, timKiemAdapter, input);
                        } else {
                            blTimKiemPhim.loadPhimToRecyclerView(getContext(), recyclerView, timKiemAdapter);
                        }
                    }
                });
            }
        });
    }

}