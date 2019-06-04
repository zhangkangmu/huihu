package com.huihu.uilib.customize;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.huihu.uilib.R;

/**
 * create by ouyangjianfeng on 2019/3/22
 * description: Material风格的Dialog样式
 */
public class LoadingDialog extends AlertDialog {

    private Context mContext;
    private AlertDialog mDialog;

    private boolean iscancelable;

    public LoadingDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    public LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.mContext = context;
        this.iscancelable = cancelable;
    }

    public void showDialog() {
        View dialogView = LayoutInflater.from(mContext).inflate(R.layout.uilib_loading_dialog, null);
        Builder builder = new Builder(mContext);
        builder.setView(dialogView);
        mDialog = builder.create();
        mDialog.setCancelable(iscancelable);
        mDialog.show();
    }

    public void dismissDialog() {
        mDialog.dismiss();
    }
}
