package com.huihu.module_notification.newreply.presenter;

import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_notification.R;
import com.huihu.module_notification.newreply.entity.NewReplyPageBean;
import com.huihu.module_notification.newreply.interfaces.NewReplyMVP;
import com.huihu.module_notification.newreply.model.ImpNewReplyModel;
import com.huihu.module_notification.reply.entity.ReplyPageBean;
import com.huihu.uilib.def.NetDataBoolean;

public class ImpNewReplyPresenter implements NewReplyMVP.IPresenter {
    private final NewReplyMVP.IView iView;
    private final NewReplyMVP.IModel iModel = new ImpNewReplyModel(this);
    private int pageIndex;
    private long mQuestionId;
    private boolean hasMore = true;

    public ImpNewReplyPresenter(NewReplyMVP.IView iView) {
        this.iView = iView;
    }

    @Override
    public void v2pGetFirstData(int messageStatus, long questionId) {
        pageIndex = 1;
        mQuestionId = questionId;
        hasMore = true;
        iModel.p2vmGetNewReplyListNet(messageStatus, pageIndex, questionId);
    }

    @Override
    public void v2pGetMoreData() {
        if (hasMore){
            iModel.p2vmGetNewReplyListNet(NetDataBoolean.NET_TRUE, pageIndex + 1, mQuestionId);
        } else {
            ToastUtil.show(R.string.module_notification_has_no_more);
            iView.p2vGetDataComplete();
        }
    }

    @Override
    public void m2pGetDataSuccess(NewReplyPageBean pageBean, int pageIndex) {
        if (pageBean != null && pageBean.getRecentAnswer() != null && pageBean.getRecentAnswer().size() > 0){
            if (pageIndex > 1){
                iView.p2vShowMoreData(pageBean.getRecentAnswer());
            } else {
                iView.p2vShowFirstData(pageBean.getTitle(), pageBean.getRecentAnswer());
            }
        } else {
            if (pageIndex > 1){
                hasMore = false;
                ToastUtil.show(R.string.module_notification_has_no_more);
            } else {
                iView.p2vShowNoData();
            }
        }
    }

    @Override
    public void m2pGetDataComplete() {
        iView.p2vGetDataComplete();
    }

    @Override
    public void m2pGetDataFail() {
        iView.p2vShowGetDataFail();
    }

    @Override
    public void m2lNetFail() {
        ToastUtil.show(R.string.uilib_http_request_fail);
    }

    @Override
    public void v2pLookOtherPeople(NewReplyPageBean.RecentAnswerBean bean) {
        iView.p2vStartLookOtherPeople(bean.getUserInfo().getUid());
    }

    @Override
    public void v2pLookAnswer(NewReplyPageBean.RecentAnswerBean bean) {
        iView.p2vStartAnswerDetail(bean.getIdeaId());
    }
}
