package com.example.yb.latte_core.net;

import android.content.Context;

import com.example.yb.latte_core.net.callback.IError;
import com.example.yb.latte_core.net.callback.IFailure;
import com.example.yb.latte_core.net.callback.IRequest;
import com.example.yb.latte_core.net.callback.ISuccess;
import com.example.yb.latte_core.net.callback.RequestCallback;
import com.example.yb.latte_core.net.download.DownLoadHandler;
import com.example.yb.latte_core.ui.LatteLoader;
import com.example.yb.latte_core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
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
    private final File FILE;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    //okhttp3里面的东西
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    public RestClient(String url,
                      Map<String, Object> params,
                      String downloadDir,
                      String extension,
                      String name,
                      ISuccess success,
                      IRequest request,
                      IError error,
                      IFailure failure,
                      RequestBody body,
                      File file,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.URL = url;
        PARMAS.putAll(params);
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.REQUEST = request;
        this.ERROR = error;
        this.FAILURE = failure;
        this.BODY = body;
        this.FILE = file;
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
            case POST_RAW:
                call = service.postRaw(URL,BODY);
                break;
            case PUT_RAW:
                call = service.putRaw(URL,BODY);
            case UPLOAD:
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL,body);
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
        if(BODY ==  null){
            request(HttpMethod.POST);
        }else {
            if(!PARMAS.isEmpty()){
                throw new RuntimeException("PARMAS  must be null");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void put() {
        if(BODY ==  null){
            request(HttpMethod.PUT);
        }else {
            if(!PARMAS.isEmpty()){
                throw new RuntimeException("PARMAS  must be null");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void download(){
        new DownLoadHandler(URL,REQUEST,DOWNLOAD_DIR,EXTENSION,NAME,SUCCESS,FAILURE,ERROR).handleDownload();
    }
}


