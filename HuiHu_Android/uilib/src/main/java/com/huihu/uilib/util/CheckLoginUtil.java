package com.huihu.uilib.util;

import com.huihu.commonlib.utils.Constant;
import com.huihu.commonlib.utils.SPUtils;

/**
 * create by wangjing on 2019/3/19 0019
 * description:
 */
public class CheckLoginUtil {

    public static boolean checkLogin() {
        return SPUtils.getInstance().getCurrentUid() != Constant.DEFAULT_UID;
    }
}
