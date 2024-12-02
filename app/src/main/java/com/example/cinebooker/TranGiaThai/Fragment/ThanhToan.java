package com.example.cinebooker.TranGiaThai.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.R;
import com.example.cinebooker.TranGiaThai.Adapter.PTTT_Adapter;
import com.example.cinebooker.TranGiaThai.Entity.PTTT_Entity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class ThanhToan extends Fragment {
  private RecyclerView PTTTRecyclerView;
           private PTTT_Adapter PTTTAdapter;
           private List<PTTT_Entity> PTTTList;

    public ThanhToan() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ThanhToan.
     */
    // TODO: Rename and change types and number of parameters


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thanh_toan, container, false);
        ThanhToan(view);
        return view;
    }
    public void ThanhToan(View view)
    {
            PTTTRecyclerView =view.findViewById(R.id.Rcv_PTTT);
            PTTTRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
            PTTTList = new ArrayList<>();
            PTTTList.add(new PTTT_Entity("momo",R.drawable.momo,"số dư :123456","Nạp thêm"));
            PTTTList.add(new PTTT_Entity("zalopay",R.drawable.zal,"số dư :123456","Nạp thêm"));
            PTTTList.add(new PTTT_Entity("Visa",R.drawable.visa,"số dư :123456","Nạp thêm"));
            PTTTList.add(new PTTT_Entity("Master card",R.drawable.card,"số dư :123456","Nạp thêm"));
        PTTTList.add(new PTTT_Entity("momo",R.drawable.momo,"số dư :123456","Nạp thêm"));
        PTTTList.add(new PTTT_Entity("zalopay",R.drawable.zal,"số dư :123456","Nạp thêm"));
        PTTTList.add(new PTTT_Entity("Visa",R.drawable.visa,"số dư :123456","Nạp thêm"));
        PTTTList.add(new PTTT_Entity("Master card",R.drawable.card,"số dư :123456","Nạp thêm"));

            PTTTAdapter = new PTTT_Adapter(PTTTList);
            PTTTRecyclerView.setAdapter(PTTTAdapter);




    }

}