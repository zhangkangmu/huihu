package com.huihu.module_mine.selectcountry.selectcountryinterface;

import com.huihu.module_mine.selectcountry.entity.CountryModel;

import java.util.List;

public interface ISelectCountryPresenter {

    void v2pGetCountry();

    void m2pGetCountrySuccess(List<CountryModel> list);

    void m2pGetCountryFail(String message);
}
