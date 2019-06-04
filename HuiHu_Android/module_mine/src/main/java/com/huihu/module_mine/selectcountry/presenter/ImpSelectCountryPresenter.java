package com.huihu.module_mine.selectcountry.presenter;

import com.huihu.module_mine.selectcountry.entity.CountryModel;
import com.huihu.module_mine.selectcountry.selectcountryinterface.ISelectCountryModel;
import com.huihu.module_mine.selectcountry.selectcountryinterface.ISelectCountryPresenter;
import com.huihu.module_mine.selectcountry.selectcountryinterface.ISelectCountryView;
import com.huihu.module_mine.selectcountry.model.ImpSelectCountryModel;

import java.util.List;

public class ImpSelectCountryPresenter implements ISelectCountryPresenter {
    private final ISelectCountryModel iSelectCountryModel = new ImpSelectCountryModel(this);
    private final ISelectCountryView iSelectCountryView;

    public ImpSelectCountryPresenter(ISelectCountryView iSelectCountryView) {
        this.iSelectCountryView = iSelectCountryView;
    }

    @Override
    public void v2pGetCountry() {
        iSelectCountryModel.p2mGetCountries();
    }

    @Override
    public void m2pGetCountrySuccess(List<CountryModel> list) {
        iSelectCountryView.p2vDismissDialog();
        iSelectCountryView.p2vShowData(list);
    }

    @Override
    public void m2pGetCountryFail(String message) {
        iSelectCountryView.p2vDismissDialog();
        iSelectCountryView.p2vShowToast(message);
    }
}
