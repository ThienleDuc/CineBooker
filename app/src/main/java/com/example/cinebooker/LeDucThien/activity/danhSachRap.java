package com.example.cinebooker.LeDucThien.activity;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinebooker.LeDucThien.adapter.diaChiRapChieuAdapter;
import com.example.cinebooker.LeDucThien.adapter.rapChieuAdapter;
import com.example.cinebooker.LeDucThien.entity.diaChiRapChieuEntity;
import com.example.cinebooker.LeDucThien.entity.rapChieuEntity;
import com.example.cinebooker.LeDucThien.generalMethod.FragmentUtils;
import com.example.cinebooker.LeDucThien.generalMethod.HorizontalSpaceItemDecoration;
import com.example.cinebooker.LeDucThien.generalMethod.SpaceItemDecoration;
import com.example.cinebooker.R;

import java.util.ArrayList;
import java.util.List;

public class danhSachRap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_danh_sach_rap);

        ImageView close = findViewById(R.id.danhsachrap_close);

        close.setOnClickListener(v-> onBackPressed());

        danhSachRap();

        danhSachDiaChiRap();
    }

    private void danhSachRap () {
        RecyclerView rapChieuRecycleView = findViewById(R.id.danhsachrap_recycle_view);
        rapChieuRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        rapChieuRecycleView.addItemDecoration(new HorizontalSpaceItemDecoration(spacingInPixels));

        List<rapChieuEntity> rapChieuList = new ArrayList<>();

        rapChieuList.add(new rapChieuEntity(R.drawable.star, "Tất cả"));
        rapChieuList.add(new rapChieuEntity(R.drawable.cgvlogo, "CGV"));
        rapChieuList.add(new rapChieuEntity(R.drawable.lottelogo, "Lotte Cinema"));
        rapChieuList.add(new rapChieuEntity(R.drawable.bhd_logo, "BHD Cinema"));
        rapChieuList.add(new rapChieuEntity(R.drawable.logo_beta, "Beta Cinema"));
        rapChieuList.add(new rapChieuEntity(R.drawable.mega_logo, "Mega GS"));
        rapChieuList.add(new rapChieuEntity(R.drawable.galaxy_cinema, "Galaxy Cinema"));
        rapChieuList.add(new rapChieuEntity(R.drawable.cinerstar, "CineStar"));
        rapChieuList.add(new rapChieuEntity(R.drawable.dcine_logo, "Dcine"));

        rapChieuAdapter rapChieuAdapter = new rapChieuAdapter(rapChieuList);
        rapChieuRecycleView.setAdapter(rapChieuAdapter);

    }

    private void danhSachDiaChiRap () {
        RecyclerView rapChieuRecycleView = findViewById(R.id.diachirap_recyclerview);
        rapChieuRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recycler_view_spacing_5);
        rapChieuRecycleView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        List<diaChiRapChieuEntity> rapChieuList = new ArrayList<>();

        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));
        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));
        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));
        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));
        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));
        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));
        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));
        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));
        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));
        rapChieuList.add(new diaChiRapChieuEntity(R.drawable.cgvlogo, "CGV Aeon Bình Tân"));


        diaChiRapChieuAdapter rapChieuAdapter = new diaChiRapChieuAdapter(rapChieuList);
        rapChieuRecycleView.setAdapter(rapChieuAdapter);

    }
}