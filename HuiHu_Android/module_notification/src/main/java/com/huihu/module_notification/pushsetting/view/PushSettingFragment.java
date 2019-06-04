package com.huihu.module_notification.pushsetting.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huihu.commonlib.simpleRouter.SimpleRouterClassRegister;
import com.huihu.commonlib.simpleRouter.SimpleRouterObj;
import com.huihu.commonlib.utils.TextViewUtils;
import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.pushsetting.adapter.PushSettingAdapter;
import com.huihu.module_notification.pushsetting.entity.PushSettingBean;
import com.huihu.module_notification.pushsetting.presenter.ImpPushSettingPresenter;
import com.huihu.module_notification.pushsetting.pushsettinginterface.IPushSettingPresenter;
import com.huihu.module_notification.pushsetting.pushsettinginterface.IPushSettingView;
import com.huihu.uilib.RouterReDefine;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

@SimpleRouterClassRegister(key = RouterReDefine.PUSHSETTING__FRAGMENT, type = SimpleRouterObj.FRAGMENT)
public class PushSettingFragment extends SupportFragment implements IPushSettingView {

    private final IPushSettingPresenter iPushSettingPresenter = new ImpPushSettingPresenter(this);

    Unbinder unbinder;
    @BindView(R2.id.rv_content)
    RecyclerView rvContent;
    @BindView(R2.id.tv_title)
    TextView tvTitle;

    private PushSettingAdapter mAdapter;

    public static PushSettingFragment newInstance() {
        return new PushSettingFragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.module_notification_fragment_push_setting, container, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvContent.setLayoutManager(layoutManager);
        mAdapter = new PushSettingAdapter(iPushSettingPresenter);
        rvContent.setAdapter(mAdapter);
        iPushSettingPresenter.v2pGetPushSetting();
        TextViewUtils.setTextFakeBold(tvTitle);
    }

    @Override
    public void p2vShowPushSetting(List<PushSettingBean> beans) {
        mAdapter.setData(beans);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void p2vChangeSettingFail(PushSettingBean bean) {
        int position = mAdapter.getData().indexOf(bean);
        mAdapter.notifyItemChanged(position, bean);
    }

    @OnClick({R2.id.iv_back})
    public void onClick(View v){
        int id = v.getId();
        if (id == R.id.iv_back){
            pop();
        }
    }
}
