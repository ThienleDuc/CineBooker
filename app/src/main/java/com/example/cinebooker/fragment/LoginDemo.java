package com.example.cinebooker.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cinebooker.R;
import com.example.cinebooker.mainproject.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginDemo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginDemo extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginDemo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginDemo.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginDemo newInstance(String param1, String param2) {
        LoginDemo fragment = new LoginDemo();
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
        View view =  inflater.inflate(R.layout.fragment_login_demo, container, false);

        Button btnLogin = view.findViewById(R.id.btn_login);
        Button btnSkip = view.findViewById(R.id.btn_skip);

        // Xử lý khi người dùng chọn Đăng nhập
        btnLogin.setOnClickListener(v -> navigateToMovies());

        // Xử lý khi người dùng chọn Bỏ qua
        btnSkip.setOnClickListener(v -> navigateToMovies());
        return view;
    }

    // Điều hướng tới trang Movies
    private void navigateToMovies() {
        // Điều hướng tới Movies Fragment thông qua MainActivity
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).loadFragment(new Movies());
            hideOverlayFragment();  // Ẩn Overlay sau khi điều hướng
        }
    }

    private void hideOverlayFragment() {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).hideOverlayFragment();
        }
    }
}