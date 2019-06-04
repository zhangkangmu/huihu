package com.huihu.module_circle.circlesetting.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.commonlib.utils.ToastUtil;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.circlesetting.circlesettinginterface.ICircleSettingPresenter;
import com.huihu.module_circle.circlesetting.circlesettinginterface.ICircleSettingView;
import com.huihu.module_circle.circlesetting.presenter.ImpCircleSettingPresenter;
import com.huihu.module_circle.createcircle.view.ui.DeleteContent_Edit;
import com.huihu.module_circle.membermanagement.view.fragment.MemberManagementFragment;
import com.huihu.uilib.album.entity.AlbumImageBean;
import com.huihu.uilib.album.view.AlbumActivity;
import com.huihu.uilib.customize.CornerImageView;
import com.huihu.uilib.util.ImgTools;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CircleSettingFragment extends BaseFragment implements ICircleSettingView,AlbumActivity.OnSelectEndListener{
    private final ICircleSettingPresenter iCircleSettingPresenter = new ImpCircleSettingPresenter(this);
    @BindView(R2.id.tv_base)
    TextView tv_base;
    private Unbinder unbinder;
    private long circleId;
    private int memberType;

    //圈子简介页id
    @BindView(R2.id.ic_edit_introduction)
    View ic_edit_introduction;
    @BindView(R2.id.tv_save)
    View tv_save;
    @BindView(R2.id.tv_introduction)
    TextView tv_introduction;
    //第一页id
    @BindView(R2.id.tv_manager_circle)
    TextView tv_manager_circle;
    @BindView(R2.id.step_one)
    View step_one;
    @BindView(R2.id.ll_introduction)
    View ll_introduction;
    @BindView(R2.id.tv_user_agreement)
    TextView tv_user_agreement;
    @BindView(R2.id.tv_line)
    TextView tv_line;

    @BindView(R2.id.post_photo)
    CornerImageView post_photo;
    @BindView(R2.id.iv_camera)
    ImageView iv_camera;
    private JsonObject jsonObject;
    @BindView(R2.id.edittext)
    EditText edittext;
    @BindView(R2.id.tv_number)
    TextView tv_number;

    public static CircleSettingFragment newInstance(int circleId,int memberType) {
        Bundle args = new Bundle();
        args.putInt("circleId",circleId);
        args.putInt("memberType",memberType);
        CircleSettingFragment fragment = new CircleSettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        circleId = getArguments().getInt("circleId", 0);
        memberType = getArguments().getInt("memberType", 0);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_circle_fragment_circle_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitle();
        initData();
    }

    private void initData() {
        jsonObject = new JsonObject();
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.length();
                if (length>200){
                    tv_number.setTextColor(getActivity().getResources().getColor(R.color.commonlib_red_warning));
                    tv_number.setText("已超出"+(length-200)+"个字");
                }else {
                    tv_number.setTextColor(getActivity().getResources().getColor(R.color.gray_two));
                    tv_number.setText(length+" / 200");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initTitle() {
        ic_edit_introduction.setVisibility(View.INVISIBLE);
        tv_manager_circle.setVisibility(View.VISIBLE);
        tv_base.setText("圈子设置");
    }

    @OnClick({R2.id.tv_manager_circle,R2.id.iv_back,R2.id.tv_introduction,R2.id.tv_user_agreement,R2.id.tv_save,R2.id.tv_disband,R2.id.post_photo})
    public void onViewClicked(View v) {
        int id = v.getId();
        if (id == R.id.tv_manager_circle) {
            ToastUtil.show("管理");
            start(MemberManagementFragment.newInstance(circleId,memberType));
        } else if (id == R.id.iv_back) {
            pop();
        }else if (id == R.id.tv_introduction) {
            ToastUtil.show("开始编写简介");
            showEdit();
        }else if (id == R.id.tv_user_agreement) {
            ToastUtil.show("用户协议");
        }else if (id == R.id.tv_save) {
            //圈子简介点击保存按钮
            ToastUtil.show("提交");
            jsonObject.addProperty("circleId",""+circleId);
            jsonObject.addProperty("introduce",edittext.getText().toString().trim());
            jsonObject.addProperty("uid", SPUtils.getInstance().getCurrentUid());
            iCircleSettingPresenter.v2pEditIntroduct(jsonObject);
        }else if (id == R.id.tv_disband) {
            ToastUtil.show("解散圈子");
        }else if (id == R.id.post_photo) {
            ToastUtil.show("选择照片");
            AlbumActivity.openSelf(getContext(), 1,this);
        }
    }


    private void showEdit() {
        step_one.setVisibility(View.INVISIBLE);
        ll_introduction.setVisibility(View.INVISIBLE);
        tv_user_agreement.setVisibility(View.INVISIBLE);
        ic_edit_introduction.setVisibility(View.VISIBLE);
        tv_manager_circle.setVisibility(View.INVISIBLE);
        tv_line.setVisibility(View.INVISIBLE);
        tv_save.setVisibility(View.VISIBLE);
        tv_base.setText("圈子简介");

    }

    @Override
    public void onSelectEnd(List<AlbumImageBean> beanList) {
        iv_camera.setSelected(true);
        ImgTools.showImageView(getActivity(),beanList.get(0).getPath(),post_photo);
        jsonObject.addProperty("imageUrl",beanList.get(0).getPath());
        jsonObject.addProperty("height",0);
        jsonObject.addProperty("width",0);
    }
}
