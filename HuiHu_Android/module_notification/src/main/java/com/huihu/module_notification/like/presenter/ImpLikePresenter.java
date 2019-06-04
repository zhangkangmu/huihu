package com.huihu.module_notification.like.presenter;

import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_notification.R;
import com.huihu.module_notification.like.entity.LikeMePageBean;
import com.huihu.module_notification.like.likeinterface.ILikeModel;
import com.huihu.module_notification.like.likeinterface.ILikePresenter;
import com.huihu.module_notification.like.likeinterface.ILikeView;
import com.huihu.module_notification.like.model.ImpLikeModel;

public class ImpLikePresenter implements ILikePresenter {
    private final ILikeModel iLikeModel = new ImpLikeModel(this);
    private final ILikeView iLikeView;
    private boolean hasMore = true;

    public ImpLikePresenter(ILikeView iLikeView) {
        this.iLikeView = iLikeView;
    }

    @Override
    public void v2pGetFirstData() {
        hasMore = true;
        iLikeModel.p2mGetLikeMeListNet(0, false);
    }

    @Override
    public void v2pGetMoreData(LikeMePageBean.PageDatasBean bean) {
        if (hasMore){
            iLikeModel.p2mGetLikeMeListNet(bean.getCreateTime(), true);
        } else {
            ToastUtil.show(R.string.module_notification_has_no_more);
            iLikeView.p2vGetDataComplete();
        }
    }

    @Override
    public void m2pGetDataSuccess(LikeMePageBean bean, boolean isMore) {
        if (bean.getPageDatas() != null && bean.getPageDatas().size() > 0){
            if (isMore){
                iLikeView.p2vShowMoreData(bean.getPageDatas());
            } else {
                iLikeView.p2vShowFirstData(bean.getPageDatas());
            }
        } else {
            if (isMore){
                hasMore = false;
                ToastUtil.show(R.string.module_notification_has_no_more);
            } else {
                iLikeView.p2vShowNoData();
            }
        }
    }

    @Override
    public void m2pNetFail() {

    }

    @Override
    public void m2pGetDataComplete() {
        iLikeView.p2vGetDataComplete();
    }

    @Override
    public void m2pGetDataFail() {
        iLikeView.p2vShowGetDataFail();
    }

    @Override
    public void v2pLookOtherPeople(LikeMePageBean.PageDatasBean bean) {
        iLikeView.p2vStartOtherPeople(bean.getViewpointUserInfo().getUid());
    }
}
