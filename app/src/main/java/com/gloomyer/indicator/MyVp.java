package com.gloomyer.indicator;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Gloomy on 2016/12/19.
 */

public class MyVp extends ViewPager {

    public MyVp(Context context) {
        super(context);
    }

    public MyVp(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }
}
