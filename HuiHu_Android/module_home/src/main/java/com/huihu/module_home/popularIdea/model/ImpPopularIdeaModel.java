package com.huihu.module_home.popularIdea.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_home.popularIdea.enity.CancelFollowSubCode;
import com.huihu.module_home.popularIdea.enity.DeleteIdeaSubCode;
import com.huihu.module_home.popularIdea.enity.GetDataSubcodeType;
import com.huihu.module_home.popularIdea.enity.GetSwitchGrphSubCodeType;
import com.huihu.module_home.popularIdea.enity.PopularIdeaData;
import com.huihu.module_home.popularIdea.enity.PutGiveFollowsSubCode;
import com.huihu.module_home.popularIdea.enity.SwitchGrph;
import com.huihu.module_home.popularIdea.popularIdeainterface.IPopularIdeaModel;
import com.huihu.module_home.popularIdea.popularIdeainterface.IPopularIdeaPresenter;

import java.util.List;

public class ImpPopularIdeaModel implements IPopularIdeaModel {
    private final IPopularIdeaPresenter iPopularIdeaPresenter;
    private static final int PAGE_SIZE = 20;
    public ImpPopularIdeaModel(IPopularIdeaPresenter iPopularIdeaPresenter) {
        this.iPopularIdeaPresenter = iPopularIdeaPresenter;
    }

    @Override
    public void p2mGetPopularIdeaList(long time , final boolean isMore) {
        getPopularIdeaList(time,new HuihuCallBack(){
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetDataSubcodeType.Success:
                        PopularIdeaData popularIdeaData = new Gson().fromJson(returnModel.getBodyMessage(), PopularIdeaData.class);
                        List<PopularIdeaData.PageDatasBean> pageDatas = popularIdeaData.getPageDatas();
                        iPopularIdeaPresenter.m2pGetList(pageDatas,isMore);
                        break;
                    case GetDataSubcodeType.ParameterError:
                    case GetDataSubcodeType.BusinessError:
                    default:
                        break;
                }

            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iPopularIdeaPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iPopularIdeaPresenter.m2pNetComplete();
            }
        });
    }

    @Override
    public void p2mGetSwitchGrph(int type) {
        getSwitchGrph(type,new HuihuCallBack(){
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetSwitchGrphSubCodeType
                            .Success:
                        List<SwitchGrph> switchGrphs = new Gson().fromJson(returnModel.getBodyMessage(), new TypeToken<List<SwitchGrph>>() {
                        }.getType());
                        iPopularIdeaPresenter.m2pGetSwitchGrph(switchGrphs);
                        break;
                    case GetSwitchGrphSubCodeType.ParameterError:
                    case GetSwitchGrphSubCodeType.BusinessError:
                        default:
                            break;
                }

            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iPopularIdeaPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iPopularIdeaPresenter.m2pNetComplete();
            }
        });
    }

    @Override
    public void p2mDeleteIdea(long ideaId, final int position) {
        deleteIdea(ideaId,new HuihuCallBack(){
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case DeleteIdeaSubCode
                            .Success:
                    iPopularIdeaPresenter.m2pDeleteIdeaSuccessful(position);
                        break;
                    case DeleteIdeaSubCode.ParameterError:
                    case DeleteIdeaSubCode.BusinessError:
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

    @Override
    public void p2mCancelFollow(long ideaId, final int position) {
        cancelFollow(ideaId, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case CancelFollowSubCode
                            .Success:
                        iPopularIdeaPresenter.m2pCancelFollowSuccessful(position);
                        break;
                    case CancelFollowSubCode.ParameterError:
                    case CancelFollowSubCode.BusinessError:
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

    @Override
    public void p2mPutGiveFollows(final PopularIdeaData.PageDatasBean.UserInfoBean bean) {
        putGiveFollows(bean,new HuihuCallBack(){
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case PutGiveFollowsSubCode
                            .Success:
                        if (bean.getFollow()!=0) {
                            bean.setFollow(0);
                        }else {
                           bean.setFollow(1);
                        }
                     iPopularIdeaPresenter.m2pPutGiveFollows();
                        break;
                    case PutGiveFollowsSubCode.UnLogin:
                    case CancelFollowSubCode.ParameterError:
                    case CancelFollowSubCode.BusinessError:
                    default:
                     iPopularIdeaPresenter.m2pPutGiveFollowsError(returnModel.getSubCode());
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

    private void deleteIdea(long ideaId, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Ideas.DeleteIdea, NetworkTransmissionDefine.HttpMethod.DELETE);
        httpRequestParam.addQuery("ideaId", String.valueOf(ideaId));
        httpRequestParam.addQuery("uid", SPUtils.getInstance().getCurrentUid() + "");
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }

    private void cancelFollow(long ideaId, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Ideas.UnInterestedIdea, NetworkTransmissionDefine.HttpMethod.POST);
        httpRequestParam.addQuery("ideaId", String.valueOf(ideaId));
        httpRequestParam.addQuery("uid", SPUtils.getInstance().getCurrentUid() + "");
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }

    private void getPopularIdeaList(long lastTime,HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Ideas.GetPopularIdeaList, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("uid", SPUtils.getInstance().getCurrentUid() + "");
        httpRequestParam.addQuery("lastTime", String.valueOf(lastTime));
        httpRequestParam.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }

    private  void getSwitchGrph(int type,HuihuCallBack mCallBack){
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Ideas.GetSwitchGrph, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("grphType", ""+type);
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }
    private void putGiveFollows( PopularIdeaData.PageDatasBean.UserInfoBean bean,HuihuCallBack mCallBack){
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Follow.PutGiveFollows, NetworkTransmissionDefine.HttpMethod.PUT);
        httpRequestParam.addQuery("aboutId", String.valueOf(bean.getUid()));
        httpRequestParam.addQuery("followType", "1");
        if (bean.getFollow()!=0) {
            httpRequestParam.addQuery("state", "0");
        }else {
            httpRequestParam.addQuery("state","1");
        }
        httpRequestParam.addQuery("uid", ""+SPUtils.getInstance().getCurrentUid());
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }

}
