package com.bailun.localeslibrary.config_support.api_scenes.api_host_depend;

import android.support.annotation.CallSuper;

import com.bailun.localeslibrary.config_support.api_scenes.default_set.DefaultCategorysAPI;
import com.bailun.localeslibrary.config_support.api_scenes.default_set.DefaultCircleAPI;
import com.bailun.localeslibrary.config_support.api_scenes.default_set.DefaultCollectionAPI;
import com.bailun.localeslibrary.config_support.api_scenes.default_set.DefaultCommentsAPI;
import com.bailun.localeslibrary.config_support.api_scenes.default_set.DefaultCommonAPI;
import com.bailun.localeslibrary.config_support.api_scenes.default_set.DefaultDraftAPI;
import com.bailun.localeslibrary.config_support.api_scenes.default_set.DefaultFollowsAPI;
import com.bailun.localeslibrary.config_support.api_scenes.default_set.DefaultIdeasAPI;
import com.bailun.localeslibrary.config_support.api_scenes.default_set.DefaultInvitationAPI;
import com.bailun.localeslibrary.config_support.api_scenes.default_set.DefaultLoginAndRegisterAPI;
import com.bailun.localeslibrary.config_support.api_scenes.default_set.DefaultReportAPI;
import com.bailun.localeslibrary.config_support.api_scenes.default_set.DefaultSmsAPI;
import com.bailun.localeslibrary.config_support.api_scenes.default_set.DefaultUserAPI;
import com.bailun.localeslibrary.config_support.api_scenes.default_set.DefaultUserNoticeAPI;
import com.bailun.localeslibrary.config_support.api_scenes.default_set.DefaultViewPointsAPI;
import com.bailun.localeslibrary.config_support.api_scenes.default_set.EnvHostBase;
import com.bailun.localeslibrary.type.EnvironmentEnum;

/**
 * Created by kingpang on 2018/8/23.
 */

public class BaseHostApiUtil {
    //kingpang.test 需要使用API.XXX的形式来方便调用，就在这里补充，然后在createAPIs里补充：newApiVariable = new DefaultXXXX();
    public DefaultUserNoticeAPI UserNotice = null;
    public DefaultFollowsAPI Follow = null;
    public DefaultLoginAndRegisterAPI LoginAndRegister = null;
    public DefaultSmsAPI Sms = null;
    public DefaultIdeasAPI Ideas = null;
    public DefaultUserAPI User = null;
    public DefaultCommonAPI Common = null;
    public DefaultCategorysAPI Categorys = null;
    public DefaultCommentsAPI Comments = null;
    public DefaultViewPointsAPI ViewPoint = null;
    public DefaultReportAPI Report = null;
    public DefaultCircleAPI Circle = null;
    public DefaultCollectionAPI Collection = null;
    public DefaultDraftAPI Draft = null;
    public DefaultInvitationAPI Invitation = null;

    public void activation(EnvironmentEnum env) {

        EnvHostBase curHost = null;
        switch (env) {
            case DEBUG:
                curHost = getDebugHost();
                break;
            case TEST:
                curHost = getTestHost();
                break;
            case PRE_RELEASE:
                curHost = getPreReleaseHost();
                break;
            case RELEASE:
                curHost = getReleaseHost();
                break;
            default:
                curHost = new EnvHostBase();
                break;
        }

        createAPIs(curHost);
    }

    //    @CallSuper
    public EnvHostBase getDebugHost() {
        return new EnvHostBase();
    }

    //    @CallSuper
    public EnvHostBase getTestHost() {
        return new EnvHostBase();
    }

    //    @CallSuper
    public EnvHostBase getPreReleaseHost() {
        return new EnvHostBase();
    }

    //    @CallSuper
    public EnvHostBase getReleaseHost() {
        return new EnvHostBase();
    }

    @CallSuper
    public void createAPIs(EnvHostBase host) {
        LoginAndRegister = new DefaultLoginAndRegisterAPI(host);
        UserNotice = new DefaultUserNoticeAPI(host);
        Follow = new DefaultFollowsAPI(host);
        Sms = new DefaultSmsAPI(host);
        Ideas = new DefaultIdeasAPI(host);
        User = new DefaultUserAPI(host);
        Common = new DefaultCommonAPI(host);
        Categorys = new DefaultCategorysAPI(host);
        Comments = new DefaultCommentsAPI(host);
        ViewPoint = new DefaultViewPointsAPI(host);
        Circle = new DefaultCircleAPI(host);
        Report = new DefaultReportAPI(host);
        Collection = new DefaultCollectionAPI(host);
        Draft = new DefaultDraftAPI(host);
        Invitation = new DefaultInvitationAPI(host);
    }
}
