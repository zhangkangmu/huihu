package com.bailun.localeslibrary.config_support.api_scenes.default_set;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseApi;

/**
 * create by ouyangjianfeng on 2019/3/22
 * description:
 */
public class DefaultUserAPI extends BaseApi {

    public String putUpdatePasswordByCode = Host.HuihuHost + "/api/putUpdatePasswordByCode";
    public String PostAddFeedback = Host.HuihuHost + "/api/postAddFeedback";
    public String getUserEditDetails=Host.HuihuHost+"/api/getUserEditDetails";
    public String getNickIsUsed=Host.HuihuHost+"/api/getNickIsUsed";
    public String putUpdateEducation=Host.HuihuHost+"/api/putUpdateEducation";
    public String putUpdateOtherDetail=Host.HuihuHost+"/api/putUpdateOtherDetail";
    public String putUpdateHeadImage=Host.HuihuHost+"/api/putUpdateHeadImage";
    public String putUpdatePhone=Host.HuihuHost+"/api/putUpdatePhone";
    public String putUpdateUserSignature=Host.HuihuHost+"/api/putUpdateUserSignature";
    public String GetUserAuthList=Host.HuihuHost+"/api/getUserAuthList";
    public String getRecentLogingUser=Host.HuihuHost+"/api/getRecentLogingUser";

    public DefaultUserAPI(EnvHostBase host) {
        super(host);
    }
}
