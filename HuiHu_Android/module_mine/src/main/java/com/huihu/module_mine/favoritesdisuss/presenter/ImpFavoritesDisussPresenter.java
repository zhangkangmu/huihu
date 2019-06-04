package com.huihu.module_mine.favoritesdisuss.presenter;

import android.util.Log;

import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.favoritesdisuss.entity.FavoritesDiscussInfo;
import com.huihu.module_mine.favoritesdisuss.favoritesdisussinterface.IFavoritesDisussModel;
import com.huihu.module_mine.favoritesdisuss.favoritesdisussinterface.IFavoritesDisussPresenter;
import com.huihu.module_mine.favoritesdisuss.favoritesdisussinterface.IFavoritesDisussView;
import com.huihu.module_mine.favoritesdisuss.model.ImpFavoritesDisussModel;

import java.util.List;

public class ImpFavoritesDisussPresenter implements IFavoritesDisussPresenter {
    private final IFavoritesDisussModel iFavoritesDisussModel = new ImpFavoritesDisussModel(this);
    private final IFavoritesDisussView iFavoritesDisussView;

    public ImpFavoritesDisussPresenter(IFavoritesDisussView iFavoritesDisussView) {
        this.iFavoritesDisussView = iFavoritesDisussView;
    }

    @Override
    public void v2pGetDisscusList() {
        iFavoritesDisussModel.p2mGetDiscussList(3,0,20, 284003,false);
    }

    @Override
    public void m2pReturnDiscussList(List<FavoritesDiscussInfo.PageDatasBean> datas, boolean isMore) {
        if (datas!=null && datas.size()>0){
            if (isMore){
                //加载更多的数据，调用v层的加载更多的方法
                iFavoritesDisussView.p2vReturnMoreDiscussListSuccess(datas);
            }else{
                //不用加载更多的数据，直接返回给v层
                iFavoritesDisussView.p2vReturnDiscussListSuccess(datas);
            }
            //pageDatas没有数据的时候
        }else{
            //并且不刷新
            if (!isMore){
                iFavoritesDisussView.p2vShowNoData();
            }

        }
    }

    @Override
    public void v2pGetMoreDiscussList(FavoritesDiscussInfo.PageDatasBean pageDatasBean) {
        iFavoritesDisussModel.p2mGetDiscussList(3,pageDatasBean.getEditTime(),20,284003,true);
    }

    @Override
    public void m2pNetFail() {
        iFavoritesDisussView.p2vNetFail();
    }

    @Override
    public void m2pNetComplete() {
        iFavoritesDisussView.p2vNetComplete();
    }

    @Override
    public void v2pRequestDeletedItem(long ideaId, int uid) {
        iFavoritesDisussModel.p2mReturnDeletedItem(ideaId,uid);
    }

    @Override
    public void m2pReturnDeletedItem() {
iFavoritesDisussView.p2mReturnDeletedItem();
    }
}
