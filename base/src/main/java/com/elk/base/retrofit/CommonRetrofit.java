package com.elk.base.retrofit;

import android.content.Context;

import com.elk.base.rxError.handler.listener.ResponseErroListener;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by maimingliang on 2017/5/5.
 */

public class CommonRetrofit {
    private volatile static CommonRetrofit Instance;
    private static final String BASE_URL = "http://192.168.0.232:8081/elk-tourist-develop/";

    private Retrofit retrofit;
    private String bu = BASE_URL;

    public static CommonRetrofit getInstance(){
        if (Instance == null)
            synchronized (CommonRetrofit.class){
                if (Instance == null)
                    Instance = new CommonRetrofit();
            }
        return Instance;
    }


    private GlobeHttpHandler mGlobeHttpHandler = new GlobeHttpHandler() {
        @Override
        public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {
            String contentType = response.headers().get("Content-Type");
            Timber.tag("CommonRetrofit").d(contentType);
            if (contentType.startsWith("application/json")) {
//                if (StringUtils.isEmpty(responseBodyString)) {
//                    throw new SystemException();
//                }
//                JSONObject jsonObject;
//                try {
//                    jsonObject = new JSONObject(responseBodyString);
//                } catch (JSONException e) {
//                    throw new SystemException();
//                }
//                String code = jsonObject.optString("code", "");
//                if (StringUtils.isEmpty(code)) {
//                    throw new SystemException();
//                }
//                if (OutputCode.EX.getCode().equals(code)) {
//                    throw new SystemException();
//                }
//                if (OutputCode.PM.getCode().equals(code)) {
//                    throw new ParameterException();
//                }
//                if (OutputCode.TX.getCode().equals(code)) {
//                    throw new UnauthorizedException();
//                }
//                String message = jsonObject.optString("message", "");
//                if (!OutputCode.OK.getCode().equals(code)) {
//                    if (StringUtils.isEmpty(message)) {
//                        throw new SystemException();
//                    } else {
//                        throw new MessageException(code, message);
//                    }
//                }

            } else {
//                throw new SystemException();
            }
            return response;
        }

        @Override
        public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
            return request;
        }
    };


    public ResponseErroListener getResponseErroListener() {
        return mResponseErroListener;
    }

    private ResponseErroListener mResponseErroListener = new ResponseErroListener() {
        @Override
        public void handleResponseError(Context context, Exception e) {
            Timber.e(e.getMessage());
//            if (e instanceof UnauthorizedException) {
//                UserUtil.clear();
//                ARouter.getInstance().build("/touristUser/login").navigation();
//            } else if (e instanceof SystemException) {
//                UiUtils.makeText(OutputCode.EX.getMessage());
//            } else if (e instanceof ParameterException) {
//                UiUtils.makeText(OutputCode.PM.getMessage());
//            } else if (e instanceof MessageException) {
//                UiUtils.makeText(e.getMessage());
//            }else if(e instanceof HttpException){
//                UiUtils.makeText("网络不给力！请稍后重试");
//            } else if (e instanceof ConnectException) {
//                UiUtils.makeText("网络出错啦！请检查后重试");
//                //                            UiUtils.makeText(e.getMessage());
//            }else if (e instanceof RxCacheException) {
//                UiUtils.makeText("无法连接到服务器获取数据");
//                //                            UiUtils.makeText(e.getMessage());
//            }else {
//                UiUtils.makeText(OutputCode.EX.getMessage());
//            }
        }
    };


    private CommonRetrofit(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(new RequestIntercept(mGlobeHttpHandler));
        builder.addInterceptor(new RequestIntercept(mGlobeHttpHandler));
        retrofit=new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(bu)
                .build();
    }
    private CommonRetrofit(String baseUrl){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(5, TimeUnit.SECONDS);
        builder.addNetworkInterceptor(new RequestIntercept(mGlobeHttpHandler));
        if (baseUrl !=null){
            bu = baseUrl;
        }

        retrofit=new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(bu)
                .build();
    }

    public Retrofit getRetrofit(){
        return  retrofit;
    }

}
