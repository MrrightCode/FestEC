package com.example.yb.latte_core.util.Dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.yb.latte_core.app.Latte;

/**
 * Created by yangbin on 2018/5/22.
 */
public class DimenUtil {

    /**
     * 得到屏幕的宽度
     */
    public static int getScreenWidth(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕的高度
     */

    public static int getScreenheight(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

}
