package com.diit.recyclerviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    private Drawable mDivider;
    private int mOrientation;
    public DividerItemDecoration(Context mContext,int mOrientation){
        final TypedArray typedArray=mContext.obtainStyledAttributes(ATTRS);
        mDivider=typedArray.getDrawable(0);
        typedArray.recycle();
        setOrientation(mOrientation);

    }
    public void setOrientation(int mOrientation){
        if (mOrientation!=HORIZONTAL_LIST&&mOrientation!=VERTICAL_LIST){
            throw new IllegalArgumentException("invalid orientation");
        }
        this.mOrientation=mOrientation;
    }

    @Override
    public void onDraw(Canvas c,RecyclerView recyclerView) {
        if (mOrientation==VERTICAL_LIST){
            drawVertical(c,recyclerView);
        }else{
            drawHorizontal(c,recyclerView);
        }
    }
    public void drawVertical(Canvas c,RecyclerView recyclerView){
        final int left=recyclerView.getPaddingLeft();
        final int right=recyclerView.getWidth()-recyclerView.getPaddingRight();
        final int childCount=recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView=recyclerView.getChildAt(i);
            final RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) childView.getLayoutParams();
            final int top=childView.getBottom()+params.bottomMargin;
            final int bottom=top+mDivider.getIntrinsicHeight();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }
    public void drawHorizontal(Canvas c,RecyclerView recyclerView){
        final int top=recyclerView.getPaddingTop();
        final int bottom=recyclerView.getHeight()-recyclerView.getPaddingBottom();
        final int childCount=recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView=recyclerView.getChildAt(i);
            final RecyclerView.LayoutParams params= (RecyclerView.LayoutParams) childView.getLayoutParams();
            final int left=childView.getRight()+params.rightMargin;
            final int right=left+mDivider.getIntrinsicWidth();
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (mOrientation==VERTICAL_LIST){
            outRect.set(0,0,0,mDivider.getIntrinsicHeight());
        }else{
            outRect.set(0,0,mDivider.getIntrinsicWidth(),0);
        }
    }
}
