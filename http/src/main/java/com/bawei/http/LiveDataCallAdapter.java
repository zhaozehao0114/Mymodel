package com.bawei.http;

import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;



import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;


public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<BaseRespEntity<R>>> {

    private Type type;
    public LiveDataCallAdapter(Type _t){
        this.type=_t;
    }

    @Override
    public Type responseType() {
        return this.type;
    }

    /**
     * adapt - OkHttp  因为 Retrofit 网络请求能力？ CallFactory
     * @param
     * @return
     * @author zhangyue
     * @time 2021/11/16 10:58
     */
    @Override
    public LiveData<BaseRespEntity<R>> adapt(Call<R> call) {
        final MutableLiveData<BaseRespEntity<R>> liveData=new MutableLiveData<>();
        call.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
                if (Looper.getMainLooper().getThread()==Thread.currentThread()){
                    liveData.setValue((BaseRespEntity<R>) response.body());
                }
                else{
                    liveData.postValue((BaseRespEntity<R>) response.body());
                }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                BaseRespEntity baseRespEntity=new BaseRespEntity();
                baseRespEntity.setCode(-1);
                baseRespEntity.setMsg(t.getMessage());
                if (Looper.getMainLooper().getThread()==Thread.currentThread()){
                    liveData.setValue(baseRespEntity);
                }
                else{
                    liveData.postValue(baseRespEntity);
                }
            }
        });
        return liveData;
    }
}
