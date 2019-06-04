package com.huihu.uilib.commentedit.commenteditinterface;

import com.huihu.uilib.commentedit.entity.PublishCommentBean;
import com.huihu.uilib.album.entity.AlbumImageBean;

import java.util.List;

public interface ICommentEditModel {

    void p2mPublicComment(PublishCommentBean publishCommentBean);

    void p2mPostImageUtil(List<AlbumImageBean> list);

}
