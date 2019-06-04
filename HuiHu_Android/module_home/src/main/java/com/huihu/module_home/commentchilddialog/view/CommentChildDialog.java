package com.huihu.module_home.commentchilddialog.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouterClassRegister;
import com.huihu.commonlib.simpleRouter.SimpleRouterObj;
import com.huihu.commonlib.utils.BitmapUtils;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.commentchilddialog.adapter.CommentChildAdapter;
import com.huihu.module_home.commentchilddialog.commentchilddialoginterface.ICommentChildDialogPresenter;
import com.huihu.module_home.commentchilddialog.commentchilddialoginterface.ICommentChildDialogView;
import com.huihu.module_home.commentchilddialog.entity.CommentChild;
import com.huihu.module_home.commentchilddialog.entity.CommentHead;
import com.huihu.module_home.commentchilddialog.presenter.ImpCommentChildDialogPresenter;
import com.huihu.module_home.commentdialog.adapter.CommentAdapter;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.commentedit.entity.PublishCommentBean;
import com.huihu.uilib.commentedit.view.CommentEditDialog;
import com.huihu.uilib.complaint.view.ComplaintDialog;
import com.huihu.commonlib.MaterialFooter;
import com.huihu.uilib.statelayout.StateLayout;
import com.huihu.uilib.util.CountUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by jiangwensong on 2019/4/2.
 * description：
 */
@SimpleRouterClassRegister(key = RouterReDefine.COMMENT_DETAILS_FRAGMENT, type = SimpleRouterObj.FRAGMENT)
public class CommentChildDialog extends BaseFragment implements ICommentChildDialogView, View.OnClickListener {

    private static final String TAG = "CommentChildDialog";

    ICommentChildDialogPresenter iCommentChildDialogPresenter = new ImpCommentChildDialogPresenter(this);

    private Context mContext;

    @BindView(R2.id.rv_comment_child)
    RecyclerView mRvCommentChild;
    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R2.id.include_bottomer)
    ConstraintLayout mClBottom;
    @BindView(R2.id.tv_edit)
    TextView mTvEdit;
    @BindView(R2.id.iv_back)
    ImageView mIvBack;

    private CommentChildAdapter mCommentChildAdapter;
    private StateLayout mStateLayout;

    private long mCommentId;
    private long mIdeaId;
    private long mCommentUid;
    private CommentEditDialog.PublishCommentCallBack mPublishCommentCallBack;

    Unbinder unbinder;

    public static CommentChildDialog newInstance(long ideaId, long commentId) {
        CommentChildDialog commentDialog = new CommentChildDialog();
        Bundle bundle = new Bundle();
        bundle.putLong("ideaId", ideaId);
        bundle.putLong("commentId", commentId);
        commentDialog.setArguments(bundle);
        return commentDialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_home_view_comment_child, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getContext();
        mCommentId = getArguments().getLong("commentId");
        mIdeaId = getArguments().getLong("ideaId");
        initView();
        initData();
    }

    private void initView() {

        mIvBack.setOnClickListener(this);
        mTvEdit.setOnClickListener(this);

        //界面刷新
        mStateLayout = new StateLayout.Builder(mRvCommentChild).setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCommentChildDialogPresenter.v2pGetFirstChildCommentList(mCommentId);
            }
        }).create();
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                iCommentChildDialogPresenter.v2pGetHeadComment(mCommentId);
                iCommentChildDialogPresenter.v2pGetFirstChildCommentList(mCommentId);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                iCommentChildDialogPresenter.v2pGetMoreChildCommentList(mCommentId);
            }
        });
        MaterialHeader header = new MaterialHeader(getContext());
        header.setColorSchemeResources(R.color.global_blue);
        mRefreshLayout.setRefreshHeader(header);
        mRefreshLayout.setRefreshFooter(new MaterialFooter(getContext()));
    }

    private void initData() {
        mStateLayout.showLoadingData();

        mCommentChildAdapter = new CommentChildAdapter(getContext());
        mCommentChildAdapter.setOnClickListener(this);
        mRvCommentChild.setAdapter(mCommentChildAdapter);

        iCommentChildDialogPresenter.v2pGetHeadComment(mCommentId);
        iCommentChildDialogPresenter.v2pGetFirstChildCommentList(mCommentId);
    }

    @Override
    public void p2vGetHeadCommentSuccess(CommentHead head) {
        mCommentUid = head.getCommentUid();
        mCommentChildAdapter.setHead(head);
    }

    @Override
    public void p2vGetHeadCommentError(String error) {

    }

    @Override
    public void p2vGetFirstChildCommentListSuccess(CommentChild commentChild) {
        mStateLayout.showContent();
        mCommentChildAdapter.setDatas(commentChild);
    }

    @Override
    public void p2vGetMoreChildCommentListSuccess(CommentChild commentChild) {
        mStateLayout.showContent();
        int oldSize = mCommentChildAdapter.getDatas().size();
        mCommentChildAdapter.getDatas().addAll(commentChild.getPageDatas());
        int newSize = mCommentChildAdapter.getDatas().size();
        mCommentChildAdapter.notifyItemRangeChanged(oldSize, newSize);
    }

    @Override
    public void p2vGetChildCommentListError(String error) {
        ToastUtil.show(error);
        mStateLayout.showNoNetwork();
    }

    @Override
    public void p2vGetChildCommentListCompleted() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void p2vShowNoData() {
        mStateLayout.showContent();
    }

    @Override
    public void p2vPutGiveLikeSuccess() {
        ToastUtil.show("点赞成功");
    }

    @Override
    public void p2vPutGiveLikeError(String msg) {
        ToastUtil.show("点赞失败");
    }

    @Override
    public void p2vPutGiveUpLikeSuccess() {
        ToastUtil.show("取消点赞成功");
    }

    @Override
    public void p2vPutGiveUpLikeError(String msg) {
        ToastUtil.show("取消点赞失败");
    }

    @Override
    public void p2vDeleteCommentSuccess() {

    }

    @Override
    public void p2vDeleteCommentFail(String error) {

    }

    @Override
    public void p2vGetLastComment() {
        mCommentChildAdapter.setNoMore(true);
        mRefreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void p2vNoGetLastComment() {
        mCommentChildAdapter.setNoMore(false);
        mRefreshLayout.setEnableLoadMore(true);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Log.d(TAG, "onclick " + viewId);
        if (viewId == R.id.iv_back) {
            pop();
        } else if (viewId == R.id.iv_more) {
            //举报&删除底部弹窗
            final Bundle bundle = (Bundle) v.getTag();
            final BottomSheetDialog dialog = new BottomSheetDialog(getContext());
            dialog.setContentView(R.layout.module_home_comment_dialog_more);
            TextView tvComplaint = dialog.findViewById(R.id.tv_complaint);
            TextView tvDelete = dialog.findViewById(R.id.tv_delete);
            TextView tvCancle = dialog.findViewById(R.id.tv_cancel);
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int viewId = v.getId();
                    if (viewId == R.id.tv_complaint) {
                        //举报
                        ComplaintDialog.newInstance(mContext, 5, bundle.getLong(CommentChildAdapter.COMMENT_UID));
                        dialog.dismiss();
                    } else if (viewId == R.id.tv_delete) {
                        //删除评论， 只能删除自己的评论
                        if (SPUtils.getInstance().getCurrentUid() == bundle.getLong(CommentChildAdapter.COMMENT_UID)) {
                            if (bundle.getInt(CommentChildAdapter.COMMENT_LEVEL) == 2) {
                                iCommentChildDialogPresenter.v2pDeleteComment(1, bundle.getLong(CommentChildAdapter.COMMENT_ID));
                            } else if (bundle.getInt(CommentChildAdapter.COMMENT_LEVEL) == 3) {
                                iCommentChildDialogPresenter.v2pDeleteComment(2, bundle.getLong(CommentChildAdapter.COMMENT_CHILD_ID));
                            }

                        } else {
                            ToastUtil.show("无权限删除他人评论");
                        }
                        dialog.dismiss();
                    } else if (viewId == R.id.tv_cancel) {
                        dialog.dismiss();
                    }
                }
            };
            tvComplaint.setOnClickListener(listener);
            tvDelete.setOnClickListener(listener);
            tvCancle.setOnClickListener(listener);
            dialog.show();
        } else if (viewId == R.id.ll_like) {
            //点赞
            Bundle bundle = (Bundle) v.getTag();
            if (bundle.getInt(CommentAdapter.COMMENT_LEVEL) == 2) {
                if (bundle.getInt(CommentAdapter.IS_AGREE) == 0) {
                    bundle.putInt(CommentAdapter.IS_AGREE, 1);
                    TextView agreeCount = v.findViewById(R.id.tv_like_count);
                    agreeCount.setText(CountUtil.toHuihuCount(bundle.getInt(CommentAdapter.AGREE_COUNT) + 1));
                    bundle.putInt(CommentChildAdapter.AGREE_COUNT, bundle.getInt(CommentAdapter.AGREE_COUNT) + 1);
                    v.setTag(bundle);
                    ImageView like = v.findViewById(R.id.iv_like);
                    like.setImageBitmap(BitmapUtils.drawableToBitmap(getContext().getDrawable(R.drawable.module_home_icon_like_blue)));
                    iCommentChildDialogPresenter.v2pPutGiveLike(bundle.getLong(CommentAdapter.COMMENT_ID), bundle.getInt(CommentAdapter.COMMENT_LEVEL));
                } else {
                    bundle.putInt(CommentAdapter.IS_AGREE, 0);
                    TextView agreeCount = v.findViewById(R.id.tv_like_count);
                    agreeCount.setText(CountUtil.toHuihuCount(bundle.getInt(CommentAdapter.AGREE_COUNT) - 1));
                    bundle.putInt(CommentChildAdapter.AGREE_COUNT, bundle.getInt(CommentAdapter.AGREE_COUNT) - 1);
                    v.setTag(bundle);
                    ImageView like = v.findViewById(R.id.iv_like);
                    like.setImageBitmap(BitmapUtils.drawableToBitmap(getContext().getDrawable(R.drawable.module_home_icon_like)));
                    iCommentChildDialogPresenter.v2pPutGiveUpLike(bundle.getLong(CommentAdapter.COMMENT_ID), bundle.getInt(CommentAdapter.COMMENT_LEVEL));
                }
            } else if (bundle.getInt(CommentAdapter.COMMENT_LEVEL) == 3) {
                if (bundle.getInt(CommentAdapter.IS_AGREE) == 0) {
                    bundle.putInt(CommentAdapter.IS_AGREE, 1);
                    TextView agreeCount = v.findViewById(R.id.tv_like_count);
                    agreeCount.setText(CountUtil.toHuihuCount(bundle.getInt(CommentAdapter.AGREE_COUNT) + 1));
                    bundle.putInt(CommentChildAdapter.AGREE_COUNT, bundle.getInt(CommentAdapter.AGREE_COUNT) + 1);
                    v.setTag(bundle);
                    ImageView like = v.findViewById(R.id.iv_like);
                    like.setImageBitmap(BitmapUtils.drawableToBitmap(getContext().getDrawable(R.drawable.module_home_icon_like_blue)));
                    iCommentChildDialogPresenter.v2pPutGiveLike(bundle.getLong(CommentAdapter.COMMENT_CHILD_ID), bundle.getInt(CommentAdapter.COMMENT_LEVEL));
                } else {
                    bundle.putInt(CommentAdapter.IS_AGREE, 0);
                    TextView agreeCount = v.findViewById(R.id.tv_like_count);
                    agreeCount.setText(CountUtil.toHuihuCount(bundle.getInt(CommentAdapter.AGREE_COUNT) - 1));
                    bundle.putInt(CommentChildAdapter.AGREE_COUNT, bundle.getInt(CommentAdapter.AGREE_COUNT) - 1);
                    v.setTag(bundle);
                    ImageView like = v.findViewById(R.id.iv_like);
                    like.setImageBitmap(BitmapUtils.drawableToBitmap(getContext().getDrawable(R.drawable.module_home_icon_like)));
                    iCommentChildDialogPresenter.v2pPutGiveUpLike(bundle.getLong(CommentAdapter.COMMENT_CHILD_ID), bundle.getInt(CommentAdapter.COMMENT_LEVEL));
                }
            }
        } else if (viewId == R.id.iv_avatar || viewId == R.id.tv_nick_name || viewId == R.id.tv_replied_name) {
            ToastUtil.show("用户详情");
        } else if (viewId == R.id.tv_comment_text || viewId == R.id.iv_comment_reply) {
            //回复二级评论
            Bundle bundle = (Bundle) v.getTag();
            if (SPUtils.getInstance().getCurrentUid() == bundle.getLong(CommentAdapter.COMMENT_UID)) {
                ToastUtil.show("不能回复自己");
                return;
            }

            PublishCommentBean publishCommentBean = new PublishCommentBean();
            if (bundle.getInt(CommentChildAdapter.COMMENT_LEVEL) == 2) {
                //二级评论
                publishCommentBean.setCommentId(bundle.getLong(CommentChildAdapter.COMMENT_ID));
                publishCommentBean.setAtCommentId(bundle.getLong(CommentChildAdapter.COMMENT_ID));
                publishCommentBean.setAtUid(bundle.getLong(CommentChildAdapter.COMMENT_UID));
                publishCommentBean.setCommentIp("127.0.0.0");
                publishCommentBean.setCommentGrade(2);
                publishCommentBean.setIdeaId(mIdeaId);
                publishCommentBean.setCommentType(1);//二级评论也是评论
                CommentEditDialog.newInstance(mContext, publishCommentBean, bundle.getString(CommentChildAdapter.COMMENT_NICK_NAME), mPublishCommentCallBack).show();
            } else if (bundle.getInt(CommentChildAdapter.COMMENT_LEVEL) == 3) {
                //回复二级评论
                publishCommentBean.setCommentId(bundle.getLong(CommentChildAdapter.COMMENT_ID));
                publishCommentBean.setAtCommentId(bundle.getLong(CommentChildAdapter.COMMENT_ID));
                publishCommentBean.setAtUid(bundle.getLong(CommentChildAdapter.COMMENT_UID));
                publishCommentBean.setCommentIp("127.0.0.0");
                publishCommentBean.setCommentGrade(2);
                publishCommentBean.setIdeaId(mIdeaId);
                publishCommentBean.setCommentType(2);//回复
                CommentEditDialog.newInstance(mContext, publishCommentBean, bundle.getString(CommentChildAdapter.COMMENT_NICK_NAME), mPublishCommentCallBack).show();
            }

        } else if (viewId == R.id.cl_edit_bottom || viewId == R.id.tv_edit) {
            //二级评论
            PublishCommentBean publishCommentBean = new PublishCommentBean();
            publishCommentBean.setCommentId(mCommentId);
            publishCommentBean.setAtCommentId(mCommentId);
            publishCommentBean.setAtUid(mCommentUid);
            publishCommentBean.setCommentIp("127.0.0.0");
            publishCommentBean.setCommentGrade(2);
            publishCommentBean.setIdeaId(mIdeaId);
            publishCommentBean.setCommentType(1);//二级评论也是评论
            CommentEditDialog.newInstance(mContext, publishCommentBean, null, mPublishCommentCallBack).show();
        }
    }
}
