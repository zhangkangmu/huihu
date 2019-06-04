package com.huihu.uilib.commentedit.commenteditinterface;

import com.huihu.uilib.commentedit.entity.PublishCommentBean;
import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.commentedit.entity.ReturnCommentBean;

import java.util.List;

public interface ICommentEditPresenter {

    void v2pPublicComment(PublishCommentBean publishCommentBean);
    void m2pPublicCommentSuccess(ReturnCommentBean bean);
    void m2pPublicCommentError(String error);
    void m2pPublicCommentCompleted();

    void v2pPostCommentImage(List<AlbumImageBean> list);
    void m2pPostCommentImageSuccess(List<AlbumImageBean> list);
    void m2pPostCommentImageFail(String fail);
}
