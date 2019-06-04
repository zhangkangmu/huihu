package com.huihu.module_notification.notification.entity.session;

import com.huihu.module_notification.R;
import com.huihu.module_notification.notification.adapter.MessageAdapter;

/**
 * create by wangjing on 2019/4/3 0003
 * description:
 */
public class AnswerInvitationSession extends BaseSession {


    /**
     * childType : 1
     * ideaId : 105
     * ideaTitle : 问题是XXX
     * msg :
     * nickName : 灌灌灌
     * userId : 284002
     * userImage : https://fxchatimage.fx110.com/headdefault/34.jpg
     */

    private int childType;
    private long ideaId;
    private String ideaTitle;
    private String msg;
    private String nickName;
    private long userId;
    private String userImage;

    public int getChildType() {
        return childType;
    }

    public void setChildType(int childType) {
        this.childType = childType;
    }

    public long getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(long ideaId) {
        this.ideaId = ideaId;
    }

    public String getIdeaTitle() {
        return ideaTitle;
    }

    public void setIdeaTitle(String ideaTitle) {
        this.ideaTitle = ideaTitle;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    @Override
    public void bindDataOnHolder(MessageAdapter.ContentViewHolder holder, String messageTitle) {
        holder.tvMessage.setText(ideaTitle);
        holder.tvTitle.setText("");
        holder.tvTitleExtra.setText(new StringBuilder().append(nickName).append(' ').append(messageTitle).toString());
        holder.ivHead.setImageResource(R.mipmap.module_notification_icon_session_answer);
        holder.redTip.setText("");
        changeHead(holder, true);
    }
}
