package com.example.yb.latte_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Create by :yb on 2018/4/23
 * Description:对配置文件进行存贮和获取的
 */
public class Configurator {

    /**
    *@Create by  yb on 2018/4/24
    *@params: static final类 命名规范 全大写加下划线分割
    */
    private static final HashMap<String,Object> LATTE_CONFIGS = new HashMap<>();

    /**
    *@Create by  yb on 2018/4/24
    *@params: iconify 图标库 初始化  并且封装在配置类中
    *
    */
    private static final ArrayList<IconFontDescriptor> ICON_FONT_DESCRIPTORS = new ArrayList<>();

    /**
    *@Create by  yb on 2018/4/24
    *@params: 私有化构造函数 开始上设置配置时 位置完成度为false
    *
    */
    private Configurator(){
        LATTE_CONFIGS.put(Configtype.CONFIG_READY.name(),false);
    }


    /**
    *@Create by  yb on 2018/4/24
    *@params: 以下的方法构成了单例模式 向外界提供Configurator实例
     * 静态内部类的类加载模式实现的单利模式
    */
    public static Configurator getInstance(){
        return configuratorHolder.CONFIGURATOR;
    }

    private static class configuratorHolder{
        public static final Configurator CONFIGURATOR = new Configurator();
    }


    /**
    *@Create by  yb on 2018/4/24
    *@params: 配置完成的时候置true 的方法
    */
    public final void  configure(){
        initIcons();
        LATTE_CONFIGS.put(Configtype.CONFIG_READY.name(),true);
    }

    /**
    *@Create by  yb on 2018/4/24
    *@params: 获取配置集合的方法
    *
    */
    public final HashMap<String,Object>getLatteConfigs(){
        return LATTE_CONFIGS;
    }


    /**
    *@Create by  yb on 2018/4/24
    *@params:设置API的方法 返回值是类本身便于点操作
    *
    */
    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(Configtype.API_HOSET.name(),host);
        return this;
    }

    /**
    *@Create by  yb on 2018/4/24
    *@params:检查配置是否完成 在获取配置的时候进行检测
    *  原则：对类变量或者是方法变量 尽量是其是不可变的。是的以后的程序最大限度的避免被错误的修改
    */
    private  void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(Configtype.CONFIG_READY.name());
        if(!isReady){
            throw new RuntimeException("Configuration is not ready  call configure!");
        }
    }

    /**
    *@Create by  yb on 2018/4/24
    *@params 获取对应的配置 因为存贮的类型是object 所以使用泛型的来取代返回值类型
    *
    */
    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<Configtype> configtypeEnum){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(configtypeEnum.name());
    }


    /**
    *@Create by  yb on 2018/4/24
    *@params:根据属性初始化
    *
    */
    private void initIcons(){
        if(ICON_FONT_DESCRIPTORS.size() > 0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICON_FONT_DESCRIPTORS.get(0));
            for(int i = 1; i < ICON_FONT_DESCRIPTORS.size();i++){
                initializer.with(ICON_FONT_DESCRIPTORS.get(i));
            }
        }
    }

    /**
    *@Create by  yb on 2018/4/24
    *@params: 将用户初始化的属性添加到list中
    *
    */
    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICON_FONT_DESCRIPTORS.add(descriptor);
        return this;
    }
}
