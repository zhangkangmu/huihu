package com.huihu.module_mine.attitude.attitudeinterface;


import com.huihu.module_mine.attitude.entity.AttitudeInfo;

import java.util.List;

public interface IAttitudeView {
void p2vReturnAttitudeListSuccess(List<AttitudeInfo.PageDatasBean> mList);
void p2vReturnMoreAttitudeListSuccess(List<AttitudeInfo.PageDatasBean> mList);
void p2vShowNoData();
void p2vGetDataEnd();
}
