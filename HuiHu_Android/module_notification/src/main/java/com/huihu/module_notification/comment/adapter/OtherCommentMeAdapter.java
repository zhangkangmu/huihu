package com.huihu.module_notification.comment.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huihu.commonlib.base.BaseApplication;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_notification.comment.CommentConstant;
import com.huihu.module_notification.comment.entity.CommentPageBean;
import com.huihu.uilib.util.MarkUtil;
import com.huihu.uilib.customize.source.SourceViewMode;
import com.huihu.module_notification.entity.NetImageBean;
import com.huihu.uilib.customize.ItemEqualSpacing;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.uilib.customize.source.MorePictureAdapter;
import com.huihu.uilib.customize.source.SourceView;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by wangjing on 2019/3/10 0010
 * description:
 */
public class OtherCommentMeAdapter extends RecyclerView.Adapter<OtherCommentMeAdapter.OtherCommentMeViewHolder> {

    private Context mContext;
    private List<CommentPageBean.DataBean> datas;
    private View.OnClickListener mListener;
    private static final String SUFFIX = BaseApplication.getApplication().getString(R.string.module_notification_comment_you);

    public static final String COMMENT_ID = "commentId";
    public static final String IDEA_ID = "ideaId";
    public static final String ABOUNT_TYPE = "abountType";
    public static  final String COMMENT_UID = "commentUid";
    public static  final String COMMENT_NICK_NAME = "commentNickname";
    public static final String ABOUNT_ID = "abountCommentId";
    public static final String FIND_COMMENT_ID = "findCommentId";

    public OtherCommentMeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public List<CommentPageBean.DataBean> getDatas() {
        return datas;
    }

    public void setDatas(List<CommentPageBean.DataBean> datas) {
        this.datas = datas;
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public OtherCommentMeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_notification_item_other_comment_me, viewGroup, false);
        return new OtherCommentMeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull OtherCommentMeViewHolder holder, int i) {
        CommentPageBean.DataBean bean = datas.get(i);
        TextViewUtils.setTextFakeBold(holder.tvUserName);
        Glide.with(mContext).load(bean.getUserInfo().getUserHeadImg_120()).into(holder.ivUserHead);
        holder.tvUserName.setText(bean.getUserInfo().getNickName());
        holder.tvTime.setText(TimeFormatUtils.toHuihuTime(bean.getCommentTime()) + SUFFIX);
        holder.tvOtherCommentContent.setText(bean.getComment());
        bindCommentImages(bean, holder);
        bindSourceView(holder.sourceOtherComment, bean);
        holder.tvOtherCommentContent.setOnClickListener(mListener);
        holder.setOnClickListener(mListener);
        holder.setTag(bean);
        MarkUtil.markPerson(mContext, bean.getUserInfo().getIncMin(), holder.ivMark);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }


    private void bindCommentImages(CommentPageBean.DataBean bean, OtherCommentMeViewHolder holder){
        if (bean.getCommentImages() != null && bean.getCommentImages().size() > 0){
            if (bean.getCommentImages().size() == 1){
                holder.ivOtherCommentSinglePicture.setVisibility(View.VISIBLE);
                holder.rvOtherCommentMorePicture.setVisibility(View.GONE);
                Glide.with(mContext).load(bean.getCommentImages().get(0).getImageUrl()).into(holder.ivOtherCommentSinglePicture);
            } else {
                holder.ivOtherCommentSinglePicture.setVisibility(View.GONE);
                holder.rvOtherCommentMorePicture.setVisibility(View.VISIBLE);
                bindCommentRecyclerView(bean.getCommentImages(), holder.rvOtherCommentMorePicture);
            }
        } else {
            holder.ivOtherCommentSinglePicture.setVisibility(View.GONE);
            holder.rvOtherCommentMorePicture.setVisibility(View.GONE);
        }
    }


    private void bindCommentRecyclerView(List<NetImageBean> beans, RecyclerView rv){
        if (rv.getLayoutManager() == null){
            GridLayoutManager manager = new GridLayoutManager(mContext, 3);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            rv.setLayoutManager(manager);
            rv.setAdapter(new MorePictureAdapter());
            rv.addItemDecoration(new ItemEqualSpacing(mContext, 3, 3));
        } else {
            MorePictureAdapter adapter = (MorePictureAdapter) rv.getAdapter();
            List<String> urls = adapter.getUrlList();
            if (urls.size() > 0){
                urls.clear();
                adapter.notifyDataSetChanged();
            }
            for (NetImageBean bean : beans){
                urls.add(bean.getImageUrl());
                adapter.notifyItemChanged(adapter.getItemCount() - 1);
            }
        }
    }

    private void bindSourceView(SourceView view, CommentPageBean.DataBean bean){
       if (bean.getAboutType() == CommentConstant.REPLY_TYPE || bean.getAboutType() == CommentConstant.DISCUSS_TYPE){
           if (bean.getAboutState() == CommentConstant.CONTENT_DISCONTINUED){
               view.setMode(SourceViewMode.USER_COMMENT_DISCONTINUED);
           } else {
               view.setText(R.id.tv_source_article, bean.getAboutContent());
               if (bean.getAboutImages() == null || bean.getAboutImages().size() == 0){
                   view.setMode(SourceViewMode.ARTICLE_TEXT);
               } else {
                   view.setMode(SourceViewMode.ARTICLE_PICTURE_TEXT);
                   view.setImageUrl(R.id.iv_source_article, bean.getAboutImages().get(0).getImageUrl());
               }
           }
       } else {
           if (bean.getAboutState() == CommentConstant.COMMENT_DISCONTINUED){
               view.setMode(SourceViewMode.USER_COMMENT_DISCONTINUED);
           } else {
               if (bean.getAboutImages() != null && bean.getAboutImages().size() > 0){
                   if (bean.getAboutImages().size() == 1){
                       view.setMode(SourceViewMode.USER_COMMENT_SINGLE_PICTURE);
                       view.setImageUrl(R.id.iv_source_single_picture, bean.getAboutImages().get(0).getImageUrl());
                   } else {
                       view.setMode(SourceViewMode.USER_COMMENT_MORE_PICTURE);
                       MorePictureAdapter adapter = view.getAdapter();
                       if (adapter.getUrlList().size() > 0){
                           adapter.getUrlList().clear();
                           adapter.notifyDataSetChanged();
                       }
                       for (NetImageBean imagesBean : bean.getAboutImages()){
                           adapter.getUrlList().add(imagesBean.getImageUrl());
                           adapter.notifyItemInserted(view.getAdapter().getItemCount());
                       }
                   }
               } else {
                   view.setMode(SourceViewMode.USER_COMMENT_SINGLE_PICTURE);
               }
           }
           view.setImageUrl(R.id.iv_source_user_head, bean.getAboutUserInfo().getUserHeadImg_120());
           view.setText(R.id.tv_source_user_name, bean.getAboutUserInfo().getNickName());
           view.setText(R.id.tv_source_comment_content, bean.getAboutContent());
       }
    }

     class OtherCommentMeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.iv_user_head)
        RoundImageView ivUserHead;
        @BindView(R2.id.tv_user_name)
        TextView tvUserName;
        @BindView(R2.id.tv_time)
        TextView tvTime;
        @BindView(R2.id.tv_reply)
        TextView tvReply;
        @BindView(R2.id.tv_other_comment_content)
        TextView tvOtherCommentContent;
        @BindView(R2.id.source_other_comment)
        SourceView sourceOtherComment;
        @BindView(R2.id.iv_other_comment_single_picture)
        ImageView ivOtherCommentSinglePicture;
        @BindView(R2.id.rv_other_comment_more_picture)
        RecyclerView rvOtherCommentMorePicture;
        @BindView(R2.id.iv_mark)
        ImageView ivMark;

        OtherCommentMeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnClickListener(View.OnClickListener listener){
            tvReply.setOnClickListener(listener);
            tvOtherCommentContent.setOnClickListener(listener);
            sourceOtherComment.setOnClickListener(listener);
        }

        public void setTag(CommentPageBean.DataBean bean){
            Bundle bundle = new Bundle();
            bundle.putLong(COMMENT_ID, bean.getCommentId());
            bundle.putLong(IDEA_ID, bean.getIdeaId());
            bundle.putInt(ABOUNT_TYPE,bean.getAboutType());
            bundle.putLong(COMMENT_UID,bean.getCommentUid());
            bundle.putString(COMMENT_NICK_NAME,bean.getUserInfo().getNickName());
            bundle.putLong(ABOUNT_ID,bean.getAboutId());
            bundle.putLong(FIND_COMMENT_ID,bean.getFindCommentId());

            tvReply.setTag(bundle);
            tvOtherCommentContent.setTag(bundle);
            sourceOtherComment.setTag(bundle);
        }
    }
}
