package com.huihu.module_home.writeanswer.presenter;

import android.text.TextUtils;

import com.huihu.module_home.question.entity.PostDraftModel;
import com.huihu.module_home.writeanswer.entity.PostAnswerModel;
import com.huihu.module_home.writeanswer.model.ImpWriteAnswerModel;
import com.huihu.module_home.writeanswer.writeanswerinterface.IWriteAnswerModel;
import com.huihu.module_home.writeanswer.writeanswerinterface.IWriteAnswerPresenter;
import com.huihu.module_home.writeanswer.writeanswerinterface.IWriteAnswerView;
import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.util.imageupload.ImageUpLoadCallback;
import com.huihu.uilib.util.imageupload.ImageUpload;

import java.util.List;

public class ImpWriteAnswerPresenter implements IWriteAnswerPresenter {
    private final IWriteAnswerModel iWriteAnswerModel = new ImpWriteAnswerModel(this);
    private final IWriteAnswerView iWriteAnswerView;

    public ImpWriteAnswerPresenter(IWriteAnswerView iWriteAnswerView) {
        this.iWriteAnswerView = iWriteAnswerView;
    }

    @Override
    public void v2pCheckReleaseStatus(String text) {
        if (TextUtils.isEmpty(text.trim())) {
            iWriteAnswerView.p2vSetReleaseUnclick();
        } else {
            iWriteAnswerView.p2vSetReleaseClick();
        }
    }

    @Override
    public void v2pPostAnswer(PostAnswerModel model) {
        iWriteAnswerModel.p2mPostAnswer(model);
    }

    @Override
    public void m2pPostAnswerSuccess(String message) {
        iWriteAnswerView.p2vShowToast(message);
        iWriteAnswerView.p2vSetResult();
        iWriteAnswerView.p2vFinish();
    }

    @Override
    public void m2pPostAnswerFail(String message) {
        iWriteAnswerView.p2vShowToast(message);
    }

    @Override
    public void v2pLoadPic(List<AlbumImageBean> list) {
        ImageUpload.getInstance().uploadPicture(list, new ImageUpLoadCallback() {
            @Override
            public void onSuccess() {
                iWriteAnswerView.p2vDismissLoadDialog();
                iWriteAnswerView.p2vInsertImage();
            }

            @Override
            public void onFail(String errorMsg) {
                iWriteAnswerView.p2vShowToast(errorMsg);
            }
        });
    }

    @Override
    public void v2pCheckString(String html) {
        if (TextUtils.isEmpty(html.trim())) {
            iWriteAnswerView.p2vFinish();
        } else {
            iWriteAnswerView.p2vShowAlertDialog();
        }
    }

    @Override
    public void v2pPostDraft(PostDraftModel model) {
        iWriteAnswerModel.p2mPostDraft(model);
    }

    @Override
    public void m2pPostDiscussFail(String msg) {
        iWriteAnswerView.p2vShowToast(msg);
        iWriteAnswerView.p2vDismissLoadDialog();
    }

    @Override
    public void m2pPostDiscussSuccess(String message) {
        iWriteAnswerView.p2vShowToast(message);
        iWriteAnswerView.p2vDismissLoadDialog();
        iWriteAnswerView.p2vFinish();
    }
}
