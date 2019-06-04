package com.huihu.module_notification.systemmessage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.systemmessage.entity.SystemMessagePageBean;
import com.huihu.module_notification.systemmessage.interfaces.SystemMessageMVP;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by wangjing on 2019/4/4 0004
 * description:
 */
public class SystemMessageAdapter extends RecyclerView.Adapter<SystemMessageAdapter.SystemMessageViewHolder> {

    private final SystemMessageMVP.IPresenter iPresenter;
    private List<SystemMessagePageBean.SystemMessageBean> beanList;

    private View.OnClickListener detailListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getTag() instanceof SystemMessagePageBean.SystemMessageBean){
                SystemMessagePageBean.SystemMessageBean bean = (SystemMessagePageBean.SystemMessageBean) v.getTag();
                iPresenter.v2pStartOtherFragment(bean);
            }
        }
    };

    public SystemMessageAdapter(SystemMessageMVP.IPresenter presenter) {
        iPresenter = presenter;
    }

    public List<SystemMessagePageBean.SystemMessageBean> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<SystemMessagePageBean.SystemMessageBean> beanList) {
        this.beanList = beanList;
    }

    @NonNull
    @Override
    public SystemMessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.module_notification_item_system_message
                , viewGroup, false);
        return new SystemMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SystemMessageViewHolder holder, int i) {
        SystemMessagePageBean.SystemMessageBean bean = beanList.get(i);
        Context context = holder.itemView.getContext();
        TextViewUtils.setTextFakeBold(holder.tvTitle);
        TextViewUtils.setTextFakeBold(holder.tvType);
        if (TextUtils.isEmpty(bean.getInfo().getTitle())){
            holder.tvTitle.setVisibility(View.GONE);
        } else {
            holder.tvTitle.setVisibility(View.VISIBLE);
            holder.tvTitle.setText(bean.getInfo().getTitle());
        }
        holder.tvTime.setText(TimeFormatUtils.toHuihuTime(bean.getSendTimestamp()));
        holder.tvContent.setText(bean.getInfo().getMsg());
        if (TextUtils.isEmpty(bean.getInfo().getImage())){
            holder.ivPicture.setVisibility(View.GONE);
        } else {
            holder.ivPicture.setVisibility(View.VISIBLE);
            Glide.with(context).load(bean.getInfo().getImage()).into(holder.ivPicture);
        }
        holder.tvDetail.setTag(bean);
        holder.tvDetail.setOnClickListener(detailListener);
    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    static class SystemMessageViewHolder extends RecyclerView.ViewHolder {

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
        @BindView(R2.id.iv_picture)
        ImageView ivPicture;

        SystemMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
