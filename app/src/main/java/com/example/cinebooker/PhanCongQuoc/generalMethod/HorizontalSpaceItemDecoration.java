package com.example.cinebooker.PhanCongQuoc.generalMethod;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class HorizontalSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final int horizontalSpace;

    public HorizontalSpaceItemDecoration(int horizontalSpace) {
        this.horizontalSpace = horizontalSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        // Thêm khoảng cách phải mỗi item
        outRect.left = horizontalSpace;
        outRect.right = horizontalSpace;

        // Thêm khoảng cách cho item đầu tiên (nếu cần)
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = 0;
        }
    }
}
