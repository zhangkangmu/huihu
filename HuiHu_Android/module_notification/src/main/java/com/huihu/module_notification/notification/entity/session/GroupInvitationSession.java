package com.huihu.module_notification.notification.entity.session;

import com.huihu.module_notification.R;
import com.huihu.module_notification.notification.adapter.MessageAdapter;

/**
 * create by wangjing on 2019/4/3 0003
 * description:
 */
public class GroupInvitationSession extends BaseSession {


    /**
     * childType : 1
     * circleId : 105
     * circleName : 外汇交易圈
     * msg :
     * userId : 284003
     * userImage : https://fxchatimage.fx110.com/headdefault/38.jpg
     * userName : DIV848732
     */

    private int childType;
    private int circleId;
    private String circleName;
    private String msg;
    private int userId;
    private String userImage;
    private String userName;

    public int getChildType() {
        return childType;
    }

    public void setChildType(int childType) {
        this.childType = childType;
    }

    public int getCircleId() {
        return circleId;
    }

    public void setCircleId(int circleId) {
        this.circleId = circleId;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void bindDataOnHolder(MessageAdapter.ContentViewHolder holder, String messageTitle) {
        holder.tvMessage.setText(circleName);
        holder.tvTitle.setText("");
        holder.tvTitleExtra.setText(new StringBuilder().append(userName).append(' ').append(messageTitle).toString());
        holder.ivHead.setImageResource(R.mipmap.module_notification_icon_session_community);
        holder.redTip.setText("");
        changeHead(holder, true);
    }
}
