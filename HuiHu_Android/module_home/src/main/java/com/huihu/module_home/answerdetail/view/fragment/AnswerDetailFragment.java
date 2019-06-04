package com.huihu.module_home.answerdetail.view.fragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.commonlib.simpleRouter.SimpleRouterClassRegister;
import com.huihu.commonlib.simpleRouter.SimpleRouterObj;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.answerdetail.answerdetailinterface.IAnswerDetailPresenter;
import com.huihu.module_home.answerdetail.answerdetailinterface.IAnswerDetailView;
import com.huihu.module_home.answerdetail.entity.AnswerInfo;
import com.huihu.module_home.answerdetail.entity.PutGiveViewpointSubCode;
import com.huihu.module_home.answerdetail.presenter.ImpAnswerDetailPresenter;
import com.huihu.module_home.answerdetail.view.adapter.AnswerDetailAdapter;
import com.huihu.module_home.answerdetail.view.adapterInterface.OnAnswerDetailItemClickListener;
import com.huihu.module_home.commentdialog.view.CommentDialog;
import com.huihu.module_home.popularIdea.enity.PutGiveFollowsSubCode;
import com.huihu.module_home.questionandanswerlist.view.QuestionAndAnswerListFragment;
import com.huihu.module_home.writeanswer.view.WriteAnswerFragment;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.customize.HHShareDialog;
import com.huihu.uilib.util.CountUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@SimpleRouterClassRegister(key = RouterReDefine.ANSWERDETAIL_FRAGMENT, type = SimpleRouterObj.FRAGMENT)
public class AnswerDetailFragment extends BaseFragment implements IAnswerDetailView, OnAnswerDetailItemClickListener {
    @BindView(R2.id.tv_question)
    TextView tv_question;
    private final IAnswerDetailPresenter iAnswerDetailPresenter = new ImpAnswerDetailPresenter(this);
    @BindView(R2.id.cl_scroll_title)
    ConstraintLayout constraintLayout_title;
    @BindView(R2.id.cl_search)
    ConstraintLayout constraintLayout_search;
    @BindView(R2.id.main_bottom)
    View main_bottom;
    @BindView(R2.id.agree_count)
    TextView agreeCount;
    @BindView(R2.id.comment_count)
    TextView commentCount;
    @BindView(R2.id.collect_count)
    TextView collectCount;
    @BindView(R2.id.download_count)
    TextView downloadCount;
    @BindView(R2.id.recycleView_an)
    RecyclerView mRecycleView;
    @BindView(R2.id.tv_question_title)
    TextView tv_question_title;
    @BindView(R2.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R2.id.constraintLayout_top)
    ConstraintLayout constraintLayout_top;
    @BindView(R2.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R2.id.constraintLayout_first_top)
    ConstraintLayout constraintLayout_first_top;
    @BindView(R2.id.tv_see_all_answer)
    TextView tv_see_all_answer;
    @BindView(R2.id.img_download)
    ImageView img_download;
    @BindView(R2.id.img_agree)
    ImageView img_agree;
    @BindView(R2.id.img_collect)
    ImageView img_collect;
    @BindView(R2.id.img_comment)
    ImageView img_comment;
    Unbinder unbinder;

    private long mIdeaId;
    private long uid;
    private String mTitle;
    private ArrayList<AnswerInfo> mlist;
    private long questionId;


    public static AnswerDetailFragment newInstance(long ideaId, long uid) {
        AnswerDetailFragment fragment = new AnswerDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("ideaId", ideaId);
        bundle.putLong("uid", uid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_home_fragment_answer_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    private int height;

    @SuppressLint("NewApi")
    @Override
    public void onViewCreated(View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycleView();
        initRefreshLayout();
        constraintLayout_top.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {


            @Override
            public void onGlobalLayout() {
                constraintLayout_top.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                height = constraintLayout_top.getHeight();
            }
        });
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                //                if (scrollY > oldScrollY) {//向下滚动
                //                    Log.i(TAG, "Scroll DOWN"+scrollY);
                if (constraintLayout_first_top.getVisibility() == View.VISIBLE) {
                    constraintLayout_search.setAlpha(1 - (float) scrollY / height);
                    constraintLayout_title.setAlpha((float) scrollY / height);
                }
                if (scrollY < oldScrollY) {//向上滚动

                }
                if (!(scrollY + 20 - (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) < 0)) {
                    main_bottom.setVisibility(View.GONE);
                } else {
                    main_bottom.setVisibility(View.VISIBLE);

                }
            }
        });

        initData();
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            mIdeaId = bundle.getLong("ideaId");
            uid = bundle.getLong("uid");
            iAnswerDetailPresenter.v2pGetAnswerInfo(mIdeaId, uid);
        }
    }

    private void initRefreshLayout() {
        smartRefreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                iAnswerDetailPresenter.v2pGetAnswerInfo(mIdeaId, uid);
                constraintLayout_first_top.setVisibility(View.VISIBLE);
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                iAnswerDetailPresenter.v2pGetAnswerInfo(183, uid);
                constraintLayout_first_top.setVisibility(View.GONE);
            }
        });
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setEnableRefresh(false);
    }


    private AnswerDetailAdapter adapter;

    private void initRecycleView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);
        adapter = new AnswerDetailAdapter();
        adapter.setContext(getActivity());
        adapter.setOnItemClickListener(this);
        mRecycleView.setAdapter(adapter);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick({R2.id.cons_comment, R2.id.cons_collect, R2.id.cons_agree, R2.id.cons_down, R2.id.cl_right})
    public void OnClick(View view) {
        int id = view.getId();
        if (id == R.id.cons_comment) {
            start(CommentDialog.newInstance(mIdeaId));
        } else if (id == R.id.cons_agree) {
            iAnswerDetailPresenter.v2pPutGiveViewpoint(answerInfo.getIdeaId(), SPUtils.getInstance().getCurrentUid(), 1, 1);
        } else if (id == R.id.cons_collect) {
            iAnswerDetailPresenter.v2pConstrolCollection(answerInfo.getIdeaId(), SPUtils.getInstance().getCurrentUid(), answerInfo.getCollection() == 0 ? true : false);
        } else if (id == R.id.cons_down) {
            downloadCount.setSelected(true);
            img_download.setImageDrawable(getActivity().getDrawable(R.drawable.module_home_icon_down_48_pre));
        } else if (id == R.id.cl_right) {
            HHShareDialog shareDialog = new HHShareDialog(_mActivity);
            shareDialog.showDialog();
        }

    }

    @Override
    public void p2vShowNoData() {

    }

    @Override
    public void p2vGetDataEnd() {
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.finishLoadMore();

    }

    @OnClick(R2.id.iv_back)
    public void onBack() {
        pop();
    }

    private AnswerInfo answerInfo;

    @Override
    public void p2vReturnAnswerInfo(AnswerInfo answerInfo) {
        this.answerInfo = answerInfo;
        mIdeaId = answerInfo.getIdeaId();
        questionId = answerInfo.getQuestionId();
        mTitle = answerInfo.getTitle();
        if (mlist == null) {
            mlist = new ArrayList<>();
        }
        mlist.clear();
        mlist.add(answerInfo);
        adapter.setmList(mlist);
        tv_question.setText(answerInfo.getTitle());
        tv_question_title.setText(answerInfo.getTitle());
        //0：默认 1:点赞 2：踩
        int viewpoint = answerInfo.getViewpoint();
        //0 没收藏 1.
        int collection = answerInfo.getCollection();
        if (collection == 0) {
            collectCount.setSelected(false);
            img_collect.setImageDrawable(getActivity().getDrawable(R.drawable.module_home_icon_collect_48_def));
        } else {
            collectCount.setSelected(true);
            img_collect.setImageDrawable(getActivity().getDrawable(R.drawable.module_home_icon_collect_48_pre));
        }
        if (viewpoint == 1) {
            agreeCount.setSelected(true);
            img_agree.setImageDrawable(getActivity().getDrawable(R.drawable.module_home_icon_like_48_pre));
        } else if (viewpoint == 2) {
            downloadCount.setSelected(true);
            img_download.setImageDrawable(getActivity().getDrawable(R.drawable.module_home_icon_down_48_pre));
        } else {

        }

        if (answerInfo.getAgreeCount() != 0) {
            agreeCount.setText(CountUtil.toHuihuCount(answerInfo.getAgreeCount()));
        } else {
            agreeCount.setText("赞");
        }
        if (answerInfo.getCollection() != 0) {
            collectCount.setText(CountUtil.toHuihuCount(answerInfo.getCollection()));
        } else {
            collectCount.setText("收藏");
        }
        if (answerInfo.getCommentCount() != 0) {
            commentCount.setText(CountUtil.toHuihuCount(answerInfo.getCommentCount()));
        } else {
            commentCount.setText("评论");
        }
        if (answerInfo.getOpposeCount() != 0) {
            downloadCount.setText(CountUtil.toHuihuCount(answerInfo.getOpposeCount()));
        } else {
            downloadCount.setText("反对");
        }
        tv_see_all_answer.setText("查看全部" + CountUtil.toHuihuCount(answerInfo.getAnswerCount()) + "回答");

    }

    @Override
    public void p2vPutGiveFollows() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void p2vPutGiveFollowsError(String subCode) {
        switch (subCode) {
            case PutGiveFollowsSubCode
                    .UnLogin:
                SimpleRouter.getInstance().startActivity(RouterReDefine.LOGIN_ACTIVITY, null);
                break;
        }
    }

    //作出评价
    @Override
    public void p2vPutGiveViewpoint(int viewpoint) {
        //0取消1赞同2反对
        switch (viewpoint) {
            case 0:
                ToastUtil.show("取消");
                break;
            case 1:
                ToastUtil.show("赞同");
                agreeCount.setSelected(true);
                img_agree.setImageDrawable(getActivity().getDrawable(R.drawable.module_home_icon_like_48_pre));
                break;
            case 2:
                ToastUtil.show("反对");
                break;
        }

    }

    @Override
    public void p2vPutGiveViewpointError(String subCode) {
        switch (subCode) {
            case PutGiveViewpointSubCode
                    .UnLogin:
                ToastUtil.show("未登录");
                break;
        }
    }

    @Override
    public void p2vChangeCollection(boolean isAddCollection) {
        if (isAddCollection) {
            collectCount.setSelected(true);
            img_collect.setImageDrawable(getActivity().getDrawable(R.drawable.module_home_icon_collect_48_pre));
        } else {
            collectCount.setSelected(false);
            img_collect.setImageDrawable(getActivity().getDrawable(R.drawable.module_home_icon_collect_48_def));
        }
        answerInfo.setCollection(isAddCollection ? 1 : 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R2.id.tv_question_title, R2.id.tv_see_all_answer})
    public void onStartAnswerList() {
        start(QuestionAndAnswerListFragment.newInstance(questionId));
    }

    @OnClick(R2.id.tv_write_answer)
    public void onStartWriteAnswer() {
        start(WriteAnswerFragment.newInstance(mIdeaId, mTitle));
    }

    @Override
    public void onAnswerDetailItemClick(View view, int position, int ViewType) {
        int id = view.getId();
        if (id == R.id.tv_answer_attention) {
            iAnswerDetailPresenter.v2pPutGiveFollows(mlist.get(position));
        }
    }
}
