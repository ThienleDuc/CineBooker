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
import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_voucherAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.ticketchuadungMoviesEntity;
import com.example.cinebooker.PhanCongQuoc.entity.ticketdadungMoviesEntity;
import com.example.cinebooker.PhanCongQuoc.entity.ticketvoucherMoviesEntity;
import com.example.cinebooker.PhanCongQuoc.generalMethod.SpaceItemDecoration;
import com.example.cinebooker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link voucherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class voucherFragment extends Fragment {
    private RecyclerView list_voucher;
    private Ticket_voucherAdapter ticketvoucherAdapter;
    private List<ticketvoucherMoviesEntity> ticketvoucherMoviesList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public voucherFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment voucherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static voucherFragment newInstance(String param1, String param2) {
        voucherFragment fragment = new voucherFragment();
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
        View view = inflater.inflate(R.layout.fragment_voucher, container, false);

        list_voucher = view.findViewById(R.id.list_voucher);
        list_voucher.setLayoutManager(new LinearLayoutManager(getContext()));

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        list_voucher.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        ticketvoucherMoviesList = new ArrayList<>();
        // Khởi tạo ticketchuadungMoviesEntity theo thứ tự trong TicketViewHolder
        ticketvoucherMoviesList.add(new ticketvoucherMoviesEntity(
                "TEAM 17",
                R.drawable.icon17_itemvoucher,
                "Giảm 10% giảm tối đa 20%",
                "Đơn tối thiểu 80k",
                "05/10/2024",
                "75%"

        ));
        ticketvoucherMoviesList.add(new ticketvoucherMoviesEntity(
                "TEAM 17",
                R.drawable.icon17_itemvoucher,
                "Giảm 10% giảm tối đa 20%",
                "Đơn tối thiểu 80k",
                "05/10/2024",
                "75%"

        ));
        ticketvoucherMoviesList.add(new ticketvoucherMoviesEntity(
                "TEAM 17",
                R.drawable.icon17_itemvoucher,
                "Giảm 10% giảm tối đa 20%",
                "Đơn tối thiểu 80k",
                "05/10/2024",
                "75%"

        ));
        ticketvoucherMoviesList.add(new ticketvoucherMoviesEntity(
                "TEAM 17",
                R.drawable.icon17_itemvoucher,
                "Giảm 10% giảm tối đa 20%",
                "Đơn tối thiểu 80k",
                "05/10/2024",
                "75%"

        ));




        ticketvoucherAdapter = new Ticket_voucherAdapter(ticketvoucherMoviesList);
        list_voucher.setAdapter(ticketvoucherAdapter);

        return view;      }
}