package com.huihu.module_circle.newcirclerecommend.presenter;

import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_circle.newcirclediscuss.entity.NewCircleDiscussInfo;
import com.huihu.module_circle.newcirclerecommend.entity.NewCircleRecommendInfo;
import com.huihu.module_circle.newcirclerecommend.newcirclerecommendinterface.INewCircleRecommendModel;
import com.huihu.module_circle.newcirclerecommend.newcirclerecommendinterface.INewCircleRecommendPresenter;
import com.huihu.module_circle.newcirclerecommend.newcirclerecommendinterface.INewCircleRecommendView;
import com.huihu.module_circle.newcirclerecommend.model.ImpNewCircleRecommendModel;

import java.util.List;

public class ImpNewCircleRecommendPresenter implements INewCircleRecommendPresenter {
    private final INewCircleRecommendModel iNewCircleRecommendModel = new ImpNewCircleRecommendModel(this);
    private final INewCircleRecommendView iNewCircleRecommendView;

    public ImpNewCircleRecommendPresenter(INewCircleRecommendView iNewCircleRecommendView) {
        this.iNewCircleRecommendView = iNewCircleRecommendView;
    }

    @Override
    public void v2pRequestCircleRecommendList() {
        iNewCircleRecommendModel.p2mRequestCircleRecommendList(1,20, SPUtils.getInstance().getCurrentUid(),false);
    }

//    @Override
//    public void m2pReturnNewCircleRecommendList(List<NewCircleRecommendInfo.PageDatasBean> datas, boolean isMore) {
//        if (datas!=null && datas.size()>0){
//            if (isMore) {
//                iNewCircleRecommendView.p2vReturnMoreNewCircleRecommendList(datas);
//            }else{
//                iNewCircleRecommendView.p2vReturnNewCircleRecommendList(datas);
//            }
//        }else{
//            if (!isMore){
//                iNewCircleRecommendView.p2vShowNoData();
//            }
//        }
//    }
@Override
public void m2pReturnNewCircleRecommendList(NewCircleRecommendInfo info, boolean isMore) {
    if (info!=null && info.getPageDatas().size()>0){
        if (isMore) {
            iNewCircleRecommendView.p2vReturnMoreNewCircleRecommendList(info);
        }else{
            iNewCircleRecommendView.p2vReturnNewCircleRecommendList(info);
        }
    }else{
        if (!isMore){
            iNewCircleRecommendView.p2vShowNoData();
        }
    }
}

    @Override
    public void m2pNetFail() {
        iNewCircleRecommendView.p2vNetFail();
    }

    @Override
    public void m2pNetComplete() {
        iNewCircleRecommendView.p2vNetComplete();
    }

    @Override
    public void v2pRequestMoreCircleRecommendList(NewCircleRecommendInfo info) {
        iNewCircleRecommendModel.p2mRequestCircleRecommendList(info.getPageIndex()+1,20,SPUtils.getInstance().getCurrentUid(),true);
    }
}
