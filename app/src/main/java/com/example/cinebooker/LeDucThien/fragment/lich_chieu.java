package com.example.cinebooker.LeDucThien.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_PhimDangChieu;
import com.example.cinebooker.LeDucThien.activity.danhSachRap;
import com.example.cinebooker.LeDucThien.adapter.moviesNgayChieuAdapter;
import com.example.cinebooker.LeDucThien.adapter.ngayChieuAdapter;
import com.example.cinebooker.LeDucThien.entity.ent_PhimDangChieu;
import com.example.cinebooker.LeDucThien.entity.moviesNgayChieuEntity;
import com.example.cinebooker.LeDucThien.entity.ngayChieuEntity;
import com.example.cinebooker.LeDucThien.entity.thoiGianChieuPhimEntity;

import com.example.cinebooker.R;
import com.example.cinebooker.LeDucThien.adapter.caroselDangChieuAdapter;
import com.example.cinebooker.generalMethod.ActivityOpen;
import com.example.cinebooker.generalMethod.HorizontalSpaceItemDecoration;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;
import com.google.android.material.tabs.TabLayout;

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

    private List<ent_PhimDangChieu> movieDangChieuList;


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

        lichChieu(view);
        dangChieu(view);

        return view;
    }

    public void lichChieu(View view) {
        // Khởi tạo TabLayout cho location
        TabLayout location_tablayout = view.findViewById(R.id.location_tabLayout);
        LinearLayout danhsachrap_open = view.findViewById(R.id.danhsachrap_open);

        danhsachrap_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityOpen.openActivityOnClick(requireActivity(), danhSachRap.class, R.id.danhsachrap_open);
            }
        });

        View tab1 = LayoutInflater.from(getContext()).inflate(R.layout.location_tab1, null);
        View tab2 = LayoutInflater.from(getContext()).inflate(R.layout.location_tab2, null);
        location_tablayout.addTab(location_tablayout.newTab().setCustomView(tab1));
        location_tablayout.addTab(location_tablayout.newTab().setCustomView(tab2));

        // Khởi tạo RecyclerView cho lịch chiếu
        RecyclerView calendar_tablayout = view.findViewById(R.id.calendar_tabLayout);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        calendar_tablayout.setLayoutManager(layoutManager);

        // Thêm khoảng cách giữa các item
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        calendar_tablayout.addItemDecoration(new HorizontalSpaceItemDecoration(spacingInPixels));

        // Tạo danh sách ngày chiếu
        List<ngayChieuEntity> ngayChieuEntityList = new ArrayList<>();
        ngayChieuEntityList.add(new ngayChieuEntity(22, "Thứ 4"));
        ngayChieuEntityList.add(new ngayChieuEntity(23, "Thứ 5"));
        ngayChieuEntityList.add(new ngayChieuEntity(24, "Thứ 6"));
        ngayChieuEntityList.add(new ngayChieuEntity(25, "Thứ 7"));
        ngayChieuEntityList.add(new ngayChieuEntity(26, "C.Nhật"));
        ngayChieuEntityList.add(new ngayChieuEntity(27, "Thứ 2"));
        ngayChieuEntityList.add(new ngayChieuEntity(28, "Thứ 3"));

        // Gán adapter cho RecyclerView
        ngayChieuAdapter adapter = new ngayChieuAdapter(ngayChieuEntityList);
        calendar_tablayout.setAdapter(adapter);

        // Lắng nghe sự kiện click trên item của RecyclerView
        adapter.setOnItemClickListener(new ngayChieuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Cuộn mượt mà đến vị trí được chọn và căn giữa item
                smoothScrollToCenter(calendar_tablayout, layoutManager, position);
            }
        });

        // Khởi tạo RecyclerView cho lịch chiếu
        RecyclerView movieCalendarRecyclerview = view.findViewById(R.id.calendar_recyclerview);
        LinearLayoutManager moviesLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        movieCalendarRecyclerview.setLayoutManager(moviesLayoutManager);

        // Thêm khoảng cách giữa các item
        movieCalendarRecyclerview.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        // Tạo danh sách thời gian chiếu
        List<thoiGianChieuPhimEntity> thoiGianChieuPhimList1 = new ArrayList<>();
        thoiGianChieuPhimList1.add(new thoiGianChieuPhimEntity("10:40", "12:42"));
        thoiGianChieuPhimList1.add(new thoiGianChieuPhimEntity("14:00", "16:00"));
        thoiGianChieuPhimList1.add(new thoiGianChieuPhimEntity("14:00", "16:00"));
        thoiGianChieuPhimList1.add(new thoiGianChieuPhimEntity("14:00", "16:00"));
        thoiGianChieuPhimList1.add(new thoiGianChieuPhimEntity("14:00", "16:00"));
        thoiGianChieuPhimList1.add(new thoiGianChieuPhimEntity("14:00", "16:00"));
        thoiGianChieuPhimList1.add(new thoiGianChieuPhimEntity("14:00", "16:00"));
        thoiGianChieuPhimList1.add(new thoiGianChieuPhimEntity("14:00", "16:00"));
        thoiGianChieuPhimList1.add(new thoiGianChieuPhimEntity("14:00", "16:00"));

        // Tạo danh sách ngày chiếu
        List<moviesNgayChieuEntity> moviesList = new ArrayList<>();

        moviesList.add(new moviesNgayChieuEntity(R.drawable.camposter, "18+", 6.2, 10500.0, 3200.0,  "Cám", "Kinh dị", thoiGianChieuPhimList1));
        moviesList.add(new moviesNgayChieuEntity(R.drawable.camposter, "18+", 6.2, 10500.0, 3200.0,  "Cám", "Kinh dị", thoiGianChieuPhimList1));
        moviesList.add(new moviesNgayChieuEntity(R.drawable.camposter, "18+", 6.2, 10500.0, 3200.0,  "Cám", "Kinh dị", thoiGianChieuPhimList1));
        moviesList.add(new moviesNgayChieuEntity(R.drawable.camposter, "18+", 6.2, 10500.0, 3200.0,  "Cám", "Kinh dị", thoiGianChieuPhimList1));
        moviesList.add(new moviesNgayChieuEntity(R.drawable.camposter, "18+", 6.2, 10500.0, 3200.0,  "Cám", "Kinh dị", thoiGianChieuPhimList1));
        moviesList.add(new moviesNgayChieuEntity(R.drawable.camposter, "18+", 6.2, 10500.0, 3200.0,  "Cám", "Kinh dị", thoiGianChieuPhimList1));
        moviesList.add(new moviesNgayChieuEntity(R.drawable.camposter, "18+", 6.2, 10500.0, 3200.0,  "Cám", "Kinh dị", thoiGianChieuPhimList1));
        moviesList.add(new moviesNgayChieuEntity(R.drawable.camposter, "18+", 6.2, 10500.0, 3200.0,  "Cám", "Kinh dị", thoiGianChieuPhimList1));
        moviesList.add(new moviesNgayChieuEntity(R.drawable.camposter, "18+", 6.2, 10500.0, 3200.0,  "Cám", "Kinh dị", thoiGianChieuPhimList1));
        moviesList.add(new moviesNgayChieuEntity(R.drawable.camposter, "18+", 6.2, 10500.0, 3200.0,  "Cám", "Kinh dị", thoiGianChieuPhimList1));


        // Gán adapter cho RecyclerView
        moviesNgayChieuAdapter moviesAdapter = new moviesNgayChieuAdapter(moviesList);
        movieCalendarRecyclerview.setAdapter(moviesAdapter);
    }

    private void smoothScrollToCenter(RecyclerView recyclerView, LinearLayoutManager layoutManager, int position) {
        View viewAtPosition = layoutManager.findViewByPosition(position);
        if (viewAtPosition == null || recyclerView.getLayoutManager() == null) {
            recyclerView.smoothScrollToPosition(position);
        } else {
            int itemWidth = viewAtPosition.getWidth();
            int screenWidth = recyclerView.getWidth();
            int scrollOffset = (screenWidth - itemWidth) / 2 - viewAtPosition.getLeft();
            recyclerView.smoothScrollBy(scrollOffset, 0);
        }
    }

    public void dangChieu (View view) {

        RecyclerView dangChieuRecycleView = view.findViewById(R.id.carosel_recycleView_dangChieu);

        BL_PhimDangChieu blPhimDangChieu = new BL_PhimDangChieu();
        blPhimDangChieu.loadDangChieuHorizontal(getContext(), dangChieuRecycleView);

        TextView dangChieuMoreThan = view.findViewById(R.id.dang_chieu_more);
        dangChieuMoreThan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TabLayout tabLayout = requireActivity().findViewById(R.id.movies_tab_layout);

                if(tabLayout != null) {
                    String tabTitleToSelect = "Đang chiếu";
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