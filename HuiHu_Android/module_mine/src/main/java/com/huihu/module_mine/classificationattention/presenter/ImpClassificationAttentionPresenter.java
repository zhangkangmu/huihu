package com.huihu.module_mine.classificationattention.presenter;

import android.util.Log;

import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.classificationattention.classificationattentioninterface.IClassificationAttentionModel;
import com.huihu.module_mine.classificationattention.classificationattentioninterface.IClassificationAttentionPresenter;
import com.huihu.module_mine.classificationattention.classificationattentioninterface.IClassificationAttentionView;
import com.huihu.module_mine.classificationattention.entity.ClassificationAttentionInfo;
import com.huihu.module_mine.classificationattention.model.ImpClassificationAttentionModel;

import java.util.List;

public class ImpClassificationAttentionPresenter implements IClassificationAttentionPresenter {
    private final IClassificationAttentionModel iClassificationAttentionModel = new ImpClassificationAttentionModel(this);
    private final IClassificationAttentionView iClassificationAttentionView;

    public ImpClassificationAttentionPresenter(IClassificationAttentionView iClassificationAttentionView) {
        this.iClassificationAttentionView = iClassificationAttentionView;
    }

    @Override
    public void v2pGetClassicationAttentionList() {
        iClassificationAttentionModel.p2mGetClassicationAttentionList(0,0,20, SPUtils.getInstance().getCurrentUid(),false);
    }
    @Override
    public void v2pGetMoreClassificationList(ClassificationAttentionInfo.PageDatasBean bean) {
        iClassificationAttentionModel.p2mGetClassicationAttentionList(bean.getFollowId(),0,20, SPUtils.getInstance().getCurrentUid(),true);
    }

    @Override
    public void m2pNetComplete() {
        iClassificationAttentionView.p2vNetComplete();
    }

    @Override
    public void m2pGetClassicationAttentionList(List<ClassificationAttentionInfo.PageDatasBean> pageDatas,boolean isMore) {
        if (pageDatas!=null && pageDatas.size()>0){
            if (isMore) {
                iClassificationAttentionView.p2vReturnMoreClassicationList(pageDatas);
            }else{
                    iClassificationAttentionView.p2vGetClassicationAttentionList(pageDatas);
                }
        }else{
            if (!isMore){
            iClassificationAttentionView.p2vShowNoData();
            }
        }

    }

    @Override
    public void m2pClassificationAttention() {
        iClassificationAttentionView.p2vClassificationAttention();
    }

    @Override
    public void m2pNetFail() {
        iClassificationAttentionView.p2vNetFail();
    }

}
