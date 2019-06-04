package com.huihu.uilib.statelayout;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.uilib.statelayout.StateLayoutType.Content;
import static com.huihu.uilib.statelayout.StateLayoutType.EmptyData;
import static com.huihu.uilib.statelayout.StateLayoutType.LoadingData;
import static com.huihu.uilib.statelayout.StateLayoutType.NetworkError;
import static com.huihu.uilib.statelayout.StateLayoutType.NoNetwork;


/**
 * create by wangjing on 2018/10/16 0016
 * description:
 */
@IntDef({Content, NetworkError, NoNetwork, EmptyData, LoadingData})
@Retention(RetentionPolicy.SOURCE)
public @interface StateLayoutType {
    /**
     * 正常状态
     */
    int Content = 1;
    /**
     * 网络异常
     */
    int NetworkError = 2;
    /**
     * 断网状态
     */
    int NoNetwork = 3;
    /**
     * 空数据状态
     */
    int EmptyData = 4;
    /**
     * 加载更多数据
     */
    int LoadingData = 5;
}
