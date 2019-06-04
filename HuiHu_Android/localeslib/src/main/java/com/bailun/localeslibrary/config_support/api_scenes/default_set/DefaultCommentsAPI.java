package com.bailun.localeslibrary.config_support.api_scenes.default_set;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseApi;

/**
 * create by wangjing on 2019/3/28 0028
 * description:
 */
public class DefaultCommentsAPI extends BaseApi {

    public String OtherCommentList = Host.HuihuHost + "/api/getOtherCommentListByUserId";
    public String CommentListByIdeaId = Host.HuihuHost + "/api/getCommentListByIdeaId";
    public String GetIdeasByCommentId = Host.HuihuHost + "/api/getIdeasByCommentId";
    public String ChildCommentList = Host.HuihuHost + "/api/getChildCommentList";
    public String PostPublishComment = Host.HuihuHost + "/api/postPublishComment";
    public String PutDeleteComment = Host.HuihuHost + "/api/putDeleteComment";
    //获取用户的所有评论列表
    public String GetCommentListByUserId = Host.HuihuHost + "/api/getCommentListByUserId";

    public DefaultCommentsAPI(EnvHostBase host) {
        super(host);
    }
}
