package com.huihu.uilib.complaint.entity;

public class ComplaintBean {


    /**
     * deviceId : string
     * reportDesc : string
     * reportImgs : string
     * reportTagId : 0
     * reportTypeId : 0
     * reportUid : 0
     * uid : 0
     */

    private String deviceId;
    private String reportDesc;
    private String reportImgs;
    private String reportTagId;
    private long reportTypeId;
    private long reportUid;
    private long uid;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getReportDesc() {
        return reportDesc;
    }

    public void setReportDesc(String reportDesc) {
        this.reportDesc = reportDesc;
    }

    public String getReportImgs() {
        return reportImgs;
    }

    public void setReportImgs(String reportImgs) {
        this.reportImgs = reportImgs;
    }

    public String getReportTagId() {
        return reportTagId;
    }

    public void setReportTagId(String reportTagId) {
        this.reportTagId = reportTagId;
    }

    public long getReportTypeId() {
        return reportTypeId;
    }

    public void setReportTypeId(long reportTypeId) {
        this.reportTypeId = reportTypeId;
    }

    public long getReportUid() {
        return reportUid;
    }

    public void setReportUid(long reportUid) {
        this.reportUid = reportUid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }
}
