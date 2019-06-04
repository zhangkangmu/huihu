package com.bailun.localeslibrary.config_support.api_scenes.default_set;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseApi;

/**
 * create by ouyangjianfeng on 2019/3/27
 * description:
 */
public class DefaultCategorysAPI extends BaseApi {

    public String postCategoryByTitle = Host.HuihuHost + "/api/postCategoryByTitle";
    public String GetAnswer = Host.HuihuHost + "/api/getCategoryAnswer";
    public String GetCategoryInfo = Host.HuihuHost + "/api/getCategoryInfo";

    public DefaultCategorysAPI(EnvHostBase host) {
        super(host);
    }
}
