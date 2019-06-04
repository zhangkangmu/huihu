package com.huihu.module_notification.commentdetails.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huihu.commonlib.utils.BitmapUtils;
import com.huihu.uilib.util.ImgTools;
import com.huihu.module_notification.R;
import com.huihu.module_notification.commentdetails.entity.CommentChild;
import com.huihu.module_notification.commentdetails.entity.CommentHead;
import com.huihu.module_notification.commentdetails.entity.CommentTwo;
import com.huihu.module_notification.commentdetails.entity.ImageInfo;
import com.huihu.module_notification.commentdetails.entity.UserInfo;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.util.CountUtil;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.List;

/**
 * Created by jiangwensong on 2019/4/2.
 * description：
 */
public class CommentDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "CommentChildAdapter";

    private Context mContext;
    private List<CommentTwo> mCommentTwoList;
    private View.OnClickListener mOnclickListener;
    private CommentHead mCommentHeader;

    private static final int TYPE_HEAD = 0;
    private static final int TYPE_COMMENT_CHILD = 1;
    public static final int TYPE_NO_MORE = 3;
    private boolean mIsNoMore = false;

    public static final String COMMENT_ID = "commentId";
    public static final String COMMENT_LEVEL = "commentLevel";//点赞PutGiveFollows:1：内容 2：评论 3：二级评论
    public static final String IS_AGREE = "isAgree";
    public static final String COMMENT_UID = "commentUid";
    public static final String COMMENT_CHILD_ID = "commentChildId";
    public static final String COMMENT_NICK_NAME = "commentNickName";
    public static final String AGREE_COUNT = "agreeCount";

    public CommentDetailsAdapter(Context context){
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        if (type == TYPE_NO_MORE){
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.module_notification_item_comment_no_more, viewGroup, false);
            return new CommentNoMoreViewHolder(view);
        }else if(type == TYPE_COMMENT_CHILD){
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.module_notification_item_comment_details, viewGroup, false);
            return new CommentChildViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if(viewHolder instanceof CommentChildViewHolder){
            if (position == 0){
                ((CommentChildViewHolder)viewHolder).llReplyCount.setVisibility(View.VISIBLE);
                ((CommentChildViewHolder) viewHolder).setView(mCommentHeader,position );
                ((CommentChildViewHolder) viewHolder).setOnClickListener(mOnclickListener);
                ((CommentChildViewHolder) viewHolder).setData(mCommentHeader,position);
            }else {
                ((CommentChildViewHolder)viewHolder).llReplyCount.setVisibility(View.GONE);
                ((CommentChildViewHolder) viewHolder).setView(mCommentTwoList.get(position - 1),position );
                ((CommentChildViewHolder) viewHolder).setOnClickListener(mOnclickListener);
                ((CommentChildViewHolder) viewHolder).setData(mCommentTwoList.get(position - 1),position);
            }
        }else {

        }

    }

    public void setNoMore(boolean b){
        if(b){
            mCommentTwoList.add(new CommentTwo());
            mIsNoMore = b;
            notifyItemChanged(getItemCount() - 1);
        }else {
            mIsNoMore = b;
        }
    }

    @Override
    public int getItemCount() {
        if (mCommentTwoList == null){
            return 1;
        }
        return mCommentTwoList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (mIsNoMore && position == getItemCount() - 1){
            return TYPE_NO_MORE;
        }else {
            return TYPE_COMMENT_CHILD;
        }
    }

    public List<CommentTwo> getDatas(){
        return mCommentTwoList;
    }

    public void setDatas(CommentChild commentChild){
        mCommentTwoList = commentChild.getPageDatas();
        //notifyDataSetChanged();
    }
    public void setHead(CommentHead commentHead){
        mCommentHeader = commentHead;
        notifyItemChanged(0);
    }

    public void setOnClickListener(View.OnClickListener listener){
        mOnclickListener = listener;
        //notifyDataSetChanged();
    }

    class CommentChildViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        RoundImageView rivAvatar;
        ImageView ivAvatarMark;
        TextView tvNickName;
        TextView tvAuthor;
        ImageView ivLinkArrow;
        TextView tvRepliedName;
        ImageView ivMore;
        TextView tvCommentText;
        RecyclerView rvImages;
        ImageView ivCommentImageBig;
        TextView tvCommentTime;
        ImageView ivCommentReply;
        ImageView ivLike;
        TextView tvLikeCount;
        LinearLayout llReplyCount;
        TextView tvReplyCount;
        LinearLayout llLike;

        public CommentChildViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            rivAvatar = itemView.findViewById(R.id.riv_avatar);
            ivAvatarMark = itemView.findViewById(R.id.iv_avatar_mark);
            tvNickName = itemView.findViewById(R.id.tv_nick_name);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            ivLinkArrow = itemView.findViewById(R.id.iv_link_arrow);
            tvRepliedName = itemView.findViewById(R.id.tv_replied_name);
            ivMore = itemView.findViewById(R.id.iv_more);
            tvCommentText = itemView.findViewById(R.id.tv_comment_text);
            rvImages = itemView.findViewById(R.id.rv_images);
            ivCommentImageBig = itemView.findViewById(R.id.iv_comment_image_big);
            tvCommentTime = itemView.findViewById(R.id.tv_comment_time);
            ivCommentReply = itemView.findViewById(R.id.iv_comment_reply);
            ivLike = itemView.findViewById(R.id.iv_like);
            tvLikeCount = itemView.findViewById(R.id.tv_like_count);
            llReplyCount = itemView.findViewById(R.id.include_reply_count);
            tvReplyCount = itemView.findViewById(R.id.tv_reply_count);
            llLike = itemView.findViewById(R.id.ll_like);
        }

        public void setOnClickListener(View.OnClickListener listener){
            rivAvatar.setOnClickListener(listener);
            tvNickName.setOnClickListener(listener);
            tvRepliedName.setOnClickListener(listener);
            tvCommentText.setOnClickListener(listener);
            ivCommentReply.setOnClickListener(listener);
            //ivLike.setOnClickListener(listener);
            ivMore.setOnClickListener(listener);
            llLike.setOnClickListener(listener);
        }


        //二级评论数据
        public void setData(CommentTwo commentTwo, int position){
            long commentId = commentTwo.getCommentId();

            Bundle bundle = new Bundle();
            bundle.putLong(COMMENT_ID,commentId);
            bundle.putInt(COMMENT_LEVEL,3);
            bundle.putInt(IS_AGREE,commentTwo.getIsAgree());
            bundle.putLong(COMMENT_UID,commentTwo.getUserInfo().getUid());
            bundle.putLong(COMMENT_CHILD_ID,commentTwo.getCommentChildId());
            bundle.putString(COMMENT_NICK_NAME, commentTwo.getUserInfo().getNickName());
            bundle.putInt(AGREE_COUNT,commentTwo.getAgreeCount());

            ivLike.setTag(bundle);
            ivCommentReply.setTag(bundle);
            tvCommentText.setTag(bundle);
            ivMore.setTag(bundle);
            llLike.setTag(bundle);

        }

        //二级评论界面
        public void setView( CommentTwo commentTwo,int position){
            Log.d(TAG, "position " + position);
            UserInfo userInfo = commentTwo.getUserInfo();
            List<ImageInfo> imageList = commentTwo.getImages();

            tvLikeCount.setText(CountUtil.toHuihuCount(commentTwo.getAgreeCount()));

            //tvCommentText.setWidth((int) (ivMore.getX() + ivMore.getWidth() - tvNickName.getX()));
            tvCommentText.setText(commentTwo.getComment());
            tvCommentTime.setText(TimeFormatUtils.toHuihuTime(commentTwo.getCommentTime()));

            if(commentTwo.getIdeaAuthId() == userInfo.getUid()){
                tvAuthor.setVisibility(View.VISIBLE);
            }else {
                tvAuthor.setVisibility(View.GONE);
            }

            if(commentTwo.getCommentType() == 2){
                ivLinkArrow.setVisibility(View.VISIBLE);
                tvRepliedName.setVisibility(View.VISIBLE);
                tvRepliedName.setText(commentTwo.getAtNickName());
            }else {
                ivLinkArrow.setVisibility(View.GONE);
                tvRepliedName.setVisibility(View.GONE);
            }

            if(!TextUtils.isEmpty(userInfo.getIncMin())){
                ivAvatarMark.setVisibility(View.VISIBLE);
                ImgTools.showImageView(mContext, userInfo.getIncMin(), ivAvatarMark);
            }else {
                ivAvatarMark.setVisibility(View.GONE);
            }

            Glide.with(mContext).asBitmap().load(userInfo.getUserHeadImg_48()).into(rivAvatar);
            tvNickName.setText(userInfo.getNickName());

            if (commentTwo.getIsAgree() == 0) {
                ivLike.setImageBitmap(BitmapUtils.drawableToBitmap(mContext.getResources().getDrawable(R.drawable.module_notification_icon_like)));
            } else {
                ivLike.setImageBitmap(BitmapUtils.drawableToBitmap(mContext.getResources().getDrawable(R.drawable.module_notification_icon_like_blue)));
            }

            //图片数量影响视图格式
            int imageCount = imageList.size();
            Log.d(TAG, "position " + position + "imageSize " + imageCount);
            if (imageCount == 0) {
                rvImages.setVisibility(View.GONE);
                ivCommentImageBig.setVisibility(View.GONE);
            } else if (imageCount == 1) {
                rvImages.setVisibility(View.GONE);
                ivCommentImageBig.setVisibility(View.VISIBLE);
                Glide.with(mContext).asBitmap().load(imageList.get(0).getImageUrl()).into(ivCommentImageBig);
            } else if (imageCount >= 2) {
                rvImages.setVisibility(View.VISIBLE);
                ivCommentImageBig.setVisibility(View.GONE);

                ImageAdapter adapter = new ImageAdapter(mContext);
                adapter.setDatas(imageList);
                rvImages.setAdapter(adapter);
            }
        }

        //一级评论头数据CommentHead 数据
        public void setData(CommentHead commentHead, int position){
            long commentId = commentHead.getCommentId();

            Bundle bundle = new Bundle();
            bundle.putLong(COMMENT_ID,commentId);
            bundle.putInt(COMMENT_LEVEL,2);
            bundle.putInt(IS_AGREE,commentHead.getIsAgree());
            bundle.putLong(COMMENT_UID,commentHead.getUserInfo().getUid());
            //bundle.putLong(COMMENT_CHILD_ID,commentOne.getCommentChildId());
            bundle.putString(COMMENT_NICK_NAME, commentHead.getUserInfo().getNickName());
            bundle.putInt(AGREE_COUNT,commentHead.getAgreeCount());

            ivLike.setTag(bundle);
            ivCommentReply.setTag(bundle);
            tvCommentText.setTag(bundle);
            ivMore.setTag(bundle);
            llLike.setTag(bundle);

        }
        public void setView(CommentHead commentHead, int position){
            CommentHead.UserInfoBean userInfo = commentHead.getUserInfo();
            List<ImageInfo> imageList = commentHead.getCommentImages();

            tvLikeCount.setText(CountUtil.toHuihuCount(commentHead.getAgreeCount()));

            //tvCommentText.setWidth((int) (ivMore.getX() + ivMore.getWidth() - tvNickName.getX()));
            tvCommentText.setText(commentHead.getComment());
            tvCommentTime.setText(TimeFormatUtils.toHuihuTime(commentHead.getCommentTime()));

            //TODO 缺少作者ID
//            if(commentHead.getIdeaAuthId() == userInfo.getUid()){
//                tvAuthor.setVisibility(View.VISIBLE);
//            }else {
//                tvAuthor.setVisibility(View.GONE);
//            }

            ivLinkArrow.setVisibility(View.GONE);
            tvRepliedName.setVisibility(View.GONE);

            if(!TextUtils.isEmpty(userInfo.getIncMin())){
                ivAvatarMark.setVisibility(View.VISIBLE);
                ImgTools.showImageView(mContext, userInfo.getIncMin(), ivAvatarMark);
            }else {
                ivAvatarMark.setVisibility(View.GONE);
            }

            Glide.with(mContext).asBitmap().load(userInfo.getUserHeadImg_48()).into(rivAvatar);
            tvNickName.setText(userInfo.getNickName());

            if (commentHead.getIsAgree() == 0) {
                ivLike.setImageBitmap(BitmapUtils.drawableToBitmap(mContext.getResources().getDrawable(R.drawable.module_notification_icon_like)));
            } else {
                ivLike.setImageBitmap(BitmapUtils.drawableToBitmap(mContext.getResources().getDrawable(R.drawable.module_notification_icon_like_blue)));
            }

            //图片数量影响视图格式
            int imageCount = imageList.size();
            Log.d(TAG, "position " + position + "imageSize " + imageCount);
            if (imageCount == 0) {
                rvImages.setVisibility(View.GONE);
                ivCommentImageBig.setVisibility(View.GONE);
            } else if (imageCount == 1) {
                rvImages.setVisibility(View.GONE);
                ivCommentImageBig.setVisibility(View.VISIBLE);
                Glide.with(mContext).asBitmap().load(imageList.get(0).getImageUrl()).into(ivCommentImageBig);
            } else if (imageCount >= 2) {
                rvImages.setVisibility(View.VISIBLE);
                ivCommentImageBig.setVisibility(View.GONE);

                ImageAdapter adapter = new ImageAdapter(mContext);
                adapter.setDatas(imageList);
                rvImages.setAdapter(adapter);
            }

            tvReplyCount.setText(String.format(mContext.getResources().getString(R.string.module_notification_reply_count), CountUtil.toHuihuCount(commentHead.getReplyNum())));
        }


        /*
        //一级评论头数据
        public void setData(CommentOne commentOne, int position){
            long commentId = commentOne.getCommentId();

            Bundle bundle = new Bundle();
            bundle.putLong(COMMENT_ID,commentId);
            bundle.putInt(COMMENT_LEVEL,2);
            bundle.putInt(IS_AGREE,commentOne.getIsAgree());
            bundle.putLong(COMMENT_UID,commentOne.getUserInfo().getUid());
            //bundle.putLong(COMMENT_CHILD_ID,commentOne.getCommentChildId());
            bundle.putString(COMMENT_NICK_NAME, commentOne.getUserInfo().getNickName());
            bundle.putInt(AGREE_COUNT,commentOne.getAgreeCount());

            ivLike.setTag(bundle);
            ivCommentReply.setTag(bundle);
            tvCommentText.setTag(bundle);
            ivMore.setTag(bundle);
            llLike.setTag(bundle);
        }

        //一级评论头
        public void setView(Context context, CommentOne commentOne, int position){
            UserInfo userInfo = commentOne.getUserInfo();
            List<ImageInfo> imageList = commentOne.getImages();

            tvLikeCount.setText(CountUtil.toHuihuCount(commentOne.getAgreeCount()));

            //tvCommentText.setWidth((int) (ivMore.getX() + ivMore.getWidth() - tvNickName.getX()));
            tvCommentText.setText(commentOne.getComment());
            tvCommentTime.setText(TimeFormatUtils.toHuihuTime(commentOne.getCommentTime()));

            if(commentOne.getIdeaAuthId() == userInfo.getUid()){
                tvAuthor.setVisibility(View.VISIBLE);
            }else {
                tvAuthor.setVisibility(View.GONE);
            }

            ivLinkArrow.setVisibility(View.GONE);
            tvRepliedName.setVisibility(View.GONE);

            if(!TextUtils.isEmpty(userInfo.getIncMin())){
                ivAvatarMark.setVisibility(View.VISIBLE);
                ImgTools.showImageView(mContext, userInfo.getIncMin(), ivAvatarMark);
            }else {
                ivAvatarMark.setVisibility(View.GONE);
            }

            Glide.with(mContext).asBitmap().load(userInfo.getUserHeadImg_48()).into(rivAvatar);
            tvNickName.setText(userInfo.getNickName());

            if (commentOne.getIsAgree() == 0) {
                ivLike.setImageBitmap(BitmapUtils.drawableToBitmap(mContext.getResources().getDrawable(R.drawable.module_home_icon_like)));
            } else {
                ivLike.setImageBitmap(BitmapUtils.drawableToBitmap(mContext.getResources().getDrawable(R.drawable.module_notification_icon_like_blue)));
            }

            //图片数量影响视图格式
            int imageCount = imageList.size();
            Log.d(TAG, "position " + position + "imageSize " + imageCount);
            if (imageCount == 0) {
                rvImages.setVisibility(View.GONE);
                ivCommentImageBig.setVisibility(View.GONE);
            } else if (imageCount == 1) {
                rvImages.setVisibility(View.GONE);
                ivCommentImageBig.setVisibility(View.VISIBLE);
                Glide.with(mContext).asBitmap().load(imageList.get(0).getImageUrl()).into(ivCommentImageBig);
            } else if (imageCount >= 2) {
                rvImages.setVisibility(View.VISIBLE);
                ivCommentImageBig.setVisibility(View.GONE);

                ImageAdapter adapter = new ImageAdapter(mContext, false);
                adapter.setDatas(imageList);
                rvImages.setAdapter(adapter);
            }
            tvReplyCount.setText(String.format(mContext.getResources().getString(R.string.module_home_comment_rely), CountUtil.toHuihuCount(commentOne.getReplyNum())));
        }*/
    }

    //没有更多评论
    class CommentNoMoreViewHolder extends RecyclerView.ViewHolder{
        View itemView;

        public CommentNoMoreViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }

}
