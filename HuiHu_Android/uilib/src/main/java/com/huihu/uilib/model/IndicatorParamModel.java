package com.huihu.uilib.model;

/**
 * @author ouyangjianfeng
 * @time 2019/3/7  21:16
 * @desc 汇乎通用indicator配参模型
 */
public class IndicatorParamModel {

    private String[] titleStrings;
    private @TextStyle int style;//文字大小

    public String[] getTitleStrings() {
        return titleStrings;
    }

    public void setTitleStrings(String[] titleStrings) {
        this.titleStrings = titleStrings;
    }

    public int getTextStyle() {
        return style;
    }

    public void setTextStyle(@TextStyle int style) {
        this.style = style;
    }
}
