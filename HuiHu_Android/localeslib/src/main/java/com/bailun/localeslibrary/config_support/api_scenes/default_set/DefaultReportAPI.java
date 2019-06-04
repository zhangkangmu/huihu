package com.bailun.localeslibrary.config_support.api_scenes.default_set;

import com.bailun.localeslibrary.config_support.api_scenes.api_host_depend.BaseApi;

public class DefaultReportAPI extends BaseApi {
    public DefaultReportAPI(EnvHostBase host) {
        super(host);
    }

    public String PostReport = Host.HuihuHost + "/api/postReport";
    public String GetReportTag = Host.HuihuHost + "/api/getReportTag";

}
