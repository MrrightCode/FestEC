package com.example.yb.festec;

import android.app.Application;

import com.example.yb.latte_core.app.Latte;
import com.example.yb.latte_ec.icon.FontEcModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Create by :yb on 2018/4/24
 * Description:
 */
public class LatteApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withApiHost("http://127.0.0.1")
                .configure();

    }
}
