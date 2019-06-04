package com.huihu.uilib.customize.source;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.huihu.uilib.customize.source.SourceViewMode.ARTICLE_PICTURE_TEXT;
import static com.huihu.uilib.customize.source.SourceViewMode.ARTICLE_TEXT;
import static com.huihu.uilib.customize.source.SourceViewMode.USER_COMMENT_DELETE;
import static com.huihu.uilib.customize.source.SourceViewMode.USER_COMMENT_DISCONTINUED;
import static com.huihu.uilib.customize.source.SourceViewMode.USER_COMMENT_MORE_PICTURE;
import static com.huihu.uilib.customize.source.SourceViewMode.USER_COMMENT_SINGLE_PICTURE;

/**
 * create by wangjing on 2019/3/9 0009
 * description:
 */
@IntDef({ARTICLE_TEXT, ARTICLE_PICTURE_TEXT, USER_COMMENT_SINGLE_PICTURE, USER_COMMENT_MORE_PICTURE
        , USER_COMMENT_DELETE, USER_COMMENT_DISCONTINUED})
@Retention(RetentionPolicy.SOURCE)
public @interface SourceViewMode {
    int ARTICLE_TEXT = 100;
    int ARTICLE_PICTURE_TEXT = 101;
    int USER_COMMENT_SINGLE_PICTURE = 200;
    int USER_COMMENT_MORE_PICTURE = 201;
    int USER_COMMENT_DELETE = 300;
    int USER_COMMENT_DISCONTINUED = 301;
}
