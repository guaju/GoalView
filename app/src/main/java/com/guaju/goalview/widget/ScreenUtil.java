package com.guaju.goalview.widget;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by guaju on 2018/4/18.
 */

public class ScreenUtil {

    private static WindowManager wm;

    public static int getScreenWidth(Context context){
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display dd = wm.getDefaultDisplay();
        int width = dd.getWidth();
        return width;

    }
        //拿到屏幕密度
    public static  float getScreenMetricDgree(Context context){
        if (wm==null){
            wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        Display defaultDisplay = wm.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        float density = displayMetrics.density;
        return  density;
    }
}
