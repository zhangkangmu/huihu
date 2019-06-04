package com.huihu.module_mine.favoritesanswered.favoritesansweredinterface;

import com.huihu.module_mine.favoritesanswered.entity.FavoritesAnsweredInfo;

import java.util.List;

public interface IFavoritesAnsweredPresenter {

    void v2pGetAnsweredList();
    void m2pReturnAnsweredList(List<FavoritesAnsweredInfo.PageDatasBean> pageDatas, boolean isMore);

    void v2pGetMoreAnsweredList(FavoritesAnsweredInfo.PageDatasBean pageDatasBean);

    void m2pNetFail();

    void m2pNetComplete();

    void v2pRequestDeletedItem(long ideaId ,int uid);

    void m2pReturnDeletedItem();
}
