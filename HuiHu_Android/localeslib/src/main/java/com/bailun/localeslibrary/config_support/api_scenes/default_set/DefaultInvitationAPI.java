package com.bailun.localeslibrary.config_support.api_scenes.default_set;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseApi;

/**
 * create by ouyangjianfeng on 2019/4/18
 * description:
 */
public class DefaultInvitationAPI extends BaseApi {

    public String getInvitationList = Host.HuihuHost + "/api/getInvitationList";

    public DefaultInvitationAPI(EnvHostBase host) {
        super(host);
    }
}
