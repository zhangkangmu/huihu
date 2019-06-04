package com.huihu.commonlib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.commonlib.utils.LogUtil;

import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * @author ouyangjianfeng
 * @time 2019/2/14  14:59
 * @desc ${Fragment基类}
 */
public class BaseFragment extends SwipeBackFragment {

    private static final String TAG = "BaseFragment";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.i(TAG,getClass().getSimpleName() + "--onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(TAG,getClass().getSimpleName() + "--onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.i(TAG,getClass().getSimpleName() + "--onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtil.i(TAG,getClass().getSimpleName() + "--onViewCreated");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.i(TAG,getClass().getSimpleName() + "--onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.i(TAG,getClass().getSimpleName() + "--onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i(TAG,getClass().getSimpleName() + "--onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.i(TAG,getClass().getSimpleName() + "--onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.i(TAG,getClass().getSimpleName() + "--onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.i(TAG,getClass().getSimpleName() + "--onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG,getClass().getSimpleName() + "--onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.i(TAG,getClass().getSimpleName() + "--onDetach");
    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }
}
