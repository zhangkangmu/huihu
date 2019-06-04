package com.huihu.module_notification.newreply.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
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
import com.huihu.module_notification.newreply.adapter.NewReplyAdapter;
import com.huihu.module_notification.newreply.entity.NewReplyPageBean;
import com.huihu.module_notification.newreply.interfaces.NewReplyMVP;
import com.huihu.module_notification.newreply.presenter.ImpNewReplyPresenter;
import com.huihu.module_notification.util.RouterUtil;
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


public class NewReplyFragment extends BaseFragment implements NewReplyMVP.IView {

    private static final String MESSAGE_STATE = "message_state";
    private static final String QUESTION_ID = "question_id";

    private final NewReplyMVP.IPresenter iPresenter = new ImpNewReplyPresenter(this);

    @BindView(R2.id.tv_title)
    TextView tvTitle;
    @BindView(R2.id.tv_question_title)
    TextView tvQuestionTitle;
    @BindView(R2.id.rv_content)
    RecyclerView rvContent;
    @BindView(R2.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R2.id.coordinator)
    CoordinatorLayout coordinator;
    Unbinder unbinder;

    private StateLayout mStateLayout;
    private NewReplyAdapter mAdapter;

    public static NewReplyFragment newInstance(int messageState, long questionId){
        NewReplyFragment fragment = new NewReplyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(MESSAGE_STATE, messageState);
        bundle.putLong(QUESTION_ID, questionId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.module_notification_fragment_new_reply, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextViewUtils.setTextFakeBold(tvTitle);
        TextViewUtils.setTextFakeBold(tvQuestionTitle);
        MaterialHeader header = new MaterialHeader(getContext());
        refresh.setRefreshHeader(header);
        refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                iPresenter.v2pGetMoreData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getFirstData();
            }
        });
        mAdapter = new NewReplyAdapter(iPresenter);
        rvContent.setAdapter(mAdapter);
        rvContent.setLayoutManager(new LinearLayoutManager(getContext()));
        mStateLayout = new StateLayout.Builder(coordinator).setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFirstData();
            }
        }).create();
        getFirstData();
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
    public void p2vShowFirstData(String question, List<NewReplyPageBean.RecentAnswerBean> beans) {
        tvQuestionTitle.setText(question);
        mAdapter.setBeans(beans);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void p2vShowMoreData(List<NewReplyPageBean.RecentAnswerBean> beans) {
        int oldCount = mAdapter.getItemCount();
        mAdapter.getBeans().addAll(beans);
        int newCount = mAdapter.getItemCount();
        mAdapter.notifyItemRangeInserted(oldCount, newCount);
    }

    @Override
    public void p2vShowNoData() {
        mStateLayout.showEmptyData();
    }

    @Override
    public void p2vShowGetDataFail() {
        mStateLayout.showNoNetwork();
    }

    @Override
    public void p2vGetDataComplete() {
        refresh.finishRefresh();
        refresh.finishLoadMore();
    }

    @Override
    public void p2vStartLookOtherPeople(long uid) {
        RouterUtil.startOtherPeople(this, uid);
    }

    @Override
    public void p2vStartAnswerDetail(long answerId) {
        RouterUtil.startAnswerDetail(this, answerId);
    }

    private void getFirstData(){
        int messageState = getArguments().getInt(MESSAGE_STATE);
        long questionId = getArguments().getLong(QUESTION_ID);
        iPresenter.v2pGetFirstData(messageState, questionId);
    }
}
