package com.huihu.module_notification.circleinvitation.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.circleinvitation.adapter.CircleInvitationAdapter;
import com.huihu.module_notification.circleinvitation.entity.CircleInvitationPageBean;
import com.huihu.module_notification.circleinvitation.interfaces.CircleInvitationMVP;
import com.huihu.module_notification.circleinvitation.presenter.ImpCircleInvitationPresenter;
import com.huihu.module_notification.intdef.NoticeId;
import com.huihu.module_notification.util.ReadUtil;
import com.huihu.module_notification.util.RouterUtil;
import com.huihu.uilib.customize.refresh.HuihuSmartRefreshFooter;
import com.huihu.uilib.statelayout.StateLayout;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CircleInvitationFragment extends BaseFragment implements CircleInvitationMVP.IView {
    private final CircleInvitationMVP.IPresenter iPresenter = new ImpCircleInvitationPresenter(this);

    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.rv_content)
    RecyclerView rvContent;
    @BindView(R2.id.refresh)
    SmartRefreshLayout refresh;

    Unbinder unbinder;

    private CircleInvitationAdapter mAdapter;
    private StateLayout mStateLayout;

    public static CircleInvitationFragment newInstance(){
        return new CircleInvitationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.module_notification_fragment_circle_invitation, container
                , false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                iPresenter.v2pGetMoreData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                iPresenter.v2pGetFirstData();
            }
        });
        MaterialHeader header = new MaterialHeader(getContext());
        header.setColorSchemeResources(R.color.global_blue);
        refresh.setRefreshHeader(header);
        refresh.setRefreshFooter(new HuihuSmartRefreshFooter(getContext()));
        iPresenter.v2pGetFirstData();
        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CircleInvitationAdapter(iPresenter);
        rvContent.setAdapter(mAdapter);
        mStateLayout = new StateLayout.Builder(rvContent).setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresenter.v2pGetFirstData();
            }
        }).create();
        ReadUtil.readNotice(NoticeId.CIRCLE_INVITE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R2.id.iv_back})
    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.iv_back){
            pop();
        }
    }


    @Override
    public void p2vShowFirstData(List<CircleInvitationPageBean.CircleInvitationData> dataList) {
        mAdapter.setDataList(dataList);
        mAdapter.notifyDataSetChanged();
        mStateLayout.showContent();
        refresh.setEnableLoadMore(true);
    }

    @Override
    public void p2vShowMoreData(List<CircleInvitationPageBean.CircleInvitationData> dataList) {
        int oldCount = mAdapter.getItemCount();
        mAdapter.getDataList().addAll(dataList);
        int newCount = mAdapter.getItemCount();
        mAdapter.notifyItemRangeInserted(oldCount, newCount);
    }

    @Override
    public void p2vShowNoData() {
        mStateLayout.showEmptyData();
        refresh.setEnableLoadMore(false);
    }

    @Override
    public void p2vGetDataComplete() {
        refresh.finishRefresh();
        refresh.finishLoadMore();
    }

    @Override
    public void p2vShowNetError() {
        mStateLayout.showNoNetwork();
    }

    @Override
    public void p2vChangeState(CircleInvitationPageBean.CircleInvitationData data) {
        int position = mAdapter.getDataList().indexOf(data);
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void p2vStartOtherPeople(long uid) {
        RouterUtil.startOtherPeople(this, uid);
    }
}
