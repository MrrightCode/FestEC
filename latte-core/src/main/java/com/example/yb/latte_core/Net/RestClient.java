package com.example.yb.latte_core.Net;

import com.example.yb.latte_core.Net.callback.IError;
import com.example.yb.latte_core.Net.callback.IFailure;
import com.example.yb.latte_core.Net.callback.IRequest;
import com.example.yb.latte_core.Net.callback.ISuccess;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * Create by :yb on 2018/4/26
 * Description: 这个是一个基于Retrofit 的网络框架
 * 采用的是建造者模式 进行请求的具体实现类
 */
public class RestClient {

    private final String URL;
    private final Map<String,Object> PARMAS;
    private final ISuccess SUCCESS;
    private final IRequest REQUEST;
    private final IError ERROR;
    private final IFailure FAILURE;
    private final RequestBody BODY;

    public RestClient(String URL,
                      Map<String, Object> PARMAS,
                      ISuccess SUCCESS,
                      IRequest REQUEST,
                      IError ERROR,
                      IFailure FAILURE,
                      RequestBody BODY) {
        this.URL = URL;
        this.PARMAS = PARMAS;
        this.SUCCESS = SUCCESS;
        this.REQUEST = REQUEST;
        this.ERROR = ERROR;
        this.FAILURE = FAILURE;
        this.BODY = BODY;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }
}
