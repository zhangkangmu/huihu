package com.huihu.module_circle.circlemember.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_circle.circlemember.circlememberinterface.ICircleMemberModel;
import com.huihu.module_circle.circlemember.circlememberinterface.ICircleMemberPresenter;
import com.huihu.module_circle.circlemember.entity.MemberManagementInfo;
import com.huihu.module_circle.circlemember.entity.PutGiveFollowsSubCode;
import com.huihu.module_circle.newcirclediscuss.entity.CancelFollowSubCode;

import java.util.List;

public class ImpCircleMemberModel implements ICircleMemberModel {
    private final ICircleMemberPresenter iCircleMemberPresenter;

    public ImpCircleMemberModel(ICircleMemberPresenter iCircleMemberPresenter) {
        this.iCircleMemberPresenter = iCircleMemberPresenter;
    }

    @Override
    public void p2mRequestMemberList(long circleId, long lastTime, int memberType, int pageSize, int uid,final boolean isMore) {
        getMemberList(circleId,lastTime,memberType,pageSize,uid,new HuihuCallBack(){

            @Override
            public void onSuccess(ReturnModel returnModel) {
                MemberManagementInfo info = new Gson().fromJson(returnModel.getBodyMessage(), MemberManagementInfo.class);
                List<MemberManagementInfo.PageDatasBean> datas = info.getPageDatas();
                iCircleMemberPresenter.m2pReturnMemberList(datas,isMore);
                Log.d("zyh-member-m",""+datas.size());
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCircleMemberPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iCircleMemberPresenter.m2pNetComplete();
            }
        });
    }

    @Override
    public void p2mPutGiveFollows(final MemberManagementInfo.PageDatasBean.UserInfoBean userInfo) {
        getFollow(userInfo,new HuihuCallBack(){

            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case PutGiveFollowsSubCode.Success:
                        if (userInfo.getFollow()!=0) {
                            userInfo.setFollow(0);
                        }else {
                            userInfo.setFollow(1);
                        }
                        iCircleMemberPresenter.m2pReturnSuccesAttention();
                        break;
                    case PutGiveFollowsSubCode.UnLogin:
                    case CancelFollowSubCode.ParameterError:
                    case CancelFollowSubCode.BusinessError:
                    default:
                        iCircleMemberPresenter.m2pPutGiveFollowsError(returnModel.getSubCode());
                        break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    //点击关注
    private void getFollow(MemberManagementInfo.PageDatasBean.UserInfoBean userInfo, HuihuCallBack huihuCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Follow.PutGiveFollows, NetworkTransmissionDefine.HttpMethod.PUT);
        httpRequestParam.addQuery("aboutId", ""+userInfo.getUid());
        httpRequestParam.addQuery("followType", "1");
        if (userInfo.getFollow()!=0) {
            httpRequestParam.addQuery("state", "0");
        }else {
            httpRequestParam.addQuery("state","1");
        }
        httpRequestParam.addQuery("uid", ""+ SPUtils.getInstance().getCurrentUid());
        HuihuHttpUtils.httpRequest(httpRequestParam, huihuCallBack);

    }

    private void getMemberList(long circleId, long lastTime, int memberType, int pageSize, int uid, HuihuCallBack huihuCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Circle.getCircleMember, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("circleId", "" + circleId);
        httpRequestParam.addQuery("lastTime", "" + lastTime);
        httpRequestParam.addQuery("memberType", "" + memberType);
        httpRequestParam.addQuery("pageSize", "" + pageSize);
        httpRequestParam.addQuery("uid", ""+uid);
        HuihuHttpUtils.httpRequest(httpRequestParam, huihuCallBack);
    }
}
