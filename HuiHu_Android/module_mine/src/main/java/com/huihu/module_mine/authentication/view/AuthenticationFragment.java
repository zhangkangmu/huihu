package com.huihu.module_mine.authentication.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.authentication.authenticationinterface.IAuthenticationPresenter;
import com.huihu.module_mine.authentication.authenticationinterface.IAuthenticationView;
import com.huihu.module_mine.authentication.entity.Authentication;
import com.huihu.module_mine.authentication.presenter.ImpAuthenticationPresenter;
import com.huihu.module_mine.authentication.view.adapter.AuthentictionAdapter;
import com.huihu.module_mine.followsandfollowed.view.adapter.FollowsAndFansAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 *认证的界面
 * @author caicancan
 * */
public class AuthenticationFragment extends BaseFragment implements IAuthenticationView{
    @BindView( R2.id.tv_base)
    TextView tv_base;
    @BindView(R2.id.mRecycleView)
    RecyclerView mRecycleView;

    private final IAuthenticationPresenter iAuthenticationPresenter = new ImpAuthenticationPresenter(this);
    private Unbinder unbinder;
    private long fid;
    private AuthentictionAdapter adapter;

    public static AuthenticationFragment newInstance(long fid) {
        Bundle bundle = new Bundle();
        AuthenticationFragment fragment = new AuthenticationFragment();
        bundle.putLong("fid",fid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_authentication, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            fid = bundle.getLong("fid");
        }
        TextViewUtils.setTextFakeBold(tv_base);
        tv_base.setText(R.string.authentiction);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        initData();
        iAuthenticationPresenter.v2pGetUserAuthList(fid);
    }
    @OnClick(R2.id.iv_back)
    public void OnClick(View view){
        pop();
    }
    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        adapter = new AuthentictionAdapter();
        adapter.setContext(getActivity());
       mRecycleView.setAdapter(adapter);
    }

    @Override
    public void p2vReturnUserAuthList(List<Authentication.UserAuthShowModelListBean> mList) {
        adapter.setmList(mList);
    }
}
