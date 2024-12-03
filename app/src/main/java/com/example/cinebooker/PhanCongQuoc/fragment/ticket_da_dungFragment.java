package com.example.cinebooker.PhanCongQuoc.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_chuadungAdapter;
import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_dadungAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.ticketchuadungMoviesEntity;
import com.example.cinebooker.PhanCongQuoc.entity.ticketdadungMoviesEntity;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ticket_da_dungFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ticket_da_dungFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    private RecyclerView list_dadung;
    private Ticket_dadungAdapter ticketdadungAdapter;
    private List<ticketdadungMoviesEntity> ticketdadungMoviesList;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ticket_da_dungFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ticket_da_dungFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ticket_da_dungFragment newInstance(String param1, String param2) {
        ticket_da_dungFragment fragment = new ticket_da_dungFragment();
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
        View view = inflater.inflate(R.layout.fragment_ticket_da_dung, container, false);

        list_dadung = view.findViewById(R.id.list_dadung);
        list_dadung.setLayoutManager(new LinearLayoutManager(getContext()));

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        list_dadung.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        ticketdadungMoviesList = new ArrayList<>();
        // Khởi tạo ticketchuadungMoviesEntity theo thứ tự trong TicketViewHolder
        ticketdadungMoviesList.add(new ticketdadungMoviesEntity(
                "02/09/2024  18:30",
                "02/10/2024  18:30",// date_chuadung
                R.drawable.camposter, // poster
                "18+",                // age_chuadung
                "Cám",               // name_chuadung
                "Kinh dị",           // style_chuadung
                1,                    // soluong_chuadung
                "CGV Vincom Plaza Đà Nẵng" // diachi_chuadung
        ));
        ticketdadungMoviesList.add(new ticketdadungMoviesEntity(
                "02/09/2024  18:30",
                "02/10/2024  18:30",
                R.drawable.camposter, // poster
                "18+",                // age_chuadung
                "Cám",               // name_chuadung
                "Kinh dị",           // style_chuadung
                1,                    // soluong_chuadung
                "CGV Vincom Plaza Đà Nẵng" // diachi_chuadung
        ));
        ticketdadungMoviesList.add(new ticketdadungMoviesEntity(
                "02/09/2024  18:30",
                "02/10/2024  18:30",// date_chuadung
                R.drawable.camposter, // poster
                "18+",                // age_chuadung
                "Cám",               // name_chuadung
                "Kinh dị",           // style_chuadung
                1,                    // soluong_chuadung
                "CGV Vincom Plaza Đà Nẵng" // diachi_chuadung
        ));
        ticketdadungMoviesList.add(new ticketdadungMoviesEntity(
                "02/09/2024  18:30",
                "02/10/2024  18:30",// date_chuadung
                R.drawable.camposter, // poster
                "18+",                // age_chuadung
                "Cám",               // name_chuadung
                "Kinh dị",           // style_chuadung
                1,                    // soluong_chuadung
                "CGV Vincom Plaza Đà Nẵng" // diachi_chuadung
        ));



        ticketdadungAdapter = new Ticket_dadungAdapter(ticketdadungMoviesList);
        list_dadung.setAdapter(ticketdadungAdapter);

        return view;   }
}