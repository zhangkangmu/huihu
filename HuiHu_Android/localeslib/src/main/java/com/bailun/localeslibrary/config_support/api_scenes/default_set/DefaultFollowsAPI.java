package com.bailun.localeslibrary.config_support.api_scenes.default_set;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseApi;

/**
 * create by wangjing on 2019/3/20 0020
 * description:
 */
public class DefaultFollowsAPI extends BaseApi {

    public String GetFansOrFollowList = Host.HuihuHost + "/api/getFansOrFollowList";

    public String PutGiveFollows = Host.HuihuHost + "/api/putGiveFollows";
    //获取关注圈子
    public String getFollowCircles = Host.HuihuHost + "/api/getFollowCircles";
    //获取关注分类
    public String getFollowCategorys = Host.HuihuHost + "/api/getFollowCategorys";

    //获取关注的问题列表
    public String getFollowIdeas = Host.HuihuHost + "/api/getFollowIdeas";


    public DefaultFollowsAPI(EnvHostBase host) {
        super(host);
    }
}
