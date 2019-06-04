package com.huihu.module_mine.editnewphone.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.Constant;
import com.huihu.commonlib.utils.SoftKeyBoardListener;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.AgreementFragment;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.editnewphone.editnewphoneinterface.IEditNewPhonePresenter;
import com.huihu.module_mine.editnewphone.editnewphoneinterface.IEditNewPhoneView;
import com.huihu.module_mine.editnewphone.presenter.ImpEditNewPhonePresenter;
import com.huihu.module_mine.editnewphoneverication.view.EditNewPhoneVericationFragment;

import com.huihu.module_mine.loginbyphone.view.LoginByPhoneFragment;
import com.huihu.module_mine.selectcountry.view.SelectCountryFragment;
import com.huihu.uilib.customize.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class EditNewPhoneFragment extends BaseFragment implements IEditNewPhoneView{
    private final IEditNewPhonePresenter iEditNewPhonePresenter = new ImpEditNewPhonePresenter(this);
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_hint)
    TextView mTvHint;

    @BindView(R2.id.iv_next)
    ImageView mIvNext;
    @BindView(R2.id.iv_confirm)
    ImageView mIvConfirm;
    @BindView(R2.id.tv_user_agreement)
    TextView mTvUserAgreement;
    @BindView(R2.id.et_phone)
    EditText mEtPhone;
    @BindView(R2.id.cl_bottom)
    ConstraintLayout mClBottom;
    @BindView(R2.id.cl_select_country)
    ConstraintLayout mClSelectCountry;


    private int mCountryCode = 6541;//国家编码
    private String mPhone;//用户输入的手机号码
    private int mSendType = 3;//发送类型
    private LoadingDialog mLoadingDialog;

    private static final int REQUEST_UPDATE_PHONE = 12;
    private static final int RESULT_UPDATE_PHONE_SUCCESS = 102;
    private static final int REQUEST_UPDATE_PASSWORD = 13;
    private static final int REQUEST_UPDATE_SUCCESS = 102;
    private static final String PHONE = "phone";
    private static final String SENDTYPE = "sendType";

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_new_phone, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mSendType = getArguments().getInt(SENDTYPE);
        initView();
        initData();
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(){
        mLoadingDialog = new LoadingDialog(_mActivity);
        //修改手机号与修改密码共用，分支判断
        if (mSendType == Constant.TYPE_CHANGE_PHONE){
            mTvTitle.setText(R.string.module_mine_title_bind_phone);
            mTvHint.setText(R.string.module_mine_hint_bind_phone);

            mIvConfirm.setVisibility(View.GONE);
            mTvUserAgreement.setVisibility(View.GONE);
        } else if(mSendType == Constant.TYPE_CHANGE_PASSWORD){
            mTvTitle.setText(R.string.module_mine_title_confirm_phone);
            mTvHint.setText(R.string.module_mine_hint_confirm_phone);

            mIvConfirm.setVisibility(View.VISIBLE);
            mTvUserAgreement.setVisibility(View.VISIBLE);
            initUserAgreement();
        }

        mEtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPhone = s.toString().trim();
                iEditNewPhonePresenter.v2pCheckPhoneEmpty(s.toString());
            }
        });

        SoftKeyBoardListener.setListener(_mActivity, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                mClBottom.animate().translationY(-height).setDuration(0).start();
            }

            @Override
            public void keyBoardHide(int height) {
                mClBottom.animate().translationY(0).start();
            }
        });
    }

    private void initData(){

    }

    private void initUserAgreement() {
        SpannableString spanString = new SpannableString(getResources().getString(R.string.module_mine_user_agreement));
        spanString.setSpan(new AgreementClickable(), 7, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvUserAgreement.setText(spanString);
        mTvUserAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        mTvUserAgreement.setHighlightColor(Color.TRANSPARENT);
    }

    class AgreementClickable extends ClickableSpan {

        @Override
        public void onClick(@NonNull View widget) {
            start(new AgreementFragment());
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            ds.setColor(getResources().getColor(R.color.module_mine_yellow));
        }
    }


    public static EditNewPhoneFragment newInstance(int sendType) {
        Bundle args = new Bundle();
        args.putInt(SENDTYPE,sendType);
        EditNewPhoneFragment fragment = new EditNewPhoneFragment();
        fragment.setArguments(args);
        return fragment;
    }
    /**
     * 发送验证码
     */
    @OnClick(R2.id.iv_next)
    public void onNext() {
        iEditNewPhonePresenter.v2pCheckPhoneValid(mCountryCode, mPhone, mSendType);
    }


    @OnClick(R2.id.iv_back)
    public void onViewClicked() {
        pop();
    }


    @Override
    public void p2vSetNextClickAble() {
        mIvNext.setClickable(true);
        mIvNext.setImageResource(R.drawable.module_mine_icon_next);
    }

    @Override
    public void p2vSetNextUnClickAble() {
        mIvNext.setClickable(false);
        mIvNext.setImageResource(R.drawable.module_mine_icon_next_disabled);
    }

    @Override
    public void p2vStartVerifcation() {
        hideSoftInput();
        if(mSendType == Constant.TYPE_CHANGE_PHONE){
            startForResult(EditNewPhoneVericationFragment.newInstance(mCountryCode, mPhone, mSendType),  REQUEST_UPDATE_PHONE);
        }else if(mSendType == Constant.TYPE_CHANGE_PASSWORD){
            startForResult(EditNewPhoneVericationFragment.newInstance(mCountryCode, mPhone, mSendType),  REQUEST_UPDATE_PASSWORD);
        }

    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        if(requestCode == REQUEST_UPDATE_PHONE && resultCode == RESULT_UPDATE_PHONE_SUCCESS){
            setFragmentResult(RESULT_UPDATE_PHONE_SUCCESS,data);
            pop();
        } else if (requestCode == REQUEST_UPDATE_PASSWORD){
            //TODO result resolve
        }
    }

    @Override
    public void p2vDismissDialog() {
        mLoadingDialog.dismissDialog();
    }

    @Override
    public void p2vShowToast(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void p2vShowLoadDialog() {
        mLoadingDialog.showDialog();
    }

    @OnClick(R2.id.cl_select_country)
    public void onSelectCountry() {
        start(new SelectCountryFragment());
    }
}
