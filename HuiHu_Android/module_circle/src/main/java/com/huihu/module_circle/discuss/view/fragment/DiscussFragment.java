package com.huihu.module_circle.discuss.view.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.discuss.discussinterface.IDiscussPresenter;
import com.huihu.module_circle.discuss.discussinterface.IDiscussView;
import com.huihu.module_circle.discuss.entity.RecemendDiscussInfo;
import com.huihu.module_circle.discuss.presenter.ImpDiscussPresenter;
import com.huihu.module_circle.discuss.view.adapter.DiscussLsitAdapter;
import com.huihu.module_circle.discuss.view.adapterInterface.OnRecommendDiscussItemClickListener;
import com.huihu.uilib.customize.HHShareDialog;
import com.huihu.uilib.util.DensityUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DiscussFragment extends BaseFragment implements IDiscussView, OnRecommendDiscussItemClickListener {
    private final IDiscussPresenter iDiscussPresenter = new ImpDiscussPresenter(this);
    private long circleId;
    private Unbinder unbinder;
    @BindView(R2.id.mRecycleView)
    RecyclerView mRecycleView;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private DiscussLsitAdapter adapter;
    private List<RecemendDiscussInfo.PageDatasBean> infoPageDatas=new ArrayList<>();
    private long pageIndex;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_circle_fragment_discuss, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycleView();
        initData();
        initSmartRefreshListener();
    }
    private void initSmartRefreshListener() {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                iDiscussPresenter.v2pGetRecemendDiscuss(1);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                iDiscussPresenter.v2pGetRecemendDiscuss(pageIndex+1);
            }
        });
    }
    private void initRecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        adapter = new DiscussLsitAdapter();
        adapter.setContext(getActivity());
        adapter.setOnRecommendDiscussItemListener(this);
        mRecycleView.setAdapter(adapter);
    }

    private void initData() {
        iDiscussPresenter.v2pGetRecemendDiscuss(1);
    }



    @Override
    public void p2vReturnRecemendDiscuss(RecemendDiscussInfo recemendDiscussInfo,boolean isMore) {
        pageIndex=recemendDiscussInfo.getPageIndex();
        if (!isMore) {
            this.infoPageDatas = recemendDiscussInfo.getPageDatas();
        }else {
            this.infoPageDatas.addAll(recemendDiscussInfo.getPageDatas());
        }
        mRecycleView.stopScroll();
        adapter.setmList(this.infoPageDatas);
    }


    @Override
    public void p2vDeleteIdeaSuccessful(int position) {
        adapter.getmList().remove(position);
        adapter.notifyDataSetChanged();
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
    public void OnRecommendDiscussItemClick(View view, int position) {
        int viewId=view.getId();
        if (viewId==R.id.share){
            HHShareDialog shareDialog = new HHShareDialog(_mActivity);
            shareDialog.showDialog();
        }else if (viewId==R.id.iv_delete){
            showPopuwindow(view,infoPageDatas.get(position),position);
        }
    }
    private void showPopuwindow(View v, final RecemendDiscussInfo.PageDatasBean bean , final int position) {
        // 用于PopupWindow的View
        View contentView=LayoutInflater.from(getContext()).inflate(R.layout.module_circle_popuwindow_uninterested, null, false);
        // 创建PopupWindow对象，其中：
        // 第一个参数是用于PopupWindow中的View，第二个参数是PopupWindow的宽度，
        // 第三个参数是PopupWindow的高度，第四个参数指定PopupWindow能否获得焦点
        final PopupWindow window=new PopupWindow(contentView, LinearLayout.LayoutParams.WRAP_CONTENT,  LinearLayout.LayoutParams.WRAP_CONTENT, true);
        // 设置PopupWindow的背景
        window.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.white)));
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        //获取点击View的坐标
        int[] location = new int[2];
        View uninterestion = contentView.findViewById(R.id.popu);
        uninterestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iDiscussPresenter.v2pDeleteRecommendDiscuss(infoPageDatas.get(position),position);
                window.dismiss();
            }
        });
        v.getLocationOnScreen(location);
        window.showAtLocation(v, Gravity.NO_GRAVITY,location[0]- DensityUtil.dip2px(getActivity(),140),location[1]);
        // window.showAsDropDown(v,-200,-v.getHeight());
    }
}
