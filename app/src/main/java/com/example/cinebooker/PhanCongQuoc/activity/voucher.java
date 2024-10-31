package com.example.cinebooker.PhanCongQuoc.activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_voucher);

    }

    public void ticketvoucher(){

        list_voucher = findViewById(R.id.list_voucher);
        list_voucher.setLayoutManager(new LinearLayoutManager(this));

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        list_voucher.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        ticketvoucherMoviesList = new ArrayList<>();
        // Khởi tạo ticketchuadungMoviesEntity theo thứ tự trong TicketViewHolder
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

        ticketvoucherAdapter = new Ticket_voucherAdapter(ticketvoucherMoviesList);
        list_voucher.setAdapter(ticketvoucherAdapter);
    }
}