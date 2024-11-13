package com.example.cinebooker.PhanCongQuoc.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_voucherAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.ticketvoucherMoviesEntity;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class voucher extends AppCompatActivity {

    private RecyclerView list_voucher;
    private Ticket_voucherAdapter ticketvoucherAdapter;
    private List<ticketvoucherMoviesEntity> ticketvoucherMoviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);
        ImageView back = findViewById(R.id.voucher_back);
        back.setOnClickListener(v -> onBackPressed());

        // Khởi tạo RecyclerView và thiết lập layout manager
        list_voucher = findViewById(R.id.list_voucher);
        list_voucher.setLayoutManager(new LinearLayoutManager(this));

        // Thêm khoảng cách giữa các item trong RecyclerView
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        list_voucher.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        // Khởi tạo danh sách và thêm các phần tử
        ticketvoucherMoviesList = new ArrayList<>();
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

        // Thiết lập adapter và gắn vào RecyclerView
        ticketvoucherAdapter = new Ticket_voucherAdapter(ticketvoucherMoviesList);
        list_voucher.setAdapter(ticketvoucherAdapter);
    }
}
