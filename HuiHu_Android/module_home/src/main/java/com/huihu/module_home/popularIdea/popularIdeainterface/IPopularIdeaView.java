package com.huihu.module_home.popularIdea.popularIdeainterface;

import android.view.View;

import com.huihu.module_home.popularIdea.enity.PopularIdeaData;
import com.huihu.module_home.popularIdea.enity.SwitchGrph;
import com.huihu.module_home.writeanswer.view.WriteAnswerFragment;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

public interface IPopularIdeaView {
    void p2vGetPopularIdeaList();
    void p2vGetPopularIdeaListSuccess(List<PopularIdeaData.PageDatasBean> mlist);
    void p2vShowMorePopularIdeaList(List<PopularIdeaData.PageDatasBean> moreList);
    void p2vShowNoData();
    void p2vGetDataEnd();
    void p2vGetPopularIdeaListFailed();
    void p2vGetSwitchGrphSuccess(List<SwitchGrph> switchGrphs);
    void p2vGetSwitchGrphFailed();
    void p2vDeleteIdeaSuccessful(int position);
    void p2vCanclFollowSuccessful(int position);
    void p2vPutGiveFollows();
    void p2vPutGiveFollowsError(String subCode);
    void p2vShowPopuWindow(View view, PopularIdeaData.PageDatasBean bean, int position);
    void p2vShowDialog();

    void p2vHandlePopularItemClickResult(SupportFragment fragment, int judyType);
}
