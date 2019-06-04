package com.huihu.module_mine.community.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.community.communityinterface.ICommunityModel;
import com.huihu.module_mine.community.communityinterface.ICommunityPresenter;
import com.huihu.module_mine.community.entity.CommunityInfo;
import com.huihu.module_mine.community.entity.GetCommunityInfoSubCode;

import java.util.List;

public class ImpCommunityModel implements ICommunityModel {
    private final ICommunityPresenter iCommunityPresenter;
    private final int PAGE_SIZE=20;

    public ImpCommunityModel(ICommunityPresenter iCommunityPresenter) {
        this.iCommunityPresenter = iCommunityPresenter;
    }

    @Override
    public void p2mGetCommunityList(long time, long fid, final boolean isMore) {
        getCommunityList(time, fid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetCommunityInfoSubCode
                            .Success:
                        CommunityInfo answeredInfo = new Gson().fromJson(returnModel.getBodyMessage(), new TypeToken<CommunityInfo>() {
                        }.getType());
                        List<CommunityInfo.PageDatasBean> mlist= answeredInfo.getPageDatas();
                        iCommunityPresenter.m2pReturnCommunityList(mlist,isMore);
                        break;
                    case  GetCommunityInfoSubCode.ParameterError:
                    case  GetCommunityInfoSubCode.BusinessError:
                        default:
                            break;
                }

            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCommunityPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iCommunityPresenter.m2pNetComplete();
            }
        });
    }
    private void getCommunityList(long time , long fid, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Ideas.GetDiscussList, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("fid", "" + fid);
        if (time!=0) {
            //首页不传
            httpRequestParam.addQuery("lastTime",String.valueOf(time));
        }
        httpRequestParam.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        httpRequestParam.addQuery("uid", SPUtils.getInstance().getCurrentUid()+"");
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);

    }
}
