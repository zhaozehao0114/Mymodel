package com.bawei.usercenter.model;

import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bawei.http.BaseRespEntity;
import com.bawei.http.RetrofitFactory;
import com.bawei.mvvmcore.model.IModel;
import com.bawei.usercenter.UserCenterApi;
import com.bawei.usercenter.entity.UserEntity;

public class UserCenterModel implements IModel {

    public LiveData<BaseRespEntity<UserEntity>> login(String phoneNumber, String pwd){

       UserCenterApi o = RetrofitFactory.getInstance().create(UserCenterApi.class);

        return o.login(new UserEntity(1,phoneNumber,pwd,"00",""));
    }
    public LiveData<BaseRespEntity<UserEntity>> reg(String phoneNumber, String pwd){

        UserCenterApi o = RetrofitFactory.getInstance().create(UserCenterApi.class);

        return o.reg(new UserEntity(1,phoneNumber,pwd,"00",""));
    }
}
