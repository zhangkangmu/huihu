package com.bailun.localeslibrary.config_support.api_scenes.default_set;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseApi;

/**
 * Created by $USER_NAME on 2019/3/26.
 * description：
 */
public class DufaultCommonAPI extends BaseApi {
    public DufaultCommonAPI(EnvHostBase host) {
        super(host);
    }

    public String postImage = Host.HuihuHost + "/api/postImage";
}
