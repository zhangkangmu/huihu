package com.huihu.module_notification.like.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseApplication;
import com.huihu.uilib.util.ImgTools;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.entity.NetImageBean;
import com.huihu.module_notification.entity.UserInfo;
import com.huihu.module_notification.like.LikeMeConstant;
import com.huihu.module_notification.like.entity.LikeMePageBean;
import com.huihu.module_notification.like.likeinterface.ILikePresenter;
import com.huihu.uilib.util.MarkUtil;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.customize.source.MorePictureAdapter;
import com.huihu.uilib.customize.source.SourceView;
import com.huihu.uilib.customize.source.SourceViewMode;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by wangjing on 2019/3/10 0010
 * description:
 */
public class LikeMeAdapter extends RecyclerView.Adapter<LikeMeAdapter.LikeMeViewHolder> {

    private static final String SUFFIX = BaseApplication.getApplication().getString(R.string.module_notification_like_you);

    private final ILikePresenter presenter;
    private Context mContext;
    private List<LikeMePageBean.PageDatasBean> dataList;
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (v.getTag() instanceof LikeMePageBean.PageDatasBean){
                LikeMePageBean.PageDatasBean bean = (LikeMePageBean.PageDatasBean) v.getTag();
                if (id == R.id.cl_head){
                    presenter.v2pLookOtherPeople(bean);
                }
            }
        }
    };

    public LikeMeAdapter(ILikePresenter presenter, Context mContext) {
        this.presenter = presenter;
        this.mContext = mContext;
    }

    public List<LikeMePageBean.PageDatasBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<LikeMePageBean.PageDatasBean> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public LikeMeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_notification_item_like, viewGroup, false);
        return new LikeMeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LikeMeViewHolder holder, int i) {
        LikeMePageBean.PageDatasBean bean = dataList.get(i);
        TextViewUtils.setTextFakeBold(holder.tvUserName);
        initLikeMeUser(holder, bean.getContentUserInfo(), bean.getCreateTime());
        initSourceView(holder.source, bean);
        holder.clHead.setTag(bean);
        holder.clHead.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    private void initLikeMeUser(LikeMeViewHolder holder, UserInfo userInfo, long time) {
        ImgTools.showImageView(mContext, userInfo.getUserHeadImg_120(), holder.ivUserHead);
        holder.tvUserName.setText(userInfo.getNickName());
        holder.tvTime.setText(TimeFormatUtils.toHuihuTime(time) + SUFFIX);
        MarkUtil.markPerson(mContext, userInfo.getIncMin(), holder.ivMark);
    }

    private void initSourceView(SourceView view, LikeMePageBean.PageDatasBean bean) {
        if (bean.getType() == LikeMeConstant.REPLY || bean.getType() == LikeMeConstant.DISCUSS) {
            view.setText(R.id.tv_source_article, bean.getContent());
            if (bean.getContentImages() == null || bean.getContentImages().size() == 0) {
                view.setMode(SourceViewMode.ARTICLE_TEXT);
            } else {
                view.setMode(SourceViewMode.ARTICLE_PICTURE_TEXT);
                view.setImageUrl(R.id.iv_source_article, bean.getContentImages().get(0).getImageUrl());
            }
        } else {
            view.setImageUrl(R.id.iv_source_user_head, bean.getContentUserInfo().getUserHeadImg_120());
            view.setText(R.id.tv_source_user_name, bean.getContentUserInfo().getNickName());
            view.setText(R.id.tv_source_comment_content, bean.getContent());
            view.setMode(SourceViewMode.USER_COMMENT_SINGLE_PICTURE);
            if (bean.getContentImages() != null && bean.getContentImages().size() > 0) {
                if (bean.getContentImages().size() == 1) {
                    view.setImageUrl(R.id.iv_source_single_picture, bean.getContentImages().get(0).getImageUrl());
                } else {
                    view.setMode(SourceViewMode.USER_COMMENT_MORE_PICTURE);
                    MorePictureAdapter adapter = view.getAdapter();
                    List<String> urls = adapter.getUrlList();
                    int oldCount = adapter.getItemCount();
                    for (NetImageBean imageBean : bean.getContentImages()) {
                        urls.add(imageBean.getImageUrl());
                    }
                    int newCount = adapter.getItemCount();
                    adapter.notifyItemRangeInserted(oldCount, newCount);
                }
            }
        }
    }

    static class LikeMeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.iv_user_head)
        RoundImageView ivUserHead;
        @BindView(R2.id.tv_user_name)
        TextView tvUserName;
        @BindView(R2.id.tv_time)
        TextView tvTime;
        @BindView(R2.id.iv_like_icon)
        ImageView ivLikeIcon;
        @BindView(R2.id.source)
        SourceView source;
        @BindView(R2.id.iv_mark)
        ImageView ivMark;
        @BindView(R2.id.cl_head)
        ConstraintLayout clHead;

        LikeMeViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

