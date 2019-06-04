package com.huihu.module_mine.favorites.presenter;

import com.huihu.module_mine.favorites.favoritesinterface.IFavoritesModel;
import com.huihu.module_mine.favorites.favoritesinterface.IFavoritesPresenter;
import com.huihu.module_mine.favorites.favoritesinterface.IFavoritesView;
import com.huihu.module_mine.favorites.model.ImpFavoritesModel;

public class ImpFavoritesPresenter implements IFavoritesPresenter {
    private final IFavoritesModel iFavoritesModel = new ImpFavoritesModel(this);
    private final IFavoritesView iFavoritesView;

    public ImpFavoritesPresenter(IFavoritesView iFavoritesView) {
        this.iFavoritesView = iFavoritesView;
    }
}
