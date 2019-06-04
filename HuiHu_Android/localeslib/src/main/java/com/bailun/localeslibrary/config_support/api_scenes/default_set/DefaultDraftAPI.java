package com.bailun.localeslibrary.config_support.api_scenes.default_set;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseApi;

/**
 * create by ouyangjianfeng on 2019/4/17
 * description:
 */
public class DefaultDraftAPI extends BaseApi {

    public String getDraftList = Host.HuihuHost + "/api/getDraftList";
    public String postDraft = Host.HuihuHost + "/api/postDraft";

    public DefaultDraftAPI(EnvHostBase host) {
        super(host);
    }
}
