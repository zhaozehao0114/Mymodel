package com.bawei.http;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * @ProjectName: MVVMZG51905
 * @Package: com.zy.net.retrofit.convertfactory
 * @ClassName: CustomRequestBodyConverter
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/11/16 11:21
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/11/16 11:21
 * @UpdateRemark:
 * @Version: 1.0
 */
public class CustomRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE=MediaType.get("application/json;charset=UTF-8");
    @Override
    public RequestBody convert(T value) throws IOException {
        String jsonContent = new Gson().toJson(value);

        return RequestBody.create(MEDIA_TYPE,jsonContent);
    }
}
