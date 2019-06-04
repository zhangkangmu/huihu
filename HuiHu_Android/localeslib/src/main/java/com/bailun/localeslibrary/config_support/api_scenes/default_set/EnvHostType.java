package com.bailun.localeslibrary.config_support.api_scenes.default_set;

/**
 * Created by kingpang on 2018/8/23.
 */

public class EnvHostType {
    public static class Test extends EnvHostBase {
        public Test() {
        }
    }

    public static class Debug extends EnvHostBase {
        public Debug() {
            HuihuHost = "http://101.37.160.220:18081";
        }
    }

    public static class PreRelease extends EnvHostBase {
        public PreRelease() {
        }
    }

    public static class Release extends EnvHostBase {
        public Release() {
        }
    }

}
