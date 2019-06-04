package com.huihu.module_circle.mydiscuss.presenter;

import android.util.Log;

import com.huihu.module_circle.mydiscuss.entity.MyDiscussInfo;
import com.huihu.module_circle.mydiscuss.mydiscussinterface.IMyDiscussModel;
import com.huihu.module_circle.mydiscuss.mydiscussinterface.IMyDiscussPresenter;
import com.huihu.module_circle.mydiscuss.mydiscussinterface.IMyDiscussView;
import com.huihu.module_circle.mydiscuss.model.ImpMyDiscussModel;

import java.util.List;

public class ImpMyDiscussPresenter implements IMyDiscussPresenter {
    private final IMyDiscussModel iMyDiscussModel = new ImpMyDiscussModel(this);
    private final IMyDiscussView iMyDiscussView;

    public ImpMyDiscussPresenter(IMyDiscussView iMyDiscussView) {
        this.iMyDiscussView = iMyDiscussView;
    }

    @Override
    public void v2pRequestMyDiscuss(long circleId, long lastTime, int pageSize, long uid) {
        iMyDiscussModel.p2mRequestMyDiscuss(circleId,lastTime,pageSize,uid,false);
    }

    @Override
    public void m2pReturnMyDiscuss(List<MyDiscussInfo.PageDatasBean> datas, boolean isMore) {
        if (datas!=null && datas.size()>0){
            if (isMore) {
                iMyDiscussView.p2vReturnMoreCircleDiscussList(datas);
            }else{
                iMyDiscussView.p2vReturnCircleDiscussList(datas);
            }
        }else{
            if (!isMore){
                iMyDiscussView.p2vShowNoData();
            }
        }
    }

    @Override
    public void m2pNetFail() {
        iMyDiscussView.p2vNetFail();
    }

    @Override
    public void m2pNetComplete() {
        iMyDiscussView.p2vNetComplete();
    }

    @Override
    public void v2pRequestMoreMyDiscuss(long circleId, long lastTime, int pageSize, long uid) {
        iMyDiscussModel.p2mRequestMyDiscuss(circleId,lastTime,pageSize,uid,true);
    }
}
