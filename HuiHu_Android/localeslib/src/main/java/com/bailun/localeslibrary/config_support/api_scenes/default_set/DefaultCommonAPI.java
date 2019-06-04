package com.bailun.localeslibrary.config_support.api_scenes.default_set;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseApi;

/**
 * create by wangjing on 2019/3/27 0027
 * description:
 */
public class DefaultCommonAPI extends BaseApi {

    public String PostImage = Host.HuihuHost + "/api/postImage";
    public String GetSystemTime = Host.HuihuHost + "/api/getSystemTime";

    public DefaultCommonAPI(EnvHostBase host) {
        super(host);
    }
}
