package com.huihu.module_circle.membermanagement.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_circle.membermanagement.entity.MemberManagementInfo;
import com.huihu.module_circle.membermanagement.membermanagementinterface.IMemberManagementModel;
import com.huihu.module_circle.membermanagement.membermanagementinterface.IMemberManagementPresenter;
import com.huihu.module_circle.newcirclediscuss.entity.CancelFollowSubCode;
import com.huihu.module_circle.newcirclediscuss.entity.NewCircleDiscussInfo;
import com.huihu.module_circle.newcirclediscuss.entity.PutGiveFollowsSubCode;

import java.util.List;

public class ImpMemberManagementModel implements IMemberManagementModel {
    private final IMemberManagementPresenter iMemberManagementPresenter;

    public ImpMemberManagementModel(IMemberManagementPresenter iMemberManagementPresenter) {
        this.iMemberManagementPresenter = iMemberManagementPresenter;
    }

    @Override
    public void p2mRequestMemberList(long circleId, long lastTime, int memberType, int pageSize, int uid, final boolean isMore) {
        getMemberList(circleId,lastTime,memberType,pageSize,uid,new HuihuCallBack(){

            @Override
            public void onSuccess(ReturnModel returnModel) {
                MemberManagementInfo info = new Gson().fromJson(returnModel.getBodyMessage(), MemberManagementInfo.class);
                List<MemberManagementInfo.PageDatasBean> datas = info.getPageDatas();
                iMemberManagementPresenter.m2pReturnMemberList(datas,isMore);
                Log.d("zyh-member-m",""+datas.size());
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iMemberManagementPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iMemberManagementPresenter.m2pNetComplete();
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
                        iMemberManagementPresenter.m2pReturnSuccesAttention();
                        break;
                    case PutGiveFollowsSubCode.UnLogin:
                    case CancelFollowSubCode.ParameterError:
                    case CancelFollowSubCode.BusinessError:
                    default:
                        iMemberManagementPresenter.m2pPutGiveFollowsError(returnModel.getSubCode());
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

    @Override
    public void p2mRequestForbidMember(long circleId, final MemberManagementInfo.PageDatasBean bean,String forbidDay) {
        //要改
        forbidMember(circleId,bean,forbidDay,new HuihuCallBack(){

            @Override
            public void onSuccess(ReturnModel returnModel) {
                Log.d("zyh-禁言","onSuccess");
                if (bean.getForbid()!=0){
                    bean.setForbid(0);
                }else {
                    bean.setForbid(1);
                }
                iMemberManagementPresenter.m2pReturnForbidMember(bean);

            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                Log.d("zyh-禁言",strErrMsg);
            }

            @Override
            public void onCompleted() {
            }
        });
    }

    //禁言
    private void forbidMember(long circleId, MemberManagementInfo.PageDatasBean bean,String forbidDay, HuihuCallBack huihuCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Circle.putForbidMember, NetworkTransmissionDefine.HttpMethod.PUT);
        httpRequestParam.addQuery("circleId", "" + circleId);
        httpRequestParam.addQuery("fid", "" + bean.getUserInfo().getUid());
        httpRequestParam.addQuery("forbidDay", forbidDay);
        if (bean.getForbid()!=0){
        httpRequestParam.addQuery("type", "" + 0);
        }else {
            httpRequestParam.addQuery("type", "" + 1);
        }
        httpRequestParam.addQuery("uid", ""+SPUtils.getInstance().getCurrentUid());
        HuihuHttpUtils.httpRequest(httpRequestParam, huihuCallBack);

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
