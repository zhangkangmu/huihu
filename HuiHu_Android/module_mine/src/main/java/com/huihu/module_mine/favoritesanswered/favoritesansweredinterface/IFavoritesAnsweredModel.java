package com.huihu.module_mine.favoritesanswered.favoritesansweredinterface;

public interface IFavoritesAnsweredModel {

    void p2mGetAnsweredList(int ideaType, long lastTime, int pageSize, int uid,boolean isMore );

    void p2mRequestDeletedItem(long ideaId, int uid);

}
