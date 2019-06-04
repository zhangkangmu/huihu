package com.huihu.module_home.questionandanswerlist.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihu.uilib.util.ImgTools;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.questionandanswerlist.entity.AnswerModel;
import com.huihu.module_home.questionandanswerlist.questionandanswerlistinterface.IQuestionAndAnswerListPresenter;
import com.huihu.uilib.checklogin.annotation.CheckLogin;
import com.huihu.uilib.customize.HHShareDialog;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.def.FollowType;
import com.huihu.uilib.util.CountUtil;
import com.huihu.uilib.util.DensityUtil;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by ouyangjianfeng on 2019/3/30
 * description:
 */
public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {

    private static final int COUNT_ONE = 1;
    private static final int COUNT_TWO = 2;
    private static final int COUNT_THREE = 3;

    private static final int RADIUS_IMAGE = 2;//图片的圆角

    private static final int UNFOLLOW = 0;//未关注
    private static final int FOLLOWED = 1;//关注

    private Context mContext;
    private IQuestionAndAnswerListPresenter mPresenter;
    private OnMyItemClickListener mListener;
    private List<AnswerModel.PageDatasBean> mAnswerModelList = new ArrayList<>();
    private int mScreenWidth;

    private View.OnClickListener shareListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            HHShareDialog shareDialog = new HHShareDialog(mContext);
            shareDialog.showDialog();
        }
    };

    private View.OnClickListener attentionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (null != mPresenter) {
                int position = (int) v.getTag();
                setFollow(position);
            }
        }
    };

    @CheckLogin
    private void setFollow(int position) {
        AnswerModel.PageDatasBean bean = mAnswerModelList.get(position);
        int follow = bean.getFollow();//0 未关注  1 已关注
        bean.setFollow(follow == UNFOLLOW ? FOLLOWED : UNFOLLOW);
        notifyItemChanged(position);
        mPresenter.v2pPutGiveFollows(bean.getUserInfo().getUid(), FollowType.HUMAN,
                follow == UNFOLLOW ? FOLLOWED : UNFOLLOW, SPUtils.getInstance().getCurrentUid());
    }

    public void setListener(OnMyItemClickListener listener) {
        mListener = listener;
    }

    public AnswerAdapter(Context context, IQuestionAndAnswerListPresenter presenter) {
        mContext = context;
        mPresenter = presenter;
    }

    public List<AnswerModel.PageDatasBean> getData() {
        return mAnswerModelList;
    }

    public long getLastTime() {
        return mAnswerModelList.get(mAnswerModelList.size() - 1).getEditTime();
    }

    public long getLastAgreeCount() {
        return mAnswerModelList.get(mAnswerModelList.size() - 1).getAgreeCount();
    }

    public void setData(List<AnswerModel.PageDatasBean> answerModelList) {
        mAnswerModelList = answerModelList;
    }

    public void addData(List<AnswerModel.PageDatasBean> answerModelList) {
        mAnswerModelList.addAll(answerModelList);
    }

    public void removeAllData() {
        mAnswerModelList.clear();
    }

    @NonNull
    @Override
    public AnswerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.module_home_item_answer, group, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        AnswerModel.PageDatasBean bean = mAnswerModelList.get(i);
        if (bean.getUserInfo().getUid() == SPUtils.getInstance().getCurrentUid()) {
            holder.mTvAttentionAnswerer.setVisibility(View.GONE);
        } else {
            holder.mTvAttentionAnswerer.setVisibility(View.VISIBLE);
        }
        holder.mIvShare.setOnClickListener(shareListener);
        holder.mTvAttentionAnswerer.setOnClickListener(attentionListener);
        //昵称
        holder.mTvAnswerName.setText(bean.getUserInfo().getNickName());
        //是否已关注答主
        setAttentionText(holder.mTvAttentionAnswerer, bean.getFollow());
        //头像
        ImgTools.showImageView(mContext, bean.getUserInfo().getUserHeadImg_48(), holder.mIvAvator);
        //最后编辑回答时间
        holder.mTvAnswerTime.setText(String.format(mContext.getResources().getString(R.string.module_home_text_edit_time), TimeFormatUtils.toHuihuTime(bean.getEditTime())));
        //赞同数
        holder.mTvCountApproval.setText(String.format(mContext.getResources().getString(R.string.module_home_text_withparam_approval), CountUtil.toHuihuCount(bean.getAgreeCount())));
        //评论数
        holder.mTvCountApproval.setText(String.format(mContext.getResources().getString(R.string.module_home_text_withparam_comment), CountUtil.toHuihuCount(bean.getCommentCount())));
        //根据数据接口确定展示的item样式
        setContent(holder, bean);
        holder.mTvAttentionAnswerer.setTag(i);
        holder.mLlItemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(i);
                }
            }
        });
    }

    private void setAttentionText(TextView answerer, int follow) {
        switch (follow) {
            case UNFOLLOW:
                answerer.setText(R.string.module_home_text_attention_person);
                answerer.setBackground(mContext.getResources().getDrawable(R.drawable.module_home_selector_bg_style_two));
                answerer.setTextColor(mContext.getResources().getColor(R.color.module_home_selector_text_style_two));
                break;
            case FOLLOWED:
                answerer.setText(R.string.module_home_text_attentioned);
                answerer.setBackground(mContext.getResources().getDrawable(R.drawable.module_home_shape_bg_unable));
                answerer.setTextColor(mContext.getResources().getColor(R.color.module_home_text_unable));
                break;
        }
    }

    private void setContent(ViewHolder holder, AnswerModel.PageDatasBean bean) {
        if (bean.getImages().isEmpty() || bean.getImages().size() == 0) {
            //纯文本显示
            holder.mLlImageContainer.setVisibility(View.GONE);
            holder.mTvContent.setVisibility(View.VISIBLE);
            holder.mTvContent.setText(bean.getBriefContent());
        } else {
            if (TextUtils.isEmpty(bean.getBriefContent())) {
                //纯图片
                holder.mTvContent.setVisibility(View.GONE);
            } else {
                //图文都有
                holder.mTvContent.setVisibility(View.VISIBLE);
                holder.mTvContent.setText(bean.getBriefContent());
            }
            holder.mLlImageContainer.setVisibility(View.VISIBLE);
            setImages(holder, bean.getImages());
        }
    }

    private void setImages(ViewHolder holder, List<AnswerModel.PageDatasBean.ImagesBean> images) {
        holder.mLlImageContainer.removeAllViews();
        for (int i = 0; i < images.size(); i++) {
            if (i > 2) {
                break;//超出三张就不用创建了
            }
            ImageView imageView = new ImageView(mContext);
            //缩放形式
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //每张图高度计算
            int height = 0;
            switch (images.size()) {
                case COUNT_ONE:
                    height = DensityUtil.dip2px(mContext, 188);
                    break;
                case COUNT_TWO:
                    height = DensityUtil.dip2px(mContext, 124);
                    break;
                case COUNT_THREE:
                default:
                    height = DensityUtil.dip2px(mContext, 82);
                    break;
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(getImageWidth(images.size()), height);
            if (i != 0) {
                //图片不止一张时，设置图片间间距
                layoutParams.setMarginStart(DensityUtil.dip2px(mContext, 4));
            }
            imageView.setLayoutParams(layoutParams);
            ImgTools.showImageViewWithConner(mContext, images.get(i).getImageUrl(), imageView, RADIUS_IMAGE);
            holder.mLlImageContainer.addView(imageView);
        }
    }

    private int getImageWidth(int imageCount) {
        int realCount = imageCount > 3 ? 3 : imageCount;
        //除去两边间距
        int totalWidth = mScreenWidth - DensityUtil.dip2px(mContext, 20) * 2;
        //图片之间间距
        int imageMargin = (realCount - 1) * DensityUtil.dip2px(mContext, 4);
        return (totalWidth - imageMargin) / realCount;
    }

    @Override
    public int getItemCount() {
        return mAnswerModelList.size();
    }

    public void setScreenWidth(int width) {
        mScreenWidth = width;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.iv_avator)
        RoundImageView mIvAvator;
        @BindView(R2.id.iv_share)
        ImageView mIvShare;
        @BindView(R2.id.tv_answer_name)
        TextView mTvAnswerName;
        @BindView(R2.id.tv_answer_time)
        TextView mTvAnswerTime;
        @BindView(R2.id.tv_count_approval)
        TextView mTvCountApproval;
        @BindView(R2.id.tv_count_comment)
        TextView mTvCountComment;
        @BindView(R2.id.tv_content)
        TextView mTvContent;
        @BindView(R2.id.ll_item_root)
        LinearLayout mLlItemRoot;
        @BindView(R2.id.ll_image_container)
        LinearLayout mLlImageContainer;
        @BindView(R2.id.tv_attention_answerer)
        TextView mTvAttentionAnswerer;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnMyItemClickListener {
        void onItemClick(int postion);
    }
}
