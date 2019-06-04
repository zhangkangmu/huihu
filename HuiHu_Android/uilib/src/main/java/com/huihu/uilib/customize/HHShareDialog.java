package com.huihu.uilib.customize;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.uilib.R;
import com.huihu.uilib.util.DensityUtil;

/**
 * create by ouyangjianfeng on 2019/4/8
 * description:
 */
public class HHShareDialog {

    private BottomSheetDialog mBottomSheetDialog;
    private Context mContext;
    private LinearLayout mContainer;

    public HHShareDialog(@NonNull Context context) {
        this.mContext = context;
        init();
    }

    private void init() {
        mBottomSheetDialog = new BottomSheetDialog(mContext);
        mBottomSheetDialog.setContentView(R.layout.uilib_share_dialog);

        mContainer = mBottomSheetDialog.findViewById(R.id.ll_container);

        TextView tv_title = mBottomSheetDialog.findViewById(R.id.tv_title);
        TextViewUtils.setTextFakeBold(tv_title);

        LinearLayout llShareToMoment = mBottomSheetDialog.findViewById(R.id.ll_share_to_moment);
        llShareToMoment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("分享到朋友圈");
                mBottomSheetDialog.dismiss();
            }
        });

        LinearLayout llShareToWechat = mBottomSheetDialog.findViewById(R.id.ll_share_to_wechat);
        llShareToWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("分享到微信");
                mBottomSheetDialog.dismiss();
            }
        });

        LinearLayout llShareToWeibo = mBottomSheetDialog.findViewById(R.id.ll_share_to_weibo);
        llShareToWeibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("分享到微博");
                mBottomSheetDialog.dismiss();
            }
        });

        LinearLayout llShareToQq = mBottomSheetDialog.findViewById(R.id.ll_share_to_qq);
        llShareToQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("分享到QQ");
                mBottomSheetDialog.dismiss();
            }
        });

        TextView tvCancel = mBottomSheetDialog.findViewById(R.id.tv_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });
    }

    /**
     * 收藏
     */
    public void setCollectView(View.OnClickListener listener) {
        View view = View.inflate(mContext, R.layout.uilib_item_share, null);
        int width = DensityUtil.getScreenWidth(mContext) / 4;
        int height = DensityUtil.dip2px(mContext, 82);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        view.setLayoutParams(params);

        TextView textView = view.findViewById(R.id.tv_name);
        ImageView imageView = view.findViewById(R.id.iv_icon);
        textView.setText(R.string.uilib_share_to_collect);
        imageView.setImageResource(R.drawable.uilib_icon_collect_72_def);

        view.setOnClickListener(listener);

        mContainer.addView(view);
    }

    /**
     * 举报
     */
    public void setReportView(View.OnClickListener listener) {
        View view = View.inflate(mContext, R.layout.uilib_item_share, null);
        int width = DensityUtil.getScreenWidth(mContext) / 4;
        int height = DensityUtil.dip2px(mContext, 82);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        view.setLayoutParams(params);

        TextView textView = view.findViewById(R.id.tv_name);
        ImageView imageView = view.findViewById(R.id.iv_icon);
        textView.setText(R.string.uilib_share_to_report);
        imageView.setImageResource(R.drawable.uilib_icon_report_72_def);

        view.setOnClickListener(listener);

        mContainer.addView(view);
    }

    /**
     * 复制链接
     */
    public void setCopyLinkView(View.OnClickListener listener) {
        View view = View.inflate(mContext, R.layout.uilib_item_share, null);
        int width = DensityUtil.getScreenWidth(mContext) / 4;
        int height = DensityUtil.dip2px(mContext, 82);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        view.setLayoutParams(params);

        TextView textView = view.findViewById(R.id.tv_name);
        ImageView imageView = view.findViewById(R.id.iv_icon);
        textView.setText(R.string.uilib_share_to_copy_link);
        imageView.setImageResource(R.drawable.uilib_icon_binding_72_def);

        view.setOnClickListener(listener);

        mContainer.addView(view);
    }

    /**
     * 删除提问
     */
    public void setDeleteQuestionView(View.OnClickListener listener) {
        View view = View.inflate(mContext, R.layout.uilib_item_share, null);
        int width = DensityUtil.getScreenWidth(mContext) / 4;
        int height = DensityUtil.dip2px(mContext, 82);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        view.setLayoutParams(params);

        TextView textView = view.findViewById(R.id.tv_name);
        ImageView imageView = view.findViewById(R.id.iv_icon);
        textView.setText(R.string.uilib_share_to_delete_question);
        imageView.setImageResource(R.drawable.uilib_icon_delete_72_def);

        view.setOnClickListener(listener);

        mContainer.addView(view);
    }

    /**
     * 删除回答
     */
    public void setDeleteAnswerView(View.OnClickListener listener) {
        View view = View.inflate(mContext, R.layout.uilib_item_share, null);
        int width = DensityUtil.getScreenWidth(mContext) / 4;
        int height = DensityUtil.dip2px(mContext, 82);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        view.setLayoutParams(params);

        TextView textView = view.findViewById(R.id.tv_name);
        ImageView imageView = view.findViewById(R.id.iv_icon);
        textView.setText(R.string.uilib_share_to_delete_answer);
        imageView.setImageResource(R.drawable.uilib_icon_delete_72_def);

        view.setOnClickListener(listener);

        mContainer.addView(view);
    }

    /**
     * 删除讨论
     */
    public void setDeleteDiscussView(View.OnClickListener listener) {
        View view = View.inflate(mContext, R.layout.uilib_item_share, null);
        int width = DensityUtil.getScreenWidth(mContext) / 4;
        int height = DensityUtil.dip2px(mContext, 82);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        view.setLayoutParams(params);

        TextView textView = view.findViewById(R.id.tv_name);
        ImageView imageView = view.findViewById(R.id.iv_icon);
        textView.setText(R.string.uilib_share_to_delete_discuss);
        imageView.setImageResource(R.drawable.uilib_icon_delete_72_def);

        view.setOnClickListener(listener);

        mContainer.addView(view);
    }

    /**
     * 申诉内容
     */
    public void setAppealContentView(View.OnClickListener listener) {
        View view = View.inflate(mContext, R.layout.uilib_item_share, null);
        int width = DensityUtil.getScreenWidth(mContext) / 4;
        int height = DensityUtil.dip2px(mContext, 82);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        view.setLayoutParams(params);

        TextView textView = view.findViewById(R.id.tv_name);
        ImageView imageView = view.findViewById(R.id.iv_icon);
        textView.setText(R.string.uilib_share_to_appeal_content);
        imageView.setImageResource(R.drawable.uilib_icon_appeal_72_def);

        view.setOnClickListener(listener);

        mContainer.addView(view);
    }

    /**
     * 下架讨论
     */
    public void setObtainedView(View.OnClickListener listener) {
        View view = View.inflate(mContext, R.layout.uilib_item_share, null);
        int width = DensityUtil.getScreenWidth(mContext) / 4;
        int height = DensityUtil.dip2px(mContext, 82);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        view.setLayoutParams(params);

        TextView textView = view.findViewById(R.id.tv_name);
        ImageView imageView = view.findViewById(R.id.iv_icon);
        textView.setText(R.string.uilib_share_to_obtained);
        imageView.setImageResource(R.drawable.uilib_icon_violation_72_def);

        view.setOnClickListener(listener);

        mContainer.addView(view);
    }

    /**
     * 禁言
     */
    public void setBannerView(View.OnClickListener listener) {
        View view = View.inflate(mContext, R.layout.uilib_item_share, null);
        int width = DensityUtil.getScreenWidth(mContext) / 4;
        int height = DensityUtil.dip2px(mContext, 82);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        view.setLayoutParams(params);

        TextView textView = view.findViewById(R.id.tv_name);
        ImageView imageView = view.findViewById(R.id.iv_icon);
        textView.setText(R.string.uilib_share_to_banned);
        imageView.setImageResource(R.drawable.uilib_icon_prohibited_72_def);

        view.setOnClickListener(listener);

        mContainer.addView(view);
    }

    public void showDialog() {
        mBottomSheetDialog.create();
        mBottomSheetDialog.show();
    }
}
