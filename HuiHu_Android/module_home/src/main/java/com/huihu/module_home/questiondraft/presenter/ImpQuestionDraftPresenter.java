package com.huihu.module_home.questiondraft.presenter;

import com.huihu.module_home.questiondraft.model.GetDraftModel;
import com.huihu.module_home.questiondraft.model.ImpQuestionDraftModel;
import com.huihu.module_home.questiondraft.questiondraftinterface.IQuestionDraftModel;
import com.huihu.module_home.questiondraft.questiondraftinterface.IQuestionDraftPresenter;
import com.huihu.module_home.questiondraft.questiondraftinterface.IQuestionDraftView;

import java.util.List;

public class ImpQuestionDraftPresenter implements IQuestionDraftPresenter {
    private final IQuestionDraftModel iQuestionDraftModel = new ImpQuestionDraftModel(this);
    private final IQuestionDraftView iQuestionDraftView;
    private long mTimeStamp;

    public ImpQuestionDraftPresenter(IQuestionDraftView iQuestionDraftView) {
        this.iQuestionDraftView = iQuestionDraftView;
    }

    @Override
    public void v2pGetDraftList(int timeStamp, int pagesize, int question, int uid) {
        mTimeStamp = timeStamp;
        iQuestionDraftModel.p2mGetDraftList(timeStamp, pagesize, question, uid);
    }

    @Override
    public void m2pGetDraftListSuccess(GetDraftModel model) {
        //请求返回的数据
        List<GetDraftModel.PageDatasBean> datas = model.getPageDatas();
        if (mTimeStamp == 0) {//下拉刷新
            if (datas == null || datas.isEmpty()) {//拉取的数据为空
                iQuestionDraftView.p2vShowEmptyView();
            } else {
                if (datas.size() < 20) {
                    iQuestionDraftView.p2vSetNoMoreData();
                }
                iQuestionDraftView.p2vShowRefreshData(datas);
            }
        } else {//加载更多
            if (datas == null || datas.isEmpty()) {
                //没有更多
                iQuestionDraftView.p2vSetNoMoreData();
            } else {
                if (datas.size() < 20) {
                    iQuestionDraftView.p2vSetNoMoreData();
                }
                iQuestionDraftView.p2vAddData(datas);
            }
        }
        iQuestionDraftView.p2vFinishRefresh();
        iQuestionDraftView.p2vFinishLoadMore();
    }

    @Override
    public void m2pGetDraftListFail(String message) {
        List<GetDraftModel.PageDatasBean> adapterData = iQuestionDraftView.getData();
        if (adapterData == null || adapterData.isEmpty()) {
            iQuestionDraftView.p2vShowNoNetWork();
        }
        iQuestionDraftView.p2vShowToast(message);
        iQuestionDraftView.p2vFinishRefresh();
        iQuestionDraftView.p2vFinishLoadMore();
    }
}
