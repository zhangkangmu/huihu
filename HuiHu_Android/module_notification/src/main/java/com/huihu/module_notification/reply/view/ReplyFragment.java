package com.huihu.module_notification.reply.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.commonlib.MaterialFooter;
import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.intdef.NoticeId;
import com.huihu.module_notification.reply.adapter.ReplyAdapter;
import com.huihu.module_notification.reply.adapter.ReplyPagerAdapter;
import com.huihu.module_notification.reply.entity.ReplyBean;
import com.huihu.module_notification.reply.presenter.ImpReplyPresenter;
import com.huihu.module_notification.reply.replyinterface.IReplyPresenter;
import com.huihu.module_notification.reply.replyinterface.IReplyView;
import com.huihu.module_notification.util.ReadUtil;
import com.huihu.module_notification.util.RouterUtil;
import com.huihu.uilib.adapter.HuihuNavigationAdapter;
import com.huihu.uilib.def.NetDataBoolean;
import com.huihu.uilib.model.IndicatorParamModel;
import com.huihu.uilib.model.TextStyle;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ReplyFragment extends BaseFragment implements IReplyView {

    private static final int MINE_QUESTION_REPLY = 0;
    private static final int ATTENTION_QUESTION_REPLY = 1;

    private final IReplyPresenter iReplyPresenter = new ImpReplyPresenter(this);

    @BindView(R2.id.indicator_content)
    MagicIndicator indicatorContent;
    @BindView(R2.id.vp_content)
    ViewPager vpContent;
    @BindView(R2.id.refresh)
    SmartRefreshLayout refresh;
    Unbinder unbinder;

    private ReplyAdapter mMineAdapter, mAttentionAdapter;
    private ReplyPagerAdapter vpAdapter;
    private int mType;


    public static ReplyFragment newInstance() {
        return new ReplyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.module_notification_fragment_reply, container, false);
        unbinder = ButterKnife.bind(this, v);
        return attachToSwipeBack(v);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initIndicator();
        initListener();
        initRefresh();
        vpAdapter = new ReplyPagerAdapter(getContext(), iReplyPresenter);
        List<ReplyAdapter> adapters = new ArrayList();
        mMineAdapter = new ReplyAdapter(iReplyPresenter, MINE_QUESTION_REPLY);
        mAttentionAdapter = new ReplyAdapter(iReplyPresenter, ATTENTION_QUESTION_REPLY);
        adapters.add(mMineAdapter);
        adapters.add(mAttentionAdapter);
        vpAdapter.setAdapters(adapters);
        vpContent.setAdapter(vpAdapter);
        iReplyPresenter.v2pGetFirstDataAttention();
        iReplyPresenter.v2pGetFirstDataMine();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R2.id.iv_back, R2.id.tv_all_read})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back) {
            pop();
        } else if (id == R.id.tv_all_read) {
           readAll();
        }
    }


    private void initListener() {
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicatorContent.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                indicatorContent.onPageSelected(position);
                if (position == 0) {
                    mType = MINE_QUESTION_REPLY;
                } else {
                    mType = ATTENTION_QUESTION_REPLY;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                indicatorContent.onPageScrollStateChanged(state);
            }
        });
    }

    private void initIndicator() {
        IndicatorParamModel paramModel = new IndicatorParamModel();
        paramModel.setTextStyle(TextStyle.level_second);
        paramModel.setTitleStrings(getContext().getResources().getStringArray(R.array.module_notification_reply_indicator));
        CommonNavigator commonNavigator = new CommonNavigator(_mActivity);
        HuihuNavigationAdapter navigationAdapter = new HuihuNavigationAdapter(vpContent, paramModel);
        commonNavigator.setAdapter(navigationAdapter);
        indicatorContent.setNavigator(commonNavigator);
    }

    private void initRefresh() {
        MaterialHeader header = new MaterialHeader(getContext());
        header.setColorSchemeResources(R.color.global_blue);
        refresh.setRefreshHeader(header);
        refresh.setRefreshFooter(new MaterialFooter(getContext()));
        refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (mType == MINE_QUESTION_REPLY) {
                    int end = mMineAdapter.getItemCount() - 1;
                    iReplyPresenter.v2pGetMoreDataMine(mMineAdapter.getBeanList().get(end));
                } else {
                    int end = mAttentionAdapter.getItemCount() - 1;
                    iReplyPresenter.v2pGetMoreDataAttention(mAttentionAdapter.getBeanList().get(end));
                }
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (mType == MINE_QUESTION_REPLY) {
                    iReplyPresenter.v2pGetFirstDataMine();
                } else {
                    iReplyPresenter.v2pGetFirstDataAttention();
                }
            }
        });
    }

    @Override
    public void p2vShowFirstDataMine(List<ReplyBean> beans) {
        showFirstData(mMineAdapter, beans);
        vpAdapter.getMineStateLayout().showContent();
    }

    @Override
    public void p2vShowMoreDataMine(List<ReplyBean> beans) {
        showMoreData(mMineAdapter, beans);
    }

    @Override
    public void p2vShowFirstDataAttention(List<ReplyBean> beans) {
        showFirstData(mAttentionAdapter, beans);
        vpAdapter.getAttentionStateLayout().showContent();
    }

    @Override
    public void p2vShowMoreDataAttention(List<ReplyBean> beans) {
        showMoreData(mAttentionAdapter, beans);
    }

    @Override
    public void p2vChangeReplyReadStats(int type, ReplyBean bean) {
        int position;
        if (type == MINE_QUESTION_REPLY){
            position = mMineAdapter.getBeanList().indexOf(bean);
            mMineAdapter.notifyItemChanged(position);
        } else {
            position = mAttentionAdapter.getBeanList().indexOf(bean);
            mAttentionAdapter.notifyItemChanged(position);
        }
    }

    @Override
    public void p2vGetDataComplete() {
        refresh.finishLoadMore();
        refresh.finishRefresh();
    }

    @Override
    public void p2vGetDataFailMine() {
        vpAdapter.getMineStateLayout().showNoNetwork();
    }

    @Override
    public void p2vGetDataFailAttention() {
        vpAdapter.getAttentionStateLayout().showNoNetwork();
    }

    @Override
    public void p2vShowNoDataMine() {
        vpAdapter.getMineStateLayout().showEmptyData();
    }

    @Override
    public void p2vShowNoDataAttention() {
        vpAdapter.getAttentionStateLayout().showEmptyData();
    }

    @Override
    public void p2vStartAnswer(long answerId) {
        RouterUtil.startAnswerDetail(this, answerId);
    }

    @Override
    public void p2vStartQuestion(long questionId) {
        RouterUtil.startQuestion(this, questionId);
    }

    @Override
    public void p2vStartOtherPeople(long uid) {
        RouterUtil.startOtherPeople(this, uid);
    }


    private void showFirstData(ReplyAdapter adapter, List<ReplyBean> beans) {
        adapter.setBeanList(beans);
        adapter.notifyDataSetChanged();
    }


    private void showMoreData(ReplyAdapter adapter, List<ReplyBean> beans) {
        int oldCount = adapter.getItemCount();
        adapter.getBeanList().addAll(beans);
        int newCount = adapter.getItemCount();
        adapter.notifyItemRangeInserted(oldCount, newCount);
    }

    private void readAll(){
        ReadUtil.readNotice(NoticeId.REPLY, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                if (ReadUtil.Success.equals(returnModel.getSubCode())){
                    for (ReplyBean bean : mAttentionAdapter.getBeanList()){
                        bean.setMessageStatus(NetDataBoolean.NET_TRUE);
                    }
                    mAttentionAdapter.notifyDataSetChanged();
                    for (ReplyBean bean : mMineAdapter.getBeanList()){
                        bean.setMessageStatus(NetDataBoolean.NET_TRUE);
                    }
                    mMineAdapter.notifyDataSetChanged();
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
