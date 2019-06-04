package com.huihu.module_mine.favoritesanswered.favoritesansweredinterface;

import com.huihu.module_mine.favoritesanswered.entity.FavoritesAnsweredInfo;

import java.util.List;

public interface IFavoritesAnsweredView {

    void p2vReturnMoreAnsweredListSuccess(List<FavoritesAnsweredInfo.PageDatasBean> pageDatas);

    void p2vReturnAnsweredListSuccess(List<FavoritesAnsweredInfo.PageDatasBean> pageDatas);

    void p2vShowNoData();

    void p2vNetFail();

    void p2vNetComplete();

    void p2mReturnDeletedItem();

}
