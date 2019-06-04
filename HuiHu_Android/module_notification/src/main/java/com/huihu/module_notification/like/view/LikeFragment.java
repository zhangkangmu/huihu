package com.huihu.module_notification.like.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.intdef.NoticeId;
import com.huihu.module_notification.like.adapter.LikeMeAdapter;
import com.huihu.module_notification.like.entity.LikeMePageBean;
import com.huihu.module_notification.like.likeinterface.ILikePresenter;
import com.huihu.module_notification.like.likeinterface.ILikeView;
import com.huihu.module_notification.like.presenter.ImpLikePresenter;
import com.huihu.module_notification.util.ReadUtil;
import com.huihu.module_notification.util.RouterUtil;
import com.huihu.uilib.statelayout.StateLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LikeFragment extends BaseFragment implements ILikeView {

    private final ILikePresenter iLikePresenter = new ImpLikePresenter(this);

    @BindView(R2.id.rv_like_me)
    RecyclerView rvLikeMe;
    Unbinder unbinder;
    @BindView(R2.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R2.id.tv_title)
    TextView tvTitle;

    private LikeMeAdapter mAdapter;
    private StateLayout mStateLayout;

    public static LikeFragment newInstance() {
        return new LikeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_notification_fragment_like, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvLikeMe.setLayoutManager(layoutManager);
        mAdapter = new LikeMeAdapter(iLikePresenter, getContext());
        rvLikeMe.setAdapter(mAdapter);
        mStateLayout = new StateLayout.Builder(rvLikeMe).create();
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                iLikePresenter.v2pGetFirstData();
            }
        });
        refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int end = mAdapter.getItemCount() - 1;
                iLikePresenter.v2pGetMoreData(mAdapter.getDataList().get(end));
            }
        });
        iLikePresenter.v2pGetFirstData();
        TextViewUtils.setTextFakeBold(tvTitle);
        ReadUtil.readNotice(NoticeId.LIKE_ME);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R2.id.iv_back})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            pop();
        }
    }

    @Override
    public void p2vShowFirstData(List<LikeMePageBean.PageDatasBean> beans) {
        mAdapter.setDataList(beans);
        mAdapter.notifyDataSetChanged();
        mStateLayout.showContent();
        refresh.setEnableLoadMore(true);
    }

    @Override
    public void p2vShowMoreData(List<LikeMePageBean.PageDatasBean> beans) {
        int oldCount = mAdapter.getItemCount();
        mAdapter.getDataList().addAll(beans);
        int newCount = mAdapter.getItemCount();
        mAdapter.notifyItemRangeChanged(oldCount, newCount);
    }

    @Override
    public void p2vGetDataComplete() {
        refresh.finishRefresh();
        refresh.finishLoadMore();
    }

    @Override
    public void p2vShowNoData() {
        mStateLayout.showEmptyData();
        refresh.setEnableLoadMore(false);
    }

    @Override
    public void p2vShowGetDataFail() {
        mStateLayout.showNoNetwork();
    }

    @Override
    public void p2vStartOtherPeople(long uid) {
        RouterUtil.startOtherPeople(this, uid);
    }
}
