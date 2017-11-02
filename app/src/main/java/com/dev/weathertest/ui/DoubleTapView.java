package com.dev.weathertest.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class DoubleTapView extends View {

    @Nullable
    private Callback callback;
    private boolean firstTouch;
    private long time;

    public DoubleTapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            if(firstTouch && (System.currentTimeMillis() - time) <= 300) {
                if (callback != null) {
                    callback.onDoubleTap(event.getX(), event.getY());
                }
                firstTouch = false;
                return true;
            } else {
                firstTouch = true;
                time = System.currentTimeMillis();
                return false;
            }
        }
        return super.onTouchEvent(event);
    }

    public void setCallback(@Nullable Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void onDoubleTap(float x, float y);
    }
}
