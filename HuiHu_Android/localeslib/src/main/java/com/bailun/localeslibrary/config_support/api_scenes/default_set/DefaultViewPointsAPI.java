package com.bailun.localeslibrary.config_support.api_scenes.default_set;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseApi;

/**
 * create by wangjing on 2019/3/29 0029
 * description:
 */
public class DefaultViewPointsAPI extends BaseApi {

    public String GetOtherLikeMeList = Host.HuihuHost + "/api/getViewpointOtherListByUid";
    public String PutGiveViewpoint = Host.HuihuHost + "/api/putGiveViewpoint";

    public DefaultViewPointsAPI(EnvHostBase host) {
        super(host);
    }
}
