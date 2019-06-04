package com.huihu.module_notification.fans.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
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
import com.huihu.module_notification.fans.entity.FollowPageBean;
import com.huihu.module_notification.fans.fansinterface.IFansPresenter;
import com.huihu.uilib.util.MarkUtil;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by wangjing on 2019/3/9 0009
 * description:
 */
public class FansAdapter extends RecyclerView.Adapter<FansAdapter.FansViewHolder> {

    private List<FollowPageBean.DataBean> dataList;
    private final int type;
    private final IFansPresenter presenter;

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (v.getTag() instanceof FollowPageBean.DataBean) {
                FollowPageBean.DataBean bean = (FollowPageBean.DataBean) v.getTag();
                if (id == R.id.tv_do) {
                    presenter.v2pPutGiveFollows(type, bean);
                } else if (id == R.id.cl_head) {
                    presenter.v2pLookOtherPeople(bean);
                }
            }
        }
    };

    public List<FollowPageBean.DataBean> getDataList() {
        return dataList;
    }

    public FansAdapter(int type, IFansPresenter presenter) {
        this.presenter = presenter;
        this.type = type;
    }

    public void setDataList(List<FollowPageBean.DataBean> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public FansViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_notification_item_fans, viewGroup, false);
        return new FansViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FansViewHolder holder, int i) {
        FollowPageBean.DataBean bean = dataList.get(i);
        Context context = holder.itemView.getContext();
        ImgTools.showImageView(context, bean.getUserHeadImg(), holder.ivHead);
        TextViewUtils.setTextFakeBold(holder.tvName);
        holder.tvName.setText(bean.getNickName());
        holder.tvTime.setText(TimeFormatUtils.toNormalTime(bean.getFollowTime()));
        holder.tvDo.setTag(bean);
        if (bean.isFollow()) {
            holder.tvDo.setText(R.string.module_notification_followed);
            holder.tvDo.setBackground(ContextCompat.getDrawable(context, R.drawable.module_notification_bg_followed));
            holder.tvDo.setTextColor(ContextCompat.getColor(context, R.color.module_notification_followed_text));
        } else {
            holder.tvDo.setText(R.string.module_notification_follow_it);
            holder.tvDo.setBackground(ContextCompat.getDrawable(context, R.drawable.background_global_blue_full));
            holder.tvDo.setTextColor(ContextCompat.getColor(context, R.color.white));
        }
        holder.tvDo.setOnClickListener(listener);
        holder.clHead.setTag(bean);
        holder.clHead.setOnClickListener(listener);
        MarkUtil.markPerson(context, bean.getIncMin(), holder.ivMark);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    static class FansViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.iv_head)
        RoundImageView ivHead;
        @BindView(R2.id.tv_name)
        TextView tvName;
        @BindView(R2.id.tv_time)
        TextView tvTime;
        @BindView(R2.id.tv_do)
        TextView tvDo;
        @BindView(R2.id.cl_head)
        ConstraintLayout clHead;
        @BindView(R2.id.iv_mark)
        ImageView ivMark;

        FansViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
