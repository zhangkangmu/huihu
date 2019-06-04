package com.huihu.module_mine.answered.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.answered.answeredinterface.IAnsweredModel;
import com.huihu.module_mine.answered.answeredinterface.IAnsweredPresenter;
import com.huihu.module_mine.answered.entity.AnsweredInfo;
import com.huihu.module_mine.answered.entity.GetAnsweredInfoSubCode;

import java.util.List;

public class ImpAnsweredModel implements IAnsweredModel {
    private final IAnsweredPresenter iansweredPresenter;
    private final int PAGE_SIZE=20;
    public ImpAnsweredModel(IAnsweredPresenter iansweredPresenter) {
        this.iansweredPresenter = iansweredPresenter;
    }

    @Override
    public void p2mGetAnseweredList(long time, long fid, final boolean isMore) {
        getAnsweredList(time,fid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetAnsweredInfoSubCode.Success:
                        AnsweredInfo answeredInfo = new Gson().fromJson(returnModel.getBodyMessage(), new TypeToken<AnsweredInfo>() {
                        }.getType());
                        List<AnsweredInfo.PageDatasBean> mlist= answeredInfo.getPageDatas();
                        iansweredPresenter.m2pReturnAnsweredList(mlist,isMore);
                        break;
                    case GetAnsweredInfoSubCode.BusinessError:
                    case GetAnsweredInfoSubCode.ParameterError:
                        default:
                            break;

                }

            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iansweredPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iansweredPresenter.m2pNetComplete();
            }
        });
    }
    private void getAnsweredList(long lastTime, long fid, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Ideas.GetIdeaList, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("fid", "" + fid);
        if (lastTime!=0) {
            //首页不传
            httpRequestParam.addQuery("lastTime", "" + lastTime);
        }
        httpRequestParam.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        httpRequestParam.addQuery("uid", SPUtils.getInstance().getCurrentUid()+"");
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);

    }
}
