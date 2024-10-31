package com.example.cinebooker.generalMethod;

import android.content.Intent;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

public class ActivityOpen {
    public static void openActivityOnClick(final FragmentActivity activity, final Class<?> targetActivity, int viewId) {
        View view = activity.findViewById(viewId);
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, targetActivity);
                    activity.startActivity(intent);
                }
            });
        }
    }
}
