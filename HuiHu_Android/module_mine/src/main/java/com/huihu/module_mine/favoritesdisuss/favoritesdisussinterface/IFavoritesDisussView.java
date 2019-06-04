package com.huihu.module_mine.favoritesdisuss.favoritesdisussinterface;

import com.huihu.module_mine.favoritesdisuss.entity.FavoritesDiscussInfo;

import java.util.List;

public interface IFavoritesDisussView {

    void p2vReturnMoreDiscussListSuccess(List<FavoritesDiscussInfo.PageDatasBean> datas);

    void p2vReturnDiscussListSuccess(List<FavoritesDiscussInfo.PageDatasBean> datas);

    void p2vShowNoData();

    void p2vNetFail();

    void p2vNetComplete();

    void p2mReturnDeletedItem();
}
