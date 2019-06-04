package com.huihu.module_mine.followsandfollowed.presenter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.module_mine.R;
import com.huihu.module_mine.followsandfollowed.entity.FollowsBean;
import com.huihu.module_mine.followsandfollowed.followsandfollowedinterface.IFollowsAndFollowedModel;
import com.huihu.module_mine.followsandfollowed.followsandfollowedinterface.IFollowsAndFollowedPresenter;
import com.huihu.module_mine.followsandfollowed.followsandfollowedinterface.IFollowsAndFollowedView;
import com.huihu.module_mine.followsandfollowed.model.ImpFollowsAndFollowedModel;
import com.huihu.uilib.RouterReDefine;

import java.util.List;

import me.yokeyword.fragmentation.SupportFragment;

public class ImpFollowsAndFollowedPresenter implements IFollowsAndFollowedPresenter {
    private final IFollowsAndFollowedModel iFollowsAndFollowedModel = new ImpFollowsAndFollowedModel(this);
    private final IFollowsAndFollowedView iFollowsAndFollowedView;

    public ImpFollowsAndFollowedPresenter(IFollowsAndFollowedView iFollowsAndFollowedView) {
        this.iFollowsAndFollowedView = iFollowsAndFollowedView;
    }

    @Override
    public void v2pGetFollowAndFansList(long userId, int type, int followId) {
        iFollowsAndFollowedModel.p2mGetFollowAndFansList(userId,type,followId);
    }

    @Override
    public void m2pReturnFollowAndFansList(List<FollowsBean.PageDatasBean> pageDatas, int followId) {
        if (followId!=0){
            //加载更多
            iFollowsAndFollowedView.p2vReturnMoreFollowAndFansList(pageDatas);
        }else {
            //刷新和首次加载
            iFollowsAndFollowedView.p2vReturnFollowAndFansList(pageDatas);
        }
    }

    @Override
    public void v2pJudgyItemClick(View view, FollowsBean.PageDatasBean bean, int position, int type) {
        int viewId=view.getId();
        if (viewId==R.id.tv_name_user||viewId==R.id.rv_user){
            SupportFragment fragment=(SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.OTHERS_FRAGMENT);
            Bundle bundle = new Bundle();
            bundle.putLong("uid", bean.getUserId());
            fragment.setArguments(bundle);
            iFollowsAndFollowedView.p2vHandleItemClickResult(fragment);
        }else if(viewId==R.id.tv_attention){
            iFollowsAndFollowedModel.p2mControlFollow(bean);
        }
    }
}
