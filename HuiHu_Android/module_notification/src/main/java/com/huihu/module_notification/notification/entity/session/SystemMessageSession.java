package com.huihu.module_notification.notification.entity.session;

import com.huihu.module_notification.R;
import com.huihu.module_notification.notification.adapter.MessageAdapter;

/**
 * create by wangjing on 2019/4/3 0003
 * description:
 */
public class SystemMessageSession extends BaseSession {


    /**
     * childType : 1
     * image : https://gaimg.fx110.com/upload/images/glodcoinmall/2018/12/28/140006385.jpg
     * msg : 【打开提问】其中的小的波段，不必非常在意第二天的行情会怎样走，要学会有利则取，落袋而安，无利则逃，避开危险，防止突发事件
     * title : 外汇课堂 | 炒外汇如何进行短线操作？
     * url : https://www.baidu.com/
     */

    private int childType;
    private String image;
    private String msg;
    private String title;
    private String url;

    public int getChildType() {
        return childType;
    }

    public void setChildType(int childType) {
        this.childType = childType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
        holder.tvTitleExtra.setText(R.string.module_notification_system_message);
        holder.ivHead.setImageResource(R.mipmap.module_notification_icon_session_message);
        holder.redTip.setText("");
        changeHead(holder, true);
    }
}
