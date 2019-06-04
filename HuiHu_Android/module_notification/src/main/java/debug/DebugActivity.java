package debug;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.StatusBarUtil;
import com.huihu.module_notification.R;
import com.huihu.module_notification.notification.view.NotificationFragment;
import com.huihu.uilib.PointListener;
import com.huihu.uilib.PointUtil;

import me.yokeyword.fragmentation.SupportActivity;


/**
 * create by wangjing on 2019/3/7 0007
 * description: 消息模块
 */
public class DebugActivity extends SupportActivity implements PointUtil {

    private PointListener pointListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_notification_activity_debug);
        if (findFragment(NotificationFragment.class) == null) {
            loadRootFragment(R.id.fl_content, new DebugFragment());
        }
        StatusBarUtil.setStatusBarBgColorAndIcon(this, Color.WHITE, StatusBarUtil.STATUS_BAR_DART_ICON);
        SPUtils.getInstance().saveCurrentUid(284002);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (pointListener != null) pointListener.onTouch(ev.getRawX(), ev.getRawY());
        return super.dispatchTouchEvent(ev);
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
