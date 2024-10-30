package com.example.cinebooker.PhanCongQuoc.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_chuadungAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.ticketchuadungMoviesEntity;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class ticket_chuadungFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1"; // Định nghĩa biến
    private static final String ARG_PARAM2 = "param2"; // Định nghĩa biến

    private RecyclerView list_chuadung;
    private Ticket_chuadungAdapter ticketChuadungAdapter;
    private List<ticketchuadungMoviesEntity> ticketChuadungMoviesList;

    public ticket_chuadungFragment() {
        // Required empty public constructor
    }

    public static ticket_chuadungFragment newInstance(String param1, String param2) {
        ticket_chuadungFragment fragment = new ticket_chuadungFragment();
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
            // Xử lý tham số nếu cần
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket_chuadung, container, false);

        list_chuadung = view.findViewById(R.id.list_chuadung);
        list_chuadung.setLayoutManager(new LinearLayoutManager(getContext()));

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        list_chuadung.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        ticketChuadungMoviesList = new ArrayList<>();
        // Khởi tạo ticketchuadungMoviesEntity theo thứ tự trong TicketViewHolder
        ticketChuadungMoviesList.add(new ticketchuadungMoviesEntity(
                "02/09/2024  18:30",         // date_chuadung
                R.drawable.camposter, // poster
                "18+",                // age_chuadung
                "Cám",               // name_chuadung
                "Kinh dị",           // style_chuadung
                1,                    // soluong_chuadung
                "CGV Vincom Plaza Đà Nẵng" // diachi_chuadung
        ));
        ticketChuadungMoviesList.add(new ticketchuadungMoviesEntity(
                "02/09/2024  18:30",         // date_chuadung
                R.drawable.camposter, // poster
                "18+",                // age_chuadung
                "Cám",               // name_chuadung
                "Kinh dị",           // style_chuadung
                1,                    // soluong_chuadung
                "CGV Vincom Plaza Đà Nẵng" // diachi_chuadung
        ));
        ticketChuadungMoviesList.add(new ticketchuadungMoviesEntity(
                "02/09/2024  18:30",         // date_chuadung
                R.drawable.camposter, // poster
                "18+",                // age_chuadung
                "Cám",               // name_chuadung
                "Kinh dị",           // style_chuadung
                1,                    // soluong_chuadung
                "CGV Vincom Plaza Đà Nẵng" // diachi_chuadung
        ));
        ticketChuadungMoviesList.add(new ticketchuadungMoviesEntity(
                "02/09/2024  18:30",         // date_chuadung
                R.drawable.camposter, // poster
                "18+",                // age_chuadung
                "Cám",               // name_chuadung
                "Kinh dị",           // style_chuadung
                1,                    // soluong_chuadung
                "CGV Vincom Plaza Đà Nẵng" // diachi_chuadung
        ));
        ticketChuadungMoviesList.add(new ticketchuadungMoviesEntity(
                "02/09/2024  18:30",         // date_chuadung
                R.drawable.camposter, // poster
                "18+",                // age_chuadung
                "Cám",               // name_chuadung
                "Kinh dị",           // style_chuadung
                1,                    // soluong_chuadung
                "CGV Vincom Plaza Đà Nẵng" // diachi_chuadung
        ));
        ticketChuadungMoviesList.add(new ticketchuadungMoviesEntity(
                "02/09/2024  18:30",         // date_chuadung
                R.drawable.camposter, // poster
                "18+",                // age_chuadung
                "Cám",               // name_chuadung
                "Kinh dị",           // style_chuadung
                1,                    // soluong_chuadung
                "CGV Vincom Plaza Đà Nẵng" // diachi_chuadung
        ));


        ticketChuadungAdapter = new Ticket_chuadungAdapter(ticketChuadungMoviesList);
        list_chuadung.setAdapter(ticketChuadungAdapter);

        return view;
    }
}
