package com.example.yb.festec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.yb.latte_core.net.RestClient;
import com.example.yb.latte_core.net.callback.ISuccess;
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
        testRestClient();
    }

   public void  testRestClient(){
        RestClient.builder()
                .url("http://www.baidu.com")
                .loader(getContext())
                .success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                //Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();
            }
        }).build().get();
    }

}
