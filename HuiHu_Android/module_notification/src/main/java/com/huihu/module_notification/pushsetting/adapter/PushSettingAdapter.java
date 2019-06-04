package com.huihu.module_notification.pushsetting.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.huihu.module_notification.R;
import com.huihu.module_notification.R2;
import com.huihu.module_notification.pushsetting.entity.PushSettingBean;
import com.huihu.module_notification.pushsetting.pushsettinginterface.IPushSettingPresenter;
import com.kyleduo.switchbutton.SwitchButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * create by wangjing on 2019/3/19 0019
 * description:
 */
public class PushSettingAdapter extends RecyclerView.Adapter<PushSettingAdapter.PushSettingViewHolder> {


    private List<PushSettingBean> beans;
    private IPushSettingPresenter iPushSettingPresenter;

    private View.OnClickListener switchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getTag() instanceof PushSettingBean && v instanceof SwitchButton){
                SwitchButton swith = (SwitchButton) v;
                PushSettingBean bean = (PushSettingBean) v.getTag();
                bean.setStatus(swith.isChecked() ? 1 : 0);
                iPushSettingPresenter.v2pUpdatePushSetting(bean);
            }
        }
    };

    public PushSettingAdapter(IPushSettingPresenter iPushSettingPresenter) {
        this.iPushSettingPresenter = iPushSettingPresenter;
    }

    public void setData(List<PushSettingBean> beans) {
        this.beans = beans;
    }

    public List<PushSettingBean> getData() {
        return beans;
    }

    @NonNull
    @Override
    public PushSettingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.module_notification_item_push_setting, viewGroup, false);
        return new PushSettingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PushSettingViewHolder viewHolder, int i) {
        PushSettingBean bean = beans.get(i);
        viewHolder.tvType.setText(bean.getNoticeName());
        viewHolder.switchSetting.setCheckedImmediately(bean.getStatus() == 1);
        viewHolder.switchSetting.setTag(bean);
        viewHolder.switchSetting.setOnClickListener(switchListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PushSettingViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.size() > 0){
            if (payloads.get(0) instanceof PushSettingBean){
                holder.switchSetting.toggleImmediately();
            }
        } else {
            this.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return beans == null ? 0 : beans.size();
    }

    static class PushSettingViewHolder extends RecyclerView.ViewHolder {

        @BindView(R2.id.tv_type)
        TextView tvType;
        @BindView(R2.id.switch_setting)
        SwitchButton switchSetting;

        public PushSettingViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
