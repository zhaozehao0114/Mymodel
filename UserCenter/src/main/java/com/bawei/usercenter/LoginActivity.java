package com.bawei.usercenter;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.Observer;

import com.bawei.http.BaseRespEntity;
import com.bawei.mvvmcore.view.BaseMVVMActivity;
import com.bawei.mvvmcore.viewmodel.BaseViewModel;
import com.bawei.usercenter.databinding.ActivityLoginBinding;
import com.bawei.usercenter.entity.UserEntity;
import com.bawei.usercenter.viewmodel.UserCenterViewModel;

import java.util.HashMap;

public class LoginActivity extends BaseMVVMActivity<UserCenterViewModel, ActivityLoginBinding> {


    private android.widget.Button log;
    private android.widget.EditText et;
    private android.widget.TextView regLog;

    @Override
    protected void prepareSetVars(HashMap<Integer, Object> mMap) {
        mMap.put(BR.user,mViewModel.source);
        mMap.put(BR.owner,this);
    }

    @Override
    protected UserCenterViewModel createViewModel() {
        return new UserCenterViewModel(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEnv() {
        mViewModel.source.setValue(new UserEntity());
        initView();
        et.setCursorVisible(false);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogin();
            }
        });
        regLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToActivity(RegActivity.class);
            }
        });
    }
    public void onLogin(){
        String username = mViewModel.source.getValue().getUsername();
        String pwd = mViewModel.source.getValue().getPwd();
        Log.d("TAG", "onLogin: "+username+pwd);
        mViewModel.login(username,pwd).observe(this, new Observer<BaseRespEntity<UserEntity>>() {
            @Override
            public void onChanged(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
                int code = userEntityBaseRespEntity.getCode();
                showMsg(""+code);
                if (code==200){

                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                }else{
                    showMsg("失败");
                }
            }
        });
    }

    private void initView() {
        log = (Button) findViewById(R.id.log);
        et = (EditText) findViewById(R.id.et);
        regLog = (TextView) findViewById(R.id.reg_log);
    }
}
