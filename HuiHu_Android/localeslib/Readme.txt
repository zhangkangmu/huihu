----------------------------------
-      多语言选择使用文档        -
----------------------------------

1、某种语种的增删改查
        1）增加一个语种，在SupportLanguageEnum.java文件，加多一个enum值，例如：
            ZH_CN(createLocales("en", "US",new HostApiUtil_zh_cn())), //new HostApiUtil_zh_cn() = 用自己的Host+API
            ZH_CN(createLocales("en", "US",null)); //null = 用默认的Host+API
        2）如果需要自己配置本语种的Host和API，就在api_scenes目录下配置一个新目录，以“语言_区域”来命名：
            a）EnvHostType_zh_cn.java：配置4个环境的Host，按需来配：

           ---------------------------------------------------------------------
            public class EnvHostType_zh_cn {
                public static class Test extends EnvHostBase {
                    public Test() {
                        MainFunc = "http://[中文大陆].Main.Host.Test";
                        AssectFunc = "http://[中文大陆].Assect.Host.Test";
                        ThirdFunc = "http://[中文大陆].3th.Host.Test";
                    }
                }

                public static class Debug extends EnvHostBase {
                    public Debug() {
                        MainFunc = "http://[中文大陆].Main.Host.Debug";
                        ThirdFunc = "http://[中文大陆].3th.Host.Debug";
                    }
                }

                public static class PreRelease extends EnvHostBase {
                    public PreRelease() {
                    }
                }

                public static class Release extends EnvHostBase {
                    public Release() {
                        MainFunc = "http://[中文大陆].Main.Host.Release";
                        ThirdFunc = "http://[中文大陆].3th.Host.Release";
                    }
                }
            }
            ---------------------------------------------------------------------

            b）HostApiUtil_zh_cn.java：配置获得4个对象的值和createAPIs函数（可能某个API的名称会改，否则不用重写这个函数）：

            ---------------------------------------------------------------------
            public class HostApiUtil_zh_cn extends BaseHostApiUtil {
                @Override
                public EnvHostBase getDebugHost() {
                    return new EnvHostType_zh_cn.Debug();
                }

                @Override
                public EnvHostBase getTestHost() {
                    return new EnvHostType_zh_cn.Test();
                }

                @Override
                public EnvHostBase getPreReleaseHost() {
                    return new EnvHostType_zh_cn.PreRelease();
                }

                @Override
                public EnvHostBase getReleaseHost() {
                    return new EnvHostType_zh_cn.Release();
                }

                //如果API的名称不改，不用写这个函数，这个情况很少，只是考虑进入了而已
                @Override
                public void createAPIs(EnvHostBase host) {
                    super.createAPIs(host);
                    Login = new LoginAPI_zh_cn(host);
                }
            }
            ---------------------------------------------------------------------
            //如果上面，Login的API徐誉滕重写，就在这里进行需要的地方的拓展
            public class LoginAPI_zh_cn extends DefaultLoginAPI {
                public LoginAPI_zh_cn(EnvHostBase host) {
                    super(host);

                    Search = Host.MainFunc + "/Login_zh_cn/搜索/";
                    Query = Host.MainFunc + "/Login_zh_cn/查询/";
                }
            }
            ---------------------------------------------------------------------

2、HOST的增删改查
        1）在EnvHostBase.java文件中，增加需要增加的host，例如：public String newHost = "http://New.Host";
        2）在EnvHostType.java文件中，对Test、Debug...具体环境下对应的newHost进行补充拓展，例如：
        public class EnvHostType {
            public static class Test extends EnvHostBase {
                public Test() {
                    newHost = "http://default.New.Host.Test";
                    ... ...
                }
            }
        3）如果其他语种有不同的Host，需要在对应的语种下对EnvHostType_xx_xx.java文件里的newHost进行补充拓展，形式同2）中的一样
        4）删除的时候，先将default_set目录下EnvHostBase删除，再根据报错来改吧
        5）修改某一个Host的值，根据语种+环境具体来修改某一个，其他的不受影响

3、API的增删改查

