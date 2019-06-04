package com.huihu.uilib.complaint.complaintinterface;

import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.complaint.entity.ComplaintBean;
import com.huihu.uilib.complaint.entity.ReportTag;

import java.util.List;

public interface IComplaintPresenter {

    void v2pPostImage(List<AlbumImageBean> list);
    void m2pPostImageSuccess(List<AlbumImageBean> list);
    void m2pPostImageFail(String error);

    void v2pPostReport(ComplaintBean bean);
    void m2pPostReportSuccess();
    void m2pPostReportError(String error);

    void v2pGetReportTag();
    void m2pGetReportTagSuccess(List<ReportTag> tags);
    void m2pGetReportTagError(String error);
}
