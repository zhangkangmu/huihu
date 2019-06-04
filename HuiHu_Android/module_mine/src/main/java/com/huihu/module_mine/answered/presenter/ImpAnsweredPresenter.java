package com.huihu.module_mine.answered.presenter;

import android.os.Bundle;

import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.R;
import com.huihu.module_mine.answered.answeredinterface.IAnsweredModel;
import com.huihu.module_mine.answered.answeredinterface.IAnsweredPresenter;
import com.huihu.module_mine.answered.answeredinterface.IAnsweredView;
import com.huihu.module_mine.answered.entity.AnsweredInfo;
import com.huihu.module_mine.answered.model.ImpAnsweredModel;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.customize.HHShareDialog;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

public class ImpAnsweredPresenter implements IAnsweredPresenter {
    private final IAnsweredModel iAnsweredModel = new ImpAnsweredModel(this);
    private final IAnsweredView iAnsweredView;

    public ImpAnsweredPresenter(IAnsweredView iAnsweredView) {
        this.iAnsweredView = iAnsweredView;
    }

    @Override
    public void v2pGetAnseweredList(long fid) {
        iAnsweredModel.p2mGetAnseweredList(0, fid,false);
    }

    @Override
    public void v2pGetMoreAnsweredList(AnsweredInfo.PageDatasBean bean) {
        iAnsweredModel.p2mGetAnseweredList(bean.getEditTime(),bean.getUser().getUid(),true);
    }

    @Override
    public void m2pReturnAnsweredList(List<AnsweredInfo.PageDatasBean> mlist,boolean isMore) {
        if (mlist!=null&&mlist.size()>0){
            if (isMore){
                iAnsweredView.p2vGetMoreAnsweredListSuccess(mlist);
            }else {
                iAnsweredView.p2vGetAnsweredListSuccess(mlist);
            }
        }else {
            if (!isMore){
                iAnsweredView.p2vShowNoData();
            }
        }

    }

    @Override
    public void m2pNetFail() {

    }

    @Override
    public void m2pNetComplete() {
        iAnsweredView.p2vGetDataEnd();
    }

    @Override
    public void v2pJudgyAnswerItemClick(int viewId, AnsweredInfo.PageDatasBean bean) {
        SupportFragment fragment = null;
        if (viewId== R.id.rl_mine_answer){
            fragment = (SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.WRITE_FRAGMENT);
            Bundle bundle = new Bundle();
            bundle.putInt("questionId", bean.getQuestionId());
            bundle.putString("questionTitle", bean.getTitle());
            fragment.setArguments(bundle);

        }else if(viewId==R.id.iv_answer_bottom_three||viewId==R.id.tv_answer_bottom_three){
            iAnsweredView.p2vShowDialog();
        }else {
            if (bean.getIdeaType()==1){
                fragment = (SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.QUESTIONDETAIL_FRAGMENT);
            }else if(bean.getIdeaType()==2){
                fragment = (SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.ANSWERDETAIL_FRAGMENT);
            }
            Bundle bundle = new Bundle();
            bundle.putInt("ideaId", bean.getIdeaId());
            fragment.setArguments(bundle);
        }
        iAnsweredView.p2vHandleAnswerItemClickResult(fragment);
    }
}
