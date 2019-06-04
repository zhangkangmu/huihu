package com.example.localeslibrary;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseHostApiUtil;
import com.bailun.localeslibrary.config_support.api_scenes.zh_cn.HostApiUtil_zh_cn;
import com.bailun.localeslibrary.type.EnvironmentEnum;

import org.junit.Test;

/**
 * Created by kingpang on 2018/8/28.
 */

public class Check_ZH_CN_HostAPI_class {
    @Test
    public void display_mock_hostapi() throws Exception {
        BaseHostApiUtil zh_cn_HostAPI = new HostApiUtil_zh_cn();// Mockito.mock(HostApiUtil_zh_cn.class);
        zh_cn_HostAPI.activation(EnvironmentEnum.DEBUG);
        myPrint("-----------------------------------------");
        myPrint(zh_cn_HostAPI.LoginAndRegister.Search);
        myPrint(zh_cn_HostAPI.LoginAndRegister.Query);
        myPrint(zh_cn_HostAPI.Info.Search);
        myPrint(zh_cn_HostAPI.Info.Delete);
        myPrint(zh_cn_HostAPI.Info.Update);
    }

    private void myPrint(String strMsg) {
        System.out.print(strMsg);
        System.out.print("\r\n");
    }
}
