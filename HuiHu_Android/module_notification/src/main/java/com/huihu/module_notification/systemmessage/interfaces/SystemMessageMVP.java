package com.huihu.module_notification.systemmessage.interfaces;

import com.huihu.module_notification.systemmessage.entity.SystemMessagePageBean;

import java.util.List;

public interface SystemMessageMVP {
    interface IView {
        void p2VShowFirstData(List<SystemMessagePageBean.SystemMessageBean> beans);
        void p2vShowMoreData(List<SystemMessagePageBean.SystemMessageBean> beans);
        void p2vShowNoData();
        void p2vGetDataComplete();
        void p2vShowGetDataFail();
        void p2vStartQuestion(long questionId);
        void p2vStartAnswer(long answerId);
        void p2vStartWeb(String url);
    }
    interface IPresenter {
        void v2pGetFirstListData();
        void v2pGetMoreListData();
        void m2pNetFail();
        void m2pGetListDataSuccess(SystemMessagePageBean pageBean);
        void m2pGetDataComplete();
        void m2pGetDataFail();
        void v2pStartOtherFragment(SystemMessagePageBean.SystemMessageBean bean);
    }
    interface IModel {
        void p2mGetSystemMessageListNet(int pageIndex);
    }
}
