package com.huihu.uilib.commentedit.commenteditinterface;

import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.commentedit.entity.ReturnCommentBean;

import java.util.List;

public interface ICommentEditView {
    void p2vPublicCommentSuccess(ReturnCommentBean bean);
    void p2vPublicCommentError(String error);
    void p2vPublicCommentCompleted();

    void p2vPostCommentImageSuccess(List<AlbumImageBean> list);
    void p2vPostCommentImageFail(String fail);
}
