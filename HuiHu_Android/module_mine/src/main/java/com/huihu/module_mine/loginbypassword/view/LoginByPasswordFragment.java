package com.huihu.module_mine.loginbypassword.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.InputType;
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
import com.huihu.commonlib.utils.SoftKeyBoardListener;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.AgreementFragment;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.loginbypassword.loginbypasswordinterface.ILoginByPasswordPresenter;
import com.huihu.module_mine.loginbypassword.loginbypasswordinterface.ILoginByPasswordView;
import com.huihu.module_mine.loginbypassword.presenter.ImpLoginByPasswordPresenter;
import com.huihu.module_mine.loginbyphone.view.LoginByPhoneFragment;
import com.huihu.module_mine.selectcountry.view.SelectCountryFragment;
import com.huihu.module_mine.verificationcode.view.VerificationCodeFragment;
import com.huihu.uilib.customize.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginByPasswordFragment extends BaseFragment implements ILoginByPasswordView {

    Unbinder unbinder;
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.iv_next)
    ImageView mIvNext;
    @BindView(R2.id.cl_bottom)
    ConstraintLayout mClBottom;
    @BindView(R2.id.tv_user_agreement)
    TextView mTvUserAgreement;
    @BindView(R2.id.iv_confirm)
    ImageView mIvConfirm;
    @BindView(R2.id.iv_psw_display)
    ImageView mIvPswDisplay;
    @BindView(R2.id.et_account)
    EditText mEtAccount;
    @BindView(R2.id.et_password)
    EditText mEtPassword;
    @BindView(R2.id.tv_hint)
    TextView mTvHint;
    @BindView(R2.id.iv_correct)
    ImageView mIvCorrect;
    @BindView(R2.id.tv_country_code)
    TextView mTvCountryCode;


    private boolean isConfirm = true;

    private String mPassword;
    private String mPhone;
    private LoadingDialog mLoadingDialog;
    private int mCountryCode = 6541;

    public static LoginByPasswordFragment newInstance() {
        Bundle args = new Bundle();
        LoginByPasswordFragment fragment = new LoginByPasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private final ILoginByPasswordPresenter iLoginByPasswordPresenter = new ImpLoginByPasswordPresenter(this);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_login_by_psw, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mTvTitle.setText(getResources().getString(R.string.module_mine_login_by_psw));
        mLoadingDialog = new LoadingDialog(_mActivity, false, null);
        initHint();
        setAgreementSpan();//用户协议部分变色
        showSoftInput(mEtAccount);
        mEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        SoftKeyBoardListener.setListener(_mActivity, mOnSoftKeyBoardChangeListener);

        mEtAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPhone = s.toString().trim();
                iLoginByPasswordPresenter.v2pCheckVaild(ImpLoginByPasswordPresenter.TYPE_PHONE, mPhone);
            }
        });

        mEtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mPassword = s.toString().trim();
                iLoginByPasswordPresenter.v2pCheckVaild(ImpLoginByPasswordPresenter.TYPE_PASSWORD, mPassword);
            }
        });
    }

    private SoftKeyBoardListener.OnSoftKeyBoardChangeListener mOnSoftKeyBoardChangeListener = new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
        @Override
        public void keyBoardShow(int height) {
            if (mEtPassword == null || mClBottom == null) {
                return;
            }
            int[] location = new int[2];
            mEtPassword.getLocationOnScreen(location);
            mClBottom.animate().translationY(-location[1]).setDuration(0).start();
        }

        @Override
        public void keyBoardHide(int height) {
            if (mEtPassword == null || mClBottom == null) {
                return;
            }
            mClBottom.animate().translationY(0).start();
        }
    };

    private void initHint() {
        mTvHint.setText(getResources().getString(R.string.module_mine_input_account_psw));
        mTvHint.setAlpha(0.6f);
    }

    private void setForgetPswSpan() {
        SpannableString spanHintString = new SpannableString(getResources().getString(R.string.module_mine_forget_psw));
        spanHintString.setSpan(new ResetClickable(), 13, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvHint.setText(spanHintString);
        mTvHint.setAlpha(1);
        mTvHint.setMovementMethod(LinkMovementMethod.getInstance());
        mTvHint.setHighlightColor(Color.TRANSPARENT);
    }

    private void setAgreementSpan() {
        SpannableString spanString = new SpannableString(getResources().getString(R.string.module_mine_user_agreement));
        spanString.setSpan(new AgreementClickable(), 7, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvUserAgreement.setText(spanString);
        mTvUserAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        mTvUserAgreement.setHighlightColor(Color.TRANSPARENT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mOnSoftKeyBoardChangeListener = null;
        unbinder.unbind();
    }

    @OnClick(R2.id.iv_back)
    public void onBack() {
        pop();
    }

    @OnClick(R2.id.iv_next)
    public void onNext() {
        iLoginByPasswordPresenter.v2pCheckValidByNext(isConfirm, mPhone, mPassword);
    }

    @OnClick(R2.id.iv_confirm)
    public void onConfirmAgreement() {
        mIvConfirm.setImageResource(isConfirm ? R.drawable.module_mine_icon_control_unselected : R.drawable.module_mine_icon_control_selected);
        isConfirm = !isConfirm;
    }

    private boolean isDisplay = false;

    @OnClick(R2.id.iv_psw_display)
    public void onDisplayPsw() {
        if (isDisplay) {
            mEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            mIvPswDisplay.setImageResource(R.drawable.module_mine_icon_hide);
        } else {
            mEtPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);

            mIvPswDisplay.setImageResource(R.drawable.module_mine_icon_display);
        }
        mEtPassword.setSelection(mEtPassword.getText().length());
        isDisplay = !isDisplay;
    }

    @Override
    public void p2vSetNextInvalid() {
        mIvNext.setClickable(false);
        mIvNext.setImageResource(R.drawable.module_mine_icon_next_disabled);
    }

    @Override
    public void p2vSetNextVaild() {
        mIvNext.setClickable(true);
        mIvNext.setImageResource(R.drawable.module_mine_icon_next);
    }

    @Override
    public void p2vShowPhoneCorrect() {
        setForgetPswSpan();//忘记密码部分变色
        mIvCorrect.setVisibility(View.VISIBLE);
    }

    @Override
    public void p2vHidePhoneCorrect() {
        initHint();
        mIvCorrect.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String s) {
        ToastUtil.show(s);
    }

    @Override
    public void p2vStartVerification() {
        start(VerificationCodeFragment.newInstance(6541, mPhone, 2));
    }

    @Override
    public void p2vShowLoadingDialog() {
        mLoadingDialog.showDialog();
    }

    @Override
    public void p2vShowToast(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void p2vDissmissLoadingDialog() {
        mLoadingDialog.dismissDialog();
    }

    @OnClick(R2.id.cl_select_country)
    public void onSelectCountry() {
        startForResult(SelectCountryFragment.newInstance(mCountryCode), LoginByPhoneFragment.requestCode);
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

    class ResetClickable extends ClickableSpan {

        @Override
        public void onClick(@NonNull View widget) {
            iLoginByPasswordPresenter.v2pForgetPsw(mPhone);
        }

        @Override
        public void updateDrawState(@NonNull TextPaint ds) {
            ds.setColor(getResources().getColor(R.color.module_mine_yellow));
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (data != null) {
            //改变了选中国家
            int countryCode = data.getInt("code");
            mCountryCode = countryCode;
            mTvCountryCode.setText("+" + countryCode);
        }
    }
}
