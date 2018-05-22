package com.example.yb.latte_core.net.callback;

import android.os.Handler;

import com.example.yb.latte_core.ui.LatteLoader;
import com.example.yb.latte_core.ui.LoaderStyle;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by yangbin on 2018/5/8.
 */
public class RequestCallback implements Callback<String> {
    private final ISuccess SUCCESS;
    private final IRequest REQUEST;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler HANDLER  = new Handler() ;

    //构造方法
    public RequestCallback(ISuccess success, IRequest request, IError error, IFailure failure,LoaderStyle loaderStyle) {
        this.SUCCESS = success;
        this.REQUEST = request;
        this.ERROR = error;
        this.FAILURE = failure;
        this.LOADER_STYLE = loaderStyle;
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            //执行了call 方法
            if(call.isExecuted()){
                if(SUCCESS != null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if (ERROR != null){
                ERROR.onError(response.code(),response.message());
            }
        }

        if(LOADER_STYLE != null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },3000);
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if(FAILURE != null){
            FAILURE.onFailure();
        }

        //请求结束
        if(REQUEST != null){
            REQUEST.onRequestEnd();
        }
    }
}
