package com.huihu.module_mine.classificationattention.view;

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

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.classificationattention.view.adapter.ClassificationAdapter;
import com.huihu.module_mine.classificationattention.classificationattentioninterface.IClassificationAttentionPresenter;
import com.huihu.module_mine.classificationattention.classificationattentioninterface.IClassificationAttentionView;
import com.huihu.module_mine.classificationattention.entity.ClassificationAttentionInfo;
import com.huihu.module_mine.classificationattention.presenter.ImpClassificationAttentionPresenter;
import com.huihu.module_mine.classificationattention.view.adapterInterface.OnAttentionItemClickListen;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.customize.LoadingDialog;
import com.huihu.commonlib.MaterialFooter;
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

public class ClassificationFragment extends BaseFragment implements IClassificationAttentionView,OnAttentionItemClickListen {
    private final IClassificationAttentionPresenter iClassificationAttentionPresenter=new ImpClassificationAttentionPresenter(this);

    @BindView(R2.id.recycler_view_answer)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    private ClassificationAdapter adapter;
    private Context mContext;
    private StateLayout stateLayout;
    private List<ClassificationAttentionInfo.PageDatasBean> pageDatas=new ArrayList<>();
    private LoadingDialog loadingDialog;


    public static ClassificationFragment newInstance() {
        Bundle args = new Bundle();
        ClassificationFragment fragment = new ClassificationFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_answered, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public void  setContext(Context mContext){
        this.mContext=mContext;
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
                iClassificationAttentionPresenter.v2pGetClassicationAttentionList();
            }
        });
        stateLayout =builder.create() ;
//        stateLayout = new StateLayout.Builder(mRecyclerView).create();
//        iClassificationAttentionPresenter.v2pGetClassicationAttentionList(0,0,20, SPUtils.getInstance().getCurrentUid());

    }

    private void initSmartLoadMoreListener() {
        MaterialHeader materialHeader = new MaterialHeader(_mActivity);
        materialHeader.setColorSchemeResources(R.color.module_mine_blue);
        refreshLayout.setRefreshHeader(materialHeader);
        refreshLayout.setFooterMaxDragRate(1);
        //箭头浮在上方刷新
        refreshLayout.setEnableHeaderTranslationContent(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                iClassificationAttentionPresenter.v2pGetClassicationAttentionList();
            }
        });
        refreshLayout.setRefreshFooter(new MaterialFooter(getActivity()));
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int end = adapter.getmList().size() - 1;
                iClassificationAttentionPresenter.v2pGetMoreClassificationList(adapter.getmList().get(end));
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iClassificationAttentionPresenter.v2pGetClassicationAttentionList();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ClassificationAdapter(getContext());
        adapter.setContext(getActivity());
        adapter.setOnAttentionItemClickListen(this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onBackPressedSupport() {
        return false;
    }

    @Override
    public void p2vGetClassicationAttentionList(List<ClassificationAttentionInfo.PageDatasBean> pageDatas) {
        adapter.setClassificationList(pageDatas);
        stateLayout.showContent();
        refreshLayout.setEnableLoadMore(true);
        this.pageDatas=pageDatas;
    }

    @Override
    public void p2vClassificationAttention() {
        ToastUtil.show("请登录");
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
    public void p2vShowNoData() {
        stateLayout.showEmptyData();
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void p2vReturnMoreClassicationList(List<ClassificationAttentionInfo.PageDatasBean> pageDatas) {
        int oldCount = adapter.getItemCount();
        adapter.getmList().addAll(pageDatas);
        int newCount = adapter.getItemCount();
        adapter.notifyItemRangeInserted(oldCount,newCount);
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
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void itemClick(View v, int position, int viewType) {
        ClassificationAttentionInfo.PageDatasBean bean = pageDatas.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("categoryId",bean.getCategoryId());
        SupportFragment fragment= (SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.CATEGORYDETAIL_FRAGMENT,bundle);
        ((SupportFragment) getParentFragment()).start(fragment);
    }
}
