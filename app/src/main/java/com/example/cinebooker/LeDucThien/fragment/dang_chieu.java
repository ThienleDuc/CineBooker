package com.example.cinebooker.LeDucThien.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cinebooker.LeDucThien.adapter.moviesDangChieuAdapter;
import com.example.cinebooker.LeDucThien.adapter.binhLuanNoiBatAdapter;
import com.example.cinebooker.LeDucThien.entity.caroselDangChieuEntity;
import com.example.cinebooker.LeDucThien.entity.binhLuanNoiBatEntity;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link dang_chieu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class dang_chieu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public dang_chieu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment dang_chieu.
     */
    // TODO: Rename and change types and number of parameters
    public static dang_chieu newInstance(String param1, String param2) {
        dang_chieu fragment = new dang_chieu();
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
        View view = inflater.inflate(R.layout.fragment_dang_chieu, container, false);

        dangChieu(view);

        binhluan(view);

        return view;
    }

    public void dangChieu (View view) {

        RecyclerView dangChieuRecycleView = view.findViewById(R.id.recycleView_dangChieu);
        dangChieuRecycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        dangChieuRecycleView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        List<caroselDangChieuEntity> movieDangChieuList = new ArrayList<>();

        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));
        movieDangChieuList.add(new caroselDangChieuEntity(R.drawable.camposter, "18+",6.2,"Cám", "Kinh dị"));

        moviesDangChieuAdapter dangChieuAdapter = new moviesDangChieuAdapter(movieDangChieuList);
        dangChieuRecycleView.setAdapter(dangChieuAdapter);
    }

    public void binhluan (View view) {

        RecyclerView recycleView = view.findViewById(R.id.recycleView_comment);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        recycleView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        List<binhLuanNoiBatEntity> movieDangChieuList = new ArrayList<>();

        movieDangChieuList.add(new binhLuanNoiBatEntity(R.drawable.camposter,
                "18+", "Cám", "Kinh dị",
                6.2, 10500.0, 32000.0,
                R.drawable.avatar, "Lê Đức Thiện", "03/10/2024",
                "TThấy phim OK mà bị mọi người chê dữ, có bám theo truyện nhưng tình tiết bị đảo. Công tâm mà nói thì so với mặt bằng ở Việt Nam thì Cám làm tốt, kỉ xảo đẹp, thoại có nhiều khúc không nghe rõ. Lần này Rima Thanh Vy phải gọi là hợp vai thật sự, một Tấm nhẹ nhàng đằm thắm, luôn yêu thương và suy nghĩ cho em gái. Cám (Thanh Mỹ) xứng đáng đóng chính nhiều phim hơn. Tất cả diễn viên đều làm tốt vai trò. Có lẽ do kết quá nhanh và kết bằng hình vẽ nên gây nhiều tranh cãi. Nhưng với mình thấy như vậy là ổn 7/10."));

        movieDangChieuList.add(new binhLuanNoiBatEntity(R.drawable.camposter,
                "18+", "Cám", "Kinh dị",
                6.2, 10500.0, 32000.0,
                R.drawable.avatar, "Lê Đức Thiện", "03/10/2024",
                "TThấy phim OK mà bị mọi người chê dữ, có bám theo truyện nhưng tình tiết bị đảo. Công tâm mà nói thì so với mặt bằng ở Việt Nam thì Cám làm tốt, kỉ xảo đẹp, thoại có nhiều khúc không nghe rõ. Lần này Rima Thanh Vy phải gọi là hợp vai thật sự, một Tấm nhẹ nhàng đằm thắm, luôn yêu thương và suy nghĩ cho em gái. Cám (Thanh Mỹ) xứng đáng đóng chính nhiều phim hơn. Tất cả diễn viên đều làm tốt vai trò. Có lẽ do kết quá nhanh và kết bằng hình vẽ nên gây nhiều tranh cãi. Nhưng với mình thấy như vậy là ổn 7/10."));

        movieDangChieuList.add(new binhLuanNoiBatEntity(R.drawable.camposter,
                "18+", "Cám", "Kinh dị",
                6.2, 10500.0, 32000.0,
                R.drawable.avatar, "Lê Đức Thiện", "03/10/2024",
                "TThấy phim OK mà bị mọi người chê dữ, có bám theo truyện nhưng tình tiết bị đảo. Công tâm mà nói thì so với mặt bằng ở Việt Nam thì Cám làm tốt, kỉ xảo đẹp, thoại có nhiều khúc không nghe rõ. Lần này Rima Thanh Vy phải gọi là hợp vai thật sự, một Tấm nhẹ nhàng đằm thắm, luôn yêu thương và suy nghĩ cho em gái. Cám (Thanh Mỹ) xứng đáng đóng chính nhiều phim hơn. Tất cả diễn viên đều làm tốt vai trò. Có lẽ do kết quá nhanh và kết bằng hình vẽ nên gây nhiều tranh cãi. Nhưng với mình thấy như vậy là ổn 7/10."));

        binhLuanNoiBatAdapter dangChieuAdapter = new binhLuanNoiBatAdapter(movieDangChieuList);
        recycleView.setAdapter(dangChieuAdapter);
    }
}