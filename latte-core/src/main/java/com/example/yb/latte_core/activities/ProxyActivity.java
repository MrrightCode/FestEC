package com.example.yb.latte_core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.example.yb.latte_core.R;
import com.example.yb.latte_core.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Create by :yb on 2018/4/24
 * Description: 这是一个容器Activity
 */
public abstract class ProxyActivity extends SupportActivity {

    //在这里传入了一个跟Delegate(实际就是Fragment)
    public abstract LatteDelegate setRootDelegate();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //调用初始化容器
        initContainer(savedInstanceState);
    }

    /**
     * @Create by  yb on 2018/4/25
     * @params: 创建根容器
     */
    private void initContainer(@Nullable Bundle savedInstanceState) {
        /**
        *@Create by  yb on 2018/4/25
        *@params:[savedInstanceState]
        *自己新建了一个FramLaout 容器container 然后为他设置一个id 再将这个容器设置给 Activity作为视图
        */
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.dalegate_container);
        setContentView(container);
        if(savedInstanceState == null){
            //这个方法会是我们引入的fragmentation.SupportActivity的方法
            loadRootFragment(R.id.dalegate_container,setRootDelegate());
        }
    }

    /**
    *@Create by  yb on 2018/4/25
    *@params:单Activity模式 所以在Activity销毁的时候就意味着应用退出了
    *  所以在这里可以进行辣鸡的回收  具体为什么可以这么做会后需要看java的回收机制
     *  TODO  JVM学习
    */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
