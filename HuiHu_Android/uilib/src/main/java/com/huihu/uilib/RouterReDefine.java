package com.huihu.uilib;

/*************************************************
 * 一些基本的公用数据，达成共识，使用同一的定义，防止拼写错误
 * 采用String而不是int型，是为了有拓展
 * 临时采用String来定义一些这里没有的，也可以更直观知道含义
 * 最后临时确定后，都放在这个正式定义
 *************************************************/
public class RouterReDefine {
    //region 首页
    public static final String HOME_FRAGMENT = "fragment_home";
    public static final String FRAGMENT_QUESTION = "fragment_question";
    public static final String WRITE_FRAGMENT = "write_fragment";
    public static final String ANSWERDETAIL_FRAGMENT = "answerdetail_fragment";
    public static final String QUESTIONDETAIL_FRAGMENT = "questiondetail_fragment";
    public static final String ANSWER_DETAIL_FRAGMENT = "answer_detail_fragment";
    public static final String CATEGORYDETAIL_FRAGMENT = "fragment_categorydetail";
    public static final String COMMENT_DETAILS_FRAGMENT = "fragment_comment_details";
    //endregion 首页

    //region 圈子
    public static final String CIRCLE_FRAGMENT = "module_circle_fragment_circle";
    public static final String NEWCIRCLEDISCUSS_FRAGMENT = "fragment_newcirclediscuss";
    public static final String CIRCLELIST_FRAGMENT = "fragment_circlelist";
    //endregion 圈子

    //region 消息
    public static final String NOTIFICATION_FRAGMENT = "fragment_notification";
    public static final String PUSHSETTING__FRAGMENT = "fragment_pushsetting";
    //endregion 消息

    //region 我的
    public static final String MINE_FRAGMENT = "fragment_mine";
    public static final String OTHERS_FRAGMENT = "fragment_others";
    public static final String LOGIN_ACTIVITY = "activity_login";
    //endregion 我的
}
