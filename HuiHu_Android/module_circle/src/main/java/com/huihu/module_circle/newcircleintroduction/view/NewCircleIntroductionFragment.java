package com.huihu.module_circle.newcircleintroduction.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.huihu.commonlib.base.BaseFragment;
import com.huihu.module_circle.R;
import com.huihu.module_circle.R2;
import com.huihu.module_circle.newcircleintroduction.entity.CircleListInfo;
import com.huihu.module_circle.newcircleintroduction.newcircleintroductioninterface.INewCircleIntroductionPresenter;
import com.huihu.module_circle.newcircleintroduction.newcircleintroductioninterface.INewCircleIntroductionView;
import com.huihu.module_circle.newcircleintroduction.presenter.ImpNewCircleIntroductionPresenter;
import com.huihu.uilib.customize.RoundImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class NewCircleIntroductionFragment extends BaseFragment implements INewCircleIntroductionView {
    private final INewCircleIntroductionPresenter iNewCircleIntroductionPresenter = new ImpNewCircleIntroductionPresenter(this);
    private static final String CIRCLEID = "circleId";
    private static final String UID = "uid";
    private Unbinder unbinder;
    private Long mCircleId;
    private Long mUid;
    @BindView(R2.id.rv_icon_user)
    RoundImageView rv_icon_user;
    @BindView(R2.id.tv_name)
    TextView tv_name;
    @BindView(R2.id.tv_icon_official)
    TextView tv_icon_official;
    @BindView(R2.id.tv_time)
    TextView tv_time;
    @BindView(R2.id.tv_content)
    TextView tv_content;

    public static NewCircleIntroductionFragment newInstance(Long circleid, Long uid) {
        Bundle args = new Bundle();
        args.putLong(CIRCLEID, circleid);
        args.putLong(UID, uid);
        NewCircleIntroductionFragment fragment = new NewCircleIntroductionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_circle_fragment_circle_introduction, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCircleId = getArguments().getLong(CIRCLEID, 0);
        mUid = getArguments().getLong(UID, 0);
        iNewCircleIntroductionPresenter.v2pRequestIntroduction(mCircleId, mUid);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onBackPressedSupport() {
        return false;
    }

    @Override
    public void p2vRturnIntroduction(CircleListInfo info) {
        Glide.with(_mActivity).load(info.getAuthor().getUserHeadImage()).into(rv_icon_user);
        //设置时间
        long createTime = info.getCreateTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date(createTime);
        tv_time.setText(simpleDateFormat.format(date));
        if (info.getCircleType() == 2) {
            tv_icon_official.setVisibility(View.VISIBLE);
        } else {
            tv_icon_official.setVisibility(View.INVISIBLE);
        }
        tv_name.setText(info.getAuthor().getNickName());
        tv_content.setText(info.getIntroduce());
    }
}
