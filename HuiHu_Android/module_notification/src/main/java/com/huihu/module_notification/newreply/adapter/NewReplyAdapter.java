package com.huihu.module_notification.newreply.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.newreply.entity.NewReplyPageBean;
import com.huihu.module_notification.newreply.interfaces.NewReplyMVP;
import com.huihu.uilib.util.MarkUtil;
import com.huihu.uilib.customize.CategoryPicturesView;
import com.huihu.uilib.customize.RedPointView;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by wangjing on 2019/4/10 0010
 * description:
 */
public class NewReplyAdapter extends RecyclerView.Adapter<NewReplyAdapter.NewReplyViewHolder> {

    private final NewReplyMVP.IPresenter iPresenter;
    private List<NewReplyPageBean.RecentAnswerBean> beans;
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (v.getTag() instanceof NewReplyPageBean.RecentAnswerBean){
                NewReplyPageBean.RecentAnswerBean bean = (NewReplyPageBean.RecentAnswerBean) v.getTag();
                if (id == R.id.cl_head){
                    iPresenter.v2pLookOtherPeople(bean);
                } else if (id == R.id.cl_answer){
                    iPresenter.v2pLookAnswer(bean);
                }
            }
        }
    };

    public NewReplyAdapter(NewReplyMVP.IPresenter iPresenter) {
        this.iPresenter = iPresenter;
    }

    public List<NewReplyPageBean.RecentAnswerBean> getBeans() {
        return beans;
    }

    public void setBeans(List<NewReplyPageBean.RecentAnswerBean> beans) {
        this.beans = beans;
    }

    @NonNull
    @Override
    public NewReplyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.module_notification_item_new_reply, viewGroup, false);
        return new NewReplyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewReplyViewHolder holder, int i) {
        Context context = holder.itemView.getContext();
        NewReplyPageBean.RecentAnswerBean bean = beans.get(i);
        TextViewUtils.setTextFakeBold(holder.tvUserName);
        ImgTools.showImageView(context, bean.getUserInfo().getUserHeadImg_120(), holder.ivUserHead);
        holder.tvUserName.setText(bean.getUserInfo().getNickName());
        holder.viewPictures.setUrls(bean.getUrls());
        holder.tvTime.setText(TimeFormatUtils.toHuihuTime(bean.getEditTime()) + context.getString(R.string.module_notification_reply));
        if (TextUtils.isEmpty(bean.getBriefContent())) {
            holder.tvAnswerContent.setVisibility(View.GONE);
        } else {
            holder.tvAnswerContent.setVisibility(View.VISIBLE);
            holder.tvAnswerContent.setText(bean.getBriefContent());
        }
        holder.tvUserName.setText(bean.getUserInfo().getNickName());
        MarkUtil.markPerson(context, bean.getUserInfo().getIncMin(), holder.ivMark);
        holder.clAnswer.setTag(bean);
        holder.clHead.setTag(bean);
        holder.clHead.setOnClickListener(listener);
        holder.clAnswer.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return beans == null ? 0 : beans.size();
    }

    static class NewReplyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.iv_user_head)
        RoundImageView ivUserHead;
        @BindView(R2.id.tv_user_name)
        TextView tvUserName;
        @BindView(R2.id.tv_time)
        TextView tvTime;
        @BindView(R2.id.red_point_no_read)
        RedPointView redPointNoRead;
        @BindView(R2.id.tv_answer_content)
        TextView tvAnswerContent;
        @BindView(R2.id.view_pictures)
        CategoryPicturesView viewPictures;
        @BindView(R2.id.iv_mark)
        ImageView ivMark;
        @BindView(R2.id.cl_head)
        ConstraintLayout clHead;
        @BindView(R2.id.cl_answer)
        ConstraintLayout clAnswer;

        NewReplyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
