package com.huihu.module_mine.selectcountry.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.selectcountry.entity.CountryModel;
import com.huihu.module_mine.selectcountry.presenter.ImpSelectCountryPresenter;
import com.huihu.module_mine.selectcountry.selectcountryinterface.ISelectCountryPresenter;
import com.huihu.module_mine.selectcountry.selectcountryinterface.ISelectCountryView;
import com.huihu.module_mine.selectcountry.view.adapter.CountryAdapter;
import com.huihu.uilib.customize.LoadingDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SelectCountryFragment extends BaseFragment implements ISelectCountryView {
    private final ISelectCountryPresenter iSelectCountryPresenter = new ImpSelectCountryPresenter(this);
    Unbinder unbinder;
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;

    private LoadingDialog mLoadingDialog;
    private CountryAdapter mCountryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_select_country, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    public static SelectCountryFragment newInstance(int countryCode) {
        SelectCountryFragment fragment = new SelectCountryFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("countryCode", countryCode);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        iSelectCountryPresenter.v2pGetCountry();
    }

    private void initView() {
        mIvBack.setImageResource(R.drawable.module_mine_icon_back_black);
        mTvTitle.setTextColor(getResources().getColor(R.color.module_mine_black));
        mTvTitle.setText(getResources().getString(R.string.module_mine_title_select_country));

        mLoadingDialog = new LoadingDialog(_mActivity);
        mLoadingDialog.showDialog();

        initRecyclerView();
    }

    private void initRecyclerView() {
        int countryCode = getArguments().getInt("countryCode");

        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mCountryAdapter = new CountryAdapter(_mActivity, countryCode);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mCountryAdapter);

        mCountryAdapter.setListener(new CountryAdapter.OnMyItemClickListener() {
            @Override
            public void onItemClick(int countryCode,String regionCode) {
                Bundle bundle = new Bundle();
                bundle.putInt("code", countryCode);
                bundle.putString("regionCode", regionCode);
                setFragmentResult(RESULT_OK, bundle);
                pop();
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
    public void p2vShowData(List<CountryModel> list) {
        mCountryAdapter.setCountryModelList(list);
        mCountryAdapter.notifyDataSetChanged();
    }

    @OnClick(R2.id.iv_back)
    public void onBack() {
        pop();
        setFragmentResult(RESULT_OK, null);
    }
}
