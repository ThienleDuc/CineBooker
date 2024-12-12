package com.example.cinebooker.LeDucThien.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_DoiTac;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_RapChieu;
import com.example.cinebooker.LeDucThien.activity.DanhSachDiaDiemRap;
import com.example.cinebooker.LeDucThien.adapter.RapChieuAdapter;
import com.example.cinebooker.LeDucThien.adapter.timDiaChiRapChieuAdapter;
import com.example.cinebooker.LeDucThien.adapter.DoiTacAdapter;
import com.example.cinebooker.LeDucThien.entity.timDiaChiRapChieuEntity;

import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ActivityOpen;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link rap_chieu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class rap_chieu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public rap_chieu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment rap_chieu.
     */
    // TODO: Rename and change types and number of parameters
    public static rap_chieu newInstance(String param1, String param2) {
        rap_chieu fragment = new rap_chieu();
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
        View view = inflater.inflate(R.layout.fragment_rap_chieu, container, false);

        TextView TenTinhThanh = view.findViewById(R.id.ten_tinh_thanh);

        LinearLayout danhsachtinhthanh_open = view.findViewById(R.id.open_list_tinh_thanh);
        LinearLayout ganday_search = view.findViewById(R.id.search_gan_ban);

        ImageView icon_location = view.findViewById(R.id.icon_location);
        ImageView icon_location2 = view.findViewById(R.id.icon_location2);
        TextView ganban = view.findViewById(R.id.gan_ban);

        // Xử lý sự kiện click để mở Activity
        danhsachtinhthanh_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityOpen.openActivityOnClick(requireActivity(), DanhSachDiaDiemRap.class, R.id.open_list_tinh_thanh);

                ganday_search.setBackgroundResource(R.drawable.strock_1_white_radius_10_transparent);
                ganban.setTextColor(getContext().getColor(R.color.colorUnSelected));
                icon_location2.setColorFilter(getContext().getColor(R.color.colorUnSelected));

                danhsachtinhthanh_open.setBackgroundResource(R.drawable.strock_1_pink_radius_10_transparent);
                TenTinhThanh.setTextColor(getContext().getColor(R.color.colorSelected));
                icon_location.setColorFilter(getContext().getColor(R.color.colorSelected));
            }

        });

        ganday_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ganday_search.setBackgroundResource(R.drawable.strock_1_pink_radius_10_transparent);
                ganban.setTextColor(getContext().getColor(R.color.colorSelected));
                icon_location2.setColorFilter(getContext().getColor(R.color.colorSelected));

                danhsachtinhthanh_open.setBackgroundResource(R.drawable.strock_1_white_radius_10_transparent);
                icon_location.setColorFilter(getContext().getColor(R.color.colorUnSelected));
            }
        });


        danhSachRap(view);

        danhSachDiaChiRap(view);

        heThongRapChieu(view);
        return view;
    }

    private void danhSachRap (View view) {
        RecyclerView recyclerView = view.findViewById(R.id.danhsachrap_recycle_view);
        BL_RapChieu blRapChieu = new BL_RapChieu();
        RapChieuAdapter adapter = new RapChieuAdapter();
        blRapChieu.loadRapChieuToRecyclerView(getContext(), recyclerView, adapter);
    }

    private void danhSachDiaChiRap (View view) {
        RecyclerView rapChieuRecycleView = view.findViewById(R.id.diachirap_recyclerview);
        rapChieuRecycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        rapChieuRecycleView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        List<timDiaChiRapChieuEntity> rapChieuList = new ArrayList<>();

        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));
        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));
        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));
        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));
        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));
        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));
        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));
        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));
        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));
        rapChieuList.add(new timDiaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Hùng Vương Plaza", "Tầng 7 | Hùng Vương Plaza 126 Hùng Vương Quận 5 Tp. Hồ Chí Minh", "https://www.google.com/maps/place/CGV+Hung+Vuong+Plaza"));

        timDiaChiRapChieuAdapter rapChieuAdapter = new timDiaChiRapChieuAdapter(rapChieuList, getContext());
        rapChieuRecycleView.setAdapter(rapChieuAdapter);

    }

    public void heThongRapChieu(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycleView_heThongRapChieu);
        BL_DoiTac blDoiTac = new BL_DoiTac();
        DoiTacAdapter adapter = new DoiTacAdapter();
        blDoiTac.loadDoiTacToRecyclerView(getContext(), recyclerView, adapter);

    }

}