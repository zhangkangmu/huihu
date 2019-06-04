package com.huihu.module_home.writeanswer.view;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bailun.wangjing.permissionlibrary.annotation.RequestPermission;
import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouterClassRegister;
import com.huihu.commonlib.simpleRouter.SimpleRouterObj;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_home.R;
import com.huihu.module_home.R2;
import com.huihu.module_home.question.entity.PostDraftModel;
import com.huihu.module_home.questiondraft.entity.DraftType;
import com.huihu.module_home.writeanswer.entity.PostAnswerModel;
import com.huihu.module_home.writeanswer.presenter.ImpWriteAnswerPresenter;
import com.huihu.module_home.writeanswer.writeanswerinterface.IWriteAnswerPresenter;
import com.huihu.module_home.writeanswer.writeanswerinterface.IWriteAnswerView;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.album.view.AlbumActivity;
import com.huihu.uilib.customize.HHAlertDialog;
import com.huihu.uilib.customize.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import jp.wasabeef.richeditor.RichEditor;

@SimpleRouterClassRegister(key = RouterReDefine.WRITE_FRAGMENT, type = SimpleRouterObj.FRAGMENT)
public class WriteAnswerFragment extends BaseFragment implements IWriteAnswerView, AlbumActivity.OnSelectEndListener {
    private final IWriteAnswerPresenter iWriteAnswerPresenter = new ImpWriteAnswerPresenter(this);
    Unbinder unbinder;
    @BindView(R2.id.tv_question_name)
    TextView mTvQuestionName;
    @BindView(R2.id.tv_release)
    TextView mTvRelease;
    @BindView(R2.id.richEditor)
    RichEditor mRichEditor;
    @BindView(R2.id.iv_bold)
    ImageView mIvBold;
    @BindView(R2.id.iv_italic)
    ImageView mIvItalic;
    private long mQuestionId;
    private LoadingDialog mLoadingDialog;

    private List<AlbumImageBean> mAlbumImageBeanList;//每次选择相册界面回来的图片
    List<PostAnswerModel.ImagesBean> mImagesBeanList = new ArrayList<>();//所有要提交的图片
    private String mQuestionTitle;


    public static WriteAnswerFragment newInstance(long questionId, String questionTitle) {
        WriteAnswerFragment fragment = new WriteAnswerFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("questionId", questionId);
        bundle.putString("questionTitle", questionTitle);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_home_fragment_write_answer, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            mQuestionId = bundle.getLong("questionId");
            mQuestionTitle = bundle.getString("questionTitle");
            mTvQuestionName.setText(mQuestionTitle);
        }
    }

    private void initView() {
        mTvRelease.setClickable(false);
        mTvRelease.setTextColor(getResources().getColor(R.color.module_home_unclickable));

        mLoadingDialog = new LoadingDialog(_mActivity);

        initRichEditor();
    }

    private void initRichEditor() {
        mRichEditor.setPlaceholder(getResources().getString(R.string.module_home_hint_write_answer));

        mRichEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                iWriteAnswerPresenter.v2pCheckReleaseStatus(text);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R2.id.iv_back)
    public void onBack() {
        iWriteAnswerPresenter.v2pCheckString(mRichEditor.getHtml());
    }

    @Override
    public boolean onBackPressedSupport() {
        iWriteAnswerPresenter.v2pCheckString(mRichEditor.getHtml());
        return true;
    }

    @OnClick(R2.id.tv_release)
    public void onRelease() {
        PostAnswerModel model = new PostAnswerModel();
        model.setContent(mRichEditor.getHtml());
        model.setQuestionId(mQuestionId);
        model.setImages(mImagesBeanList);
        model.setUid(SPUtils.getInstance().getCurrentUid());
        iWriteAnswerPresenter.v2pPostAnswer(model);
    }

    @OnClick(R2.id.iv_photo)
    public void onSelectPhoto() {
        startAlbum(_mActivity);
    }

    @RequestPermission(request = 0, permissions = {Manifest.permission.READ_EXTERNAL_STORAGE})
    private void startAlbum(Context context) {
        AlbumActivity.openSelf(context, 9, this);
    }

    @Override
    public void p2vSetReleaseClick() {
        mTvRelease.setClickable(true);
        mTvRelease.setTextColor(getResources().getColor(R.color.module_home_blue));
    }

    @Override
    public void p2vSetReleaseUnclick() {
        mTvRelease.setClickable(false);
        mTvRelease.setTextColor(getResources().getColor(R.color.module_home_unclickable));
    }

    @Override
    public void p2vShowToast(String message) {
        ToastUtil.show(message);
    }

    @Override
    public void p2vFinish() {
        hideSoftInput();
        pop();
    }

    @Override
    public void p2vDismissLoadDialog() {
        mLoadingDialog.dismissDialog();
    }

    @Override
    public void p2vInsertImage() {
        for (AlbumImageBean albumImageBeanList : mAlbumImageBeanList) {
            mRichEditor.insertImage(albumImageBeanList.getUrl(), "dachshund");
            PostAnswerModel.ImagesBean imagesBean = new PostAnswerModel.ImagesBean();
            imagesBean.setImageUrl(albumImageBeanList.getUrl());
            mImagesBeanList.add(imagesBean);
        }
    }

    @Override
    public void p2vSetResult() {
        setFragmentResult(RESULT_OK, null);
    }

    @Override
    public void p2vShowAlertDialog() {
        HHAlertDialog alertDialog = new HHAlertDialog(_mActivity);
        alertDialog.setTitle("退出编辑？");
        alertDialog.setMessage("当前编辑内容将保存为草稿");
        alertDialog.setPositiveButton("继续编辑", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.setNegativeButton("保存并退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PostDraftModel model = new PostDraftModel();
                model.setContent(mRichEditor.getHtml());
                model.setQuestionId(mQuestionId);
                model.setTitle(mQuestionTitle);
                model.setType(DraftType.answer);
                model.setUid(SPUtils.getInstance().getCurrentUid());
                if (mAlbumImageBeanList == null || mAlbumImageBeanList.size() == 0) {
                    model.setImages(new ArrayList<PostDraftModel.ImagesBean>());
                } else {
                    List<PostDraftModel.ImagesBean> imagesBeans = new ArrayList<>();
                    for (AlbumImageBean bean : mAlbumImageBeanList) {
                        PostDraftModel.ImagesBean imagesBean = new PostDraftModel.ImagesBean();
                        imagesBean.setImageUrl(bean.getUrl());
                        imagesBean.setImageDesc("");
                        imagesBeans.add(imagesBean);
                    }
                    model.setImages(imagesBeans);
                }
                iWriteAnswerPresenter.v2pPostDraft(model);
                mLoadingDialog.showDialog();
            }
        });
        alertDialog.showDialog();
    }

    @Override
    public void onSelectEnd(List<AlbumImageBean> beanList) {
        mAlbumImageBeanList = beanList;
        mLoadingDialog.showDialog();
        iWriteAnswerPresenter.v2pLoadPic(beanList);
    }

    private boolean isBold = false;
    private boolean isItalic = false;

    @OnClick(R2.id.iv_bold)
    public void onBold() {
        mRichEditor.setBold();
        if (isBold) {
            mIvBold.setImageResource(R.drawable.module_home_icon_bold);
        } else {
            mIvBold.setImageResource(R.drawable.module_home_icon_bold_sel);
        }
        isBold = !isBold;
    }

    @OnClick(R2.id.iv_italic)
    public void onItalic() {
        mRichEditor.setItalic();
        if (isItalic) {
            mIvItalic.setImageResource(R.drawable.module_home_icon_italic);
        } else {
            mIvItalic.setImageResource(R.drawable.module_home_icon_italic_sel);
        }
        isItalic = !isItalic;
    }
}
