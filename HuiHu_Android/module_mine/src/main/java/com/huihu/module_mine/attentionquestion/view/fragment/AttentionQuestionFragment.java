package com.huihu.module_mine.attentionquestion.view.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.attentionquestion.attentionquestioninterface.IAttentionQuestionPresenter;
import com.huihu.module_mine.attentionquestion.attentionquestioninterface.IAttentionQuestionView;
import com.huihu.module_mine.attentionquestion.entity.AttentionQuestionInfo;
import com.huihu.module_mine.attentionquestion.presenter.ImpAttentionQuestionPresenter;
import com.huihu.module_mine.attentionquestion.view.adapter.AttentionQuestionAdapter;
import com.huihu.module_mine.attentionquestion.view.adapterInterface.OnDeletedItemClickListen;
import com.huihu.uilib.RouterReDefine;
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
import me.yokeyword.fragmentation.SupportFragment;

public class AttentionQuestionFragment extends BaseFragment implements IAttentionQuestionView, OnDeletedItemClickListen {
    private final IAttentionQuestionPresenter iAttentionQuestionPresenter = new ImpAttentionQuestionPresenter(this);
    Unbinder unbinder;
    @BindView(R2.id.recycler_view_answer)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private AttentionQuestionAdapter adapter;
    private StateLayout stateLayout;
    private LoadingDialog loadingDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_answered, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public static AttentionQuestionFragment newInstance() {
        Bundle args = new Bundle();
        AttentionQuestionFragment fragment = new AttentionQuestionFragment();
        fragment.setArguments(args);
        return fragment;
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
                iAttentionQuestionPresenter.v2pGetAttentionQuestionList();
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
                iAttentionQuestionPresenter.v2pGetAttentionQuestionList();
            }
        });
        refreshLayout.setRefreshFooter(new MaterialFooter(_mActivity));
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int end = adapter.getmList().size() - 1;
                iAttentionQuestionPresenter.v2pGetMoreQuestionList(adapter.getmList().get(end));
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        iAttentionQuestionPresenter.v2pGetAttentionQuestionList();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new AttentionQuestionAdapter(getContext());
        adapter.setOnDeletedItemClickListen(this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void p2vReturnAttentionQuestionList(List<AttentionQuestionInfo.PageDatasBean> pageDatas) {
        adapter.setAttentionQuestionList(pageDatas);
        stateLayout.showContent();
        refreshLayout.setEnableLoadMore(true);
    }

    @Override
    public void p2vNetFailed() {
        //联网失败
        stateLayout.showNoNetwork();
        if (refreshLayout!=null) {
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
    public void p2vReturnMoreAttentionQuestionList(List<AttentionQuestionInfo.PageDatasBean> pageDatas) {
       //加载更多
        int oldCount = adapter.getItemCount();
        adapter.getmList().addAll(pageDatas);
        int newCount = adapter.getItemCount();
        adapter.notifyItemRangeInserted(oldCount, newCount);
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
    public void onItemClick(View view, int position, int viewType) {
        AttentionQuestionInfo.PageDatasBean bean = adapter.geItemDatasBean().get(position);
        if (view.getId()==R.id.icon_delete) {
            showPopuwindow(view,position);
        }else{
            Bundle bundle = new Bundle();
            bundle.putLong("ideaId", bean.getIdeaId());
            SupportFragment fragmentQuestionAndAnswerlist= (SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.QUESTIONDETAIL_FRAGMENT,bundle);
            ((SupportFragment) getParentFragment()).start(fragmentQuestionAndAnswerlist);
        }

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
                ToastUtil.show("删除");
                window.dismiss();
                adapter.removeItem(position);
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


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public boolean onBackPressedSupport() {
        return false;
    }
}
