package com.huihu.module_notification.comment.presenter;

import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_notification.R;
import com.huihu.module_notification.comment.commentinterface.ICommentModel;
import com.huihu.module_notification.comment.commentinterface.ICommentPresenter;
import com.huihu.module_notification.comment.commentinterface.ICommentView;
import com.huihu.module_notification.comment.entity.CommentPageBean;
import com.huihu.module_notification.comment.model.ImpCommentModel;

public class ImpCommentPresenter implements ICommentPresenter {
    private final ICommentModel iCommentModel = new ImpCommentModel(this);
    private final ICommentView iCommentView;

    private boolean hasMore = true;

    public ImpCommentPresenter(ICommentView iCommentView) {
        this.iCommentView = iCommentView;
    }

    @Override
    public void v2pGetFirstData() {
        hasMore = true;
        iCommentModel.p2mGetOtherCommentListNet(0, false);
    }

    @Override
    public void v2pGetMoreData(CommentPageBean.DataBean lastDataBean) {
        if (hasMore){
            iCommentModel.p2mGetOtherCommentListNet(lastDataBean.getCommentTime(), true);
        } else {
            ToastUtil.show(R.string.module_notification_has_no_more);
            iCommentView.p2vGetDataEnd();
        }

    }

    @Override
    public void m2pGetOtherCommentListSuccess(CommentPageBean bean, boolean isMore) {
        if (bean.getPageDatas() != null && bean.getPageDatas().size() > 0){
            if (isMore){
                iCommentView.p2vShowMoreData(bean.getPageDatas());
            } else {
                iCommentView.p2vShowFirstData(bean.getPageDatas());
            }
        } else {
            if (isMore){
                hasMore = false;
                ToastUtil.show(R.string.module_notification_has_no_more);
            } else {
                iCommentView.p2vShowNoData();
            }
        }
    }

    @Override
    public void m2pNetFail() {
        ToastUtil.show(R.string.uilib_http_request_fail);
    }

    @Override
    public void m2pNetComplete() {
        iCommentView.p2vGetDataEnd();
    }

    @Override
    public void m2pGetDataFail() {
        iCommentView.p2vShowGetDataFail();
    }
}
