package com.example.yb.latte_core.net;


import com.example.yb.latte_core.app.Configtype;
import com.example.yb.latte_core.app.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by yangbin on 2018/5/7.
 * 这个类的主要目的是为了返回
 */
public class RestCreator {

    private RestCreator(){}

    public static RestService getResetService(){
        return ResetServiceHolder.REST_SERVICE;
    }

    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }

    private static final class ParamsHolder{
        private static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    }

    private static final class RetrofitHolder{
        private static final String BASE_URL = (String) Latte.getConfigurations().get(Configtype.API_HOSET.name());
        private static final Retrofit RETROFIT = new Retrofit.Builder()
                .client(OkhttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    private static final class OkhttpHolder{
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class ResetServiceHolder{
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT.create(RestService.class);
    }
}
