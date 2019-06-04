package com.huihu.module_circle.membermanagement.view.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.huihu.commonlib.MaterialFooter;
import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.membermanagement.entity.MemberManagementInfo;
import com.huihu.module_circle.membermanagement.membermanagementinterface.IMemberManagementPresenter;
import com.huihu.module_circle.membermanagement.membermanagementinterface.IMemberManagementView;
import com.huihu.module_circle.membermanagement.presenter.ImpMemberManagementPresenter;
import com.huihu.module_circle.membermanagement.view.adapter.MemberManagerAdapter;
import com.huihu.module_circle.membermanagement.view.adapterInterface.OnItemClickListen;
import com.huihu.module_circle.newcirclediscuss.entity.NewCircleDiscussInfo;
import com.huihu.module_circle.newcirclediscuss.entity.PutGiveFollowsSubCode;
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
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public class MemberManagementFragment extends BaseFragment implements IMemberManagementView, OnItemClickListen {
    private final IMemberManagementPresenter iMemberManagementPresenter = new ImpMemberManagementPresenter(this);
    private Unbinder unbinder;
    @BindView(R2.id.rv_recyclerview)
    RecyclerView mRecyclerView;
    @BindView(R2.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private Context mContext;
    private StateLayout stateLayout;
    private LoadingDialog loadingDialog;
    private MemberManagerAdapter adapter;
    private List<MemberManagementInfo.PageDatasBean> pageDatas=new ArrayList<>();
    private long circleId;
    private int memberType;
    private TextView attention;
    private TextView banned;

    public static MemberManagementFragment newInstance(long circleId, int memberType) {
        Bundle args = new Bundle();
        args.putLong("circleId",circleId);
        args.putInt("memberType",memberType);
        MemberManagementFragment fragment = new MemberManagementFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        circleId = getArguments().getLong("circleId", 0);
        memberType = getArguments().getInt("memberType", 0);
        iMemberManagementPresenter.v2pRequestMemberList(circleId,0,memberType,20, SPUtils.getInstance().getCurrentUid(),false);
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
                iMemberManagementPresenter.v2pRequestMemberList(circleId,0,memberType,20, SPUtils.getInstance().getCurrentUid(),false);
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
                iMemberManagementPresenter.v2pRequestMemberList(circleId,0,memberType,20, SPUtils.getInstance().getCurrentUid(),false);
            }
        });
        refreshLayout.setRefreshFooter(new MaterialFooter(getActivity()));
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int end = adapter.getmList().size() - 1;
                iMemberManagementPresenter.v2pRequestMemberList(circleId,pageDatas.get(end).getJoinTime(),memberType,20,SPUtils.getInstance().getCurrentUid(),true);
            }
        });
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MemberManagerAdapter(getContext());
        adapter.setContext(getActivity());
        adapter.setOnItemClickListen(this);
        mRecyclerView.setAdapter(adapter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_circle_fragment_member_manager, container, false);
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
            iMemberManagementPresenter.v2pPutGiveFollows(bean.getUserInfo());
        } else if (view.getId() == R.id.ll_other) {
            SupportFragment fragment=(SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.OTHERS_FRAGMENT);
            Bundle bundle = new Bundle();
            bundle.putLong("uid", bean.getUserInfo().getUid());
            fragment.setArguments(bundle);
            start(fragment);
        }else if (view.getId() == R.id.im_manage) {
            ToastUtil.show("禁言");
            showPopuwindow(view,bean);
        }
    }

    private void showPopuwindow(View v,final MemberManagementInfo.PageDatasBean bean) {
        View attentionAndBannedView=LayoutInflater.from(getContext()).inflate(R.layout.module_mine_comment_attention_banned, null, false);
        final PopupWindow window = new PopupWindow(attentionAndBannedView, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        window.setOutsideTouchable(true);
        window.setTouchable(true);
        window.showAsDropDown(v, -v.getWidth() - 20, -v.getHeight());
        attention = attentionAndBannedView.findViewById(R.id.tv_attention);
        banned = attentionAndBannedView.findViewById(R.id.tv_banned);
        if (bean.getUserInfo().getFollow() == 0) {
            if (bean.getUserInfo().getFollow() == 0) {
                attention.setText("关注");
            } else {
                attention.setText("已关注");
            }
        }
        //关注
        attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iMemberManagementPresenter.v2pPutGiveFollows(bean.getUserInfo());
                window.dismiss();
            }
        });
        //禁言
        banned.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("禁言");
                //先禁言1天，要改
                //（0：取消禁言 1：禁言），周一核对，要改
                final EditText editText = new EditText(getContext());
                new AlertDialog.Builder(getContext()).setTitle("请输入禁言天数")
                        .setIcon(android.R.drawable.sym_def_app_icon)
                        .setView(editText)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                iMemberManagementPresenter.v2pRequestForbidMember(circleId,bean,editText.getText().toString().trim());
                            }
                        }).setNegativeButton("取消",null).show();
                window.dismiss();
            }
        });
    }

    //禁言返回
    @Override
    public void p2vReturnForbidMember(MemberManagementInfo.PageDatasBean bean) {
        if (bean.getForbid() == 1) {
            banned.setText("解除禁言");
            ToastUtil.show("解除禁言");
        }else {
            banned.setText("禁言");
            ToastUtil.show("禁言");
        }
    }
}
