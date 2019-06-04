package com.huihu.commonlib.base;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.bailun.localeslibrary.CurLocales;
import com.bailun.localeslibrary.type.EnvironmentEnum;
import com.huihu.commonlib.MaterialFooter;
import com.huihu.commonlib.R;
import com.huihu.commonlib.shareData.ShareDataUtils;
import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshInitializer;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import org.xutils.x;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import me.yokeyword.fragmentation.Fragmentation;

/**
 * @author Yaooi
 * @time 2018/12/26  19:30
 * @desc ${TODD}
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initXUTILS(this); //init xUtils
        ShareDataUtils.getInstance().init(); //init share data
        initAPI();
        SimpleRouter.getInstance().init(this);//init SimpleRouter
        initFragmentation();//init Fragmentation
        initRefresh();//刷新初始化设置
    }

    private void initRefresh() {
        //设置全局默认配置（优先级最低，会被其他设置覆盖）
        SmartRefreshLayout.setDefaultRefreshInitializer(new DefaultRefreshInitializer() {
            @Override
            public void initialize(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setEnableRefresh(true);
                layout.setEnableLoadMore(true);
                //是否滚到底部自动加载
                layout.setEnableAutoLoadMore(true);
                //显示拖动高度/真实拖动高度
                layout.setDragRate(1);
                //refresh最大拖动距离，和footer等距，不可越界拖动
                layout.setFooterMaxDragRate(1);
            }
        });

        //全局设置默认的 Header
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                return new MaterialHeader(context).setColorSchemeResources(R.color.common_blue);
            }
        });
        //全局设置默认的 Footer
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @NonNull
            @Override
            public RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout) {
                return new MaterialFooter(context);
            }
        });
    }

    private void initAPI() {
        CurLocales.init(this);
        CurLocales.instance().Set(EnvironmentEnum.DEBUG);
    }

    private void initFragmentation() {
        Fragmentation.builder()
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(true)
                .install();
    }

    private void initXUTILS(Application app) {
        x.Ext.init(app);
        //x.Ext.setDebug(BuildConfig.DEBUG); // 开启debug会影响性能

        // 全局默认信任所有https域名 或 仅添加信任的https域名
        // 使用RequestParams#setHostnameVerifier(...)方法可设置单次请求的域名校验
        x.Ext.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
    }

    public static Context getApplication() {
        return x.app().getApplicationContext();
    }
}
