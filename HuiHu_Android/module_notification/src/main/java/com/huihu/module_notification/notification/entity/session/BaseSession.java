package com.huihu.module_notification.notification.entity.session;

import android.view.View;

import com.google.gson.Gson;
import com.huihu.module_notification.intdef.NoticeId;
import com.huihu.module_notification.notification.adapter.MessageAdapter;

/**
 * create by wangjing on 2019/3/21 0021
 * description:
 */
public abstract class BaseSession {

    public static BaseSession getSessionByType(@NoticeId int type, String body){
        BaseSession session = null;
        switch (type) {
            case NoticeId.ATTENTION:
                break;
            case NoticeId.CIRCLE_INVITE:
                session = new Gson().fromJson(body, GroupInvitationSession.class);
                break;
            case NoticeId.COMMENT:
                break;
            case NoticeId.REPLY_INVITE:
                session = new Gson().fromJson(body, AnswerInvitationSession.class);
                break;
            case NoticeId.REPLY:
                session = new Gson().fromJson(body, AnswerSession.class);
                break;
            case NoticeId.SYSTEM_MESSAGE:
                session = new Gson().fromJson(body, SystemMessageSession.class);
                break;
            case NoticeId.SYSTEM_NOTIFY:
                session = new Gson().fromJson(body, SystemNoticeSession.class);
                break;
            default:
                break;
        }
        return session;
    }


    public abstract void bindDataOnHolder(MessageAdapter.ContentViewHolder holder, String messageTitle);

    protected void changeHead(MessageAdapter.ContentViewHolder holder, boolean isSingle){
        if(isSingle){
            holder.ivHead.setVisibility(View.VISIBLE);
            holder.flFirstHead.setVisibility(View.GONE);
            holder.flSecondHead.setVisibility(View.GONE);
        } else {
            holder.ivHead.setVisibility(View.INVISIBLE);
            holder.flFirstHead.setVisibility(View.VISIBLE);
            holder.flSecondHead.setVisibility(View.VISIBLE);
        }
    }

}
