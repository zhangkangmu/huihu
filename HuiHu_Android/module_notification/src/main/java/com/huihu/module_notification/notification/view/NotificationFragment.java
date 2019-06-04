package com.huihu.module_notification.notification.view;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouterClassRegister;
import com.huihu.commonlib.simpleRouter.SimpleRouterObj;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.circleinvitation.view.CircleInvitationFragment;
import com.huihu.module_notification.comment.view.CommentFragment;
import com.huihu.module_notification.fans.view.FansFragment;
import com.huihu.module_notification.intdef.NoticeId;
import com.huihu.module_notification.like.view.LikeFragment;
import com.huihu.module_notification.newreply.view.NewReplyFragment;
import com.huihu.module_notification.notification.adapter.MessageAdapter;
import com.huihu.module_notification.notification.entity.MessageSession;
import com.huihu.module_notification.notification.entity.session.AnswerSession;
import com.huihu.module_notification.notification.notificationinterface.INotificationPresenter;
import com.huihu.module_notification.notification.notificationinterface.INotificationView;
import com.huihu.module_notification.notification.presenter.ImpNotificationPresenter;
import com.huihu.module_notification.pushsetting.view.PushSettingFragment;
import com.huihu.module_notification.reply.entity.AnswerReadState;
import com.huihu.module_notification.reply.view.ReplyFragment;
import com.huihu.module_notification.replyinvitation.view.ReplyInvitationFragment;
import com.huihu.module_notification.systemmessage.view.SystemMessageFragment;
import com.huihu.module_notification.systemnotification.view.SystemNotificationFragment;
import com.huihu.module_notification.util.RouterUtil;
import com.huihu.uilib.PointListener;
import com.huihu.uilib.PointUtil;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.checklogin.annotation.CheckLogin;
import com.huihu.uilib.customize.RedPointView;
import com.huihu.uilib.customize.popwindow.HuihuPopwindow;
import com.huihu.uilib.def.NetDataBoolean;
import com.huihu.uilib.evenbusBean.NotificationRefresh;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

@SimpleRouterClassRegister(key = RouterReDefine.NOTIFICATION_FRAGMENT, type = SimpleRouterObj.FRAGMENT)
public class NotificationFragment extends BaseFragment implements INotificationView, PointListener {

    private final INotificationPresenter iNotificationPresenter = new ImpNotificationPresenter(this);

    Unbinder unbinder;
    @BindView(R2.id.rv_message)
    RecyclerView rvMessage;
    @BindView(R2.id.red_point_fans)
    RedPointView redPointFans;
    @BindView(R2.id.red_point_like)
    RedPointView redPointLike;
    @BindView(R2.id.red_point_reply)
    RedPointView redPointReply;
    @BindView(R2.id.red_point_comment)
    RedPointView redPointComment;

    private MessageAdapter mAdapter;
    private int x, y;

    public static final int FANS_FRAGMENT = 10001;
    public static final int LIKE_ME_FRAGMENT = 10002;
    public static final int OTHER_COMMENT_ME_FRAGMENT = 10003;
    public static final int REPLY_FRAGMENT = 10004;
    public static final int NOTICE_SETTING = 10005;



    public static NotificationFragment newInstance() {
        Bundle args = new Bundle();
        NotificationFragment fragment = new NotificationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        iNotificationPresenter.v2pSubscribeNoticeMessage();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.module_notification_fragment_notification, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMessage.setLayoutManager(layoutManager);
        mAdapter = new MessageAdapter(iNotificationPresenter);
        rvMessage.setAdapter(mAdapter);
        iNotificationPresenter.v2pGetListData();
        if (_mActivity instanceof PointUtil){
            PointUtil util = (PointUtil) _mActivity;
            util.registerPointListener(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (_mActivity instanceof PointUtil){
            PointUtil util = (PointUtil) _mActivity;
            util.unRegisterPointListener();
        }
        iNotificationPresenter.v2pUnSubscribeNoticeMessage();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshUI(NotificationRefresh notificationRefresh){
        iNotificationPresenter.v2pGetListData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeMessageSessionState(AnswerReadState state){
        if (state.isAnswer()){
            refreshAnswerRead(state);
        } else {
            int position = 0;
            for (MessageSession session : mAdapter.getSessionList()){
                if (session.getNoticeId() == NoticeId.REPLY_INVITE){
                    session.setMessageStatus(NetDataBoolean.NET_TRUE);
                    mAdapter.notifyItemChanged(position);
                    break;
                }
                position++;
            }
        }
    }

    @OnClick({R2.id.cl_right, R2.id.iv_fans, R2.id.iv_like, R2.id.iv_comment, R2.id.iv_reply
            , R2.id.tv_all_read})
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.cl_right) {
            iNotificationPresenter.v2pStartOther(NOTICE_SETTING);
        } else if (id == R.id.iv_fans) {
            iNotificationPresenter.v2pStartOther(FANS_FRAGMENT);
            redPointFans.setVisibility(View.GONE);
        } else if (id == R.id.iv_like) {
            iNotificationPresenter.v2pStartOther(LIKE_ME_FRAGMENT);
            redPointLike.setVisibility(View.GONE);
        } else if (id == R.id.iv_comment) {
            iNotificationPresenter.v2pStartOther(OTHER_COMMENT_ME_FRAGMENT);
            redPointComment.setVisibility(View.GONE);
        } else if (id == R.id.iv_reply) {
            iNotificationPresenter.v2pStartOther(REPLY_FRAGMENT);
        } else if (id == R.id.tv_all_read){
            iNotificationPresenter.v2pAllRead();
        }
    }

    @Override
    public void p2vStartOther(int otherId) {
        SupportFragment parentFragment = (SupportFragment) getParentFragment();
        switch (otherId) {
            case NOTICE_SETTING:
                parentFragment.start(PushSettingFragment.newInstance());
                break;
            case NoticeId.ATTENTION:
                break;
            case NoticeId.CIRCLE_INVITE:
                parentFragment.start(CircleInvitationFragment.newInstance());
                break;
            case NoticeId.COMMENT:
                break;
            case NoticeId.REPLY_INVITE:
                parentFragment.start(ReplyInvitationFragment.newInstance());
                break;
            case NoticeId.REPLY:
                break;
            case NoticeId.SYSTEM_MESSAGE:
                parentFragment.start(SystemMessageFragment.newInstance());
                break;
            case NoticeId.SYSTEM_NOTIFY:
                parentFragment.start(SystemNotificationFragment.newInstance());
                break;
            default:
                openFragmentNeedLogin(otherId, parentFragment);
                break;
        }
    }

    @Override
    public void p2vStartNewAnswer(int state, long questionId) {
        SupportFragment parentFragment = (SupportFragment) getParentFragment();
        parentFragment.start(NewReplyFragment.newInstance(state, questionId));
    }

    @Override
    public void p2vStartDetailAnswer(long ideaId) {
        SupportFragment parentFragment = (SupportFragment) getParentFragment();
        RouterUtil.startAnswerDetail(parentFragment, ideaId);
    }

    @Override
    public void p2cChangeUnRead(MessageSession session) {
        int i = mAdapter.getSessionList().indexOf(session);
        mAdapter.notifyItemChanged(i);
    }

    @Override
    public void p2vShowListData(List<MessageSession> sessionList) {
        mAdapter.setSessionList(sessionList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void p2vShowTopState(List<MessageSession> sessionList) {
        for (MessageSession session : sessionList) {
            int visibility = session.getMessageStatus() == NetDataBoolean.NET_FALSE ? View.VISIBLE : View.INVISIBLE;
            switch (session.getNoticeId()) {
                case NoticeId.COMMENT:
                    redPointComment.setVisibility(visibility);
                    break;
                case NoticeId.LIKE_ME:
                    redPointLike.setVisibility(visibility);
                    break;
                case NoticeId.ATTENTION:
                    redPointLike.setVisibility(visibility);
                    break;
                case NoticeId.REPLY:
                    redPointReply.setVisibility(visibility);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void p2vChangeAllUnRead() {
        List<MessageSession>  messageSessions = mAdapter.getSessionList();
        for (MessageSession session : messageSessions){
            session.setMessageStatus(NetDataBoolean.NET_TRUE);
        }
        redPointComment.setVisibility(View.GONE);
        redPointLike.setVisibility(View.GONE);
        redPointFans.setVisibility(View.GONE);
        redPointReply.setVisibility(View.GONE);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void p2vShowDeleteMenu(final MessageSession session, View view) {
        HuihuPopwindow popwindow = new HuihuPopwindow(getContext(), view);
        popwindow.items(getString(R.string.module_notification_delete));
        popwindow.show(new Point(x, y));
        popwindow.setOnItemClickListener(new HuihuPopwindow.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                iNotificationPresenter.v2pDeleteSession(session);
            }
        });
    }



    @Override
    public void p2vDeleteSession(MessageSession session) {
        int position = mAdapter.getSessionList().indexOf(session);
        mAdapter.getSessionList().remove(session);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void p2vReceiveNoticeMessage(MessageSession session) {

    }

    @CheckLogin
    private void openFragmentNeedLogin(int otherId, SupportFragment fragment) {
        switch (otherId) {
            case FANS_FRAGMENT:
                fragment.start(FansFragment.newInstance());
                break;
            case LIKE_ME_FRAGMENT:
                fragment.start(LikeFragment.newInstance());
                break;
            case OTHER_COMMENT_ME_FRAGMENT:
                fragment.start(CommentFragment.newInstance());
                break;
            case REPLY_FRAGMENT:
                fragment.start(ReplyFragment.newInstance());
                break;
        }
    }

    @Override
    public void onTouch(float x, float y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    private void refreshAnswerRead(AnswerReadState state){
        redPointReply.setVisibility(state.isAllAnswerRead() ? View.GONE : View.VISIBLE);
        int position = 0;
        for (MessageSession session : mAdapter.getSessionList()){
            if (session.getNoticeId() == NoticeId.REPLY && session.getAboutId().equals(String.valueOf(state.getQuestionId()))){
                AnswerSession answerSession = (AnswerSession) session.getInfo();
                if (state.getQuestionUnReadCount() == 0) {
                    session.setMessageStatus(NetDataBoolean.NET_TRUE);
                } else {
                    session.setMessageStatus(NetDataBoolean.NET_FALSE);
                    answerSession.setSize(state.getQuestionUnReadCount());
                }
                mAdapter.notifyItemChanged(position);
                break;
            }
            position++;
        }
    }
}
