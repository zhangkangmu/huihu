package com.huihu.module_mine.attitude.attitudeinterface;

import com.huihu.module_mine.attitude.entity.AttitudeInfo;

import java.util.List;

public interface IAttitudePresenter {
 void v2pGetAttitudeList(long fid);
 void v2pGetMoreAttitudeList(AttitudeInfo.PageDatasBean bean,long fid);
 void m2pReturnAttitudeList(List<AttitudeInfo.PageDatasBean> mList,boolean isMore);
 void m2pNetFail();
 void m2pNetComplete();
}
