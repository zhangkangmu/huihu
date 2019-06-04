package com.huihu.module_notification.pushsetting.entity;

import java.util.List;

/**
 * create by wangjing on 2019/3/20 0020
 * description:
 */
public class PostPusSettingBean {


    /**
     * list : [{"noticeId":0,"status":0}]
     * uid : 0
     */

    private long uid;
    private List<ListBean> list;

    public PostPusSettingBean(long uid, List<ListBean> list) {
        this.uid = uid;
        this.list = list;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * noticeId : 0
         * status : 0
         */

        private int noticeId;
        private int status;

        public ListBean(int noticeId, int status) {
            this.noticeId = noticeId;
            this.status = status;
        }

        public int getNoticeId() {
            return noticeId;
        }

        public void setNoticeId(int noticeId) {
            this.noticeId = noticeId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
