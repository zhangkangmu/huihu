package com.huihu.commonlib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.huihu.commonlib.utils.LogUtil;

/**
 * @author ouyangjianfeng
 * @time 2019/2/14  15:37
 * @desc ${基类activity}
 */
public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(TAG,getClass().getSimpleName() + "--onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.i(TAG,getClass().getSimpleName() + "--onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i(TAG,getClass().getSimpleName() + "--onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.i(TAG,getClass().getSimpleName() + "--onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.i(TAG,getClass().getSimpleName() + "--onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.i(TAG,getClass().getSimpleName() + "--onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG,getClass().getSimpleName() + "--onDestroy");
    }
}
