package com.example.cinebooker.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cinebooker.R;
import com.example.cinebooker.mainproject.MainActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link startapp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class startapp extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public startapp() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment startapp.
     */
    // TODO: Rename and change types and number of parameters
    public static startapp newInstance(String param1, String param2) {
        startapp fragment = new startapp();
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
        View view = inflater.inflate(R.layout.fragment_startapp, container, false);

        // Sau 300ms, chuyển sang trang Đăng nhập
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showOverlayFragment(new LoginDemo());
            }
        }, 1000);


        return view;
    }

    private void showOverlayFragment(Fragment fragment) {
        // Gọi đến phương thức trong MainActivity
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showOverlayFragment(fragment);
        }
    }
}