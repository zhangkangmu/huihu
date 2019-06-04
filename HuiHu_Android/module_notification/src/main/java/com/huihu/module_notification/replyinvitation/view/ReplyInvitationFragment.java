package com.huihu.module_notification.replyinvitation.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.intdef.NoticeId;
import com.huihu.module_notification.util.ReadUtil;
import com.huihu.module_notification.replyinvitation.adapter.ReplyInvitationAdapter;
import com.huihu.module_notification.replyinvitation.entity.ReplyInvitationPageBean;
import com.huihu.module_notification.replyinvitation.presenter.ImpReplyInvitationPresenter;
import com.huihu.module_notification.replyinvitation.replyinvitationinterface.IReplyInvitationPresenter;
import com.huihu.module_notification.replyinvitation.replyinvitationinterface.IReplyInvitationView;
import com.huihu.module_notification.util.RouterUtil;
import com.huihu.uilib.customize.refresh.HuihuSmartRefreshFooter;
import com.huihu.uilib.def.NetDataBoolean;
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

public class ReplyInvitationFragment extends SupportFragment implements IReplyInvitationView {

    private final IReplyInvitationPresenter iReplyInvitationPresenter = new ImpReplyInvitationPresenter(this);

    @BindView(R2.id.rv_content)
    RecyclerView rvContent;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.refresh)
    SmartRefreshLayout refresh;
    Unbinder unbinder;

    private ReplyInvitationAdapter mAdapter;
    private StateLayout mStateLayout;

    public static ReplyInvitationFragment newInstance() {
        ReplyInvitationFragment fragment = new ReplyInvitationFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.module_notification_fragment_reply_invitation, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvContent.setLayoutManager(layoutManager);
        mAdapter = new ReplyInvitationAdapter(iReplyInvitationPresenter);
        rvContent.setAdapter(mAdapter);
        iReplyInvitationPresenter.v2pGetFirstListData();
        TextViewUtils.setTextFakeBold(tvTitle);
        mStateLayout = new StateLayout.Builder(rvContent).setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iReplyInvitationPresenter.v2pGetFirstListData();
            }
        }).create();
        MaterialHeader header = new MaterialHeader(getContext());
        header.setColorSchemeResources(R.color.global_blue);
        refresh.setRefreshHeader(header);
        refresh.setRefreshFooter(new HuihuSmartRefreshFooter(getContext()));
        refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                iReplyInvitationPresenter.v2pGetMoreListData();
            }
        });
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                iReplyInvitationPresenter.v2pGetFirstListData();
            }
        });
    }

    @OnClick({R2.id.iv_back, R2.id.tv_all_read})
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_back) {
            pop();
        } else if (id == R.id.tv_all_read){
            readAll();
        }
    }

    @Override
    public void p2vShowFirstData(List<ReplyInvitationPageBean.ReplyInvitationBean> beans) {
        mAdapter.setSessions(beans);
        mAdapter.notifyDataSetChanged();
        mStateLayout.showContent();
        refresh.setEnableLoadMore(true);
    }

    @Override
    public void p2vShowMoreData(List<ReplyInvitationPageBean.ReplyInvitationBean> beans) {
        int oldCount = mAdapter.getItemCount();
        mAdapter.getSessions().addAll(beans);
        int newCount = mAdapter.getItemCount();
        mAdapter.notifyItemRangeInserted(oldCount, newCount);
    }

    @Override
    public void p2vShowNoData() {
        mStateLayout.showNetworkError();
        refresh.setEnableLoadMore(false);
    }

    @Override
    public void p2vShowNetFail() {
        mStateLayout.showNoNetwork();
    }

    @Override
    public void p2vGetDataComplete() {
        refresh.finishLoadMore();
        refresh.finishRefresh();
    }

    @Override
    public void p2vStartOtherPeople(long uid) {
        RouterUtil.startOtherPeople(this, uid);
    }

    @Override
    public void p2vStartQuestion(long questionId) {
        RouterUtil.startQuestion(this, questionId);
    }

    @Override
    public void p2vChangeReadState(ReplyInvitationPageBean.ReplyInvitationBean bean) {
        int position = mAdapter.getSessions().indexOf(bean);
        mAdapter.notifyItemChanged(position);
    }

    private void readAll(){
        ReadUtil.readNotice(NoticeId.REPLY_INVITE, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
               if (ReadUtil.Success.equals(returnModel.getSubCode())){
                   for (ReplyInvitationPageBean.ReplyInvitationBean bean : mAdapter.getSessions()){
                       bean.setMessageStatus(NetDataBoolean.NET_TRUE);
                   }
                   mAdapter.notifyDataSetChanged();
               }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                ToastUtil.show(R.string.uilib_http_request_fail);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

}
