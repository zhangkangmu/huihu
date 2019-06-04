package com.huihu.module_notification.reply.presenter;

import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_notification.R;
import com.huihu.module_notification.reply.entity.AnswerReadState;
import com.huihu.module_notification.reply.entity.ReplyBean;
import com.huihu.module_notification.reply.replyinterface.IReplyModel;
import com.huihu.module_notification.reply.replyinterface.IReplyPresenter;
import com.huihu.module_notification.reply.replyinterface.IReplyView;
import com.huihu.module_notification.reply.model.ImpReplyModel;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ImpReplyPresenter implements IReplyPresenter {
    private final IReplyModel iReplyModel = new ImpReplyModel(this);
    private final IReplyView iReplyView;
    private boolean hasMineMore = true;
    private boolean hasAttentionMore = true;

    public ImpReplyPresenter(IReplyView iReplyView) {
        this.iReplyView = iReplyView;
    }

    @Override
    public void v2pGetFirstDataAttention() {
        hasAttentionMore = true;
        iReplyModel.m2pGetAttentionQuestionReplyList(-1L, false);
    }

    @Override
    public void v2pGetFirstDataMine() {
        hasMineMore = true;
        iReplyModel.m2pGetMineQuestionReplyList(-1L, false);
    }

    @Override
    public void v2pGetMoreDataAttention(ReplyBean bean) {
        if (hasAttentionMore){
            iReplyModel.m2pGetAttentionQuestionReplyList(bean.getEditTime(), true);
        } else {
            ToastUtil.show(R.string.module_notification_has_no_more);
            iReplyView.p2vGetDataComplete();
        }

    }

    @Override
    public void v2pGetMoreDataMine(ReplyBean bean) {
        if (hasMineMore){
            iReplyModel.m2pGetMineQuestionReplyList(bean.getEditTime(), true);
        } else {
            ToastUtil.show(R.string.module_notification_has_no_more);
            iReplyView.p2vGetDataComplete();
        }
    }

    @Override
    public void v2pReadAnswer(int type, ReplyBean bean) {
        iReplyModel.m2pPutAnswerReadState(type,bean);
        iReplyView.p2vStartAnswer(bean.getIdeaId());
    }

    @Override
    public void v2pReadQuestion(int type, ReplyBean bean) {
        iReplyModel.m2pPutAnswerReadState(type, bean);
        iReplyView.p2vStartQuestion(bean.getQuestion().getIdeaId());
    }

    @Override
    public void v2pLookOtherPeople(ReplyBean bean) {
        iReplyView.p2vStartOtherPeople(bean.getUserInfo().getUid());
    }

    @Override
    public void m2pGetDataSuccessAttention(List<ReplyBean> beans, boolean isMore) {
        if (beans != null && beans.size() > 0){
            if (isMore){
                iReplyView.p2vShowMoreDataAttention(beans);
            } else {
                iReplyView.p2vShowFirstDataAttention(beans);
            }
        } else {
            if (isMore){
                hasAttentionMore = false;
                ToastUtil.show(R.string.module_notification_has_no_more);
            } else {
                iReplyView.p2vShowNoDataAttention();
            }
        }
    }

    @Override
    public void m2pGetDataSuccessMine(List<ReplyBean> beans, boolean isMore) {
        if (beans != null && beans.size() > 0){
            if (isMore){
                iReplyView.p2vShowMoreDataMine(beans);
            } else {
                iReplyView.p2vShowFirstDataMine(beans);
            }
        } else {
            if (isMore){
                hasMineMore = false;
                ToastUtil.show(R.string.module_notification_has_no_more);
            } else {
                iReplyView.p2vShowNoDataMine();
            }
        }
    }

    @Override
    public void m2pNetFail() {
        ToastUtil.show(R.string.uilib_http_request_fail);
    }

    @Override
    public void m2pGetDataFailAttention() {
        iReplyView.p2vGetDataFailAttention();
    }

    @Override
    public void m2pGetDataFailMine() {
        iReplyView.p2vGetDataFailMine();
    }

    @Override
    public void m2pGetDataComplete() {
        iReplyView.p2vGetDataComplete();
    }

    @Override
    public void m2pPutReadStateSuccess(int type,ReplyBean bean, AnswerReadState state) {
        EventBus.getDefault().post(state);
        state.setAnswer(true);
        iReplyView.p2vChangeReplyReadStats(type, bean);
    }
}
