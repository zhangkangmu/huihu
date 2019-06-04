package com.huihu.module_notification.replyinvitation.presenter;

import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_notification.R;
import com.huihu.module_notification.reply.entity.AnswerReadState;
import com.huihu.module_notification.replyinvitation.entity.ReplyInvitationPageBean;
import com.huihu.module_notification.replyinvitation.replyinvitationinterface.IReplyInvitationModel;
import com.huihu.module_notification.replyinvitation.replyinvitationinterface.IReplyInvitationPresenter;
import com.huihu.module_notification.replyinvitation.replyinvitationinterface.IReplyInvitationView;
import com.huihu.module_notification.replyinvitation.model.ImpReplyInvitationModel;

import org.greenrobot.eventbus.EventBus;

public class ImpReplyInvitationPresenter implements IReplyInvitationPresenter {

    private final IReplyInvitationModel iReplyInvitationModel = new ImpReplyInvitationModel(this);
    private final IReplyInvitationView iReplyInvitationView;
    private boolean hasMore;
    private int pageIndex;

    public ImpReplyInvitationPresenter(IReplyInvitationView iReplyInvitationView) {
        this.iReplyInvitationView = iReplyInvitationView;
    }                             

    @Override
    public void v2pGetFirstListData() {
        pageIndex = 1;
        hasMore = true;
        iReplyInvitationModel.p2mGetReplyInvitationListNet(pageIndex);
    }

    @Override
    public void v2pGetMoreListData() {
        if (hasMore){
            iReplyInvitationModel.p2mGetReplyInvitationListNet(pageIndex + 1);
        } else{
            iReplyInvitationView.p2vGetDataComplete();
            ToastUtil.show(R.string.module_notification_has_no_more);
        }
    }

    @Override
    public void m2pGetListDataSuccess(ReplyInvitationPageBean bean) {
        if (bean.getPageDatas() != null && bean.getPageDatas().size() > 0){
            pageIndex = bean.getPageIndex();
            if (pageIndex > 1){
                iReplyInvitationView.p2vShowMoreData(bean.getPageDatas());
            } else {
                iReplyInvitationView.p2vShowFirstData(bean.getPageDatas());
            }
        } else {
            if (pageIndex > 1){
                hasMore = false;
                ToastUtil.show(R.string.module_notification_has_no_more);
            } else {
                iReplyInvitationView.p2vShowNoData();
            }
        }
    }

    @Override
    public void m2pNetFail() {
        ToastUtil.show(R.string.uilib_http_request_fail);
    }

    @Override
    public void m2pGetDataComplete() {
        iReplyInvitationView.p2vGetDataComplete();
    }

    @Override
    public void m2pGetDataFail() {
        iReplyInvitationView.p2vShowNetFail();
    }

    @Override
    public void v2pLookOtherPeople(ReplyInvitationPageBean.ReplyInvitationBean bean) {
        iReplyInvitationView.p2vStartOtherPeople(bean.getInfo().getUserId());
    }

    @Override
    public void v2pLookQuestion(ReplyInvitationPageBean.ReplyInvitationBean bean) {
        iReplyInvitationView.p2vStartQuestion(bean.getInfo().getIdeaId());
        iReplyInvitationModel.p2mPutInviteRead(bean);
    }

    @Override
    public void m2pReadSuccess(ReplyInvitationPageBean.ReplyInvitationBean bean, AnswerReadState state) {
        iReplyInvitationView.p2vChangeReadState(bean);
        state.setAnswer(false);
        EventBus.getDefault().post(state);
    }
}
