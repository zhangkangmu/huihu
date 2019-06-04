package com.huihu.module_mine.favoritesdisuss.favoritesdisussinterface;

public interface IFavoritesDisussModel {

    void p2mGetDiscussList(int ideaType, long lastTime, int pageSize, int uid, boolean isMore);

    void p2mReturnDeletedItem(long ideaId, int uid);

}
