package com.huihu.uilib.util.imageupload;


/**
 * create by wangjing on 2019/3/27 0027
 * description:
 */
public interface ImageUpLoadCallback {
    void onSuccess();
    void onFail(String errorMsg);
}
