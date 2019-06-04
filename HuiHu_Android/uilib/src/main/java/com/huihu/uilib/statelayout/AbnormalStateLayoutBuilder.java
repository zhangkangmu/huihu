package com.huihu.uilib.statelayout;

import android.support.annotation.DrawableRes;

/**
 * 类说明：异常状态布局构建对象
 * 1.空数据
 * 2.无网络
 * 3.请求异常
 *
 * @author yh
 *         on 2018/12/5 000510:01
 */

public class AbnormalStateLayoutBuilder {

    /**
     * 提示文字
     */
    private String tipsStr;
    /**
     * 提示图片资源
     */
    @DrawableRes
    private int tipsDrawableRes;
    /**
     * 提示文字和图片的间距-dps
     */
    private int imageAndTextSpace;
    /**
     * 布局背景颜色
     */
    private int backgroundColor;


    public String getTipsStr() {
        return tipsStr;
    }

    public int getTipsDrawableRes() {
        return tipsDrawableRes;
    }

    public int getImageAndTextSpace() {
        return imageAndTextSpace;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public static class Builder {

        private AbnormalStateLayoutBuilder abnormalStateLayoutBuilder;

        public Builder() {
            abnormalStateLayoutBuilder = new AbnormalStateLayoutBuilder();
        }

        public Builder setTipsStr(String tipsStr) {
            abnormalStateLayoutBuilder.tipsStr = tipsStr;
            return this;
        }

        public Builder setTipsDrawableRes(@DrawableRes int tipsDrawableRes) {
            abnormalStateLayoutBuilder.tipsDrawableRes = tipsDrawableRes;
            return this;
        }

        public Builder setImageAndTextSpace(int imageAndTextSpace) {
            abnormalStateLayoutBuilder.imageAndTextSpace = imageAndTextSpace;
            return this;
        }

        public Builder setBackgroundColor(int color){
            abnormalStateLayoutBuilder.backgroundColor = color;
            return this;
        }

        public AbnormalStateLayoutBuilder build() {
            return abnormalStateLayoutBuilder;
        }
    }

}
