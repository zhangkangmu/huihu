package com.huihu.module_mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * create by ouyangjianfeng on 2019/4/8
 * description:
 */
public class AboutUsFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_about, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mTvTitle.setText(R.string.module_mine_about_us);
    }

    @OnClick(R2.id.iv_back)
    public void onBack() {
        pop();
    }
}
