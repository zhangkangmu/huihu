package com.example.localeslibrary;

import android.content.Context;
import android.test.mock.MockContext;

import com.bailun.localeslibrary.CurLocales;
import com.bailun.localeslibrary.type.EnvironmentEnum;
import com.bailun.localeslibrary.type.SupportLanguageEnum;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by kingpang on 2018/8/21.
 */

public class LocaleUnitTest {

    @Before
    public void setUp() throws Exception {
        Context context = new MockContext();
        CurLocales.init(context);
        CurLocales.instance().Set(SupportLanguageEnum.EN_US, EnvironmentEnum.TEST);
    }

    @Test
    public void display_ENUS_and_ZHCN() throws Exception {
        myPrint("----  语言：EN-US 环境：Test  ---");
        myPrint(CurLocales.instance().getCurConfigString());
        myPrint(CurLocales.instance().API.LoginAndRegister.Search);
        myPrint(CurLocales.instance().API.LoginAndRegister.Query);
        myPrint(CurLocales.instance().API.Info.Search);
        myPrint(CurLocales.instance().API.Info.Delete);
        myPrint(CurLocales.instance().API.Info.Update);

        myPrint("----  语言：EN-US 环境：Debug  ---");
        CurLocales.instance().Set(EnvironmentEnum.DEBUG);
        myPrint(CurLocales.instance().getCurConfigString());
        myPrint(CurLocales.instance().API.LoginAndRegister.Search);
        myPrint(CurLocales.instance().API.LoginAndRegister.Query);
        myPrint(CurLocales.instance().API.Info.Search);
        myPrint(CurLocales.instance().API.Info.Delete);
        myPrint(CurLocales.instance().API.Info.Update);

        myPrint("----  语言：EN-US 环境：Release  ---");
        CurLocales.instance().Set(EnvironmentEnum.RELEASE);
        myPrint(CurLocales.instance().getCurConfigString());
        myPrint(CurLocales.instance().API.LoginAndRegister.Search);
        myPrint(CurLocales.instance().API.LoginAndRegister.Query);
        myPrint(CurLocales.instance().API.Info.Search);
        myPrint(CurLocales.instance().API.Info.Delete);
        myPrint(CurLocales.instance().API.Info.Update);

        myPrint("----  语言：ZH-CN 环境：Release  ---");
        CurLocales.instance().Set(SupportLanguageEnum.ZH_CN);
        myPrint(CurLocales.instance().getCurConfigString());
        myPrint(CurLocales.instance().API.LoginAndRegister.Search);
        myPrint(CurLocales.instance().API.LoginAndRegister.Query);
        myPrint(CurLocales.instance().API.Info.Search);
        myPrint(CurLocales.instance().API.Info.Delete);
        myPrint(CurLocales.instance().API.Info.Update);

        myPrint("----  语言：ZH-CN 环境：Test  ---");
        CurLocales.instance().Set(EnvironmentEnum.TEST);
        myPrint(CurLocales.instance().getCurConfigString());
        myPrint(CurLocales.instance().API.LoginAndRegister.Search);
        myPrint(CurLocales.instance().API.LoginAndRegister.Query);
        myPrint(CurLocales.instance().API.Info.Search);
        myPrint(CurLocales.instance().API.Info.Delete);
        myPrint(CurLocales.instance().API.Info.Update);

        myPrint("----  语言：ZH-CN 环境：Debug  ---");
        CurLocales.instance().Set(EnvironmentEnum.DEBUG);
        myPrint(CurLocales.instance().getCurConfigString());
        myPrint(CurLocales.instance().API.LoginAndRegister.Search);
        myPrint(CurLocales.instance().API.LoginAndRegister.Query);
        myPrint(CurLocales.instance().API.Info.Search);
        myPrint(CurLocales.instance().API.Info.Delete);
        myPrint(CurLocales.instance().API.Info.Update);

        CurLocales.instance().ROUTER.openLoginUI()
                if en_us:
                defaultLoginUI:UIViewController
                        zh_cn:
                LoginUI4China :UIViewController
                        ReS
    }

    private void myPrint(String strMsg) {
        System.out.print(strMsg);
        System.out.print("\r\n");
    }
}
