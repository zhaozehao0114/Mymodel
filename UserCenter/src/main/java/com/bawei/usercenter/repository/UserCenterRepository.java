package com.bawei.usercenter.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bawei.http.BaseRespEntity;
import com.bawei.mvvmcore.repository.BaseRepository;
import com.bawei.usercenter.entity.UserEntity;
import com.bawei.usercenter.model.UserCenterModel;

public class UserCenterRepository extends BaseRepository<UserCenterModel> {
    @Override
    protected UserCenterModel createModel() {
        return new UserCenterModel();

    }
    public LiveData<BaseRespEntity<UserEntity>> login(String phoneNumber, String pwd){
       return mModel.login(phoneNumber,pwd);
    }
    public LiveData<BaseRespEntity<UserEntity>> reg(String phoneNumber, String pwd){
        return mModel.reg(phoneNumber,pwd);
    }
}
