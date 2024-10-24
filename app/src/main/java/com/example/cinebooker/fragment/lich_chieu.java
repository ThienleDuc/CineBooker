package com.example.cinebooker.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinebooker.R;
import com.example.cinebooker.adapter.caroselDangChieuAdapter;
import com.example.cinebooker.entity.caroselDangChieuEntity;
import com.example.cinebooker.generalMethod.HorizontalSpaceItemDecoration;

import java.util.ArrayList;
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

    private List<caroselDangChieuEntity> movieDangChieuList;


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

        RecyclerView dangChieuRecycleView = view.findViewById(R.id.carosel_recycleView_dangChieu);
        dangChieuRecycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_7_5);
        dangChieuRecycleView.addItemDecoration(new HorizontalSpaceItemDecoration(spacingInPixels));

        movieDangChieuList = new ArrayList<>();

        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));

        caroselDangChieuAdapter dangChieuAdapter = new caroselDangChieuAdapter(movieDangChieuList);
        dangChieuRecycleView.setAdapter(dangChieuAdapter);
        return view;
    }
}