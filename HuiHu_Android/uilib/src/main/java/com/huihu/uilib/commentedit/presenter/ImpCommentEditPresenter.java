package com.huihu.uilib.commentedit.presenter;

import com.huihu.uilib.commentedit.commenteditinterface.ICommentEditModel;
import com.huihu.uilib.commentedit.commenteditinterface.ICommentEditPresenter;
import com.huihu.uilib.commentedit.commenteditinterface.ICommentEditView;
import com.huihu.uilib.commentedit.entity.PublishCommentBean;
import com.huihu.uilib.commentedit.entity.ReturnCommentBean;
import com.huihu.uilib.commentedit.model.ImpCommentEditModel;
import com.huihu.uilib.album.entity.AlbumImageBean;

import java.util.List;

public class ImpCommentEditPresenter implements ICommentEditPresenter {
    private final ICommentEditModel iCommentEditModel = new ImpCommentEditModel(this);
    private final ICommentEditView iCommentEditView;

    public ImpCommentEditPresenter(ICommentEditView iCommentEditView) {
        this.iCommentEditView = iCommentEditView;
    }

    @Override
    public void v2pPublicComment(PublishCommentBean publishCommentBean) {
        iCommentEditModel.p2mPublicComment(publishCommentBean);
    }

    @Override
    public void m2pPublicCommentSuccess(ReturnCommentBean bean) {
        iCommentEditView.p2vPublicCommentSuccess(bean);
    }

    @Override
    public void m2pPublicCommentError(String error) {
        iCommentEditView.p2vPublicCommentError(error);
    }

    @Override
    public void m2pPublicCommentCompleted() {
        iCommentEditView.p2vPublicCommentCompleted();
    }


    @Override
    public void v2pPostCommentImage(List<AlbumImageBean> list) {
        iCommentEditModel.p2mPostImageUtil(list);
    }

    @Override
    public void m2pPostCommentImageSuccess(List<AlbumImageBean> list) {
        iCommentEditView.p2vPostCommentImageSuccess(list);
    }

    @Override
    public void m2pPostCommentImageFail(String fail) {
        iCommentEditView.p2vPostCommentImageFail(fail);
    }


}
