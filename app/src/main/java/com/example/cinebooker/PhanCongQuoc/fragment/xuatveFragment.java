package com.example.cinebooker.PhanCongQuoc.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cinebooker.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link xuatveFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class xuatveFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public xuatveFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment xuatveFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static xuatveFragment newInstance(String param1, String param2) {
        xuatveFragment fragment = new xuatveFragment();
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
        View view = inflater.inflate(R.layout.fragment_xuatve, container, false);

        // Thiết lập listener cho nút quay lại
        ImageView backButton = view.findViewById(R.id.itemback);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Quay về da_dungFragment
                Fragment chuadungFragment = new ticket_chuadungFragment(); // Tạo instance của da_dungFragment
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, chuadungFragment) // Đảm bảo ID này chính xác
                        .addToBackStack(null) // Thêm vào back stack nếu muốn quay lại
                        .commit();
            }
        });

        return view;  }
}