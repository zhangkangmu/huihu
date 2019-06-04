package com.bailun.localeslibrary.config_support.api_scenes.default_set;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseApi;

/**
 * create by wangjing on 2019/3/20 0020
 * description:
 */
public class DefaultUserNoticeAPI extends BaseApi {

    public String GetMessageList = Host.HuihuHost + "/api/getMessageSessionList";
    public String GetUserPushSetting = Host.HuihuHost + "/api/getUserNoticeList";
    public String PostUserPushSetting = Host.HuihuHost + "/api/postUserNotices";
    public String GetUserNoticeByNoticeIdOfPage = Host.HuihuHost + "/api/getUserNoticeByNoticeIdOfPage";
    public String GetMineReplyList = Host.HuihuHost + "/api/getOwnAnswerList";
    public String GetAttentionReplyList = Host.HuihuHost + "/api/getAttentionAnswerList";
    public String PutUserNoticeRead = Host.HuihuHost + "/api/putUserNoticeRead";
    public String PutAllNoticeRead = Host.HuihuHost + "/api/putAllUserNoticeRead";
    public String PutAnswerReadState = Host.HuihuHost + "/api/putAnswerReadStatus";
    public String PutAnswerInviteReadState = Host.HuihuHost + "/api/putQuestionReadStatus";
    public String DeleteSessionAnswer = Host.HuihuHost + "/api/delSessionAnswer";

    public DefaultUserNoticeAPI(EnvHostBase host) {
        super(host);
    }
}
