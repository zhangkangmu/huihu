package com.huihu.module_mine.answered.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.answered.answeredinterface.IAnsweredPresenter;
import com.huihu.module_mine.answered.answeredinterface.IAnsweredView;
import com.huihu.module_mine.answered.entity.AnsweredInfo;
import com.huihu.module_mine.answered.presenter.ImpAnsweredPresenter;
import com.huihu.module_mine.answered.view.adapter.AnswerListAdapter;
import com.huihu.module_mine.answered.view.adapterInterface.AnswerItemClickListener;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.customize.HHShareDialog;
import com.huihu.uilib.customize.refresh.HuihuSmartRefreshFooter;
import com.huihu.uilib.statelayout.StateLayout;
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

/**
 * 回答的圈子
 * */
public class AnsweredFragment extends BaseFragment implements IAnsweredView , AnswerItemClickListener {
    private final IAnsweredPresenter iAnsweredPresenter = new ImpAnsweredPresenter(this);
    Unbinder unbinder;
    @BindView(R2.id.recycler_view_answer)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private AnswerListAdapter adapter;
    private List<AnsweredInfo.PageDatasBean> answerList;
    private StateLayout stateLayout;
    private long fid;
    public static AnsweredFragment newInstance(long fid) {
        Bundle bundle = new Bundle();
        bundle.putLong("fid", fid);
        AnsweredFragment fragment = new AnsweredFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_answered, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }


    private void initData() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            fid = bundle.getLong("fid");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        initSmartLoadMoreListener();
        stateLayout = new StateLayout.Builder(mRecyclerView).create();
        iAnsweredPresenter.v2pGetAnseweredList(fid);
    }
    private void initSmartLoadMoreListener() {
        refreshLayout.setRefreshFooter(new HuihuSmartRefreshFooter(getActivity()));
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                int end = adapter.getmList().size()-1;
                iAnsweredPresenter.v2pGetMoreAnsweredList(adapter.getmList().get(end));
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new AnswerListAdapter();
        adapter.setmContext(getActivity());
        adapter.setAnswerListAdapter(this);
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    public void p2vGetAnsweredListSuccess(List<AnsweredInfo.PageDatasBean>  mlist) {
        this.answerList=mlist;
        adapter.setDatas(mlist);
        stateLayout.showContent();
        refreshLayout.setEnableLoadMore(true);
    }

    @Override
    public void p2vGetMoreAnsweredListSuccess(List<AnsweredInfo.PageDatasBean> mlist) {
        int oldCount = adapter.getItemCount();
        adapter.getmList().addAll(mlist);
        int newCount = adapter.getItemCount();
        adapter.notifyItemRangeInserted(oldCount, newCount);
    }

    @Override
    public void p2vShowNoData() {
        stateLayout.showEmptyData();
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void p2vGetDataEnd() {
        refreshLayout.finishLoadMore();
    }

    @Override
    public void p2vHandleAnswerItemClickResult(SupportFragment fragment) {
        ((SupportFragment) getParentFragment()).start(fragment);
    }

    @Override
    public void p2vShowDialog() {
        HHShareDialog shareDialog = new HHShareDialog(_mActivity);
        shareDialog.showDialog();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @Override
    public boolean onBackPressedSupport() {
        ((SupportFragment) getParentFragment()).pop();
        return false;
    }

    @Override
    public void onAnswerItemClick(View view, int position, int ViewType) {
        AnsweredInfo.PageDatasBean bean = answerList.get(position);
        int viewId = view.getId();
        iAnsweredPresenter.v2pJudgyAnswerItemClick(viewId,bean);
    }
}
