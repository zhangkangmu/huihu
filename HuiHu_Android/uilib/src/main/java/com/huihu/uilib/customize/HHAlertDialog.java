package com.huihu.uilib.customize;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

/**
 * create by ouyangjianfeng on 2019/3/25
 * description:汇乎统一dialog
 */
public class HHAlertDialog {

    private Context mContext;
    private AlertDialog mDialog;
    private AlertDialog.Builder mBuilder;

    private String mButtonColor = "#1777e6";

    public HHAlertDialog(@NonNull Context context) {
        init(context);
    }


    private void init(Context context) {
        this.mContext = context;
        mBuilder = new AlertDialog.Builder(mContext);
    }

    public HHAlertDialog setTitle(String title) {
        mBuilder.setTitle(title);
        return this;
    }

    public HHAlertDialog setMessage(String message) {
        mBuilder.setMessage(message);
        return this;
    }

    public HHAlertDialog setPositiveButton(String text, DialogInterface.OnClickListener onClickListener) {
        mBuilder.setPositiveButton(text, onClickListener);
        return this;
    }

    public HHAlertDialog setNegativeButton(String text, DialogInterface.OnClickListener onClickListener) {
        mBuilder.setNegativeButton(text, onClickListener);
        return this;
    }

    public void showDialog() {
        mDialog = mBuilder.create();
        mDialog.show();
        mDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor(mButtonColor));
        mDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.parseColor(mButtonColor));
    }

    public void dismissDialog() {
        mDialog.dismiss();
    }

}
