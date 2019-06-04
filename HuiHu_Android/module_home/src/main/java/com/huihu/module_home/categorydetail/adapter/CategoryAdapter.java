package com.huihu.module_home.categorydetail.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.categorydetail.categorydetailinterface.ICategoryDetailPresenter;
import com.huihu.uilib.customize.CategoryPicturesView;
import com.huihu.module_home.categorydetail.entity.PageDatasBean;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.def.NetDataBoolean;
import com.huihu.uilib.util.CountUtil;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by wangjing on 2019/4/1 0001
 * description:
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<PageDatasBean> datas;
    private final ICategoryDetailPresenter presenter;
    private View.OnClickListener followListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getTag() instanceof PageDatasBean){
                presenter.v2pPutGiveFollows((PageDatasBean) v.getTag());
            }
        }
    };

    public CategoryAdapter(ICategoryDetailPresenter presenter) {
        this.presenter = presenter;
    }

    public List<PageDatasBean> getDatas() {
        return datas;
    }

    public void setDatas(List<PageDatasBean> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.module_home_item_category_answer, viewGroup, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int i) {
        PageDatasBean bean = datas.get(i);
        Context context = holder.itemView.getContext();
        Glide.with(context).load(bean.getUserInfo().getUserHeadImg_120()).into(holder.ivUserHead);
        holder.viewPictures.setTag(bean);
        holder.viewPictures.setUrls(bean.getUrls());
        bindTextView(context, holder, bean);
        bindAttention(context, holder, bean);
    }

    private void bindTextView(Context context, CategoryViewHolder holder, PageDatasBean bean){
        holder.tvQuestionContent.setText(bean.getTitle());
        TextViewUtils.setTextFakeBold(holder.tvQuestionContent);
        holder.tvTime.setText(TimeFormatUtils.toHuihuTime(bean.getEditTime()) + context.getString(R.string.module_home_text_answer));
        if (TextUtils.isEmpty(bean.getBriefContent())){
            holder.tvAnswerContent.setVisibility(View.GONE);
        } else {
            holder.tvAnswerContent.setVisibility(View.VISIBLE);
            holder.tvAnswerContent.setText(bean.getBriefContent());
        }
        holder.tvUserName.setText(bean.getUserInfo().getNickName());
        holder.tvEndorsedNumber.setText(CountUtil.toHuihuCount(bean.getAgreeCount()) + context.getString(R.string.module_home_text_approval));
        holder.tvCommentNumber.setText(CountUtil.toHuihuCount(bean.getCommentCount()) + context.getString(R.string.module_home_text_comment));
    }

    private void bindAttention(Context context, CategoryViewHolder holder, PageDatasBean bean){
        holder.tvDo.setTag(bean);
        if (bean.getUserInfo().getFollow() == NetDataBoolean.NET_TRUE) {
            holder.tvDo.setText(R.string.module_home_text_attentioned);
            holder.tvDo.setBackground(ContextCompat.getDrawable(context, R.drawable.uilib_bg_invalid));
            holder.tvDo.setTextColor(ContextCompat.getColor(context, R.color.uilib_light_gray));
        } else {
            holder.tvDo.setText(R.string.module_home_text_attention_person);
            holder.tvDo.setBackground(ContextCompat.getDrawable(context, R.drawable.module_home_follow_it_bg_light_blue));
            holder.tvDo.setTextColor(ContextCompat.getColor(context, R.color.global_blue));
        }
        holder.tvDo.setOnClickListener(followListener);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv_question_content)
        TextView tvQuestionContent;
        @BindView(R2.id.iv_user_head)
        RoundImageView ivUserHead;
        @BindView(R2.id.iv_identity)
        ImageView ivIdentity;
        @BindView(R2.id.tv_user_name)
        TextView tvUserName;
        @BindView(R2.id.tv_time)
        TextView tvTime;
        @BindView(R2.id.tv_do)
        TextView tvDo;
        @BindView(R2.id.tv_answer_content)
        TextView tvAnswerContent;
        @BindView(R2.id.view_pictures)
        CategoryPicturesView viewPictures;
        @BindView(R2.id.tv_endorsed_number)
        TextView tvEndorsedNumber;
        @BindView(R2.id.tv_comment_number)
        TextView tvCommentNumber;
        @BindView(R2.id.iv_share_answer)
        ImageView ivShareAnswer;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
