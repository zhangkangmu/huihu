package com.huihu.module_mine.favoritesdisuss.view.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.favoritesdisuss.entity.FavoritesDiscussInfo;
import com.huihu.module_mine.favoritesdisuss.favoritesdisussinterface.IFavoritesDisussPresenter;
import com.huihu.module_mine.favoritesdisuss.favoritesdisussinterface.IFavoritesDisussView;
import com.huihu.module_mine.favoritesdisuss.presenter.ImpFavoritesDisussPresenter;
import com.huihu.module_mine.favoritesdisuss.view.adapter.FavoritesDisussAdapter;
import com.huihu.module_mine.favoritesdisuss.view.adapterInterface.OnDeletedItemClickListen;
import com.huihu.uilib.customize.LoadingDialog;
import com.huihu.commonlib.MaterialFooter;
import com.huihu.uilib.statelayout.StateLayout;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FavoritesDisussFragment extends BaseFragment implements IFavoritesDisussView, OnDeletedItemClickListen {
    private final IFavoritesDisussPresenter iFavoritesDisussPresenter = new ImpFavoritesDisussPresenter(this);
    private Unbinder unbinder;
    @BindView(R2.id.recycler_view_answer)
    RecyclerView recycler_view_discuss;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private FavoritesDisussAdapter adapter;
    private StateLayout stateLayout;
    private LoadingDialog loadingDialog;

    public static FavoritesDisussFragment newInstance() {
        Bundle args = new Bundle();
        FavoritesDisussFragment fragment = new FavoritesDisussFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_answered, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        initSmartLoadMoreListener();
        StateLayout.Builder builder = new StateLayout.Builder(recycler_view_discuss);
        builder.setOnRetryClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingDialog = new LoadingDialog(getContext());
                loadingDialog.showDialog();
                refreshLayout.setEnableRefresh(true);
                iFavoritesDisussPresenter.v2pGetDisscusList();
            }
        });
        stateLayout =builder.create() ;
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
                iFavoritesDisussPresenter.v2pGetDisscusList();
            }
        });
        refreshLayout.setRefreshFooter(new MaterialFooter(getActivity()));
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int end = adapter.getmList().size() - 1;
                iFavoritesDisussPresenter.v2pGetMoreDiscussList(adapter.getmList().get(end));
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view_discuss.setLayoutManager(linearLayoutManager);
        adapter = new FavoritesDisussAdapter(getContext());
        adapter.setOnDeletedItemClickListen(this);
        recycler_view_discuss.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iFavoritesDisussPresenter.v2pGetDisscusList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public boolean onBackPressedSupport() {
        return false;
    }

    @Override
    public void p2vReturnMoreDiscussListSuccess(List<FavoritesDiscussInfo.PageDatasBean> datas) {
        //加载更多
        int oldCount = adapter.getItemCount();
        adapter.getmList().addAll(datas);
        int newCount = adapter.getItemCount();
        adapter.notifyItemRangeInserted(oldCount, newCount);
    }

    @Override
    public void p2vReturnDiscussListSuccess(List<FavoritesDiscussInfo.PageDatasBean> datas) {
        //首次返回数据
        adapter.setDatas(datas);
        stateLayout.showContent();
        refreshLayout.setEnableLoadMore(true);
    }

    @Override
    public void p2vShowNoData() {
        //没有数据
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
        if (loadingDialog!=null){
            loadingDialog.dismissDialog();
        }
        refreshLayout.finishRefresh();
    }

    @Override
    public void p2mReturnDeletedItem() {
        iFavoritesDisussPresenter.v2pGetDisscusList();
    }

    @Override
    public void deleteItemClick(View v, int position, int viewType) {
        showPopuwindow(v,position);
    }

    private void showPopuwindow(View v,final int position) {
        // 用于PopupWindow的View
        View contentView=LayoutInflater.from(getContext()).inflate(R.layout.module_mine_comment_delete_menu, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window=new PopupWindow(contentView, LinearLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        window.setAnimationStyle(R.style.pop_animation);
        window.showAsDropDown(v,0,-v.getHeight());
        TextView delete = (TextView) contentView.findViewById(R.id.ensure);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
                deletedItem(position);
            }
        });

        contentView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("取消");
                window.dismiss();
            }
        });
    }

    private void deletedItem(int position) {
        FavoritesDiscussInfo.PageDatasBean bean = adapter.getmList().get(position);
        iFavoritesDisussPresenter.v2pRequestDeletedItem(bean.getIdeaId() ,284003 );
    }
}
