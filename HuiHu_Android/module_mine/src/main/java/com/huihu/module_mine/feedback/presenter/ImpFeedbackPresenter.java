package com.huihu.module_mine.feedback.presenter;

import android.os.Build;

import com.huihu.commonlib.utils.AppUtil;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.R;
import com.huihu.module_mine.feedback.entity.PostFeedbackBean;
import com.huihu.module_mine.feedback.feedbackinterface.IFeedbackModel;
import com.huihu.module_mine.feedback.feedbackinterface.IFeedbackPresenter;
import com.huihu.module_mine.feedback.feedbackinterface.IFeedbackView;
import com.huihu.module_mine.feedback.model.ImpFeedbackModel;
import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.util.imageupload.ImageUpLoadCallback;
import com.huihu.uilib.util.imageupload.ImageUpload;

import java.util.List;

import static com.huihu.commonlib.utils.Constant.COMMA;
import static com.huihu.commonlib.utils.Constant.TYPE_DEVICE;

public class ImpFeedbackPresenter implements IFeedbackPresenter {
    private final IFeedbackModel iFeedbackModel = new ImpFeedbackModel(this);
    private final IFeedbackView iFeedbackView;

    public ImpFeedbackPresenter(IFeedbackView iFeedbackView) {
        this.iFeedbackView = iFeedbackView;
    }

    @Override
    public void v2pUploadImage(List<AlbumImageBean> beans) {
        ImageUpload.getInstance().uploadPicture(beans, new ImageUpLoadCallback() {
            @Override
            public void onSuccess() {
                iFeedbackView.p2vUploadImageSuccess();
            }

            @Override
            public void onFail(String errorMsg) {
                ToastUtil.show(errorMsg);
            }
        });
    }

    @Override
    public void v2pPostFeedback(List<AlbumImageBean> imageBeans, String content, String contact) {
        PostFeedbackBean bean = new PostFeedbackBean();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < imageBeans.size(); i++){
            if (i == imageBeans.size() - 1){
                stringBuilder.append(imageBeans.get(i).getUrl());
            } else {
                stringBuilder.append(imageBeans.get(i).getUrl()).append(COMMA);
            }
        }
        bean.setContent(content);
        bean.setImgUrls(stringBuilder.toString());
        bean.setEquipmentType(TYPE_DEVICE);
        bean.setContact(contact);
        bean.setClientModel(Build.DEVICE);
        bean.setUserId(SPUtils.getInstance().getCurrentUid());
        bean.setVersion(AppUtil.getVersionName());
        iFeedbackModel.p2mPostFeedback(bean);
    }

    @Override
    public void m2pPostFeedbackSuccess() {
        iFeedbackView.p2vPostFeedbackSuccess();
    }

    @Override
    public void m2pNetFail() {
        ToastUtil.show(R.string.uilib_http_request_fail);
    }
}
