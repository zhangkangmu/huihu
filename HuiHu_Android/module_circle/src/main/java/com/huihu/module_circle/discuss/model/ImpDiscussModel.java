package com.huihu.module_circle.discuss.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_circle.discuss.discussinterface.IDiscussModel;
import com.huihu.module_circle.discuss.discussinterface.IDiscussPresenter;
import com.huihu.module_circle.discuss.entity.DeleteIdeaSubCode;
import com.huihu.module_circle.discuss.entity.GetRecemendDiscussSubCode;
import com.huihu.module_circle.discuss.entity.RecemendDiscussInfo;

public class ImpDiscussModel implements IDiscussModel {
    private final IDiscussPresenter iDiscussPresenter;
    private int PAGE_SIZE=20;

    public ImpDiscussModel(IDiscussPresenter iDiscussPresenter) {
        this.iDiscussPresenter = iDiscussPresenter;
    }

    @Override
    public void p2mGetRecemendDiscuss(long pageIndex, final boolean isMore) {
        getRecemendDiscuss(pageIndex,new HuihuCallBack(){
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetRecemendDiscussSubCode
                            .Success:
                        RecemendDiscussInfo recemendDiscussInfo = new Gson().fromJson(returnModel.getBodyMessage(), RecemendDiscussInfo.class);
                        iDiscussPresenter.m2pReturnRecemendDiscuss(recemendDiscussInfo,isMore);
                        break;
                    case GetRecemendDiscussSubCode.BusinessError:
                    case GetRecemendDiscussSubCode.ParameterError:
                        default:
                        break;
                }

            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iDiscussPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iDiscussPresenter.m2pNetComplete();
            }
        });
    }

    @Override
    public void p2mDeleteRecommendDiscuss(RecemendDiscussInfo.PageDatasBean pageDatasBean, final int position) {
        //删除
        deleteIdea(pageDatasBean.getIdeaId(), new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case DeleteIdeaSubCode
                            .Success:
                    iDiscussPresenter.m2pDeleteIdeaSuccessful(position);
                        break;
                    case DeleteIdeaSubCode.ParameterError:
                    case DeleteIdeaSubCode.BusinessError:
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iDiscussPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iDiscussPresenter.m2pNetComplete();
            }
        });
    }
    //不感兴趣
    private void deleteIdea(long ideaId, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Ideas.UnInterestedIdea, NetworkTransmissionDefine.HttpMethod.POST);
        httpRequestParam.addQuery("ideaId", String.valueOf(ideaId));
        httpRequestParam.addQuery("uid", SPUtils.getInstance().getCurrentUid() + "");
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }

    private void getRecemendDiscuss(long pageIndex, HuihuCallBack mhuihuCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Circle.GetRecomendList, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("pageIndex",String.valueOf(pageIndex));
        httpRequestParam.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        httpRequestParam.addQuery("uid",String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(httpRequestParam, mhuihuCallBack);
    }
}
