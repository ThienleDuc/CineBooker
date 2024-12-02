package com.example.cinebooker.TranGiaThai.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Adapter.Thucan_Adapter;
import com.example.cinebooker.TranGiaThai.Entity.Thucan_Entity;
import com.google.android.material.tabs.TabLayout;

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
    private Button btn_thanhtoan_thucan;


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
        btn_thanhtoan_thucan = view.findViewById(R.id.btn_thanhtoan_thucan);
        btn_thanhtoan_thucan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabLayout tabLayout = requireActivity().findViewById(R.id.tab_layout_gt);
                if(tabLayout != null) {
                    String tabTitleToSelect = "Thanh Toán";
                    int tabCount = tabLayout.getTabCount();

                    for(int i = 0; i < tabCount; i++) {
                        TabLayout.Tab tab = tabLayout.getTabAt(i);

                        if (tab != null && tab.getText() != null) {
                            if (tab.getText().toString().toUpperCase().equals(tabTitleToSelect.trim().toUpperCase())) {
                                tab.select();
                                break;
                            }
                        }
                    }
                }


            }
        });





    }
}