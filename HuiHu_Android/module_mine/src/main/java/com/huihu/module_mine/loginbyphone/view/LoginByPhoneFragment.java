package com.huihu.module_mine.loginbyphone.view;

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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.Constant;
import com.huihu.commonlib.utils.SoftKeyBoardListener;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.AgreementFragment;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.loginbypassword.view.LoginByPasswordFragment;
import com.huihu.module_mine.loginbyphone.logininterface.ILoginPresenter;
import com.huihu.module_mine.loginbyphone.logininterface.ILoginView;
import com.huihu.module_mine.loginbyphone.presenter.ImpLoginPresenter;
import com.huihu.module_mine.selectcountry.view.SelectCountryFragment;
import com.huihu.module_mine.verificationcode.view.VerificationCodeFragment;
import com.huihu.uilib.customize.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class LoginByPhoneFragment extends BaseFragment implements ILoginView {

    @BindView(R2.id.iv_confirm)
    ImageView mIvConfirm;
    @BindView(R2.id.tv_login_by_psw)
    TextView mTvLoginByPsw;
    @BindView(R2.id.iv_next)
    ImageView mIvNext;
    Unbinder unbinder;
    @BindView(R2.id.tv_user_agreement)
    TextView mTvUserAgreement;
    @BindView(R2.id.et_phone)
    EditText mEtPhone;
    @BindView(R2.id.cl_bottom)
    ConstraintLayout mClBottom;
    @BindView(R2.id.cl_select_country)
    ConstraintLayout mClSelectCountry;
    @BindView(R2.id.tv_country_code)
    TextView mTvCountryCode;
    @BindView(R2.id.cl_third_login)
    ConstraintLayout mClThirdLogin;
    @BindView(R2.id.ll_bottom)
    LinearLayout mLlBottom;

    private int mCountryCode = 6541;//国家代码,默认中国
    private String mRegionCode = "86";//手机区域代码，默认中国+86
    private String mPhone;//用户输入的手机号码
    private LoadingDialog mLoadingDialog;
    public static final int requestCode = 0;

    public static LoginByPhoneFragment newInstance() {
        Bundle args = new Bundle();
        LoginByPhoneFragment fragment = new LoginByPhoneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private final ILoginPresenter iLoginPresenter = new ImpLoginPresenter(this);

    private boolean isConfirm = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        initUserAgreement();//底部用户协议颜色和跳转
        mLoadingDialog = new LoadingDialog(_mActivity);

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
                iLoginPresenter.v2pCheckPhoneEmpty(s.toString());
            }
        });

        SoftKeyBoardListener.setListener(_mActivity, new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {
                mClThirdLogin.setVisibility(View.GONE);
                mLlBottom.animate().translationY(-height).setDuration(0).start();
            }

            @Override
            public void keyBoardHide(int height) {
                mClThirdLogin.setVisibility(View.VISIBLE);
                mLlBottom.animate().translationY(0).start();
            }
        });
    }

    private void initUserAgreement() {
        SpannableString spanString = new SpannableString(getResources().getString(R.string.module_mine_user_agreement));
        spanString.setSpan(new AgreementClickable(), 7, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvUserAgreement.setText(spanString);
        mTvUserAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        mTvUserAgreement.setHighlightColor(Color.TRANSPARENT);
    }

    /**
     * 发送验证码
     */
    @OnClick(R2.id.iv_next)
    public void onNext() {
        iLoginPresenter.v2pCheckPhoneValid(isConfirm, mCountryCode, mPhone, Constant.TYPE_SEND);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 处理返回事件
     */
    @Override
    public boolean onBackPressedSupport() {
        _mActivity.finish();
        return true;
    }

    /**
     * 密码登录
     */
    @OnClick(R2.id.tv_login_by_psw)
    public void onLoginByPsw() {
        start(LoginByPasswordFragment.newInstance());
    }

    /**
     * 是否同意用户协议
     */
    @OnClick(R2.id.iv_confirm)
    public void onConfirmAgreement() {
        mIvConfirm.setImageResource(isConfirm ? R.drawable.module_mine_icon_control_unselected : R.drawable.module_mine_icon_control_selected);
        isConfirm = !isConfirm;
    }

    @OnClick(R2.id.iv_back)
    public void onViewClicked() {
        _mActivity.finish();
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
        start(VerificationCodeFragment.newInstance(mCountryCode, mPhone, Constant.TYPE_SEND));
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
        startForResult(SelectCountryFragment.newInstance(mCountryCode), requestCode);
    }

    @OnClick(R2.id.iv_login_wechat)
    public void onWechatLogin() {
        ToastUtil.show("微信登录");
    }

    @OnClick(R2.id.iv_login_qq)
    public void onQQLogin() {
        ToastUtil.show("QQ登录");
    }

    @OnClick(R2.id.iv_login_weibo)
    public void onWeiboLogin() {
        ToastUtil.show("微博登陆");
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

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (data != null) {
            //改变了选中国家
            mCountryCode = data.getInt("code");
            mRegionCode = data.getString("regionCode").substring(2);
            mTvCountryCode.setText("+" + mRegionCode);
        }
    }
}
