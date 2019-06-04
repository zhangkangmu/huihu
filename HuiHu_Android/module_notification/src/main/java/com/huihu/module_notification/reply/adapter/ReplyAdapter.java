package com.huihu.module_notification.reply.adapter;

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
import com.huihu.module_notification.reply.entity.ReplyBean;
import com.huihu.module_notification.reply.replyinterface.IReplyPresenter;
import com.huihu.uilib.util.MarkUtil;
import com.huihu.uilib.customize.CategoryPicturesView;
import com.huihu.uilib.customize.RedPointView;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.def.NetDataBoolean;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by wangjing on 2019/3/10 0010
 * description:
 */
public class ReplyAdapter extends RecyclerView.Adapter<ReplyAdapter.ReplyViewHolder> {

    private List<ReplyBean> beanList;
    private final IReplyPresenter iReplyPresenter;
    private final int type;
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getTag() instanceof ReplyBean) {
                ReplyBean bean = (ReplyBean) v.getTag();
                if (v.getId() == R.id.cl_answer) {
                    iReplyPresenter.v2pReadAnswer(type, bean);
                } else if (v.getId() == R.id.tv_question_title) {
                    iReplyPresenter.v2pReadQuestion(type, bean);
                } else if (v.getId() == R.id.cl_head){
                    iReplyPresenter.v2pLookOtherPeople(bean);
                }
            }
        }
    };

    public ReplyAdapter(IReplyPresenter iReplyPresenter, int type) {
        this.iReplyPresenter = iReplyPresenter;
        this.type = type;
    }

    public List<ReplyBean> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<ReplyBean> beanList) {
        this.beanList = beanList;
    }

    @NonNull
    @Override
    public ReplyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_notification_item_reply, viewGroup, false);
        return new ReplyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReplyViewHolder holder, int i) {
        ReplyBean bean = beanList.get(i);
        Context context = holder.itemView.getContext();
        TextViewUtils.setTextFakeBold(holder.tvUserName);
        TextViewUtils.setTextFakeBold(holder.tvQuestionTitle);
        ImgTools.showImageView(context, bean.getUserInfo().getUserHeadImg_120(), holder.ivUserHead);
        holder.tvUserName.setText(bean.getUserInfo().getNickName());
        holder.tvTime.setText(TimeFormatUtils.toHuihuTime(bean.getEditTime()));
        holder.tvAnswerContent.setText(bean.getBriefContent());
        holder.tvQuestionTitle.setText(bean.getQuestion().getTitle());
        holder.viewPictures.setUrls(bean.getUrls());
        holder.redPointNoRead.setVisibility(bean.getMessageStatus() == NetDataBoolean.NET_FALSE ? View.VISIBLE : View.GONE);
        MarkUtil.markPerson(context, bean.getUserInfo().getIncMin(), holder.ivMark);
        holder.clAnswer.setTag(bean);
        holder.tvQuestionTitle.setTag(bean);
        holder.clHead.setTag(bean);
        holder.tvQuestionTitle.setOnClickListener(listener);
        holder.clAnswer.setOnClickListener(listener);
        holder.clHead.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    static class ReplyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.tv_question_title)
        TextView tvQuestionTitle;
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
        @BindView(R2.id.cl_answer)
        ConstraintLayout clAnswer;
        @BindView(R2.id.cl_head)
        ConstraintLayout clHead;

        public ReplyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
