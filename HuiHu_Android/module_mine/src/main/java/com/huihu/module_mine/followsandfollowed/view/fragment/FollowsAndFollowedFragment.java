package com.huihu.module_mine.followsandfollowed.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.followsandfollowed.entity.FollowsBean;
import com.huihu.module_mine.followsandfollowed.followsandfollowedinterface.IFollowsAndFollowedPresenter;
import com.huihu.module_mine.followsandfollowed.followsandfollowedinterface.IFollowsAndFollowedView;
import com.huihu.module_mine.followsandfollowed.presenter.ImpFollowsAndFollowedPresenter;
import com.huihu.module_mine.followsandfollowed.view.adapter.FollowsAndFansAdapter;
import com.huihu.module_mine.followsandfollowed.view.adapterInterface.OnFollowsAndFansItemClick;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public class FollowsAndFollowedFragment extends BaseFragment implements IFollowsAndFollowedView, OnFollowsAndFansItemClick {
/**
 * @des
 * 我关注的人和我的粉丝界面
 * */

    @BindView(R2.id.tv_base)
    TextView tv_title;
    @BindView(R2.id.recycleView_follow)
    RecyclerView mRecycleView;
    Unbinder unbinder;
    private FollowsAndFansAdapter adapter;
    private final IFollowsAndFollowedPresenter iFollowsAndFollowedPresenter = new ImpFollowsAndFollowedPresenter(this);
    public static FollowsAndFollowedFragment newInstance(long userId,int type,String title) {
        FollowsAndFollowedFragment fragment = new FollowsAndFollowedFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("userId", userId);
        bundle.putInt("type",type);
        bundle.putString("title",title);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.module_mine_fragment_aboutfollows ,container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        initData();
        iFollowsAndFollowedPresenter.v2pGetFollowAndFansList(userId,type,0);
    }
    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        adapter = new FollowsAndFansAdapter();
        adapter.setContext(getActivity());
        adapter.setOnFollowsAndFansItemClickListener(this);
        mRecycleView.setAdapter(adapter);
    }

    private long userId;
    private int type;
    private String title;
    private void initData() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            userId= bundle.getLong("userId");
            type=bundle.getInt("type");
            title=bundle.getString("title");
        }
        tv_title.setText(title);

    }

    @Override
    public boolean onBackPressedSupport() {
        pop();
        return true;
    }

    @Override
    public void p2vReturnMoreFollowAndFansList(List<FollowsBean.PageDatasBean> pageDatas) {

    }
    @OnClick(R2.id.iv_back)
    public void OnClick(View view){
        pop();
    }

    private List<FollowsBean.PageDatasBean> pageDatas=new ArrayList<>();
    @Override
    public void p2vReturnFollowAndFansList(List<FollowsBean.PageDatasBean> pageDatas) {
        this.pageDatas=pageDatas;
        adapter.setmList(pageDatas);
    }

    @Override
    public void p2vHandleItemClickResult(SupportFragment fragment) {
        start(fragment);
    }

    @Override
    public void onFollowsAndFaansItemClick(View view, int position) {
        FollowsBean.PageDatasBean bean = pageDatas.get(position);
        iFollowsAndFollowedPresenter.v2pJudgyItemClick(view,bean,position,type);
    }
}
