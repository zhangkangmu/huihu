package com.huihu.module_circle.createcircle.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.huihu.commonlib.base.BaseFragment;
import com.huihu.uilib.util.ImgTools;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.createcircle.createcircleinterface.ICreateCirclePresenter;
import com.huihu.module_circle.createcircle.createcircleinterface.ICreateCircleView;
import com.huihu.module_circle.createcircle.entity.CreateCircleInfo;
import com.huihu.module_circle.createcircle.entity.CreateCircleSubCode;
import com.huihu.module_circle.createcircle.presenter.ImpCreateCirclePresenter;
import com.huihu.module_circle.createcircle.view.ui.DeleteContent_Edit;
import com.huihu.module_circle.createcirclesuccess.view.CreateCircleSuccessFragment;
import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.album.view.AlbumActivity;
import com.huihu.uilib.customize.CornerImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CreateCircleFragment extends BaseFragment implements ICreateCircleView,AlbumActivity.OnSelectEndListener {
    private final ICreateCirclePresenter iCreateCirclePresenter = new ImpCreateCirclePresenter(this);
    @BindView(R2.id.iv_back)
    ImageView ivBack;
    @BindView(R2.id.tv_base)
    TextView tvBase;
    @BindView(R2.id.post_photo)
    CornerImageView post_photo;
    @BindView(R2.id.iv_camera)
    ImageView iv_camera;
    @BindView(R2.id.tv_post_photo)
    TextView tv_post_photo;
    @BindView(R2.id.cons_next)
    ConstraintLayout cons_next;
    @BindView(R2.id.step_one)
    View step_one;
    @BindView(R2.id.step_two)
    View step_two;
    @BindView(R2.id.step_three)
    View step_three;
    @BindView(R2.id.tv_number)
    TextView tv_number;
    @BindView(R2.id.edittext)
    DeleteContent_Edit iv_delete;
    @BindView(R2.id.tv_name_circle)
    TextView tv_name_circle;
    @BindView(R2.id.tv_instruction_circle)
    TextView tv_instruction_circle;
    @BindView(R2.id.edittext_three)
    EditText edittext_three;
    @BindView(R2.id.tv_number_three)
    TextView tv_number_three;
    @BindView(R2.id.iv_next)
    ImageView iv_next;
    private Unbinder unbinder;
    private int currentStep=1;
    private JsonObject jsonObject;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_circle_circle_create, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private void initData() {
        jsonObject = new JsonObject();
        TextViewUtils.setTextFakeBold(tvBase);
        TextViewUtils.setTextFakeBold(tv_post_photo);
        TextViewUtils.setTextFakeBold(tv_name_circle);
        TextViewUtils.setTextFakeBold(tv_instruction_circle);
        currentStep=1;
        handleStep(currentStep);
        iv_delete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                tv_number.setText(""+length);
                if (length>15){
                    tv_number.setTextColor(getActivity().getResources().getColor(R.color.commonlib_red_warning));
                    cons_next.setVisibility(View.GONE);
                }else {
                    if (length==0){
                        cons_next.setVisibility(View.GONE);
                    }else {
                        cons_next.setVisibility(View.VISIBLE);
                    }
                    tv_number.setTextColor(getActivity().getResources().getColor(R.color.gray_two));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edittext_three.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                if (length>200){
                    tv_number_three.setTextColor(getActivity().getResources().getColor(R.color.commonlib_red_warning));
                    tv_number_three.setText("已超出"+(length-200)+"个字");
                    cons_next.setVisibility(View.GONE);
                }else {
                    tv_number_three.setTextColor(getActivity().getResources().getColor(R.color.gray_two));
                    tv_number_three.setText(length+" / 200");
                    if (length!=0) {
                        cons_next.setVisibility(View.VISIBLE);
                    }else {
                        cons_next.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
    @OnClick({R2.id.iv_back,R2.id.post_photo,R2.id.cons_next})
    public void click(View view){
        int viewId=view.getId();
        if (viewId==R.id.iv_back) {
           handleback();
        }else if(viewId==R.id.post_photo){
            AlbumActivity.openSelf(getContext(), 1,this);
        }else if(viewId==R.id.cons_next){
            if (currentStep==1){
                currentStep=2;
                handleStep(currentStep);
            }else if(currentStep==2){
                currentStep=3;
                jsonObject.addProperty("circleName",iv_delete.getText().toString().trim());
                handleStep(currentStep);
            }else if (currentStep==3){
                jsonObject.addProperty("introduce",edittext_three.getText().toString().trim());
                jsonObject.addProperty("circleUid", SPUtils.getInstance().getCurrentUid());
                iCreateCirclePresenter.v2pCreateCircle(jsonObject);
            }
        }
    }

    private void handleStep(int currentStep) {
    if (currentStep==1){
        step_one.setVisibility(View.VISIBLE);
        step_two.setVisibility(View.GONE);
        step_three.setVisibility(View.GONE);
        cons_next.setVisibility(View.GONE);
        iv_next.setImageResource(R.drawable.module_circle_right_arrow_32_white_def);
        tvBase.setText(getActivity().getResources().getString(R.string.create_circle)+"(1/3)");
    }else if (currentStep==2){
        step_one.setVisibility(View.GONE);
        step_two.setVisibility(View.VISIBLE);
        step_three.setVisibility(View.GONE);
        if (iv_delete.length()==0) {
            cons_next.setVisibility(View.GONE);
        }else {
            cons_next.setVisibility(View.VISIBLE);
        }
        tvBase.setText(getActivity().getResources().getString(R.string.create_circle)+"(2/3)");
        iv_next.setImageResource(R.drawable.module_circle_right_arrow_32_white_def);
    }else if (currentStep==3){
        tvBase.setText(getActivity().getResources().getString(R.string.create_circle)+"(3/3)");
        step_two.setVisibility(View.GONE);
        step_three.setVisibility(View.VISIBLE);
        if (edittext_three.length()==0) {
            cons_next.setVisibility(View.GONE);
        }else {
            cons_next.setVisibility(View.VISIBLE);
        }
        step_one.setVisibility(View.GONE);
        iv_next.setImageResource(R.drawable.module_circle_correct_32_white_def);
    }
    }

    private void handleback() {
        if (currentStep==1) {
            pop();
        }else if (currentStep==2){
            currentStep=1;
            handleStep(currentStep);
            cons_next.setVisibility(View.VISIBLE);
        }else if (currentStep==3){
            currentStep=2;
            handleStep(currentStep);
            cons_next.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onSelectEnd(List<AlbumImageBean> beanList) {
        iv_camera.setSelected(true);
        tv_post_photo.setText(R.string.choose_photo);
        ImgTools.showImageView(getActivity(),beanList.get(0).getPath(),post_photo);
        jsonObject.addProperty("imageUrl",beanList.get(0).getPath());
        jsonObject.addProperty("height",0);
        jsonObject.addProperty("width",0);
        cons_next.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onBackPressedSupport() {
        handleback();
        return true;
    }

    @Override
    public void p2vReturnCircleInfo(CreateCircleInfo createCircleInfo) {
        start(CreateCircleSuccessFragment.newInstance(createCircleInfo.getCircleId()));
    }

    @Override
    public void p2vReturnCircleInfoError(String subCode) {
        if (subCode== CreateCircleSubCode.CreateLimited){
            ToastUtil.show("您创建的圈子数已达上限");
        }
    }
}
