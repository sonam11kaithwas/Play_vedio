package com.example.myapplication;

import android.content.Context;
import android.util.AttributeSet;

import com.google.android.exoplayer2.ui.PlayerView;

/**
 * Created by Sonam-11 on 26/5/20.
 */
public class MySimpleExoPlayerView2 extends PlayerView {
    public MySimpleExoPlayerView2(Context context) {
        super(context);
    }

    public MySimpleExoPlayerView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MySimpleExoPlayerView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /****change mode fill/fitt**/
    @Override
    public void setResizeMode(int resizeMode) {
        super.setResizeMode(resizeMode);
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
    }


}
