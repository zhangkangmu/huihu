package com.huihu.module_mine.attentionquestion.presenter;

import android.util.Log;

import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.attentionquestion.attentionquestioninterface.IAttentionQuestionModel;
import com.huihu.module_mine.attentionquestion.attentionquestioninterface.IAttentionQuestionPresenter;
import com.huihu.module_mine.attentionquestion.attentionquestioninterface.IAttentionQuestionView;
import com.huihu.module_mine.attentionquestion.entity.AttentionQuestionInfo;
import com.huihu.module_mine.attentionquestion.model.ImpAttentionQuestionModel;

import java.util.List;

public class ImpAttentionQuestionPresenter implements IAttentionQuestionPresenter {
    private final IAttentionQuestionModel iAttentionQuestionModel = new ImpAttentionQuestionModel(this);
    private final IAttentionQuestionView iAttentionQuestionView;

    public ImpAttentionQuestionPresenter(IAttentionQuestionView iAttentionQuestionView) {
        this.iAttentionQuestionView = iAttentionQuestionView;
    }

    @Override
    public void v2pGetAttentionQuestionList() {
        iAttentionQuestionModel.p2mGetAttentionQuestionList(0,0,20, SPUtils.getInstance().getCurrentUid(),false);
    }
    @Override
    public void v2pGetMoreQuestionList(AttentionQuestionInfo.PageDatasBean pageDatasBean) {
        iAttentionQuestionModel.p2mGetAttentionQuestionList(pageDatasBean.getFollowId(),0,20, SPUtils.getInstance().getCurrentUid(),true);
    }

    @Override
    public void m2pNetComplete() {
        iAttentionQuestionView.p2vNetComplete();
    }

    @Override
    public void m2pReturnAttentionQuestionList(List<AttentionQuestionInfo.PageDatasBean> pageDatas,boolean isMore) {

        if (pageDatas !=null && pageDatas.size()>0){
            if (isMore){
                iAttentionQuestionView.p2vReturnMoreAttentionQuestionList(pageDatas);
            }else {
            iAttentionQuestionView.p2vReturnAttentionQuestionList(pageDatas);
            }
        }else{
            if (!isMore){
            iAttentionQuestionView.p2vShowNoData();
            }else{
            }
        }
    }

    @Override
    public void m2pNetFailed() {
        iAttentionQuestionView.p2vNetFailed();
    }

}
