package com.huihu.module_home.question.presenter;

import android.text.TextUtils;

import com.huihu.module_home.question.entity.PostDiscussModel;
import com.huihu.module_home.question.entity.PostDraftModel;
import com.huihu.module_home.question.entity.TypeWrite;
import com.huihu.module_home.question.questioninterface.IQuestionModel;
import com.huihu.module_home.question.questioninterface.IQuestionPresenter;
import com.huihu.module_home.question.questioninterface.IQuestionView;
import com.huihu.module_home.question.model.ImpQuestionModel;
import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.util.imageupload.ImageUpLoadCallback;
import com.huihu.uilib.util.imageupload.ImageUpload;

import java.util.List;

public class ImpQuestionPresenter implements IQuestionPresenter {
    private final IQuestionModel iQuestionModel = new ImpQuestionModel(this);
    private final IQuestionView iQuestionView;
    private int mType;

    public ImpQuestionPresenter(IQuestionView iQuestionView) {
        this.iQuestionView = iQuestionView;
    }

    @Override
    public void v2pLoadPic(List<AlbumImageBean> list) {
        ImageUpload.getInstance().uploadPicture(list, new ImageUpLoadCallback() {
            @Override
            public void onSuccess() {
                iQuestionView.p2vDismissLoadDialog();
                iQuestionView.p2vInsertImage();
            }

            @Override
            public void onFail(String errorMsg) {
                iQuestionView.p2vShowToast(errorMsg);
            }
        });
    }

    @Override
    public void v2pCheckInput(String trim, int type) {
        mType = type;
        if (TextUtils.isEmpty(trim)) {//输入内容为空
            //不可点击下一步
            iQuestionView.p2vSetNextUnClick();
            iQuestionView.p2vSetRichEditGone();
            iQuestionView.p2vSetDescriptionGone();
        } else {//输入内容不为空
            //下一步可点击
            iQuestionView.p2vSetNextClick();
            //编辑器隐藏
            iQuestionView.p2vSetRichEditGone();
            //底部添加描述可见
            iQuestionView.p2vSetDescriptionVisiible();
            if (mType == TypeWrite.question) {
                //展示关联问题
                iQuestionView.p2vShowRelated();
            }
        }
    }

    @Override
    public void v2pPostDraft(PostDraftModel model) {
        iQuestionModel.p2mPostDraft(model);
    }

    @Override
    public void m2pPostDraftSuccess() {
        //取消dialog
        iQuestionView.p2vDismissLoadDialog();
        //pop当前fragment
        iQuestionView.p2vFinsih();
    }

    @Override
    public void m2pPostDraftFail() {
        //保存到本地
        iQuestionModel.p2mSaveToLocal();
        iQuestionView.p2vDismissLoadDialog();
        iQuestionView.p2vFinsih();
    }

    @Override
    public void v2pCheckString(String trim, String html) {
        if (TextUtils.isEmpty(trim) && TextUtils.isEmpty(html) || mType == TypeWrite.discuss) {
            iQuestionView.p2vFinsih();
        } else {
            iQuestionView.p2vShowAlertDialog();
        }
    }

    @Override
    public void v2pGoto(int typeWrite) {
        if (typeWrite == TypeWrite.question) {
            iQuestionView.p2vStartAddCategory();
        } else {
            iQuestionView.p2vPostDiscuss();
            iQuestionView.p2vShowLoadingDialog();
        }
    }

    @Override
    public void v2pPostDiscuss(PostDiscussModel model) {
        if (null != model) {
            iQuestionModel.p2mPostDiscuss(model);
        }
    }

    @Override
    public void m2pPostDiscussFail(String msg) {
        iQuestionView.p2vShowToast(msg);
        iQuestionView.p2vDismissLoadDialog();
    }

    @Override
    public void m2pPostDiscussSuccess(String message) {
        iQuestionView.p2vShowToast(message);
        iQuestionView.p2vDismissLoadDialog();
        iQuestionView.p2vFinsih();
    }
}
