package com.bawei.mvvmcore.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.bawei.mvvmcore.viewmodel.BaseViewModel;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseMVVMActivity<VM extends BaseViewModel,Binding extends ViewDataBinding> extends BaseActivity {
    protected VM mViewModel;
    protected Binding mBinding;
    private HashMap<Integer,Object> mMap=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mBinding= DataBindingUtil.setContentView(this,getLayoutId());
        mBinding.setLifecycleOwner(this);
        mViewModel=createViewModel();
        super.onCreate(savedInstanceState);
        prepareSetVars(mMap);
        setVars(mBinding,mMap);
    }

    protected  void setVars(Binding mBinding, HashMap<Integer, Object> mMap){
        if (mMap==null||mMap.size()==0){
            throw  new RuntimeException("please set variables");
        }
        for (Map.Entry<Integer,Object> entry:mMap.entrySet()) {
            mBinding.setVariable(entry.getKey(),entry.getValue());
        }
    }

    protected abstract void prepareSetVars(HashMap<Integer, Object> mMap);

    protected abstract VM createViewModel();

    protected abstract int getLayoutId();
}
