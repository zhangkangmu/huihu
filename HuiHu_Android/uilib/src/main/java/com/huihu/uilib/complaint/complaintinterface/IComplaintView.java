package com.huihu.uilib.complaint.complaintinterface;

import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.complaint.entity.ReportTag;

import java.util.List;

public interface IComplaintView {

    void p2vPostImageSuccess(List<AlbumImageBean> list);
    void p2vPostImageFail(String error);

    void p2vPostReportSuccess();
    void p2vPostReportError(String error);

    void p2vGetReportTagSuccess(List<ReportTag> tags);
    void p2vGetReportTagError(String error);
}
