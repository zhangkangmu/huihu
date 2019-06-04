package com.huihu.module_mine.attentioncircle.presenter;

import android.util.Log;

import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.attentioncircle.attentioncircleinterface.IAttentionCircleModel;
import com.huihu.module_mine.attentioncircle.attentioncircleinterface.IAttentionCirclePresenter;
import com.huihu.module_mine.attentioncircle.attentioncircleinterface.IAttentionCircleView;
import com.huihu.module_mine.attentioncircle.entity.CircleAttentionInfo;
import com.huihu.module_mine.attentioncircle.model.ImpAttentionCircleModel;

import java.util.List;

public class ImpAttentionCirclePresenter implements IAttentionCirclePresenter {
    private final IAttentionCircleModel iAttentionCircleModel = new ImpAttentionCircleModel(this);
    private final IAttentionCircleView iAttentionCircleView;

    public ImpAttentionCirclePresenter(IAttentionCircleView iAttentionCircleView) {
        this.iAttentionCircleView = iAttentionCircleView;
    }

    @Override
    public void v2pGetAttentionCircleList() {
        iAttentionCircleModel.p2mGetAttentionCircleList(0,0,20, SPUtils.getInstance().getCurrentUid(),false);
    }
    @Override
    public void v2pGetMoreCircleList(CircleAttentionInfo.PageDatasBean bean) {
        iAttentionCircleModel.p2mGetAttentionCircleList(bean.getFollowId(), 0, 20, SPUtils.getInstance().getCurrentUid(), true);
    }
    @Override
    public void m2pGetAttentionCircle(List<CircleAttentionInfo.PageDatasBean> pageDatas,boolean isMore) {
        if (pageDatas!=null && pageDatas.size()>0){
            if (isMore){
                iAttentionCircleView.p2vReturnMoreCircleListSuccess(pageDatas);

            }else {
                iAttentionCircleView.p2vGetAttentionCircleListSucces(pageDatas);
            }

        }else{
            if (!isMore){
            iAttentionCircleView.p2vShowNoData();
            }
        }

    }

    @Override
    public void m2pNetFail() {
        iAttentionCircleView.p2vNetFail();
    }

    @Override
    public void m2pNetComplete() {
        iAttentionCircleView.p2vNetComplete();
    }

}
