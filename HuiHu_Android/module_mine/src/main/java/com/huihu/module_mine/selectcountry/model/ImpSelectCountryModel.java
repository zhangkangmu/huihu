package com.huihu.module_mine.selectcountry.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.module_mine.selectcountry.entity.CountryModel;
import com.huihu.module_mine.selectcountry.entity.SelectCountrySubcode;
import com.huihu.module_mine.selectcountry.selectcountryinterface.ISelectCountryModel;
import com.huihu.module_mine.selectcountry.selectcountryinterface.ISelectCountryPresenter;

import java.util.List;

public class ImpSelectCountryModel implements ISelectCountryModel {
    private final ISelectCountryPresenter iSelectCountryPresenter;

    public ImpSelectCountryModel(ISelectCountryPresenter iSelectCountryPresenter) {
        this.iSelectCountryPresenter = iSelectCountryPresenter;
    }

    private void getCountries(HuihuCallBack callBack) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Sms.getCountries, NetworkTransmissionDefine.HttpMethod.GET);
        HuihuHttpUtils.httpRequest(param, callBack);
    }

    @Override
    public void p2mGetCountries() {
        getCountries(new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel model) {
                switch (model.getSubCode()) {
                    case SelectCountrySubcode.success:
                        List<CountryModel> countryModelList = new Gson().fromJson(model.getBodyMessage(), new TypeToken<List<CountryModel>>() {
                        }.getType());
                        iSelectCountryPresenter.m2pGetCountrySuccess(countryModelList);
                        break;
                    default:
                        iSelectCountryPresenter.m2pGetCountryFail(model.getMessage());
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iSelectCountryPresenter.m2pGetCountryFail(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }
}
