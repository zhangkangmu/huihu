package com.bailun.localeslibrary.config_support.api_scenes.zh_tw;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseHostApiUtil;
import com.bailun.localeslibrary.config_support.api_scenes.default_set.EnvHostBase;


/**
 * Created by kingpang on 2018/9/20.
 */

public class HostApiUtil_zh_tw extends BaseHostApiUtil {

    @Override
    public EnvHostBase getDebugHost() {
        return new EnvHostType_zh_tw.Debug();
    }

    @Override
    public EnvHostBase getTestHost() {
        return new EnvHostType_zh_tw.Test();
    }

    @Override
    public EnvHostBase getPreReleaseHost() {
        return new EnvHostType_zh_tw.PreRelease();
    }

    @Override
    public EnvHostBase getReleaseHost() {
        return new EnvHostType_zh_tw.Release();
    }

// kingpang.test:当台湾版，除了host域名外，API的值也不同，才需要启动下面的代码
//    @Override
//    public void createAPIs(EnvHostBase host) {
//        super.createAPIs(host);
//    }
}
