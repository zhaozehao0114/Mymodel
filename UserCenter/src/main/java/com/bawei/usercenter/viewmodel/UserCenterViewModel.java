package com.bawei.usercenter.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bawei.http.BaseRespEntity;
import com.bawei.mvvmcore.viewmodel.BaseViewModel;
import com.bawei.usercenter.entity.UserEntity;
import com.bawei.usercenter.repository.UserCenterRepository;

public class UserCenterViewModel extends BaseViewModel<UserCenterRepository> {
    public MutableLiveData<UserEntity> source=new MutableLiveData<>();
    public MutableLiveData<UserEntity> source_reg=new MutableLiveData<>();
    public UserCenterViewModel(LifecycleOwner _owner) {
        super(_owner);
    }

    @Override
    protected UserCenterRepository createRepository() {
        return new UserCenterRepository();
    }

    @Override
    protected void releaseRes() {

    }

    @Override
    protected void initRes() {

    }
    public LiveData<BaseRespEntity<UserEntity>> login(String phoneNumber, String pwd){
        return mRepository.login(phoneNumber,pwd);
    }
    public LiveData<BaseRespEntity<UserEntity>> reg(String phoneNumber, String pwd){
        return mRepository.reg(phoneNumber,pwd);
    }
}
