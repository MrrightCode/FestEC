package com.example.yb.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.yb.latte_core.Net.RestClient;
import com.example.yb.latte_core.Net.callback.ISuccess;
import com.example.yb.latte_core.delegates.LatteDelegate;

/**
 * Create by :yb on 2018/4/25
 * Description:
 */
public class ExampleDelegate extends LatteDelegate{

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    //对控件的操作
    @Override
    public void onBinView(@Nullable Bundle savedInstanceState, View rootView) {


    }


}
