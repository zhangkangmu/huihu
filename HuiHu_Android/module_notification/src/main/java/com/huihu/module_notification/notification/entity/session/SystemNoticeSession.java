package com.huihu.module_notification.notification.entity.session;

import com.huihu.module_notification.R;
import com.huihu.module_notification.notification.adapter.MessageAdapter;

/**
 * create by wangjing on 2019/4/3 0003
 * description:
 */
public class SystemNoticeSession extends BaseSession {


    /**
     * childType : 1
     * msg : 参与新人调查 赢海南三亚5日游，更多好礼尽在汇乎,HI,这位新人，在茫茫人海中，你被抽到成为汇乎的第一批体验用户，请尽快填写我们的资料，你将获得对应的海南三亚五日游的机会，机会不多，不容错过
     * title : 新人调查通知（标题）
     * url :
     */

    private int childType;
    private String msg;
    private String title;
    private String url;

    public int getChildType() {
        return childType;
    }

    public void setChildType(int childType) {
        this.childType = childType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void bindDataOnHolder(MessageAdapter.ContentViewHolder holder, String messageTitle) {
        holder.tvMessage.setText(msg);
        holder.tvTitle.setText("");
        holder.tvTitleExtra.setText(R.string.module_notification_system_notification_title);
        holder.ivHead.setImageResource(R.mipmap.module_notification_icon_session_notice);
        holder.redTip.setText("");
        changeHead(holder, true);
    }
}
