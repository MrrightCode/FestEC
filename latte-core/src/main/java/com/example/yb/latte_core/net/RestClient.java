package com.example.yb.latte_core.net;

import android.content.Context;

import com.example.yb.latte_core.net.callback.IError;
import com.example.yb.latte_core.net.callback.IFailure;
import com.example.yb.latte_core.net.callback.IRequest;
import com.example.yb.latte_core.net.callback.ISuccess;
import com.example.yb.latte_core.net.callback.RequestCallback;
import com.example.yb.latte_core.ui.LatteLoader;
import com.example.yb.latte_core.ui.LoaderStyle;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Create by :yb on 2018/4/26
 * Description: 这个是一个基于Retrofit 的网络框架
 * 采用的是建造者模式 进行请求的具体实现类
 */
public class RestClient {

    private final String URL;
    private static final Map<String, Object> PARMAS = RestCreator.getParams();
    private final ISuccess SUCCESS;
    private final IRequest REQUEST;
    private final IError ERROR;
    private final IFailure FAILURE;
    //okhttp3里面的东西
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url,
                      Map<String, Object> params,
                      ISuccess success,
                      IRequest request,
                      IError error,
                      IFailure failure,
                      RequestBody body,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.URL = url;
        PARMAS.putAll(params);
        this.SUCCESS = success;
        this.REQUEST = request;
        this.ERROR = error;
        this.FAILURE = failure;
        this.BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod httpMethod) {
        final RestService service = RestCreator.getResetService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if(LOADER_STYLE != null){
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }

        switch (httpMethod) {
            case GET:
                call = service.get(URL, PARMAS);
                break;
            case PUT:
                call = service.put(URL, PARMAS);
                break;
            case DELETE:
                call = service.delete(URL, PARMAS);
                break;
            case POST:
                call = service.post(URL, PARMAS);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallBack());
        }
    }

    public RequestCallback getRequestCallBack() {
        return new RequestCallback(SUCCESS, REQUEST, ERROR, FAILURE,LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}

