package com.huihu.uilib.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.huihu.commonlib.R;

import org.xutils.common.util.DensityUtil;

/**
 * 图片显示工具
 */
public class ImgTools {

    /**
     * 显示图片，没有占位图
     *
     * @param ctx context
     * @param url 网络路径
     * @param iv  图片控件
     */
    public static void showImageView(Context ctx, String url, ImageView iv) {
        Glide.with(ctx).load(url).error(ctx.getDrawable(R.drawable.fragmentation_help)).into(iv);
    }

    public static void showImageViewWithConner(Context ctx, String url, ImageView iv, int corner) {
        Glide.with(ctx)
                .load(url)
                .apply(new RequestOptions()
                        .transforms(new CenterCrop(), new RoundedCorners(DensityUtil.dip2px(corner))
                        ))
                .error(ctx.getDrawable(R.drawable.fragmentation_help))
                .into(iv);
    }
}
