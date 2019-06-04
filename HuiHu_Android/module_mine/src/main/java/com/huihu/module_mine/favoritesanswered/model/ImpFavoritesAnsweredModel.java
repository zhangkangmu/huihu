package com.huihu.module_mine.favoritesanswered.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.favoritesanswered.entity.FavoritesAnsweredInfo;
import com.huihu.module_mine.favoritesanswered.entity.GetFavoritesAnsweredListSubcode;
import com.huihu.module_mine.favoritesanswered.favoritesansweredinterface.IFavoritesAnsweredModel;
import com.huihu.module_mine.favoritesanswered.favoritesansweredinterface.IFavoritesAnsweredPresenter;

import java.util.List;

public class ImpFavoritesAnsweredModel implements IFavoritesAnsweredModel {
    private final IFavoritesAnsweredPresenter iFavoritesAnsweredPresenter;
    public ImpFavoritesAnsweredModel(IFavoritesAnsweredPresenter iFavoritesAnsweredPresenter) {
        this.iFavoritesAnsweredPresenter = iFavoritesAnsweredPresenter;
    }

    @Override
    public void p2mGetAnsweredList(int ideaType, long lastTime, int pageSize, int uid, final boolean isMore) {
        getAnsweredList(ideaType,lastTime,pageSize,uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()) {
                    case GetFavoritesAnsweredListSubcode.Success:
                        FavoritesAnsweredInfo answeredInfo = new Gson().fromJson(returnModel.getBodyMessage(), FavoritesAnsweredInfo.class);
                        List<FavoritesAnsweredInfo.PageDatasBean> pageDatas = answeredInfo.getPageDatas();
                        iFavoritesAnsweredPresenter.m2pReturnAnsweredList(pageDatas, isMore);
                        break;
                    case GetFavoritesAnsweredListSubcode.BusinessError:
                    case GetFavoritesAnsweredListSubcode.ParameterError:
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iFavoritesAnsweredPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iFavoritesAnsweredPresenter.m2pNetComplete();
            }
        });
    }

    private void getAnsweredList(int ideaType,long lastTime,int pageSize, int uid, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Collection.getCollection, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("ideaType",""+ideaType);
        if (lastTime!=0) {
            //首页不传
            httpRequestParam.addQuery("lastTime", "" + lastTime);
        }
        httpRequestParam.addQuery("pageSize",String.valueOf(pageSize));
        httpRequestParam.addQuery("uid",""+284003);
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }

    @Override
    public void p2mRequestDeletedItem(long ideaId, int uid) {
        requestDeletedItem(ideaId, uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()) {
                    case GetFavoritesAnsweredListSubcode.DeletedSuccess:
                    iFavoritesAnsweredPresenter.m2pReturnDeletedItem();
                    break;
                    case GetFavoritesAnsweredListSubcode.DeletedError:
                    case GetFavoritesAnsweredListSubcode.DeletedBusinessError:
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iFavoritesAnsweredPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iFavoritesAnsweredPresenter.m2pNetComplete();
            }
        });
    }

    private void requestDeletedItem(long ideaId, int uid, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Collection.deleteCollection, NetworkTransmissionDefine.HttpMethod.DELETE);
        httpRequestParam.addQuery("ideaId",""+ideaId);
        httpRequestParam.addQuery("uid",""+uid);
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }
}
