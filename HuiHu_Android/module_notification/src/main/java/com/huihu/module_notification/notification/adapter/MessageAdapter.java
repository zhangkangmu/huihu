package com.huihu.module_notification.notification.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.intdef.NoticeId;
import com.huihu.module_notification.notification.entity.MessageSession;
import com.huihu.module_notification.notification.notificationinterface.INotificationPresenter;
import com.huihu.uilib.customize.RedPointView;
import com.huihu.uilib.customize.RoundImageView;
import com.huihu.uilib.def.NetDataBoolean;
import com.huihu.uilib.util.TimeFormatUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * create by wangjing on 2019/2/28 0028
 * description:
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ContentViewHolder> {


    private INotificationPresenter iNotificationPresenter;

    private View.OnClickListener itemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getTag() instanceof MessageSession) {
                MessageSession messageSession = (MessageSession) v.getTag();
                iNotificationPresenter.v2pStartOther(messageSession);
                iNotificationPresenter.v2pChangeMessageState(messageSession);
            }
        }
    };
    private View.OnLongClickListener deleteListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (v.getTag() instanceof MessageSession) {
                MessageSession messageSession = (MessageSession) v.getTag();
                iNotificationPresenter.v2pMenu(messageSession, v);
            }
            return true;
        }
    };

    private List<MessageSession> sessionList;

    public MessageAdapter(@NonNull INotificationPresenter iNotificationPresenter) {
        this.iNotificationPresenter = iNotificationPresenter;
    }

    public List<MessageSession> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<MessageSession> sessionList) {
        this.sessionList = sessionList;
    }

    @NonNull
    @Override
    public ContentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.module_notification_item_notification_message, viewGroup, false);
        return new ContentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContentViewHolder holder, int i) {
        MessageSession session = sessionList.get(i);
        if (session.getInfo() != null) {
            session.getInfo().bindDataOnHolder(holder, session.getTitle());
        }
        TextViewUtils.setTextFakeBold(holder.tvTitle);
        TextViewUtils.setTextFakeBold(holder.tvTitleExtra);
        holder.redTip.setVisibility(session.getMessageStatus() == NetDataBoolean.NET_FALSE ? View.VISIBLE : View.INVISIBLE);
        holder.tvTime.setText(TimeFormatUtils.toHuihuTime(session.getSendTimestamp()));
        holder.clItem.setOnClickListener(itemListener);
        holder.clItem.setTag(session);
        if (session.getNoticeId() == NoticeId.SYSTEM_MESSAGE || session.getNoticeId() == NoticeId.SYSTEM_NOTIFY) {
            holder.tvOfficial.setVisibility(View.VISIBLE);
        } else {
            holder.tvOfficial.setVisibility(View.GONE);
        }
        holder.clItem.setOnLongClickListener(deleteListener);
    }

    @Override
    public int getItemCount() {
        return sessionList == null ? 0 : sessionList.size();
    }


    public static class ContentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.cl_item)
        public ConstraintLayout clItem;
        @BindView(R2.id.iv_head)
        public RoundImageView ivHead;
        @BindView(R2.id.tv_title)
        public TextView tvTitle;
        @BindView(R2.id.tv_message)
        public TextView tvMessage;
        @BindView(R2.id.tv_time)
        public TextView tvTime;
        @BindView(R2.id.red_tip)
        public RedPointView redTip;
        @BindView(R2.id.tv_official)
        public TextView tvOfficial;
        @BindView(R2.id.iv_first_head)
        public RoundImageView ivFirstHead;
        @BindView(R2.id.iv_second_head)
        public RoundImageView ivSecondHead;
        @BindView(R2.id.tv_title_extra)
        public TextView tvTitleExtra;
        @BindView(R2.id.fl_first_head)
        public FrameLayout flFirstHead;
        @BindView(R2.id.fl_second_head)
        public FrameLayout flSecondHead;
        @BindView(R2.id.iv_mark)
        public ImageView ivMark;

        ContentViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
