package com.huihu.module_notification.systemnotification.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.systemnotification.entity.SystemNoticePageBean;
import com.huihu.module_notification.systemnotification.entity.SystemNoticeType;
import com.huihu.module_notification.util.WebUtil;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by wangjing on 2019/3/8 0008
 * description:
 */
public class SystemNotificationAdapter extends RecyclerView.Adapter<SystemNotificationAdapter.SystemNotificationViewHolder> {


    private List<SystemNoticePageBean.SystemNoticeBean> beanList;

    private View.OnClickListener detailLisenter = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getTag() instanceof SystemNoticePageBean.SystemNoticeBean){
                SystemNoticePageBean.SystemNoticeBean bean = (SystemNoticePageBean.SystemNoticeBean) v.getTag();
                WebUtil.startWeb(bean.getInfo().getUrl());
            }
        }
    };

    public List<SystemNoticePageBean.SystemNoticeBean> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<SystemNoticePageBean.SystemNoticeBean> beanList) {
        this.beanList = beanList;
    }

    @NonNull
    @Override
    public SystemNotificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_notification_item_system_notification, viewGroup, false);
        return new SystemNotificationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SystemNotificationViewHolder holder, int i) {
        SystemNoticePageBean.SystemNoticeBean bean = beanList.get(i);
        holder.tvTime.setText(TimeFormatUtils.toNormalTime(bean.getSendTimestamp()));
        SystemNoticePageBean.SystemNoticeInfo info = bean.getInfo();
        if (!TextUtils.isEmpty(info.getTitle())){
            holder.tvTitle.setVisibility(View.VISIBLE);
            holder.tvTitle.setText(info.getTitle());
        } else {
            holder.tvTitle.setVisibility(View.GONE);
        }
        holder.tvContent.setText(info.getMsg());
        if (info.getChildType() == SystemNoticeType.Normal) {
            holder.tvDetail.setVisibility(View.GONE);
        } else {
            holder.tvDetail.setVisibility(View.VISIBLE);
        }
        holder.tvDetail.setTag(bean);
        holder.tvDetail.setOnClickListener(detailLisenter);
        TextViewUtils.setTextFakeBold(holder.tvTitle);
        TextViewUtils.setTextFakeBold(holder.tvType);
    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    static class SystemNotificationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.iv_icon)
        ImageView ivIcon;
        @BindView(R2.id.tv_type)
        TextView tvType;
        @BindView(R2.id.tv_time)
        TextView tvTime;
        @BindView(R2.id.tv_detail)
        TextView tvDetail;
        @BindView(R2.id.tv_title)
        TextView tvTitle;
        @BindView(R2.id.tv_content)
        TextView tvContent;

        SystemNotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
