package com.huihu.module_maincontainer;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;

import com.huihu.commonlib.utils.StatusBarUtil;
import com.huihu.uilib.PointListener;
import com.huihu.uilib.PointUtil;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;


public class MainActivity extends SupportActivity implements PointUtil {

    private PointListener pointListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_uilib_fragment_root_contmodule_uilib_fragment_root_containerainer);
        StatusBarUtil.setStatusBarBgColorAndIcon(this, Color.WHITE, StatusBarUtil.STATUS_BAR_DART_ICON);
        if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.fl_container, MainFragment.newInstance());
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN && pointListener!= null) pointListener.onTouch(ev.getRawX(), ev.getRawY());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultNoAnimator();
    }

    @Override
    public void registerPointListener(PointListener listener) {
        pointListener = listener;
    }

    @Override
    public void unRegisterPointListener() {
        pointListener = null;
    }
}
