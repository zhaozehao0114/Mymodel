package com.bawei.http;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @ProjectName: MVVMZG51905
 * @Package: com.zy.net.retrofit.convertfactory
 * @ClassName: CustomResponseBodyConverter
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/11/16 11:26
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/11/16 11:26
 * @UpdateRemark:
 * @Version: 1.0
 */
public class CustomResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private Type type;
    public CustomResponseBodyConverter(Type _type){
        type=_type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        Gson gson=new Gson();
        String respContent = value.string();
        if (respContent.contains("access_")){
            return (T) gson.fromJson(respContent, TokenRespEntity.class);
        }

        BaseRespEntity baseRespEntity = gson.fromJson(respContent, BaseRespEntity.class);
        if (baseRespEntity.getCode()!=200){
            return (T) baseRespEntity;
        }else{
            return gson.fromJson(respContent,type);
        }
    }
}
