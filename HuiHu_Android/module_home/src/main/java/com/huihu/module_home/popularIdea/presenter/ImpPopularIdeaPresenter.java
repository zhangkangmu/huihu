package com.huihu.module_home.popularIdea.presenter;

import android.os.Bundle;
import android.view.View;

import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_home.R;
import com.huihu.module_home.answerdetail.view.fragment.AnswerDetailFragment;
import com.huihu.module_home.popularIdea.enity.ItemType;
import com.huihu.module_home.popularIdea.enity.PopularIdeaData;
import com.huihu.module_home.popularIdea.enity.SwitchGrph;
import com.huihu.module_home.popularIdea.model.ImpPopularIdeaModel;
import com.huihu.module_home.popularIdea.popularIdeainterface.IPopularIdeaModel;
import com.huihu.module_home.popularIdea.popularIdeainterface.IPopularIdeaPresenter;
import com.huihu.module_home.popularIdea.popularIdeainterface.IPopularIdeaView;
import com.huihu.module_home.questionandanswerlist.view.QuestionAndAnswerListFragment;
import com.huihu.module_home.writeanswer.view.WriteAnswerFragment;
import com.huihu.uilib.RouterReDefine;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

public class ImpPopularIdeaPresenter implements IPopularIdeaPresenter {
    private final IPopularIdeaModel iPopularIdeaModel=new ImpPopularIdeaModel(this);
    private final IPopularIdeaView iPopularIdeaView;


    public ImpPopularIdeaPresenter(IPopularIdeaView iPopularIdeaView) {
        this.iPopularIdeaView = iPopularIdeaView;
    }

    @Override
    public void v2pGetListFirst() {
        //开始
        iPopularIdeaModel.p2mGetPopularIdeaList(0,false);
    }

    @Override
    public void v2pLoadMoreList(PopularIdeaData.PageDatasBean endBean) {
        iPopularIdeaModel.p2mGetPopularIdeaList(endBean.getPopularTime(),true);
    }


    @Override
    public void m2pGetList(List<PopularIdeaData.PageDatasBean> mlist,boolean isMore) {
        if (mlist != null && mlist.size() > 0){
            if (isMore){
                iPopularIdeaView.p2vShowMorePopularIdeaList(mlist);
            } else {
                iPopularIdeaView.p2vGetPopularIdeaListSuccess(mlist);
            }
        } else {
            if (!isMore){
                iPopularIdeaView.p2vShowNoData();
            }
        }

    }

    @Override
    public void m2pGetSwitchGrph(List<SwitchGrph> switchGrphs) {
        iPopularIdeaView.p2vGetSwitchGrphSuccess(switchGrphs);
    }

    @Override
    public void m2pNetFail() {
        iPopularIdeaView.p2vGetPopularIdeaListFailed();
    }

    @Override
    public void m2pNetComplete() {
        iPopularIdeaView.p2vGetDataEnd();
    }

    @Override
    public void v2pDeleteIdea(long ideaId,int position) {
        iPopularIdeaModel.p2mDeleteIdea(ideaId,position);
    }

    @Override
    public void m2pDeleteIdeaSuccessful(int position) {
        iPopularIdeaView.p2vDeleteIdeaSuccessful(position);
    }

    @Override
    public void m2pCancelFollowSuccessful(int position) {
        iPopularIdeaView.p2vCanclFollowSuccessful(position);
    }

    @Override
    public void v2pCancelFollow(long ideaId,int position) {
        iPopularIdeaModel.p2mCancelFollow(ideaId,position);
    }

    @Override
    public void v2pPutGiveFollows(PopularIdeaData.PageDatasBean.UserInfoBean bean) {
        iPopularIdeaModel.p2mPutGiveFollows(bean);
    }

    @Override
    public void m2pPutGiveFollows() {
        iPopularIdeaView.p2vPutGiveFollows();
    }

    @Override
    public void m2pPutGiveFollowsError(String subCode) {
        iPopularIdeaView.p2vPutGiveFollowsError(subCode);
    }

    @Override
    public void v2pJudgyPopularItemClick(View view, int viewType, PopularIdeaData.PageDatasBean bean,int position) {
        int viewId=view.getId();
        switch (viewType) {
            case ItemType.question:
                if(viewId== R.id.iv_delete){
                    iPopularIdeaView.p2vShowPopuWindow(view,bean,position);
                }else if(viewId==R.id.share){
                    iPopularIdeaView.p2vShowDialog();
                }else if(viewId==R.id.rl_home_answer){
                    SupportFragment writeAnswerFragment = (SupportFragment) WriteAnswerFragment.newInstance(bean.getIdeaId(), bean.getTitle());
                    iPopularIdeaView.p2vHandlePopularItemClickResult(writeAnswerFragment,2);
                }else if(viewId==R.id.cons_question){
                    SupportFragment fragment=(SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.OTHERS_FRAGMENT);
                    Bundle bundle = new Bundle();
                    bundle.putLong("uid", bean.getUserInfo().getUid());
                    fragment.setArguments(bundle);
                    iPopularIdeaView.p2vHandlePopularItemClickResult(fragment,2);
                } else {
                    SupportFragment fragment = (SupportFragment)QuestionAndAnswerListFragment.newInstance(bean.getIdeaId());
                    iPopularIdeaView.p2vHandlePopularItemClickResult(fragment,2);
                }
                break;
            case ItemType.answer:
                if(viewId==R.id.iv_delete){
                    iPopularIdeaView.p2vShowPopuWindow(view,bean,position);
                }else if(viewId==R.id.share){
                    iPopularIdeaView.p2vShowDialog();
                }else if(viewId==R.id.tv_attention){
                    v2pPutGiveFollows(bean.getUserInfo());
                }else if (viewId==R.id.cons_answer){
                    SupportFragment fragment=(SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.OTHERS_FRAGMENT);
                    Bundle bundle = new Bundle();
                    bundle.putLong("uid", bean.getUserInfo().getUid());
                    fragment.setArguments(bundle);
                    iPopularIdeaView.p2vHandlePopularItemClickResult(fragment,2);
                } else {
                    SupportFragment fragment = (SupportFragment)AnswerDetailFragment.newInstance(bean.getIdeaId(), SPUtils.getInstance().getCurrentUid());
                    iPopularIdeaView.p2vHandlePopularItemClickResult(fragment,2);
                }
                break;
            case ItemType.discuss:
                if (view.getId()==R.id.cons_disscution){
                    SupportFragment fragment=(SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.OTHERS_FRAGMENT);
                    Bundle bundle = new Bundle();
                    bundle.putLong("uid", bean.getUserInfo().getUid());
                    fragment.setArguments(bundle);
                    iPopularIdeaView.p2vHandlePopularItemClickResult(fragment,2);
                }else if(view.getId()==R.id.iv_delete){
                    iPopularIdeaView.p2vShowPopuWindow(view,bean,position);
                }else if(view.getId()==R.id.share){
                   iPopularIdeaView.p2vShowDialog();
                }
                break;
            default:
                ToastUtil.show("还没处理的类型我懵逼了");
                break;
        }

    }

    @Override
    public void v2pGetSwitch(int type) {
        iPopularIdeaModel.p2mGetSwitchGrph(type);
    }

}
