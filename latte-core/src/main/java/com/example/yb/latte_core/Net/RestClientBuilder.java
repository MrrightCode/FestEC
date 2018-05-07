package com.example.yb.latte_core.Net;

import com.example.yb.latte_core.Net.callback.IError;
import com.example.yb.latte_core.Net.callback.IFailure;
import com.example.yb.latte_core.Net.callback.IRequest;
import com.example.yb.latte_core.Net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by yangbin on 2018/5/7.
 */
public class RestClientBuilder {

    private String mUrl;
    private Map<String, Object> mParams;
    private ISuccess mISuccess;
    private IRequest mIRequest;
    private IError mIError;
    private IFailure mIFailure;
    private RequestBody mBody;

    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(Map<String, Object> params) {
        this.mParams = params;
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        if (mParams == null) {
            mParams = new WeakHashMap<>();
        }
        mParams.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw){
        this.mBody  =  RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess success) {
        this.mISuccess = success;
        return this;
    }

    public final RestClientBuilder failure(IFailure failure) {
        this.mIFailure = failure;
        return this;
    }

    public final RestClientBuilder error(IError error) {
        this.mIError = error;
        return this;
    }

    public final RestClientBuilder onRequest(IRequest request) {
        this.mIRequest = request;
        return this;
    }

    private Map<String,Object> cheackParams(){
        if(mParams == null){
            return new WeakHashMap<>();
        }
        return mParams;
    }

    public final RestClient build(){
        return new RestClient(mUrl,mParams,mISuccess,mIRequest,mIError,mIFailure,mBody);
    }

}
