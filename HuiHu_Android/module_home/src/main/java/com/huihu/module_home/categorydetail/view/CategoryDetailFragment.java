package com.huihu.module_home.categorydetail.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouterClassRegister;
import com.huihu.commonlib.simpleRouter.SimpleRouterObj;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.categorydetail.adapter.CategoryAdapter;
import com.huihu.module_home.categorydetail.categorydetailinterface.ICategoryDetailPresenter;
import com.huihu.module_home.categorydetail.categorydetailinterface.ICategoryDetailView;
import com.huihu.module_home.categorydetail.entity.CategoryInfoBean;
import com.huihu.module_home.categorydetail.entity.ImagesBean;
import com.huihu.module_home.categorydetail.entity.PageDatasBean;
import com.huihu.module_home.categorydetail.presenter.ImpCategoryDetailPresenter;
import com.huihu.module_home.menu.HuihuMenu;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.album.view.LookPicturesFragment;
import com.huihu.uilib.customize.refresh.HuihuSmartRefreshFooter;
import com.huihu.uilib.statelayout.StateLayout;
import com.huihu.uilib.util.CountUtil;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

@SimpleRouterClassRegister(key = RouterReDefine.CATEGORYDETAIL_FRAGMENT, type = SimpleRouterObj.FRAGMENT)
public class CategoryDetailFragment extends BaseFragment implements ICategoryDetailView {

    private static final String CATEGORY_ID = "categoryId";

    private final ICategoryDetailPresenter iCategoryDetailPresenter = new ImpCategoryDetailPresenter(this);

    //    region 绑定控件
    @BindView(R2.id.iv_toolbar_back)
    ImageView ivToolbarBack;
    @BindView(R2.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R2.id.iv_category)
    ImageView ivCategory;
    @BindView(R2.id.tv_category)
    TextView tvCategory;
    @BindView(R2.id.tv_category_follow)
    TextView tvCategoryFollow;
    @BindView(R2.id.tv_do)
    TextView tvDo;
    @BindView(R2.id.cl_content)
    ConstraintLayout clContent;
    @BindView(R2.id.tv_category_answer)
    TextView tvCategoryAnswer;
    @BindView(R2.id.tv_sort_type)
    TextView tvSortType;
    @BindView(R2.id.cl_other)
    ConstraintLayout clOther;
    @BindView(R2.id.app_bar)
    AppBarLayout appBar;
    @BindView(R2.id.rv_category_answer)
    RecyclerView rvCategoryAnswer;
    @BindView(R2.id.refresh)
    SmartRefreshLayout refresh;
    @BindView(R2.id.v_line)
    View vLine;
//    endregion

    Unbinder unbinder;

    private CategoryAdapter mAdapter;
    private int mCategoryId, mOrder;
    private StateLayout mStateLayout;
    private CategoryInfoBean mInfo;


    public static CategoryDetailFragment newInstance(int categoryId){
        CategoryDetailFragment fragment = new CategoryDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(CATEGORY_ID, categoryId);
        fragment.setArguments(bundle);
        return fragment;
    }

//    region生命周期
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_home_fragment_category_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRv();
        initTextView();
        initAppBar();
        initRefreshView();
        mCategoryId = getArguments().getInt(CATEGORY_ID, 0);
        mOrder = 1;
        iCategoryDetailPresenter.v2pGetFirstList(mCategoryId, mOrder);
        iCategoryDetailPresenter.v2pGetInfo(mCategoryId);
        mStateLayout = new StateLayout.Builder(rvCategoryAnswer).create();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
//    endregion

    @OnClick({R2.id.iv_toolbar_back, R2.id.tv_do, R2.id.tv_sort_type})
    public void onClick(View view){
        int id = view.getId();
        if (id == R.id.iv_toolbar_back){
            pop();
        } else if (id == R.id.tv_do){
            if (mInfo != null) iCategoryDetailPresenter.v2pPutGiveFollows(mInfo);
        } else if (id == R.id.tv_sort_type){
            showOrderMenu();
        }
    }

//    regionMVP方法
    @Override
    public void p2vShowFirstList(List<PageDatasBean> beans) {
        mAdapter.setDatas(beans);
        mAdapter.notifyDataSetChanged();
        mStateLayout.showContent();
        refresh.setEnableLoadMore(true);
    }

    @Override
    public void p2vShowMoreList(List<PageDatasBean> beans) {
        int oldCount = mAdapter.getItemCount();
        mAdapter.getDatas().addAll(beans);
        int newCount = mAdapter.getItemCount();
        mAdapter.notifyItemRangeInserted(oldCount, newCount);
    }

    @Override
    public void p2vChangeFollowState(CategoryInfoBean bean) {
        changeAttention();
    }

    @Override
    public void p2vChangeFollowState(PageDatasBean bean) {
        int position = mAdapter.getDatas().indexOf(bean);
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void p2vGetListComplete() {
        refresh.finishRefresh();
        refresh.finishLoadMore();
    }

    @Override
    public void p2vShowInfo(CategoryInfoBean bean) {
        mInfo = bean;
        tvCategory.setText(mInfo.getCategory());
        tvToolbarTitle.setText(mInfo.getCategory());
        Glide.with(this).load(mInfo.getPicture()).into(ivCategory);
        tvCategoryAnswer.setText(CountUtil.toHuihuCount(mInfo.getAnswerNum()) + getString(R.string.module_home_text_answer));
        changeAttention();
    }

    @Override
    public void p2vShowNoData() {
        mStateLayout.showEmptyData();
        refresh.setEnableLoadMore(false);
    }

    @Override
    public void p2vShowNetError() {
        mStateLayout.showNoNetwork();
    }

//    endregion

//    regionUI渲染
    private void initRv(){
        rvCategoryAnswer.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CategoryAdapter(iCategoryDetailPresenter);
        rvCategoryAnswer.setAdapter(mAdapter);
        ((SimpleItemAnimator)rvCategoryAnswer.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    private void initTextView(){
        TextViewUtils.setTextFakeBold(tvToolbarTitle);
        TextViewUtils.setTextFakeBold(tvCategory);
        TextViewUtils.setTextFakeBold(tvCategoryAnswer);
    }

    private void initAppBar(){
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                int titleLimit = 0 - (int) (tvCategory.getY() + tvCategory.getHeight());
                int lineLimit = 0 - clContent.getHeight();
                tvToolbarTitle.setVisibility(i < titleLimit ? View.VISIBLE : View.INVISIBLE);
                vLine.setVisibility(i == lineLimit ? View.VISIBLE : View.INVISIBLE);
            }
        });
    }

    private void initRefreshView(){
        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                iCategoryDetailPresenter.v2pGetFirstList(mCategoryId, mOrder);
            }
        });
        refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int end = mAdapter.getDatas().size() - 1;
                iCategoryDetailPresenter.v2pGetMoreList(mAdapter.getDatas().get(end), mCategoryId, mOrder);
            }
        });
        MaterialHeader header = new MaterialHeader(getContext());
        header.setColorSchemeResources(R.color.module_home_blue);
        refresh.setRefreshHeader(header);
        refresh.setRefreshFooter(new HuihuSmartRefreshFooter(getContext()));
    }

    private void changeAttention(){
        tvCategoryFollow.setText(CountUtil.toHuihuCount(mInfo.getFollowPeopleNum()) + getString(R.string.module_home_text_attention));
        if (mInfo.getFollow() == 0){
            tvDo.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            tvDo.setBackgroundResource(R.drawable.background_global_blue_full);
            tvDo.setText(R.string.module_home_text_attention);
        } else {
            tvDo.setTextColor(ContextCompat.getColor(getContext(), R.color.uilib_light_gray));
            tvDo.setBackgroundResource(R.drawable.uilib_bg_invalid);
            tvDo.setText(R.string.module_home_text_attentioned);
        }
    }

    private void showOrderMenu(){
        String[] items = getResources().getStringArray(R.array.module_home_sort);
        String now = tvSortType.getText().toString();
        int position = -1;
        for (int i = 0; i < items.length; i++){
            if (now.equals(items[i])){
                position = i;
                break;
            }
        }
        HuihuMenu.showMenu(getContext(), items, position, tvSortType, new HuihuMenu.OnItemClickListener() {
            @Override
            public void onClick(int position, String text) {
                mOrder = position + 1;
                iCategoryDetailPresenter.v2pGetFirstList(mCategoryId, mOrder);
                tvSortType.setText(text);
            }
        });
    }
//    endregion
}
