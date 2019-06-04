package com.huihu.module_mine.bindsocial.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.bindsocial.bindsocialinterface.IBindSocialPresenter;
import com.huihu.module_mine.bindsocial.bindsocialinterface.IBindSocialView;
import com.huihu.module_mine.bindsocial.presenter.ImpBindSocialPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BindSocialFragment extends BaseFragment implements IBindSocialView {
    private final IBindSocialPresenter iBindSocialPresenter = new ImpBindSocialPresenter(this);
    private Unbinder unbinder;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;

    public static BindSocialFragment newInstance() {
        Bundle args = new Bundle();
        BindSocialFragment fragment = new BindSocialFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_social_bind, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitle();
    }

    private void initTitle() {
        mTvTitle.setText("绑定第三方");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R2.id.iv_back)
    public void onBack() {
        pop();
    }
}
