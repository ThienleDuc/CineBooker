package com.example.cinebooker.PhanCongQuoc.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_capbacAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.ticketcapbacMoviesEntity;
import com.example.cinebooker.PhanCongQuoc.generalMethod.SpaceItemDecoration;
import com.example.cinebooker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link dac_quyen_cap_bacFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class dac_quyen_cap_bacFragment extends Fragment {
    private RecyclerView list_capbac;
    private Ticket_capbacAdapter ticketcapbacAdapter;
    private List<ticketcapbacMoviesEntity> ticketcapbacMoviesList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public dac_quyen_cap_bacFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment dac_quyen_cap_bacFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static dac_quyen_cap_bacFragment newInstance(String param1, String param2) {
        dac_quyen_cap_bacFragment fragment = new dac_quyen_cap_bacFragment();
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
        
            View view = inflater.inflate(R.layout.fragment_dac_quyen_cap_bac, container, false);

            list_capbac = view.findViewById(R.id.list_capbac);
            list_capbac.setLayoutManager(new LinearLayoutManager(getContext()));

            int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
            list_capbac.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

            ticketcapbacMoviesList = new ArrayList<>();
            // Khởi tạo ticketchuadungMoviesEntity theo thứ tự trong TicketViewHolder
            ticketcapbacMoviesList.add(new ticketcapbacMoviesEntity(
                    "TEAM 17",
                    R.drawable.icon17_itemvoucher,
                    "Giảm 10% giảm tối đa 20%",
                    "Đơn tối thiểu 80k",
                    "05/10/2024"


            ));
        ticketcapbacMoviesList.add(new ticketcapbacMoviesEntity(
                "TEAM 17",
                R.drawable.icon17_itemvoucher,
                "Giảm 10% giảm tối đa 20%",
                "Đơn tối thiểu 80k",
                "05/10/2024"


        ));ticketcapbacMoviesList.add(new ticketcapbacMoviesEntity(
                "TEAM 17",
                R.drawable.icon17_itemvoucher,
                "Giảm 10% giảm tối đa 20%",
                "Đơn tối thiểu 80k",
                "05/10/2024"


        ));ticketcapbacMoviesList.add(new ticketcapbacMoviesEntity(
                "TEAM 17",
                R.drawable.icon17_itemvoucher,
                "Giảm 10% giảm tối đa 20%",
                "Đơn tối thiểu 80k",
                "05/10/2024"


        ));



            ticketcapbacAdapter = new Ticket_capbacAdapter(ticketcapbacMoviesList);
            list_capbac.setAdapter(ticketcapbacAdapter);

            return view;      }   }
