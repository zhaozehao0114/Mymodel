package com.bawei.mvvmcore.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initEnv();
    }

    protected abstract void initEnv();

    protected void jumpToActivity(Class<?> target){this.startActivity(new Intent(this,target));}
    protected void jumpToActivity(Class<?> target,Bundle bundle){
        Intent intent = new Intent(this, target);
        intent.putExtra("params",bundle);
        this.startActivity(intent);
    }
    protected void showMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
