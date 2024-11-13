package com.example.cinebooker.PhanCongQuoc.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_capbacAdapter;
import com.example.cinebooker.PhanCongQuoc.adapter.Ticket_capbacAdapter;
import com.example.cinebooker.PhanCongQuoc.entity.ticketcapbacMoviesEntity;
import com.example.cinebooker.PhanCongQuoc.entity.ticketcapbacMoviesEntity;
import com.example.cinebooker.R;
import com.example.cinebooker.generalMethod.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class cap_bac extends AppCompatActivity {
    private RecyclerView list_capbac;
    private Ticket_capbacAdapter ticketcapbacAdapter;
    private List<ticketcapbacMoviesEntity> ticketcapbacMoviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cap_bac);

        ImageView back = findViewById(R.id.level_back);
        back.setOnClickListener(v -> onBackPressed());

        // Khởi tạo RecyclerView và thiết lập layout manager
        list_capbac = findViewById(R.id.list_capbac);
        list_capbac.setLayoutManager(new LinearLayoutManager(this));

        // Thêm khoảng cách giữa các item trong RecyclerView
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        list_capbac.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        // Khởi tạo danh sách và thêm các phần tử
        ticketcapbacMoviesList = new ArrayList<>();
        ticketcapbacMoviesList.add(new ticketcapbacMoviesEntity(

                "KIM CƯƠNG",
                R.drawable.icon17_itemvoucher,
                "Giảm 10% giảm tối đa 20%",
                "Đơn tối thiểu 80k",
                "05/10/2024"

        ));
        ticketcapbacMoviesList.add(new ticketcapbacMoviesEntity(

                "KIM CƯƠNG",
                R.drawable.icon17_itemvoucher,
                "Giảm 10% giảm tối đa 20%",
                "Đơn tối thiểu 80k",
                "05/10/2024"

        ));
        ticketcapbacMoviesList.add(new ticketcapbacMoviesEntity(

                "KIM CƯƠNG",
                R.drawable.icon17_itemvoucher,
                "Giảm 10% giảm tối đa 20%",
                "Đơn tối thiểu 80k",
                "05/10/2024"

        ));
        ticketcapbacMoviesList.add(new ticketcapbacMoviesEntity(

                "KIM CƯƠNG",
                R.drawable.icon17_itemvoucher,
                "Giảm 10% giảm tối đa 20%",
                "Đơn tối thiểu 80k",
                "05/10/2024"

        ));


        // Thiết lập adapter và gắn vào RecyclerView
        ticketcapbacAdapter = new Ticket_capbacAdapter(ticketcapbacMoviesList);
        list_capbac.setAdapter(ticketcapbacAdapter);
    }
}