package com.huihu.commonlib.shareData;

import android.util.Log;

import com.huihu.commonlib.callback.ShareDataChangedCallback;

import java.util.HashMap;

/*************************************************
 * Copyright (C), 2017-2021, Bailun Tech. Co., Ltd.
 * File name: ShareDataUtils.java
 * Author: kingpang   Version: v1.0   Date: 2017/9/28
 * Description: 共享数据源的操作
 *              将组件间需要公用的数据对象，或保持至此，或通过这个方式发布给需要调用的组件
 * History:
 *          kingpang 2017/9/28 v1.0 Create 
 *************************************************/
public class ShareDataUtils {
    private static ShareDataUtils m_Instance = null;
    private static HashMap<String, Object> m_ObjData = null;
    private static HashMap<String, ShareDataSubscriber> m_SubscriberMap = null;

    public static ShareDataUtils getInstance() {
        if (null == m_Instance) {
            synchronized (ShareDataUtils.class) {
                if (null == m_Instance) {
                    m_Instance = new ShareDataUtils();
                }
            }
        }

        return m_Instance;
    }

    public void init() {
        if (null == m_ObjData) {
            m_ObjData = new HashMap<>();
        }

        if (m_ObjData != null) {
            m_ObjData.clear();
        }

        if (null == m_SubscriberMap) {
            m_SubscriberMap = new HashMap<>();
        }

        if (m_SubscriberMap != null) {
            m_SubscriberMap.clear();
        }
    }

    //简单地写入一个共享对象
    public void write(String strKey, Object obj) {
        //map.put本身是覆盖写，如果需要拓展成判断存在就不覆盖写，需要多做处理
        m_ObjData.put(strKey, obj);
    }

    //简单地读取某一个共享对象
    public Object read(String strKey) {
        return m_ObjData.get(strKey);
    }

    private void setSubscriber(String strKey, ShareDataSubscriber subscriber) {
        m_SubscriberMap.put(strKey, subscriber);
    }

    private ShareDataSubscriber getSubscriber(String strKey) {
        return m_SubscriberMap.get(strKey);
    }

    //发布：通知所有订阅者 - 共享数据的变化
    public void publish(String strKey) {
        Object obj = read(strKey);
        publish(strKey, obj);
    }

    //发布：通知所有订阅者 - 可以是组件之间直接提供的数据对象，不保存在Common中的
    public void publish(String strKey, Object obj) {
        if (obj != null) {
            ShareDataSubscriber subscriber = getSubscriber(strKey);
            if (subscriber != null) {
                subscriber.publish(obj);
            }
        }
    }

    //订阅
    public void subscribe(String strKey, String strSubscriber, ShareDataChangedCallback changedCallback) {
        ShareDataSubscriber subscriber = getSubscriber(strKey);
        if (null == subscriber) {
            subscriber = new ShareDataSubscriber();
            setSubscriber(strKey, subscriber);
        }

        if (null != subscriber) {
            Log.e("@@@", "key[" + strKey + "] add subscriber:" + strSubscriber);
            subscriber.add(strSubscriber, changedCallback);
        }
    }

    //取消某个订阅
    public void unSubscribe(String strKey, String strSubscriber) {
        ShareDataSubscriber subscriber = getSubscriber(strKey);
        if (null != subscriber) {
            subscriber.remove(strSubscriber);
        }
    }

    //取消strSubscriber的所有订阅
    public void unSubscribe(String strSubscriber) {
        for (String key : m_SubscriberMap.keySet()) {
            m_SubscriberMap.get(key).remove(strSubscriber);
        }
    }

}
