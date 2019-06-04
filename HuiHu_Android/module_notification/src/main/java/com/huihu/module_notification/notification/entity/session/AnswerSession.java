package com.huihu.module_notification.notification.entity.session;

import android.content.Context;

import com.huihu.uilib.util.ImgTools;
import com.huihu.module_notification.R;
import com.huihu.module_notification.notification.adapter.MessageAdapter;
import com.huihu.uilib.util.MarkUtil;

import java.util.List;

/**
 * create by wangjing on 2019/4/3 0003
 * description:
 */
public class AnswerSession extends BaseSession {


    /**
     * answerList : [{"answerId":235,"nickName":"LTZ783809","userId":284039,"userImage":"https://fxchatimage.fx110.com/headdefault/45.jpg"}]
     * childType : 1
     * ideaId : 107
     * ideaTitle : null
     * msg : null
     */

    private int childType;
    private long ideaId;
    private String ideaTitle;
    private String msg;
    private int size;
    private List<AnswerUser> answerList;
    private String incMin;

    public String getIncMin() {
        return incMin;
    }

    public void setIncMin(String incMin) {
        this.incMin = incMin;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

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

    public List<AnswerUser> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerUser> answerList) {
        this.answerList = answerList;
    }

    @Override
    public void bindDataOnHolder(MessageAdapter.ContentViewHolder holder, String messageTitle) {
        Context context = holder.itemView.getContext();
        StringBuilder titleBuilder = new StringBuilder();
        StringBuilder extraTitle = new StringBuilder();
        if (answerList != null && answerList.size() > 0){
            if (answerList.size() == 1){
                changeHead(holder, true);
                AnswerUser user = answerList.get(0);
                extraTitle.append(user.getNickName()).append(' ');
                ImgTools.showImageView(context, user.getUserImage(), holder.ivHead);
                MarkUtil.markPerson(context, incMin, holder.ivMark);
            } else {
                changeHead(holder, false);
                AnswerUser firstUser = answerList.get(0);
                AnswerUser secondUser = answerList.get(1);
                titleBuilder.append(firstUser.getNickName()).append('、').append(secondUser.getNickName());
                extraTitle.append(String.format(context.getString(R.string.module_notification_more_people), size));
                ImgTools.showImageView(context, firstUser.getUserImage(), holder.ivFirstHead);
                ImgTools.showImageView(context, secondUser.getUserImage(), holder.ivSecondHead);
            }
            extraTitle.append(context.getString(R.string.module_notification_reply_question));
            holder.tvTitle.setText(titleBuilder.toString());
            holder.tvMessage.setText(ideaTitle);
            holder.tvTitleExtra.setText(extraTitle.toString());
            holder.redTip.setText(String.valueOf(size));
        }
    }

    public static class AnswerUser {
        /**
         * answerId : 235
         * nickName : LTZ783809
         * userId : 284039
         * userImage : https://fxchatimage.fx110.com/headdefault/45.jpg
         */

        private int answerId;
        private String nickName;
        private int userId;
        private String userImage;

        public int getAnswerId() {
            return answerId;
        }

        public void setAnswerId(int answerId) {
            this.answerId = answerId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
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
    }
}
