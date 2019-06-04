package com.huihu.module_home.menu;

import android.content.Context;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.view.ViewGroup;

import com.huihu.uilib.util.DensityUtil;

/**
 * create by wangjing on 2019/4/2 0002
 * description:
 */
public class HuihuMenu {

    public static void showMenu(Context context, String[] items, int selectPosition, View topView, OnItemClickListener listener){
        initMenu(context, items, selectPosition, topView, listener).show();
    }

    public static void postShowMenu(Context context, String[] items, int selectPosition, View topView, OnItemClickListener listener){
        initMenu(context, items, selectPosition, topView, listener).postShow();
    }

    private static ListPopupWindow initMenu(Context context, String[] items, int selectPosition, View topView, OnItemClickListener listener){
        ListPopupWindow listPopupWindow = new ListPopupWindow(context);
        listPopupWindow.setWidth(DensityUtil.dip2px(context, 180));
        listPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        ListPopupWindowAdapter adapter = new ListPopupWindowAdapter(context, listener, listPopupWindow);
        adapter.setSelectPosition(selectPosition);
        adapter.setData(items);
        listPopupWindow.setAdapter(adapter);
        listPopupWindow.setAnchorView(topView);
        return listPopupWindow;
    }


    public interface OnItemClickListener{
        void onClick(int position, String text);
    }
}
