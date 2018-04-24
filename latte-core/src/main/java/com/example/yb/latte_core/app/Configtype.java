package com.example.yb.latte_core.app;

/**
 * Create by :yb on 2018/4/23
 * Description: 枚举类型  唯一单利 只能被初始化一次
 * 可以使用其作为线程安全的懒汉模式
 */
public enum Configtype {
    API_HOSET,//网络请求域名
    APPLICATION_CONTEXT,//上下文
    CONFIG_READY,//配置是否完成
    ICON
}
