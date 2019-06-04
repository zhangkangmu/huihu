package com.huihu.uilib.complaint.presenter;

import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.complaint.complaintinterface.IComplaintModel;
import com.huihu.uilib.complaint.complaintinterface.IComplaintPresenter;
import com.huihu.uilib.complaint.complaintinterface.IComplaintView;
import com.huihu.uilib.complaint.entity.ComplaintBean;
import com.huihu.uilib.complaint.entity.ReportTag;
import com.huihu.uilib.complaint.model.ImpComplaintModel;

import java.util.List;

public class ImpComplaintPresenter implements IComplaintPresenter {
    private final IComplaintModel iComplaintModel = new ImpComplaintModel(this);
    private final IComplaintView iComplaintView;

    public ImpComplaintPresenter(IComplaintView iComplaintView) {
        this.iComplaintView = iComplaintView;
    }

    @Override
    public void v2pPostImage(List<AlbumImageBean> list) {
        iComplaintModel.p2mPostImage(list);
    }

    @Override
    public void m2pPostImageSuccess(List<AlbumImageBean> list) {
        iComplaintView.p2vPostImageSuccess(list);
    }

    @Override
    public void m2pPostImageFail(String error) {
        iComplaintView.p2vPostImageFail(error);
    }

    @Override
    public void v2pPostReport(ComplaintBean bean) {
        iComplaintModel.p2mPostReport(bean);
    }

    @Override
    public void m2pPostReportSuccess() {
        iComplaintView.p2vPostReportSuccess();
    }

    @Override
    public void m2pPostReportError(String error) {
        iComplaintView.p2vPostReportError(error);

    }

    @Override
    public void v2pGetReportTag() {
        iComplaintModel.p2mGetReportTag();
    }

    @Override
    public void m2pGetReportTagSuccess(List<ReportTag> tags) {
        iComplaintView.p2vGetReportTagSuccess(tags);
    }

    @Override
    public void m2pGetReportTagError(String error) {
        iComplaintView.p2vGetReportTagError(error);
    }
}
