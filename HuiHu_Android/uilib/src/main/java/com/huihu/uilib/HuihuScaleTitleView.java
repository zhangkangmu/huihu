package com.huihu.uilib;

import android.content.Context;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.TextView;

import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.uilib.model.TextStyle;
import com.huihu.uilib.util.DensityUtil;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IMeasurablePagerTitleView;

/**
 * @author ouyangjianfeng
 * @time 2019/2/27  13:56d
 * @desc
 */
public class HuihuScaleTitleView extends TextView implements IMeasurablePagerTitleView {

    private Context mContext;

    //默认字体大小
    private int TEXT_SIZE_NORMAL;
    //选中字体大小
    private int TEXT_SIZE_SELECTED;

    public HuihuScaleTitleView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        setGravity(Gravity.BOTTOM);
        setTextColor(getResources().getColor(R.color.text_black));
        setSingleLine();
        int padding = UIUtil.dip2px(context, 10);
        setPadding(padding, 0, padding, UIUtil.dip2px(context, 13));
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        if (enterPercent > 0.5) {
            TextViewUtils.setTextFakeBold(this);
        }
        //放大
        setTextSize(TypedValue.COMPLEX_UNIT_PX, TEXT_SIZE_NORMAL + (TEXT_SIZE_SELECTED - TEXT_SIZE_NORMAL) * enterPercent);
    }

    @Override
    public void onSelected(int index, int totalCount) {

    }

    @Override
    public void onDeselected(int index, int totalCount) {

    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        if (leavePercent > 0.5) {
            TextViewUtils.cancelTextFakeBold(this);
        }
        //缩小
        setTextSize(TypedValue.COMPLEX_UNIT_PX, TEXT_SIZE_SELECTED - (TEXT_SIZE_SELECTED - TEXT_SIZE_NORMAL) * leavePercent);
    }

    @Override
    public int getContentLeft() {
        Rect bound = new Rect();
        this.getPaint().getTextBounds(this.getText().toString(), 0, this.getText().length(), bound);
        int contentWidth = bound.width();
        return this.getLeft() + this.getWidth() / 2 - contentWidth / 2;
    }

    @Override
    public int getContentTop() {
        FontMetrics metrics = this.getPaint().getFontMetrics();
        float contentHeight = metrics.bottom - metrics.top;
        return (int) ((float) (this.getHeight() / 2) - contentHeight / 2.0F);
    }

    @Override
    public int getContentRight() {
        Rect bound = new Rect();
        this.getPaint().getTextBounds(this.getText().toString(), 0, this.getText().length(), bound);
        int contentWidth = bound.width();
        return this.getLeft() + this.getWidth() / 2 + contentWidth / 2;
    }

    @Override
    public int getContentBottom() {
        FontMetrics metrics = this.getPaint().getFontMetrics();
        float contentHeight = metrics.bottom - metrics.top;
        return (int) ((float) (this.getHeight() / 2) + contentHeight / 2.0F);
    }

    public void setTextStyle(@TextStyle int style) {
        switch (style) {
            case TextStyle.level_first:
                TEXT_SIZE_NORMAL = DensityUtil.sp2px(mContext, 18);
                TEXT_SIZE_SELECTED = DensityUtil.sp2px(mContext, 32);
                break;
            case TextStyle.level_second:
                TEXT_SIZE_NORMAL = DensityUtil.sp2px(mContext, 14);
                TEXT_SIZE_SELECTED = DensityUtil.sp2px(mContext, 20);
                break;
        }
        setTextSize(TypedValue.COMPLEX_UNIT_PX, TEXT_SIZE_SELECTED);
    }
}
