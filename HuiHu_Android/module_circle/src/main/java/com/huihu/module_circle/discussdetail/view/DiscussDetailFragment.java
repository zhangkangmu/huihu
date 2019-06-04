package com.huihu.module_circle.discussdetail.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.MaterialFooter;
import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.commonlib.utils.BitmapUtils;
import com.huihu.uilib.util.ImgTools;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.discussdetail.adapter.CommentAdapter;
import com.huihu.module_circle.discussdetail.discussdetailinterface.IDiscussDetailPresenter;
import com.huihu.module_circle.discussdetail.discussdetailinterface.IDiscussDetailView;
import com.huihu.module_circle.discussdetail.entity.CommentBean;
import com.huihu.module_circle.discussdetail.entity.CommentTwo;
import com.huihu.module_circle.discussdetail.entity.DiscussBean;
import com.huihu.module_circle.discussdetail.presenter.ImpDiscussDetailPresenter;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.commentedit.entity.PublishCommentBean;
import com.huihu.uilib.commentedit.entity.ReturnCommentBean;
import com.huihu.uilib.commentedit.entity.UserInfoBean;
import com.huihu.uilib.commentedit.view.CommentEditDialog;
import com.huihu.uilib.complaint.view.ComplaintDialog;
import com.huihu.uilib.customize.HHShareDialog;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.menu.HuihuMenu;
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
import jp.wasabeef.richeditor.RichEditor;
import me.yokeyword.fragmentation.SupportFragment;

public class DiscussDetailFragment extends BaseFragment implements IDiscussDetailView, View.OnClickListener {
    private final static String TAG = "DiscussDetailFragment";
    private final IDiscussDetailPresenter iDiscussDetailPresenter = new ImpDiscussDetailPresenter(this);

    @BindView(R2.id.cl_head1)
    ConstraintLayout mHeader1;
    @BindView(R2.id.cl_head2)
    ConstraintLayout mHeader2;
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.cl_search)
    ConstraintLayout mClSearch;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.iv_menu_more)
    ImageView mIvMenuMore;
    @BindView(R2.id.nsv_main)
    NestedScrollView mNsvMain;
    @BindView(R2.id.tv_circle_name)
    TextView mTvCircleName;
    @BindView(R2.id.tv_discuss_title)
    TextView mTvDiscussTitle;
    @BindView(R2.id.riv_avatar)
    RoundImageView mRivAvatar;
    @BindView(R2.id.iv_avatar_mark)
    ImageView mIvAvatartMark;
    @BindView(R2.id.tv_nick_name)
    TextView mTvNickName;
    @BindView(R2.id.tv_identify)
    TextView mTvIdentify;
    @BindView(R2.id.tv_attention)
    TextView mTvAttention;
    @BindView(R2.id.iv_violation)
    ImageView mIvViolation;
    @BindView(R2.id.tv_violation)
    TextView mTvViolation;
    @BindView(R2.id.re_discuss_text)
    RichEditor mReDiscussText;

    @BindView(R2.id.cl_agree)
    ConstraintLayout mClAgree;
    @BindView(R2.id.iv_agree)
    ImageView mIvAgree;
    @BindView(R2.id.tv_agree_count)
    TextView mTvAgreeCount;
    @BindView(R2.id.cl_oppose)
    ConstraintLayout mClOppose;
    @BindView(R2.id.iv_oppose)
    ImageView mIvOppose;
    @BindView(R2.id.tv_oppose_count)
    TextView mTvOpposeCount;
    @BindView(R2.id.cl_comment)
    ConstraintLayout mClComment;
    @BindView(R2.id.iv_comment)
    ImageView mIvComment;
    @BindView(R2.id.tv_comment_count)
    TextView mTvCommentCount;
    @BindView(R2.id.iv_prohibited)
    ImageView mIvProhibited;
    @BindView(R2.id.tv_edit)
    TextView mTvEdit;
    @BindView(R2.id.srl_refresh_layout)
    SmartRefreshLayout mDiscussRefreshLayout;
    @BindView(R2.id.include_comment_header)
    ConstraintLayout mCommentHead;
    @BindView(R2.id.rv_comment_view)
    RecyclerView mCommentView;
    @BindView(R2.id.include_discuss_detail)
    ConstraintLayout mDiscussDetail;

    @BindView(R2.id.tv_reply_count)
    TextView mTvReplyCount;
    @BindView(R2.id.tv_only_author)
    TextView mTvOnlyAuthor;
    @BindView(R2.id.iv_hot)
    ImageView mIvHot;
    @BindView(R2.id.tv_hot)
    TextView mTvHot;

    Unbinder unbinder;
    private Context mContext;
    private StateLayout mStateLayout;
    private DiscussBean mDiscussBean;
    private CommentAdapter mCommentAdapter;
    private long mIdeaId;

    private long mUid;
    private int mAgreeState;
    private int mAttentionState;
    private int mViewState = 0;

    private int mOrderType = 1;
    private int mIsOnlyAuth = 0;
    private int mPosition;
    private CommentEditDialog.PublishCommentCallBack mPublishCommentCallBack;
    private CommentBean mCommentBean;

    String[] mOrderStr = new String[]{"时间正序", "热门", "时间倒序" };

    public static DiscussDetailFragment newInstance(long ideaId, long uid) {
        Bundle args = new Bundle();
        args.putLong("ideaId", ideaId);
        args.putLong("uid", uid);
        DiscussDetailFragment fragment = new DiscussDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_circle_fragment_discuss_detail, container,false);
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

    private void initView(){
        //发布评论回调
        mPublishCommentCallBack = new CommentEditDialog.PublishCommentCallBack() {
            @Override
            public void onPublishCommentCompleted(PublishCommentBean publicshBean, final ReturnCommentBean bean, String atNickName) {
                if (mPosition == -1) {
                    Snackbar snackbar = Snackbar.make(mCommentView, "评论成功", Snackbar.LENGTH_LONG);
                    View view = snackbar.getView();
                    view.setBackground(getContext().getResources().getDrawable(R.drawable.module_circle_shape_litterwhite));
                    TextView tvSnackbarText = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
                    tvSnackbarText.setTextColor(getContext().getResources().getColor(R.color.text_black));
                    snackbar.setAction("点击查看", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = (Bundle) v.getTag();
                            SupportFragment fragment=(SupportFragment)SimpleRouter.getInstance()
                                    .getFragment(RouterReDefine.COMMENT_DETAILS_FRAGMENT);
                            Bundle b = new Bundle();
                            b.putLong("ideaId",mIdeaId);
                            b.putLong("commentId",bean.getCommentId());
                            fragment.setArguments(b);
                            start(fragment);
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
                    mCommentAdapter.setData(mCommentBean);
                    mCommentAdapter.notifyItemChanged(mPosition);
                }
            }
        };

        mStateLayout = new StateLayout.Builder(mCommentView).setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).create();

        //上下拉刷新
        mDiscussRefreshLayout.setRefreshHeader(new MaterialHeader(mContext));
        mDiscussRefreshLayout.setRefreshFooter(new MaterialFooter(mContext));
        mDiscussRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                iDiscussDetailPresenter.v2pGetDiscussInfo(mIdeaId);
            }
        });
        mDiscussRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mCommentHead.setVisibility(View.VISIBLE);
                mCommentView.setVisibility(View.VISIBLE);
                iDiscussDetailPresenter.v2pGetFirstCommentList(mIdeaId,mIsOnlyAuth,mOrderType);

                int[] intArray = new int[2];
                mCommentHead.getLocationOnScreen(intArray);//测量某View相对于屏幕的距离
                mNsvMain.smoothScrollTo(intArray[0],intArray[1]);
            }
        });

        //滑动监听
        mNsvMain.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Rect scrollBounds = new Rect();
                mNsvMain.getHitRect(scrollBounds);
                if (mTvDiscussTitle.getLocalVisibleRect(scrollBounds)) {//可见
                    mClSearch.setVisibility(View.VISIBLE);
                    mTvTitle.setVisibility(View.GONE);
                }else {//完全不可见
                    mClSearch.setVisibility(View.GONE);
                    mTvTitle.setVisibility(View.VISIBLE);
                }

                //滑动置顶界面
                float h = mHeader1.getHeight() + mDiscussDetail.getHeight();
                if (scrollY >= mCommentHead.getTop()) {
                    mCommentHead.setY(h-(mCommentHead.getBottom() - scrollY ));
                }else if(oldScrollY  >= mCommentHead.getTop()){
                    //防抖动
                    mCommentHead.setY(h-(mCommentHead.getBottom() - mCommentHead.getTop() ));
                }
            }
        });

    }

    private void initData(){
        mIdeaId = getArguments().getLong("ideaId");
        mUid = getArguments().getLong("uid");

        mTvOnlyAuthor.setOnClickListener(this);
        mTvHot.setOnClickListener(this);
        mIvHot.setOnClickListener(this);

        mCommentAdapter = new CommentAdapter(mContext);
        mCommentAdapter.setOnclickListener(this);
        mCommentView.setAdapter(mCommentAdapter);

        iDiscussDetailPresenter.v2pGetDiscussInfo(mIdeaId);
    }

    private void setView(DiscussBean bean){
        UserInfoBean userInfo = bean.getUserInfo();
        mTvCircleName.setText(bean.getCircleName());
        mTvDiscussTitle.setText(bean.getTitle());
        mTvTitle.setText(bean.getTitle());
        ImgTools.showImageView(mContext,userInfo.getUserHeadImage(),mRivAvatar);
        ImgTools.showImageView(mContext,userInfo.getIncMin(),mIvAvatartMark);
        mTvNickName.setText(userInfo.getNickName());
        mTvIdentify.setText(userInfo.getAuthBewrite());
        setAttention(userInfo.getFollow() == 1);
        setDiscussState(bean.getState());
        mReDiscussText.setHtml(bean.getContent());
        setViewPoint(bean.getViewpoint());
        mTvAgreeCount.setText(CountUtil.toHuihuCount(bean.getAgreeCount()));
        mTvOpposeCount.setText(CountUtil.toHuihuCount(bean.getOpposeCount()));
        mTvCommentCount.setText(CountUtil.toHuihuCount(bean.getCommentCount()));

        mTvReplyCount.setText(String.format("%s", CountUtil.toHuihuCount(bean.getCommentCount()) ));
        //TODO 禁言

        mAttentionState = userInfo.getFollow();
        mAgreeState = bean.getViewpoint();
        mUid = userInfo.getUid();
    }

    //观点状态(0：默认 1:点赞 2：踩)
    private void setViewPoint(int att){
        if(att == 0){
            mTvAgreeCount.setSelected(false);
            mIvAgree.setImageDrawable(mContext.getResources().getDrawable(R.drawable.module_circle_icon_like));
            mIvOppose.setImageDrawable(mContext.getResources().getDrawable(R.drawable.module_circle_icon_down));
            mTvOpposeCount.setSelected(false);
        }else if(att == 1){
            mTvAgreeCount.setSelected(true);
            mIvAgree.setImageDrawable(mContext.getResources().getDrawable(R.drawable.module_circle_icon_like_blue));
            mIvOppose.setImageDrawable(mContext.getResources().getDrawable(R.drawable.module_circle_icon_down));
            mTvOpposeCount.setSelected(false);
        }else if(att == 2){
            mTvAgreeCount.setSelected(false);
            mIvAgree.setImageDrawable(mContext.getResources().getDrawable(R.drawable.module_circle_icon_like));
            mIvOppose.setImageDrawable(mContext.getResources().getDrawable(R.drawable.module_circle_icon_down_blue));
            mTvOpposeCount.setSelected(true);
        }
    }

    private void setAttention(boolean b){
        if (b){
            mTvAttention.setText(R.string.module_circle_attentioned);
            mTvAttention.setTextColor(mContext.getResources().getColor(R.color.global_blue));
            mTvAttention.setBackground(mContext.getResources().getDrawable(R.drawable.module_circle_shape_litterwhite));
        }else {
            mTvAttention.setText(R.string.module_circle_attention);
            mTvAttention.setTextColor(Color.WHITE);
            mTvAttention.setBackground(mContext.getResources().getDrawable(R.drawable.module_circle_shape_blue));
        }

    }
    //1:发布 2:违规下架 3：重新审核中
    private void setDiscussState(int state){
        if (state == 1){
            mTvAttention.setVisibility(View.VISIBLE);
            mIvViolation.setVisibility(View.GONE);
            mTvViolation.setVisibility(View.GONE);
        }else if (state == 2){
            mTvViolation.setText("违规下架");
            mTvAttention.setVisibility(View.GONE);
            mIvViolation.setVisibility(View.VISIBLE);
            mTvViolation.setVisibility(View.VISIBLE);
        }else if (state == 3){
            mTvViolation.setText("重新审核中");
            mTvAttention.setVisibility(View.GONE);
            mIvViolation.setVisibility(View.VISIBLE);
            mTvViolation.setVisibility(View.VISIBLE);
        }
    }

    @OnClick({R2.id.iv_back,R2.id.cl_search,R2.id.iv_menu_more,R2.id.riv_avatar,R2.id.cl_agree,R2.id.cl_oppose,R2.id.cl_comment,R2.id.tv_edit,R2.id.tv_attention,R2.id.tv_hot,R2.id.iv_hot,R2.id.tv_only_author})
    @Override
    public void onClick(View v){
        int viewId = v.getId();
        if (viewId == R.id.iv_back){
            pop();
        }else if(viewId == R.id.cl_search){

        }else if(viewId == R.id.iv_menu_more){
            //TODO more select
            new HHShareDialog(mContext).showDialog();
        }else if(viewId == R.id.riv_avatar){

        }else if(viewId == R.id.cl_agree){
            //三状态点击
            if (mAgreeState == 0){
                mAgreeState = 1;
                setViewPoint(1);
                mDiscussBean.setAgreeCount(mDiscussBean.getAgreeCount() + 1);
                mTvAgreeCount.setText(CountUtil.toHuihuCount(mDiscussBean.getAgreeCount()));
                iDiscussDetailPresenter.v2pPutGiveLike(mIdeaId,1,1);
            }else if(mAgreeState == 1) {
                mAgreeState = 0;
                setViewPoint(0);
                mDiscussBean.setAgreeCount(mDiscussBean.getAgreeCount() - 1);
                mTvAgreeCount.setText(CountUtil.toHuihuCount(mDiscussBean.getAgreeCount()));
                iDiscussDetailPresenter.v2pPutGiveLike(mIdeaId,0,1);
            }else if (mAgreeState == 2){
                mAgreeState = 1;
                setViewPoint(1);
                mDiscussBean.setAgreeCount(mDiscussBean.getAgreeCount() + 1);
                mTvAgreeCount.setText(CountUtil.toHuihuCount(mDiscussBean.getAgreeCount()));
                mDiscussBean.setOpposeCount(mDiscussBean.getOpposeCount() - 1);
                mTvOpposeCount.setText(CountUtil.toHuihuCount(mDiscussBean.getOpposeCount()));
                iDiscussDetailPresenter.v2pPutGiveLike(mIdeaId,1,1);
            }
        }else if(viewId == R.id.cl_oppose){
            //三状态点击
            if (mAgreeState == 0){
                mAgreeState = 2;
                setViewPoint(2);
                mDiscussBean.setOpposeCount(mDiscussBean.getOpposeCount() + 1);
                mTvOpposeCount.setText(CountUtil.toHuihuCount(mDiscussBean.getOpposeCount()));
                iDiscussDetailPresenter.v2pPutGiveLike(mIdeaId,2,1);
            }else if (mAgreeState == 1){
                mAgreeState = 2;
                setViewPoint(2);
                mDiscussBean.setOpposeCount(mDiscussBean.getOpposeCount() + 1);
                mTvOpposeCount.setText(CountUtil.toHuihuCount(mDiscussBean.getOpposeCount()));
                mDiscussBean.setAgreeCount(mDiscussBean.getAgreeCount() - 1);
                mTvAgreeCount.setText(CountUtil.toHuihuCount(mDiscussBean.getAgreeCount()));
                iDiscussDetailPresenter.v2pPutGiveLike(mIdeaId,2,1);
            }else if (mAgreeState == 2){
                mAgreeState = 0;
                setViewPoint(0);
                mDiscussBean.setOpposeCount(mDiscussBean.getOpposeCount() - 1);
                mTvOpposeCount.setText(CountUtil.toHuihuCount(mDiscussBean.getOpposeCount()));
                iDiscussDetailPresenter.v2pPutGiveLike(mIdeaId,0,1);
            }
        }else if(viewId == R.id.cl_comment){
            //评论跳转
//            SupportFragment fragment = (SupportFragment) SimpleRouter.getInstance()
//                    .getFragment(RouterReDefine.COMMENT_FRAGMENT);
//            Bundle b = new Bundle();
//            b.putLong("ideaId", mIdeaId);
//            //b.putLong("commentId", bundle.getLong(OtherCommentMeAdapter.FIND_COMMENT_ID));
//            fragment.setArguments(b);
//            start(fragment);

        }else if(viewId == R.id.tv_edit){
            //主评论编写
            PublishCommentBean publishCommentBean = new PublishCommentBean();
            publishCommentBean.setCommentIp("127.0.0.0");
            publishCommentBean.setCommentGrade(1);
            publishCommentBean.setIdeaId(mIdeaId);
            publishCommentBean.setCommentType(1);
            CommentEditDialog.newInstance(mContext,publishCommentBean, null,null).show();
        }else if(viewId == R.id.tv_attention){
            if(mAttentionState == 0){
                iDiscussDetailPresenter.p2vGiveFollows(mUid,1);
                setAttention(true);
                mAttentionState = 1;
            }else {
                iDiscussDetailPresenter.p2vGiveFollows(mUid,0);
                setAttention(false);
                mAttentionState = 0;
            }
        }else if (viewId == R.id.tv_only_author) {
            if (mIsOnlyAuth == 0) {
                mIsOnlyAuth = 1;
                mTvOnlyAuthor.setTextColor(mContext.getResources().getColor(R.color.text_black));
                iDiscussDetailPresenter.v2pGetFirstCommentList(mIdeaId, mIsOnlyAuth, mOrderType);
            } else {
                mIsOnlyAuth = 0;
                mTvOnlyAuthor.setTextColor(mContext.getResources().getColor(R.color.text_gray));
                iDiscussDetailPresenter.v2pGetFirstCommentList(mIdeaId, mIsOnlyAuth, mOrderType);
            }
        } else if (viewId == R.id.tv_hot || viewId == R.id.iv_hot) {
            HuihuMenu.showMenu(getContext(), mOrderStr, mOrderType - 1, v, new HuihuMenu.OnItemClickListener() {
                @Override
                public void onClick(int position, String text) {
                    if (position == 0) {
                        //时间正序
                        mTvHot.setText(text);
                        mOrderType = 1;
                        iDiscussDetailPresenter.v2pGetFirstCommentList(mIdeaId, mIsOnlyAuth, mOrderType);
                    } else if (position == 1) {
                        //热门
                        //TODO 接口不可用，除非去重
                        mTvHot.setText(text);
                        mOrderType = 2;
                        iDiscussDetailPresenter.v2pGetFirstCommentList(mIdeaId, mIsOnlyAuth, mOrderType);
                    } else if (position == 2) {
                        //时间倒序
                        mTvHot.setText(text);
                        mOrderType = 3;
                        iDiscussDetailPresenter.v2pGetFirstCommentList(mIdeaId, mIsOnlyAuth, mOrderType);
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
            dialog.setContentView(R.layout.module_circle_comment_dialog_more);
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
                                iDiscussDetailPresenter.v2pDeleteComment(1, bundle.getLong(CommentAdapter.COMMENT_ID));
                            } else if (bundle.getInt(CommentAdapter.COMMENT_LEVEL) == 3) {
                                iDiscussDetailPresenter.v2pDeleteComment(2, bundle.getLong(CommentAdapter.COMMENT_CHILD_ID));
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
                    like.setImageBitmap(BitmapUtils.drawableToBitmap(getContext().getDrawable(R.drawable.module_circle_icon_like_blue)));
                    iDiscussDetailPresenter.v2pPutGiveLike(bundle.getLong(CommentAdapter.COMMENT_ID), 1,bundle.getInt(CommentAdapter.COMMENT_LEVEL));
                } else {
                    bundle.putInt(CommentAdapter.IS_AGREE, 0);
                    TextView agreeCount = v.findViewById(R.id.tv_like_count);
                    agreeCount.setText(CountUtil.toHuihuCount(bundle.getInt(CommentAdapter.AGREE_COUNT) - 1));
                    bundle.putInt(CommentAdapter.AGREE_COUNT, bundle.getInt(CommentAdapter.AGREE_COUNT) - 1);
                    v.setTag(bundle);
                    ImageView like = v.findViewById(R.id.iv_like);
                    like.setImageBitmap(BitmapUtils.drawableToBitmap(getContext().getDrawable(R.drawable.module_circle_icon_like)));
                    iDiscussDetailPresenter.v2pPutGiveLike(bundle.getLong(CommentAdapter.COMMENT_ID), 0,bundle.getInt(CommentAdapter.COMMENT_LEVEL));
                }
            } else if (bundle.getInt(CommentAdapter.COMMENT_LEVEL) == 3) {
                if (bundle.getInt(CommentAdapter.IS_AGREE) == 0) {
                    bundle.putInt(CommentAdapter.IS_AGREE, 1);
                    TextView agreeCount = v.findViewById(R.id.tv_like_count);
                    agreeCount.setText(CountUtil.toHuihuCount(bundle.getInt(CommentAdapter.AGREE_COUNT) + 1));
                    bundle.putInt(CommentAdapter.AGREE_COUNT, bundle.getInt(CommentAdapter.AGREE_COUNT) + 1);
                    v.setTag(bundle);
                    ImageView like = v.findViewById(R.id.iv_like);
                    like.setImageBitmap(BitmapUtils.drawableToBitmap(getContext().getDrawable(R.drawable.module_circle_icon_like_blue)));
                    iDiscussDetailPresenter.v2pPutGiveLike(bundle.getLong(CommentAdapter.COMMENT_CHILD_ID),1, bundle.getInt(CommentAdapter.COMMENT_LEVEL));
                } else {
                    bundle.putInt(CommentAdapter.IS_AGREE, 0);
                    TextView agreeCount = v.findViewById(R.id.tv_like_count);
                    agreeCount.setText(CountUtil.toHuihuCount(bundle.getInt(CommentAdapter.AGREE_COUNT) - 1));
                    bundle.putInt(CommentAdapter.AGREE_COUNT, bundle.getInt(CommentAdapter.AGREE_COUNT) - 1);
                    v.setTag(bundle);
                    ImageView like = v.findViewById(R.id.iv_like);
                    like.setImageBitmap(BitmapUtils.drawableToBitmap(getContext().getDrawable(R.drawable.module_circle_icon_like)));
                    iDiscussDetailPresenter.v2pPutGiveLike(bundle.getLong(CommentAdapter.COMMENT_CHILD_ID),0, bundle.getInt(CommentAdapter.COMMENT_LEVEL));
                }
            }

        } else if (viewId == R.id.ll_comment_more) {
            Bundle bundle = (Bundle) v.getTag();
            SupportFragment fragment=(SupportFragment)SimpleRouter.getInstance()
                    .getFragment(RouterReDefine.COMMENT_DETAILS_FRAGMENT);
            Bundle b = new Bundle();
            b.putLong("ideaId",mIdeaId);
            b.putLong("commentId",bundle.getLong(CommentAdapter.COMMENT_ID));
            fragment.setArguments(b);
            start(fragment);
        }
    }


    @Override
    public void p2vGetDiscussInfoSuccess(DiscussBean bean) {
        mDiscussRefreshLayout.finishRefresh();
        mDiscussBean = bean;
        setView(bean);
    }

    @Override
    public void p2vGetDiscussInfoError(String error) {
        ToastUtil.show(error);
    }

    @Override
    public void p2vPutGiveLikeSuccess(int type) {
    }

    @Override
    public void p2vPutGiveLikeError(String error) {
        ToastUtil.show(error);
    }

    @Override
    public void p2vGiveFollowsSuccess(int state) {
        setAttention(state == 1);
    }

    @Override
    public void p2vGiveFollowsError(String error) {
        ToastUtil.show(error);
    }

    @Override
    public void p2vShowNoData() {
        mStateLayout.showEmptyData();
    }

    @Override
    public void p2vGetFirstCommentListSuccess(CommentBean commentBean) {
        mStateLayout.showContent();

        mCommentBean = commentBean;
        mCommentAdapter.setData(commentBean);
        mCommentAdapter.notifyDataSetChanged();
        mCommentAdapter.setOnclickListener(this);
        mCommentView.setAdapter(mCommentAdapter);
        if (commentBean.getPageDatas().size() == 0) {
            mStateLayout.showEmptyData();
        }
    }

    @Override
    public void p2vGetMoreCommentListSuccess(CommentBean commentBean) {
        int oldSize = mCommentBean.getPageDatas().size();
        mCommentBean.getPageDatas().addAll(commentBean.getPageDatas());
        int newSize = mCommentBean.getPageDatas().size();
        mCommentAdapter.setData(mCommentBean);
        mCommentAdapter.notifyItemRangeChanged(oldSize, newSize);
    }

    @Override
    public void p2vGetCommentListError(String error) {
        mStateLayout.showNoNetwork();
    }

    @Override
    public void p2vGetCommentListCompleted() {
        mDiscussRefreshLayout.finishRefresh();
        mDiscussRefreshLayout.finishLoadMore();
    }

    @Override
    public void p2vGetLastComment() {
        mDiscussRefreshLayout.setEnableLoadMore(false);
        mCommentAdapter.setNoMore(true);
    }

    @Override
    public void p2vNoGetLastComment() {
        mDiscussRefreshLayout.setEnableLoadMore(true);
        mCommentAdapter.setNoMore(false);
    }

}
