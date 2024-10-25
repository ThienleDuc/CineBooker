package com.example.cinebooker.PhanCongQuoc.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_khuhoiAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.ticketkhuhoiMoviesEntity;
import com.example.cinebooker.PhanCongQuoc.generalMethod.SpaceItemDecoration;
import com.example.cinebooker.R;

import java.util.ArrayList;
import java.util.List;

public class ticket_khuhoiFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1"; // Định nghĩa biến
    private static final String ARG_PARAM2 = "param2"; // Định nghĩa biến

    private RecyclerView list_khuhoi;
    private Ticket_khuhoiAdapter ticketkhuhoiAdapter;
    private List<ticketkhuhoiMoviesEntity> ticketkhuhoiMoviesList;

    public ticket_khuhoiFragment() {
        // Required empty public constructor
    }

    public static ticket_khuhoiFragment newInstance(String param1, String param2) {
        ticket_khuhoiFragment fragment = new ticket_khuhoiFragment();
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
        View view = inflater.inflate(R.layout.fragment_ticket_khuhoi, container, false);

        list_khuhoi = view.findViewById(R.id.list_khuhoi);
        list_khuhoi.setLayoutManager(new LinearLayoutManager(getContext()));

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        list_khuhoi.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        ticketkhuhoiMoviesList = new ArrayList<>();
        // Khởi tạo ticketkhuhoiMoviesEntity theo thứ tự trong TicketViewHolder
        ticketkhuhoiMoviesList.add(new ticketkhuhoiMoviesEntity(
                R.drawable.icon17_fragment,
                "02/09/2024 18:30",
                "02/10/2024 18:30",
                "18+",
                "Cám",
                "kinh dị",
                "1",
                "CGV Vincom Plaza Đà Nẵng",
                R.drawable.phimcam,
                R.drawable.icon_cgv
        ));
        ticketkhuhoiMoviesList.add(new ticketkhuhoiMoviesEntity(
                R.drawable.icon17_fragment,
                "02/09/2024 18:30",
                "02/10/2024 18:30",
                "18+",
                "Cám",
                "kinh dị",
                "1",
                "CGV Vincom Plaza Đà Nẵng",
                R.drawable.phimcam,
                R.drawable.icon_cgv
        ));ticketkhuhoiMoviesList.add(new ticketkhuhoiMoviesEntity(
                R.drawable.icon17_fragment,
                "02/09/2024 18:30",
                "02/10/2024 18:30",
                "18+",
                "Cám",
                "kinh dị",
                "1",
                "CGV Vincom Plaza Đà Nẵng",
                R.drawable.phimcam,
                R.drawable.icon_cgv
        ));ticketkhuhoiMoviesList.add(new ticketkhuhoiMoviesEntity(
                R.drawable.icon17_fragment,
                "02/09/2024 18:30",
                "02/10/2024 18:30",
                "18+",
                "Cám",
                "kinh dị",
                "1",
                "CGV Vincom Plaza Đà Nẵng",
                R.drawable.phimcam,
                R.drawable.icon_cgv
        ));
        ticketkhuhoiMoviesList.add(new ticketkhuhoiMoviesEntity(
                R.drawable.icon17_fragment,
                "02/09/2024 18:30",
                "02/10/2024 18:30",
                "18+",
                "Cám",
                "kinh dị",
                "1",
                "CGV Vincom Plaza Đà Nẵng",
                R.drawable.phimcam,
                R.drawable.icon_cgv
        ));



        ticketkhuhoiAdapter = new Ticket_khuhoiAdapter(ticketkhuhoiMoviesList);
        list_khuhoi.setAdapter(ticketkhuhoiAdapter);

        return view;
    }
}
