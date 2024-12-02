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
import com.example.cinebooker.TranGiaThai.Adapter.Khunggiochieu_Adapter;
import com.example.cinebooker.TranGiaThai.Adapter.Rap_Adapter;
import com.example.cinebooker.TranGiaThai.Entity.Khunggiochieu_Entity;
import com.example.cinebooker.TranGiaThai.Entity.Rap_Entity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class ThongTin extends Fragment {

    private RecyclerView KCHrecyclerView, RapRecyclerView;
    private Khunggiochieu_Adapter KHCadapter;
    private List<Khunggiochieu_Entity> KCHlist;
    private Rap_Adapter RapAdapter;
    private List<Rap_Entity> RapList;
    private Button btn_datve;

    public ThongTin() {
        // Required empty public constructor
    }






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thong_tin, container, false);
        khunggiochieu(view);
        Rap(view);
        return view;
    }
    ;
    public void khunggiochieu(View view)
    {
        KCHrecyclerView =view.findViewById(R.id.Rcv_khunggiochieu);

        KCHrecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        KCHlist = new ArrayList<>();
        KCHlist.add(new Khunggiochieu_Entity("10:00","70/100"));
        KCHlist.add(new Khunggiochieu_Entity("10:00","70/100"));
        KCHlist.add(new Khunggiochieu_Entity("10:00","70/100"));
        KCHlist.add(new Khunggiochieu_Entity("10:00","70/100"));
        KCHlist.add(new Khunggiochieu_Entity("10:00","70/100"));
        KCHlist.add(new Khunggiochieu_Entity("10:00","70/100"));
        KCHlist.add(new Khunggiochieu_Entity("10:00","70/100"));
        KCHlist.add(new Khunggiochieu_Entity("10:00","70/100"));
        KCHlist.add(new Khunggiochieu_Entity("10:00","70/100"));

        KHCadapter = new Khunggiochieu_Adapter( KCHlist);
        KCHrecyclerView.setAdapter(KHCadapter);

    }
    public void Rap(View view)
    {
        RapRecyclerView =view.findViewById(R.id.Rcv_Rap);
        RapRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        RapList = new ArrayList<>();
        RapList.add(new Rap_Entity(R.drawable.cgv,"Rạp 1",R.drawable.placeholder));
        RapList.add(new Rap_Entity(R.drawable.galaxy,"Rạp 2",R.drawable.placeholder));
        RapList.add(new Rap_Entity(R.drawable.lotte_icon,"Rạp 3",R.drawable.placeholder));
        RapList.add(new Rap_Entity(R.drawable.cgv,"Rạp 1",R.drawable.placeholder));
        RapList.add(new Rap_Entity(R.drawable.cgv,"Rạp 2",R.drawable.placeholder));
        RapList.add(new Rap_Entity(R.drawable.cgv,"Rạp 3",R.drawable.placeholder));
        RapAdapter = new Rap_Adapter(RapList);
        RapRecyclerView.setAdapter(RapAdapter);
        btn_datve = view.findViewById(R.id.btn_tieptuc);
        btn_datve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabLayout tabLayout = requireActivity().findViewById(R.id.tab_layout);
                if(tabLayout != null) {
                    String tabTitleToSelect = "Chọn Ghế";
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