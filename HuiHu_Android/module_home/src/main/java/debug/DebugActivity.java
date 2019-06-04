package debug;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.StatusBarUtil;
import com.huihu.module_home.R;


import me.yokeyword.fragmentation.SupportActivity;


/**
 * create by wangjing on 2019/3/7 0007
 * description: 消息模块
 */
public class DebugActivity extends SupportActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_notification_activity_debug);
        if (findFragment(DebugFragment.class) == null) {
            loadRootFragment(R.id.fl_content, new DebugFragment());
        }
        StatusBarUtil.setStatusBarBgColorAndIcon(this, Color.WHITE, StatusBarUtil.STATUS_BAR_DART_ICON);
        SPUtils.getInstance().saveCurrentUid(284002);
    }
}
