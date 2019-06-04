package com.huihu.uilib.complaint.entity;

public class ReportTag {

    private String rtDesc;
    private long reportTagId;
    private int tagSelected;

    public String getRtDesc() {
        return rtDesc;
    }

    public void setRtDesc(String rtDesc) {
        this.rtDesc = rtDesc;
    }

    public long getReportTagId() {
        return reportTagId;
    }

    public void setReportTagId(long reportTagId) {
        this.reportTagId = reportTagId;
    }

    public int getTagSelected() {
        return tagSelected;
    }

    public void setTagSelected(int tagSelected) {
        this.tagSelected = tagSelected;
    }
}
