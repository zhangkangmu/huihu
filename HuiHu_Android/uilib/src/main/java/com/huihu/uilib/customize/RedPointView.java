package com.huihu.uilib.customize;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.huihu.uilib.util.DensityUtil;

/**
 * create by wangjing on 2019/2/28 0028
 * description:
 */
public class
RedPointView extends android.support.v7.widget.AppCompatTextView {

    /**
     * 默认值
     */
    private static final int DEF_SIZE_DIP = 8;
    private static final int DEF_PADDING_DIP = 3;
    private static final int DEF_TEXT_SIZE_SP = 10;
    private static final int DEF_BACKGROUND_COLOR = Color.parseColor("#F12C20");
    private static final int DEF_SIDE_DIP = 2;

    private Paint paint;
    private int round;

    public RedPointView(Context context) {
        this(context, null);
    }

    public RedPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        setTextSize(TypedValue.COMPLEX_UNIT_SP, DEF_TEXT_SIZE_SP);
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = getMeasuredWidth();
        int measureHeight = getMeasuredHeight();
        int padding;
        if (measureHeight == 0 || measureWidth == 0){
            int size = DensityUtil.dip2px(getContext(), DEF_SIZE_DIP + DEF_SIDE_DIP * 2);
            setMeasuredDimension(size, size);
        } else if (measureHeight >= measureWidth){
            padding = (measureHeight - measureWidth);
            setMeasuredDimension(measureWidth + padding, measureHeight);
        } else {
            padding = DensityUtil.dip2px(getContext(), DEF_PADDING_DIP) * 2;
            setMeasuredDimension(measureWidth + padding, measureHeight);
        }
        round = measureHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        String text = getText().toString();
        if (!TextUtils.isEmpty(text)){
            paint.setColor(DEF_BACKGROUND_COLOR);
            canvas.drawRoundRect(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()), round, round, paint);
            paint.setColor(Color.WHITE);
            paint.setTextSize(DensityUtil.sp2px(getContext(), DEF_TEXT_SIZE_SP));
            //文字的x轴坐标
            float stringWidth = paint.measureText(text);
            float x = (getWidth() - stringWidth) / 2;
            //文字的y轴坐标
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            float y = getHeight() / 2 + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2;
            canvas.drawText(text, x, y, paint);
        } else {
            int strokeWidth = DensityUtil.dip2px(getContext(), DEF_SIDE_DIP);
            int w = getMeasuredWidth(), h = getMeasuredHeight();
            int cW = w /2 , cH = h/2;
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(strokeWidth);
            canvas.drawArc(new RectF(strokeWidth, strokeWidth, w - strokeWidth, h - strokeWidth), 0, 360, true, paint);
            paint.setColor(DEF_BACKGROUND_COLOR);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(cW, cH, DensityUtil.dip2px(getContext(), DEF_SIZE_DIP) / 2, paint);

        }
    }
}
