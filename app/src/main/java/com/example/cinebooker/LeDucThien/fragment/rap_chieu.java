package com.example.cinebooker.LeDucThien.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cinebooker.LeDucThien.BussinessLogic.BL_DoiTac;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_RapChieu;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_RapChieuCon;
import com.example.cinebooker.LeDucThien.BussinessLogic.BL_TinhThanh;
import com.example.cinebooker.LeDucThien.activity.DanhSachDiaDiemRap;
import com.example.cinebooker.LeDucThien.activity.danhSachRap;
import com.example.cinebooker.LeDucThien.adapter.DiaChiRapChieuAdapter;
import com.example.cinebooker.LeDucThien.adapter.RapChieuAdapter;
import com.example.cinebooker.LeDucThien.adapter.DoiTacAdapter;

import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.ActivityOpen;



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
    private String mParam1;
    private String mParam2;
    public int _maTinhThanh;
    public int _maRapChieu;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    String previousSearch = " ";

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
            // TODO: Rename and change types of parameters
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rap_chieu, container, false);

        controllerLichChieu(view);
        danhSachRap(view);

        danhSachDiaChiRap(view);

        heThongRapChieu(view);
        return view;
    }

    public void controllerLichChieu(View view) {
        TextView TenTinhThanh = view.findViewById(R.id.ten_tinh_thanh);

        // Khởi tạo TabLayout cho location
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
                TenTinhThanh.setTextColor(getContext().getColor(R.color.colorUnSelected));
                icon_location.setColorFilter(getContext().getColor(R.color.colorUnSelected));
            }
        });
    }

    private void danhSachRap (View view) {
        RecyclerView recyclerView = view.findViewById(R.id.danhsachrap_recycle_view);
        BL_RapChieu blRapChieu = new BL_RapChieu();
        RapChieuAdapter adapter = new RapChieuAdapter();
        blRapChieu.loadRapChieuToRecyclerView(getContext(), recyclerView, adapter);
    }

    private void danhSachDiaChiRap(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.diachirap_recyclerview);
        BL_RapChieuCon blRapChieuCon = new BL_RapChieuCon();
        BL_RapChieu blRapChieu = new BL_RapChieu();

        // Lấy SharedPreferences để lấy giá trị maRapChieu, maTinhThanh
        sharedPreferences = requireContext().getSharedPreferences("LeDucThien", Context.MODE_PRIVATE);

        // Lấy giá trị maRapChieu từ SharedPreferences, nếu không có, lấy maRapChieu nhỏ nhất từ BL_RapChieu
        _maRapChieu = sharedPreferences.getInt("maRapChieu", -1);
        if (_maRapChieu == -1) {
            _maRapChieu = blRapChieu.loadMinMaRapChieu();
            blRapChieu.updateSharedPreferences(getContext(), _maRapChieu);
        }

        // Lấy giá trị maTinhThanh từ SharedPreferences
        _maTinhThanh = sharedPreferences.getInt("maTinhThanh", -1);

        // Tạo adapter và gọi BL_RapChieuCon để load dữ liệu vào RecyclerView
        DiaChiRapChieuAdapter adapter = new DiaChiRapChieuAdapter();
        blRapChieuCon.loadDiaChiRapChieuConToRecyclerView(getContext(), recyclerView, _maTinhThanh, _maRapChieu, adapter);

        EditText editText = view.findViewById(R.id.header_search_input);
        // TextWatcher để xử lý tìm kiếm
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Không cần xử lý
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Không cần xử lý
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String input = editable.toString().trim();

                // Cập nhật dữ liệu chỉ khi từ khóa tìm kiếm thay đổi
                if (!input.equals(previousSearch)) {
                    previousSearch = input;

                    if (!input.isEmpty()) {
                        Log.d("Search", "Searching for: " + input);  // Log tìm kiếm
                        blRapChieuCon.loadDiaChiRapChieuConToRecyclerViewAfterSearch(
                                requireContext(),
                                recyclerView,
                                _maTinhThanh,
                                _maRapChieu,
                                input,
                                adapter);
                    } else {
                        Log.d("Search", "Clearing search input");
                    }
                }
            }
        };

        // Xử lý sự kiện focus của EditText
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                editText.addTextChangedListener(textWatcher);
            } else {
                editText.removeTextChangedListener(textWatcher);
            }
        });
    }


    public void heThongRapChieu(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recycleView_heThongRapChieu);
        BL_DoiTac blDoiTac = new BL_DoiTac();
        DoiTacAdapter adapter = new DoiTacAdapter();
        blDoiTac.loadDoiTacToRecyclerView(getContext(), recyclerView, adapter);
    }

}