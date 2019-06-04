package com.huihu.module_notification.comment.view;

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
import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.comment.adapter.OtherCommentMeAdapter;
import com.huihu.module_notification.comment.commentinterface.ICommentPresenter;
import com.huihu.module_notification.comment.commentinterface.ICommentView;
import com.huihu.module_notification.comment.entity.CommentPageBean;
import com.huihu.module_notification.comment.presenter.ImpCommentPresenter;
import com.huihu.module_notification.commentdetails.view.CommentDetailsFragment;
import com.huihu.module_notification.intdef.NoticeId;
import com.huihu.module_notification.util.ReadUtil;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.commentedit.entity.PublishCommentBean;
import com.huihu.uilib.commentedit.view.CommentEditDialog;
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

public class CommentFragment extends BaseFragment implements ICommentView, View.OnClickListener {
    private static final String TAG = "CommentFragment";
    private final ICommentPresenter iCommentPresenter = new ImpCommentPresenter(this);

    @BindView(R2.id.rv_other_comment_me)
    RecyclerView rvOtherCommentMe;
    Unbinder unbinder;
    @BindView(R2.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R2.id.tv_title)
    TextView tvTitle;
    private OtherCommentMeAdapter mAdapter;
    private StateLayout mStateLayout;

    public static CommentFragment newInstance() {
        return new CommentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.module_notification_fragment_comment, container, false);
        unbinder = ButterKnife.bind(this, v);
        return attachToSwipeBack(v);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvOtherCommentMe.setLayoutManager(linearLayoutManager);
        mAdapter = new OtherCommentMeAdapter(getContext());
        mAdapter.setOnClickListener(this);
        rvOtherCommentMe.setAdapter(mAdapter);
        iCommentPresenter.v2pGetFirstData();
        mStateLayout = new StateLayout.Builder(rvOtherCommentMe).create();
        refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int end = mAdapter.getDatas().size() - 1;
                iCommentPresenter.v2pGetMoreData(mAdapter.getDatas().get(end));
            }
        });
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                iCommentPresenter.v2pGetFirstData();
            }
        });
        MaterialHeader header = new MaterialHeader(getContext());
        header.setColorSchemeResources(R.color.global_blue);
        refresh.setRefreshHeader(header);
        refresh.setRefreshFooter(new HuihuSmartRefreshFooter(getContext()));
        TextViewUtils.setTextFakeBold(tvTitle);
        ReadUtil.readNotice(NoticeId.COMMENT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R2.id.iv_back})
    @Override
    public void onClick(View v) {
        int id = v.getId();
        Log.d(TAG, "onClick " + id);
        if (id == R.id.iv_back) {
            pop();
        } else if(id == R.id.tv_reply){
            //TODO 分种类处理
            //回复一级评论或回复二级评论
            Bundle bundle = (Bundle) v.getTag();
            PublishCommentBean publishCommentBean = new PublishCommentBean();
            int type = bundle.getInt(OtherCommentMeAdapter.ABOUNT_TYPE);
            if ( type == 1 || type == 2 ) {
                //二级评论
                publishCommentBean.setCommentId(bundle.getLong(OtherCommentMeAdapter.COMMENT_ID));
                publishCommentBean.setAtCommentId(bundle.getLong(OtherCommentMeAdapter.COMMENT_ID));
                publishCommentBean.setAtUid(bundle.getLong(OtherCommentMeAdapter.COMMENT_UID));
                publishCommentBean.setCommentIp("127.0.0.0");
                publishCommentBean.setCommentGrade(2);
                publishCommentBean.setIdeaId(bundle.getLong(OtherCommentMeAdapter.IDEA_ID));
                publishCommentBean.setCommentType(1);//二级评论也是评论
                CommentEditDialog.newInstance(getContext(), publishCommentBean, bundle.getString(OtherCommentMeAdapter.COMMENT_NICK_NAME),null).show();
            } else if ( type == 3 ||type == 4 ) {
                //回复二级评论
                publishCommentBean.setCommentId(bundle.getLong(OtherCommentMeAdapter.COMMENT_ID));
                publishCommentBean.setAtCommentId(bundle.getLong(OtherCommentMeAdapter.COMMENT_ID));
                publishCommentBean.setAtUid(bundle.getLong(OtherCommentMeAdapter.COMMENT_UID));
                publishCommentBean.setCommentIp("127.0.0.0");
                publishCommentBean.setCommentGrade(2);
                publishCommentBean.setIdeaId(bundle.getLong(OtherCommentMeAdapter.IDEA_ID));
                publishCommentBean.setCommentType(2);//回复
                CommentEditDialog.newInstance(getContext(), publishCommentBean, bundle.getString(OtherCommentMeAdapter.COMMENT_NICK_NAME),null).show();
            }
        }else if(id == R.id.tv_other_comment_content){
            //评论详情 CommentChildDialog
            Bundle bundle = (Bundle) v.getTag();
            SupportFragment fragment = (SupportFragment)SimpleRouter.getInstance()
                    .getFragment(RouterReDefine.COMMENT_DETAILS_FRAGMENT);
            Bundle b = new Bundle();
            b.putLong("ideaId", bundle.getLong(OtherCommentMeAdapter.IDEA_ID));
            b.putLong("commentId", bundle.getLong(OtherCommentMeAdapter.FIND_COMMENT_ID));
            fragment.setArguments(b);
            start(fragment);

        } else if (id == R.id.source_other_comment){
            Bundle bundle = (Bundle) v.getTag();
            int type = bundle.getInt(OtherCommentMeAdapter.ABOUNT_TYPE);
            if (type == 1){
                //TODO 讨论详情
                ToastUtil.show("讨论详情");
            }else if (type == 2){
                //回答详情 AnswerDetailFragment
                SupportFragment fragment = (SupportFragment)SimpleRouter.getInstance()
                        .getFragment(RouterReDefine.ANSWER_DETAIL_FRAGMENT);
                Bundle b = new Bundle();
                b.putLong("ideaId", bundle.getLong(OtherCommentMeAdapter.IDEA_ID));
                b.putLong("uid", SPUtils.getInstance().getCurrentUid());
                fragment.setArguments(b);
                start(fragment);
            }else if (type == 3 || type == 4){
                //评论详情 CommentChildDialog
                SupportFragment fragment = (SupportFragment)SimpleRouter.getInstance()
                        .getFragment(RouterReDefine.COMMENT_DETAILS_FRAGMENT);
                Bundle b = new Bundle();
                b.putLong("ideaId", bundle.getLong(OtherCommentMeAdapter.IDEA_ID));
                b.putLong("commentId", bundle.getLong(OtherCommentMeAdapter.FIND_COMMENT_ID));
                fragment.setArguments(b);
                start(fragment);
            }
        }
    }

    @Override
    public void p2vShowFirstData(List<CommentPageBean.DataBean> dataBeans) {
        mAdapter.setDatas(dataBeans);
        mAdapter.notifyDataSetChanged();
        mStateLayout.showContent();
        refresh.setEnableLoadMore(true);
    }

    @Override
    public void p2vShowMoreData(List<CommentPageBean.DataBean> dataBeans) {
        int oldCount = mAdapter.getItemCount();
        mAdapter.getDatas().addAll(dataBeans);
        int newCount = mAdapter.getItemCount();
        mAdapter.notifyItemRangeInserted(oldCount, newCount);
    }

    @Override
    public void p2vShowNoData() {
        mStateLayout.showEmptyData();
        refresh.setEnableLoadMore(false);
    }

    @Override
    public void p2vGetDataEnd() {
        refresh.finishLoadMore();
        refresh.finishRefresh();
    }

    @Override
    public void p2vShowGetDataFail() {
        mStateLayout.showNoNetwork();
    }
}
