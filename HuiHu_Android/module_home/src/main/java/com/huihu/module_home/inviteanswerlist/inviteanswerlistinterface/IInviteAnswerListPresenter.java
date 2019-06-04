package com.huihu.module_home.inviteanswerlist.inviteanswerlistinterface;

import com.huihu.module_home.inviteanswer.entity.RecommendUserModel;

import java.util.List;

public interface IInviteAnswerListPresenter {

    void v2pGetRecentLogingUser(int pageIndex, int pageSize, int uid);

    void m2pGetRecentLogingUserSuccess(List<RecommendUserModel> model);

    void m2petRecentLogingUserFail(String message);
}
