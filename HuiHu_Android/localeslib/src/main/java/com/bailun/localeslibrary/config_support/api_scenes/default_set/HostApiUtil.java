package com.bailun.localeslibrary.config_support.api_scenes.default_set;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseHostApiUtil;

/**
 * Created by kingpang on 2018/8/21.
 */

public class HostApiUtil extends BaseHostApiUtil {
    @Override
    public EnvHostBase getDebugHost() {
        return new EnvHostType.Debug();
    }

    @Override
    public EnvHostBase getTestHost() {
        return new EnvHostType.Test();
    }

    @Override
    public EnvHostBase getPreReleaseHost() {
        return new EnvHostType.PreRelease();
    }

    @Override
    public EnvHostBase getReleaseHost() {
        return new EnvHostType.Release();
    }
}
