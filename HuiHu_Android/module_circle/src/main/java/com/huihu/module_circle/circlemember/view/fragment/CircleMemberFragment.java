package com.huihu.module_circle.circlemember.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.commonlib.MaterialFooter;
import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.circlemember.circlememberinterface.ICircleMemberPresenter;
import com.huihu.module_circle.circlemember.circlememberinterface.ICircleMemberView;
import com.huihu.module_circle.circlemember.entity.MemberManagementInfo;
import com.huihu.module_circle.circlemember.entity.PutGiveFollowsSubCode;
import com.huihu.module_circle.circlemember.presenter.ImpCircleMemberPresenter;
import com.huihu.module_circle.circlemember.view.adapterInterface.OnItemClickListen;
import com.huihu.module_circle.circlemember.view.dapter.CircleMemberAdapter;
import com.huihu.module_circle.membermanagement.view.adapter.MemberManagerAdapter;
import com.huihu.uilib.RouterReDefine;
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
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public class CircleMemberFragment extends BaseFragment implements ICircleMemberView, OnItemClickListen {
    private final ICircleMemberPresenter iCircleMemberPresenter = new ImpCircleMemberPresenter(this);
    private Unbinder unbinder;
    @BindView(R2.id.rv_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Context mContext;
    private StateLayout stateLayout;
    private LoadingDialog loadingDialog;
    private CircleMemberAdapter adapter;
    private List<MemberManagementInfo.PageDatasBean> pageDatas=new ArrayList<>();

    public static CircleMemberFragment newInstance() {
        Bundle args = new Bundle();
        CircleMemberFragment fragment = new CircleMemberFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iCircleMemberPresenter.v2pRequestMemberList(6,0,1,20,284017,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
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
                iCircleMemberPresenter.v2pRequestMemberList(6,0,1,20,284017,false);
            }
        });
        stateLayout =builder.create() ;
    }
    private void initSmartLoadMoreListener() {
        MaterialHeader materialHeader = new MaterialHeader(_mActivity);
        materialHeader.setColorSchemeResources(R.color.global_blue);
        refreshLayout.setRefreshHeader(materialHeader);
        refreshLayout.setFooterMaxDragRate(1);
        //箭头浮在上方刷新
        refreshLayout.setEnableHeaderTranslationContent(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                iCircleMemberPresenter.v2pRequestMemberList(6,0,1,20,284017,false);
            }
        });
        refreshLayout.setRefreshFooter(new MaterialFooter(getActivity()));
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int end = adapter.getmList().size() - 1;
                iCircleMemberPresenter.v2pRequestMemberList(6,pageDatas.get(end).getJoinTime(),1,20,284017,true);
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CircleMemberAdapter(getContext());
        adapter.setContext(getActivity());
        adapter.setOnItemClickListen(this);
        mRecyclerView.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_circle_fragment_circle_member, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
    @Override
    public void p2vReturnMemberList(List<MemberManagementInfo.PageDatasBean> datas) {
        Log.d("zyh-member",""+datas.size());
        adapter.setMemberManagerList(datas);
        stateLayout.showContent();
        refreshLayout.setEnableLoadMore(true);
        this.pageDatas=datas;
    }
    @Override
    public void p2vNetFail() {
        stateLayout.showNoNetwork();
        if (refreshLayout!=null){
            refreshLayout.setEnableLoadMore(false);
            refreshLayout.setEnableRefresh(false);
            ToastUtil.show("没有网络咯~~~");
        }
    }

    @Override
    public void p2vNetComplete() {
        refreshLayout.finishLoadMore();
        if (loadingDialog!=null){
            loadingDialog.dismissDialog();
        }
        refreshLayout.finishRefresh();
    }

    @Override
    public void p2vShowNoData() {
        stateLayout.showEmptyData();
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void p2vReturnMoreMemberList(List<MemberManagementInfo.PageDatasBean> datas) {
        int oldCount = adapter.getItemCount();
        adapter.getmList().addAll(pageDatas);
        int newCount = adapter.getItemCount();
        adapter.notifyItemRangeInserted(oldCount,newCount);
    }

    @Override
    public void p2vReturnSuccesAttention() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void p2vPutGiveFollowsError(String subCode) {
        switch (subCode){
            case PutGiveFollowsSubCode.UnLogin:
                SimpleRouter.getInstance().startActivity(RouterReDefine.LOGIN_ACTIVITY, null);
                break;
        }
    }

    @OnClick({R2.id.iv_back})
    public void onViewClicked(View v) {
        int id = v.getId();
        if (id == R.id.iv_back) {
            pop();
        }
    }

    @Override
    public void itemClick(View view, int position) {

        MemberManagementInfo.PageDatasBean bean = adapter.getmList().get(position);
        if (view.getId() == R.id.tv_attention) {
            ToastUtil.show("點擊了關注");
            iCircleMemberPresenter.v2pPutGiveFollows(bean.getUserInfo());
        } else if (view.getId() == R.id.ll_other) {
            SupportFragment fragment=(SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.OTHERS_FRAGMENT);
            Bundle bundle = new Bundle();
            bundle.putLong("uid", bean.getUserInfo().getUid());
            fragment.setArguments(bundle);
            start(fragment);
        }
    }
}
