package com.bawei.http;

import android.util.Log;

import androidx.lifecycle.LiveData;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * @ProjectName: MVVMZG51905
 * @Package: com.zy.net.retrofit.calladapter
 * @ClassName: LiveDataCallAdapterFactory
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/11/16 10:17
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/11/16 10:17
 * @UpdateRemark:
 * @Version: 1.0
 */
public class LiveDataCallAdapterFactory extends CallAdapter.Factory  {

    public static LiveDataCallAdapterFactory create(){
        return new LiveDataCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Class<?> rawType= CallAdapter.Factory.getRawType(returnType);
        if (rawType!= LiveData.class){
            Log.i("123", "this retrofit request is not call type LiveData");
            return null;
        }

        if (!(returnType instanceof ParameterizedType)){
            throw new IllegalStateException("returnType must be ParameterizedType");
        }

        if (rawType!=LiveData.class&&rawType!=Call.class){
            throw new IllegalArgumentException("returnType must be LiveData or Call");
        }

        Type t = CallAdapter.Factory.getParameterUpperBound(0, (ParameterizedType) returnType);

        if (rawType== Call.class){
            return new DefaultCallAdapter<>(t);
        }else if (rawType==LiveData.class){
            return new LiveDataCallAdapter<>(t);
        }
        return new DefaultCallAdapter<>(t);
    }
}
