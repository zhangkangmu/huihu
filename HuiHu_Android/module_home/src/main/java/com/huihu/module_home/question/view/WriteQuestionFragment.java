package com.huihu.module_home.question.view;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
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
import com.huihu.module_home.addquestioncategory.entitiy.PostQuestionModel;
import com.huihu.module_home.addquestioncategory.view.fragment.AddQuestionCategoryFragment;
import com.huihu.module_home.draftmanager.view.DraftManagerFragment;
import com.huihu.module_home.question.entity.PostDiscussModel;
import com.huihu.module_home.question.entity.PostDraftModel;
import com.huihu.module_home.question.entity.TypeWrite;
import com.huihu.module_home.question.presenter.ImpQuestionPresenter;
import com.huihu.module_home.question.questioninterface.IQuestionPresenter;
import com.huihu.module_home.question.questioninterface.IQuestionView;
import com.huihu.module_home.questiondraft.entity.DraftType;
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

@SimpleRouterClassRegister(key = RouterReDefine.FRAGMENT_QUESTION, type = SimpleRouterObj.FRAGMENT)
public class WriteQuestionFragment extends BaseFragment implements IQuestionView, AlbumActivity.OnSelectEndListener {
    private final IQuestionPresenter iQuestionPresenter = new ImpQuestionPresenter(this);
    Unbinder unbinder;
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.cl_rich_editor)
    ConstraintLayout mClRichEditor;
    @BindView(R2.id.et_question)
    EditText mEtQuestion;
    @BindView(R2.id.et_rich_editor)
    RichEditor mRichEditor;
    @BindView(R2.id.cl_add_description)
    ConstraintLayout mClAddDescription;
    @BindView(R2.id.iv_photo)
    ImageView mIvPhoto;
    @BindView(R2.id.iv_bold)
    ImageView mIvBold;
    @BindView(R2.id.iv_italic)
    ImageView mIvItalic;
    @BindView(R2.id.tv_next)
    TextView mTvNext;
    @BindView(R2.id.rv_related)
    RecyclerView mRvRelated;
    @BindView(R2.id.scroll_view)
    NestedScrollView mScrollView;
    @BindView(R2.id.tv_draft)
    TextView mTvDraft;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.tv_description)
    TextView mTvDescription;

    private LoadingDialog mLoadingDialog;
    private List<AlbumImageBean> mAlbumImageBeanList;
    private RelatedQuestionAdapter mAdapter;
    private int mTypeWrite;
    private Long mCiecleId;

    public static WriteQuestionFragment newInstance(int type, Long ciecleId) {
        WriteQuestionFragment fragment = new WriteQuestionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        bundle.putLong("ciecleId", ciecleId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_home_fragment_question, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        //获取界面参数
        getArgs();
        //控件的初始化状态
        initViewState();
        //输入框设置
        initQuestionEdit();
        //编辑器设置
        initRichEdit();
        //关联问题recyclerview初始化
        initRecyclerView();
        //滚动控件
        initNestedScrollView();
    }

    private void getArgs() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            mTypeWrite = bundle.getInt("type");
            mCiecleId = bundle.getLong("ciecleId");
        }
    }

    private void initNestedScrollView() {

    }

    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(_mActivity);
        mRvRelated.setLayoutManager(layoutManager);
        mAdapter = new RelatedQuestionAdapter(_mActivity);
    }

    private void initViewState() {
        //下一步按钮不可点击
        mTvNext.setClickable(false);
        //loadingDialog初始化
        mLoadingDialog = new LoadingDialog(_mActivity);
        //发布讨论暂时没有草稿
        if (mTypeWrite == TypeWrite.discuss) {
            mTvDraft.setVisibility(View.GONE);
            mTvNext.setText(R.string.module_home_text_release);
            mTvTitle.setText(R.string.module_home_title_initiate_discuss);
            mEtQuestion.setHint(R.string.module_home_hint_discuss);
            mRichEditor.setPlaceholder(getResources().getString(R.string.module_home_hint_richeditor));
            mTvDescription.setText(R.string.module_home_hint_richeditor);
        } else {
            mRichEditor.setPlaceholder(getResources().getString(R.string.module_home_hint_question_description));
        }
        //弹起软键盘
        showSoftInput(mEtQuestion);
    }

    private void initQuestionEdit() {
        mEtQuestion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus && TextUtils.isEmpty(mRichEditor.getHtml())) {
                    mClAddDescription.setVisibility(View.VISIBLE);
                } else {
                    mClAddDescription.setVisibility(View.GONE);
                }
            }
        });

        mEtQuestion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                iQuestionPresenter.v2pCheckInput(s.toString().trim(),mTypeWrite);
            }
        });
    }

    private void initRichEdit() {
        mRichEditor.setEditorFontSize(16);
        mRichEditor.setEditorFontColor(getResources().getColor(R.color.module_home_text_black));
        mRichEditor.setBackgroundColor(Color.WHITE);
        mRichEditor.setPadding(10, 10, 10, 10);

        mRichEditor.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mClRichEditor.setVisibility(View.VISIBLE);
                } else {
                    mClRichEditor.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        _mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);//设置键盘弹起模式
    }

    @OnClick(R2.id.iv_back)
    public void onViewClicked() {
        iQuestionPresenter.v2pCheckString(mEtQuestion.getText().toString().trim(), mRichEditor.getHtml());
    }

    @Override
    public boolean onBackPressedSupport() {
        iQuestionPresenter.v2pCheckString(mEtQuestion.getText().toString().trim(), mRichEditor.getHtml());
        return true;
    }


    @OnClick(R2.id.iv_photo)
    public void onMIvPhotoClicked() {
        startAlbum(_mActivity);
    }

    @RequestPermission(request = 0, permissions = {Manifest.permission.READ_EXTERNAL_STORAGE})
    private void startAlbum(Context context) {
        AlbumActivity.openSelf(context, 9
                , this);
    }

    private boolean isBold = false;
    private boolean isItalic = false;

    @OnClick(R2.id.iv_bold)
    public void onMIvBoldClicked() {
        mRichEditor.setBold();
        if (isBold) {
            mIvBold.setImageResource(R.drawable.module_home_icon_bold);
        } else {
            mIvBold.setImageResource(R.drawable.module_home_icon_bold_sel);
        }
        isBold = !isBold;
    }

    @OnClick(R2.id.iv_italic)
    public void onMIvItalicClicked() {
        mRichEditor.setItalic();
        if (isItalic) {
            mIvItalic.setImageResource(R.drawable.module_home_icon_italic);
        } else {
            mIvItalic.setImageResource(R.drawable.module_home_icon_italic_sel);
        }
        isItalic = !isItalic;
    }

    @OnClick(R2.id.tv_next)
    public void onNext() {
        iQuestionPresenter.v2pGoto(mTypeWrite);
    }

    @Override
    public void onSelectEnd(List<AlbumImageBean> beanList) {
        mAlbumImageBeanList = beanList;
        mLoadingDialog.showDialog();
        iQuestionPresenter.v2pLoadPic(beanList);
    }

    @Override
    public void p2vShowToast(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void p2vDismissLoadDialog() {
        mLoadingDialog.dismissDialog();
    }

    @Override
    public void p2vSetNextClick() {
        mTvNext.setClickable(true);
        mTvNext.setTextColor(getResources().getColor(R.color.module_home_blue));
    }

    @Override
    public void p2vSetNextUnClick() {
        mTvNext.setClickable(false);
        mTvNext.setTextColor(getResources().getColor(R.color.module_home_unclickable));
    }

    @Override
    public void p2vFinsih() {
        pop();
    }

    @Override
    public void p2vInsertImage() {
        for (AlbumImageBean albumImageBeanList : mAlbumImageBeanList) {
            mRichEditor.insertImage(albumImageBeanList.getUrl(), "dachshund");
        }
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
                model.setTitle(mEtQuestion.getText().toString());
                model.setType(DraftType.question);
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
                iQuestionPresenter.v2pPostDraft(model);
                mLoadingDialog.showDialog();
            }
        });
        alertDialog.showDialog();
    }

    @Override
    public void p2vSetDescriptionGone() {
        mClAddDescription.setVisibility(View.GONE);
    }

    @Override
    public void p2vSetRichEditGone() {
        mRichEditor.setVisibility(View.GONE);
    }

    @Override
    public void p2vSetDescriptionVisiible() {
        mClAddDescription.setVisibility(View.VISIBLE);
    }

    @Override
    public void p2vShowRelated() {
        mRvRelated.setVisibility(View.VISIBLE);
        mRvRelated.setAdapter(mAdapter);
    }

    @Override
    public void p2vStartAddCategory() {
        PostQuestionModel model = new PostQuestionModel();
        model.setContent(mRichEditor.getHtml());
        model.setTitle(mEtQuestion.getText().toString());
        model.setUid(SPUtils.getInstance().getCurrentUid());
        if (mAlbumImageBeanList == null || mAlbumImageBeanList.size() == 0) {
            model.setImages(new ArrayList<PostQuestionModel.ImagesBean>());
        } else {
            List<PostQuestionModel.ImagesBean> imagesBeans = new ArrayList<>();
            for (AlbumImageBean bean : mAlbumImageBeanList) {
                PostQuestionModel.ImagesBean imagesBean = new PostQuestionModel.ImagesBean();
                imagesBean.setImageUrl(bean.getUrl());
                imagesBean.setImageDesc("");
                imagesBeans.add(imagesBean);
            }
            model.setImages(imagesBeans);
        }
        start(AddQuestionCategoryFragment.newInstance(model));
    }

    @Override
    public void p2vPostDiscuss() {
        PostDiscussModel model = new PostDiscussModel();
        model.setCircleId(mCiecleId);
        model.setContent(mRichEditor.getHtml());
        model.setTitle(mEtQuestion.getText().toString());
        model.setUid(SPUtils.getInstance().getCurrentUid());
        if (mAlbumImageBeanList == null || mAlbumImageBeanList.size() == 0) {
            model.setImages(new ArrayList<PostDiscussModel.ImagesBean>());
        } else {
            List<PostDiscussModel.ImagesBean> imagesBeans = new ArrayList<>();
            for (AlbumImageBean bean : mAlbumImageBeanList) {
                PostDiscussModel.ImagesBean imagesBean = new PostDiscussModel.ImagesBean();
                imagesBean.setImageUrl(bean.getUrl());
                imagesBean.setImageDesc("");
                imagesBeans.add(imagesBean);
            }
            model.setImages(imagesBeans);
        }
        iQuestionPresenter.v2pPostDiscuss(model);
    }

    @Override
    public void p2vShowLoadingDialog() {
        mLoadingDialog.showDialog();
    }

    @OnClick(R2.id.tv_draft)
    public void onStartDraft() {
        start(new DraftManagerFragment());
    }

    @OnClick(R2.id.cl_add_description)
    public void onAddDescription() {
        mRvRelated.setVisibility(View.GONE);
        mClAddDescription.setVisibility(View.GONE);
        mClRichEditor.setVisibility(View.VISIBLE);
        mRichEditor.setVisibility(View.VISIBLE);
        showSoftInput(mRichEditor);
    }
}
