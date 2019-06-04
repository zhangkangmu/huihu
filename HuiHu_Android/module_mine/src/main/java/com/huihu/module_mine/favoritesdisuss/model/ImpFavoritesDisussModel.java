package com.huihu.module_mine.favoritesdisuss.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_mine.favoritesanswered.entity.GetFavoritesAnsweredListSubcode;
import com.huihu.module_mine.favoritesdisuss.entity.FavoritesDiscussInfo;
import com.huihu.module_mine.favoritesdisuss.favoritesdisussinterface.IFavoritesDisussModel;
import com.huihu.module_mine.favoritesdisuss.favoritesdisussinterface.IFavoritesDisussPresenter;

import java.util.List;

public class ImpFavoritesDisussModel implements IFavoritesDisussModel {
    private final IFavoritesDisussPresenter iFavoritesDisussPresenter;

    public ImpFavoritesDisussModel(IFavoritesDisussPresenter iFavoritesDisussPresenter) {
        this.iFavoritesDisussPresenter = iFavoritesDisussPresenter;
    }

    @Override
    public void p2mGetDiscussList(int ideaType, long lastTime, int pageSize, int uid, final boolean isMore) {
        getDiscussList(ideaType,lastTime,pageSize,uid,isMore, new HuihuCallBack() {

            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()) {
                    case GetFavoritesAnsweredListSubcode.Success:
                        FavoritesDiscussInfo info = new Gson().fromJson(returnModel.getBodyMessage(), FavoritesDiscussInfo.class);
                        List<FavoritesDiscussInfo.PageDatasBean> datas = info.getPageDatas();
                        iFavoritesDisussPresenter.m2pReturnDiscussList(datas, isMore);
                        break;
                    case GetFavoritesAnsweredListSubcode.BusinessError:
                        break;
                    case GetFavoritesAnsweredListSubcode.ParameterError:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iFavoritesDisussPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iFavoritesDisussPresenter.m2pNetComplete();
            }
        });
    }

    @Override
    public void p2mReturnDeletedItem(long ideaId, int uid) {
        requestDeletedItem(ideaId, uid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                iFavoritesDisussPresenter.m2pReturnDeletedItem();
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iFavoritesDisussPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iFavoritesDisussPresenter.m2pNetComplete();
            }
        });
    }

    private void requestDeletedItem(long ideaId, int uid, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Collection.deleteCollection, NetworkTransmissionDefine.HttpMethod.DELETE);
        httpRequestParam.addQuery("ideaId",""+ideaId);
        httpRequestParam.addQuery("uid",""+uid);
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }

    private void getDiscussList(int ideaType, long lastTime,int pageSize, int uid, boolean isMore, HuihuCallBack huihuCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Collection.getCollection, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("ideaType",""+ideaType);
        if (lastTime!=0) {
            //首页不传
            httpRequestParam.addQuery("lastTime", "" + lastTime);
        }
        httpRequestParam.addQuery("pageSize",""+pageSize);
        httpRequestParam.addQuery("uid",""+uid);
        HuihuHttpUtils.httpRequest(httpRequestParam, huihuCallBack);
    }
}
