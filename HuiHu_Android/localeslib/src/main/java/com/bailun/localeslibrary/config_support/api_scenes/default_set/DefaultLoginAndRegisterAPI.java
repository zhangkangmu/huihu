package com.bailun.localeslibrary.config_support.api_scenes.default_set;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseApi;

/**
 * create by ouyangjianfeng on 2019/3/20
 * description:
 */
public class DefaultLoginAndRegisterAPI extends BaseApi {

    public String getSendLoginSms = Host.HuihuHost + "/api/getSendLoginSms";
    public String postLoginUserBySms = Host.HuihuHost + "/api/postLoginUserBySms";
    public String postLoginUser = Host.HuihuHost + "/api/postLoginUser";//账号密码登录
    public String putLoginOut = Host.HuihuHost + "/api/putLoginOut";//退出登录

    public DefaultLoginAndRegisterAPI(EnvHostBase host) {
        super(host);
    }

}
