package com.example.yb.latte_core.net;

import android.content.Context;

import com.example.yb.latte_core.net.callback.IError;
import com.example.yb.latte_core.net.callback.IFailure;
import com.example.yb.latte_core.net.callback.IRequest;
import com.example.yb.latte_core.net.callback.ISuccess;
import com.example.yb.latte_core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by yangbin on 2018/5/7.
 * 建造者模式的builder 主要是为RestClient赋值
 */
public class RestClientBuilder {

    private String mUrl = null;
    private static final Map<String, Object> mParams = RestCreator.getParams();
    private ISuccess mISuccess;
    private IRequest mIRequest;
    private IError mIError;
    private IFailure mIFailure;
    private RequestBody mBody;
    private Context mContext;
    private LoaderStyle mLoaderStyle;
    private File mFile;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    //只允许同包的类通过new方法创建
    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
       mParams.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
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

    public final RestClientBuilder loader(Context context,LoaderStyle loaderStyle){
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }
    public final RestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.mName = name;
        return this;
    }

    public final RestClientBuilder dir(String dir) {
        this.mDownloadDir = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.mExtension = extension;
        return this;
    }

    public final RestClient build(){
        return new RestClient(mUrl,mParams,mDownloadDir, mExtension, mName,mISuccess,
                mIRequest,mIError,mIFailure,mBody,mFile,mContext,mLoaderStyle);
    }

}
