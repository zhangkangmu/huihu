package com.huihu.uilib.commentedit.model;

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
import com.huihu.uilib.commentedit.commenteditinterface.ICommentEditModel;
import com.huihu.uilib.commentedit.commenteditinterface.ICommentEditPresenter;
import com.huihu.uilib.commentedit.entity.PublishCommentBean;
import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.commentedit.entity.ReturnCommentBean;
import com.huihu.uilib.subcode.PutGiveFollowsSubcode;
import com.huihu.uilib.util.imageupload.ImageUpLoadCallback;
import com.huihu.uilib.util.imageupload.ImageUpload;

import java.util.List;

public class ImpCommentEditModel implements ICommentEditModel {
    private static final String TAG = "ImpCommentEditModel";
    private final ICommentEditPresenter iCommentEditPresenter;

    public ImpCommentEditModel(ICommentEditPresenter iCommentEditPresenter) {
        this.iCommentEditPresenter = iCommentEditPresenter;
    }


    public void p2mPublicComment(PublishCommentBean publishCommentBean){
        long uid = SPUtils.getInstance().getCurrentUid();
        publishCommentBean.setUid(uid);
        postPublishComment(publishCommentBean, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                String subcode = returnModel.getSubCode();
                Log.d(TAG,"subcode " + subcode);
                Log.d(TAG,returnModel.getBodyMessage());
                ReturnCommentBean bean = new Gson().fromJson(returnModel.getBodyMessage(),ReturnCommentBean.class);
                iCommentEditPresenter.m2pPublicCommentSuccess(bean);
            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCommentEditPresenter.m2pPublicCommentError(strErrMsg);
            }

            @Override
            public void onCompleted() {
                iCommentEditPresenter.m2pPublicCommentCompleted();
            }
        });
    }

    //发布评论
    public void postPublishComment(PublishCommentBean publishCommentBean, HuihuCallBack callBack){
        Log.d(TAG, new Gson().toJson(publishCommentBean));
        HttpRequestParam param = new HttpRequestParam(CurLocales.instance().API.Comments.PostPublishComment, NetworkTransmissionDefine.HttpMethod.POST);
        param.addBody(new Gson().toJson(publishCommentBean));
        HuihuHttpUtils.httpRequest(param, callBack);
    }


    @Override
    public void p2mPostImageUtil(final List<AlbumImageBean> imageBeans) {

        ImageUpload.getInstance().uploadPicture(imageBeans, new ImageUpLoadCallback() {
            @Override
            public void onSuccess() {
                iCommentEditPresenter.m2pPostCommentImageSuccess(imageBeans);
            }

            @Override
            public void onFail(String errorMsg) {
                iCommentEditPresenter.m2pPostCommentImageFail(errorMsg);
            }
        });
    }

}
