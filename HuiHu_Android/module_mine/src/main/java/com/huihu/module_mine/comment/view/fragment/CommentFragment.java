package com.huihu.module_mine.comment.view.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
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
import com.huihu.module_mine.comment.commentinterface.ICommentPresenter;
import com.huihu.module_mine.comment.commentinterface.ICommentView;
import com.huihu.module_mine.comment.entity.CommentInfo;
import com.huihu.module_mine.comment.entity.DeleteCommentSubCode;
import com.huihu.module_mine.comment.presenter.ImpCommentPresenter;
import com.huihu.module_mine.comment.view.adapter.CommentListAdapter;
import com.huihu.module_mine.comment.view.adapterInterface.OnCommentItemClickListener;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.customize.refresh.HuihuSmartRefreshFooter;
import com.huihu.uilib.statelayout.StateLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public class CommentFragment extends BaseFragment implements ICommentView, OnCommentItemClickListener {
    private final ICommentPresenter iCommentPresenter = new ImpCommentPresenter(this);
    Unbinder unbinder;
    @BindView(R2.id.recycler_view_answer)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private StateLayout stateLayout;
    private long fid;
    List<CommentInfo.PageDatasBean> mlist;
    public static CommentFragment newInstance(long fid) {
        Bundle bundle = new Bundle();
        bundle.putLong("fid",fid);
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_comment, container, false);
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
        iCommentPresenter.v2pGetCommentList(fid);
    }
    private void initSmartLoadMoreListener() {
        refreshLayout.setRefreshFooter(new HuihuSmartRefreshFooter(getActivity()));
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                int end = adapter.getmList().size()-1;
                iCommentPresenter.v2pGetMoreCommentList(adapter.getmList().get(end),fid);
            }
        });
    }

    CommentListAdapter adapter;
    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CommentListAdapter();
        adapter.setOnCommentItemClickListener(this);
        adapter.setmContext(getActivity());
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    public void p2vGetCommentListSuccess(List<CommentInfo.PageDatasBean> mlist) {
        this.mlist=mlist;
        adapter.setDatas(mlist);
        stateLayout.showContent();
        refreshLayout.setEnableLoadMore(true);
    }

    @Override
    public void p2vGetMoreCommentListSuccess(List<CommentInfo.PageDatasBean> msg) {
        int oldCount = adapter.getItemCount();
        adapter.getmList().addAll(msg);
        int newCount = adapter.getItemCount();
        adapter.notifyItemRangeInserted(oldCount, newCount);
    }

    @Override
    public void p2vGetCommentListFailed() {

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
    public void p2vDeleteCommentSuccess(int position) {
        mlist.remove(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void p2vDeleteCommentWithError(String subCode) {
        switch (subCode){
            case DeleteCommentSubCode
                    .UnLogin:
                SimpleRouter.getInstance().startActivity(RouterReDefine.LOGIN_ACTIVITY, null);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public boolean onBackPressedSupport() {
        ((SupportFragment) getParentFragment()).pop();
        return false;
    }

    @Override
    public void OnCommentItemClick(View v, int position, int viewType) {
        if (v.getId()==R.id.iv_delete) {
            showPopuwindow(v,position);
        }else if(v.getId()==R.id.iv_like){
            ToastUtil.show("点赞" + position);
        }else {
            ToastUtil.show("点击" + position);
        }

    }

    private void showPopuwindow(View v, final int position) {
        // 用于PopupWindow的View
           View contentView=LayoutInflater.from(getContext()).inflate(R.layout.module_mine_comment_delete_menu, null, false);
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
             window.setAnimationStyle(R.style.pop_animation);
             window.showAsDropDown(v,0,-v.getHeight());
        TextView delete = (TextView) contentView.findViewById(R.id.ensure);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            iCommentPresenter.v2pDeleteComment(mlist.get(position),position,fid);
            window.dismiss();
            }
        });

        contentView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            window.dismiss();
            }
        });


    }

}
