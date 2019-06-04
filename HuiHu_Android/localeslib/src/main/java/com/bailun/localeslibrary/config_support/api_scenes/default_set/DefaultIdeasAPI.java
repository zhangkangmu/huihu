package com.bailun.localeslibrary.config_support.api_scenes.default_set;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseApi;

import java.util.logging.Handler;

public class DefaultIdeasAPI extends BaseApi {
    //获取热门列表
    public String GetPopularIdeaList = Host.HuihuHost + "/api/getPopularIdea";
    //获取轮播图
    public String GetSwitchGrph = Host.HuihuHost + "/api/getSwitchGrph";
    //获取热榜本
    public String GetHoldIdea = Host.HuihuHost + "/api/getHoldIdea";
    //获取用户信息
    public String GetUserHomePageDetails = Host.HuihuHost + "/api/getUserHomePageDetails";
    //获取用户的所有问答列表
    public String GetIdeaList = Host.HuihuHost + "/api/getIdeaList";
    //获取用户的所有圈子列表
    public String GetDiscussList = Host.HuihuHost + "/api/getDiscussList";
    //获取用户的所有态度列表
    public String GetViewpointListByUid = Host.HuihuHost + "/api/getViewpointListByUid";
    //提问
    public String postQuestion = Host.HuihuHost + "/api/postQuestion";
    //查看问题详情页
    public String getQuestionInfo = Host.HuihuHost + "/api/getQuestionInfo";
    //获取提问的回答列表
    public String getAnswerList = Host.HuihuHost + "/api/getAnswerList";
    //获取关注圈子
    public String getFollowCircles = Host.HuihuHost + "/api/getFollowCircles";
    //获取关注分类
    public String getFollowCategorys = Host.HuihuHost + "/api/getFollowCategorys";
    //查看问答详情页面
    public String GetAnswerInfo = Host.HuihuHost + "/api/getAnswerInfo";
    //发布回答
    public String postAnswer = Host.HuihuHost + "/api/postAnswer";

    //删除（讨论，问题，回答）
    public String DeleteIdea = Host.HuihuHost +"/api/deleteIdea";
    //添加不感兴趣
    public String UnInterestedIdea = Host.HuihuHost+"/api/postUnInterestedIdea";

    public String GetNewReply = Host.HuihuHost + "/api/getRecentAnswer";

    public DefaultIdeasAPI(EnvHostBase host) {
        super(host);
    }
}
