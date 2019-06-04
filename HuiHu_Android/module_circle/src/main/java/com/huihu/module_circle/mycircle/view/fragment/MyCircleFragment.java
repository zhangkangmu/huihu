package com.huihu.module_circle.mycircle.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.commonlib.MaterialFooter;
import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.circlelist.view.CircleListFragment;
import com.huihu.module_circle.mycircle.entity.MyCircleInfo;
import com.huihu.module_circle.mycircle.mycircleinterface.IMyCirclePresenter;
import com.huihu.module_circle.mycircle.mycircleinterface.IMyCircleView;
import com.huihu.module_circle.mycircle.presenter.ImpMyCirclePresenter;
import com.huihu.module_circle.mycircle.view.adapter.MyCircleAdapter;
import com.huihu.module_circle.mycircle.view.adapterInterface.OnMyCircleItemClickListener;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public class MyCircleFragment extends BaseFragment implements IMyCircleView, OnMyCircleItemClickListener {
    private final IMyCirclePresenter iMyCirclePresenter = new ImpMyCirclePresenter(this);
    private Unbinder unbinder;
    private MyCircleAdapter adapter;
    @BindView(R2.id.mRecycleView)
    RecyclerView mRecycleView;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_circle_fragment_mycircle, container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycleView();
        initData();
        initSmartRefreshListener();
    }

    private void initData() {
        iMyCirclePresenter.v2pGetMyCircle(-1);
    }
    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        adapter = new MyCircleAdapter();
        adapter.setContext(getActivity());
        adapter.setOnMyCircleItemClickListener(this);
        mRecycleView.setAdapter(adapter);
    }

    private List<MyCircleInfo.PageDatasBean> pageDatas=new ArrayList<>();
    @Override
    public void p2vReturnMyCircle(MyCircleInfo myCircleInfo,boolean isMore) {
        if (!isMore) {
           this.pageDatas = myCircleInfo.getPageDatas();
            adapter.setmList(pageDatas);
        }else{
            this.pageDatas.addAll(myCircleInfo.getPageDatas());
            adapter.setmList(pageDatas);
        }
        mRecycleView.stopScroll();
    }
    private void initSmartRefreshListener() {
        MaterialHeader materialHeader = new MaterialHeader(_mActivity);
        materialHeader.setColorSchemeResources(R.color.common_blue);
        refreshLayout.setRefreshHeader(materialHeader);
        refreshLayout.setEnableHeaderTranslationContent(false);
        refreshLayout.setRefreshFooter(new MaterialFooter(_mActivity));
        //显示拖动高度/真实拖动高度
        refreshLayout.setDragRate(1);
        //refresh最大拖动距离，和footer等距，不可越界拖动
        refreshLayout.setFooterMaxDragRate(1);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                iMyCirclePresenter.v2pGetMyCircle(-1);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                iMyCirclePresenter.v2pGetMyCircle(pageDatas.get(pageDatas.size()-1).getJoinTime());
            }
        });
    }

    @Override
    public void p2vShowNoData() {

    }

    @Override
    public void p2vGetDataEnd() {
        refreshLayout.finishLoadMore();
        refreshLayout.finishRefresh();
    }

    @Override
    public void p2vJoinCircleSuccess(int position) {
        pageDatas.get(position).setMemberType(1);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void onMyCircleItemClick(View view, int position) {
        int viewId=view.getId();
        if (viewId==R.id.join) {
            iMyCirclePresenter.v2pJoinCircle(pageDatas.get(position).getCircleId(),1,position);
        }else if (viewId==R.id.cons_manage){
            ((SupportFragment) getParentFragment().getParentFragment()).start(CircleListFragment.newInstance(pageDatas.get(position).getCircleId(),pageDatas.get(position).getMemberType()));
        }else {
            ((SupportFragment) getParentFragment().getParentFragment()).start(CircleListFragment.newInstance(pageDatas.get(position).getCircleId(), SPUtils.getInstance().getCurrentUid()));
        }
    }
}
