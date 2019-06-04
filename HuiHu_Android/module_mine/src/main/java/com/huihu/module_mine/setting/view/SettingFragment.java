package com.huihu.module_mine.setting.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.huihu.commonlib.base.BaseApplication;
import com.huihu.commonlib.base.BaseFragment;
import com.huihu.commonlib.simpleRouter.SimpleRouter;
import com.huihu.commonlib.utils.Constant;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_mine.AboutUsFragment;
import com.huihu.module_mine.R;
import com.huihu.module_mine.R2;
import com.huihu.module_mine.bindsocial.view.BindSocialFragment;
import com.huihu.module_mine.editnewphone.view.EditNewPhoneFragment;
import com.huihu.module_mine.editprofile.view.EditProfileFragment;
import com.huihu.module_mine.feedback.view.FeedbackFragment;
import com.huihu.module_mine.setting.presenter.ImpSettingPresenter;
import com.huihu.module_mine.setting.settinginterface.ISettingPresenter;
import com.huihu.module_mine.setting.settinginterface.ISettingView;
import com.huihu.uilib.RouterReDefine;
import com.huihu.uilib.customize.HHAlertDialog;
import com.huihu.uilib.evenbusBean.NotificationRefresh;
import com.wangjing.module_push.Push;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public class SettingFragment extends BaseFragment implements ISettingView {
    private final ISettingPresenter iSettingPresenter = new ImpSettingPresenter(this);

    @BindView(R2.id.iv_info_icon)
    ImageView mIvInfoIcon;
    @BindView(R2.id.cl_edit_profile)
    ConstraintLayout mClEditProfile;
    @BindView(R2.id.cl_set_psw)
    ConstraintLayout mClSetPsw;
    @BindView(R2.id.cl_bind_social)
    ConstraintLayout mClBindSocial;
    @BindView(R2.id.cl_set_message)
    ConstraintLayout mClSetMessage;
    @BindView(R2.id.cl_feedback)
    ConstraintLayout mClFeedback;
    @BindView(R2.id.cl_check_update)
    ConstraintLayout mClCheckUpdate;
    @BindView(R2.id.cl_user_protocol)
    ConstraintLayout mClUserProtocol;
    @BindView(R2.id.cl_about_us)
    ConstraintLayout mClAboutUs;
    @BindView(R2.id.cl_exit_login)
    ConstraintLayout mClExitLogin;
    Unbinder unbinder;

    public static SettingFragment newInstance() {
        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_mine_fragment_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R2.id.cl_edit_profile, R2.id.cl_set_psw, R2.id.cl_bind_social, R2.id.cl_set_message, R2.id.cl_feedback, R2.id.cl_check_update, R2.id.cl_user_protocol, R2.id.cl_about_us, R2.id.cl_exit_login})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.cl_edit_profile) {
            start(EditProfileFragment.newInstance());
        } else if (i == R.id.cl_set_psw) {
            //修改密码与修改手机号界面共用
            start(EditNewPhoneFragment.newInstance(Constant.TYPE_CHANGE_PASSWORD));
        } else if (i == R.id.cl_bind_social) {
            start(BindSocialFragment.newInstance());
        } else if (i == R.id.cl_set_message) {
            SupportFragment pushSettingFragment = (SupportFragment) SimpleRouter.getInstance().getFragment(RouterReDefine.PUSHSETTING__FRAGMENT);
            start(pushSettingFragment);
        } else if (i == R.id.cl_feedback) {
            start(FeedbackFragment.newInstance());
        } else if (i == R.id.cl_check_update) {

        } else if (i == R.id.cl_user_protocol) {

        } else if (i == R.id.cl_about_us) {
            start(new AboutUsFragment());
        } else if (i == R.id.cl_exit_login) {
            HHAlertDialog hhAlertDialog = new HHAlertDialog(_mActivity);
            hhAlertDialog.setTitle("退出登录");
            hhAlertDialog.setMessage("是否确认退出登录？");
            hhAlertDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    iSettingPresenter.v2pExitLogin(String.valueOf(SPUtils.getInstance().getCurrentUid()));
                    SPUtils.getInstance().saveCurrentUid(Constant.DEFAULT_UID);
                    EventBus.getDefault().post(new NotificationRefresh());
                    Push.initUser(BaseApplication.getApplication());
                }
            });
            hhAlertDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            hhAlertDialog.showDialog();
        }
    }

}
