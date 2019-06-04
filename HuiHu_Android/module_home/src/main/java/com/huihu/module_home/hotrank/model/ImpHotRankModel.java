package com.huihu.module_home.hotrank.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_home.hotrank.entity.GetHotRankSubCode;
import com.huihu.module_home.hotrank.entity.HotRankBean;
import com.huihu.module_home.hotrank.hotrankinterface.IHotRankModel;
import com.huihu.module_home.hotrank.hotrankinterface.IHotRankPresenter;
import com.huihu.module_home.popularIdea.enity.GetSwitchGrphSubCodeType;
import com.huihu.module_home.popularIdea.enity.SwitchGrph;

import java.util.List;

public class ImpHotRankModel implements IHotRankModel {
    private final IHotRankPresenter iHotRankPresenter;
    private final int PAGE_SIZE=20;

    public ImpHotRankModel(IHotRankPresenter iHotRankPresenter) {
        this.iHotRankPresenter = iHotRankPresenter;
    }

    @Override
    public void p2mGetHotRank(int broswes, long time, final boolean isMore) {
        getHotRankList(broswes,time, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetHotRankSubCode.Success:
                        HotRankBean hotRankBean = new Gson().fromJson(returnModel.getBodyMessage(), HotRankBean.class);
                        List<HotRankBean.PageDatasBean> pageDatas = hotRankBean.getPageDatas();
                        iHotRankPresenter.m2pGetHotRank(pageDatas,isMore);
                        break;
                    case GetHotRankSubCode.ParameterError:
                    case GetHotRankSubCode.BusinessError:
                        default:
                        break;
                }

            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iHotRankPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iHotRankPresenter.m2pNetComplete();
            }
        });

    }

    @Override
    public void p2mGetSwitch(int type) {
        getSwitchGrph(type, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetSwitchGrphSubCodeType
                            .Success:
                        List<SwitchGrph> switchGrphs = new Gson().fromJson(returnModel.getBodyMessage(), new TypeToken<List<SwitchGrph>>() {
                        }.getType());
                        iHotRankPresenter.m2pGetSwitchGrph(switchGrphs);
                        break;
                    case GetSwitchGrphSubCodeType.ParameterError:
                    case GetSwitchGrphSubCodeType.BusinessError:
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

    private void getHotRankList(long browses,long time, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Ideas.GetHoldIdea, NetworkTransmissionDefine.HttpMethod.GET);
        if (browses!=0) {
            httpRequestParam.addQuery("browses", String.valueOf(browses));
            httpRequestParam.addQuery("lastTime", String.valueOf(time));
        }
        httpRequestParam.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }
    private  void getSwitchGrph(int type,HuihuCallBack mCallBack){
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Ideas.GetSwitchGrph, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("grphType", ""+type);
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }
}
