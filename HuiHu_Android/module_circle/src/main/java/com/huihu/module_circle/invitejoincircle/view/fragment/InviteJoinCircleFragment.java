package com.huihu.module_circle.invitejoincircle.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.invitejoincircle.invitejoincircleinterface.IInviteJoinCirclePresenter;
import com.huihu.module_circle.invitejoincircle.invitejoincircleinterface.IInviteJoinCircleView;
import com.huihu.module_circle.invitejoincircle.presenter.ImpInviteJoinCirclePresenter;
import com.huihu.uilib.statelayout.StateLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class InviteJoinCircleFragment extends BaseFragment implements IInviteJoinCircleView {
    private final IInviteJoinCirclePresenter iInviteJoinCirclePresenter = new ImpInviteJoinCirclePresenter(this);
    @BindView(R2.id.rv_recyclerview)
    RecyclerView mRvRecyclerview;
    private Unbinder unbinder;
    @BindView(R2.id.tv_complete)
    TextView mTvComplete;
    private boolean mIsComplete;

    public static InviteJoinCircleFragment newInstance(boolean isComplete) {
        InviteJoinCircleFragment fragment = new InviteJoinCircleFragment();
        Bundle args = new Bundle();
        args.putBoolean("isComplete", isComplete);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_circle_fragment_invite, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        getArgs();
        initViewState();
        initStateLayout();
    }

    private void initStateLayout() {
        StateLayout stateLayout = new StateLayout.Builder(mRvRecyclerview).create();
        stateLayout.showEmptyData();
    }

    private void getArgs() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            mIsComplete = bundle.getBoolean("isComplete");
        }
    }

    private void initViewState() {
        mTvComplete.setVisibility(mIsComplete ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R2.id.tv_complete)
    public void onComplete() {

    }

    @OnClick(R2.id.cl_back)
    public void onBack() {
        pop();
    }
}
