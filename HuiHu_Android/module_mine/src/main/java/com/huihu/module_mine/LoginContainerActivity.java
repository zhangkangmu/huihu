package com.huihu.module_mine;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.huihu.commonlib.simpleRouter.SimpleRouterClassRegister;
import com.huihu.commonlib.simpleRouter.SimpleRouterObj;
import com.huihu.module_mine.loginbyphone.view.LoginByPhoneFragment;
import com.huihu.uilib.RouterReDefine;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * create by ouyangjianfeng on 2019/3/18
 * description:登陆模块所有fragment依赖的activity
 */


@SimpleRouterClassRegister(key = RouterReDefine.LOGIN_ACTIVITY, type = SimpleRouterObj.ACTIVITY)
public class LoginContainerActivity extends SupportActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar();
        setContentView(R.layout.module_uilib_fragment_root_container);
        if (findFragment(LoginByPhoneFragment.class) == null) {
            loadRootFragment(R.id.fl_container, LoginByPhoneFragment.newInstance());
        }
    }

    /**
     * 初始化状态栏
     */
    public void initStatusBar() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
    }
}
