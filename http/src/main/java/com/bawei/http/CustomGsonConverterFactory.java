package com.bawei.http;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @ProjectName: MVVMZG51905
 * @Package: com.zy.net
 * @ClassName: CustomGsonConverterFactory
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/11/16 11:18
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/11/16 11:18
 * @UpdateRemark:
 * @Version: 1.0
 */
public class CustomGsonConverterFactory extends Converter.Factory {

    public static CustomGsonConverterFactory create(){
        return new CustomGsonConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new CustomResponseBodyConverter<>(type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new CustomRequestBodyConverter<>();
    }
}
