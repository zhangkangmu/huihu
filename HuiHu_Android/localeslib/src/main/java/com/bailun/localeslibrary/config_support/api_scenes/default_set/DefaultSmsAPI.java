package com.bailun.localeslibrary.config_support.api_scenes.default_set;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseApi;

/**
 * create by ouyangjianfeng on 2019/3/20
 * description: 短信API
 */
public class DefaultSmsAPI extends BaseApi {

    public String postSendCode = Host.HuihuHost + "/api/postSendCode";
    public String getCountries = Host.HuihuHost + "/api/getCountries";
    public String postValidateCode = Host.HuihuHost + "/api/postValidateCode";

    public DefaultSmsAPI(EnvHostBase host) {
        super(host);
    }
}
