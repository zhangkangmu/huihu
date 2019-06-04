package com.huihu.module_notification.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.uilib.RouterReDefine;

import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * create by wangjing on 2019/4/15 0015
 * description:
 */
public class RouterUtil {

    public static void startOtherPeople(SupportFragment fragment, long uid){
        Bundle bundle = new Bundle();
        bundle.putLong("uid", uid);
        Fragment targetFragment = SimpleRouter.getInstance().getFragment(RouterReDefine.OTHERS_FRAGMENT, bundle);
        if (targetFragment == null) return;
        fragment.start((ISupportFragment) targetFragment);
    }

    public static void startAnswerDetail(SupportFragment fragment, long answerId){
        Bundle bundle = new Bundle();
        bundle.putLong("ideaId", answerId);
        Fragment targetFragment = SimpleRouter.getInstance().getFragment(RouterReDefine.ANSWERDETAIL_FRAGMENT, bundle);
        if (targetFragment == null) return;
        fragment.start((ISupportFragment) targetFragment);
    }

    public static void startQuestion(SupportFragment fragment, long questionId){
        Bundle bundle = new Bundle();
        bundle.putLong("ideaId", questionId);
        Fragment targetFragment = SimpleRouter.getInstance().getFragment(RouterReDefine.QUESTIONDETAIL_FRAGMENT, bundle);
        if (targetFragment == null) return;
        fragment.start((ISupportFragment) targetFragment);
    }
}
