package com.bailun.localeslibrary.config_support.api_scenes.api_host_depend;

import com.bailun.localeslibrary.config_support.api_scenes.default_set.EnvHostBase;

/**
 * Created by kingpang on 2018/8/23.
 */

public class BaseApi {
    public EnvHostBase Host = null;

    public BaseApi(EnvHostBase host) {
        this.Host = host;
    }
}
