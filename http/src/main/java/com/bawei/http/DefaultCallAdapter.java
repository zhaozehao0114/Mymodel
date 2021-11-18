package com.bawei.http;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;

/**
 * @ProjectName: MVVMZG51905
 * @Package: com.zy.net.retrofit.calladapter
 * @ClassName: DefaultCallAdapter
 * @Description:
 * @Author: 张跃 企鹅：444511958
 * @CreateDate: 2021/11/16 11:34
 * @UpdateUser: 张跃
 * @UpdateDate: 2021/11/16 11:34
 * @UpdateRemark:
 * @Version: 1.0
 */
public class DefaultCallAdapter<R> implements CallAdapter<R,Object> {
    Type type=null;
    public DefaultCallAdapter(Type _t){
        type=_t;
    }
    @Override
    public Type responseType() {
        return type;
    }

    @Override
    public Object adapt(Call<R> call) {
        return call;
    }
}
