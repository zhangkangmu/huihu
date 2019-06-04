package com.huihu.module_mine.favorites.model;

import com.huihu.module_mine.favorites.favoritesinterface.IFavoritesModel;
import com.huihu.module_mine.favorites.favoritesinterface.IFavoritesPresenter;

public class ImpFavoritesModel implements IFavoritesModel {
    private final IFavoritesPresenter iFavoritesPresenter;

    public ImpFavoritesModel(IFavoritesPresenter iFavoritesPresenter) {
        this.iFavoritesPresenter = iFavoritesPresenter;
    }
}
