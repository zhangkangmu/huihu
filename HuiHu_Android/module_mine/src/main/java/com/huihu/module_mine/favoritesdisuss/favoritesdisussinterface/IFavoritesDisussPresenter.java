package com.huihu.module_mine.favoritesdisuss.favoritesdisussinterface;

import com.huihu.module_mine.favoritesdisuss.entity.FavoritesDiscussInfo;

import java.util.List;

public interface IFavoritesDisussPresenter {

    void v2pGetDisscusList();

    void m2pReturnDiscussList(List<FavoritesDiscussInfo.PageDatasBean> datas, boolean isMore);

    void v2pGetMoreDiscussList(FavoritesDiscussInfo.PageDatasBean pageDatasBean);

    void m2pNetFail();

    void m2pNetComplete();

    void v2pRequestDeletedItem(long ideaId, int uid);

    void m2pReturnDeletedItem();
}
