package com.example.cinebooker.PhanCongQuoc.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.cinebooker.mainproject.MainActivity;
import com.example.cinebooker.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Profile#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Profile.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile newInstance(String param1, String param2) {
        Profile fragment = new Profile();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        // Inflate the layout for this fragment
        LinearLayout action_level = view.findViewById(R.id.action_level);
        LinearLayout action_voucher = view.findViewById(R.id.action_voucher);
        LinearLayout action_bank= view.findViewById(R.id.action_bank_bulding);
        LinearLayout action_account = view.findViewById(R.id.action_account);
        LinearLayout action_gioithieu = view.findViewById(R.id.action_gioithieu);
        LinearLayout action_phanhoi = view.findViewById(R.id.action_phanhoi);
        LinearLayout action_about = view.findViewById(R.id.action_chungtoi);
        LinearLayout action_chinhsach = view.findViewById(R.id.action_chinnhach);
        LinearLayout action_dieukhoan = view.findViewById(R.id.action_dieukhoan);

        setLinearLayoutClickListener(action_level, new cap_bac());
        setLinearLayoutClickListener(action_bank, new ngan_hang());
        setLinearLayoutClickListener(action_account, new taikhoan_baomat());
        setLinearLayoutClickListener(action_voucher, new voucher());

        setLinearLayoutClickListenerOpenUrl(action_gioithieu, "https://www.figma.com/design/CEbN97NZxjJGNBXZ1FxEZd/nhom_LTTDD?node-id=0-1&node-type=canvas&t=MZ9AjqqUg1wsfYpM-0");
        setLinearLayoutClickListenerOpenUrl(action_phanhoi, "https://www.figma.com/design/CEbN97NZxjJGNBXZ1FxEZd/nhom_LTTDD?node-id=0-1&node-type=canvas&t=MZ9AjqqUg1wsfYpM-0");
        setLinearLayoutClickListenerOpenUrl(action_about, "https://www.figma.com/design/CEbN97NZxjJGNBXZ1FxEZd/nhom_LTTDD?node-id=0-1&node-type=canvas&t=MZ9AjqqUg1wsfYpM-0");
        setLinearLayoutClickListenerOpenUrl(action_chinhsach, "https://www.figma.com/design/CEbN97NZxjJGNBXZ1FxEZd/nhom_LTTDD?node-id=0-1&node-type=canvas&t=MZ9AjqqUg1wsfYpM-0");
        setLinearLayoutClickListenerOpenUrl(action_dieukhoan, "https://www.figma.com/design/CEbN97NZxjJGNBXZ1FxEZd/nhom_LTTDD?node-id=0-1&node-type=canvas&t=MZ9AjqqUg1wsfYpM-0");


        return view;
    }

    private void setLinearLayoutClickListener(LinearLayout linearLayout, Fragment overlayFragment) {
        linearLayout.setOnClickListener(v -> showOverlayFragment(overlayFragment));
    }

    private void showOverlayFragment(Fragment fragment) {
        // Gọi đến phương thức trong MainActivity
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showOverlayFragment(fragment);
        }
    }

    // Phương thức để thiết lập sự kiện click cho LinearLayout và mở URL
    private void setLinearLayoutClickListenerOpenUrl(LinearLayout linearLayout, final String url) {
        linearLayout.setOnClickListener(v -> {
            // Tạo một Intent để mở trình duyệt
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        });
    }
}