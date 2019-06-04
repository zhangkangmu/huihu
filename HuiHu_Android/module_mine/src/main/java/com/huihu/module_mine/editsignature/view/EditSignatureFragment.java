package com.huihu.module_mine.editsignature.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.editname.view.EditNameFragment;
import com.huihu.module_mine.editsignature.editsignatureinterface.IEditSignaturePresenter;
import com.huihu.module_mine.editsignature.editsignatureinterface.IEditSignatureView;
import com.huihu.module_mine.editsignature.presenter.ImpEditSignaturePresenter;
import com.huihu.uilib.customize.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class EditSignatureFragment extends BaseFragment implements IEditSignatureView{
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_right_operating)
    TextView mTvRightOperation;
    @BindView(R2.id.et_name)
    EditText mEtSignature;
    @BindView(R2.id.iv_clear)
    ImageView mIvClear;
    @BindView(R2.id.tv_words)
    TextView mTvWords;

    private LoadingDialog mLoadingDialog;

    Unbinder unbinder;
    private final IEditSignaturePresenter iEditSignaturePresenter = new ImpEditSignaturePresenter(this);

    private static final int REQUEST_UPDATE_SIGNATURE= 13;
    private static final int RESULT_UPDATE_SIGNATURE_SUCCESS = 103;
    private static final String SIGNATURE = "signature";

    private static final int SIGN_SIZE = 30;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_edit_name, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void init() {
        mLoadingDialog = new LoadingDialog(_mActivity);
        //mTvTitle.setText(getResources().getText(R.string.module_mine_edit_name));
        mTvTitle.setText("个性签名");
        mEtSignature.setHint("记录你的签名吧");
        mEtSignature.setText(getArguments().getString(SIGNATURE));
        mTvRightOperation.setVisibility(View.VISIBLE);
        mTvWords.setText(mEtSignature.length()+ "/" + SIGN_SIZE);

        mEtSignature.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTvWords.setText(s.length() + "/" + SIGN_SIZE);
                if(s.length() > SIGN_SIZE){
                    mTvWords.setTextColor(getResources().getColor(R.color.commonlib_red_warning));
                }else {
                    mTvWords.setTextColor(getResources().getColor(R.color.module_mine_text_gray));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public static EditSignatureFragment newInstance(String signature) {
        Bundle args = new Bundle();
        args.putString(SIGNATURE, signature);
        EditSignatureFragment fragment = new EditSignatureFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick({R2.id.iv_back, R2.id.tv_right_operating, R2.id.iv_clear})
    public void onViewClick(View v){
        int viewId = v.getId();
        if (viewId == R.id.iv_back){
            pop();
        }else if (viewId == R.id.tv_right_operating) {
            String signature = mEtSignature.getText().toString();
            if(signature.length() > SIGN_SIZE){
                showToast("个性签名字数超出范围");
            }else {
                mLoadingDialog.showDialog();
                //上传更新签名
                iEditSignaturePresenter.v2pPutUpdateUserSignature(signature);
            }
        }else if (viewId == R.id.iv_clear) {
            mEtSignature.setText("");
        }
    }

    public void p2vPutUpdateUserSignatureSuccess(){
        mLoadingDialog.dismissDialog();
        Bundle resultArgs = new Bundle();
        resultArgs.putString(SIGNATURE, mEtSignature.getText().toString());
        setFragmentResult(RESULT_UPDATE_SIGNATURE_SUCCESS,resultArgs);
        pop();
    }

    public void p2vPutUpdateUserSignatureError(){
        mLoadingDialog.dismissDialog();
        showToast("更新个性签名失败");
    }

    public void showToast(String text){
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

}
