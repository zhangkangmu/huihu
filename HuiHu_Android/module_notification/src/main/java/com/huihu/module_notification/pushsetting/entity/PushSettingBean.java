package com.huihu.module_notification.pushsetting.entity;

import com.huihu.module_notification.intdef.NoticeId;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * create by wangjing on 2019/3/19 0019
 * description:
 */
@Table(name = "PUSH_SETTING")
public class PushSettingBean {

    /**
     * noticeId : 0
     * noticeName : string
     * status : 0
     */
    @Column(name = "noticeId", isId = true, autoGen = false)
    private @NoticeId int noticeId;
    @Column(name = "noticeName")
    private String noticeName;
    @Column(name = "status")
    private int status;

    public PushSettingBean(int noticeId, String noticeName, int status) {
        this.noticeId = noticeId;
        this.noticeName = noticeName;
        this.status = status;
    }

    public PushSettingBean() {
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(@NoticeId int noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeName() {
        return noticeName;
    }

    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
