package com.bawei.mvvmcore.repository;

import com.bawei.mvvmcore.model.IModel;

public abstract class BaseRepository<M extends IModel> {
    protected M mModel;

    public BaseRepository() {
        mModel=createModel();
    }

    /**
     * 创建实例化model
     * @return
     */
    protected abstract M createModel() ;
}
