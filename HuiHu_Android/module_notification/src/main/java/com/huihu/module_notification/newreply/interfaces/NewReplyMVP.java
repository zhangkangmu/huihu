package com.huihu.module_notification.newreply.interfaces;

import com.huihu.module_notification.newreply.entity.NewReplyPageBean;


import java.util.List;

public interface NewReplyMVP {
    interface IView {
        void p2vShowFirstData(String question, List<NewReplyPageBean.RecentAnswerBean> beans);
        void p2vShowMoreData(List<NewReplyPageBean.RecentAnswerBean> beans);
        void p2vShowNoData();
        void p2vShowGetDataFail();
        void p2vGetDataComplete();
        void p2vStartLookOtherPeople(long uid);
        void p2vStartAnswerDetail(long answerId);
    }
    interface IPresenter {
        void v2pGetFirstData(int messageStatus, long questionId);
        void v2pGetMoreData();
        void m2pGetDataSuccess(NewReplyPageBean pageBean, int pageIndex);
        void m2pGetDataComplete();
        void m2pGetDataFail();
        void m2lNetFail();
        void v2pLookOtherPeople(NewReplyPageBean.RecentAnswerBean bean);
        void v2pLookAnswer(NewReplyPageBean.RecentAnswerBean bean);
    }
    interface IModel {
        void p2vmGetNewReplyListNet(int messageStatus, int pageIndex, long questionId);
    }
}
