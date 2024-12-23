

package com.example.cinebooker.LeDucThien.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cinebooker.LeDucThien.activity.taiKhoan_BaoMat;
import com.example.cinebooker.PhanCongQuoc.activity.cap_bac;
import com.example.cinebooker.PhanCongQuoc.activity.phuong_thuc_thanh_toan;
import com.example.cinebooker.PhanCongQuoc.activity.voucher;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ActivityOpen;
import com.example.cinebooker.generalMethod.ConnectionDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Profile extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Profile() {
        // Required empty public constructor
    }

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

        // Khai báo các TextView để hiển thị tên và email khách hàng
        TextView tenkh = view.findViewById(R.id.tenkh);
        TextView email = view.findViewById(R.id.emailkh);

        // Khai báo các LinearLayout cho các mục khác
        LinearLayout action_level = view.findViewById(R.id.action_level);
        LinearLayout action_voucher = view.findViewById(R.id.action_voucher);
        LinearLayout action_bank = view.findViewById(R.id.action_bank_bulding);
        LinearLayout action_account = view.findViewById(R.id.action_account);

        // Thiết lập sự kiện click cho các mục
        setLinearLayoutClickListener(action_level, cap_bac.class, R.id.action_level);
        setLinearLayoutClickListener(action_voucher, voucher.class, R.id.action_voucher);
        setLinearLayoutClickListener(action_bank, phuong_thuc_thanh_toan.class, R.id.action_bank_bulding);
        setLinearLayoutClickListener(action_account, taiKhoan_BaoMat.class, R.id.action_account);

        // Lấy MaKhachHang từ SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("QuocDepTrai", getContext().MODE_PRIVATE);
        int maKhachHang = sharedPreferences.getInt("user_id", -1);  // Mặc định là -1 nếu không tìm thấy

        if (maKhachHang != -1) {
            // Nếu có MaKhachHang, thực hiện truy vấn
            getUserInfo(maKhachHang, tenkh, email);
        } else {
            Toast.makeText(getContext(), "Không tìm thấy thông tin người dùng", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    private void setLinearLayoutClickListener(LinearLayout linearLayout, Class<?> targetActivity, int id) {
        linearLayout.setOnClickListener(v -> {
            ActivityOpen.openActivityOnClick(requireActivity(), targetActivity, id);
        });
    }

    // Phương thức để truy vấn thông tin khách hàng từ cơ sở dữ liệu và cập nhật UI
    private void getUserInfo(int maKhachHang, TextView tenkh, TextView email) {
        // Truy vấn thông tin từ cơ sở dữ liệu bằng MaKhachHang
        new Thread(() -> {
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;

            try {
                connection = new ConnectionDatabase().getConnection();
                if (connection == null) {
                    return;
                }

                String sql = "SELECT * FROM KhachHang WHERE MaKhachHang = ?";
                statement = connection.prepareStatement(sql);
                statement.setInt(1, maKhachHang);

                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String tenKhachHang = resultSet.getString("TenKhachHang");
                    String emailKhachHang = resultSet.getString("Email");

                    // Cập nhật giao diện người dùng với dữ liệu từ cơ sở dữ liệu
                    getActivity().runOnUiThread(() -> {
                        tenkh.setText(tenKhachHang);  // Cập nhật tên khách hàng
                        email.setText(emailKhachHang);  // Cập nhật email khách hàng
                    });
                } else {
                    getActivity().runOnUiThread(() -> {
                        Toast.makeText(getContext(), "Không tìm thấy thông tin người dùng", Toast.LENGTH_SHORT).show();
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) resultSet.close();
                    if (statement != null) statement.close();
                    if (connection != null) connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
