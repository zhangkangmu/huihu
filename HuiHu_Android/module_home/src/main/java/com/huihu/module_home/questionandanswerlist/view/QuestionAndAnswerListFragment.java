package com.huihu.module_home.questionandanswerlist.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihu.commonlib.MaterialFooter;
import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouterClassRegister;
import com.huihu.commonlib.simpleRouter.SimpleRouterObj;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.StatusBarUtil;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.answerdetail.view.fragment.AnswerDetailFragment;
import com.huihu.module_home.inviteanswer.view.InviteAnswerFragment;
import com.huihu.module_home.menu.HuihuMenu;
import com.huihu.module_home.questionandanswerlist.entity.AnswerModel;
import com.huihu.module_home.questionandanswerlist.entity.FollowState;
import com.huihu.module_home.questionandanswerlist.entity.GetAnswerInfoParamModel;
import com.huihu.module_home.questionandanswerlist.entity.Order;
import com.huihu.module_home.questionandanswerlist.entity.QuestionDetailModel;
import com.huihu.module_home.questionandanswerlist.entity.QuestionStatus;
import com.huihu.module_home.questionandanswerlist.entity.State;
import com.huihu.module_home.questionandanswerlist.presenter.ImpQuestionAndAnswerListPresenter;
import com.huihu.module_home.questionandanswerlist.questionandanswerlistinterface.IQuestionAndAnswerListPresenter;
import com.huihu.module_home.questionandanswerlist.questionandanswerlistinterface.IQuestionAndAnswerListView;
import com.huihu.module_home.questionandanswerlist.view.adapter.AnswerAdapter;
import com.huihu.module_home.writeanswer.view.WriteAnswerFragment;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.checklogin.annotation.CheckLogin;
import com.huihu.uilib.complaint.view.ComplaintDialog;
import com.huihu.uilib.customize.HHShareDialog;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.def.FollowType;
import com.huihu.uilib.statelayout.StateLayout;
import com.huihu.uilib.util.DensityUtil;
import com.huihu.uilib.util.ImgTools;
import com.huihu.uilib.util.TimeFormatUtils;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.richeditor.RichEditor;

import static org.xutils.common.util.DensityUtil.getScreenWidth;


@SimpleRouterClassRegister(key = RouterReDefine.QUESTIONDETAIL_FRAGMENT, type = SimpleRouterObj.FRAGMENT)
public class QuestionAndAnswerListFragment extends BaseFragment implements IQuestionAndAnswerListView, AnswerAdapter.OnMyItemClickListener {
    private final IQuestionAndAnswerListPresenter iQuestionAndAnswerListPresenter = new ImpQuestionAndAnswerListPresenter(this);
    Unbinder unbinder;
    @BindView(R2.id.v_richeditor)
    RichEditor mRichEditor;
    @BindView(R2.id.v_gradient)
    View mVGradient;
    @BindView(R2.id.cl_expand)
    ConstraintLayout mClExpand;
    @BindView(R2.id.ll_switch_sort)
    LinearLayout mLlSwitchSort;
    @BindView(R2.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R2.id.rv_answer)
    RecyclerView mRvAnswer;
    @BindView(R2.id.ll_category_container)
    LinearLayout mLlCategoryContainer;
    @BindView(R2.id.iv_questioner_avator)
    RoundImageView mIvQuestionerAvator;
    @BindView(R2.id.tv_questioner_name)
    TextView mTvQuestionerName;
    @BindView(R2.id.tv_question_title)
    TextView mTvQuestionTitle;
    @BindView(R2.id.tv_answer_count)
    TextView mTvAnswerCount;
    @BindView(R2.id.tv_attention_count)
    TextView mTvAttentionCount;
    @BindView(R2.id.tv_status)
    TextView mTvStatus;
    @BindView(R2.id.iv_icon_status)
    ImageView mIvIconStatus;
    @BindView(R2.id.v_line)
    View mVLine;
    @BindView(R2.id.nest_scrollview)
    NestedScrollView mNestScrollview;
    @BindView(R2.id.panel_float)
    LinearLayout mPanelFloat;
    @BindView(R2.id.cl_answer_top)
    ConstraintLayout mClAnswerTop;
    @BindView(R2.id.cl_state_violation)
    ConstraintLayout mClStateViolation;
    @BindView(R2.id.iv_answer)
    ImageView mIvAnswer;
    @BindView(R2.id.tv_answer)
    TextView mTvAnswer;
    @BindView(R2.id.cl_answer)
    ConstraintLayout mClAnswer;
    @BindView(R2.id.tv_invitation_answer)
    TextView mTvInvitationAnswer;

    //浮窗的回答和关注
    @BindView(R2.id.tv_panel_answer)
    TextView mTvPanelAnswer;
    @BindView(R2.id.cl_panel_answer)
    ConstraintLayout mClPanelAnswer;
    @BindView(R2.id.iv_attention)
    ImageView mIvAttention;
    @BindView(R2.id.tv_attention)
    TextView mTvAttention;
    @BindView(R2.id.iv_panel_answer)
    ImageView mIvPanelAnswer;
    @BindView(R2.id.tv_order)
    TextView mTvOrder;
    @BindView(R2.id.tv_footer)
    TextView mTvFooter;
    @BindView(R2.id.tv_question_title_center)
    TextView mTvQuestionTitleCenter;
    @BindView(R2.id.cl_search)
    ConstraintLayout mClSearch;

    private AnswerAdapter mAdapter;
    private int mWebviewRealHeight;
    List<Integer> mheightList = new ArrayList<>();
    //问题名，去写回答页面需要的参数
    private String mQuestionTitle;
    //当前的ideaId
    private long mQuestionId;
    //当前的排序方式,默认热门排序
    private int mOrderBy = Order.ORDER_PRAISE;
    //是否已回答
    private Integer mAnswer;
    //是否已关注
    private int mFollowed;
    //关注数
    private int mFollowCount;
    //RequestCode
    private static final int REQUEST_CODE = 0;

    private StateLayout mStateLayout;
    private GetAnswerInfoParamModel mAnswerInfoParamModel = new GetAnswerInfoParamModel();
    private List<AnswerModel.PageDatasBean> mAnswerList;
    private int mQuestionUid;
    private int mQuestionState;


    public static QuestionAndAnswerListFragment newInstance(long ideaId) {
        QuestionAndAnswerListFragment fragment = new QuestionAndAnswerListFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("questionId", ideaId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_home_fragment_question_answer_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            mQuestionId = bundle.getLong("questionId");
            //获取提问详情
            getQuestionInfo();
            //获取回答列表
            getAnswerList();
        }
    }

    private void getAnswerList() {
        mAnswerInfoParamModel.setOrderBy(mOrderBy);
        mAnswerInfoParamModel.setUid(SPUtils.getInstance().getCurrentUid());
        mAnswerInfoParamModel.setQuestionId(mQuestionId);
        iQuestionAndAnswerListPresenter.v2pGetAnswerList(mAnswerInfoParamModel);
    }

    private void getQuestionInfo() {
        iQuestionAndAnswerListPresenter.v2pGetQuestionInfo(mQuestionId);
    }

    private void initView() {
        //初始化状态
        initState();
        //滚动监听
        initNestScrollView();
        //refresh设置
        initRefreshLayout();
        //recyclerview初始化
        initRecyclerView();
        //编辑器初始化
        initRichEditor();
        //设置需要加粗的字体
        initFakeBold();
    }

    private void initState() {
        mStateLayout = new StateLayout.Builder(mRefreshLayout).setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        }).create();
        mStateLayout.showLoadingData();
    }

    private void initFakeBold() {
        //关注问题字体
        TextViewUtils.setTextFakeBold(mTvStatus);
        //邀请回答字体
        TextViewUtils.setTextFakeBold(mTvInvitationAnswer);
        //回答字体
        TextViewUtils.setTextFakeBold(mTvAnswer);
        //总回答数
        TextViewUtils.setTextFakeBold(mTvAnswerCount);
        //滚动时出现的标题
        TextViewUtils.setTextFakeBold(mTvQuestionTitleCenter);
    }

    private void initNestScrollView() {
        mNestScrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView view, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int[] titleLocation = new int[2];
                int[] lineLocation = new int[2];
                mTvQuestionTitle.getLocationOnScreen(titleLocation);
                mVLine.getLocationOnScreen(lineLocation);

                int lineLocal = lineLocation[1] - DensityUtil.dip2px(_mActivity, 44) - StatusBarUtil.getStatusBarHeight(_mActivity) + mVLine.getHeight();
                int titleLocal = titleLocation[1] - DensityUtil.dip2px(_mActivity, 44) - StatusBarUtil.getStatusBarHeight(_mActivity) + mTvQuestionTitle.getHeight();

                mPanelFloat.setVisibility(lineLocal < 0 ? View.VISIBLE : View.GONE);
                mTvQuestionTitleCenter.setAlpha(titleLocal < 0 ? 1 : 0);
                mClSearch.setVisibility(titleLocal < 0 ? View.INVISIBLE : View.VISIBLE);
            }
        });

    }

    private void initRichEditor() {
        mRichEditor.setEnabled(false);
        mRichEditor.setPageFinishListener(new RichEditor.OnPageFinishListener() {
            @Override
            public void onPageFinish() {
                setContentHeight();
            }
        });
    }

    private void setContentHeight() {
        int maxHeightPx = DensityUtil.dip2px(_mActivity, 214);//设计稿给出的超过就要收起的高度值

        mheightList.add(mRichEditor.getHeight());
        mWebviewRealHeight = Collections.max(mheightList);

        if (mWebviewRealHeight >= maxHeightPx) {
            ViewGroup.LayoutParams params = mRichEditor.getLayoutParams();
            params.height = maxHeightPx;
            mRichEditor.setLayoutParams(params);

            mVGradient.setVisibility(View.VISIBLE);
            mClExpand.setVisibility(View.VISIBLE);
        } else {
            mVGradient.setVisibility(View.GONE);
            mClExpand.setVisibility(View.GONE);
        }
    }

    private void initRecyclerView() {
        mRvAnswer.setNestedScrollingEnabled(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        mAdapter = new AnswerAdapter(_mActivity, iQuestionAndAnswerListPresenter);
        mAdapter.setScreenWidth(getScreenWidth());
        mRvAnswer.setLayoutManager(layoutManager);
        mRvAnswer.setAdapter(mAdapter);

        mAdapter.setListener(this);
    }

    private void initRefreshLayout() {
        //原生下拉刷新样式
        MaterialHeader header = new MaterialHeader(_mActivity);
        header.setSize(40);
        header.setColorSchemeColors(getResources().getColor(R.color.module_home_blue));
        mRefreshLayout.setRefreshHeader(header);
        //仿知乎上拉加载样式
        MaterialFooter footer = new MaterialFooter(_mActivity);
        mRefreshLayout.setRefreshFooter(footer);
        //允许上拉刷新和下拉加载
        mRefreshLayout.setEnableRefresh(true);
        mRefreshLayout.setEnableLoadMore(true);
        //refresh最大拖动距离，和footer等距，不可越界拖动
        mRefreshLayout.setFooterMaxDragRate(1);
        //刷新和加载监听
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mAdapter.removeAllData();
                initData();
            }
        });

        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (mOrderBy == Order.ORDER_PRAISE) {
                    mAnswerInfoParamModel.setLastTime(mAdapter.getLastAgreeCount());
                } else {
                    mAnswerInfoParamModel.setLastTime(mAdapter.getLastTime());
                }
                getAnswerList();
            }
        });
    }

    @OnClick(R2.id.iv_back)
    public void onBack() {
        pop();
    }

    @OnClick(R2.id.cl_expand)
    public void onExpand() {
        mRichEditor.setExpand();

        ViewGroup.LayoutParams params = mRichEditor.getLayoutParams();
        params.height = mWebviewRealHeight;
        mRichEditor.setLayoutParams(params);

        mVGradient.setVisibility(View.GONE);
        mClExpand.setVisibility(View.GONE);
    }

    @OnClick(R2.id.ll_switch_sort)
    public void onSwitchSort() {
        int selectPosition = mOrderBy == Order.ORDER_PRAISE ? 0 : 1;
        HuihuMenu.showMenu(_mActivity, getResources().getStringArray(R.array.module_home_sort), selectPosition
                , mLlSwitchSort, new HuihuMenu.OnItemClickListener() {
                    @Override
                    public void onClick(int position, String text) {
                        switch (position) {
                            case 0:
                                //热门排序
                                mOrderBy = Order.ORDER_PRAISE;
                                break;
                            case 1:
                                //时间倒序
                                mOrderBy = Order.ODEER_TIME;
                                break;
                        }
                        getAnswerList();
                    }
                });
    }

    @Override
    public void p2vShowQuestionDetail(QuestionDetailModel model) {
        //是否已回答
        mAnswer = model.getAnswer();
        //是否已关注
        mFollowed = model.getFollow();
        //关注数
        mFollowCount = model.getFollowCount();
        //提问者的uid
        mQuestionUid = model.getUserInfo().getUid();
        //问题的state
        mQuestionState = model.getState();
        //问题所属标签设置
        setCategoryTag(model.getCategoryMongos());
        //提问者头像和昵称和最后编辑时间
        ImgTools.showImageView(_mActivity, model.getUserInfo().getUserHeadImage(), mIvQuestionerAvator);
        mTvQuestionerName.setText(String.format(getResources().getString(R.string.module_home_text_withparam_question_naem), model.getUserInfo().getNickName(), TimeFormatUtils.toHuihuTime(model.getEditTime())));
        //问题标题设置
        mQuestionTitle = model.getTitle();
        mTvQuestionTitle.setText(mQuestionTitle);
        mTvQuestionTitleCenter.setText(mQuestionTitle);
        //问题详情
        setQusetionContent(model.getContent());
        //问题关注数
        setFollowCount(mFollowCount);
        //问题是否已关注和违规设置
        setQuestionStatus(mQuestionState);
    }

    private void setFollowCount(int followCount) {
        mTvAttentionCount.setText(String.format(getResources().getString(R.string.module_home_text_attention_count), followCount));
    }

    private void setQuestionStatus(int state) {
        switch (state) {
            case QuestionStatus.release:
                if (mFollowed == FollowState.unFollow) {
                    //未关注
                    setUnFollowState();
                } else {
                    //已关注
                    setFollowState();
                }
                break;
            case QuestionStatus.violation:
                mIvIconStatus.setImageResource(R.drawable.module_home_icon_violation);
                mTvStatus.setText(getResources().getString(R.string.module_home_text_violation));
                mTvStatus.setTextColor(getResources().getColor(R.color.module_home_warning));
                mClAnswerTop.setVisibility(View.GONE);
                mRvAnswer.setVisibility(View.GONE);
                mClStateViolation.setVisibility(View.VISIBLE);
                break;
            case QuestionStatus.review:
                mIvIconStatus.setImageResource(R.drawable.module_home_icon_review);
                mTvStatus.setText(getResources().getString(R.string.module_home_text_review));
                mTvStatus.setTextColor(getResources().getColor(R.color.module_home_review));
                break;
        }

        if (mAnswer == null) {
            //自己没有回答过
            mClAnswer.setBackgroundResource(R.drawable.module_home_shape_add_bg_4);
            mTvAnswer.setText(R.string.module_home_text_answer);
            mTvAnswer.setTextColor(getResources().getColor(R.color.module_home_white));
            mIvAnswer.setImageResource(R.drawable.module_home_icon_answer);

            mTvPanelAnswer.setText(R.string.module_home_text_invitation_answer_question);
            mIvPanelAnswer.setImageResource(R.drawable.module_home_icon_answer);
        } else {
            //回答过了
            mClAnswer.setBackgroundResource(R.drawable.module_home_shape_invite_answer);
            mTvAnswer.setText(R.string.module_home_text_my_answer);
            mTvAnswer.setTextColor(getResources().getColor(R.color.module_home_blue));
            mIvAnswer.setImageResource(R.drawable.module_home_icon_answered);

            mTvPanelAnswer.setText(R.string.module_home_text_my_answer);
            mIvPanelAnswer.setImageResource(R.drawable.module_home_icon_answered_gray);
        }
    }

    private void setFollowState() {
        mIvIconStatus.setVisibility(View.GONE);
        mTvStatus.setText(getResources().getString(R.string.module_home_text_attentioned_question));
        mTvStatus.setTextColor(getResources().getColor(R.color.module_home_gray_three));

        mIvAttention.setVisibility(View.GONE);
        mTvAttention.setText(getResources().getString(R.string.module_home_text_attentioned_question));
    }

    private void setUnFollowState() {
        mTvStatus.setText(getResources().getString(R.string.module_home_text_attention_question));
        mTvStatus.setTextColor(getResources().getColor(R.color.module_home_blue));
        mIvIconStatus.setVisibility(View.VISIBLE);

        mIvAttention.setVisibility(View.VISIBLE);
        mTvAttention.setText(getResources().getString(R.string.module_home_text_attention_question));
    }

    @Override
    public void p2vShowAnswer(List<AnswerModel.PageDatasBean> list) {
        mAnswerList = list;
        mTvOrder.setText(mOrderBy == Order.ORDER_PRAISE ? "热门" : "时间倒序");
        mAdapter.setData(list);
        mAdapter.notifyDataSetChanged();
        mTvAnswerCount.setText(String.format(getResources().getString(R.string.module_home_text_answer_count), mAdapter.getData().size()));

        if (list.size() < 20) {
            mRefreshLayout.setEnableLoadMore(false);
            mTvFooter.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void p2vShowToast(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void setButtonState() {
        if (mFollowed == FollowState.unFollow) {
            //未关注改成已关注
            setFollowState();
            mFollowed = FollowState.followed;
            setFollowCount(++mFollowCount);
        } else {
            //已关注改成未关注
            setUnFollowState();
            mFollowed = FollowState.unFollow;
            setFollowCount(--mFollowCount);
        }
    }

    @Override
    public void p2vCheckHasData() {
        List<AnswerModel.PageDatasBean> data = mAdapter.getData();
        if (data == null || data.isEmpty()) {
            mStateLayout.showNoNetwork();
        }
    }

    @Override
    public void p2vRefreshFinish() {
        mRefreshLayout.finishRefresh();
        mRefreshLayout.finishLoadMore();
    }

    @Override
    public List<AnswerModel.PageDatasBean> p2vGetDatas() {
        return mAdapter.getData();
    }

    @Override
    public void p2vShowContent() {
        mStateLayout.showContent();
    }

    @Override
    public void p2vShowMyselfQusetionRelease() {
        HHShareDialog shareDialog = new HHShareDialog(_mActivity);
        shareDialog.setReportView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("重新编辑");
            }
        });
        shareDialog.setDeleteQuestionView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("删除提问");
            }
        });
        shareDialog.setCopyLinkView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("复制链接");
            }
        });
        shareDialog.showDialog();
    }

    @Override
    public void p2vShowOtherQuestion() {
        HHShareDialog shareDialog = new HHShareDialog(_mActivity);
        shareDialog.setReportView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //举报
                ComplaintDialog.newInstance(_mActivity, 2, mQuestionUid).show();
            }
        });
        shareDialog.setCopyLinkView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("复制链接");
            }
        });
        shareDialog.showDialog();
    }

    private void setQusetionContent(String content) {
        mRichEditor.setHtml(content);
    }

    private void setCategoryTag(List<QuestionDetailModel.CategoryMongosBean> mongos) {
        mLlCategoryContainer.removeAllViews();
        for (QuestionDetailModel.CategoryMongosBean bean : mongos) {
            View view = LayoutInflater.from(_mActivity).inflate(R.layout.module_home_view_category, mLlCategoryContainer, false);
            ImageView iv_close = view.findViewById(R.id.iv_close);
            iv_close.setVisibility(View.GONE);
            TextView tv_category = view.findViewById(R.id.tv_category);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
            params.setMarginEnd(DensityUtil.dip2px(_mActivity, 8));
            tv_category.setText(bean.getCategory());
            mLlCategoryContainer.addView(view);
        }
    }

    @Override
    public void onItemClick(int postion) {
        start(AnswerDetailFragment.newInstance(mAnswerList.get(postion).getIdeaId(), SPUtils.getInstance().getCurrentUid()));
    }

    @OnClick({R2.id.cl_invite, R2.id.cl_panel_invite})
    public void onInvite() {
        start(InviteAnswerFragment.newInstance(mQuestionId));
    }

    @OnClick({R2.id.cl_answer, R2.id.cl_panel_answer})
    public void onAnswer() {
        if (null == mAnswer) {
            //自己没有回答过的情况下去写回答
            startForResult(WriteAnswerFragment.newInstance(mQuestionId, mQuestionTitle), REQUEST_CODE);
        } else {
            //自己写过了跳去自己的回答详情页
            start(AnswerDetailFragment.newInstance(mAnswer, SPUtils.getInstance().getCurrentUid()));
        }
    }

    @OnClick(R2.id.cl_right)
    public void onOtherClick() {
        iQuestionAndAnswerListPresenter.v2pShowShareQuestionDialog(mQuestionUid, mQuestionState);
    }

    @OnClick({R2.id.cl_question_status, R2.id.cl_answer_follow})
    public void onAttentionQuestion() {
        if (mFollowed == FollowState.unFollow) {
            //去关注
            setFollow(State.putAttention);
        } else {
            //取消关注
            setFollow(State.cancel);
        }
    }

    @CheckLogin
    private void setFollow(@State int state) {
        iQuestionAndAnswerListPresenter.v2pPutGiveFollows(mQuestionId, FollowType.CONTENT, state, SPUtils.getInstance().getCurrentUid());
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        //刷新界面
        initData();
    }

    @OnClick(R2.id.cl_search)
    public void onClickSearch() {
        ToastUtil.show("跳转搜索界面");
    }
}
