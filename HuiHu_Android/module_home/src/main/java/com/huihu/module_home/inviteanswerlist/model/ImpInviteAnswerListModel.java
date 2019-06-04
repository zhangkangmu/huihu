package com.huihu.module_home.inviteanswerlist.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_home.inviteanswer.entity.GetRecentLogingUserSubcode;
import com.huihu.module_home.inviteanswer.entity.RecommendUserModel;
import com.huihu.module_home.inviteanswerlist.inviteanswerlistinterface.IInviteAnswerListModel;
import com.huihu.module_home.inviteanswerlist.inviteanswerlistinterface.IInviteAnswerListPresenter;

import java.util.List;

public class ImpInviteAnswerListModel implements IInviteAnswerListModel {
    private final IInviteAnswerListPresenter iInviteAnswerListPresenter;

    public ImpInviteAnswerListModel(IInviteAnswerListPresenter iInviteAnswerListPresenter) {
        this.iInviteAnswerListPresenter = iInviteAnswerListPresenter;
    }

    @Override
    public void p2mGetRecentLogingUser(int index, int size, int uid) {
        getRecentLogingUser(index, size, uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                switch (model.getSubCode()) {
                    case GetRecentLogingUserSubcode.success:
                        List<RecommendUserModel> userModel = new Gson().fromJson(model.getBodyMessage(), new TypeToken<List<RecommendUserModel>>() {
                        }.getType());
                        iInviteAnswerListPresenter.m2pGetRecentLogingUserSuccess(userModel);
                        break;
                    case GetRecentLogingUserSubcode.paramError:
                    case GetRecentLogingUserSubcode.businessException:
                        iInviteAnswerListPresenter.m2petRecentLogingUserFail(model.getMessage());
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iInviteAnswerListPresenter.m2petRecentLogingUserFail(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    private void getRecentLogingUser(int index, int size, int uid, HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.User.getRecentLogingUser, NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("pageIndex", index + "");
        param.addQuery("pageSize", size + "");
        param.addQuery("userId", uid + "");
        HuihuHttpUtils.httpRequest(param, callBack);
    }

    private void getInvitationList(HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Invitation.getInvitationList, NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("", "");
        param.addQuery("", "");
        param.addQuery("", "");
        HuihuHttpUtils.httpRequest(param, callBack);
    }
}
