package com.bawei.http;

import android.provider.SyncStateContract;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {
    private static  RetrofitFactory retrofitFactory;
    private Retrofit retrofit=null;

    public RetrofitFactory() {
        retrofit=createRetroit();
    }

    private Retrofit createRetroit() {
        return new Retrofit.Builder().baseUrl("http://82.156.178.182:8082/")
                .addConverterFactory(CustomGsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .client(createOkHttpClient())
                .build();
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient build = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2,TimeUnit.MINUTES)
                .writeTimeout(2,TimeUnit.MINUTES)
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(createTokenInterceptor())
                .addInterceptor(createHeadsInterceptor())
                .build();
        return build;
    }

    private Interceptor createHeadsInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return chain.proceed(chain.request());
            }
        };
    }
    public static class Holder{
        private static RetrofitFactory INSTANCE=new RetrofitFactory();
    }

    public static RetrofitFactory getInstance(){
        return Holder.INSTANCE;
    }
    private Interceptor createTokenInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response proceed = chain.proceed(request);
                if (proceed.code()==401){
                    String token=doTokenTask();

                    Request authorization = request.newBuilder().addHeader("Authorization", "bearer " + token).build();
                    return chain.proceed(authorization);
                }
                return proceed;

            }
        };
    }

    private String doTokenTask() {
        TokenApi o = create(TokenApi.class);
        Call<TokenRespEntity> token = o.getToken("password", Constanct.getAuthCode(), "");
        try {
            retrofit2.Response<TokenRespEntity> execute = token.execute();
            if (execute!=null&&execute.body()!=null){
                return execute.body().getAccess_token();
            }
        } catch (IOException e) {
            LogUtils.d(e.getMessage());
        }
        return "";

    }
    public  <T> T create(Class<?> service){
        return (T)retrofit.create(service);
    }
}
