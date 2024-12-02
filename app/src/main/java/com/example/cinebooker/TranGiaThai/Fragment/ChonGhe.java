package com.example.cinebooker.TranGiaThai.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Adapter.Ghe_Adapter;
import com.example.cinebooker.TranGiaThai.Entity.Ghe_Entity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class ChonGhe extends Fragment {
private RecyclerView GheRecyclerView;
private Ghe_Adapter GheAdapter;
private List<Ghe_Entity> GheList;
private Button continueButton;
private Button returnButton;;
    public ChonGhe() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ChonGhe.
     */
    // TODO: Rename and change types and number of parameters


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chon_ghe, container, false);
        Ghe(view);
        return view;
    }
    public void Ghe(View view)
    {
        GheRecyclerView =view.findViewById(R.id.Rcv_ghengoi);
        GheRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
        GheList = new ArrayList<>();
        GheList.add(new Ghe_Entity(R.drawable.sofa,"Ghế thường","30/30"));
        GheList.add(new Ghe_Entity(R.drawable.sofa_a,"Ghế VIP","50/50"));
        GheList.add(new Ghe_Entity(R.drawable.sofa_b,"Ghế SweetBox","10/10"));
        GheList.add(new Ghe_Entity(R.drawable.sofa_c,"Ghế Đã Đặt","0"));
        GheList.add(new Ghe_Entity(R.drawable.sofa_d,"Ghế Bạn Chọn","0"));
        GheAdapter = new Ghe_Adapter(GheList);
        GheRecyclerView.setAdapter(GheAdapter);

        continueButton = view.findViewById(R.id.btn_thanhtoan_chonghe);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabLayout tabLayout = requireActivity().findViewById(R.id.tab_layout_gt);
                if(tabLayout != null) {
                    String tabTitleToSelect = "Thức Ăn";
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
        returnButton = view.findViewById(R.id.btn_return);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabLayout tabLayout = requireActivity().findViewById(R.id.tab_layout_gt);
                if(tabLayout != null) {
                    String tabTitleToSelect = "Thông Tin";
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