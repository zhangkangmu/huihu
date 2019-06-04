package com.huihu.module_mine.resetpassword.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.SoftKeyBoardListener;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.loginbypassword.view.LoginByPasswordFragment;
import com.huihu.module_mine.resetpassword.presenter.ImpResetPasswordPresenter;
import com.huihu.module_mine.resetpassword.resetpasswordinterface.IResetPasswordPresenter;
import com.huihu.module_mine.resetpassword.resetpasswordinterface.IResetPasswordView;
import com.huihu.uilib.customize.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ResetPasswordFragment extends BaseFragment implements IResetPasswordView {

    Unbinder unbinder;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.et_new_psw)
    EditText mEtNewPsw;
    @BindView(R2.id.iv_next)
    ImageView mIvNext;
    @BindView(R2.id.iv_psw_display)
    ImageView mIvPswDisplay;
    private String mNewPassword;
    private LoadingDialog mLoadingDialog;

    public static ResetPasswordFragment newInstance(String phone, String vCode) {
        ResetPasswordFragment fragment = new ResetPasswordFragment();
        Bundle args = new Bundle();
        args.putString("phone", phone);
        args.putString("vcode", vCode);
        fragment.setArguments(args);
        return fragment;
    }

    private final IResetPasswordPresenter iResetPasswordPresenter = new ImpResetPasswordPresenter(this);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_reset_psw, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        mTvTitle.setText(getResources().getString(R.string.module_mine_title_set_new_psw));
        mEtNewPsw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        mLoadingDialog = new LoadingDialog(_mActivity, false, null);
        SoftKeyBoardListener.setListener(_mActivity, mOnSoftKeyBoardChangeListener);
        mEtNewPsw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mNewPassword = s.toString().trim();
                iResetPasswordPresenter.v2pCheckNextState(mNewPassword);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        showSoftInput(mEtNewPsw);
    }

    private SoftKeyBoardListener.OnSoftKeyBoardChangeListener mOnSoftKeyBoardChangeListener = new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
        @Override
        public void keyBoardShow(int height) {
            if (mEtNewPsw == null || mIvNext == null) {
                return;
            }
            int[] location = new int[2];
            mEtNewPsw.getLocationOnScreen(location);
            mIvNext.animate().translationY(-height).setDuration(0).start();
        }

        @Override
        public void keyBoardHide(int height) {
            if (mEtNewPsw == null || mIvNext == null) {
                return;
            }
            mIvNext.animate().translationY(0).start();
        }
    };

    @OnClick(R2.id.iv_back)
    public void onBack() {
        popTo(LoginByPasswordFragment.class, false);
    }

    @Override
    public boolean onBackPressedSupport() {
        popTo(LoginByPasswordFragment.class, false);
        return true;
    }

    @OnClick(R2.id.iv_next)
    public void onNext() {
        String vcode = getArguments().getString("vcode");
        String phone = getArguments().getString("phone");
        iResetPasswordPresenter.v2pResetPsw(phone, mNewPassword, vcode);
    }

    @Override
    public void p2vShowLoadingDialog() {
        mLoadingDialog.showDialog();
    }

    @Override
    public void p2vDimissLoadingDialog() {
        mLoadingDialog.dismissDialog();
    }

    @Override
    public void p2vShowToast(String message) {
        ToastUtil.show(message);
    }

    @Override
    public void p2vSetNextClickAble() {
        mIvNext.setImageResource(R.drawable.module_mine_icon_next);
    }

    @Override
    public void p2vSetNextUnClickAble() {
        mIvNext.setImageResource(R.drawable.module_mine_icon_next_disabled);
    }

    @Override
    public void p2vFinish() {
        popTo(LoginByPasswordFragment.class, false);
    }

    private boolean isDisplay = false;

    @OnClick(R2.id.iv_psw_display)
    public void onDisplayPsw() {
        if (isDisplay) {
            mEtNewPsw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            mIvPswDisplay.setImageResource(R.drawable.module_mine_icon_hide);
        } else {
            mEtNewPsw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);

            mIvPswDisplay.setImageResource(R.drawable.module_mine_icon_display);
        }
        mEtNewPsw.setSelection(mEtNewPsw.getText().length());
        isDisplay = !isDisplay;
    }
}
