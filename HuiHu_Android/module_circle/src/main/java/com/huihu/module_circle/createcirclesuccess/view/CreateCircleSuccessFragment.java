package com.huihu.module_circle.createcirclesuccess.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihu.commonlib.base.BaseFragment;
import com.huihu.uilib.util.ImgTools;
import com.huihu.commonlib.utils.SPUtils;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.circlelist.view.CircleListFragment;
import com.huihu.module_circle.createcirclesuccess.createcirclesuccessinterface.ICreateCircleSuccessPresenter;
import com.huihu.module_circle.createcirclesuccess.createcirclesuccessinterface.ICreateCircleSuccessView;
import com.huihu.module_circle.createcirclesuccess.entity.CreatedCircleInfo;
import com.huihu.module_circle.createcirclesuccess.presenter.ImpCreateCircleSuccessPresenter;
import com.huihu.module_circle.invitejoincircle.view.fragment.InviteJoinCircleFragment;
import com.huihu.uilib.customize.CornerImageView;
import com.huihu.uilib.customize.RoundImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class CreateCircleSuccessFragment extends BaseFragment implements ICreateCircleSuccessView{
    private final ICreateCircleSuccessPresenter iCreateCircleSuccessPresenter = new ImpCreateCircleSuccessPresenter(this);
    private Unbinder unbinder;
    private long circleId;
    @BindView(R2.id.civ_circle)
    CornerImageView civ_circle;
    @BindView(R2.id.tv_circle_name)
    TextView tv_circle_name;
    @BindView(R2.id.riv_circle)
    RoundImageView riv_circle;
    @BindView(R2.id.tv_number)
    TextView tv_number;

    public static CreateCircleSuccessFragment newInstance(long circleId) {
        CreateCircleSuccessFragment fragment = new CreateCircleSuccessFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("circleId", circleId);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_circle_circle_created_successful, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        iCreateCircleSuccessPresenter.v2pGetCircleInfo(circleId);
    }
    private void initData() {
        Bundle bundle = getArguments();
        if (null != bundle) {
            circleId = bundle.getLong("circleId");
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @Override
    public void p2vReturnCreatedCircleInfo(CreatedCircleInfo createdCircleInfo) {
        ImgTools.showImageView(getActivity(),createdCircleInfo.getImageUrl(),civ_circle);
        tv_circle_name.setText(createdCircleInfo.getCircleName());
        tv_number.setText(createdCircleInfo.getMemberNum()+"成员");
        ImgTools.showImageView(getActivity(),createdCircleInfo.getMembers().get(0).getUserHeadImage(),riv_circle);
    }
    @OnClick({R2.id.tv_complete,R2.id.tv_invite})
    public void Click(View view){
        int viewId = view.getId();
        if (viewId==R.id.tv_complete) {
            start(CircleListFragment.newInstance(circleId, SPUtils.getInstance().getCurrentUid()));
        }else if (viewId==R.id.tv_invite){
            start(InviteJoinCircleFragment.newInstance(true));
        }
    }
}
