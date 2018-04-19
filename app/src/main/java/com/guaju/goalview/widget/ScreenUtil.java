package com.guaju.goalview.widget;

import android.content.Context;
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
}
