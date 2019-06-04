package com.huihu.module_notification.systemnotification.view;

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

import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.intdef.NoticeId;
import com.huihu.module_notification.util.ReadUtil;
import com.huihu.module_notification.systemnotification.adapter.SystemNotificationAdapter;
import com.huihu.module_notification.systemnotification.entity.SystemNoticePageBean;
import com.huihu.module_notification.systemnotification.presenter.ImpSystemNotificationPresenter;
import com.huihu.module_notification.systemnotification.systemnotificationinterface.ISystemNotificationPresenter;
import com.huihu.module_notification.systemnotification.systemnotificationinterface.ISystemNotificationView;
import com.huihu.uilib.customize.refresh.HuihuSmartRefreshFooter;
import com.huihu.uilib.statelayout.StateLayout;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public class SystemNotificationFragment extends SupportFragment implements ISystemNotificationView {

    private final ISystemNotificationPresenter iSystemNotificationPresenter = new ImpSystemNotificationPresenter(this);

    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.rv_content)
    RecyclerView rvContent;
    @BindView(R2.id.refresh)
    SmartRefreshLayout refresh;
    Unbinder unbinder;


    private StateLayout mStateLayout;
    private SystemNotificationAdapter mAdapter;

    public static SystemNotificationFragment newInstance() {
        return new SystemNotificationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_notification_fragment_system_notification, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvContent.setLayoutManager(layoutManager);
        mAdapter = new SystemNotificationAdapter();
        rvContent.setAdapter(mAdapter);
        TextViewUtils.setTextFakeBold(tvTitle);
        mStateLayout = new StateLayout.Builder(rvContent).setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iSystemNotificationPresenter.v2pGetFirstData();
            }
        }).create();
        MaterialHeader header = new MaterialHeader(getContext());
        header.setColorSchemeResources(R.color.global_blue);
        refresh.setRefreshHeader(header);
        refresh.setRefreshFooter(new HuihuSmartRefreshFooter(getContext()));
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                iSystemNotificationPresenter.v2pGetFirstData();
            }
        });
        refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                iSystemNotificationPresenter.v2pGetMoreData();
            }
        });
        iSystemNotificationPresenter.v2pGetFirstData();
        ReadUtil.readNotice(NoticeId.SYSTEM_NOTIFY);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R2.id.iv_back})
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_back) {
            pop();
        }
    }

    @Override
    public void p2vShowFirstData(List<SystemNoticePageBean.SystemNoticeBean> sessions) {
        mAdapter.setBeanList(sessions);
        mAdapter.notifyDataSetChanged();
        mStateLayout.showContent();
        refresh.setEnableLoadMore(true);
    }

    @Override
    public void p2vShowMoreData(List<SystemNoticePageBean.SystemNoticeBean> sessions) {
        int oldCount = mAdapter.getItemCount();
        mAdapter.getBeanList().addAll(sessions);
        int newCount = mAdapter.getItemCount();
        mAdapter.notifyItemRangeInserted(oldCount, newCount);
    }

    @Override
    public void p2vShowNoData() {
        mStateLayout.showEmptyData();
        refresh.setEnableLoadMore(false);

    }

    @Override
    public void p2vShowNetError() {
        mStateLayout.showNoNetwork();
    }

    @Override
    public void p2vShowGetDataComplete() {
        refresh.finishLoadMore();
        refresh.finishRefresh();
    }
}
