package com.huihu.module_mine.comment.model;

import android.util.Log;

import com.bailun.bl_commonlib.net.NetworkTransmissionDefine;
import com.bailun.bl_commonlib.net.http.HttpRequestParam;
import com.bailun.localeslibrary.CurLocales;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huihu.commonlib.net.HuihuCallBack;
import com.huihu.commonlib.net.HuihuHttpUtils;
import com.huihu.commonlib.net.ReturnModel;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.R;
import com.huihu.module_mine.comment.commentinterface.ICommentModel;
import com.huihu.module_mine.comment.commentinterface.ICommentPresenter;
import com.huihu.module_mine.comment.entity.CommentInfo;
import com.huihu.module_mine.comment.entity.DeleteCommentSubCode;
import com.huihu.module_mine.comment.entity.GetCommentInfoSubCode;

import java.util.List;

public class ImpCommentModel implements ICommentModel {
    private static final int PAGE_SIZE =3 ;
    private final ICommentPresenter iCommentPresenter;

    public ImpCommentModel(ICommentPresenter iCommentPresenter) {
        this.iCommentPresenter = iCommentPresenter;
    }

    @Override
    public void p2mGetCommentList(long time,long fid, final boolean isMore) {
        getCommentList(time, fid,new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case GetCommentInfoSubCode
                            .Success:
                        CommentInfo commentInfo = new Gson().fromJson(returnModel.getBodyMessage(), new TypeToken<CommentInfo>() {
                        }.getType());
                        List<CommentInfo.PageDatasBean> mlist= commentInfo.getPageDatas();
                        iCommentPresenter.m2pReturnCommentList(mlist,isMore);
                        break;
                    case GetCommentInfoSubCode.BusinessError:
                    case GetCommentInfoSubCode.ParameterError:
                    case GetCommentInfoSubCode.NoUser:
                      default:
                          break;
                }

            }

            @Override
            public void onError(int errCode, String strErrMsg) {
                iCommentPresenter.m2pNetFail();
            }

            @Override
            public void onCompleted() {
                iCommentPresenter.m2pNetComplete();
            }
        });
    }

    @Override
    public void p2mDeleteComment(CommentInfo.PageDatasBean bean, final int position,long fid) {
        deleteComment(bean,fid, new HuihuCallBack() {
            @Override
            public void onSuccess(ReturnModel returnModel) {
                switch (returnModel.getSubCode()){
                    case DeleteCommentSubCode
                            .Success:
                    iCommentPresenter.m2pDeleteCommentSuccess(position);
                        break;
                    case DeleteCommentSubCode.BusinessError:
                    case DeleteCommentSubCode.ParameterError:
                    case DeleteCommentSubCode.UnLogin:
                        iCommentPresenter.m2pDeleteCommentWithError(returnModel.getSubCode());
                        default:
                            break;
                }
            }

            @Override
            public void onError(int errCode, String strErrMsg) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }

    private void getCommentList(long time,long fid, HuihuCallBack mCallBack) {
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Comments.GetCommentListByUserId, NetworkTransmissionDefine.HttpMethod.GET);
        httpRequestParam.addQuery("commentTime", ""+time);
        httpRequestParam.addQuery("loginUid", "" + SPUtils.getInstance().getCurrentUid());
        httpRequestParam.addQuery("pageSize", String.valueOf(PAGE_SIZE));
        httpRequestParam.addQuery("uid",""+fid);
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }

    private void deleteComment(CommentInfo.PageDatasBean bean,long fid,HuihuCallBack mCallBack){
        HttpRequestParam httpRequestParam = new HttpRequestParam(CurLocales.instance().API.Comments.PutDeleteComment, NetworkTransmissionDefine.HttpMethod.DELETE);
        httpRequestParam.addQuery("commentGrand", ""+2);
        httpRequestParam.addQuery("commentId", "" + bean.getCommentId());
        httpRequestParam.addQuery("uid",""+fid);
        HuihuHttpUtils.httpRequest(httpRequestParam, mCallBack);
    }

}
