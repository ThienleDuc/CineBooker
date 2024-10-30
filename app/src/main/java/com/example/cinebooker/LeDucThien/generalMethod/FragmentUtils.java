package com.example.cinebooker.LeDucThien.generalMethod;

import android.view.View;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.cinebooker.R;

public class FragmentUtils {
    // Hàm load Fragment vào một container cụ thể
    public static void loadFragment(FragmentActivity activity, Fragment fragment, int containerId) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.commit();
    }

    // Hàm hiển thị Overlay Fragment với animation
    public static void showOverlayFragment(FragmentActivity activity, Fragment fragment, int containerId) {
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_up, R.anim.slide_in_down, R.anim.slide_out_up);
        transaction.addToBackStack(null);
        transaction.commit();

        // Hiển thị FrameLayout chứa overlay
        FrameLayout overlayContainer = activity.findViewById(containerId);
        overlayContainer.setVisibility(View.VISIBLE);
    }

    // Hàm ẩn Overlay Fragment
    public static void hideOverlayFragment(FragmentActivity activity, int containerId) {
        Fragment overlayFragment = activity.getSupportFragmentManager().findFragmentById(containerId);
        if (overlayFragment != null) {
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_down, R.anim.slide_in_down, R.anim.slide_out_up);
            transaction.remove(overlayFragment);
            transaction.commit();
        }

        // Ẩn FrameLayout chứa overlay
        FrameLayout overlayContainer = activity.findViewById(containerId);
        overlayContainer.setVisibility(View.GONE);
    }
}
