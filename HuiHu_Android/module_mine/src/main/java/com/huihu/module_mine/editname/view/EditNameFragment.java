package com.huihu.module_mine.editname.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.editname.editnameinterface.IEditNamePresenter;
import com.huihu.module_mine.editname.editnameinterface.IEditNameView;
import com.huihu.module_mine.editname.presenter.ImpEditNamePresenter;
import com.huihu.uilib.customize.LoadingDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class EditNameFragment extends BaseFragment implements IEditNameView{

    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_right_operating)
    TextView mTvRightOperation;
    @BindView(R2.id.et_name)
    EditText mEtname;
    @BindView(R2.id.iv_clear)
    ImageView mIvClear;
    @BindView(R2.id.tv_words)
    TextView mTvWords;
    private LoadingDialog mLoadingDialog;

    Unbinder unbinder;
    private final IEditNamePresenter mIEditNamePresenter = new ImpEditNamePresenter(this);

    public static final String NAME = "name";
    public static final int RESULT_UPDATE_NAME_SUCCESS = 101;
    private static final int NAME_SIZE = 10;

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
        //mTvTitle.setText(getResources().getText(R.string.module_mine_edit_name));
        mTvTitle.setText("昵称");
        mEtname.setText(getArguments().getString(NAME));
        mTvRightOperation.setVisibility(View.VISIBLE);
        mTvWords.setText(mEtname.getText().length() + "/" + NAME_SIZE);

        mLoadingDialog = new LoadingDialog(_mActivity, false, null);

        mEtname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTvWords.setText(s.length() + "/" + NAME_SIZE);
                if(s.length() > NAME_SIZE){
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

    @OnClick({R2.id.iv_back, R2.id.tv_right_operating, R2.id.iv_clear})
    public void onViewClick(View v){
        int viewId = v.getId();
        if (viewId == R.id.iv_back){
            pop();
        }else if (viewId == R.id.tv_right_operating) {
            String name = mEtname.getText().toString();
            if(TextUtils.isEmpty(name)){
                ToastUtil.show("用户名为空");
            }else if(name.length() > NAME_SIZE) {
                ToastUtil.show("用户名字数超出范围");
            }else { //上传更新用户名
                mLoadingDialog.showDialog();
                mIEditNamePresenter.v2pPutUpdateName(name);
            }
        }else if (viewId == R.id.iv_clear) {
            mEtname.setText("");
        }
    }

    public static EditNameFragment newInstance(String name) {
        Bundle args = new Bundle();
        args.putString(NAME,name);
        EditNameFragment fragment = new EditNameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void p2vPutUpdateNameSucess(){
        mLoadingDialog.dismissDialog();
        pop();
        Bundle resultArgs = new Bundle();
        resultArgs.putString(NAME,mEtname.getText().toString());
        setFragmentResult(RESULT_UPDATE_NAME_SUCCESS,resultArgs);
    }
    public void p2vPutUpdateNameError(String error){
        mLoadingDialog.dismissDialog();
        showToast(error);
    }

    public void p2vNameHasUsed(){
        mLoadingDialog.dismissDialog();
        showToast("昵称已被使用");
    }

    public void showToast(String text){
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }
}
