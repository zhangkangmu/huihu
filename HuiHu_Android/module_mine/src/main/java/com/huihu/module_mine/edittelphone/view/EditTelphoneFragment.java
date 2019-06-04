package com.huihu.module_mine.edittelphone.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.utils.Constant;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.editname.view.EditNameFragment;
import com.huihu.module_mine.editnewphone.view.EditNewPhoneFragment;
import com.huihu.module_mine.edittelphone.edittelphoneinterface.IEditTelphonePresenter;
import com.huihu.module_mine.edittelphone.edittelphoneinterface.IEditTelphoneView;
import com.huihu.module_mine.edittelphone.presenter.ImpEditTelphonePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class EditTelphoneFragment extends BaseFragment implements IEditTelphoneView{

    public static final String PHONE = "phone";

    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.ll_edit_tel)
    LinearLayout mLlEditTel;
    @BindView(R2.id.tv_phone_num)
    TextView mTvPhoneNum;

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_edit_tel, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initView() {
        mTvTitle.setText("手机号");
        //mTvTitle.setText(getResources().getText(R.string.module_mine_edit_name));
        String tel = getArguments().getString(PHONE);
        mTvPhoneNum.setText(tel);

    }

    public static EditTelphoneFragment newInstance(String phone) {
        Bundle args = new Bundle();
        args.putString(PHONE, phone);
        EditTelphoneFragment fragment = new EditTelphoneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick({R2.id.iv_back, R2.id.ll_edit_tel})
    public void onViewClick(View view){
        int viewId = view.getId();
        if(viewId == R.id.iv_back){
            pop();
        }else if (viewId == R.id.ll_edit_tel) {
            startForResult(EditNewPhoneFragment.newInstance(Constant.TYPE_CHANGE_PHONE),REQUEST_UPDATE_PHONE);
        }else {

        }
    }


    int REQUEST_UPDATE_PHONE = 12;
    int RESULT_UPDATE_PHONE_SUCCESS = 102;

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        if(requestCode == REQUEST_UPDATE_PHONE && resultCode == RESULT_UPDATE_PHONE_SUCCESS){
            pop();
            setFragmentResult(RESULT_UPDATE_PHONE_SUCCESS, data);

        }
    }
}
