package com.huihu.module_mine.editprofile.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.editprofile.editprofileinterface.IEditProfileModel;
import com.huihu.module_mine.editprofile.editprofileinterface.IEditProfilePresenter;
import com.huihu.module_mine.editprofile.entity.User;
import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.util.imageupload.ImageUpLoadCallback;
import com.huihu.uilib.util.imageupload.ImageUpload;

import java.util.List;


public class ImpEditProfileModel implements IEditProfileModel {
    private static final String TAG = "ImpEditProfileModel";

    private final IEditProfilePresenter iEditProfilePresenter;

    String mUid = SPUtils.getInstance().getCurrentUid() + "";
    private User mUser;
    private Gson mGson = new Gson();

    public ImpEditProfileModel(IEditProfilePresenter iEditProfilePresenter) {
        this.iEditProfilePresenter = iEditProfilePresenter;
    }

    public void p2mGetUserEditDetails() {
        mUid = SPUtils.getInstance().getCurrentUid() + "";
        getUserEditDetails(mUid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                Log.d(TAG, "body message " + returnModel.getBodyMessage());
                mUser = mGson.fromJson(returnModel.getBodyMessage(), User.class);
                iEditProfilePresenter.m2pInitProfileFragment(mUser);
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                Log.e(TAG, "get user info fialture: " + errCode);
                mUser = null;
                iEditProfilePresenter.m2pGetUserEditDetailsError(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }


    public void p2mPutUpdateEducation(String education) {
        mUid = SPUtils.getInstance().getCurrentUid() + "";
        putUpdateEducation(education, mUid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                Log.d(TAG, "update education seccess");
                iEditProfilePresenter.m2pPutUpdateEducationSuccess();
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iEditProfilePresenter.m2pPutUpdateEducationError(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public void p2mPutUpdateOtherDetail(String name, int sex, String industry) {
        mUid = SPUtils.getInstance().getCurrentUid() + "";
        putUpdateOtherDetail(name, sex, industry, mUid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                Log.d(TAG, "put and update sex seccess");
            }

            @Override
            public void onError(int errCode, String strErrMsg) {

            }

            @Override
            public void onCompleted() {

            }
        });

    }

    public void p2mUpdateSex(int sex) {
        mUid = SPUtils.getInstance().getCurrentUid() + "";
        putUpdateOtherDetail(null, sex, null, mUid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                Log.d(TAG, "put and update sex seccess");
                iEditProfilePresenter.m2pUpdateSexSuccess();
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iEditProfilePresenter.m2pUpdateSexError(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mUpdateIndustry(String industry) {
        mUid = SPUtils.getInstance().getCurrentUid() + "";
        putUpdateOtherDetail(null, -1, industry, mUid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                iEditProfilePresenter.m2pUpdateIndustrySuccess();
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iEditProfilePresenter.m2pUpdateIndustryError(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mPutUpdateHeadImage(String headImage) {
        mUid = SPUtils.getInstance().getCurrentUid() + "";
        putUpdateHeadImage(headImage, mUid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                Log.d(TAG, "p2mPutUpdateHeadImage success");
                iEditProfilePresenter.m2pPutUpdateHeadImageSuccess();
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                Log.d(TAG, "p2mPutUpdateHeadImage error: " + errCode);
                Log.d(TAG, strErrMsg);
                iEditProfilePresenter.m2pPutUpdateHeadImageError(strErrMsg);
            }

            @Override
            public void onCompleted() {

            }
        });
    }

    @Override
    public void p2mPostImageUtil(final List<AlbumImageBean> imageBeans) {
        //List<AlbumImageBean> imageBeans = new ArrayList<>();

        ImageUpload.getInstance().uploadPicture(imageBeans, new ImageUpLoadCallback() {
            @Override
            public void onSuccess() {
                iEditProfilePresenter.m2pPostImageUtilSuccess(imageBeans.get(0).getUrl());
            }

            @Override
            public void onFail(String errorMsg) {
                iEditProfilePresenter.m2pPostImageUtilFail(errorMsg);
            }
        });
    }

    //    @Override
    //    public void p2mPostImage(File photo){
    //        postImage(photo, new HuihuCallBack() {
    //            @Override
    //            public void onSuccess(ReturnModel returnModel) {
    //                Log.d(TAG, returnModel.getBodyMessage());
    //                String headImageUrl = returnModel.getBodyMessage();
    //                p2mPutUpdateHeadImage(headImageUrl);
    //            }
    //
    //            @Override
    //            public void onError(int errCode, String strErrMsg) {
    //                Log.d(TAG, "p2mPostImage error: " + errCode);
    //                Log.d(TAG, strErrMsg);
    //                iEditProfilePresenter.m2pPutUpdateHeadImageError(strErrMsg);
    //            }
    //
    //            @Override
    //            public void onCompleted() {
    //
    //            }
    //        });
    //    }

    public void getUserEditDetails(String userId, HuihuCallBack callback) {
        Log.d(TAG, "getUserEditDetails: " + userId);
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.User.getUserEditDetails, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("userId", userId + "");
        HuihuHttpUtils.httpRequest(httpRequestParam, callback);
    }

    public void putUpdateEducation(String education, String uid, HuihuCallBack callBack) {
        Log.d(TAG, "putUpdateEducation");
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.User.putUpdateEducation, NetworkTransmissionDefine.HttpMethod.PUT);
        httpRequestParam.addQuery("education", education + "");
        httpRequestParam.addQuery("uid", uid + "");
        HuihuHttpUtils.httpRequest(httpRequestParam, callBack);
    }


    // update name, sex, industry
    public void putUpdateOtherDetail(String nickName, int sex, String occupationValue, String uid, HuihuCallBack callBack) {
        Log.d(TAG, "putUpdateOtherDetail");
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.User.putUpdateOtherDetail, NetworkTransmissionDefine.HttpMethod.PUT);
        if (nickName != null) {
            httpRequestParam.addQuery("nickName", nickName + "");
        }
        if (sex == 0 || sex == 1) {
            httpRequestParam.addQuery("sex", sex + "");
        }
        if (occupationValue != null) {
            httpRequestParam.addQuery("occupationValue", occupationValue + "");
        }
        httpRequestParam.addQuery("uid", uid + "");
        HuihuHttpUtils.httpRequest(httpRequestParam, callBack);
    }

    //上传头像
    //    public void postImage(File photo, HuihuCallBack callBack){
    //        Log.d(TAG, "postImage");
    //        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Common.PostImage, NetworkTransmissionDefine.HttpMethod.POST);
    //        ImagePostRequest imagePostRequest = new ImagePostRequest(BitmapUtils.bitmapToBase64(BitmapFactory.decodeFile(photo.getPath())));
    //        imagePostRequest.setFileType(1);
    //        imagePostRequest.setSiteType(17);
    //        String request = new Gson().toJson(imagePostRequest);
    //        Log.d(TAG, "imagePostRequest: " + request);
    //        httpRequestParam.addBody(request);
    //        HuihuHttpUtils.httpRequest(httpRequestParam, callBack);
    //    }

    //修改头像
    public void putUpdateHeadImage(String headImage, String uid, HuihuCallBack callBack) {
        Log.d(TAG, "putUpdateHeadImage");
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.User.putUpdateHeadImage, NetworkTransmissionDefine.HttpMethod.PUT);
        httpRequestParam.addQuery("headImage", headImage + "");
        httpRequestParam.addQuery("uid", uid + "");
        HuihuHttpUtils.httpRequest(httpRequestParam, callBack);
    }
}
