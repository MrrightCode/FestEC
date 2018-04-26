package com.example.yb.latte_core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Create by :yb on 2018/4/23
 * Description:  管理全局设置 final 类 不会被继承修改
 * 对外工具类都是静态的方法
 */
public final class Latte {

    /**
    *@Create by  yb on 2018/4/24
    *@params: 获取具体配置类的方法
    */
    public static Configurator init(Context context){
        //首先设置Context
        getConfigurations().put(Configtype.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String ,Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }


    public static Context getApplicationContext(){
        return (Context) getConfigurations().get(Configtype.APPLICATION_CONTEXT.name());
    }

}
