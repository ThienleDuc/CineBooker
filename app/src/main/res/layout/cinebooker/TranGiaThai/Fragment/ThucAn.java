package com.example.cinebooker.TranGiaThai.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Adapter.Thucan_Adapter;
import com.example.cinebooker.TranGiaThai.Entity.Thucan_Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class ThucAn extends Fragment {
    private RecyclerView ThucAnRecyclerView;
    private Thucan_Adapter ThucAnAdapter;
    private List<Thucan_Entity> ThucAnList;


    public ThucAn() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ThucAn.
     */
    // TODO: Rename and change types and number of parameters





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thuc_an, container, false);
        Thucan(view);
        return view;

    }

    public void Thucan(View view)
    {
        ThucAnRecyclerView =view.findViewById(R.id.Rcv_ThucAn);

        ThucAnRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        ThucAnList = new ArrayList<>();
        ThucAnList.add(new Thucan_Entity(R.drawable.food1,"Combo Bắp Nước 1","90.000đ",R.drawable.add,R.drawable.subtract));
        ThucAnList.add(new Thucan_Entity(R.drawable.food1,"Combo Bắp Nước 2","90.000đ",R.drawable.add,R.drawable.subtract));
        ThucAnList.add(new Thucan_Entity(R.drawable.food1,"Combo Bắp Nước 3","90.000đ",R.drawable.add,R.drawable.subtract));
        ThucAnList.add(new Thucan_Entity(R.drawable.food1,"Combo Bắp Nước 4","90.000đ",R.drawable.add,R.drawable.subtract));
        ThucAnList.add(new Thucan_Entity(R.drawable.food1,"Combo Bắp Nước 5","90.000đ",R.drawable.add,R.drawable.subtract));
        ThucAnList.add(new Thucan_Entity(R.drawable.food1,"Combo Bắp Nước 6","90.000đ",R.drawable.add,R.drawable.subtract));
        ThucAnList.add(new Thucan_Entity(R.drawable.food1,"Combo Bắp Nước 7","90.000đ",R.drawable.add,R.drawable.subtract));
        ThucAnList.add(new Thucan_Entity(R.drawable.food1,"Combo Bắp Nước 8","90.000đ",R.drawable.add,R.drawable.subtract));
        ThucAnList.add(new Thucan_Entity(R.drawable.food1,"Combo Bắp Nước 9","90.000đ",R.drawable.add,R.drawable.subtract));
        ThucAnAdapter = new Thucan_Adapter(ThucAnList);
        ThucAnRecyclerView.setAdapter(ThucAnAdapter);




    }
}