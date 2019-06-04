package com.huihu.module_circle.newcirclediscuss.presenter;

import android.util.Log;

import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_circle.newcirclediscuss.entity.NewCircleDiscussInfo;
import com.huihu.module_circle.newcirclediscuss.newcirclediscussinterface.INewCircleDiscussModel;
import com.huihu.module_circle.newcirclediscuss.newcirclediscussinterface.INewCircleDiscussPresenter;
import com.huihu.module_circle.newcirclediscuss.newcirclediscussinterface.INewCircleDiscussView;
import com.huihu.module_circle.newcirclediscuss.model.ImpNewCircleDiscussModel;

import java.util.List;

public class ImpNewCircleDiscussPresenter implements INewCircleDiscussPresenter {
    private final INewCircleDiscussModel iNewCircleDiscussModel = new ImpNewCircleDiscussModel(this);
    private final INewCircleDiscussView iNewCircleDiscussView;

    public ImpNewCircleDiscussPresenter(INewCircleDiscussView iNewCircleDiscussView) {
        this.iNewCircleDiscussView = iNewCircleDiscussView;
    }

    @Override
    public void v2pRequestCircleDiscuss(long circleId , long lastTime, int pageSize, long uid ,boolean isMore) {
        iNewCircleDiscussModel.m2pRequestCircleDiscuss(circleId,lastTime,pageSize,uid,isMore);
    }

    @Override
    public void m2pReturnCircleDiscussList(List<NewCircleDiscussInfo.PageDatasBean> datas, boolean isMore) {
        if (datas!=null && datas.size()>0){
            if (isMore) {
                iNewCircleDiscussView.p2vReturnMoreCircleDiscussList(datas);
            }else{
                iNewCircleDiscussView.p2vReturnCircleDiscussList(datas);
            }
        }else{
            if (!isMore){
                iNewCircleDiscussView.p2vShowNoData();
            }
        }
    }

    @Override
    public void m2pNetFail() {
        iNewCircleDiscussView.p2vNetFail();
    }

    @Override
    public void m2pNetComplete() {
        iNewCircleDiscussView.p2vNetComplete();
    }

    @Override
    public void v2pRequestMoreCircleDiscuss(long circleId , long lastTime, int pageSize, long uid ,boolean isMore) {
        iNewCircleDiscussModel.m2pRequestCircleDiscuss(circleId,lastTime,pageSize, uid,isMore);
    }

    @Override
    public void v2pPutGiveFollows(NewCircleDiscussInfo.PageDatasBean.UserInfoBean userInfo) {
        iNewCircleDiscussModel.p2mPutGiveFollows(userInfo);
    }

    @Override
    public void m2pReturnSuccesAttention() {
        iNewCircleDiscussView.p2vReturnSuccesAttention();
    }

    @Override
    public void m2pPutGiveFollowsError(String subCode) {
        iNewCircleDiscussView.p2vPutGiveFollowsError(subCode);
    }
}
