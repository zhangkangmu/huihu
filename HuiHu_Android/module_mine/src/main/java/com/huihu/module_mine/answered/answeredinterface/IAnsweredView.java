package com.huihu.module_mine.answered.answeredinterface;

import com.huihu.module_mine.answered.entity.AnsweredInfo;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

public interface IAnsweredView {
    void p2vGetAnsweredListSuccess(List<AnsweredInfo.PageDatasBean> mlist);
    void p2vGetMoreAnsweredListSuccess(List<AnsweredInfo.PageDatasBean> mlist);
    void p2vShowNoData();
    void p2vGetDataEnd();
    void p2vHandleAnswerItemClickResult(SupportFragment fragment);
    void p2vShowDialog();
}
