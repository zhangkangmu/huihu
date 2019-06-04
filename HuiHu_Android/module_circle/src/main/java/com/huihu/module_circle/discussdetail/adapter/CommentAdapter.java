package com.huihu.module_circle.discussdetail.adapter;

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
import com.huihu.module_circle.R;
import com.huihu.module_circle.discussdetail.entity.CommentBean;
import com.huihu.module_circle.discussdetail.entity.CommentOne;
import com.huihu.module_circle.discussdetail.entity.CommentTwo;
import com.huihu.uilib.commentedit.entity.ImagesBean;
import com.huihu.uilib.commentedit.entity.UserInfoBean;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.util.CountUtil;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.List;

/**
 * Created by jiangwensong on 2019/3/30.
 * description：
 */
public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "CommentAdapter";

    public static final int TYPE_COMMENT_ONE = 0;
    public static final int TYPE_COMMENT_TWO = 1;
    public static final int TYPE_MORE = 2;
    public static final int TYPE_NO_MORE = 3;

    public static final String COMMENT_ID = "commentId";
    public static final String COMMENT_LEVEL = "commentLevel";//点赞PutGiveFollows:1：内容 2：评论 3：二级评论
    public static final String IS_AGREE = "isAgree";
    public static final String COMMENT_UID = "commentUid";
    public static final String COMMENT_CHILD_ID = "commentChildId";
    public static final String POSITION = "position";
    public static final String COMMENT_NICK_NAME = "commentNickName";
    public static final String AGREE_COUNT = "agreeCount";

    private Context mContext;
    private CommentBean mCommentBean;
    private List<CommentOne> mCommentList;
    private View.OnClickListener mOnClickListener;
    private boolean mIsNoMore = false;


    public CommentAdapter(Context context){
        mContext = context;
    }

    public void setData(CommentBean datas){
        mCommentList = datas.getPageDatas();
        //notifyDataSetChanged();
    }

    public void setNoMore(boolean b){
        if(b){
            mCommentList.add(new CommentOne());
            mIsNoMore = b;
            notifyItemChanged(getItemCount() - 1);
        }else {
            mIsNoMore = b;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mIsNoMore && position == getItemCount() - 1){
            return TYPE_NO_MORE;
        }else {
            return TYPE_COMMENT_ONE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        //一级评论与二级评论与查看更多 作为一个元素
        Log.d(TAG, "type = " + type);
        if (type == TYPE_COMMENT_ONE){
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.module_circle_item_comment_one, viewGroup, false);
            return new CommentOneViewHolder(view);
        }else if (type == TYPE_NO_MORE){
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.uilib_footer_nomore, viewGroup, false);
            return new CommentNoMoreViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof CommentOneViewHolder){
            ((CommentOneViewHolder) viewHolder).setView(mContext, mCommentList.get(position), position);
            ((CommentOneViewHolder) viewHolder).setData(mCommentList.get(position), position);
            ((CommentOneViewHolder) viewHolder).setOnclickListener(mOnClickListener);
        }else if(viewHolder instanceof CommentNoMoreViewHolder){

        }

    }

    @Override
    public int getItemCount() {
        if(mCommentList == null){
            return 0;
        }
        return mCommentList.size();
    }

    public void setOnclickListener(View.OnClickListener listener){
        mOnClickListener = listener;
        //notifyDataSetChanged();
    }


    //一级评论
    class CommentOneViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        RoundImageView rivAvatar;
        ImageView ivAvatarMark;
        TextView tvNickName;
        TextView tvAuthor;
        ImageView ivMore;
        TextView tvCommentText;
        RecyclerView rvImages;
        ImageView ivCommentImageBig;
        TextView tvCommentTime;
        ImageView ivCommentReply;
        ImageView ivLike;
        TextView tvLikeCount;
        LinearLayout llLike;

        LinearLayout llCommentTwoFirst;
        LinearLayout llCommentTwoSecond;
        LinearLayout llCommentMore;


        public CommentOneViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            rivAvatar = itemView.findViewById(R.id.riv_avatar);
            ivAvatarMark = itemView.findViewById(R.id.iv_avatar_mark);
            tvNickName = itemView.findViewById(R.id.tv_nick_name);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            ivMore = itemView.findViewById(R.id.iv_more);
            tvCommentText = itemView.findViewById(R.id.tv_comment_text);
            rvImages = itemView.findViewById(R.id.rv_images);
            ivCommentImageBig = itemView.findViewById(R.id.iv_comment_image_big);
            tvCommentTime = itemView.findViewById(R.id.tv_comment_time);
            ivCommentReply = itemView.findViewById(R.id.iv_comment_reply);
            ivLike = itemView.findViewById(R.id.iv_like);
            tvLikeCount = itemView.findViewById(R.id.tv_like_count);
            llLike = itemView.findViewById(R.id.ll_like);

            //二级评论两条与更多
            llCommentTwoFirst = itemView.findViewById(R.id.ll_comment_two_first);
            llCommentTwoSecond = itemView.findViewById(R.id.ll_comment_two_second);
            llCommentMore = itemView.findViewById(R.id.ll_comment_more);

        }

        public void setOnclickListener(View.OnClickListener listener) {
            rivAvatar.setOnClickListener(listener);
            tvNickName.setOnClickListener(listener);
            tvCommentText.setOnClickListener(listener);
            //ivLike.setOnClickListener(listener);
            llCommentMore.setOnClickListener(listener);
            ivCommentReply.setOnClickListener(listener);
            ivMore.setOnClickListener(listener);
            llLike.setOnClickListener(listener);

            new CommentTwoViewHolder(llCommentTwoFirst).setOnclickListener(listener);
            new CommentTwoViewHolder(llCommentTwoSecond).setOnclickListener(listener);

        }

        //设置参数
        public void setData(CommentOne commentOne, int position){
            long commentId = commentOne.getCommentId();
            List<CommentTwo>  commentTwoList = commentOne.getCommentChildsMongodbs();

            Bundle bundle = new Bundle();
            bundle.putLong(COMMENT_ID,commentId);
            bundle.putInt(COMMENT_LEVEL,2);
            bundle.putInt(IS_AGREE,commentOne.getIsAgree());
            bundle.putLong(COMMENT_UID,commentOne.getUserInfo().getUid());
            bundle.putInt(POSITION,position);
            bundle.putString(COMMENT_NICK_NAME,commentOne.getUserInfo().getNickName());
            bundle.putInt(AGREE_COUNT, commentOne.getAgreeCount());

            ivLike.setTag(bundle);
            ivCommentReply.setTag(bundle);
            tvCommentText.setTag(bundle);
            llCommentMore.setTag(bundle);
            ivMore.setTag(bundle);
            llLike.setTag(bundle);

            if(commentTwoList.size() == 1){
                new CommentTwoViewHolder(llCommentTwoFirst).setData(commentTwoList.get(0), position);
            }else if (commentTwoList.size() >= 2){
                new CommentTwoViewHolder(llCommentTwoFirst).setData(commentTwoList.get(0), position);
                new CommentTwoViewHolder(llCommentTwoSecond).setData(commentTwoList.get(1), position);
            }
        }

        public void setView(Context mComtext, CommentOne commentOne, int position) {
            UserInfoBean userInfo = commentOne.getUserInfo();
            List<CommentTwo> commentTwoList = commentOne.getCommentChildsMongodbs();
            List<ImagesBean> imageList = commentOne.getImages();

            tvLikeCount.setText(CountUtil.toHuihuCount(commentOne.getAgreeCount()));

            //tvCommentText.setWidth((int) (ivMore.getX() + ivMore.getWidth() - tvNickName.getX()));
            tvCommentText.setText(commentOne.getComment());
            tvCommentTime.setText(TimeFormatUtils.toHuihuTime(commentOne.getCommentTime()));

            if(commentOne.getIdeaAuthId() == userInfo.getUid()){
                tvAuthor.setVisibility(View.VISIBLE);
            }else {
                tvAuthor.setVisibility(View.GONE);
            }

            if(!TextUtils.isEmpty(userInfo.getIncMin())){
                ivAvatarMark.setVisibility(View.VISIBLE);
                ImgTools.showImageView(mContext, userInfo.getIncMin(), ivAvatarMark);
            }else {
                ivAvatarMark.setVisibility(View.GONE);
            }

            Glide.with(mComtext).asBitmap().load(userInfo.getUserHeadImg_48()).into(rivAvatar);
            tvNickName.setText(userInfo.getNickName());

            if (commentOne.getIsAgree() == 0) {
                ivLike.setImageBitmap(BitmapUtils.drawableToBitmap(mComtext.getResources().getDrawable(R.drawable.module_circle_icon_like)));
            } else {
                ivLike.setImageBitmap(BitmapUtils.drawableToBitmap(mComtext.getResources().getDrawable(R.drawable.module_circle_icon_like_blue)));
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
                Glide.with(mComtext).asBitmap().load(imageList.get(0).getImageUrl()).into(ivCommentImageBig);
            } else if (imageCount >= 2) {
                rvImages.setVisibility(View.VISIBLE);
                ImageAdapter adapter = new ImageAdapter(mContext, false);
                adapter.setDatas(imageList);
                rvImages.setAdapter(adapter);
            }
            //二级评论数量影响视图格式
            int commentTwoSize = commentTwoList.size();
            if (commentTwoSize == 0) {
                llCommentTwoFirst.setVisibility(View.GONE);
                llCommentTwoSecond.setVisibility(View.GONE);
                llCommentMore.setVisibility(View.GONE);
            } else if (commentTwoSize == 1) {
                llCommentTwoFirst.setVisibility(View.VISIBLE);
                llCommentTwoSecond.setVisibility(View.GONE);
                llCommentMore.setVisibility(View.GONE);
                //设置二级评论视图参数
                new CommentTwoViewHolder(llCommentTwoFirst).setView(mComtext, commentTwoList.get(0),position);

            } else if (commentTwoSize >= 2) {
                llCommentTwoFirst.setVisibility(View.VISIBLE);
                llCommentTwoSecond.setVisibility(View.VISIBLE);
                if (commentOne.getReplyNum() > 2) {
                    llCommentMore.setVisibility(View.VISIBLE);
                } else {
                    llCommentMore.setVisibility(View.GONE);
                }
                //设置二级评论视图参数
                new CommentTwoViewHolder(llCommentTwoFirst).setView(mComtext, commentTwoList.get(0), position);
                new CommentTwoViewHolder(llCommentTwoSecond).setView(mComtext, commentTwoList.get(1), position);
            }

            //更多评论
            if (commentOne.getReplyNum() > 2) {
                llCommentMore.setVisibility(View.VISIBLE);
            } else {
                llCommentMore.setVisibility(View.GONE);
            }
        }
    }
    //二级评论
    class CommentTwoViewHolder extends RecyclerView.ViewHolder{
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
        LinearLayout llLike;


        public CommentTwoViewHolder(@NonNull View itemView) {
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
            llLike = itemView.findViewById(R.id.ll_like);
        }

        public void setOnclickListener(View.OnClickListener listener){
            rivAvatar.setOnClickListener(listener);
            tvNickName.setOnClickListener(listener);
            tvCommentText.setOnClickListener(listener);
            //ivLike.setOnClickListener(listener);
            ivCommentReply.setOnClickListener(listener);
            ivMore.setOnClickListener(listener);
            llLike.setOnClickListener(listener);
        }

        public void setData(CommentTwo commentTwo, int position){
            long commentId = commentTwo.getCommentId();

            Bundle bundle = new Bundle();
            bundle.putLong(COMMENT_ID,commentId);
            bundle.putInt(COMMENT_LEVEL,3);
            bundle.putInt(IS_AGREE,commentTwo.getIsAgree());
            bundle.putLong(COMMENT_UID,commentTwo.getUserInfo().getUid());
            bundle.putLong(COMMENT_CHILD_ID,commentTwo.getCommentChildId());
            bundle.putString(COMMENT_NICK_NAME,commentTwo.getUserInfo().getNickName());
            bundle.putInt(AGREE_COUNT, commentTwo.getAgreeCount());

            ivLike.setTag(bundle);
            ivCommentReply.setTag(bundle);
            tvCommentText.setTag(bundle);
            ivMore.setTag(bundle);
            llLike.setTag(bundle);
        }


        private void setView( Context context, CommentTwo commentTwo, int position){

            UserInfoBean userInfo = commentTwo.getUserInfo();
            List<ImagesBean> imageList = commentTwo.getImages();

            tvLikeCount.setText(CountUtil.toHuihuCount(commentTwo.getAgreeCount()));
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
                ivLike.setImageBitmap(BitmapUtils.drawableToBitmap(mContext.getResources().getDrawable(R.drawable.module_circle_icon_like)));
            } else {
                ivLike.setImageBitmap(BitmapUtils.drawableToBitmap(mContext.getResources().getDrawable(R.drawable.module_circle_icon_like_blue)));
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
                ImageAdapter adapter = new ImageAdapter(mContext,true);
                adapter.setDatas(imageList);
                rvImages.setAdapter(adapter);
            }
        }
    }

    //查看更多评论
    class CommentMoreViewHolder extends RecyclerView.ViewHolder{
        View itemView;

        public CommentMoreViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
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
