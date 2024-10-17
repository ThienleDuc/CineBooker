package com.example.cinebooker.PhanCongQuoc.fragment_vephim;

import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.FragmentTransaction;
import com.example.cinebooker.R;

import java.util.Calendar;

public class registerFragment extends Fragment {

    private EditText dateInput;

    public registerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        dateInput = view.findViewById(R.id.date_input);

        // Thiết lập sự kiện cho EditText ngày sinh
        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Thiết lập sự kiện cho TextView "Quay lại"
        TextView backTextView = view.findViewById(R.id.back_register);
        backTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBackToLoginFragment();
            }
        });

        // Thiết lập sự kiện cho nút "Đăng Ký"
        Button registerButton = view.findViewById(R.id.register_btn);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện đăng ký (có thể thêm logic xử lý đăng ký ở đây)
                navigateBackToLoginFragment(); // Quay lại loginFragment sau khi đăng ký
            }
        });

        return view;
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Cập nhật ngày đã chọn vào EditText
                    String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    dateInput.setText(date);
                }, year, month, day);

        datePickerDialog.show();
    }

    private void navigateBackToLoginFragment() {
        // Tạo và hiển thị loginFragment
        loginFragment fragment = new loginFragment();
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null); // Thêm vào back stack
        transaction.commit();
    }
}
