package com.wangjing.module_push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.huihu.commonlib.shareData.ShareDataUtils;
import com.huihu.commonlib.task.SimpleTask;
import com.huihu.uilib.ShareDataKey;
import com.huihu.uilib.entity.NoticeCustomMessage;

import cn.jpush.android.api.JPushInterface;

/**
 * create by wangjing on 2019/4/16 0016
 * description:
 */
public class PushReceive extends BroadcastReceiver {

    public static final String TAG = "PushReceive";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String action = intent.getAction();
        switch (action){
            case JPushInterface.ACTION_REGISTRATION_ID:
                Push.logD(TAG, "JPush User registration is successful");
                break;
            case JPushInterface.ACTION_MESSAGE_RECEIVED:
                String message = bundle.getString(JPushInterface.EXTRA_EXTRA);
                processPushMessage(message);
                break;
            case JPushInterface.ACTION_NOTIFICATION_RECEIVED:
                String noticeExtra = bundle.getString(JPushInterface.EXTRA_EXTRA);
                processPushNoticeExtra(noticeExtra);
                break;
            case JPushInterface.ACTION_NOTIFICATION_OPENED:
                String clickExtra = bundle.getString(JPushInterface.EXTRA_EXTRA);
                processPushNoticeClick(clickExtra);
                break;
            default:
                Push.logD(TAG, "Unprocessed intent - " + intent.getAction());
                break;
        }
    }


    private void processPushMessage(final String message){
        Push.logD(TAG, "Message is " + message);
        if (TextUtils.isEmpty(message)) return;
        SimpleTask.runInBackground(new Runnable() {
            @Override
            public void run() {
                ShareDataUtils.getInstance().publish(ShareDataKey.MessageKey, message);
            }
        });
    }

    private void processPushNoticeExtra(String noticeExtra){
        Push.logD(TAG, "NoticeExtra is " + noticeExtra);
        if (TextUtils.isEmpty(noticeExtra)) return;
        SimpleTask.runInBackground(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    private void processPushNoticeClick(String clickExtra){
        Push.logD(TAG, "ClickExtra is " + clickExtra);
        if (TextUtils.isEmpty(clickExtra)) return;
        SimpleTask.runInBackground(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
