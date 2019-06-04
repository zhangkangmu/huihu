package com.huihu.module_mine.verificationcode.view;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.loginbyphone.view.customize.RectangleBoxEditText;
import com.huihu.module_mine.resetpassword.view.ResetPasswordFragment;
import com.huihu.module_mine.verificationcode.presenter.ImpVerificationCodePresenter;
import com.huihu.module_mine.verificationcode.verificationcodeinterface.IVerificationCodePresenter;
import com.huihu.module_mine.verificationcode.verificationcodeinterface.IVerificationCodeView;
import com.huihu.uilib.customize.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class VerificationCodeFragment extends BaseFragment implements IVerificationCodeView {
    private final IVerificationCodePresenter iVerificationCodePresenter = new ImpVerificationCodePresenter(this);
    Unbinder unbinder;
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.tv_hint)
    TextView mTvHint;
    @BindView(R2.id.tv_countdown)
    TextView mTvCountdown;
    @BindView(R2.id.tv_reacquire_code)
    TextView mTvReacquireCode;
    @BindView(R2.id.et_rectangle)
    RectangleBoxEditText mEtRectangle;

    private static final String COUNTRYCODE = "countryCode";
    private static final String PHONE = "phone";
    private static final String SENDTYPE = "sendType";

    public static final int TYPE_RESET = 2;
    public static final int TYPE_LOGIN = 9;

    private String mPhone;
    private String mVcode;
    private LoadingDialog mLoadingDialog;

    public static VerificationCodeFragment newInstance(int countryCode, String phone, int sendType) {
        VerificationCodeFragment fragment = new VerificationCodeFragment();
        Bundle args = new Bundle();
        args.putInt(COUNTRYCODE, countryCode);
        args.putString(PHONE, phone);
        args.putInt(SENDTYPE, sendType);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_verification, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        final int countryCode = getArguments().getInt(COUNTRYCODE);
        final int sendType = getArguments().getInt(SENDTYPE);
        mLoadingDialog = new LoadingDialog(_mActivity, false, null);

        showSoftInput(mEtRectangle);

        initTitle(sendType);
        initHint();
        startDownCount();


        mEtRectangle.setOnCheckListener(new RectangleBoxEditText.OnCheckListener() {
            @Override
            public void onTextFull(String text) {
                mVcode = text;
                iVerificationCodePresenter.v2pCheckVerification(countryCode, text, sendType, mPhone);
            }
        });
    }

    private void initTitle(int sendType) {
        if (sendType == TYPE_RESET) {
            mTvTitle.setText(getResources().getString(R.string.module_mine_title_approved));
        } else if (sendType == TYPE_LOGIN) {
            mTvTitle.setText(getResources().getString(R.string.module_mine_title_verification));
        }
    }

    private void initHint() {
        mPhone = getArguments().getString(PHONE);
        String formatStr = String.format(getResources().getString(R.string.module_mine_hint_send), mPhone);
        SpannableString spannableString = new SpannableString(formatStr);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.module_mine_yellow));
        spannableString.setSpan(colorSpan, 14, 14 + mPhone.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTvHint.setText(spannableString);
    }

    /**
     * 开始倒计时
     */
    private void startDownCount() {
        mTimer.start();
    }

    private void cancelCountdown() {
        mTimer.cancel();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        cancelCountdown();
    }

    @OnClick(R2.id.iv_back)
    public void onBack() {
        pop();
    }

    @OnClick(R2.id.tv_reacquire_code)
    public void onResetCode() {
        startDownCount();
    }

    private CountDownTimer mTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mTvCountdown.setVisibility(View.VISIBLE);
            mTvReacquireCode.setVisibility(View.GONE);
            mTvCountdown.setText((millisUntilFinished / 1000) + "s");
        }

        @Override
        public void onFinish() {
            mTvCountdown.setVisibility(View.GONE);
            mTvReacquireCode.setVisibility(View.VISIBLE);
        }
    };

    @Override
    public void p2vStartReset() {
        start(ResetPasswordFragment.newInstance(mPhone, mVcode));
    }

    @Override
    public void p2vShowLoadingDialog() {
        mLoadingDialog.showDialog();
    }

    @Override
    public void p2vDismissLoadingDialog() {
        mLoadingDialog.dismissDialog();
    }

    @Override
    public void p2vShowToast(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    public void p2vFinishLogin() {
        hideSoftInput();
        _mActivity.finish();
    }

    @Override
    public void p2vClearInput() {
        mEtRectangle.setText("");
    }
}
