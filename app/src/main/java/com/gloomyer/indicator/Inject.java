package com.gloomyer.indicator;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Gloomy on 2016/12/19.
 */

public class Inject extends FrameLayout implements ViewPager.OnPageChangeListener {
    private ShapeDrawable selectShape;
    private MyVp mViewPager;
    private ShapeDrawable defaultShape;
    private View moveView;

    public Inject(Context context) {
        this(context, null);
    }

    public Inject(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Inject(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        {
            OvalShape ovalShape = new OvalShape();
            defaultShape = new ShapeDrawable(ovalShape);
            defaultShape.getPaint().setColor(Color.BLACK);
            defaultShape.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        }

        {
            OvalShape ovalShape = new OvalShape();
            selectShape = new ShapeDrawable(ovalShape);
            selectShape.getPaint().setColor(Color.BLUE);
            selectShape.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
        }

    }

    public void setViewPager(MyVp vp, boolean isInfinite) {
        this.mViewPager = vp;
        mViewPager.addOnPageChangeListener(this);
        initView();
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mViewPager != null)
            mViewPager.removeOnPageChangeListener(this);
        mViewPager = null;
        super.onDetachedFromWindow();
    }

    private void initView() {
        for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
            View v = new View(getContext());
            v.setBackgroundDrawable(defaultShape);
            LayoutParams layoutParams = new LayoutParams(14, 14);
            layoutParams.leftMargin = i * 42;
            addView(v, layoutParams);
        }


        moveView = new View(getContext());
        moveView.setBackgroundDrawable(selectShape);
        LayoutParams layoutParams = new LayoutParams(14, 14);
        addView(moveView, layoutParams);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        LayoutParams layoutParams = (LayoutParams) moveView.getLayoutParams();
        layoutParams.leftMargin = (int) (position * 42 + (42 * positionOffset));
        moveView.setLayoutParams(layoutParams);
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
