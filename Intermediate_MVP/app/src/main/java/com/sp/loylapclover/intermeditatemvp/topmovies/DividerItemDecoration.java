package com.sp.loylapclover.intermeditatemvp.topmovies;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sp.loylapclover.intermeditatemvp.R;

/**
 * Created by Daniel on 08/07/2017.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable divider;

    public DividerItemDecoration(Context context) {
        divider = ContextCompat.getDrawable(context, R.drawable.recyclerview_divider_normal);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + divider.getIntrinsicHeight();

            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
        }
    }
}