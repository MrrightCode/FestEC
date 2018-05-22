package com.example.yb.latte_core.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.example.yb.latte_core.R;
import com.example.yb.latte_core.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by yangbin on 2018/5/22.
 */
public class LatteLoader {

    private static final int LOADER_SIZE_SCALE = 8;
    private static final int LOADER_OFFSET_SCALE = 10;
    private static final String DEFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    public  static  final ArrayList<AppCompatDialog> LOADER = new ArrayList<>();

    public static  void showLoading(Context context,String type){

        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type,context);

        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenheight();

        final Window dialogWindow = dialog.getWindow();

        if(dialogWindow != null){
            WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
            layoutParams.width = deviceWidth/LOADER_SIZE_SCALE;
            layoutParams.height = deviceHeight/LOADER_SIZE_SCALE;
            //设置一个偏移量
            layoutParams.height = layoutParams.height+deviceHeight/LOADER_OFFSET_SCALE;
            layoutParams.gravity = Gravity.CENTER;

        }

        LOADER.add(dialog);
        dialog.show();
    }

    public static  void showLoading(Context context){
        showLoading(context,DEFAULT_LOADER);
    }


    public static  void showLoading(Context context,Enum<LoaderStyle> styleEnum){
        showLoading(context,styleEnum.name());
    }

    public static void stopLoading(){
        for (AppCompatDialog dialog : LOADER){
            if(dialog != null){
                dialog.cancel();
               // dialog.dismiss(); cancel会执行回调  dismiss  只是消失
            }
        }
    }
}
