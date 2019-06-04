package com.huihu.module_home.questiondraft.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.questiondraft.model.GetDraftModel;
import com.huihu.uilib.customize.CategoryPicturesView;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by ouyangjianfeng on 2019/4/17
 * description:
 */
public class QuestionDraftAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<GetDraftModel.PageDatasBean> mDatasBeans;
    private boolean mNoMore;

    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_BOTTOM = 1;

    public QuestionDraftAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<GetDraftModel.PageDatasBean> data) {
        mDatasBeans = data;
    }

    public List<GetDraftModel.PageDatasBean> getData() {
        return mDatasBeans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int i) {
        if (i == TYPE_NORMAL) {
            View view = LayoutInflater.from(group.getContext()).inflate(R.layout.module_home_item_draft_question, group, false);
            return new DraftViewHolder(view);
        } else {
            View view = LayoutInflater.from(group.getContext()).inflate(R.layout.uilib_footer_nomore, group, false);
            return new NoMoreViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (holder instanceof DraftViewHolder) {
            DraftViewHolder draftViewHolder = (DraftViewHolder) holder;
            GetDraftModel.PageDatasBean bean = mDatasBeans.get(i);
            //标题
            if (TextUtils.isEmpty(bean.getTitle())) {
                draftViewHolder.mTvTitle.setVisibility(View.GONE);
            } else {
                draftViewHolder.mTvTitle.setVisibility(View.VISIBLE);
                draftViewHolder.mTvTitle.setText(bean.getTitle());
                TextViewUtils.setTextFakeBold(draftViewHolder.mTvTitle);
            }
            //内容区域
            if (TextUtils.isEmpty(bean.getContent())) {
                draftViewHolder.mTvContent.setVisibility(View.GONE);
            } else {
                draftViewHolder.mTvContent.setVisibility(View.VISIBLE);
                draftViewHolder.mTvContent.setText(bean.getContent());
            }
            //图片
            if (bean.getImages() == null || bean.getImages().isEmpty()) {
                draftViewHolder.mIvPic.setVisibility(View.GONE);
            } else {
                draftViewHolder.mIvPic.setVisibility(View.VISIBLE);
                draftViewHolder.mIvPic.setUrls(getUrls(bean.getImages()));
            }
            //最后编辑时间
            draftViewHolder.mTvDraftTime.setText(TimeFormatUtils.long2Time(bean.getEditTime(), TimeFormatUtils.df1));
        }
    }

    private List<String> getUrls(List<GetDraftModel.PageDatasBean.ImagesBean> images) {
        List<String> urls = new ArrayList<>();
        for (GetDraftModel.PageDatasBean.ImagesBean bean : images) {
            urls.add(bean.getImageUrl());
        }
        return urls;
    }

    @Override
    public int getItemCount() {
        int count = mDatasBeans == null ? 0 : mDatasBeans.size();
        if (mNoMore) {
            count++;
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (mNoMore && position == getItemCount() - 1) {
            return TYPE_BOTTOM;
        } else {
            return TYPE_NORMAL;
        }
    }

    public void setNoMoreDataItem(boolean noMore) {
        mNoMore = noMore;
    }

    public void addData(List<GetDraftModel.PageDatasBean> datas) {
        mDatasBeans.addAll(datas);
    }

    class DraftViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv_title)
        TextView mTvTitle;
        @BindView(R2.id.tv_content)
        TextView mTvContent;
        @BindView(R2.id.iv_pic)
        CategoryPicturesView mIvPic;
        @BindView(R2.id.tv_draft_time)
        TextView mTvDraftTime;
        @BindView(R2.id.iv_delete)
        ImageView mIvDelete;

        public DraftViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class NoMoreViewHolder extends RecyclerView.ViewHolder {

        public NoMoreViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
