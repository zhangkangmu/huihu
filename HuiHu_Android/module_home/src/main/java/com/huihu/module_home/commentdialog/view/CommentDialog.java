package com.huihu.module_home.commentdialog.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.BitmapUtils;
import com.huihu.uilib.util.ImgTools;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.commentchilddialog.view.CommentChildDialog;
import com.huihu.module_home.commentdialog.adapter.CommentAdapter;
import com.huihu.module_home.commentdialog.commentdialoginterface.ICommentDialogPresenter;
import com.huihu.module_home.commentdialog.commentdialoginterface.ICommentDialogView;
import com.huihu.module_home.commentdialog.entity.AnswerInfo;
import com.huihu.module_home.commentdialog.entity.CommentBean;
import com.huihu.module_home.commentdialog.entity.CommentTwo;
import com.huihu.module_home.commentdialog.presenter.ImpCommentDialogPresenter;
import com.huihu.module_home.menu.HuihuMenu;
import com.huihu.uilib.commentedit.entity.PublishCommentBean;
import com.huihu.uilib.commentedit.entity.ReturnCommentBean;
import com.huihu.uilib.commentedit.view.CommentEditDialog;
import com.huihu.uilib.complaint.view.ComplaintDialog;
import com.huihu.uilib.customize.RoundImageView;
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
 * Created by jiangwensong on 2019/3/30.
 * description：
 */
public class CommentDialog extends BaseFragment implements ICommentDialogView, View.OnClickListener {
    private static final String TAG = "CommentDialog";
    private ICommentDialogPresenter iCommentDialogPresenter = new ImpCommentDialogPresenter(this);

    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.riv_avatar)
    RoundImageView mRivAvatar;
    @BindView(R2.id.iv_avatar_mark)
    ImageView mIvAvatarMark;
    @BindView(R2.id.tv_nick_name)
    TextView mTvNickName;
    @BindView(R2.id.tv_attention)
    TextView mTvAttention;
    @BindView(R2.id.tv_reply_count)
    TextView mTvReplyCount;
    @BindView(R2.id.tv_only_author)
    TextView mTvOnlyAuthor;
    @BindView(R2.id.tv_hot)
    TextView mTvHot;
    @BindView(R2.id.iv_hot)
    ImageView mIvHot;
    @BindView(R2.id.iv_select_picture)
    ImageView mIvSelectPicture;
    @BindView(R2.id.tv_edit)
    TextView mTvEdit;
    @BindView(R2.id.tv_publish)
    TextView mTvPublish;
    @BindView(R2.id.include_bottomer)
    ConstraintLayout mClBottom;
    @BindView(R2.id.rv_comment)
    RecyclerView mRvComment;
    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private StateLayout mStateLayout;
    private CommentAdapter mRvCommentAdapter;
    private AnswerInfo mAnswerInfo;
    private CommentBean mCommentBean;
    private long mIdeaId;
    private int mOrderType = 1;
    private int mIsOnlyAuth = 0;
    private boolean mIsAttention;
    private CommentEditDialog.PublishCommentCallBack mPublishCommentCallBack;
    private int mPosition;//本地界面更新使用

    private static final String IDEA_ID = "ideaId";
    private Context mContext;
    Unbinder unbinder;


    String[] mOrderStr = new String[]{"时间正序", "热门", "时间倒序" };

    public static CommentDialog newInstance(long ideaId) {
        CommentDialog fragment = new CommentDialog();
        Bundle bundle = new Bundle();
        bundle.putLong(IDEA_ID, ideaId);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_home_view_comment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContext = getContext();
        initView();
        initData();
    }

    private void initView() {

        mIvBack.setOnClickListener(this);
        mTvAttention.setOnClickListener(this);
        mTvOnlyAuthor.setOnClickListener(this);
        mTvHot.setOnClickListener(this);
        mIvHot.setOnClickListener(this);
        mIvSelectPicture.setOnClickListener(this);
        mTvEdit.setOnClickListener(this);
        mTvPublish.setOnClickListener(this);

        mTvHot.setText(mOrderStr[mOrderType - 1]);

        //界面刷新
        mStateLayout = new StateLayout.Builder(mRvComment).setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCommentDialogPresenter.v2pGetFirstCommentList(mIdeaId, mIsOnlyAuth, mOrderType);
            }
        }).create();
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                iCommentDialogPresenter.v2pGetAnswerInfo(mIdeaId);
                iCommentDialogPresenter.v2pGetFirstCommentList(mIdeaId, mIsOnlyAuth, mOrderType);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                iCommentDialogPresenter.v2pGetMoreCommentList(mIdeaId, mIsOnlyAuth, mOrderType);
            }
        });
        MaterialHeader header = new MaterialHeader(getContext());
        header.setColorSchemeResources(R.color.global_blue);
        mRefreshLayout.setRefreshHeader(header);
        mRefreshLayout.setRefreshFooter(new MaterialFooter(getContext()));

        //发布评论回调
        mPublishCommentCallBack = new CommentEditDialog.PublishCommentCallBack() {
            @Override
            public void onPublishCommentCompleted(PublishCommentBean publicshBean, final ReturnCommentBean bean, String atNickName) {
                if (mPosition == -1) {
                    Snackbar snackbar = Snackbar.make(mRvComment, "评论成功", Snackbar.LENGTH_LONG);
                    View view = snackbar.getView();
                    view.setBackground(getContext().getResources().getDrawable(R.drawable.module_home_follow_it_bg_light_blue));
                    TextView tvSnackbarText = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tvSnackbarText.setTextColor(getContext().getResources().getColor(R.color.text_black));
                    snackbar.setAction("点击查看", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            start(CommentChildDialog.newInstance(mIdeaId, bean.getCommentId()));
                        }
                    });
                    snackbar.setDuration(Snackbar.LENGTH_LONG);
                    snackbar.show();
                } else if (mPosition >= 0) {
                    CommentTwo commentTwo = new CommentTwo();
                    commentTwo.setAgreeCount(0);
                    commentTwo.setAtNickName(atNickName);
                    commentTwo.setAtUid(publicshBean.getAtUid());
                    commentTwo.setComment(bean.getComment());
                    //TODO 缺少数据 childid
                    commentTwo.setCommentChildId(bean.getCommentId());
                    commentTwo.setCommentId(bean.getCommentId());
                    commentTwo.setCommentTime(bean.getCommentTime());//现在
                    commentTwo.setCommentType(publicshBean.getCommentType());
                    commentTwo.setIdeaAuthId(bean.getIdeaAuthId());
                    commentTwo.setImages(bean.getImages());
                    commentTwo.setIsAgree(bean.getIsAgree());
                    commentTwo.setUid(bean.getUid());
                    commentTwo.setUserInfo(bean.getUserInfo());
                    //UserInfo 拼组
                    mCommentBean.getPageDatas().get(mPosition).getCommentChildsMongodbs().add(0, commentTwo);
                    mRvCommentAdapter.setData(mCommentBean);
                    mRvCommentAdapter.notifyItemChanged(mPosition);
                }
            }
        };
    }

    private void initData() {
        mStateLayout.showLoadingData();
        mIdeaId = getArguments().getLong(IDEA_ID);
        mRvCommentAdapter = new CommentAdapter(getContext());
        //获取评论信息
        iCommentDialogPresenter.v2pGetAnswerInfo(mIdeaId);
        iCommentDialogPresenter.v2pGetFirstCommentList(mIdeaId, mIsOnlyAuth, mOrderType);
        //iCommentDialogPresenter.v2pGetCurrentUserDetails();


    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Log.d(TAG, "onclick " + viewId);
        if (viewId == R.id.iv_back) {
            pop();
        } else if (viewId == R.id.tv_attention) {
            //关注
            if (mAnswerInfo == null) return;
            if (mIsAttention) {
                iCommentDialogPresenter.v2pPutGiveFollows(mAnswerInfo.getUserInfo().getUid(), 0);
            } else {
                iCommentDialogPresenter.v2pPutGiveFollows(mAnswerInfo.getUserInfo().getUid(), 1);
            }

        } else if (viewId == R.id.tv_only_author) {
            if (mIsOnlyAuth == 0) {
                mIsOnlyAuth = 1;
                mTvOnlyAuthor.setTextColor(mContext.getResources().getColor(R.color.text_black));
                iCommentDialogPresenter.v2pGetFirstCommentList(mIdeaId, mIsOnlyAuth, mOrderType);
            } else {
                mIsOnlyAuth = 0;
                mTvOnlyAuthor.setTextColor(mContext.getResources().getColor(R.color.text_gray));
                iCommentDialogPresenter.v2pGetFirstCommentList(mIdeaId, mIsOnlyAuth, mOrderType);
            }
        } else if (viewId == R.id.tv_hot || viewId == R.id.iv_hot) {
            HuihuMenu.showMenu(getContext(), mOrderStr, mOrderType - 1, v, new HuihuMenu.OnItemClickListener() {
                @Override
                public void onClick(int position, String text) {
                    if (position == 0) {
                        //时间正序
                        mTvHot.setText(text);
                        mOrderType = 1;
                        iCommentDialogPresenter.v2pGetFirstCommentList(mIdeaId, mIsOnlyAuth, mOrderType);
                    } else if (position == 1) {
                        //热门
                        //TODO 接口不可用，除非去重
                        mTvHot.setText(text);
                        mOrderType = 2;
                        iCommentDialogPresenter.v2pGetFirstCommentList(mIdeaId, mIsOnlyAuth, mOrderType);
                    } else if (position == 2) {
                        //时间倒序
                        mTvHot.setText(text);
                        mOrderType = 3;
                        iCommentDialogPresenter.v2pGetFirstCommentList(mIdeaId, mIsOnlyAuth, mOrderType);
                    }
                }
            });
        } else if (viewId == R.id.tv_comment_text || viewId == R.id.iv_comment_reply) {
            //回复一级评论或回复二级评论
            Bundle bundle = (Bundle) v.getTag();
            mPosition = bundle.getInt(CommentAdapter.POSITION);
            if (SPUtils.getInstance().getCurrentUid() == bundle.getLong(CommentAdapter.COMMENT_UID)) {
                ToastUtil.show("不能回复自己");
                return;
            }
            PublishCommentBean publishCommentBean = new PublishCommentBean();
            if (bundle.getInt(CommentAdapter.COMMENT_LEVEL) == 2) {
                //二级评论
                publishCommentBean.setCommentId(bundle.getLong(CommentAdapter.COMMENT_ID));
                publishCommentBean.setAtCommentId(bundle.getLong(CommentAdapter.COMMENT_ID));
                publishCommentBean.setAtUid(bundle.getLong(CommentAdapter.COMMENT_UID));
                publishCommentBean.setCommentIp("127.0.0.0");
                publishCommentBean.setCommentGrade(2);
                publishCommentBean.setIdeaId(mIdeaId);
                publishCommentBean.setCommentType(1);//二级评论也是评论
                CommentEditDialog.newInstance(mContext, publishCommentBean, bundle.getString(CommentAdapter.COMMENT_NICK_NAME), mPublishCommentCallBack).show();
            } else if (bundle.getInt(CommentAdapter.COMMENT_LEVEL) == 3) {
                //回复二级评论
                publishCommentBean.setCommentId(bundle.getLong(CommentAdapter.COMMENT_ID));
                publishCommentBean.setAtCommentId(bundle.getLong(CommentAdapter.COMMENT_ID));
                publishCommentBean.setAtUid(bundle.getLong(CommentAdapter.COMMENT_UID));
                publishCommentBean.setCommentIp("127.0.0.0");
                publishCommentBean.setCommentGrade(2);
                publishCommentBean.setIdeaId(mIdeaId);
                publishCommentBean.setCommentType(2);//回复
                CommentEditDialog.newInstance(mContext, publishCommentBean, bundle.getString(CommentAdapter.COMMENT_NICK_NAME), mPublishCommentCallBack).show();
            }
        } else if (viewId == R.id.cl_edit_bottom || viewId == R.id.tv_edit) {
            mPosition = -1;
            //主评论编写
            PublishCommentBean publishCommentBean = new PublishCommentBean();
            publishCommentBean.setCommentIp("127.0.0.0");
            publishCommentBean.setCommentGrade(1);
            publishCommentBean.setIdeaId(mIdeaId);
            publishCommentBean.setCommentType(1);
            CommentEditDialog.newInstance(mContext, publishCommentBean, null, mPublishCommentCallBack).show();
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
                        ComplaintDialog.newInstance(mContext, 5, bundle.getLong(CommentAdapter.COMMENT_UID)).show();
                        dialog.dismiss();
                    } else if (viewId == R.id.tv_delete) {
                        //删除评论， 只能删除自己的评论
                        if (SPUtils.getInstance().getCurrentUid() == bundle.getLong(CommentAdapter.COMMENT_UID)) {
                            if (bundle.getInt(CommentAdapter.COMMENT_LEVEL) == 2) {
                                iCommentDialogPresenter.v2pDeleteComment(1, bundle.getLong(CommentAdapter.COMMENT_ID));
                            } else if (bundle.getInt(CommentAdapter.COMMENT_LEVEL) == 3) {
                                iCommentDialogPresenter.v2pDeleteComment(2, bundle.getLong(CommentAdapter.COMMENT_CHILD_ID));
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
            //点赞与取消点赞
            Bundle bundle = (Bundle) v.getTag();
            if (bundle.getInt(CommentAdapter.COMMENT_LEVEL) == 2) {
                if (bundle.getInt(CommentAdapter.IS_AGREE) == 0) {
                    bundle.putInt(CommentAdapter.IS_AGREE, 1);
                    TextView agreeCount = v.findViewById(R.id.tv_like_count);
                    agreeCount.setText(CountUtil.toHuihuCount(bundle.getInt(CommentAdapter.AGREE_COUNT) + 1));
                    bundle.putInt(CommentAdapter.AGREE_COUNT, bundle.getInt(CommentAdapter.AGREE_COUNT) + 1);
                    v.setTag(bundle);
                    ImageView like = v.findViewById(R.id.iv_like);
                    like.setImageBitmap(BitmapUtils.drawableToBitmap(getContext().getDrawable(R.drawable.module_home_icon_like_blue)));
                    iCommentDialogPresenter.v2pPutGiveLike(bundle.getLong(CommentAdapter.COMMENT_ID), bundle.getInt(CommentAdapter.COMMENT_LEVEL));
                } else {
                    bundle.putInt(CommentAdapter.IS_AGREE, 0);
                    TextView agreeCount = v.findViewById(R.id.tv_like_count);
                    agreeCount.setText(CountUtil.toHuihuCount(bundle.getInt(CommentAdapter.AGREE_COUNT) - 1));
                    bundle.putInt(CommentAdapter.AGREE_COUNT, bundle.getInt(CommentAdapter.AGREE_COUNT) - 1);
                    v.setTag(bundle);
                    ImageView like = v.findViewById(R.id.iv_like);
                    like.setImageBitmap(BitmapUtils.drawableToBitmap(getContext().getDrawable(R.drawable.module_home_icon_like)));
                    iCommentDialogPresenter.v2pPutGiveUpLike(bundle.getLong(CommentAdapter.COMMENT_ID), bundle.getInt(CommentAdapter.COMMENT_LEVEL));
                }
            } else if (bundle.getInt(CommentAdapter.COMMENT_LEVEL) == 3) {
                if (bundle.getInt(CommentAdapter.IS_AGREE) == 0) {
                    bundle.putInt(CommentAdapter.IS_AGREE, 1);
                    TextView agreeCount = v.findViewById(R.id.tv_like_count);
                    agreeCount.setText(CountUtil.toHuihuCount(bundle.getInt(CommentAdapter.AGREE_COUNT) + 1));
                    bundle.putInt(CommentAdapter.AGREE_COUNT, bundle.getInt(CommentAdapter.AGREE_COUNT) + 1);
                    v.setTag(bundle);
                    ImageView like = v.findViewById(R.id.iv_like);
                    like.setImageBitmap(BitmapUtils.drawableToBitmap(getContext().getDrawable(R.drawable.module_home_icon_like_blue)));
                    iCommentDialogPresenter.v2pPutGiveLike(bundle.getLong(CommentAdapter.COMMENT_CHILD_ID), bundle.getInt(CommentAdapter.COMMENT_LEVEL));
                } else {
                    bundle.putInt(CommentAdapter.IS_AGREE, 0);
                    TextView agreeCount = v.findViewById(R.id.tv_like_count);
                    agreeCount.setText(CountUtil.toHuihuCount(bundle.getInt(CommentAdapter.AGREE_COUNT) - 1));
                    bundle.putInt(CommentAdapter.AGREE_COUNT, bundle.getInt(CommentAdapter.AGREE_COUNT) - 1);
                    v.setTag(bundle);
                    ImageView like = v.findViewById(R.id.iv_like);
                    like.setImageBitmap(BitmapUtils.drawableToBitmap(getContext().getDrawable(R.drawable.module_home_icon_like)));
                    iCommentDialogPresenter.v2pPutGiveUpLike(bundle.getLong(CommentAdapter.COMMENT_CHILD_ID), bundle.getInt(CommentAdapter.COMMENT_LEVEL));
                }
            }

        } else if (viewId == R.id.ll_comment_more) {
            Bundle bundle = (Bundle) v.getTag();
            start(CommentChildDialog.newInstance(mIdeaId, bundle.getLong(CommentAdapter.COMMENT_ID)));
        } else if (viewId == R.id.iv_avatar || viewId == R.id.tv_nick_name || viewId == R.id.tv_replied_name) {
            ToastUtil.show("用户详情");
        }
    }


    @Override
    public void p2vGetCommentListError(String error) {
        mStateLayout.showNoNetwork();
    }

    @Override
    public void p2vGetCommentListCompleted() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
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
        ToastUtil.show(msg);
    }

    @Override
    public void p2vDeleteCommentSuccess() {
        ToastUtil.show("删除成功");
    }

    @Override
    public void p2vDeleteCommentFail(String error) {
        ToastUtil.show("删除评论失败");
    }

    @Override
    public void p2vGetAnswerInfoSuccess(AnswerInfo answerInfo) {
        mAnswerInfo = answerInfo;

        mTvReplyCount.setText(String.format(mContext.getResources().getString(R.string.module_home_comment_rely), CountUtil.toHuihuCount(answerInfo.getCommentCount())));
        mTvNickName.setText(answerInfo.getUserInfo().getNickName());
        ImgTools.showImageView(mContext, answerInfo.getUserInfo().getUserHeadImg_48(), mRivAvatar);

        if (!TextUtils.isEmpty(answerInfo.getUserInfo().getIncMin())) {
            mIvAvatarMark.setVisibility(View.VISIBLE);
            ImgTools.showImageView(mContext, answerInfo.getUserInfo().getIncMin(), mIvAvatarMark);
        } else {
            mIvAvatarMark.setVisibility(View.GONE);
        }

        if (answerInfo.getUserInfo().getFollow() == 0) {
            mIsAttention = false;
            mTvAttention.setText(R.string.module_home_text_attention_person);
            mTvAttention.setTextColor(mContext.getResources().getColor(R.color.global_blue));
            mTvAttention.setBackground(mContext.getResources().getDrawable(R.drawable.module_home_shape_attention_bg));
        } else {
            mIsAttention = true;
            mTvAttention.setText(R.string.module_home_text_attentioned);
            mTvAttention.setTextColor(Color.WHITE);
            mTvAttention.setBackground(mContext.getResources().getDrawable(R.drawable.module_home_shape_attentioned_bg));
        }
    }

    @Override
    public void p2vGetAnswerInfoFail(String error) {
        ToastUtil.show("获取头部信息失败");
    }

    @Override
    public void p2vPutGiveFollowsSuccess() {
        mIsAttention = !mIsAttention;
        if (mIsAttention) {
            mTvAttention.setText(R.string.module_home_text_attentioned);
            mTvAttention.setTextColor(Color.WHITE);
            mTvAttention.setBackground(mContext.getResources().getDrawable(R.drawable.module_home_shape_attentioned_bg));
        } else {
            mTvAttention.setText(R.string.module_home_text_attention_person);
            mTvAttention.setTextColor(mContext.getResources().getColor(R.color.global_blue));
            mTvAttention.setBackground(mContext.getResources().getDrawable(R.drawable.module_home_shape_attention_bg));
        }
    }

    @Override
    public void p2vPutGiveFollowsError(String error) {
        ToastUtil.show("关注失败");
    }

    @Override
    public void p2vGetFirstCommentListSuccess(CommentBean commentBean) {
        mStateLayout.showContent();

        mCommentBean = commentBean;
        mRvCommentAdapter.setData(commentBean);
        mRvCommentAdapter.notifyDataSetChanged();
        mRvCommentAdapter.setOnclickListener(this);
        mRvComment.setAdapter(mRvCommentAdapter);
        if (commentBean.getPageDatas().size() == 0) {
            mStateLayout.showEmptyData();
        }
    }

    @Override
    public void p2vGetMoreCommentListSuccess(CommentBean commentBean) {
        int oldSize = mCommentBean.getPageDatas().size();
        mCommentBean.getPageDatas().addAll(commentBean.getPageDatas());
        int newSize = mCommentBean.getPageDatas().size();
        mRvCommentAdapter.setData(mCommentBean);
        mRvCommentAdapter.notifyItemRangeChanged(oldSize, newSize);
    }

    @Override
    public void p2vGetLastComment() {
        mRefreshLayout.setEnableLoadMore(false);
        mRvCommentAdapter.setNoMore(true);
    }

    @Override
    public void p2vNoGetLastComment() {
        mRefreshLayout.setEnableLoadMore(true);
        mRvCommentAdapter.setNoMore(false);
    }

    @Override
    public void p2vShowNoData() {
        mStateLayout.showEmptyData();
    }

}
