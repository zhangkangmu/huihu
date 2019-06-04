package com.huihu.module_circle.createcircle.view.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.huihu.module_circle.R;

@SuppressLint("AppCompatCustomView")
public class DeleteContent_Edit extends EditText {

    private Context mContext;
    private Drawable deleteImg;



    public DeleteContent_Edit(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs);
    }

    public DeleteContent_Edit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init(attrs);
    }


    private void init(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.DeleteContent_Edit);
        deleteImg = typedArray.getDrawable(R.styleable.DeleteContent_Edit_deleteImg);
        typedArray.recycle();
        if (deleteImg != null){//判断是否添加了删除按钮
            addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    setDeleteImg();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

    }

    private void setDeleteImg() {//设置删除按钮，输入字符串大于1时显示
        if (length()< 1){
            setCompoundDrawablesWithIntrinsicBounds(null,null,null,null);
        }else {
            setCompoundDrawablesWithIntrinsicBounds(null,null,deleteImg,null);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (deleteImg != null && event.getAction() == MotionEvent.ACTION_UP){//对内容清空
            getText().clear();
        }
        return super.onTouchEvent(event);
    }
}
