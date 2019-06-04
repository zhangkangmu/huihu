package com.huihu.module_home.addquestioncategory.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.Constant;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.addquestioncategory.addquestioncategoryinterface.IAddQuestionCategoryPresenter;
import com.huihu.module_home.addquestioncategory.addquestioncategoryinterface.IAddQuestionCategoryView;
import com.huihu.module_home.addquestioncategory.entitiy.CategoryModel;
import com.huihu.module_home.addquestioncategory.entitiy.PostCategoryByTitleModel;
import com.huihu.module_home.addquestioncategory.entitiy.PostQuestionModel;
import com.huihu.module_home.addquestioncategory.entitiy.TypeMatch;
import com.huihu.module_home.addquestioncategory.presenter.ImpAddQuestionCategoryPresenter;
import com.huihu.module_home.addquestioncategory.view.adapter.CategoryAdapter;
import com.huihu.module_home.categorydetail.view.CategoryDetailFragment;
import com.huihu.module_home.question.view.WriteQuestionFragment;
import com.huihu.uilib.customize.LoadingDialog;
import com.huihu.uilib.statelayout.StateLayout;
import com.huihu.uilib.util.DensityUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AddQuestionCategoryFragment extends BaseFragment implements IAddQuestionCategoryView {
    private final IAddQuestionCategoryPresenter iAddQuestionCategoryPresenter = new ImpAddQuestionCategoryPresenter(this);
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_release)
    TextView mTvRelease;
    @BindView(R2.id.ll_container)
    LinearLayout mLlContainer;
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    Unbinder unbinder;
    @BindView(R2.id.tv_count)
    TextView mTvCount;
    @BindView(R2.id.refresh)
    SmartRefreshLayout mRefresh;
    private CategoryAdapter mCategoryAdapter;
    private PostQuestionModel mModel;
    private LoadingDialog mLoadingDialog;

    private List<CategoryModel> mSelectedCategoryList = new ArrayList<>();
    private StateLayout mStateLayout;

    private int mIndex = 1;

    public static AddQuestionCategoryFragment newInstance(PostQuestionModel model) {
        AddQuestionCategoryFragment fragment = new AddQuestionCategoryFragment();
        Bundle args = new Bundle();
        args.putSerializable("model", model);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_home_fragment_add_category, container, false);
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
        mModel = (PostQuestionModel) getArguments().getSerializable("model");
        if (mModel != null) {
            PostCategoryByTitleModel model = new PostCategoryByTitleModel();
            model.setPageIndex(mIndex);
            model.setPageSize(Constant.PAGE_SIZE);
            model.setTitle(mModel.getTitle());
            model.setType(TypeMatch.matchTitle);
            iAddQuestionCategoryPresenter.v2pPostCategory(model);
        }
    }

    private void initView() {
        initViewState();
        initCategory();//初始化选中分类的容器
        initRefresh();
        initRecyclerView();
    }

    private void initRefresh() {
        mRefresh.setEnableRefresh(false);
        mRefresh.setEnableLoadMore(false);
        mRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });
    }

    private void initViewState() {
        mLoadingDialog = new LoadingDialog(_mActivity);
        mStateLayout = new StateLayout.Builder(mRefresh).create();
        mStateLayout.showLoadingData();
    }

    private void initCategory() {
        setCategoryCount(0);
        mTvRelease.setClickable(false);

        TextView hintTextView = new TextView(_mActivity);
        hintTextView.setText(getResources().getString(R.string.module_home_hint_add_category));
        hintTextView.setTextSize(14);
        hintTextView.setTag("hint");
        hintTextView.setTextColor(getResources().getColor(R.color.module_home_gray_three));
        mLlContainer.addView(hintTextView);
    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mCategoryAdapter = new CategoryAdapter(_mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mCategoryAdapter);

        mCategoryAdapter.setListener(new CategoryAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(CategoryModel categoryModel) {
                start(CategoryDetailFragment.newInstance(categoryModel.getCategoryId()));
            }

            @Override
            public void onAddClick(CategoryModel categoryModel) {
                iAddQuestionCategoryPresenter.v2pCheckCount(mSelectedCategoryList, categoryModel);
            }
        });
    }

    private void addCategory(final CategoryModel categoryModel) {
        TextView hint = mLlContainer.findViewWithTag("hint");
        if (hint != null) {
            mLlContainer.removeView(hint);
        }
        final View view = LayoutInflater.from(_mActivity).inflate(R.layout.module_home_view_category, mLlContainer, false);
        TextView tv_category = view.findViewById(R.id.tv_category);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.setMarginEnd(DensityUtil.dip2px(_mActivity, 8));
        ImageView iv_close = view.findViewById(R.id.iv_close);
        tv_category.setText(categoryModel.getCategory());
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryModel.setSelected(false);
                mSelectedCategoryList.remove(categoryModel);
                mLlContainer.removeView(view);
                setCategoryCount(mLlContainer.getChildCount());
                mCategoryAdapter.notifyDataSetChanged();
                setReleaseStatus();
            }
        });
        mLlContainer.addView(view);
        mSelectedCategoryList.add(categoryModel);
    }

    private void setCategoryCount(int count) {
        mTvCount.setText(String.format(getResources().getString(R.string.module_home_count_category), count));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R2.id.iv_back)
    public void onBack() {
        pop();
    }

    @Override
    public void onResume() {
        super.onResume();
        hideSoftInput();//上个界面onPause中hide失效
    }

    @Override
    public void onPause() {
        super.onPause();
        hideSoftInput();
    }

    @OnClick(R2.id.tv_release)
    public void onRelease() {
        mLoadingDialog.showDialog();

        mModel.setCategorys(getSelectedIntList(mSelectedCategoryList));
        iAddQuestionCategoryPresenter.v2pRelease(mModel);
    }

    private List<Integer> getSelectedIntList(List<CategoryModel> list) {
        List<Integer> integerList = new ArrayList<>();
        for (CategoryModel categoryModel : list) {
            integerList.add(categoryModel.getCategoryId());
        }
        return integerList;
    }

    @Override
    public void p2vShowCategoryList(List<CategoryModel> list) {
        mCategoryAdapter.setData(list);
        mCategoryAdapter.notifyDataSetChanged();
        mStateLayout.showContent();
    }

    @Override
    public void p2vShowToast(String message) {
        ToastUtil.show(message);
    }

    @Override
    public void p2vDismissDialog() {
        mLoadingDialog.dismissDialog();
    }

    @Override
    public void p2vFinish() {
        popTo(WriteQuestionFragment.class, true);//发布成功连同写提问fragment一起出栈
    }

    @Override
    public void p2vAddCategory(CategoryModel categoryModel) {
        categoryModel.setSelected(true);
        addCategory(categoryModel);
        setCategoryCount(mLlContainer.getChildCount());
        mCategoryAdapter.notifyDataSetChanged();
        setReleaseStatus();
    }

    private void setReleaseStatus() {
        if (mSelectedCategoryList.size() == 0) {
            mTvRelease.setClickable(false);
            mTvRelease.setTextColor(getResources().getColor(R.color.module_home_unclickable));
        } else {
            mTvRelease.setClickable(true);
            mTvRelease.setTextColor(getResources().getColor(R.color.module_home_blue));
        }
    }
}
