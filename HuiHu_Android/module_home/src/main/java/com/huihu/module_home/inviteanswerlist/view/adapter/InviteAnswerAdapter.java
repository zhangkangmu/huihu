package com.huihu.module_home.inviteanswerlist.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.inviteanswer.entity.RecommendUserModel;
import com.huihu.module_home.inviteanswerlist.inviteanswerlistinterface.IInviteAnswerListPresenter;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.util.MarkUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by ouyangjianfeng on 2019/4/13
 * description:
 */
public class InviteAnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private IInviteAnswerListPresenter mPresenter;
    private Context mContext;
    private boolean mNoMore;
    private List<RecommendUserModel> mDatas;

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_BOTTOM = 1;


    private View.OnClickListener inviteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //            mPresenter.v2pPutInviteUserAnswer();
        }
    };

    public InviteAnswerAdapter(Context context, IInviteAnswerListPresenter presenter) {
        mContext = context;
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
        if (i == TYPE_NORMAL) {
            View view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_home_item_invite_answer, group, false);
            return new ViewHolder(view);
        } else {
            View view = LayoutInflater.from(group.getContext()).inflate(R.layout.uilib_footer_nomore, group, false);
            return new NoMoreViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (holder instanceof ViewHolder) {
            RecommendUserModel userModel = mDatas.get(i);
            ViewHolder viewHolder = (ViewHolder) holder;
            ImgTools.showImageView(mContext, userModel.getUserHeadImg_48(), viewHolder.mIvAvator);
            viewHolder.mTvName.setText(userModel.getNickName());
            viewHolder.mTvInvite.setOnClickListener(inviteListener);
            MarkUtil.markPerson(mContext, userModel.getIncMin(), viewHolder.mIvMark);
        }
    }

    public void setNoMoreDataItem(boolean noMore) {
        mNoMore = noMore;
    }

    public void setData(List<RecommendUserModel> model) {
        mDatas = model;
    }

    public void addData(List<RecommendUserModel> datas) {
        mDatas.addAll(datas);
    }

    @Override
    public int getItemCount() {
        int count = mDatas == null ? 0 : mDatas.size();
        if (mNoMore) {
            count++;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (mNoMore && position == getItemCount() - 1) {
            return TYPE_BOTTOM;
        } else {
            return TYPE_NORMAL;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.iv_avator)
        RoundImageView mIvAvator;
        @BindView(R2.id.iv_mark)
        RoundImageView mIvMark;
        @BindView(R2.id.tv_name)
        TextView mTvName;
        @BindView(R2.id.tv_invite)
        TextView mTvInvite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class NoMoreViewHolder extends RecyclerView.ViewHolder {

        public NoMoreViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
