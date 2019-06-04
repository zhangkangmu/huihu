package com.huihu.commonlib.shareData;


import com.huihu.commonlib.callback.ShareDataChangedCallback;

import java.util.HashMap;

/*************************************************
 * Copyright (C), 2017-2021, Bailun Tech. Co., Ltd.
 * File name: BaseActivity.java
 * Author: kingpang   Version: v1.0   Date: 2017/10/18
 * Description: null
 *              1、...
 * Other: null
 * Function List: null
 * History:
 *          kingpang 2017/10/18 v1.0 Create 
 *************************************************/
public class ShareDataSubscriber {

    private HashMap<String, ShareDataChangedCallback> m_CallbackMap = new HashMap<>();

    public void add(String strSubscriber, ShareDataChangedCallback callback) {
        m_CallbackMap.put(strSubscriber, callback);
    }

    public void remove(String strSubscriber) {
        m_CallbackMap.remove(strSubscriber);
    }

    public void publish(Object obj) {
        for (String key : m_CallbackMap.keySet()) {
            //可以弱引用Context，然后再用判断是否已经销毁了
            m_CallbackMap.get(key).update(obj);
        }
    }
}
