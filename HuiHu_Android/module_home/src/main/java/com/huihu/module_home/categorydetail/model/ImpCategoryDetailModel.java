package com.huihu.module_home.categorydetail.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_home.categorydetail.categorydetailinterface.ICategoryDetailModel;
import com.huihu.module_home.categorydetail.categorydetailinterface.ICategoryDetailPresenter;
import com.huihu.module_home.categorydetail.entity.CategoryInfoBean;
import com.huihu.module_home.categorydetail.entity.CategoryInfoSubcode;
import com.huihu.module_home.categorydetail.entity.CategoryListSubcode;
import com.huihu.module_home.categorydetail.entity.CategoryPageBean;
import com.huihu.module_home.categorydetail.entity.PageDatasBean;
import com.huihu.uilib.def.FollowType;
import com.huihu.uilib.subcode.PutGiveFollowsSubcode;

import static com.huihu.uilib.def.NetDataBoolean.NET_FALSE;
import static com.huihu.uilib.def.NetDataBoolean.NET_TRUE;

public class ImpCategoryDetailModel implements ICategoryDetailModel {

    private static final int PAGE_SIZE = 20;

    private final ICategoryDetailPresenter iCategoryDetailPresenter;

    public ImpCategoryDetailModel(ICategoryDetailPresenter iCategoryDetailPresenter) {
        this.iCategoryDetailPresenter = iCategoryDetailPresenter;
    }

    @Override
    public void p2mGetAnswerListNet(int categoryId, int order, final boolean isMore, final PageDatasBean bean) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Categorys.GetAnswer
                , NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("categoryId", String.valueOf(categoryId));
        param.addQuery("orderBy", String.valueOf(order));
        param.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        param.addQuery("uid", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        if (bean != null){
            param.addQuery("agreeCount", String.valueOf(bean.getAgreeCount()));
            param.addQuery("lastTime", String.valueOf(bean.getEditTime()));
        }
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                String subcode = returnModel.getSubCode();
                switch (subcode){
                    case CategoryListSubcode.Success:
                        CategoryPageBean pageBean = new Gson().fromJson(returnModel.getBodyMessage()
                                , CategoryPageBean.class);
                        iCategoryDetailPresenter.m2pGetListSuccess(pageBean, isMore);
                        break;
                    case CategoryListSubcode.ParameterError:
                        break;
                    case CategoryListSubcode.BusinessError:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCategoryDetailPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iCategoryDetailPresenter.m2pGetListComplete();
            }
        });
    }

    @Override
    public void p2mGetCategoryInfoNet(int categoryId) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Categorys.GetCategoryInfo
                , NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("categoryId", String.valueOf(categoryId));
        param.addQuery("uid", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                String subcode = returnModel.getSubCode();
                switch (subcode){
                    case CategoryInfoSubcode.Success:
                        CategoryInfoBean bean = new Gson().fromJson(returnModel.getBodyMessage()
                                , CategoryInfoBean.class);
                        iCategoryDetailPresenter.m2pGetInfoSuccess(bean);
                        break;
                    case CategoryInfoSubcode.ParameterError:
                        break;
                    case CategoryInfoSubcode.BusinessError:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCategoryDetailPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mPutGiveFollowsNet(final PageDatasBean bean) {
        final int state = bean.getUserInfo().getFollow() == NET_FALSE ? NET_TRUE : NET_FALSE;
        requestPutFollow(state, bean.getUserInfo().getUid(), FollowType.HUMAN, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case PutGiveFollowsSubcode.Success:
                        bean.getUserInfo().setFollow(state);
                        iCategoryDetailPresenter.v2pPutFollowSuccess(bean);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCategoryDetailPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mPutGiveFollowsNet(final CategoryInfoBean bean) {
        final int state = bean.getFollow() == NET_FALSE ? NET_TRUE : NET_FALSE;
        requestPutFollow(state, bean.getCategoryId(), FollowType.CATEGORY, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case PutGiveFollowsSubcode.Success:
                        bean.setFollow(state);
                        if (state == NET_TRUE){
                            bean.setFollowPeopleNum(bean.getFollowPeopleNum() + 1);
                        } else {
                            bean.setFollowPeopleNum(bean.getFollowPeopleNum() - 1);
                        }
                        iCategoryDetailPresenter.v2pPutFollowSuccess(bean);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }


    private void requestPutFollow(int state, long aboutId, int followType, HuihuCallBack callBack){
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Follow.PutGiveFollows
                , NetworkTransmissionDefine.HttpMethod.PUT);
        param.addQuery("aboutId", String.valueOf(aboutId));
        param.addQuery("followType", String.valueOf(followType));
        param.addQuery("state", String.valueOf(state));
        param.addQuery("uid", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(param, callBack);
    }

}
