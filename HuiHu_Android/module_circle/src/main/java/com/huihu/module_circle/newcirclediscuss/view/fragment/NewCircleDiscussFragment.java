package com.huihu.module_circle.newcirclediscuss.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.commonlib.MaterialFooter;
import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.commonlib.simpleRouter.SimpleRouterClassRegister;
import com.huihu.commonlib.simpleRouter.SimpleRouterObj;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.discussdetail.view.DiscussDetailFragment;
import com.huihu.module_circle.newcirclediscuss.entity.NewCircleDiscussInfo;
import com.huihu.module_circle.newcirclediscuss.entity.PutGiveFollowsSubCode;
import com.huihu.module_circle.newcirclediscuss.newcirclediscussinterface.INewCircleDiscussPresenter;
import com.huihu.module_circle.newcirclediscuss.newcirclediscussinterface.INewCircleDiscussView;
import com.huihu.module_circle.newcirclediscuss.presenter.ImpNewCircleDiscussPresenter;
import com.huihu.module_circle.newcirclediscuss.view.adapter.NewCircleDiscussAdapter;
import com.huihu.module_circle.newcirclediscuss.view.adapterInterface.OnDeletedItemClickListen;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.customize.HHShareDialog;
import com.huihu.uilib.customize.LoadingDialog;
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

@SimpleRouterClassRegister(key = RouterReDefine.NEWCIRCLEDISCUSS_FRAGMENT, type = SimpleRouterObj.FRAGMENT)
public class NewCircleDiscussFragment extends BaseFragment implements INewCircleDiscussView, OnDeletedItemClickListen, NewCircleDiscussAdapter.onMyItemClickListener {
    private final INewCircleDiscussPresenter iNewCircleDiscussPresenter = new ImpNewCircleDiscussPresenter(this);
    private Unbinder unbinder;
    @BindView(R2.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private NewCircleDiscussAdapter adapter;
    private Context mContext;
    private StateLayout stateLayout;
    private List<NewCircleDiscussInfo.PageDatasBean> datas = new ArrayList<>();
    private LoadingDialog loadingDialog;
    private static final String CIRCLEID = "circleId";
    private static final String UID = "uid";
    private Long mCircleId;
    private Long mUid;


    public static NewCircleDiscussFragment newInstance(long circleId, long uid) {
        Bundle bundle = new Bundle();
        bundle.putLong(CIRCLEID, circleId);
        bundle.putLong(UID, uid);
        NewCircleDiscussFragment fragment = new NewCircleDiscussFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_circle_fragment_common, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        initSmartLoadMoreListener();
        StateLayout.Builder builder = new StateLayout.Builder(mRecyclerView);
        builder.setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog = new LoadingDialog(getContext());
                loadingDialog.showDialog();
                refreshLayout.setEnableRefresh(true);
                iNewCircleDiscussPresenter.v2pRequestCircleDiscuss(mCircleId, 0, 20, mUid, false);
            }
        });
        stateLayout = builder.create();
    }

    private void initSmartLoadMoreListener() {
        MaterialHeader materialHeader = new MaterialHeader(_mActivity);
        refreshLayout.setRefreshHeader(materialHeader);
        //箭头浮在上方刷新
        refreshLayout.setEnableHeaderTranslationContent(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                iNewCircleDiscussPresenter.v2pRequestCircleDiscuss(mCircleId, 0, 20, mUid, false);
            }
        });
        refreshLayout.setRefreshFooter(new MaterialFooter(getActivity()));
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int end = adapter.getmList().size() - 1;
                iNewCircleDiscussPresenter.v2pRequestMoreCircleDiscuss(mCircleId, adapter.getmList().get(end).getCreateTime(), 20, mUid, true);
            }
        });
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new NewCircleDiscussAdapter(getContext());
        adapter.setOnDeletedItemClickListen(this);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCircleId = getArguments().getLong(CIRCLEID, 0);
        mUid = getArguments().getLong(UID, 0);
        iNewCircleDiscussPresenter.v2pRequestCircleDiscuss(mCircleId, 0, 20, mUid, false);
    }

    @Override
    public void p2vReturnMoreCircleDiscussList(List<NewCircleDiscussInfo.PageDatasBean> datas) {
        int oldCount = adapter.getItemCount();
        adapter.getmList().addAll(datas);
        int newCount = adapter.getItemCount();
        adapter.notifyItemRangeInserted(oldCount, newCount);
    }

    @Override
    public void p2vReturnCircleDiscussList(List<NewCircleDiscussInfo.PageDatasBean> datas) {
        adapter.setCircleDiscussList(datas);
        stateLayout.showContent();
        refreshLayout.setEnableLoadMore(true);
        this.datas = datas;
    }

    @Override
    public void p2vShowNoData() {
        stateLayout.showEmptyData();
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void p2vNetFail() {
        stateLayout.showNoNetwork();
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableRefresh(false);
        ToastUtil.show("没有网络咯~~~");
    }

    @Override
    public void p2vNetComplete() {
        refreshLayout.finishLoadMore();
        if (loadingDialog != null) {
            loadingDialog.dismissDialog();
        }
        refreshLayout.finishRefresh();
    }

    @Override
    public void p2vReturnSuccesAttention() {
        adapter.notifyDataSetChanged();
    }

    //没有登录的情况
    @Override
    public void p2vPutGiveFollowsError(String subCode) {
        switch (subCode) {
            case PutGiveFollowsSubCode.UnLogin:
                SimpleRouter.getInstance().startActivity(RouterReDefine.LOGIN_ACTIVITY, null);
                break;
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        return false;
    }

    @Override
    public void deleteItemClick(View view, int position) {
        NewCircleDiscussInfo.PageDatasBean bean = adapter.getmList().get(position);
        if (view.getId() == R.id.tv_attention) {
            ToastUtil.show("點擊了關注");
            iNewCircleDiscussPresenter.v2pPutGiveFollows(bean.getUserInfo());
        } else if (view.getId() == R.id.iv_share) {
            HHShareDialog shareDialog = new HHShareDialog(_mActivity);
            shareDialog.showDialog();
        } else if (view.getId() == R.id.rl_person) {
            SupportFragment fragment = (SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.OTHERS_FRAGMENT);
            Bundle bundle = new Bundle();
            bundle.putLong("uid", bean.getUserInfo().getUid());
            fragment.setArguments(bundle);
            ((SupportFragment) getParentFragment()).start(fragment);
        }
    }

    @Override
    public void onItemClick(long ideaId) {
        ((SupportFragment)getParentFragment()).start(DiscussDetailFragment.newInstance(ideaId,0L));
    }
}
