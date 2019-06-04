package com.huihu.uilib.customize;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.huihu.uilib.util.DensityUtil;

/**
 * create by wangjing on 2019/3/13 0013
 * description: RecyclerView的间距。保证每个间距一致
 */
public class ItemEqualSpacing extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int offsetPx;

    /**
     * @param mContext 上下文
     * @param spanCount 列数
     * @param offsetDp 间隔大小（单位：dp）
     */
    public ItemEqualSpacing(Context mContext, int spanCount, int offsetDp) {
        this.spanCount = spanCount;
        this.offsetPx = DensityUtil.dip2px(mContext, offsetDp);
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // item position
        int column = position % spanCount;
//        这里的思路来源于数学
        if (column == 0){
            outRect.left = offsetPx;
            outRect.right = offsetPx / spanCount;
        } else if (column == spanCount - 1){
            outRect.left = offsetPx / spanCount;
            outRect.right = offsetPx;
        } else {
            outRect.left = offsetPx - (column * offsetPx) / spanCount;
            outRect.right = ( (column + 1) * offsetPx ) / spanCount;
        }
        outRect.bottom = offsetPx;
    }
}
