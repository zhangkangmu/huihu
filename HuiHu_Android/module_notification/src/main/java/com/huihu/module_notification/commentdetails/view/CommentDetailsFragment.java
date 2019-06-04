package com.huihu.module_notification.commentdetails.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.BitmapUtils;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.commentdetails.adapter.CommentDetailsAdapter;
import com.huihu.module_notification.commentdetails.commentdetailsinterface.ICommentDetailsPresenter;
import com.huihu.module_notification.commentdetails.commentdetailsinterface.ICommentDetailsView;
import com.huihu.module_notification.commentdetails.entity.CommentChild;
import com.huihu.module_notification.commentdetails.entity.CommentHead;
import com.huihu.module_notification.commentdetails.presenter.ImpCommentDetailsPresenter;
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
import butterknife.OnClick;
import butterknife.Unbinder;

public class CommentDetailsFragment extends BaseFragment implements ICommentDetailsView, View.OnClickListener {
    private static final String TAG = "CommentDetailsFragment";
    private final ICommentDetailsPresenter iCommentDetailsPresenter = new ImpCommentDetailsPresenter(this);

    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R2.id.rv_comment_details)
    RecyclerView mRvCommentDetails;
    @BindView(R2.id.iv_select_picture)
    ImageView mIvSelectPicture;
    @BindView(R2.id.tv_publish)
    TextView mTvPublish;

    private static final String COMMENT_ID = "commentId";
    private static final String IDEA_ID = "ideaId";

    private long mCommentId;
    private long mIdeaId;
    private long mCommentUid;
    private StateLayout mStateLayout;
    private CommentDetailsAdapter mCommentDetailsAdapter;
    private CommentEditDialog.PublishCommentCallBack mPublishCommentCallBack;
    Unbinder unbinder;
    private Context mContext;
    public static CommentDetailsFragment newInstance(long ideaId,long commentId) {
        CommentDetailsFragment fragment = new CommentDetailsFragment();
        Bundle args = new Bundle();
        args.putLong(COMMENT_ID, commentId);
        args.putLong(IDEA_ID, ideaId);
        fragment.setArguments(args);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.module_notification_fragment_comment_details, container, false);
        unbinder = ButterKnife.bind(this, v);
        return attachToSwipeBack(v);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getContext();
        initView();
        initData();
    }

    private void initView(){
        //界面刷新
        mStateLayout = new StateLayout.Builder(mRvCommentDetails).setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCommentDetailsPresenter.v2pGetFirstChildCommentList(mCommentId);
            }
        }).create();
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                iCommentDetailsPresenter.v2pGetFirstChildCommentList(mCommentId);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                iCommentDetailsPresenter.v2pGetMoreChildCommentList(mCommentId);
            }
        });
        MaterialHeader header = new MaterialHeader(getContext());
        header.setColorSchemeResources(R.color.global_blue);
        mRefreshLayout.setRefreshHeader(header);
        mRefreshLayout.setRefreshFooter(new MaterialFooter(getContext()));
    }

    private void initData(){
        mCommentId = getArguments().getLong(COMMENT_ID);
        mIdeaId = getArguments().getLong(IDEA_ID);

        mCommentDetailsAdapter = new CommentDetailsAdapter(getContext());
        mCommentDetailsAdapter.setOnClickListener(this);

        iCommentDetailsPresenter.v2pGetHeadComment(mCommentId);
        iCommentDetailsPresenter.v2pGetFirstChildCommentList(mCommentId);
    }


    @Override
    public void p2vGetHeadCommentSuccess(CommentHead head) {
        mCommentUid = head.getCommentUid();
        mStateLayout.showContent();
        mCommentDetailsAdapter.setHead(head);
        mRvCommentDetails.setAdapter(mCommentDetailsAdapter);
    }

    @Override
    public void p2vGetHeadCommentError(String error) {

    }

    @Override
    public void p2vGetFirstChildCommentListSuccess(CommentChild commentChild) {
        mStateLayout.showContent();
        mCommentDetailsAdapter.setDatas(commentChild);
        mCommentDetailsAdapter.notifyDataSetChanged();
    }

    @Override
    public void p2vGetMoreChildCommentListSuccess(CommentChild commentChild) {
        mStateLayout.showContent();
        int oldSize = mCommentDetailsAdapter.getDatas().size();
        mCommentDetailsAdapter.getDatas().addAll(commentChild.getPageDatas());
        int newSize = mCommentDetailsAdapter.getDatas().size();
        mCommentDetailsAdapter.notifyItemRangeChanged(oldSize,newSize);
    }

    @Override
    public void p2vGetChildCommentListError(String error) {
        //ToastUtil.show(error);
        mStateLayout.showNoNetwork();
    }

    @Override
    public void p2vGetChildCommentListCompleted() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public void p2vShowNoData() {
        //需要显示头
        mStateLayout.showContent();
    }

    @Override
    public void p2vPutGiveLikeSuccess() {

    }

    @Override
    public void p2vPutGiveLikeError(String msg) {

    }

    @Override
    public void p2vPutGiveUpLikeSuccess() {

    }

    @Override
    public void p2vPutGiveUpLikeError(String msg) {

    }

    @Override
    public void p2vDeleteCommentSuccess() {

    }

    @Override
    public void p2vDeleteCommentFail(String error) {

    }

    @Override
    public void p2vGetLastComment() {
        mCommentDetailsAdapter.setNoMore(true);
        mRefreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void p2vNoGetLastComment() {
        mCommentDetailsAdapter.setNoMore(false);
        mRefreshLayout.setEnableLoadMore(true);
    }


    @OnClick({R2.id.iv_back})
    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Log.d(TAG, "onclick " + viewId);
        if(viewId == R.id.iv_back){
            pop();
        } else if(viewId == R.id.iv_more){
            //举报&删除底部弹窗
            final Bundle bundle = (Bundle) v.getTag();
            final BottomSheetDialog dialog = new BottomSheetDialog(getContext());
            dialog.setContentView(R.layout.module_notification_comment_menu);
            TextView tvComplaint = dialog.findViewById(R.id.tv_complaint);
            TextView tvDelete = dialog.findViewById(R.id.tv_delete);
            TextView tvCancle = dialog.findViewById(R.id.tv_cancel);
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int viewId = v.getId();
                    if (viewId == R.id.tv_complaint){
                        //举报
                        ComplaintDialog.newInstance(mContext,5, bundle.getLong(CommentDetailsAdapter.COMMENT_UID));
                        dialog.dismiss();
                    }else if(viewId == R.id.tv_delete){
                        //删除评论， 只能删除自己的评论
                        if(SPUtils.getInstance().getCurrentUid() == bundle.getLong(CommentDetailsAdapter.COMMENT_UID)){
                            if(bundle.getInt(CommentDetailsAdapter.COMMENT_LEVEL) == 2){
                                iCommentDetailsPresenter.v2pDeleteComment(1,bundle.getLong(CommentDetailsAdapter.COMMENT_ID));
                            }else if (bundle.getInt(CommentDetailsAdapter.COMMENT_LEVEL) == 3){
                                iCommentDetailsPresenter.v2pDeleteComment(2,bundle.getLong(CommentDetailsAdapter.COMMENT_CHILD_ID));
                            }

                        }else {
                            ToastUtil.show("无权限删除他人评论");
                        }
                        dialog.dismiss();
                    }else if (viewId == R.id.tv_cancel){
                        dialog.dismiss();
                    }
                }
            };
            tvComplaint.setOnClickListener(listener);
            tvDelete.setOnClickListener(listener);
            tvCancle.setOnClickListener(listener);
            dialog.show();
        }else if(viewId == R.id.ll_like){
            //点赞
            Bundle bundle = (Bundle) v.getTag();
            if (bundle.getInt(CommentDetailsAdapter.COMMENT_LEVEL) == 2) {
                if (bundle.getInt(CommentDetailsAdapter.IS_AGREE) == 0) {
                    bundle.putInt(CommentDetailsAdapter.IS_AGREE, 1);
                    TextView agreeCount = v.findViewById(R.id.tv_like_count);
                    agreeCount.setText(CountUtil.toHuihuCount(bundle.getInt(CommentDetailsAdapter.AGREE_COUNT) + 1));
                    bundle.putInt(CommentDetailsAdapter.AGREE_COUNT, bundle.getInt(CommentDetailsAdapter.AGREE_COUNT) + 1);
                    v.setTag(bundle);
                    ImageView like = v.findViewById(R.id.iv_like);
                    like.setImageBitmap(BitmapUtils.drawableToBitmap(getContext().getDrawable(R.drawable.module_notification_icon_like_blue)));
                    iCommentDetailsPresenter.v2pPutGiveLike(bundle.getLong(CommentDetailsAdapter.COMMENT_ID), bundle.getInt(CommentDetailsAdapter.COMMENT_LEVEL));
                } else {
                    bundle.putInt(CommentDetailsAdapter.IS_AGREE, 0);
                    TextView agreeCount = v.findViewById(R.id.tv_like_count);
                    agreeCount.setText(CountUtil.toHuihuCount(bundle.getInt(CommentDetailsAdapter.AGREE_COUNT) - 1));
                    bundle.putInt(CommentDetailsAdapter.AGREE_COUNT, bundle.getInt(CommentDetailsAdapter.AGREE_COUNT) - 1);
                    v.setTag(bundle);
                    ImageView like = v.findViewById(R.id.iv_like);
                    like.setImageBitmap(BitmapUtils.drawableToBitmap(getContext().getDrawable(R.drawable.module_notification_icon_like)));
                    iCommentDetailsPresenter.v2pPutGiveUpLike(bundle.getLong(CommentDetailsAdapter.COMMENT_ID), bundle.getInt(CommentDetailsAdapter.COMMENT_LEVEL));
                }
            } else if (bundle.getInt(CommentDetailsAdapter.COMMENT_LEVEL) == 3) {
                if (bundle.getInt(CommentDetailsAdapter.IS_AGREE) == 0) {
                    bundle.putInt(CommentDetailsAdapter.IS_AGREE, 1);
                    TextView agreeCount = v.findViewById(R.id.tv_like_count);
                    agreeCount.setText(CountUtil.toHuihuCount(bundle.getInt(CommentDetailsAdapter.AGREE_COUNT) + 1));
                    bundle.putInt(CommentDetailsAdapter.AGREE_COUNT, bundle.getInt(CommentDetailsAdapter.AGREE_COUNT) + 1);
                    v.setTag(bundle);
                    ImageView like = v.findViewById(R.id.iv_like);
                    like.setImageBitmap(BitmapUtils.drawableToBitmap(getContext().getDrawable(R.drawable.module_notification_icon_like_blue)));
                    iCommentDetailsPresenter.v2pPutGiveLike(bundle.getLong(CommentDetailsAdapter.COMMENT_CHILD_ID), bundle.getInt(CommentDetailsAdapter.COMMENT_LEVEL));
                } else {
                    bundle.putInt(CommentDetailsAdapter.IS_AGREE, 0);
                    TextView agreeCount = v.findViewById(R.id.tv_like_count);
                    agreeCount.setText(CountUtil.toHuihuCount(bundle.getInt(CommentDetailsAdapter.AGREE_COUNT) - 1));
                    bundle.putInt(CommentDetailsAdapter.AGREE_COUNT, bundle.getInt(CommentDetailsAdapter.AGREE_COUNT) - 1);
                    v.setTag(bundle);
                    ImageView like = v.findViewById(R.id.iv_like);
                    like.setImageBitmap(BitmapUtils.drawableToBitmap(getContext().getDrawable(R.drawable.module_notification_icon_like)));
                    iCommentDetailsPresenter.v2pPutGiveUpLike(bundle.getLong(CommentDetailsAdapter.COMMENT_CHILD_ID), bundle.getInt(CommentDetailsAdapter.COMMENT_LEVEL));
                }
            }
        }else if(viewId == R.id.riv_avatar || viewId == R.id.tv_nick_name || viewId == R.id.tv_replied_name){
            ToastUtil.show("用户详情");
        }else if (viewId == R.id.tv_comment_text || viewId == R.id.iv_comment_reply){
            Bundle bundle = (Bundle) v.getTag();
            if(SPUtils.getInstance().getCurrentUid() == bundle.getLong(CommentDetailsAdapter.COMMENT_UID)){
                ToastUtil.show("不能回复自己");
                return;
            }

            PublishCommentBean publishCommentBean = new PublishCommentBean();
            if (bundle.getInt(CommentDetailsAdapter.COMMENT_LEVEL) == 2) {
                //二级评论
                publishCommentBean.setCommentId(bundle.getLong(CommentDetailsAdapter.COMMENT_ID));
                publishCommentBean.setAtCommentId(bundle.getLong(CommentDetailsAdapter.COMMENT_ID));
                publishCommentBean.setAtUid(bundle.getLong(CommentDetailsAdapter.COMMENT_UID));
                publishCommentBean.setCommentIp("127.0.0.0");
                publishCommentBean.setCommentGrade(2);
                publishCommentBean.setIdeaId(mIdeaId);
                publishCommentBean.setCommentType(1);//二级评论也是评论
                CommentEditDialog.newInstance(mContext, publishCommentBean, bundle.getString(CommentDetailsAdapter.COMMENT_NICK_NAME),mPublishCommentCallBack).show();
            } else if (bundle.getInt(CommentDetailsAdapter.COMMENT_LEVEL) == 3) {
                //回复二级评论
                publishCommentBean.setCommentId(bundle.getLong(CommentDetailsAdapter.COMMENT_ID));
                publishCommentBean.setAtCommentId(bundle.getLong(CommentDetailsAdapter.COMMENT_ID));
                publishCommentBean.setAtUid(bundle.getLong(CommentDetailsAdapter.COMMENT_UID));
                publishCommentBean.setCommentIp("127.0.0.0");
                publishCommentBean.setCommentGrade(2);
                publishCommentBean.setIdeaId(mIdeaId);
                publishCommentBean.setCommentType(2);//回复
                CommentEditDialog.newInstance(mContext, publishCommentBean, bundle.getString(CommentDetailsAdapter.COMMENT_NICK_NAME),mPublishCommentCallBack).show();
            }

        }else if (viewId == R.id.cl_edit_bottom || viewId == R.id.tv_edit){
            //二级评论
            PublishCommentBean publishCommentBean = new PublishCommentBean();
            publishCommentBean.setCommentId(mCommentId);
            publishCommentBean.setAtCommentId(mCommentId);
            publishCommentBean.setAtUid(mCommentUid);
            publishCommentBean.setCommentIp("127.0.0.0");
            publishCommentBean.setCommentGrade(2);
            publishCommentBean.setIdeaId(mIdeaId);
            publishCommentBean.setCommentType(1);//二级评论也是评论
            CommentEditDialog.newInstance(mContext,publishCommentBean,null,mPublishCommentCallBack).show();
        }
    }
}
