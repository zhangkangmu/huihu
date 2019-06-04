package com.huihu.uilib.complaint.complaintinterface;

import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.complaint.entity.ComplaintBean;

import java.util.List;

public interface IComplaintModel {

    void p2mPostImage(List<AlbumImageBean> list);

    void p2mPostReport(ComplaintBean bean);

    void p2mGetReportTag();
}
