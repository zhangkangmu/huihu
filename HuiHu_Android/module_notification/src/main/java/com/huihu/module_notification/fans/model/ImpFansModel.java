package com.huihu.module_notification.fans.model;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_notification.fans.entity.FollowPageBean;
import com.huihu.module_notification.fans.entity.GetFansOrFollowListSubcode;
import com.huihu.module_notification.fans.fansinterface.IFansModel;
import com.huihu.module_notification.fans.fansinterface.IFansPresenter;
import com.huihu.module_notification.util.FollowUtil;
import com.huihu.uilib.def.FollowType;
import com.huihu.uilib.subcode.PutGiveFollowsSubcode;

import static com.huihu.uilib.def.NetDataBoolean.NET_FALSE;
import static com.huihu.uilib.def.NetDataBoolean.NET_TRUE;

public class ImpFansModel implements IFansModel {

    private static final int PAGE_SIZE = 20;

    private final IFansPresenter iFansPresenter;

    public ImpFansModel(IFansPresenter iFansPresenter) {
        this.iFansPresenter = iFansPresenter;
    }

    @Override
    public void p2mGetFansOrFollowListNet(long followId, final int type, final boolean isMore) {
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Follow.GetFansOrFollowList
                , NetworkTransmissionDefine.HttpMethod.GET);
        param.addQuery("followId", String.valueOf(followId));
        param.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        param.addQuery("type", String.valueOf(type));
        param.addQuery("uid", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        param.addQuery("userId", String.valueOf(SPUtils.getInstance().getCurrentUid()));
        HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                String subcode = returnModel.getSubCode();
                switch (subcode){
                    case GetFansOrFollowListSubcode.Success:
                        FollowPageBean bean = new Gson().fromJson(returnModel.getBodyMessage(), FollowPageBean.class);
                        iFansPresenter.m2pGetFansOrFollowSuccess(type, isMore, bean);
                        break;
                    case GetFansOrFollowListSubcode.ParameterError:
                        break;
                    case GetFansOrFollowListSubcode.BusinessError:
                        break;
                    case GetFansOrFollowListSubcode.ThirdPartyError:
                        break;
                    default:
                        break;

                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iFansPresenter.m2pNetFail();
                iFansPresenter.m2pGetFansFail(type);
            }

            @Override
            public void onCompleted() {
                iFansPresenter.m2pGetDataComplete();
            }
        });
    }

    @Override
    public void p2mPutGiveFollows(final int type, final FollowPageBean.DataBean bean) {
        int state = bean.isFollow() ? NET_FALSE : NET_TRUE;
        FollowUtil.follow(bean.getUserId(), FollowType.HUMAN, state, SPUtils.getInstance().getCurrentUid(), new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case PutGiveFollowsSubcode.Success:
                        bean.setFollow(!bean.isFollow());
                        iFansPresenter.m2pPutGiveFollowsSuccess(type, bean);
                        break;
                    case PutGiveFollowsSubcode.ParameterError:
                        break;
                    case PutGiveFollowsSubcode.BusinessError:
                        break;
                    case PutGiveFollowsSubcode.UserNoLogin:
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iFansPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {

            }
        });

    }
}
