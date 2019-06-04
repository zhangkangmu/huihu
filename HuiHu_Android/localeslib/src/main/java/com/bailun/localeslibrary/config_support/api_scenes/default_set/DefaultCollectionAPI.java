package com.bailun.localeslibrary.config_support.api_scenes.default_set;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseApi;

public class DefaultCollectionAPI extends BaseApi {

    //获取我的收藏
    public String getCollection = Host.HuihuHost + "/api/getCollection";
    //取消收藏
    public String deleteCollection = Host.HuihuHost + "/api/deleteCollection";
    //添加收藏
    public String postCollection=Host.HuihuHost+"/api/postCollection";
    public DefaultCollectionAPI(EnvHostBase host) {
        super(host);
    }
}
