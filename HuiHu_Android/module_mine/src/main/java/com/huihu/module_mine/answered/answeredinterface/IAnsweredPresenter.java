package com.huihu.module_mine.answered.answeredinterface;

import com.huihu.module_mine.answered.entity.AnsweredInfo;

import java.util.List;

public interface IAnsweredPresenter {
void v2pGetAnseweredList(long fit);
void v2pGetMoreAnsweredList(AnsweredInfo.PageDatasBean bean);
void m2pReturnAnsweredList(List<AnsweredInfo.PageDatasBean> mlist,boolean isMore);
void m2pNetFail();
void m2pNetComplete();
void v2pJudgyAnswerItemClick(int viewId, AnsweredInfo.PageDatasBean bean);
}
