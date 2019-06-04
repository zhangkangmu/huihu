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
import butterknife.Unbinder;

/**
 * create by ouyangjianfeng on 2019/3/18
 * description:
 */
public class AgreementFragment extends BaseFragment {

    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_agreement, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mTvTitle.setTextColor(getResources().getColor(R.color.module_mine_black));
        mTvTitle.setText(getResources().getString(R.string.module_mine_title_agreement));

        mIvBack.setImageResource(R.drawable.module_mine_icon_back_black);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
