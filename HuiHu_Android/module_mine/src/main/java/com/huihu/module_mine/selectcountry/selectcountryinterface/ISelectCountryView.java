package com.huihu.module_mine.selectcountry.selectcountryinterface;

import com.huihu.module_mine.selectcountry.entity.CountryModel;

import java.util.List;

public interface ISelectCountryView {

    void p2vShowToast(String message);

    void p2vDismissDialog();

    void p2vShowData(List<CountryModel> list);
}
