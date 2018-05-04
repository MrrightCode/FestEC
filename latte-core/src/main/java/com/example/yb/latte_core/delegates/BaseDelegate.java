package com.example.yb.latte_core.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Create by :yb on 2018/4/24
 * Description: 基础的Delegate 抽象类来声明 避免了造成new实例
 * 类主要实现的功能是给Fragment 添加布局
 */
public abstract class BaseDelegate extends SwipeBackFragment{

    private Unbinder mBind;

    //抽象方法 让Layout  传入自己的布局可能是ID 或者View
    public abstract Object setLayout();


    //强制把当前的全局数据，和RootView 传出去 可以进行操作
    public abstract void onBinView(@Nullable Bundle savedInstanceState ,View rootView );


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = null;
        //传入的是ID
        if(setLayout() instanceof Integer){
            rootView = inflater.inflate((Integer) setLayout(),container,false);
        }else if(setLayout() instanceof View){
            rootView = (View) setLayout();
        }

        //布局进行绑定
        if(rootView != null){
            mBind = ButterKnife.bind(this,rootView);
            onBinView(savedInstanceState,rootView);
        }

        return rootView;
    }

    //在销毁的时候解除绑定
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mBind != null){
            mBind.unbind();
        }
    }
}
