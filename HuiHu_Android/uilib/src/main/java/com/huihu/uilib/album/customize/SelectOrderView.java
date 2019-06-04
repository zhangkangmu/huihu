package com.huihu.uilib.album.customize;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;

import com.huihu.uilib.R;
import com.huihu.uilib.util.DensityUtil;

/**
 * create by wangjing on 2019/3/15 0015
 * description:
 */
public class SelectOrderView extends android.support.v7.widget.AppCompatTextView {

    private Paint paint;
    private int strokeWidth;
    private int defColor;

    public SelectOrderView(Context context) {
        this(context, null);
    }

    public SelectOrderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
//        抗锯齿
        paint.setAntiAlias(true);
        strokeWidth = DensityUtil.dip2px(context, 2);
        defColor = context.getResources().getColor(R.color.global_blue);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        String text = getText().toString();
        int w = getMeasuredWidth();
        int h = getMeasuredHeight();
        int center = w / 2;
        if (text.equals("")) {
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(strokeWidth);
            canvas.drawArc(new RectF(strokeWidth, strokeWidth, w - strokeWidth, h - strokeWidth)
                    , 0, 360, true, paint);
        } else {
            paint.setColor(defColor);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(center, center, center, paint);
        }
        super.onDraw(canvas);
    }


}
