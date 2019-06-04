package com.huihu.module_mine.attitude.presenter;

import com.huihu.module_mine.attitude.attitudeinterface.IAttitudeModel;
import com.huihu.module_mine.attitude.attitudeinterface.IAttitudePresenter;
import com.huihu.module_mine.attitude.attitudeinterface.IAttitudeView;
import com.huihu.module_mine.attitude.entity.AttitudeInfo;
import com.huihu.module_mine.attitude.model.ImpAttitudeModel;

import java.util.List;

public class ImpAttitudePresenter implements IAttitudePresenter {
    private final IAttitudeModel iAttitudeModel = new ImpAttitudeModel(this);
    private final IAttitudeView iAttitudeView;

    public ImpAttitudePresenter(IAttitudeView iAttitudeView) {
        this.iAttitudeView = iAttitudeView;
    }

    @Override
    public void v2pGetAttitudeList(long fid) {
        iAttitudeModel.p2mGetAttitudeList(0,fid,false);
    }

    @Override
    public void v2pGetMoreAttitudeList(AttitudeInfo.PageDatasBean bean,long fid) {
        iAttitudeModel.p2mGetAttitudeList(bean.getCreateTime(),fid,true);
    }

    @Override
    public void m2pReturnAttitudeList(List<AttitudeInfo.PageDatasBean> mList,boolean isMore) {
        if (mList!=null&&mList.size()>0){
            if (isMore){
                iAttitudeView.p2vReturnMoreAttitudeListSuccess(mList);
            }else {
                iAttitudeView.p2vReturnAttitudeListSuccess(mList);
            }
        }else {
            if (!isMore){
                iAttitudeView.p2vShowNoData();
            }
        }

    }

    @Override
    public void m2pNetFail() {

    }

    @Override
    public void m2pNetComplete() {
        iAttitudeView.p2vGetDataEnd();
    }
}
