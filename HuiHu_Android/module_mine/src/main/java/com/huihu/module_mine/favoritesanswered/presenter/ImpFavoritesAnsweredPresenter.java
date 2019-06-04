package com.huihu.module_mine.favoritesanswered.presenter;


import android.util.Log;

import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.favoritesanswered.entity.FavoritesAnsweredInfo;
import com.huihu.module_mine.favoritesanswered.favoritesansweredinterface.IFavoritesAnsweredModel;
import com.huihu.module_mine.favoritesanswered.favoritesansweredinterface.IFavoritesAnsweredPresenter;
import com.huihu.module_mine.favoritesanswered.favoritesansweredinterface.IFavoritesAnsweredView;
import com.huihu.module_mine.favoritesanswered.model.ImpFavoritesAnsweredModel;

import java.util.List;

public class ImpFavoritesAnsweredPresenter implements IFavoritesAnsweredPresenter {
    private final IFavoritesAnsweredModel iFavoritesAnsweredModel = new ImpFavoritesAnsweredModel(this);
    private final IFavoritesAnsweredView iFavoritesAnsweredView;

    public ImpFavoritesAnsweredPresenter(IFavoritesAnsweredView iFavoritesAnsweredView) {
        this.iFavoritesAnsweredView = iFavoritesAnsweredView;
    }

    @Override
        public void v2pGetAnsweredList() {
        iFavoritesAnsweredModel.p2mGetAnsweredList(2,0,20,284003,false);
    }

    @Override
    public void m2pReturnAnsweredList(List<FavoritesAnsweredInfo.PageDatasBean> pageDatas, boolean isMore) {
        if (pageDatas!=null && pageDatas.size()>0){
            if (isMore){
                //加载更多的数据，调用v层的加载更多的方法
                iFavoritesAnsweredView.p2vReturnMoreAnsweredListSuccess(pageDatas);
            }else{
                //不用加载更多的数据，直接返回给v层
                iFavoritesAnsweredView.p2vReturnAnsweredListSuccess(pageDatas);
            }
            //pageDatas没有数据的时候
        }else{
            //并且不刷新
            if (!isMore){
                iFavoritesAnsweredView.p2vShowNoData();
            }

        }
    }

    @Override
    public void v2pGetMoreAnsweredList(FavoritesAnsweredInfo.PageDatasBean pageDatasBean) {
        //加载更多的方法
        iFavoritesAnsweredModel.p2mGetAnsweredList(2,0,20,284003,true);
    }

    @Override
    public void m2pNetFail() {
        iFavoritesAnsweredView.p2vNetFail();
    }

    @Override
    public void m2pNetComplete() {
        iFavoritesAnsweredView.p2vNetComplete();
    }

    @Override
    public void v2pRequestDeletedItem(long ideaId, int uid) {
        iFavoritesAnsweredModel.p2mRequestDeletedItem(ideaId, uid);
    }

    @Override
    public void m2pReturnDeletedItem() {
        iFavoritesAnsweredView.p2mReturnDeletedItem();
    }
}
