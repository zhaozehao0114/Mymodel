package com.bawei.usercenter;



import androidx.lifecycle.LiveData;

import com.bawei.http.BaseRespEntity;
import com.bawei.usercenter.entity.UserEntity;

import retrofit2.http.Body;
import retrofit2.http.POST;


public interface UserCenterApi {
    @POST("api/User/login")
    LiveData<BaseRespEntity<UserEntity>> login(@Body UserEntity entity);
    @POST("api/User/register")
    LiveData<BaseRespEntity<UserEntity>> reg(@Body UserEntity entity);
}
