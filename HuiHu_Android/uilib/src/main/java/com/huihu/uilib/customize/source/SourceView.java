package com.huihu.uilib.customize.source;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.uilib.R;
import com.huihu.uilib.R2;
import com.huihu.uilib.customize.RoundImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by wangjing on 2019/3/9 0009
 * description:自定义View用来显示点赞来源或者评论来源
 */

public class SourceView extends FrameLayout {

    @SourceViewMode
    private int mode;

    //    region控件定义
    @BindView(R2.id.iv_source_article)
    ImageView ivArticle;
    @BindView(R2.id.tv_source_article)
    TextView tvArticle;
    @BindView(R2.id.cl_article)
    ConstraintLayout clArticle;
    @BindView(R2.id.iv_source_user_head)
    RoundImageView ivUserHead;
    @BindView(R2.id.tv_source_user_name)
    TextView tvUserName;
    @BindView(R2.id.tv_source_comment_content)
    TextView tvCommentContent;
    @BindView(R2.id.iv_source_single_picture)
    ImageView ivSinglePicture;
    @BindView(R2.id.cl_user_comment)
    ConstraintLayout clUserComment;
    @BindView(R2.id.iv_source_limit)
    ImageView ivLimit;
    @BindView(R2.id.tv_source_limit)
    TextView tvLimit;
    @BindView(R2.id.cl_limit)
    ConstraintLayout clLimit;
    @BindView(R2.id.rv_source_more_picture)
    RecyclerView rvMorePicture;
//    endregion
    private MorePictureAdapter mAdapter;

    public SourceView(@NonNull Context context) {
        this(context, null);
    }

    public SourceView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.uilib_view_source, this, true);
        ButterKnife.bind(this);
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.SourceView);
        mode = array.getInt(R.styleable.SourceView_mode, SourceViewMode.ARTICLE_PICTURE_TEXT);
        array.recycle();
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        changeViewState(mode);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMorePicture.setLayoutManager(gridLayoutManager);
        mAdapter = new MorePictureAdapter();
        rvMorePicture.setAdapter(mAdapter);
        TextViewUtils.setTextFakeBold(tvUserName);
    }

    /**
     * 切换相应的显示模式
     *
     * @param mode 显示的模式
     */
    private void changeViewState(@SourceViewMode int mode) {
        switch (mode) {
            case SourceViewMode.ARTICLE_PICTURE_TEXT:
                clUserComment.setVisibility(GONE);
                clLimit.setVisibility(GONE);
                clArticle.setVisibility(VISIBLE);
                ivArticle.setVisibility(VISIBLE);
                break;
            case SourceViewMode.ARTICLE_TEXT:
                clUserComment.setVisibility(GONE);
                clLimit.setVisibility(GONE);
                clArticle.setVisibility(VISIBLE);
                ivArticle.setVisibility(GONE);
                break;
            case SourceViewMode.USER_COMMENT_SINGLE_PICTURE:
                clLimit.setVisibility(GONE);
                clArticle.setVisibility(GONE);
                clLimit.setVisibility(GONE);
                clUserComment.setVisibility(VISIBLE);
                rvMorePicture.setVisibility(GONE);
                ivSinglePicture.setVisibility(VISIBLE);
                break;
            case SourceViewMode.USER_COMMENT_MORE_PICTURE:
                clArticle.setVisibility(GONE);
                clUserComment.setVisibility(VISIBLE);
                rvMorePicture.setVisibility(VISIBLE);
                ivSinglePicture.setVisibility(GONE);
                break;
            case SourceViewMode.USER_COMMENT_DELETE:
                clArticle.setVisibility(GONE);
                clUserComment.setVisibility(GONE);
                clLimit.setVisibility(VISIBLE);
                ivLimit.setImageResource(R.drawable.uilib_icon_deleted_gray);
                tvLimit.setText(R.string.uilib_link_delete);
                break;
            case SourceViewMode.USER_COMMENT_DISCONTINUED:
                clArticle.setVisibility(GONE);
                clUserComment.setVisibility(GONE);
                clLimit.setVisibility(VISIBLE);
                ivLimit.setImageResource(R.drawable.uilib_icon_violationed);
                tvLimit.setText(R.string.uilib_link_violationed);
                break;
            default:
                break;
        }
    }

//    region  外部使用的API

    /**
     * 设置显示模式
     *
     * @param mode 固定的几个取值
     */
    public void setMode(@SourceViewMode int mode) {
        this.mode = mode;
        changeViewState(mode);
    }


    /**
     * 给view内部的ImageView设置图像
     *
     * @param id     内部的ImageView的id
     * @param bitmap 图像
     */
    public void setImageBitmap(@IdRes int id, Bitmap bitmap) {
        if (id == R.id.iv_source_article) {
            ivArticle.setImageBitmap(bitmap);
        } else if (id == R.id.iv_source_user_head) {
            ivUserHead.setImageBitmap(bitmap);
        } else if (id == R.id.iv_source_single_picture) {
            ivSinglePicture.setImageBitmap(bitmap);
        }
    }

    /**
     * 给view内部的ImageView设置图像
     *
     * @param id       内部的ImageView的id
     * @param drawable 图像
     */
    public void setImageDrawable(@IdRes int id, Drawable drawable) {
        if (id == R.id.iv_source_article) {
            ivArticle.setImageDrawable(drawable);
        } else if (id == R.id.iv_source_user_head) {
            ivUserHead.setImageDrawable(drawable);
        } else if (id == R.id.iv_source_single_picture) {
            ivSinglePicture.setImageDrawable(drawable);
        }
    }

    /**
     * 给view内部的ImageView设置图像
     *
     * @param id  内部的ImageView的id
     * @param url 图像的url
     */
    public void setImageUrl(@IdRes int id, String url) {
        ImageView iv = null;
        if (id == R.id.iv_source_article) {
            iv = ivArticle;
        } else if (id == R.id.iv_source_user_head) {
            iv = ivUserHead;
        } else if (id == R.id.iv_source_single_picture) {
            iv = ivSinglePicture;
        }
        if (iv != null) Glide.with(getContext()).load(url).into(iv);
    }

    /**
     * 给view内部的TextView设置文字
     *
     * @param id   内部的TextView的id
     * @param text 文字
     */
    public void setText(@IdRes int id, String text) {
        if (id == R.id.tv_source_article) {
            tvArticle.setText(text);
        } else if (id == R.id.tv_source_comment_content) {
            tvCommentContent.setText(text);
        } else if (id == R.id.tv_source_user_name) {
            tvUserName.setText(text);
        }
    }

    /**
     * @return 获取内部多图显示的Adapter
     */
    public MorePictureAdapter getAdapter(){
        return mAdapter;
    }

//    endregion
}
