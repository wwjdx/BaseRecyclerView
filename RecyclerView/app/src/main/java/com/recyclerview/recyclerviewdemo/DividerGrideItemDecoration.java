package com.diit.recyclerviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerGrideItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    private Drawable mDivider;

    public DividerGrideItemDecoration(Context mContext) {
        final TypedArray typedArray = mContext.obtainStyledAttributes(ATTRS);
        mDivider = typedArray.getDrawable(0);
        typedArray.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView recyclerView) {
        drawVertical(c, recyclerView);
        drawHorizontal(c, recyclerView);
    }

    public void drawVertical(Canvas c, RecyclerView recyclerView) {
        final int left = recyclerView.getPaddingLeft();
        final int right = recyclerView.getWidth() - recyclerView.getPaddingRight();
        final int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = recyclerView.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();
            final int top = childView.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView recyclerView) {
        final int top = recyclerView.getPaddingTop();
        final int bottom = recyclerView.getHeight() - recyclerView.getPaddingBottom();
        final int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = recyclerView.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) childView.getLayoutParams();
            final int left = childView.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.set(0, 0, mDivider.getIntrinsicWidth(), mDivider.getIntrinsicHeight());
    }
}
