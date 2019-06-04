package com.huihu.module_circle.discuss.presenter;

import com.huihu.module_circle.discuss.discussinterface.IDiscussModel;
import com.huihu.module_circle.discuss.discussinterface.IDiscussPresenter;
import com.huihu.module_circle.discuss.discussinterface.IDiscussView;
import com.huihu.module_circle.discuss.entity.RecemendDiscussInfo;
import com.huihu.module_circle.discuss.model.ImpDiscussModel;

import java.util.List;

public class ImpDiscussPresenter implements IDiscussPresenter {
    private final IDiscussModel iDiscussModel = new ImpDiscussModel(this);
    private final IDiscussView iDiscussView;

    public ImpDiscussPresenter(IDiscussView iDiscussView) {
        this.iDiscussView = iDiscussView;
    }

    @Override
    public void v2pGetRecemendDiscuss(long index) {
        if (index!=1) {
            iDiscussModel.p2mGetRecemendDiscuss(index, true);
        }else {
            iDiscussModel.p2mGetRecemendDiscuss(index, false);
        }

    }

    @Override
    public void m2pNetFail() {

    }

    @Override
    public void m2pNetComplete() {
        iDiscussView.p2vGetDataEnd();
    }

    @Override
    public void m2pReturnRecemendDiscuss(RecemendDiscussInfo recemendDiscussInfo,boolean isMore) {
        iDiscussView.p2vReturnRecemendDiscuss(recemendDiscussInfo,isMore);
    }

    @Override
    public void v2pDeleteRecommendDiscuss(RecemendDiscussInfo.PageDatasBean pageDatasBean, int position) {
        iDiscussModel.p2mDeleteRecommendDiscuss(pageDatasBean,position);
    }

    @Override
    public void m2pDeleteIdeaSuccessful(int position) {
        iDiscussView.p2vDeleteIdeaSuccessful(position);
    }

}
