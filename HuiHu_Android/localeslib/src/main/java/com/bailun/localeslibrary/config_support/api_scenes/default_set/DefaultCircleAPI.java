package com.bailun.localeslibrary.config_support.api_scenes.default_set;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseApi;

/**
 * create by wangjing on 2019/4/11 0011
 * description:
 */
public class DefaultCircleAPI extends BaseApi {

    public String PutCircleMember = Host.HuihuHost + "/api/putCircleMember";
    public String PutCircleManager = Host.HuihuHost + "/api/putCircleManagerConfirm";
    public String GetCircle = Host.HuihuHost + "/api/getCircle";
    //获取某个圈子的所有讨论
    public String getCircleDiscuss = Host.HuihuHost + "/api/getCircleDiscuss";
    //获取圈子信息
    public String getCircleInfo = Host.HuihuHost + "/api/getCircleInfo";
    //获取我的所有讨论
    public String GetMyCircleDiscuss = Host.HuihuHost + "/api/getMyCircleDiscuss";
    //获取某用户的所有讨论
    public String GetDiscussList = Host.HuihuHost + "/api/getDiscussList";
    //获得推荐圈子
    public String GetRecemendCircle = Host.HuihuHost + "/api/getRecemendCircle";
    //获得推荐的讨论
    public String GetRecomendList = Host.HuihuHost + "/api/getRecemendDiscuss";
    //成員管理（加入，退出）圈子
    public String putCircleMember = Host.HuihuHost + "/api/putCircleMember";
    //获得我的圈子信息
    public String GetMyCircle = Host.HuihuHost + "/api/getMyCircle";
    //获取推荐圈子
    public String getRecemendCircle = Host.HuihuHost + "/api/getRecemendCircle";
    //获取我的所有讨论
    public String getMyCircleDiscuss = Host.HuihuHost + "/api/getMyCircleDiscuss";

    public String GetDiscussInfo = Host.HuihuHost + "/api/getDiscussInfo";
    //创建圈子
    public String PostCircle = Host.HuihuHost + "/api/postCircle";
    //获取圈子成员信息
    public String getCircleMember = Host.HuihuHost + "/api/getCircleMember";
    //发布讨论
    public String postDiscuss = Host.HuihuHost + "/api/postDiscuss";
    //禁言用户
    public  String putForbidMember=Host.HuihuHost+"/api/putForbidMember";



    public DefaultCircleAPI(EnvHostBase host) {
        super(host);
    }
}
