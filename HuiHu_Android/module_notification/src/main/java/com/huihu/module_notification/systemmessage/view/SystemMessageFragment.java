package com.huihu.module_notification.systemmessage.view;

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
import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.commonlib.utils.BitmapUtils;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.intdef.NoticeId;
import com.huihu.module_notification.util.ReadUtil;
import com.huihu.module_notification.systemmessage.adapter.SystemMessageAdapter;
import com.huihu.module_notification.systemmessage.entity.SystemMessagePageBean;
import com.huihu.module_notification.systemmessage.interfaces.SystemMessageMVP;
import com.huihu.module_notification.systemmessage.presenter.ImpSystemMessagePresenter;
import com.huihu.module_notification.util.WebUtil;
import com.huihu.uilib.RouterReDefine;
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

public class SystemMessageFragment extends BaseFragment implements SystemMessageMVP.IView {

    private final SystemMessageMVP.IPresenter iPresenter = new ImpSystemMessagePresenter(this);
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.rv_content)
    RecyclerView rvContent;
    @BindView(R2.id.refresh)
    SmartRefreshLayout refresh;
    Unbinder unbinder;
    private SystemMessageAdapter mAdapter;
    private StateLayout mStateLayout;

    public static SystemMessageFragment newInstance(){
        return new SystemMessageFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_notification_fragment_system_message, container
                , false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextViewUtils.setTextFakeBold(tvTitle);
        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new SystemMessageAdapter(iPresenter);
        rvContent.setAdapter(mAdapter);
        mStateLayout = new StateLayout.Builder(rvContent).setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPresenter.v2pGetFirstListData();
            }
        }).create();
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                iPresenter.v2pGetFirstListData();
            }
        });
        refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                iPresenter.v2pGetMoreListData();
            }
        });
        MaterialHeader header = new MaterialHeader(getContext());
        header.setColorSchemeResources(R.color.global_blue);
        refresh.setRefreshHeader(header);
        refresh.setRefreshFooter(new HuihuSmartRefreshFooter(getContext()));
        iPresenter.v2pGetFirstListData();
        ReadUtil.readNotice(NoticeId.SYSTEM_MESSAGE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R2.id.iv_back})
    public void onClick(View v){
        int id = v.getId();
        if (id == R.id.iv_back){
            pop();
        }
    }


    @Override
    public void p2VShowFirstData(List<SystemMessagePageBean.SystemMessageBean> beans) {
        mStateLayout.showContent();
        mAdapter.setBeanList(beans);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void p2vShowMoreData(List<SystemMessagePageBean.SystemMessageBean> beans) {
        int oldCount = mAdapter.getItemCount();
        mAdapter.getBeanList().addAll(beans);
        int newCount = mAdapter.getItemCount();
        mAdapter.notifyItemRangeInserted(oldCount, newCount);
    }

    @Override
    public void p2vShowNoData() {
        mStateLayout.showEmptyData();
    }

    @Override
    public void p2vGetDataComplete() {
        refresh.finishRefresh();
        refresh.finishLoadMore();
    }

    @Override
    public void p2vShowGetDataFail() {
        mStateLayout.showNoNetwork();
    }

    @Override
    public void p2vStartQuestion(long questionId) {
        Bundle bundle = new Bundle();
        bundle.putLong("ideaId", questionId);
        BaseFragment fragment = (BaseFragment) SimpleRouter.getInstance()
                .getFragment(RouterReDefine.QUESTIONDETAIL_FRAGMENT, bundle);
        if (fragment != null) start(fragment);

    }

    @Override
    public void p2vStartAnswer(long answerId) {
        Bundle bundle = new Bundle();
        bundle.putLong("ideaId", answerId);
        BaseFragment fragment = (BaseFragment) SimpleRouter.getInstance()
                .getFragment(RouterReDefine.ANSWERDETAIL_FRAGMENT, bundle);
        if (fragment != null) start(fragment);
    }

    @Override
    public void p2vStartWeb(String url) {
        WebUtil.startWeb(url);
    }

}
