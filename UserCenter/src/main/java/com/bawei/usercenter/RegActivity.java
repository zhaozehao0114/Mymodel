package com.bawei.usercenter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.bawei.http.BaseRespEntity;
import com.bawei.mvvmcore.view.BaseMVVMActivity;
import com.bawei.usercenter.databinding.ActivityLoginBinding;
import com.bawei.usercenter.databinding.ActivityRegBinding;
import com.bawei.usercenter.entity.UserEntity;
import com.bawei.usercenter.viewmodel.UserCenterViewModel;

import java.util.HashMap;

public class RegActivity extends BaseMVVMActivity<UserCenterViewModel, ActivityRegBinding> {
    private android.widget.TextView regLog;
    private android.widget.Button reg;

    @Override
    protected void prepareSetVars(HashMap<Integer, Object> mMap) {
        mMap.put(BR.user_reg,mViewModel.source_reg);
        mMap.put(BR.owner_reg,this);
    }

    @Override
    protected UserCenterViewModel createViewModel() {
        return new UserCenterViewModel(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reg;
    }

    @Override
    protected void initEnv() {
        initView();
        mViewModel.source_reg.setValue(new UserEntity());
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mViewModel.source_reg.getValue().getUsername();
                String pwd = mViewModel.source_reg.getValue().getPwd();
                mViewModel.reg(username,pwd).observe(RegActivity.this, new Observer<BaseRespEntity<UserEntity>>() {
                    @Override
                    public void onChanged(BaseRespEntity<UserEntity> userEntityBaseRespEntity) {
                        if (userEntityBaseRespEntity.getCode()==200){
                            showMsg("注册成功");
                        }else {

                            showMsg("注册失败");
                        }
                    }
                });
            }
        });
        regLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpToActivity(LoginActivity.class);
            }
        });
    }

    private void initView() {
        regLog = (TextView) findViewById(R.id.reg_log);
        reg = (Button) findViewById(R.id.reg);
    }
}
