package com.huihu.uilib.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.huihu.uilib.HuihuScaleTitleView;
import com.huihu.uilib.model.IndicatorParamModel;

import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;

/**
 * @author ouyangjianfeng
 * @time 2019/3/7  21:05
 * @desc 汇乎统一头部导航adapter
 */
public class HuihuNavigationAdapter extends CommonNavigatorAdapter {

    private IndicatorParamModel mIndicatorParamModel;
    private ViewPager mViewPager;

    public HuihuNavigationAdapter(ViewPager viewPager, IndicatorParamModel paramModel) {
        this.mIndicatorParamModel = paramModel;
        this.mViewPager = viewPager;
    }

    @Override
    public int getCount() {
        return mIndicatorParamModel.getTitleStrings().length;
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {
        String[] titleStrings = mIndicatorParamModel.getTitleStrings();
        HuihuScaleTitleView textView = new HuihuScaleTitleView(context);
        textView.setTextStyle(mIndicatorParamModel.getTextStyle());
        textView.setText(titleStrings[index]);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewPager.setCurrentItem(index);
            }
        });
        return textView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
        indicator.setLineHeight(UIUtil.dip2px(context, 4));
        indicator.setLineWidth(UIUtil.dip2px(context, 16));
        indicator.setRoundRadius(UIUtil.dip2px(context, 2));
        indicator.setColors(Color.parseColor("#333333"));
        indicator.setYOffset(UIUtil.dip2px(context, 4));
        return indicator;
    }
}
