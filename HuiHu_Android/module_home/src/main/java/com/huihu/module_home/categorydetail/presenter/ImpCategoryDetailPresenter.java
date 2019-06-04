package com.huihu.module_home.categorydetail.presenter;

import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_home.categorydetail.categorydetailinterface.ICategoryDetailModel;
import com.huihu.module_home.categorydetail.categorydetailinterface.ICategoryDetailPresenter;
import com.huihu.module_home.categorydetail.categorydetailinterface.ICategoryDetailView;
import com.huihu.module_home.categorydetail.entity.CategoryInfoBean;
import com.huihu.module_home.categorydetail.entity.CategoryPageBean;
import com.huihu.module_home.categorydetail.entity.PageDatasBean;
import com.huihu.module_home.categorydetail.model.ImpCategoryDetailModel;

public class ImpCategoryDetailPresenter implements ICategoryDetailPresenter {
    private final ICategoryDetailModel iCategoryDetailModel = new ImpCategoryDetailModel(this);
    private final ICategoryDetailView iCategoryDetailView;

    public ImpCategoryDetailPresenter(ICategoryDetailView iCategoryDetailView) {
        this.iCategoryDetailView = iCategoryDetailView;
    }


    @Override
    public void v2pGetFirstList(int categoryId, int order) {
        iCategoryDetailModel.p2mGetAnswerListNet(categoryId, order, false, null);
    }

    @Override
    public void v2pGetMoreList(PageDatasBean bean, int categoryId, int order) {
        iCategoryDetailModel.p2mGetAnswerListNet(categoryId, order, true, bean);
    }

    @Override
    public void v2pGetInfo(int categoryId) {
        iCategoryDetailModel.p2mGetCategoryInfoNet(categoryId);
    }

    @Override
    public void v2pPutGiveFollows(PageDatasBean bean) {
        iCategoryDetailModel.p2mPutGiveFollowsNet(bean);
    }

    @Override
    public void v2pPutGiveFollows(CategoryInfoBean bean) {
        iCategoryDetailModel.p2mPutGiveFollowsNet(bean);
    }

    @Override
    public void v2pPutFollowSuccess(PageDatasBean bean) {
        iCategoryDetailView.p2vChangeFollowState(bean);
    }

    @Override
    public void v2pPutFollowSuccess(CategoryInfoBean bean) {
        iCategoryDetailView.p2vChangeFollowState(bean);
    }

    @Override
    public void m2pGetListSuccess(CategoryPageBean bean, boolean isMore) {
        if (bean.getPageDatas() != null && bean.getPageDatas().size() > 0 ){
            if (isMore){
                iCategoryDetailView.p2vShowMoreList(bean.getPageDatas());
            } else {
                iCategoryDetailView.p2vShowFirstList(bean.getPageDatas());
            }
        } else {
            if (isMore){

            } else {
                iCategoryDetailView.p2vShowNoData();
            }
        }
    }

    @Override
    public void m2pGetInfoSuccess(CategoryInfoBean bean) {
        iCategoryDetailView.p2vShowInfo(bean);
    }

    @Override
    public void m2pGetListComplete() {
        iCategoryDetailView.p2vGetListComplete();
    }

    @Override
    public void m2pNetFail() {
        ToastUtil.show(com.huihu.uilib.R.string.uilib_net_fail_tip);
    }

    @Override
    public void m2pGetListDataFail() {
        iCategoryDetailView.p2vShowNetError();
    }
}
