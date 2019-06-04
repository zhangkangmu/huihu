package com.huihu.module_mine.feedback.feedbackinterface;

import com.huihu.uilib.album.entity.AlbumImageBean;

import java.util.List;

public interface IFeedbackPresenter {
    void v2pUploadImage(List<AlbumImageBean> beans);
    void v2pPostFeedback(List<AlbumImageBean> imageBeans, String content, String contact);
    void m2pPostFeedbackSuccess();
    void m2pNetFail();
}
