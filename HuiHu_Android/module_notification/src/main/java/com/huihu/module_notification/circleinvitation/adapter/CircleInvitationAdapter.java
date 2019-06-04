package com.huihu.module_notification.circleinvitation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.circleinvitation.entity.CircleInvitationPageBean;
import com.huihu.module_notification.circleinvitation.entity.CircleInvitationType;
import com.huihu.module_notification.circleinvitation.interfaces.CircleInvitationMVP;
import com.huihu.uilib.util.MarkUtil;
import com.huihu.uilib.customize.RedPointView;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by wangjing on 2019/4/9 0009
 * description:
 */
public class CircleInvitationAdapter extends RecyclerView.Adapter<CircleInvitationAdapter.CircleInvitationViewHolder> {

    private final CircleInvitationMVP.IPresenter iPresenter;
    private List<CircleInvitationPageBean.CircleInvitationData> dataList;
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getTag() instanceof CircleInvitationPageBean.CircleInvitationData) {
                int id = v.getId();
                CircleInvitationPageBean.CircleInvitationData data = (CircleInvitationPageBean.CircleInvitationData) v.getTag();
                if (id == R.id.tv_refuse) {
                    iPresenter.v2pDoSomeThings(data, false);
                } else if (id == R.id.tv_state) {
                    iPresenter.v2pDoSomeThings(data, true);
                } else if (id == R.id.cl_head) {
                    iPresenter.v2pLookOtherPeople(data);
                }
            }
        }
    };

    public CircleInvitationAdapter(CircleInvitationMVP.IPresenter presenter) {
        this.iPresenter = presenter;
    }

    public List<CircleInvitationPageBean.CircleInvitationData> getDataList() {
        return dataList;
    }

    public void setDataList(List<CircleInvitationPageBean.CircleInvitationData> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public CircleInvitationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.module_notification_item_circle_invaitation
                , viewGroup, false);
        return new CircleInvitationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CircleInvitationViewHolder holder, int i) {
        CircleInvitationPageBean.CircleInvitationData data = dataList.get(i);
        Context context = holder.itemView.getContext();
        TextViewUtils.setTextFakeBold(holder.tvUserName);
        TextViewUtils.setTextFakeBold(holder.tvTitle);
        Glide.with(context).load(data.getInfo().getUserImage()).into(holder.ivUserHead);
        holder.tvTime.setText(TimeFormatUtils.toNormalTime(data.getSendTimestamp()));
        holder.tvUserName.setText(data.getInfo().getUserName());
        holder.tvContent.setText(data.getInfo().getCircleName());
        @CircleInvitationType int type = data.getInfo().getChildType();
        changeState(context, data, holder, type);
        holder.tvState.setTag(data);
        holder.tvRefuse.setTag(data);
        holder.clHead.setTag(data);
        holder.tvRefuse.setOnClickListener(listener);
        holder.tvState.setOnClickListener(listener);
        holder.clHead.setOnClickListener(listener);
        MarkUtil.markPerson(context, data.getInfo().getIncMin(), holder.ivMark);
    }

    private void changeState(Context context, CircleInvitationPageBean.CircleInvitationData data, CircleInvitationViewHolder holder, @CircleInvitationType int type) {
        StringBuilder titleText = new StringBuilder();
        titleText.append(data.getInfo().getUserName());
        switch (type) {
            case CircleInvitationType.AllowedManager:
                titleText.append(context.getString(R.string.module_notification_invite_you_manager));
                showOldState(context, holder, R.string.module_notification_allowed);
                break;
            case CircleInvitationType.CancelManagerPermission:
                titleText.append(' ').append(context.getString(R.string.module_notification_cancel_manager));
                holder.tvState.setVisibility(View.GONE);
                holder.tvRefuse.setVisibility(View.GONE);
                break;
            case CircleInvitationType.InviteJoinCircle:
                titleText.append(context.getString(R.string.module_notification_invite_you_join));
                holder.tvRefuse.setVisibility(View.GONE);
                showNewState(context, holder, R.string.module_notification_join);
                break;
            case CircleInvitationType.InviteManagerCircle:
                titleText.append(context.getString(R.string.module_notification_invite_you_manager));
                holder.tvRefuse.setVisibility(View.VISIBLE);
                showNewState(context, holder, R.string.module_notification_allow);
                break;
            case CircleInvitationType.JoinedCircle:
                titleText.append(context.getString(R.string.module_notification_invite_you_join));
                showOldState(context, holder, R.string.module_notification_joined);
                break;
            case CircleInvitationType.ManagerFull:
                titleText.append(context.getString(R.string.module_notification_invite_you_manager));
                showOldState(context, holder, R.string.module_notification_full);
                break;
            case CircleInvitationType.OtherRefuseManagerCircle:
                titleText.append(' ').append(context.getString(R.string.module_notification_refuse_manager));
                holder.tvState.setVisibility(View.GONE);
                holder.tvRefuse.setVisibility(View.GONE);
                break;
            case CircleInvitationType.RefusedManager:
                titleText.append(context.getString(R.string.module_notification_invite_you_manager));
                showOldState(context, holder, R.string.module_notification_refused);
                break;
            default:
                break;
        }
        holder.tvTitle.setText(titleText.toString());
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    private void showOldState(Context context, CircleInvitationViewHolder holder, @StringRes int stringId) {
        holder.tvState.setVisibility(View.VISIBLE);
        holder.tvRefuse.setVisibility(View.GONE);
        holder.tvState.setText(stringId);
        holder.tvState.setTextColor(ContextCompat.getColor(context, R.color.module_notification_invite_old_state));
        holder.tvState.setBackground(null);
    }

    private void showNewState(Context context, CircleInvitationViewHolder holder, @StringRes int stringId) {
        holder.tvState.setVisibility(View.VISIBLE);
        holder.tvState.setText(stringId);
        holder.tvState.setTextColor(ContextCompat.getColor(context, R.color.white));
        holder.tvState.setBackgroundResource(R.drawable.background_global_blue_full);
    }

    static class CircleInvitationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.iv_user_head)
        RoundImageView ivUserHead;
        @BindView(R2.id.tv_user_name)
        TextView tvUserName;
        @BindView(R2.id.tv_time)
        TextView tvTime;
        @BindView(R2.id.red_point_no_read)
        RedPointView redPointNoRead;
        @BindView(R2.id.tv_title)
        TextView tvTitle;
        @BindView(R2.id.tv_content)
        TextView tvContent;
        @BindView(R2.id.tv_state)
        TextView tvState;
        @BindView(R2.id.tv_refuse)
        TextView tvRefuse;
        @BindView(R2.id.line)
        View line;
        @BindView(R2.id.iv_mark)
        ImageView ivMark;
        @BindView(R2.id.cl_head)
        ConstraintLayout clHead;

        CircleInvitationViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
