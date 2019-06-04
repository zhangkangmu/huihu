package debug;

import com.huihu.commonlib.base.BaseApplication;
import com.huihu.uilib.util.TimeFormatUtils;

/**
 * @author Yaooi
 * @time 2018/12/27  15:33
 * @desc ${TODD}
 */
public class NotificationFragment extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        TimeFormatUtils.initTime();
    }
}
