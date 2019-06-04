package com.huihu.module_home.questionandanswerlist.presenter;

import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_home.questionandanswerlist.entity.AnswerModel;
import com.huihu.module_home.questionandanswerlist.entity.GetAnswerInfoParamModel;
import com.huihu.module_home.questionandanswerlist.entity.QuestionDetailModel;
import com.huihu.module_home.questionandanswerlist.entity.QuestionStatus;
import com.huihu.module_home.questionandanswerlist.model.ImpQuestionAndAnswerListModel;
import com.huihu.module_home.questionandanswerlist.questionandanswerlistinterface.IQuestionAndAnswerListModel;
import com.huihu.module_home.questionandanswerlist.questionandanswerlistinterface.IQuestionAndAnswerListPresenter;
import com.huihu.module_home.questionandanswerlist.questionandanswerlistinterface.IQuestionAndAnswerListView;

import java.util.List;

public class ImpQuestionAndAnswerListPresenter implements IQuestionAndAnswerListPresenter {
    private final IQuestionAndAnswerListModel iQuestionAndAnswerListModel = new ImpQuestionAndAnswerListModel(this);
    private final IQuestionAndAnswerListView iQuestionAndAnswerListView;

    private int mCount;

    public ImpQuestionAndAnswerListPresenter(IQuestionAndAnswerListView iQuestionAndAnswerListView) {
        this.iQuestionAndAnswerListView = iQuestionAndAnswerListView;
    }

    @Override
    public void v2pGetQuestionInfo(long ideaId) {
        iQuestionAndAnswerListModel.p2mGetQuestionInfo(ideaId);
    }

    @Override
    public void m2pGetQuestionInfoSuccess(QuestionDetailModel model) {
        mCount++;

        iQuestionAndAnswerListView.p2vShowQuestionDetail(model);

        checkDatas();
    }

    private void checkDatas() {
        if (mCount == 2) {
            iQuestionAndAnswerListView.p2vShowContent();
        }
    }

    @Override
    public void v2pGetAnswerList(GetAnswerInfoParamModel model) {
        iQuestionAndAnswerListModel.p2mGetAnswerList(model);
    }

    @Override
    public void m2pGetAnswerInfoSuccess(AnswerModel answerModel) {
        mCount++;

        List<AnswerModel.PageDatasBean> datas = answerModel.getPageDatas();
        List<AnswerModel.PageDatasBean> adapterDatas = iQuestionAndAnswerListView.p2vGetDatas();
        if (adapterDatas == null || adapterDatas.isEmpty()) {
            //第一次拉取
            if (datas == null || datas.isEmpty()) {
                //获取推荐的人的数据,显示推荐人的列表
            } else {
                iQuestionAndAnswerListView.p2vShowAnswer(datas);
            }
        } else {
            //添加数据
            if (datas == null || datas.isEmpty()) {
                //获取到的数据为空，已经到底部
            } else {
                iQuestionAndAnswerListView.p2vShowAnswer(datas);
            }
        }
        checkDatas();
    }

    @Override
    public void m2pGetAnswerInfoFail(String message) {
        iQuestionAndAnswerListView.p2vShowToast(message);
        iQuestionAndAnswerListView.p2vCheckHasData();
    }

    @Override
    public void v2pPutGiveFollows(long aboutId, int followType, int state, int uid) {
        iQuestionAndAnswerListModel.p2mPutGiveFollows(aboutId, followType, state, uid);
    }

    @Override
    public void m2pPutGiveFollowsFail(String msg) {
        iQuestionAndAnswerListView.p2vShowToast(msg);
    }

    @Override
    public void m2pPutGiveFollowsSuccess() {
        iQuestionAndAnswerListView.setButtonState();
    }

    @Override
    public void m2pRefreshFinish() {
        iQuestionAndAnswerListView.p2vRefreshFinish();
    }

    @Override
    public void m2pPutGivePersonSuccess() {

    }

    @Override
    public void v2pShowShareQuestionDialog(int uid, int state) {
        if (uid == SPUtils.getInstance().getCurrentUid()) {
            //自己的提问
            //判断问题状态
            switch (state) {
                case QuestionStatus.release:
                    iQuestionAndAnswerListView.p2vShowMyselfQusetionRelease();

                    break;
                case QuestionStatus.violation:

                    break;
                case QuestionStatus.review:

                    break;
            }
        } else {
            //他人的提问
            iQuestionAndAnswerListView.p2vShowOtherQuestion();
        }
    }
}
