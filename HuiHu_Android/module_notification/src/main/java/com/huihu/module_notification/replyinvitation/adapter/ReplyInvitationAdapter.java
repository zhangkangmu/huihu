package com.huihu.module_notification.replyinvitation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.replyinvitation.entity.ReplyInvitationPageBean;
import com.huihu.module_notification.replyinvitation.replyinvitationinterface.IReplyInvitationPresenter;
import com.huihu.uilib.util.MarkUtil;
import com.huihu.uilib.customize.RedPointView;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.def.NetDataBoolean;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by wangjing on 2019/3/18 0018
 * description:
 */
public class ReplyInvitationAdapter extends RecyclerView.Adapter<ReplyInvitationAdapter.ReplyInvitationViewHolder> {

    private final IReplyInvitationPresenter iReplyInvitationPresenter;
    private List<ReplyInvitationPageBean.ReplyInvitationBean> beanList;
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (v.getTag() instanceof ReplyInvitationPageBean.ReplyInvitationBean) {
                ReplyInvitationPageBean.ReplyInvitationBean bean = (ReplyInvitationPageBean.ReplyInvitationBean) v.getTag();
                if (id == R.id.cl_head) {
                    iReplyInvitationPresenter.v2pLookOtherPeople(bean);
                } else if (id == R.id.cl_item) {
                    iReplyInvitationPresenter.v2pLookQuestion(bean);
                }
            }
        }
    };


    public ReplyInvitationAdapter(IReplyInvitationPresenter iReplyInvitationPresenter) {
        this.iReplyInvitationPresenter = iReplyInvitationPresenter;
    }


    public List<ReplyInvitationPageBean.ReplyInvitationBean> getSessions() {
        return beanList;
    }

    public void setSessions(List<ReplyInvitationPageBean.ReplyInvitationBean> sessions) {
        this.beanList = sessions;
    }

    @NonNull
    @Override
    public ReplyInvitationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.module_notification_item_reply_invitation, viewGroup, false);
        return new ReplyInvitationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReplyInvitationViewHolder holder, int i) {
        ReplyInvitationPageBean.ReplyInvitationBean bean = beanList.get(i);
        TextViewUtils.setTextFakeBold(holder.tvWhoInvite);
        TextViewUtils.setTextFakeBold(holder.tvUserName);
        Context context = holder.itemView.getContext();
        holder.tvTime.setText(TimeFormatUtils.toNormalTime(bean.getSendTimestamp()));
        holder.tvProblemDescription.setText(bean.getInfo().getMsg());
        holder.tvWhoInvite.setText(bean.getInfo().getNickName()
                + context.getString(R.string.module_notification_invite_you_answer) + bean.getInfo().getIdeaTitle());
        ImgTools.showImageView(context, bean.getInfo().getUserImage(), holder.ivUserHead);
        MarkUtil.markPerson(context, bean.getInfo().getIncMin(), holder.ivMark);
        holder.redPointNoRead.setVisibility(bean.getMessageStatus() == NetDataBoolean.NET_TRUE ? View.GONE : View.VISIBLE);
        holder.tvUserName.setText(bean.getInfo().getNickName());
        holder.clHead.setTag(bean);
        holder.clItem.setTag(bean);
        holder.clItem.setOnClickListener(listener);
        holder.clHead.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    static class ReplyInvitationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.iv_user_head)
        RoundImageView ivUserHead;
        @BindView(R2.id.tv_user_name)
        TextView tvUserName;
        @BindView(R2.id.tv_time)
        TextView tvTime;
        @BindView(R2.id.red_point_no_read)
        RedPointView redPointNoRead;
        @BindView(R2.id.tv_who_invite)
        TextView tvWhoInvite;
        @BindView(R2.id.tv_problem_description)
        TextView tvProblemDescription;
        @BindView(R2.id.cl_head)
        ConstraintLayout clHead;
        @BindView(R2.id.cl_item)
        ConstraintLayout clItem;
        @BindView(R2.id.iv_mark)
        ImageView ivMark;

        ReplyInvitationViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
