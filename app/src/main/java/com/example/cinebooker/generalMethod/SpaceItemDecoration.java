package com.example.cinebooker.generalMethod;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int spaceHeight;

    public SpaceItemDecoration(int spaceHeight) {
        this.spaceHeight = spaceHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        // Thêm khoảng cách dưới mỗi item
        outRect.bottom = spaceHeight;

        // Thêm khoảng cách cho item đầu tiên (nếu cần)
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = spaceHeight;
        }
    }
}