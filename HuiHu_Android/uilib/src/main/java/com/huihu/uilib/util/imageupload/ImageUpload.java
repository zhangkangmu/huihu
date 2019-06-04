package com.huihu.uilib.util.imageupload;


import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.util.ImageUtil;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * create by wangjing on 2019/3/27 0027
 * description:用于汇乎图片上传
 */
public class ImageUpload {

    private static ImageUpload mImageUpload;

    private String mImageUploadAPI = CurLocales.instance().API.Common.PostImage;
    private Map<String, String> mCache = new Hashtable<>();
    private ImageUpLoadCallback mCallback;
    private int size;
    private int completeCount;
    private int successCount;

    public static ImageUpload getInstance(){
        if (mImageUpload == null) {
            synchronized (ImageUpload.class){
                if (mImageUpload == null) mImageUpload = new ImageUpload();
            }
        }
        return mImageUpload;
    }

    private ImageUpload(){
    }

    public void uploadPicture(@NonNull List<AlbumImageBean> beans, @NonNull ImageUpLoadCallback callback){
        initUpload(beans, callback);
        if (size == 0) {
            onUploadFail("没有数据");
            return;
        }
        for (AlbumImageBean bean : beans){
            if (TextUtils.isEmpty(bean.getPath())) {
                uploadImage(bean);
            } else {
                setUrlInCache(bean);
            }
        }
        if (successCount == size) onUploadSuccess();
    }

    private void setUrlInCache(AlbumImageBean bean){
        String url = mCache.get(bean.getPath());
        if (TextUtils.isEmpty(url)){
            uploadImage(bean);
        } else {
            bean.setUrl(url);
            successCount++;
            completeCount++;
        }
    }


    private void uploadImage(final AlbumImageBean bean){
        Executors.newCachedThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                String base64;
                if (TextUtils.isEmpty(bean.getPath())){
                    base64 = ImageUtil.getImageBase64(bean.getBitmap());
                } else {
                    base64 = ImageUtil.getImageBase64(bean.getPath());
                }
                if (TextUtils.isEmpty(base64)) return;
                ImageUploadBean uploadBean = new ImageUploadBean(base64);
                HttpRequestParam param = new HttpRequestParam(mImageUploadAPI
                        , NetworkTransmissionDefine.HttpMethod.POST);
                param.addBody(new Gson().toJson(uploadBean));
                HuihuHttpUtils.httpRequest(param, new HuihuCallBack() {
                    @Override
                    public void onSuccess(ReturnModel returnModel) {
                        bean.setUrl(returnModel.getBodyMessage());
                        if (!TextUtils.isEmpty(bean.getPath())) mCache.put(bean.getPath(), returnModel.getBodyMessage());
                        successCount++;
                    }

                    @Override
                    public void onError(int errCode, String strErrMsg) {
                        Log.d("wangjing", strErrMsg);
                    }

                    @Override
                    public void onCompleted() {
                        completeCount++;
                        if (completeCount == size){
                            if (completeCount == successCount) {
                                onUploadSuccess();
                            } else {
                                onUploadFail("上传图片失败，请重新上传");
                            }
                        }
                    }
                });
            }
        });
    }

    private void onUploadFail(String errorMsg){
        mCallback.onFail(errorMsg);
        onUploadComplete();
    }

    private void onUploadSuccess(){
        mCallback.onSuccess();
       onUploadComplete();
    }

    private void onUploadComplete(){
        mCallback = null;
        size = 0;
    }

    private void initUpload(@NonNull List beans, @NonNull ImageUpLoadCallback callback){
        size = beans.size();
        mCallback = callback;
        completeCount = 0;
        successCount = 0;
    }
}
